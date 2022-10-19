package domain;

import java.io.Serializable;
import java.util.Date;

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
public class Jarraitzailea implements Serializable{
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer jarraitzaileaNumber;
	@XmlIDREF
	private Registered nork;
	@XmlIDREF
	private Registered nori;
	private Date data;
	
	public Jarraitzailea() {
		super();
	}
	public Jarraitzailea(Registered nork, Registered nori) {
		this.nork=nork;
		this.nori=nori;
		this.data=new Date();
	}
	public Integer getJarraitzaileaNumber() {
		return jarraitzaileaNumber;
	}
	public void setJarraitzaileaNumber(Integer jarraitzaileaNumber) {
		this.jarraitzaileaNumber = jarraitzaileaNumber;
	}
	public Registered getNork() {
		return nork;
	}
	public void setNork(Registered nork) {
		this.nork = nork;
	}
	public Registered getNori() {
		return nori;
	}
	public void setNori(Registered nori) {
		this.nori = nori;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
