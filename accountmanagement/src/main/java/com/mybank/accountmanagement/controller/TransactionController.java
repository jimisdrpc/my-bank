package com.mybank.accountmanagement.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.accountmanagement.dto.BankStatementFilter;
import com.mybank.accountmanagement.model.Transaction;
import com.mybank.accountmanagement.repository.TransactionRepository;
import com.mybank.accountmanagement.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@Autowired
	TransactionRepository transactionRepository;

	// Implementar path que realiza operação de saque em uma conta
	@PostMapping("/transactions/{accountId}/withdrawal/{amountToDebit}")
	public Transaction debit(@PathVariable(value = "accountId") Long accountId,
			@PathVariable(value = "amountToDebit") BigDecimal amountToDebit) throws Exception {
		return transactionService.withdrawal(accountId, amountToDebit);
	}

	// Implementar path que realiza operação de depósito em uma conta;
	@PostMapping("/transactions/{accountId}/credit/{amountToCredit}")
	public Transaction credit(@PathVariable(value = "accountId") Long accountId,
			@PathVariable(value = "amountToCredit") BigDecimal amountToCredit) throws Exception {
		return transactionService.credit(accountId, amountToCredit);
	}

// Implementar path que recupera o extrato de transações de uma conta;
// Implementar extrato por período;
	@GetMapping("/transaction/{accountId}/bankstatement")
	public List<Transaction> bankStatement(@PathVariable(value = "accountId") Long accountId,
			@Valid @RequestBody BankStatementFilter bankStatementFilter) {

		return transactionRepository.findByAccountIdWithCreationDateBetween(accountId,
				bankStatementFilter.getFromDate(), bankStatementFilter.getToDate());
	}

}
