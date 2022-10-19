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

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction implements Serializable{
	
	@Id 
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer transactionNum; 
	@XmlIDREF
	private Registered erabiltzaile; 
	private Double dirua; 
	private Date data; 
	private String mota;
	
	public Transaction(Registered erabiltzaile, Double dirua, Date data, String mota) {
		this.erabiltzaile = erabiltzaile;
		this.dirua = dirua;
		this.data = data;
		this.mota = mota;
	}
	
	public Transaction() {
		super();
	}
	
	public Integer getTransactionNumber() {
		return transactionNum;
	}
	public void setTransactionNumber(Integer transactionNum) {
		this.transactionNum = transactionNum;
	}
	public User getErabiltzailea() {
		return erabiltzaile;
	}
	public void setErabiltzailea(Registered erabiltzaile) {
		this.erabiltzaile = erabiltzaile;
	}
	public Double getDirua() {
		return dirua;
	}
	public void setDirua(Double dirua) {
		this.dirua = dirua;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	
	@Override
	public String toString() {
		return "Data: " + this.data + ", dirua: " + this.dirua + ", mota: " + this.mota; 
	}
	
	

}
