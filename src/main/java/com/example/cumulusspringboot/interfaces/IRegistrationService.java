package com.example.cumulusspringboot.interfaces;

import java.util.List;

import com.example.cumulusspringboot.entities.Registration;

public interface IRegistrationService {

	List<Registration> retrieveAllregistration();
	public Registration deleteRegistration(int id_registration);
	public Registration addRegistration(Registration registration, int id_event, Long id_user );
}
