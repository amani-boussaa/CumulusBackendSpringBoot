package com.example.cumulusspringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cumulusspringboot.entities.Event;
import com.example.cumulusspringboot.entities.Registration;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IRegistrationService;
import com.example.cumulusspringboot.repositories.EventRepo;
import com.example.cumulusspringboot.repositories.RegistrationRepo;
import com.example.cumulusspringboot.repositories.UserRepo;

@Service
public class RegistrationService implements IRegistrationService{

	@Autowired
	RegistrationRepo regisrepos;
	
	@Autowired
	EventRepo eventrepo;
	
	@Autowired
	UserRepo userrepo;
	
	@Override
	public List<Registration> retrieveAllregistration() {
		regisrepos.findAll();
		return (List<Registration>) regisrepos;
	}


	@Override
	public Registration deleteRegistration(int id_registration) {
		regisrepos.deleteById(id_registration);
		return null;
	}



	@Override
	public Registration addRegistration(Registration registration, int id_event, Long id_user) {
		
		int nb;

		Event event =  eventrepo.findById(id_event).orElse(null);
		User user= userrepo.findById(id_user).orElse(null);
		
		if(event.getNb_restant() > 0 ) {
			
			registration.setEvent(event);
			registration.setUser(user);
			regisrepos.save(registration);
			
			nb = event.getNb_restant();
			event.setNb_restant(nb-1);
			
	        user.getEvents().add(event);
	        
			eventrepo.save(event);
			userrepo.save(user);
			
			return registration;
		}
		
		
		return null;
	}

}
