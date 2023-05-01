package com.example.cumulusspringboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cumulusspringboot.entities.Event;
import com.example.cumulusspringboot.services.EventService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class EventController {

	@Autowired
	EventService eventservice;
	
	@GetMapping("/event")
	@ResponseBody
	public List<Event> retrieveAllEvent(){
		return eventservice.retrieveAllEvent();
	}
	
	@PostMapping("/event")
	@ResponseBody
	Event addEvent(@RequestBody Event event) {
		
		return eventservice.addEvent(event);
	}
	
	@PutMapping("/event/{id}")
	@ResponseBody
	Event updateEvent(@RequestBody Event event ,@PathVariable int id) {
		return eventservice.updateEvent(event, id);
	}
	
	@GetMapping("/event/{id_event}")
	@ResponseBody
	Event retrieveEvent( @PathVariable int id_event) {
		
		return eventservice.retrieveEvent(id_event);
	}
	
	@DeleteMapping("/event/{id_event}")
	@ResponseBody
	Event deleteEvent(@PathVariable int id_event) {
		
		return eventservice.deleteEvent(id_event);
	}
	
	@GetMapping("/statisque")
	public List<Event> afficherEvenementsLesPlusParticipatifs() {
		
		return eventservice.afficherEvenementsLesPlusParticipatifs();
	}
	
	 @GetMapping("/events")
	    public List<Event> getEventsForUser(@RequestParam("userId") Long userId){
		 return eventservice.getEventsForUser(userId);
	 }
	
}
