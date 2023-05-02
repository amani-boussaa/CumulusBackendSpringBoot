package com.example.cumulusspringboot.exception;

import org.springframework.http.HttpStatus;

public class CumulusAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public CumulusAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CumulusAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
