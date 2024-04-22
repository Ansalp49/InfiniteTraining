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
@Table(name = "Provider_Details")
public class ProviderDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "providerid")
	private String providerid;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "DateOfBirth")
	private Date dateOfBirth;

	@Column(name = "Enrollmentdate")
	private Date enrollmentdate;

	@Column(name = "email")
	private String email;

	@Column(name = "LicenseNumber")
	private String licenseNumber;

	@Column(name = "Qualification")
	private String qualification;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private String status;

	@Column(name = "phoneno")
	private String phoneno;

	@Column(name = "gender")
	private Gender gender;

	@Column(name = "speciality")
	private String speciality;

	@Column(name = "address")
	private String address;

	@Column(name = "IsActive")
	private String isActive;

	@Column(name = "comments")
	private String comments;

	@Column(name = "security_question")
	private String securityquestion;

	@Override
	public String toString() {
		return "ProviderDetails [providerid=" + providerid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", enrollmentdate=" + enrollmentdate + ", email=" + email
				+ ", licenseNumber=" + licenseNumber + ", qualification=" + qualification + ", username=" + username
				+ ", password=" + password + ", status=" + status + ", phoneno=" + phoneno + ", gender=" + gender
				+ ", speciality=" + speciality + ", address=" + address + ", isActive=" + isActive + ", comments="
				+ comments + ", securityquestion=" + securityquestion + ", securityanswer=" + securityanswer + "]";
	}

	@Column(name = "security_answer")
	private String securityanswer;

	public String getProviderid() {
		return providerid;
	}

	public void setProviderid(String providerid) {
		this.providerid = providerid;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getEnrollmentdate() {
		return enrollmentdate;
	}

	public void setEnrollmentdate(Date enrollmentdate) {
		this.enrollmentdate = enrollmentdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSecurityquestion() {
		return securityquestion;
	}

	public void setSecurityquestion(String securityquestion) {
		this.securityquestion = securityquestion;
	}

	public String getSecurityanswer() {
		return securityanswer;
	}

	public void setSecurityanswer(String securityanswer) {
		this.securityanswer = securityanswer;
	}

	public ProviderDetails(String providerid, String firstName, String lastName, Date dateOfBirth, Date enrollmentdate,
			String email, String licenseNumber, String qualification, String username, String password, String status,
			String phoneno, Gender gender, String speciality, String address, String isActive, String comments,
			String securityquestion, String securityanswer) {
		super();
		this.providerid = providerid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.enrollmentdate = enrollmentdate;
		this.email = email;
		this.licenseNumber = licenseNumber;
		this.qualification = qualification;
		this.username = username;
		this.password = password;
		this.status = status;
		this.phoneno = phoneno;
		this.gender = gender;
		this.speciality = speciality;
		this.address = address;
		this.isActive = isActive;
		this.comments = comments;
		this.securityquestion = securityquestion;
		this.securityanswer = securityanswer;
	}

	public ProviderDetails() {
		super();
	}

}
