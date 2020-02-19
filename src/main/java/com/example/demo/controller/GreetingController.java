package com.example.demo.controller;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Greeting;
import com.example.demo.services.GreetingServices;

@RestController
public class GreetingController {
	@Autowired
	private GreetingServices services;
	
	
	@RequestMapping(value = "/api/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
       Collection<Greeting> greetings=services.findall();
       
       return new ResponseEntity<Collection<Greeting>>(greetings,HttpStatus.OK);
	}
	@RequestMapping(value="/api/greetings/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id){
		Greeting greeting=services.findOne(id);
		if(greeting==null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Greeting>(greeting,HttpStatus.OK);
	}
	@RequestMapping(value="/api/greetings",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting){
		
		Greeting savedGreeting=services.save(greeting);
		
		return new ResponseEntity<Greeting>(savedGreeting,HttpStatus.CREATED);
		
	}
@RequestMapping(value="/api/greetings/{id}",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting){
	
	Greeting updatedGreeting=services.update(greeting);
	
	if(updatedGreeting==null) {
		return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	return new ResponseEntity<Greeting>(updatedGreeting,HttpStatus.OK);
		
	}
@RequestMapping(value="/api/greetings/{id}",method=RequestMethod.DELETE,consumes=MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id,@RequestBody Greeting greeting){
	services.delete(id);
	return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
}
}
