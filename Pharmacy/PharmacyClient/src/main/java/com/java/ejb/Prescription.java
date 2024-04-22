package com.java.ejb;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prescription")
public class Prescription implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prescription_Id")
	private String prescriptionId;

	@Column(name = "appointmentId")
	private String appointmentId;

	@Column(name = "providerid")
	private String providerid;

	@Column(name = "UHID")
	private String recipentId;

	@Column(name = "Symptoms")
	private String symptoms;

	@Column(name = "medicine")
	private String medicine;
	
	@Column(name = "Dosage")
	private String dosage;

	@Column(name = "Prescribed_Date")
	private Date prescribedDate;

	@Column(name = "Instructions")
	private String instructions;
	
	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getProviderid() {
		return providerid;
	}

	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}

	public String getRecipentId() {
		return recipentId;
	}

	public void setRecipentId(String recipentId) {
		this.recipentId = recipentId;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Date getPrescribedDate() {
		return prescribedDate;
	}

	public void setPrescribedDate(Date prescribedDate) {
		this.prescribedDate = prescribedDate;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", appointmentId=" + appointmentId + ", providerid="
				+ providerid + ", recipentId=" + recipentId + ", symptoms=" + symptoms + ", medicine=" + medicine
				+ ", dosage=" + dosage + ", prescribedDate=" + prescribedDate + ", instructions=" + instructions + "]";
	}

	public Prescription(String prescriptionId, String appointmentId, String providerid, String recipentId,
			String symptoms, String medicine, String dosage, Date prescribedDate, String instructions) {
		super();
		this.prescriptionId = prescriptionId;
		this.appointmentId = appointmentId;
		this.providerid = providerid;
		this.recipentId = recipentId;
		this.symptoms = symptoms;
		this.medicine = medicine;
		this.dosage = dosage;
		this.prescribedDate = prescribedDate;
		this.instructions = instructions;
	}

	public Prescription() {
		super();
	}

	public void resetButton() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		prescriptionId = null;
		sessionMap.remove("prescriptionId");
		sessionMap.remove("presId");		
	}
	
	public void clearButton() {
		prescriptionId = "";
	}

}
