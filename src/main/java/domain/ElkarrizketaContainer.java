package domain;

import java.util.Vector;

public class ElkarrizketaContainer {
	private Elkarrizketa elka;
	private User lehenIgorle;
	private User lehenHartzailea;
	private Vector<Message> message;
	
	public ElkarrizketaContainer() {
		this.elka=null;
		this.lehenIgorle=null;
		this.lehenHartzailea=null;
		this.message=null;
	}
	
	public ElkarrizketaContainer(Elkarrizketa elk) {
		this.elka=elk;
		this.message=elk.getMessages();
		this.lehenIgorle=elk.getUser1();
		this.lehenHartzailea=elk.getUser2();
	}

	public User getLehenIgorle() {
		return lehenIgorle;
	}

	public void setLehenIgorle(User lehenIgorle) {
		this.lehenIgorle = lehenIgorle;
	}

	public User getLehenHartzailea() {
		return lehenHartzailea;
	}

	public void setLehenHartzailea(User lehenHartzailea) {
		this.lehenHartzailea = lehenHartzailea;
	}

	public Vector<Message> getMessage() {
		return message;
	}

	public void setMessage(Vector<Message> message) {
		this.message = message;
	}

	public Elkarrizketa getElka() {
		return elka;
	}

	public void setElka(Elkarrizketa elka) {
		this.elka = elka;
	}
	
	public boolean denakIrakurrita(User u) {
		for(int i=0; i<message.size(); i++) {
			if(!message.get(i).isIrakurrita() && u.getUsername().equals(message.get(i).getHartzailea().getUsername())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return this.elka.getData() + " : " +this.elka.getTitulo();
	}
}
