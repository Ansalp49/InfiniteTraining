package com.java.ejb;

import javax.ejb.Remote;

@Remote
public interface PharmaBeanRemote {

	String addMedicine(MedicineEntry entry);
	String addSaleHistory(MedicineSales sales);
}
