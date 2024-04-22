package com.java.ejb;

public interface PharmaLoginDAO {
	
	public String validateOtp(PharmacyLogin pharma);
	public String loginDao(PharmacyLogin pharma);
}
