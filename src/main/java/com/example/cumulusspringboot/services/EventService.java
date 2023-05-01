package com.example.cumulusspringboot.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cumulusspringboot.entities.Event;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IEventService;
import com.example.cumulusspringboot.repositories.EventRepo;
import com.example.cumulusspringboot.repositories.UserRepo;
import java.util.stream.Collectors;

@Service
public class EventService implements IEventService {

	@Autowired
	EventRepo eventrepos;
	UserRepo userrepos;
	
	@Override
	public List<Event> retrieveAllEvent() {
		return (List<Event>) eventrepos.findAll();
		
	}


	@Override
	public Event retrieveEvent(int id_event) {
		
		
		
		return eventrepos.findById(id_event).orElse(null);
	}

	@Override
	public Event deleteEvent(int id_event) {
		eventrepos.deleteById(id_event);
		return null;
	}

	@Override
	public Event updateEvent(Event event, int id) {

		 Optional<Event> eventOpt = eventrepos.findById(id);
		 return eventOpt.map(existingEvent -> {
	            existingEvent.setName_event(event.getName_event());
	            existingEvent.setDescription(event.getDescription());
	            existingEvent.setDuree(event.getDuree());
	            existingEvent.setNb_participant(event.getNb_participant());
	            existingEvent.setNb_restant(event.getNb_restant());
	            existingEvent.setStart_date(event.getStart_date());
	            existingEvent.setEnd_date(event.getEnd_date());


	            return eventrepos.save(existingEvent);
	        }).orElse(null);
	       
	}

	@Override
	public Event addEvent(Event event) {
		return eventrepos.save(event);
	}


	@Override
	public List<Event> afficherEvenementsLesPlusParticipatifs() {
		
		List<Event> evenements = (List<Event>) eventrepos.findAll();
		
		// Trier les événements par ordre décroissant de nombre de participants
        Collections.sort(evenements, new Comparator<Event>() {
            public int compare(Event e1, Event e2) {
                return e2.getNb_participant() - e1.getNb_participant();
            }
        });
        
     // Afficher les 3 événements les plus participatifs
        System.out.println("Les événements les plus participatifs :");
        for (int i = 0; i < 3 && i < evenements.size(); i++) {
            Event e = evenements.get(i);
            System.out.println("- " + e.getName_event() + " (" + e.getNb_participant() + " participants)");
        }
		return evenements;
		
	}


	@Override
	public List<Event> getEventsForUser(Long userId) {
		
		 // Récupérer l'utilisateur correspondant à l'ID fourni
        User user = userrepos.findById(userId).orElse(null);
        
        // Récupérer tous les événements qui ont encore des places disponibles
        List<Event> events = eventrepos.findByNbRestantGreaterThan(0);
        
        // Filtrer les événements qui se chevauchent avec un événement existant de l'utilisateur
        List<Event> filteredEvents = events.stream()
                .filter(event -> user.getEvents().stream()
                        .noneMatch(existingEvent -> existingEvent.overlapsWith(event)))
                .collect(Collectors.toList());
        
        return filteredEvents;
	}


}
