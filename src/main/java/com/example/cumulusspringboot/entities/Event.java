package com.example.cumulusspringboot.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_event ;
	
	private String name_event;
	private int nb_participant;
	private int nb_restant;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	@Temporal(TemporalType.DATE)
	private Date end_date;
	
	private int duree;
	private String description;
	
	public boolean overlapsWith(Event other) {
		return (start_date.before(other.end_date) || start_date.equals(other.end_date))
	            && (end_date.after(other.start_date) || end_date.equals(other.start_date));
	}

	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "events")
	@JsonIgnore
	private Set<User> users;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "event")
	@JsonIgnore
	private Set<Registration> registrations;
	
}
