package br.com.paulodt.apicurrencyconverter.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulodt.apicurrencyconverter.entity.Conversion;
import br.com.paulodt.apicurrencyconverter.entity.Transaction;
import br.com.paulodt.apicurrencyconverter.service.TransactionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    private TransactionService transactionService;
    
    @PostMapping
    List<Transaction> create(@RequestBody @Valid Transaction transaction) throws Exception{
        transactionService.getRateConversion(transaction);
        Date data = new Date(System.currentTimeMillis());
        transaction.setDate(data);
        return transactionService.create(transaction);
    }

    @GetMapping
    List<Transaction> list(){
        return transactionService.list();
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
