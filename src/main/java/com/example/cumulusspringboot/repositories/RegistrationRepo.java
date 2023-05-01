package com.example.cumulusspringboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.cumulusspringboot.entities.Registration;

@Repository
public interface RegistrationRepo extends CrudRepository<Registration, Integer>{

}
