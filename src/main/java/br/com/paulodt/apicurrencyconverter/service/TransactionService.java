package br.com.paulodt.apicurrencyconverter.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import okhttp3.*;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.paulodt.apicurrencyconverter.ApiCurrencyConverterApplication;
import br.com.paulodt.apicurrencyconverter.entity.Conversion;
import br.com.paulodt.apicurrencyconverter.entity.Transaction;
import br.com.paulodt.apicurrencyconverter.entity.User;
import br.com.paulodt.apicurrencyconverter.exceptionHandler.UserNotFoundException;
import br.com.paulodt.apicurrencyconverter.exceptionHandler.ValidationException;
import br.com.paulodt.apicurrencyconverter.repository.TransactionRepository;
import br.com.paulodt.apicurrencyconverter.repository.UserRepository;
import br.com.paulodt.apicurrencyconverter.util.UtilConversion;
import jakarta.validation.Valid;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private static Logger log = LoggerFactory.getLogger(ApiCurrencyConverterApplication.class);
    
    public enum currency{
        BRL,
        USD,
        EUR,
        JPY
    }

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @SuppressWarnings("null")
    public List<Transaction> create(Transaction transaction){
        transactionRepository.save(transaction);
        return list();
    }

    public List<Transaction> list(){
        Sort sort = Sort.by("Id").descending();
        return transactionRepository.findAll(sort);
    }

    public List<Transaction> listByUser(Long userId){
        return transactionRepository.findByUser(userId);
    }

    @SuppressWarnings("null")
    public List<Transaction> update(Transaction transaction){
        transactionRepository.save(transaction);
        return list();
    }

    @SuppressWarnings("null")
    public List<Transaction> delete(Long id){
        transactionRepository.deleteById(id);
        return list();
    }

    @SuppressWarnings("null")
    public void getRateConversion(@Valid Transaction transaction) throws Exception {
      
        log.info("method rateConversion - BEGIN");

        if(transaction != null && transaction.getUser() != null){
            List<User> users = userRepository.findAllById(Arrays.asList(transaction.getUser().getUserId()));
            if(users.isEmpty()){
                log.error("UserNotFoundException");
                throw new UserNotFoundException();
            }
            if(transaction.getOriginCurrency() == ""){
                log.error("ValidationException");
                throw new ValidationException("Origin Currency is mandatory.");
            }
            if(transaction.getDestinationCurrency() == ""){
                log.error("ValidationException");
                throw new ValidationException("Destination Currency is mandatory.");
            }

            currency[] currencies = currency.values();
            
            boolean validOriginCurrency = false;
            boolean validDestinationCurrency = false;

            for (currency option : currencies) {
                if(option.toString().equals(transaction.getOriginCurrency())){
                    validOriginCurrency = true;
                }
                if(option.toString().equals(transaction.getDestinationCurrency())){
                    validDestinationCurrency = true;
                }
            }
            if(!validDestinationCurrency  || !validOriginCurrency){
                throw new ValidationException("Destination or Origin Currency is wrong (Valid Options: BRL, USD, EUR, JPY)");
            }
        }
        log.info("Consult apilayer to get conversion rate - BEGIN");
        String url = "https://api.apilayer.com/fixer/convert?to="
            +transaction.getDestinationCurrency()+"&from="+transaction.getOriginCurrency()+"&amount="+transaction.getOriginValue();
        log.info("URL - " + url);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        
        Request request = new Request.Builder()
            .url(url)
            .addHeader("apikey", "PaIk95bwZCPFj4ZHA4bh0ooHxOPaLpLc")
            .get()
            .build();

        log.info("Consult apilayer to get conversion rate - END");
        Response response = client.newCall(request).execute();
        Gson g = new Gson();  
        
        String jsonEmString = UtilConversion.converteJsonEmString(response.body().string());
        Conversion conversion = g.fromJson(jsonEmString, Conversion.class); 
        if(conversion.getInfo() == null || conversion.getInfo().getRate() == null){
            log.error("ValidationException");
            log.error("Transaction " + transaction);
            throw new ValidationException("Problem to return currency rate. Probabily origin currency and/or destination currency is wrong.");
        }
        log.info("Conversion Rate " + conversion.getInfo().getRate());
        transaction.setConversionRate(Double.parseDouble(conversion.getInfo().getRate()));
        transaction.setDestinationValue(Double.parseDouble(conversion.getResult()));
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date dataFormatada = formato.parse(conversion.getDate()); 
        transaction.setDate(dataFormatada);
        log.info("method rateConversion - END");
    }
}
