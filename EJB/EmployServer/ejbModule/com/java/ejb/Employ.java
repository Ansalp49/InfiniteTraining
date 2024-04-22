package com.java.ejb;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employ")
public class Employ implements Serializable {

	@Id
	@Column(name = "EmpId")
	private int empid;

	@Column(name = "EmpName")
	private String empName;

	@Column(name = "EmpEmail")
	private String empEmail;

	@Column(name = "MobileNo")
	private String mobileNo;

	@Column(name = "Address")
	private String address;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "DateOfJoin")
	private Date joinDate;

	@Column(name = "ManagerId")
	private int managerId;

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Employ [empid=" + empid + ", empName=" + empName + ", empEmail=" + empEmail + ", mobileNo=" + mobileNo
				+ ", address=" + address + ", gender=" + gender + ", joinDate=" + joinDate + ", managerId=" + managerId
				+ "]";
	}

	public Employ() {
		super();
	}

	public Employ(int empid, String empName, String empEmail, String mobileNo, String address, String gender,
			Date joinDate, int managerId) {
		super();
		this.empid = empid;
		this.empName = empName;
		this.empEmail = empEmail;
		this.mobileNo = mobileNo;
		this.address = address;
		this.gender = gender;
		this.joinDate = joinDate;
		this.managerId = managerId;
	}

}
