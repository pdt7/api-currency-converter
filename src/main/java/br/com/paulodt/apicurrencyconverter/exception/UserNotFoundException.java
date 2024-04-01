package br.com.paulodt.apicurrencyconverter.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("User not found!!");
    }
    
    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
