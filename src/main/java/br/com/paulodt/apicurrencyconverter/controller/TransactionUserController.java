package br.com.paulodt.apicurrencyconverter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulodt.apicurrencyconverter.entity.Transaction;
import br.com.paulodt.apicurrencyconverter.service.TransactionService;

@RestController
@RequestMapping("/transaction/user")
public class TransactionUserController {

    private TransactionService transactionService;

    public TransactionUserController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @GetMapping("{id}")
    List<Transaction> list(@PathVariable("id") Long id){
        return transactionService.listByUser(id);
    }
}
