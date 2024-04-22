package com.java.ejb;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pharmacy")
public class PharmacyEnroll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pharm_Id")
	private String pharmacyId;

	@Column(name = "shop_Name")
	private String shopName;

	@Column(name = "address")
	private String address;

	@Column(name = "lic_No")
	private String licenseNo;

	@Column(name = "owner_Name")
	private String ownerName;

	@Column(name = "regd_No")
	private String regdNo;

	@Column(name = "date_Of_Registration")
	private Date dateOfRegistration;

	@Column(name = "qualification")
	private String qualification;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "regulatory")
	private String regulatory;

	public String getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getRegdNo() {
		return regdNo;
	}

	public void setRegdNo(String regdNo) {
		this.regdNo = regdNo;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegulatory() {
		return regulatory;
	}

	public void setRegulatory(String regulatory) {
		this.regulatory = regulatory;
	}

	@Override
	public String toString() {
		return "PharmacyEnroll [pharmacyId=" + pharmacyId + ", shopName=" + shopName + ", address=" + address
				+ ", licenseNo=" + licenseNo + ", ownerName=" + ownerName + ", regdNo=" + regdNo
				+ ", dateOfRegistration=" + dateOfRegistration + ", qualification=" + qualification + ", phone=" + phone
				+ ", email=" + email + ", regulatory=" + regulatory + "]";
	}

	public PharmacyEnroll(String pharmacyId, String shopName, String address, String licenseNo, String ownerName,
			String regdNo, Date dateOfRegistration, String qualification, String phone, String email,
			String regulatory) {
		super();
		this.pharmacyId = pharmacyId;
		this.shopName = shopName;
		this.address = address;
		this.licenseNo = licenseNo;
		this.ownerName = ownerName;
		this.regdNo = regdNo;
		this.dateOfRegistration = dateOfRegistration;
		this.qualification = qualification;
		this.phone = phone;
		this.email = email;
		this.regulatory = regulatory;
	}

	public PharmacyEnroll() {
		super();
	}

}
