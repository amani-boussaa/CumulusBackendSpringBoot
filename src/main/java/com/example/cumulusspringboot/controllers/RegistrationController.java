package com.example.cumulusspringboot.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cumulusspringboot.entities.Registration;
import com.example.cumulusspringboot.services.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationService regisservice;
   
	
	@GetMapping("/retrieveRegistration")
	@ResponseBody
	List<Registration> retrieveAllregistration(){
		
		return regisservice.retrieveAllregistration();
	}

	@PostMapping("/addRegistration/{id_event}/{id_user}")
	@ResponseBody
	public Registration addRegistration( @RequestBody Registration registration, @PathVariable("id_event") int id_event, @PathVariable("id_user") Long id_user) {
		
		return regisservice.addRegistration(registration, id_event, id_user);
	}
	
	
	@DeleteMapping("/deleteRegistration/{id_registration}")
	@ResponseBody
	public ResponseEntity<String> deleteRegistration(@PathVariable int id_registration) {
		regisservice.deleteRegistration(id_registration);
	
		 return  ResponseEntity.ok("deleted successfully !");
	}
	
}
