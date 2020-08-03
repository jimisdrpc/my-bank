package com.mybank.accountmanagement.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mybank.accountmanagement.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	// transactionType=:2 = DEBIT
	@Query(value = "SELECT SUM(amount) FROM transactions WHERE account_id=:idAccount AND transaction_type=2 AND CAST(created_at AS date)=CURRENT_DATE", nativeQuery = true)
	BigDecimal totalDayWithDrawal(@Param("idAccount") Long idAccount);

	@Query(value = "SELECT * FROM transactions WHERE account_id=:idAccount AND CAST(created_at AS date) "
			+ "BETWEEN CAST(:fromDate AS date) AND CAST(:toDate AS date)", nativeQuery = true)
	List<Transaction> findByAccountIdWithCreationDateBetween(@Param("idAccount") Long idAccount,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}
