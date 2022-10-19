package domain;

public class MezuakContainer {
	private Message message;
	private User igorlea;
	private User hartzailea;
	private Elkarrizketa elkarrizketa;
	
	public MezuakContainer() {
		this.igorlea=null;
		this.hartzailea=null;
		this.elkarrizketa=null;
		this.message=null;
	}
	
	public MezuakContainer(Message m) {
		this.igorlea=m.getIgorlea();
		this.hartzailea=m.getHartzailea();
		this.elkarrizketa=m.getElkarrizketa();
		this.message=m;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getIgorlea() {
		return igorlea;
	}

	public void setIgorlea(User igorlea) {
		this.igorlea = igorlea;
	}

	public User getHartzailea() {
		return hartzailea;
	}

	public void setHartzailea(User hartzailea) {
		this.hartzailea = hartzailea;
	}

	public Elkarrizketa getElkarrizketa() {
		return elkarrizketa;
	}

	public void setElkarrizketa(Elkarrizketa elkarrizketa) {
		this.elkarrizketa = elkarrizketa;
	}
	
	@Override
	public String toString() {
		return igorlea.toString() + "-->" + this.hartzailea.toString();
	}
}
