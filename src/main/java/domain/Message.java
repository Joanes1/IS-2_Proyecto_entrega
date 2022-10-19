package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Message implements Serializable{
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer messageNumber;
	@XmlIDREF
	private User igorlea;
	@XmlIDREF
	private User hartzailea;
	private String testua;
	private boolean irakurrita;
	@XmlIDREF
	private Elkarrizketa elkarrizketa;
	
	public Message() {
		super();
	}
	
	public Message(User igorlea, User hartzailea, String testua) {
		super();
		this.igorlea=igorlea;
		this.hartzailea=hartzailea;
		//this.asunto=asunto;
		this.testua=testua;
		this.irakurrita=false;
		//this.elkarrizketa=new Vector<Message>();
		this.elkarrizketa=null;
	}

	public Integer getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(Integer messageNumber) {
		this.messageNumber = messageNumber;
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

	public String getTestua() {
		return testua;
	}

	public void setTestua(String testua) {
		this.testua = testua;
	}

	public boolean isIrakurrita() {
		return irakurrita;
	}

	public void setIrakurrita(boolean irakurrita) {
		this.irakurrita = irakurrita;
	}
	

	public Elkarrizketa getElkarrizketa() {
		return elkarrizketa;
	}

	public void setElkarrizketa(Elkarrizketa elkarrizketa) {
		this.elkarrizketa = elkarrizketa;
	}
	
	
}
