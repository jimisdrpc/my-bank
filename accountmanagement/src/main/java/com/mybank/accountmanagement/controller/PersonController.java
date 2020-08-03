package com.mybank.accountmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.accountmanagement.model.Person;
import com.mybank.accountmanagement.repository.PersonRepository;

@RestController
public class PersonController {
	
	@Autowired
	PersonRepository personRepository;
	
    @PostMapping("/persons")
    public Person createPost(@Valid @RequestBody Person post) {
        return personRepository.save(post);
    }
}
