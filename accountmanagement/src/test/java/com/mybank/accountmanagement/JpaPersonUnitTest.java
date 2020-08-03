package com.mybank.accountmanagement;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mybank.accountmanagement.model.Person;
import com.mybank.accountmanagement.repository.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaPersonUnitTest {

	@Autowired
	PersonRepository personRepository;

	@Test
	public void should_save_a_person() throws ParseException {

		Person p1 = new Person();
		p1.setCpf("12345678901");
		p1.setName("Fulano de tal");
		p1.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1978-04-14"));
		p1.setCreatedAt(new Date());
		p1.setUpdatedAt(new Date());
		
		Person personSaved = personRepository.save(p1);

		assertNotNull(personSaved);

	}

}
