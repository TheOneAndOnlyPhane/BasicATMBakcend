package com.phane.basicatm.services;

import java.math.BigDecimal;
import java.util.List;

import com.phane.basicatm.exceptions.AccountNotFoundException;
import com.phane.basicatm.exceptions.InsufficientFundsException;
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
        log.info("Retrieving all accounts");
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) throws AccountNotFoundException {
        log.info("Retrieving account with id: " + id);
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public void updateAccount(Account account) {
        log.info("Updating account with id: " + account.getId());
        accountRepository.save(account);
    }

    public BigDecimal checkBalance(Long id) {
        log.info("Checking balance for account with id: " + id);
        return getAccount(id).getBalance();
    }

    public void updateBalance(Long id, BigDecimal funds) throws InsufficientFundsException {
        log.info("Updating balance for account with id: " + id);
        Account currentAccount = getAccount(id);
        BigDecimal currentBalance = currentAccount.getBalance();
        BigDecimal updatedBalance = currentBalance.add(funds);

        if (updatedBalance.doubleValue() < 0) {
            throw new InsufficientFundsException(id);
        }

        currentAccount.setBalance(updatedBalance);
        updateAccount(currentAccount);

    }
}