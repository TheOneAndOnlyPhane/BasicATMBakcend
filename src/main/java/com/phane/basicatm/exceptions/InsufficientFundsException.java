package com.phane.basicatm.exceptions;

public class InsufficientFundsException extends RuntimeException {
    
    private static final long serialVersionUID = 2032753225777747291L;

    public InsufficientFundsException(Long id) {
        super("Insufficient funds on account with id: " + id);
    }
}