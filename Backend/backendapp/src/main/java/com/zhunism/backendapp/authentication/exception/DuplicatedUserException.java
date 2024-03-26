package com.zhunism.backendapp.authentication.exception;

public class DuplicatedUserException extends RuntimeException {

    public DuplicatedUserException(){
        super("Username already exists. Please use another Username.");
    }
}
