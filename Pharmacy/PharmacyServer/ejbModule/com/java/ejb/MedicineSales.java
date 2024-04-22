package com.java.ejb;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MedicineSales")
public class MedicineSales implements Serializable {

	@Id
	@Column(name = "SaleId")
	private String saleId;

	@Column(name = "pharm_id")
	private String pharmId;

	@Column(name = "EntryId")
	private int entryId;

	@Column(name = "MedId")
	private String medId;

	@Column(name = "MedName")
	private String medName;

	@Column(name = "SaleDate")
	private Date saleDate;

	@Column(name = "Quantity")
	private int quantity;

	@Column(name = "Price")
	private double price;

	@Column(name = "TotalPrice")
	private double totalPrice;

	@Column(name = "ProviderId")
	private String providerId;

	@Column(name = "UHID")
	private String recipientId;

	@Column(name = "prescription_Id")
	private String prescriptionId;

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getPharmId() {
		return pharmId;
	}

	public void setPharmId(String pharmId) {
		this.pharmId = pharmId;
	}

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

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public MedicineSales(String saleId, String pharmId, int entryId, String medId, String medName, Date saleDate,
			int quantity, double price, double totalPrice, String providerId, String recipientId,
			String prescriptionId) {
		super();
		this.saleId = saleId;
		this.pharmId = pharmId;
		this.entryId = entryId;
		this.medId = medId;
		this.medName = medName;
		this.saleDate = saleDate;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.providerId = providerId;
		this.recipientId = recipientId;
		this.prescriptionId = prescriptionId;
	}

	public MedicineSales() {
		super();
	}

	@Override
	public String toString() {
		return "MedicineSales [saleId=" + saleId + ", pharmId=" + pharmId + ", entryId=" + entryId + ", medId=" + medId
				+ ", medName=" + medName + ", saleDate=" + saleDate + ", quantity=" + quantity + ", price=" + price
				+ ", totalPrice=" + totalPrice + ", providerId=" + providerId + ", recipientId=" + recipientId
				+ ", prescriptionId=" + prescriptionId + "]";
	}

}
