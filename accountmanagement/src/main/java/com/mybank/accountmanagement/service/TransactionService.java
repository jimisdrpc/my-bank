package com.mybank.accountmanagement.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybank.accountmanagement.model.Account;
import com.mybank.accountmanagement.model.Transaction;
import com.mybank.accountmanagement.repository.AccountRepository;
import com.mybank.accountmanagement.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Transactional
	public Transaction withdrawal(Long accountId, BigDecimal amountToDebit) throws Exception {

		BigDecimal totalDayWithDrawal = transactionRepository.totalDayWithDrawal(accountId) == null
				? BigDecimal.valueOf(0).movePointLeft(2)
				: transactionRepository.totalDayWithDrawal(accountId);

		Account account = accountRepository.findById(accountId).get();
		
		if (totalDayWithDrawal.add(amountToDebit).compareTo(account.getDailyWithdrawalLimit()) >0 ) {
			throw new Exception("Daily Limit exceeded!");
		}
			
			
		if (!account.isActive()) {
			throw new Exception("Account inactive!");
		}
		
		if (account.getBalance().compareTo(amountToDebit) >= 0) {
			account.setBalance(account.getBalance().subtract(amountToDebit));
		} else {
			throw new Exception("Balance not satisfatory!");
		}

		accountRepository.save(account);

		Transaction transaction = new Transaction(account, amountToDebit, 2);

		return transactionRepository.save(transaction);

	}

	@Transactional
	public Transaction credit(Long accountId, BigDecimal amountToCredit) throws Exception {

		Account account = accountRepository.findById(accountId).get();

		if (!account.isActive()) {
			throw new Exception("Account inactive!");
		}

		account.setBalance(account.getBalance().add(amountToCredit));

		accountRepository.save(account);

		Transaction transaction = new Transaction(account, amountToCredit, 1);

		return transactionRepository.save(transaction);

	}
}
