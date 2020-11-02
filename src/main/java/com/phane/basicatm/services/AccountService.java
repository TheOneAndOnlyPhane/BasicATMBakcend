package com.phane.basicatm.services;

import java.util.List;

import com.phane.basicatm.exceptions.AccountNotFoundException;
import com.phane.basicatm.models.Account;
import com.phane.basicatm.repositories.AccountRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final static Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) throws AccountNotFoundException {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }
}