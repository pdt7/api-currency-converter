package br.com.paulodt.apicurrencyconverter.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.paulodt.apicurrencyconverter.entity.Transaction;
import br.com.paulodt.apicurrencyconverter.repository.TransactionRepository;

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
}
