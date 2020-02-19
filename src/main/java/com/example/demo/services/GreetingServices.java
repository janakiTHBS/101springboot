package com.example.demo.services;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.example.demo.models.Greeting;

public interface GreetingServices {

	Collection<Greeting> findall();

	Greeting findOne(Long id);

	Greeting save(Greeting greeting);

	Greeting update(Greeting greeting);

	void delete(Long id);

}
