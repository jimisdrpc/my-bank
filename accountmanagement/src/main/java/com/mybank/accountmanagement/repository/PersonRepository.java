package com.mybank.accountmanagement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mybank.accountmanagement.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
