package com.example.demo.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Greeting;
import com.example.demo.repository.GreetingRepository;
@Service
public class GreetingServiceBean implements GreetingServices {
	@Autowired
	private GreetingRepository repository;
	
	private static Long nextid;
	private static Map<Long,Greeting> greetingMap;
	
	private static Greeting saveGreeting(Greeting greeting) {
		if(greetingMap==null) {
			greetingMap=new HashMap<Long,Greeting>();
			nextid=1l;
		}
		//if update
		if(greeting.getId()!=null) {
			Greeting oldgreeting=greetingMap.get(greeting.getId());
			
			if(oldgreeting==null) {
				return null;
			}
			greetingMap.remove(greeting.getId());
			greetingMap.put(greeting.getId(), greeting);
			return greeting;
		}
		//if create 
		greeting.setId(nextid);
		nextid=nextid+1;
		greetingMap.put(greeting.getId(), greeting);
		return greeting;
	}
	static {
		Greeting g1=new Greeting();
		g1.setText("hello world");
		saveGreeting(g1);
		
		Greeting g2=new Greeting();
		g2.setText("Good Morning..");
		saveGreeting(g2);
	}
	
	public static boolean deleteGreeting(Long id) {
	    Greeting remove = greetingMap.remove(id);
	    if(remove!=null) {
	    	return true;
	    }
		return false;
		
	}

	@Override
	public Collection<Greeting> findall() {
		Collection<Greeting> greetings=repository.findAll();
		return greetings;
	}

	@Override
	public Greeting findOne(Long id) {
		Greeting greeting=repository.findById(id).get();
		return greeting;
	}

	@Override
	public Greeting save(Greeting greeting) {
		if(greeting.getId()!=null) {
			//already exists
			return null;
			
		}
	Greeting savedgreeting=repository.save(greeting);
		return savedgreeting;
	}

	@Override
	public Greeting update(Greeting greeting) {
		Greeting greetingPersist=findOne(greeting.getId());
		if(greetingPersist==null) {
			//can't update 
			return null;
		}
	Greeting updatedgreeting=repository.save(greeting);
		return updatedgreeting;
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

}
