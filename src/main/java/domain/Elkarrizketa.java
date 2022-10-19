package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Elkarrizketa implements Serializable{
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer elkarrizketaNumber;
	private String titulo="";
	private Date data;
	@XmlIDREF
	private User user1;
	@XmlIDREF
	private User user2;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Message> messages = new Vector<Message>();
	private Participantes participantes;
	
	public Participantes getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Participantes participantes) {
		this.participantes = participantes;
	}

	public Elkarrizketa() {
		super();
	}
	
	public Elkarrizketa(String t, User u1, User u2) {
		super();
		this.titulo=t;
		this.data=new Date();
		this.user1=u1;
		this.user2=u2;
	}
	

	public Integer getElkarrizketaNumber() {
		return elkarrizketaNumber;
	}

	public void setElkarrizketaNumber(Integer elkarrizketaNumber) {
		this.elkarrizketaNumber = elkarrizketaNumber;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Vector<Message> getMessages() {
		return messages;
	}

	public void setMessages(Vector<Message> messages) {
		this.messages = messages;
	}
	
	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public void addMezua(Message mezua) {
		this.messages.add(mezua);
	}
	
	public boolean denakIrakurrita(User u) {
		for(int i=0; i<messages.size(); i++) {
			if(!messages.get(i).isIrakurrita() && u.getUsername().equals(messages.get(i).getHartzailea().getUsername())) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return this.data + " : " +this.titulo;
	}
}
