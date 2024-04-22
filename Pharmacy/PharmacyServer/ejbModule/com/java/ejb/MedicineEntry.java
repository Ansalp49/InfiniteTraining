package com.java.ejb;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Medicines")
public class MedicineEntry implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EntryId")
	private int entryId;

	@Column(name = "MedId")
	private String medId;

	@Column(name = "pharm_Id")
	private String pharmId;

	@Column(name = "MedName")
	private String medName;

	@Column(name = "Type")
	private String type;

	@Column(name = "Quantity")
	private int quantity;

	@Column(name = "EntryDate")
	private Date entryDate;

	@Column(name = "MfgDate")
	private Date mfgDate;

	@Column(name = "ExpiryDate")
	private Date expiryDate;

	@Column(name = "Price")
	private double price;

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public String getMedId() {
		return medId;
	}

	public void setMedId(String medId) {
		this.medId = medId;
	}

	public String getPharmId() {
		return pharmId;
	}

	public void setPharmId(String pharmId) {
		this.pharmId = pharmId;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public MedicineEntry(int entryId, String medId, String pharmId, String medName, String type, int quantity,
			Date entryDate, Date mfgDate, Date expiryDate, double price) {
		super();
		this.entryId = entryId;
		this.medId = medId;
		this.pharmId = pharmId;
		this.medName = medName;
		this.type = type;
		this.quantity = quantity;
		this.entryDate = entryDate;
		this.mfgDate = mfgDate;
		this.expiryDate = expiryDate;
		this.price = price;
	}

	public MedicineEntry() {
		super();
	}

	@Override
	public String toString() {
		return "MedicineEntry [entryId=" + entryId + ", medId=" + medId + ", pharmId=" + pharmId + ", medName="
				+ medName + ", type=" + type + ", quantity=" + quantity + ", entryDate=" + entryDate + ", mfgDate="
				+ mfgDate + ", expiryDate=" + expiryDate + ", price=" + price + "]";
	}

}
