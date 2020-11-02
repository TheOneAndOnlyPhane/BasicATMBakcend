package com.phane.basicatm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import com.phane.basicatm.exceptions.InsufficientFundsException;
import com.phane.basicatm.models.Account;
import com.phane.basicatm.repositories.AccountRepository;
import com.phane.basicatm.services.AccountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicAtmBackendApplicationTests {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private AccountService accountService;

	private Account account;

	@BeforeEach
	private void init() {
		account = new Account(1L, "TestUser", new BigDecimal(5000));
		when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
	}

	@Test
	public void testAddingFundsToAccount() {
		accountService.updateBalance(1L, new BigDecimal(1000));

		assertEquals(new BigDecimal(6000), account.getBalance());
	}

	@Test
	public void testWithdrawingFundsFromAccount() {
		accountService.updateBalance(1L, new BigDecimal(-1000));

		assertEquals(new BigDecimal(4000), account.getBalance());
	}

	@Test
	public void testInsufficientFunds() {
		Exception exception = assertThrows(InsufficientFundsException.class, () -> {
			accountService.updateBalance(1L, new BigDecimal(-6000));
		});

		String expectedMessage = "Insufficient funds on account with id: 1";
		assertTrue(expectedMessage.equals(exception.getMessage()));
	}

}
