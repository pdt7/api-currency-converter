package br.com.paulodt.apicurrencyconverter.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulodt.apicurrencyconverter.ApiCurrencyConverterApplication;
import br.com.paulodt.apicurrencyconverter.entity.Transaction;
import br.com.paulodt.apicurrencyconverter.service.TransactionService;
import br.com.paulodt.apicurrencyconverter.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/transaction", produces = "application/json")
public class TransactionController {

    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
    }

    private TransactionService transactionService;
        
private static Logger log = LoggerFactory.getLogger(ApiCurrencyConverterApplication.class);

    @PostMapping
    ResponseEntity<Transaction> create(@RequestBody @Valid Transaction transaction) throws Exception{
        log.info("POST transaction - BEGIN");
        log.info("TRANSACTION INPUT " + transaction.toString());
        transactionService.getRateConversion(transaction);
        Date data = new Date(System.currentTimeMillis());
        transaction.setDate(data);
        transactionService.create(transaction);
        log.info("POST transaction - END");
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    List<Transaction> list(){
        return transactionService.list();
    }

    @GetMapping("{id}")
    List<Transaction> listTransactions(@PathVariable("id") Long id){
        return transactionService.listByUser(id);
    }

    @PutMapping
    List<Transaction> update(@RequestBody Transaction transaction){
        return transactionService.update(transaction);
    }

    @DeleteMapping("{id}")
    List<Transaction> delete(@PathVariable("id") Long id){
        return transactionService.delete(id);
    }
}
