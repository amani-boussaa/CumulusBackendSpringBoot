package com.example.cumulusspringboot.interfaces;

import java.util.List;

import com.example.cumulusspringboot.entities.Event;

public interface IEventService {

	List<Event> retrieveAllEvent();
	Event addEvent(Event event);
	Event updateEvent(Event event,int id);
	Event retrieveEvent(int id_event);
	Event deleteEvent(int id_event);
	List<Event> afficherEvenementsLesPlusParticipatifs();
    public List<Event> getEventsForUser(Long userId);
}
