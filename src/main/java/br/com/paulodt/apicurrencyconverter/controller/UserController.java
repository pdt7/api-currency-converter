package br.com.paulodt.apicurrencyconverter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulodt.apicurrencyconverter.entity.User;

import br.com.paulodt.apicurrencyconverter.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    List<User> create(@RequestBody @Valid User user) throws Exception{
        return userService.create(user);
    }

    @GetMapping
    List<User> list(){
        return userService.list();
    }

    @PutMapping
    List<User> update(@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("{id}")
    List<User> delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }
}
