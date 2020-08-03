package com.mybank.accountmanagement;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybank.accountmanagement.controller.PersonController;
import com.mybank.accountmanagement.model.Person;
import com.mybank.accountmanagement.repository.PersonRepository;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PersonRepository personRepository;
	
	@Test
	void whenValidInput_thenReturns200() throws Exception {

		Person p1 = new Person();
		p1.setCpf("12345678901");
		p1.setName("Fulano de tal");
		p1.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1978-04-14"));

		mockMvc.perform(post("/persons/").contentType("application/json").content(objectMapper.writeValueAsString(p1)))
				.andExpect(status().isOk());

	}
}
