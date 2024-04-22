package com.java.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DOCTOR_MASTER")
public class DoctorMaster implements Serializable {

	@Id
	@Column(name="Dr_id")
	private String drId;
	
	@Column(name="Dr_name")
	private String drName;
	
	@Column(name="Dept")
	private String Dept;
	public String getDrId() {
		return drId;
	}
	public void setDrId(String drId) {
		this.drId = drId;
	}
	@Override
	public String toString() {
		return "DoctorMaster [drId=" + drId + ", drName=" + drName + ", Dept=" + Dept + "]";
	}
	public String getDrName() {
		return drName;
	}
	public void setDrName(String drName) {
		this.drName = drName;
	}
	public String getDept() {
		return Dept;
	}
	public void setDept(String dept) {
		Dept = dept;
	}
	public DoctorMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorMaster(String drId, String drName, String dept) {
		super();
		this.drId = drId;
		this.drName = drName;
		Dept = dept;
	}
	
	
}
