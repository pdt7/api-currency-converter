package br.com.paulodt.apicurrencyconverter.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import okhttp3.*;
import com.google.gson.Gson;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.paulodt.apicurrencyconverter.entity.Conversion;
import br.com.paulodt.apicurrencyconverter.entity.Transaction;
import br.com.paulodt.apicurrencyconverter.repository.TransactionRepository;
import br.com.paulodt.apicurrencyconverter.util.UtilConversion;
import jakarta.validation.Valid;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
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

    @SuppressWarnings("null")
    public List<Transaction> listByUser(Long userId){
        return transactionRepository.findAllById(Arrays.asList(userId));
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

    public void getRateConversion(@Valid Transaction transaction) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        
        Request request = new Request.Builder()
            .url("https://api.apilayer.com/fixer/convert?to=BRL&from=USD&amount=10")
            .addHeader("apikey", "PaIk95bwZCPFj4ZHA4bh0ooHxOPaLpLc")
            .get()
            .build();
        Response response = client.newCall(request).execute();
        Gson g = new Gson();  
        
        String jsonEmString = UtilConversion.converteJsonEmString(response.body().string());
        Conversion conversion = g.fromJson(jsonEmString, Conversion.class); 
        System.out.println(conversion.getInfo().getRate());
        transaction.setConversionRate(Double.parseDouble(conversion.getInfo().getRate()));
        transaction.setDestinationValue(Double.parseDouble(conversion.getResult()));
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date dataFormatada = formato.parse(conversion.getDate()); 
        transaction.setDate(dataFormatada);
        System.out.println(jsonEmString);   
    }
}
