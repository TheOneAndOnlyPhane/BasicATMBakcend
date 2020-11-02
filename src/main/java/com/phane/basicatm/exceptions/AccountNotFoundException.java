package com.phane.basicatm.exceptions;

public class AccountNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 546002623636626973L;

    public AccountNotFoundException(Long id) {
        super("Cannot find account with id: " + id);
    }
}