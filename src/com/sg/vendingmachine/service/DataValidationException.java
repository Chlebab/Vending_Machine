package com.sg.vendingmachine.service;

public class DataValidationException extends Exception {

    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(String message,
                                   Throwable cause) {
        super(message, cause);
    }

}
