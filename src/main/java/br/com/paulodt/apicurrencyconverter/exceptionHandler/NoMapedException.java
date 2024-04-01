package br.com.paulodt.apicurrencyconverter.exceptionHandler;

public class NoMapedException extends RuntimeException{
    public NoMapedException(String message){
        super(message);
    }
}
