package com.java.ejb;

public class PharmacyLogin {
	private int authId;
	private String pharmId;
	private String email;
	private String password;
	private String status;
	private String otp;
	public int getAuthId() {
		return authId;
	}
	public void setAuthId(int authId) {
		this.authId = authId;
	}
	public String getPharmId() {
		return pharmId;
	}
	public void setPharmId(String pharmId) {
		this.pharmId = pharmId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
