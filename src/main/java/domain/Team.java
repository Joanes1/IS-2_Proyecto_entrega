package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Team implements Serializable{
	@XmlID
	@Id 
	private String izena;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Registered> users =new Vector<Registered>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Event> events =new Vector<Event>();
	
	public Team() {
		super();
	}
	
	public Team(String izena) {
		this.izena=izena;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public Vector<Registered> getUsers() {
		return users;
	}

	public void setUsers(Vector<Registered> users) {
		this.users = users;
	}

	public Vector<Event> getEvents() {
		return events;
	}

	public void setEvents(Vector<Event> events) {
		this.events = events;
	}
	
	public void addEvent(Event ev) {
		this.events.add(ev);
	}
	
	public void removeUser(Registered u) {
		this.users.remove(u);
	}
	
	public void addUser(Registered u) {
		this.users.add(u);
	}
}
