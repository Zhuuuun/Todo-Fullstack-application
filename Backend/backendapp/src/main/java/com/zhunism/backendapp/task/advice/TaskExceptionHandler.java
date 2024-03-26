package com.zhunism.backendapp.task.advice;

import com.zhunism.backendapp.task.exception.InvalidOperationException;
import com.zhunism.backendapp.task.exception.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TaskExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> invalidRequestHandler(MethodArgumentNotValidException e) {
        Map<String,String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> errorMap.put(error.getField(),error.getDefaultMessage())
        );

        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> BadCredentialsHandler(BadCredentialsException e) {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",e.getMessage());
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> UserNameNotFoundExceptionHandler(UsernameNotFoundException e) {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",e.getMessage());
        return new ResponseEntity<>(errorMap,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<?> ElementNotFoundExceptionHandler(ElementNotFoundException e) {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",e.getMessage());
        return new ResponseEntity<>(errorMap,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<?> InvalidOperationExceptionHandler(InvalidOperationException e) {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",e.getMessage());
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

}
