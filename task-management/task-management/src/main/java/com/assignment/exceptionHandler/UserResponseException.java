package com.assignment.exceptionHandler;

public class UserResponseException extends RuntimeException {
    public UserResponseException(String message) {
        super(message);
    }
}
