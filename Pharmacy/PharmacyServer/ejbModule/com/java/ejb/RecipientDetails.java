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
@Table(name = "patient_enrollment")
public class RecipientDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UHID")
	private String recipientId;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "Gender")
	private Gender Gender;

	@Column(name = "userName")
	private String userName;

	@Column(name = "Phoneno")
	private String Phoneno;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private String status;

	@Column(name = "address")
	private String address;

	@Column(name = "medHistory")
	private String medHistory;

	@Column(name = "countryCode")
	private String countryCode;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "postalCode")
	private String postalCode;

	@Column(name = "country")
	private String country;

	@Column(name = "permanentAddress")
	private String permanentAddress;

	@Column(name = "permanentCity")
	private String permanentCity;

	@Column(name = "permanentState")
	private String permanentState;

	@Column(name = "permanentPostalCode")
	private String permanentPostalCode;

	@Column(name = "permanentCountry")
	private String permanentCountry;

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return Gender;
	}

	public void setGender(Gender gender) {
		Gender = gender;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneno() {
		return Phoneno;
	}

	public void setPhoneno(String phoneno) {
		Phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMedHistory() {
		return medHistory;
	}

	public void setMedHistory(String medHistory) {
		this.medHistory = medHistory;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getPermanentPostalCode() {
		return permanentPostalCode;
	}

	public void setPermanentPostalCode(String permanentPostalCode) {
		this.permanentPostalCode = permanentPostalCode;
	}

	public String getPermanentCountry() {
		return permanentCountry;
	}

	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}

	public RecipientDetails(String recipientId, String firstName, String lastName, Date dob, com.java.ejb.Gender gender,
			String userName, String phoneno, String email, String status, String address, String medHistory,
			String countryCode, String city, String state, String postalCode, String country, String permanentAddress,
			String permanentCity, String permanentState, String permanentPostalCode, String permanentCountry) {
		super();
		this.recipientId = recipientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		Gender = gender;
		this.userName = userName;
		Phoneno = phoneno;
		this.email = email;
		this.status = status;
		this.address = address;
		this.medHistory = medHistory;
		this.countryCode = countryCode;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.permanentAddress = permanentAddress;
		this.permanentCity = permanentCity;
		this.permanentState = permanentState;
		this.permanentPostalCode = permanentPostalCode;
		this.permanentCountry = permanentCountry;
	}

	public RecipientDetails() {
		super();
	}

	@Override
	public String toString() {
		return "RecipientDetails [recipientId=" + recipientId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dob=" + dob + ", Gender=" + Gender + ", userName=" + userName + ", Phoneno=" + Phoneno + ", email="
				+ email + ", status=" + status + ", address=" + address + ", medHistory=" + medHistory
				+ ", countryCode=" + countryCode + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode
				+ ", country=" + country + ", permanentAddress=" + permanentAddress + ", permanentCity=" + permanentCity
				+ ", permanentState=" + permanentState + ", permanentPostalCode=" + permanentPostalCode
				+ ", permanentCountry=" + permanentCountry + "]";
	}

}
