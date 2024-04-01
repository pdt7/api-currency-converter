package br.com.paulodt.apicurrencyconverter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.paulodt.apicurrencyconverter.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query("from Transaction t where t.user.userId = :userId")
    List<Transaction> findByUser(long userId);

}
