package com.mybank.accountmanagement.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.accountmanagement.jpa.exception.*;
import com.mybank.accountmanagement.model.Account;
import com.mybank.accountmanagement.repository.AccountRepository;
import com.mybank.accountmanagement.repository.PersonRepository;

@RestController
public class AccountController {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PersonRepository personRepository;

	//Implementar path que realiza a criação de uma conta;
	@PostMapping("/accounts/{personId}")
	public Account createAccount(@PathVariable(value = "personId") Long personId, @Valid @RequestBody Account account) {
		return personRepository.findById(personId).map(person -> {
			account.setPerson(person);
			return accountRepository.save(account);
		}).orElseThrow(() -> new ResourceNotFoundException("PersonId " + personId + " not found"));
	}

	//Implementar path que realiza o bloqueio de uma conta;
	@PostMapping("/accounts/block/{accountId}")
	public Account blockAccount(@PathVariable(value = "accountId") Long accountId) {
		return accountRepository.findById(accountId).map(account -> {
			account.setActive(false);
			;
			return accountRepository.save(account);
		}).orElseThrow(() -> new ResourceNotFoundException("Account Id " + accountId + " not found"));
	}

	//Implementar path que realiza operação de consulta de saldo em determinada conta;
	@GetMapping("/accounts/balance/{accountId}")
	public BigDecimal getBalance(@PathVariable(value = "accountId") Long accountId) {
		return accountRepository.findById(accountId).get().getBalance();
	}
}
