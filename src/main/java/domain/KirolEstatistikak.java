package domain;

import java.io.Serializable;

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
public class KirolEstatistikak implements Serializable{
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer KENumber;
	@XmlIDREF
	private Registered user;
	@XmlIDREF
	private Sport sport;
	private Integer kont;
	
	public KirolEstatistikak() {
		super();
	}
	
	public KirolEstatistikak(Registered u, Sport sp) {
		this.sport=sp;
		this.user=u;
		this.kont=0;
	}
	
	public Integer getKENumber() {
		return KENumber;
	}
	
	public void setKENumber(Integer kENumber) {
		KENumber = kENumber;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(Registered user) {
		this.user = user;
	}
	
	public Sport getSport() {
		return sport;
	}
	
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	
	public Integer getKont() {
		return kont;
	}
	
	public void setKont(Integer kont) {
		this.kont = kont;
	}
	
	public void eguneratuKont(Integer i) {
		this.kont =this.kont + i;
	}
}
