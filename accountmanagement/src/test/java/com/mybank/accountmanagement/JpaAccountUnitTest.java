package com.mybank.accountmanagement;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.javamoney.moneta.Money;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.mybank.accountmanagement.model.Account;
import com.mybank.accountmanagement.model.Person;
import com.mybank.accountmanagement.repository.AccountRepository;
import com.mybank.accountmanagement.repository.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaAccountUnitTest {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void should_save_an_account() {

		Person p1 = new Person();
		p1.setCpf("12345678901");
		p1.setName("Fulano de tal");
		try {
			p1.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1978-04-14"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		p1.setCreatedAt(new Date());
		p1.setUpdatedAt(new Date());

		Person personSaved = personRepository.save(p1);

		Account ac = new Account();
		ac.setPerson(personSaved);
		ac.setBalance(BigDecimal.valueOf(1).movePointLeft(2));
		ac.setCreatedAt(new Date());
		ac.setUpdatedAt(new Date());

		Account accountSaved = accountRepository.save(ac);

		Assert.assertNotNull(accountSaved);
		Assert.assertEquals(accountSaved.getBalance(), BigDecimal.valueOf(1).movePointLeft(2));

	}
}
