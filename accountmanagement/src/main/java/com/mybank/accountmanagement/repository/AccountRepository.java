package com.mybank.accountmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.accountmanagement.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
