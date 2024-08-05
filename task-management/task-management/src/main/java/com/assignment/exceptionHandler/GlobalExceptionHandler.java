package com.assignment.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskResponseException.class)
    public ResponseEntity<String> handleTaskResponseException(TaskResponseException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = {UserResponseException.class})
    public ResponseEntity<String> handleUserResponseException(UserResponseException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
//
//    @ExceptionHandler(value = {UsernameNotFoundException.class})
//    public ResponseEntity<String> handleUserNameNotException(UsernameNotFoundException exception) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
//    }

}
