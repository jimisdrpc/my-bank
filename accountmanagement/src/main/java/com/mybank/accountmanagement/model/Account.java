package com.mybank.accountmanagement.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.javamoney.moneta.Money;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "accounts")
public class Account extends AuditModel  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	//@OneToOne
	@JoinColumn(name = "person_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("person_id")
	@Column(unique = true)
	private Person person;
	
	@NotNull
	@NumberFormat(pattern = "#,###,###,###.##")
	private BigDecimal balance;
	
	@NotNull
	@NumberFormat(pattern = "#,###,###,###.##")
	private BigDecimal dailyWithdrawalLimit;
	
	@NotNull
	private boolean active;
	
	@NotNull
	private int accountType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getDailyWithdrawalLimit() {
		return dailyWithdrawalLimit;
	}

	public void setDailyWithdrawalLimit(BigDecimal dailyWithdrawalLimit) {
		this.dailyWithdrawalLimit = dailyWithdrawalLimit;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
	
}
