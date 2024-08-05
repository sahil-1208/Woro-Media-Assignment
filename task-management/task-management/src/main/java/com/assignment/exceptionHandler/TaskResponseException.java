package com.assignment.exceptionHandler;

public class TaskResponseException extends RuntimeException {

    public TaskResponseException(String message) {
        super(message);
    }
}
