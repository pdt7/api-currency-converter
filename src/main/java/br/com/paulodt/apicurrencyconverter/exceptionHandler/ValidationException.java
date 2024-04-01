package br.com.paulodt.apicurrencyconverter.exceptionHandler;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}
