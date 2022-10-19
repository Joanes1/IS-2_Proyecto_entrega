package domain;

public class Participantes {
	private String hartzailea;
	private User igorlea;
	
	public Participantes(String hartzailea, User igorlea) {
		super();
		this.hartzailea = hartzailea;
		this.igorlea = igorlea;
	}
	

	public String getHartzailea() {
		return hartzailea;
	}

	public void setHartzailea(String hartzailea) {
		this.hartzailea = hartzailea;
	}

	public User getIgorlea() {
		return igorlea;
	}

	public void setIgorlea(User igorlea) {
		this.igorlea = igorlea;
	}
	
	
	
}

