package com.java.ejb;

public interface PharmacyDao {
	
	MedicineEntry searchMedicine(int entryId);

	Prescription searchPrescription(String prescriptionId);

	ProviderDetails searchProvider(String providerId);

	PharmacyEnroll searchPharm(String pharmacyId);

	RecipientDetails searchRecipient(String uhid);
}
