package br.com.paulodt.apicurrencyconverter.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulodt.apicurrencyconverter.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
