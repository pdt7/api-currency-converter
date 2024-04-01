package br.com.paulodt.apicurrencyconverter.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.paulodt.apicurrencyconverter.entity.User;
import br.com.paulodt.apicurrencyconverter.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @SuppressWarnings("null")
    public List<User> create(User user){
        userRepository.save(user);
        return list();
    }

    public List<User> list(){
        Sort sort = Sort.by("Id").descending();
        return userRepository.findAll(sort);
    }

    @SuppressWarnings("null")
    public List<User> listByUser(Long userId){
        return userRepository.findAllById(Arrays.asList(userId));
    }

    @SuppressWarnings("null")
    public List<User> update(User user){
        userRepository.save(user);
        return list();
    }

    @SuppressWarnings("null")
    public List<User> delete(Long id){
        userRepository.deleteById(id);
        return list();
    }
}
