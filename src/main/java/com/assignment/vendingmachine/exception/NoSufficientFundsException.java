package com.assignment.vendingmachine.exception;


public class NoSufficientFundsException extends Exception {

    private String message;
    public NoSufficientFundsException(String message) {
        this.message = message;
    }
}
