package com.sg.vendingmachine.service;

public class DuplicateIdException extends Exception {

    public DuplicateIdException(String message) {
        super(message);
    }

    public DuplicateIdException(String message,
                                Throwable cause) {
        super(message, cause);
    }

}
