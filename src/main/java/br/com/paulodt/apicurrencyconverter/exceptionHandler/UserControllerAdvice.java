package br.com.paulodt.apicurrencyconverter.exceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserControllerAdvice {
    
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> userNotFound(UserNotFoundException userNotFound){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "Invalid user.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<MessageExceptionHandler> validationException(ValidationException validationException){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), validationException.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageExceptionHandler> noMapedException(NoMapedException exception){
        MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
