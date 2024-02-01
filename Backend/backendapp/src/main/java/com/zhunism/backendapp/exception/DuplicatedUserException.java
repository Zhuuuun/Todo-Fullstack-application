package com.zhunism.backendapp.exception;

public class DuplicatedUserException extends RuntimeException {

    public DuplicatedUserException(){
        super("Username already exists. Please use another Username.");
    }
}
