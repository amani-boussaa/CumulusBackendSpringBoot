package com.example.cumulusspringboot.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.cumulusspringboot.entities.Event;

@Repository
public interface EventRepo extends CrudRepository<Event, Integer>{

//List<Event> findBynb_restantGreaterThan(int nb_restant);
}
