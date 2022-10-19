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
import javax.xml.bind.annotation.XmlSeeAlso;

@Entity
@XmlSeeAlso({Registered.class, Admin.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class User implements Serializable{
	
	@Id
	@XmlID
	private String usrname;
	private String password;
	private Integer bankAccount;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Message> bidalitakoak=new Vector<Message>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Message> jasotakoak=new Vector<Message>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Elkarrizketa> elkarrizketak=new Vector<Elkarrizketa>();

	public User(String usrname, String password, Integer bankAccount) {
		super();
		this.usrname = usrname;
		this.password = password;
		this.bankAccount = bankAccount;
	}
	
	public User() {
		super();
	}

	public String getUsername() {
		return usrname;
	}

	public void setUsername(String usrname) {
		this.usrname = usrname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(Integer bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Vector<Message> getBidalitakoak() {
		return bidalitakoak;
	}

	public void setBidalitakoak(Vector<Message> bidalitakoak) {
		this.bidalitakoak = bidalitakoak;
	}

	public Vector<Message> getJasotakoak() {
		return jasotakoak;
	}

	public void setJasotakoak(Vector<Message> jasotakoak) {
		this.jasotakoak = jasotakoak;
	}
	
	public void addBidalitakoMezuak(Message m) {
		this.bidalitakoak.add(m);
	}
	
	public void addJasotakoMezuak(Message m) {
		this.jasotakoak.add(m);
	}
	
	public Vector<Elkarrizketa> getElkarrizketak() {
		return elkarrizketak;
	}

	public void setElkarrizketak(Vector<Elkarrizketa> elkarrizketak) {
		this.elkarrizketak = elkarrizketak;
	}
	
	public void addElkarrizketak(Elkarrizketa elka) {
		this.elkarrizketak.add(elka);
	}

	@Override
	public String toString() {
		return this.usrname;
	}
}
