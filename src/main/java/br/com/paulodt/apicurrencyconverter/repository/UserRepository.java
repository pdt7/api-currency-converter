package br.com.paulodt.apicurrencyconverter.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulodt.apicurrencyconverter.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
