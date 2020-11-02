package com.phane.basicatm.repositories;

import com.phane.basicatm.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
