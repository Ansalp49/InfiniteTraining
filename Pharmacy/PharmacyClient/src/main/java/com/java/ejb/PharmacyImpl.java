package com.java.ejb;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

public class PharmacyImpl implements PharmacyDao {

	SessionFactory sf;
	Session session;

	private int desiredQuantity;
	private String medName;

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public int getDesiredQuantity() {
		return desiredQuantity;
	}

	public void setDesiredQuantity(int desiredQuantity) {
		this.desiredQuantity = desiredQuantity;
	}

	public String addMedicineEjb(MedicineEntry entry) throws NamingException {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		String pharmaId = (String) sessionMap.get("pharmId");
		entry.setPharmId(pharmaId);

		PharmaBeanRemote remote = RemoteHelper.lookupRemoteStatelessPharmacy();
		remote.addMedicine(entry);
		sessionMap.put("added", "Added Successfully");
		return "ShowMedicinesPagination.jsp?faces-redirect=true";
	}

	public String addSaleHistoryEjb(List<MedicineEntry> medicineEntry) throws NamingException {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		String pharmaId = (String) sessionMap.get("pharmId");
		String providerId = (String) sessionMap.get("providerId");
		String recipientId = (String) sessionMap.get("recipientId");
		String prescriptionId = (String) sessionMap.get("prescriptionId");

		searchPharm(pharmaId);
		searchProvider(providerId);
		searchPrescription(prescriptionId);
		searchRecipient(recipientId);

		Double eachPrice;
		Double totalPrice = 0.0;
		Double price;

		MedicineSales sales;

		for (MedicineEntry entry : medicineEntry) {
			System.out.println("Entry:- " + entry);

			if (entry.getSaleQuantity() > 0) {
				sales = new MedicineSales();

				price = entry.getPrice();
				eachPrice = price * entry.getSaleQuantity();
				totalPrice = totalPrice + eachPrice;
				deductQuantity(entry.getSaleQuantity(), entry.getEntryId());

				sales.setPharmId(pharmaId);
				sales.setEntryId(entry.getEntryId());
				sales.setMedId(entry.getMedId());
				sales.setMedName(entry.getMedName());
				sales.setPrice(price);
				sales.setQuantity(entry.getSaleQuantity());
				sales.setTotalPrice(eachPrice);
				sales.setProviderId(providerId);
				sales.setRecipientId(recipientId);
				sales.setPrescriptionId(prescriptionId);

				PharmaBeanRemote remote = RemoteHelper.lookupRemoteStatelessPharmacy();
				remote.addSaleHistory(sales);
				String formattedTotalPrice = String.format("%.2f", totalPrice);
				sessionMap.put("totalprice", formattedTotalPrice);
				System.out.println("Total Price: " + formattedTotalPrice);
				System.out.println("Sold Successfully");
				sessionMap.put("success", "Sold successfully");

			} else {
				System.out.println("Sale quantity is zero or invalid for medicine: " + entry.getMedName());
			}
		}
		return "ViewDetails.jsp?faces-redirect=true";
	}

	public Double calculateTotalPrice(int quantity, double price) {
		return quantity * price;
	}

	public void deductQuantity(int desiredQuantity, int entryId) {
		System.out.println("entryId:" + entryId);

		MedicineEntry medicines = searchMedicine(entryId);
		System.out.println("existingSales:" + medicines);
		if (medicines != null) {
			if (medicines.getQuantity() >= desiredQuantity) {
				System.out.println("test: " + desiredQuantity);
				medicines.setQuantity(medicines.getQuantity() - desiredQuantity);

				SessionFactory sf = SessionHelper.getConnection();
				Session session = sf.openSession();
				Transaction transaction = session.beginTransaction();
				session.update(medicines);
				transaction.commit();
			}
		}
	}

	public String redirectToViewMedicine(String prescriptionId) {
		System.out.println("Inside redirect to medicine");
		System.out.println("Pres Id: " + prescriptionId);

		sf = SessionHelper.getConnection();
		session = sf.openSession();

		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		Criteria prescriptionCr = session.createCriteria(Prescription.class);
		prescriptionCr.add(Restrictions.eq("prescriptionId", prescriptionId));
		Prescription prescription = (Prescription) prescriptionCr.uniqueResult();

		if (prescription != null) {
			sessionMap.put("searchPres", prescriptionId);
			sessionMap.put("medFoundForSale", prescription.getMedicine());
			sessionMap.put("providerId", prescription.getProviderid());
			sessionMap.put("recipientId", prescription.getRecipentId());
			sessionMap.put("prescriptionId", prescription.getPrescriptionId());
			System.out.println("medFoundForSale: " + prescription.getMedicine());
			System.out.println("providerId: " + prescription.getProviderid());
			System.out.println("recipientId: " + prescription.getRecipentId());
			System.out.println("prescriptionId: " + prescription.getPrescriptionId());
		} else {
			System.out.println("No Prescription found.");
		}
		return "SaleMedicine.jsp?faces-redirect=true";
	}

	public List<MedicineSales> showDetails(String prescriptionId, String recipientId, String providerId, String pharmId)
			throws ParseException {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria cr = session.createCriteria(MedicineSales.class);

		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		pharmId = (String) sessionMap.get("pharmId");
		prescriptionId = (String) sessionMap.get("prescriptionId");
		recipientId = (String) sessionMap.get("recipientId");
		providerId = (String) sessionMap.get("providerId");

		cr.add(Restrictions.eq("pharmId", pharmId));
		cr.add(Restrictions.eq("prescriptionId", prescriptionId));
		cr.add(Restrictions.eq("recipientId", recipientId));
		cr.add(Restrictions.eq("providerId", providerId));

		List<MedicineSales> detailList = cr.list();

		return detailList;
	}

	@Override
	public MedicineEntry searchMedicine(int entryId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria cr = session.createCriteria(MedicineEntry.class);
		cr.add(Restrictions.eq("entryId", entryId));
		MedicineEntry medent = (MedicineEntry) cr.uniqueResult();
		System.out.println("entry:" + entryId);
		return medent;
	}

	@Override
	public Prescription searchPrescription(String prescriptionId) {
		System.out.println("Hit Pres");
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		Criteria prescriptionCr = session.createCriteria(Prescription.class);
		prescriptionCr.add(Restrictions.eq("prescriptionId", prescriptionId));

		Prescription prescription = (Prescription) prescriptionCr.uniqueResult();

		if (prescription != null) {
			sessionMap.put("medFoundForSale", prescription.getMedicine());
			sessionMap.put("providerId", prescription.getProviderid());
			sessionMap.put("recipientId", prescription.getRecipentId());
			sessionMap.put("prescriptionId", prescription.getPrescriptionId());
			System.out.println("medFoundForSale: " + prescription.getMedicine());
			System.out.println("providerId: " + prescription.getProviderid());
			System.out.println("recipientId: " + prescription.getRecipentId());
			System.out.println("prescriptionId: " + prescription.getPrescriptionId());
		} else {
			System.out.println("No Prescription found.");
		}

		return prescription;
	}

	public List<MedicineEntry> searchMedNameFromPres(String medicine) {
		System.out.println("Pres-" + medicine);
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		sessionMap.remove("medSearchError");

		if (medicine != null && !medicine.isEmpty()) {
			List<String> medNameList = Arrays.asList(medicine.split(","));
			System.out.println("Medicine List:- " + medNameList);
			Criteria listCriteria = session.createCriteria(MedicineEntry.class);
			Disjunction disjunction = Restrictions.disjunction();

			for (String medName : medNameList) {
				disjunction.add(Restrictions.like("medName", "%" + medName.trim() + "%"));
			}

			listCriteria.add(disjunction);
			List<MedicineEntry> medEntries = listCriteria.list();
			System.out.println("List: " + medEntries);

			if (!medEntries.isEmpty()) {
				for (MedicineEntry uniqueMedEntry : medEntries) {
					if (uniqueMedEntry != null) {
						sessionMap.put("entryId", uniqueMedEntry.getEntryId());
						sessionMap.put("medId", uniqueMedEntry.getMedId());
						sessionMap.put("medName", uniqueMedEntry.getMedName());
						sessionMap.put("price", uniqueMedEntry.getPrice());

						System.out.println("entryId: " + uniqueMedEntry.getEntryId());
						System.out.println("medId: " + uniqueMedEntry.getMedId());
						System.out.println("medName: " + uniqueMedEntry.getMedName());
						System.out.println("price: " + uniqueMedEntry.getPrice());
					} else {
						String errorMessage = "Medicine not available: " + uniqueMedEntry.getMedName();
						sessionMap.put("medSearchError", errorMessage);
						System.out.println(errorMessage);
					}
				}
			} else {
				String errorMessage = "Medicine not available: " + medicine;
				sessionMap.put("medSearchError", errorMessage);
				System.out.println(errorMessage);
			}

			return medEntries;
		}
		return null;
	}

	public List<MedicineEntry> getListOfMedicine(int firstRow, int rowsPerPage) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		String medNameStr = (String) sessionMap.get("medFoundForSale");
		List<MedicineEntry> medicineList = searchMedNameFromPres(medNameStr);

		int rowsCount = medicineList.size();
		sessionMap.put("rowsCount", rowsCount);
		if (medicineList.isEmpty()) {
			String errorMessage = medNameStr + " Not Found in the Database.";
			sessionMap.put("medSearchError", errorMessage);
			System.out.println(errorMessage);
			return new ArrayList<>();
		}
		List<MedicineEntry> filteredMedicineList = new ArrayList<>();
		for (MedicineEntry medicine : medicineList) {
			if (medicine.isExpired()) {
				sessionMap.put("expired", "Expired!");
				System.out.println("Expired Medicine: " + medicine.getMedName());
			}
			if (medicine.isExpiryWithinOneMonth()) {
				sessionMap.put("expired", "Will Expire Soon!");
				System.out.println("Medicine Within One Month of Expiry: " + medicine.getMedName());
			}
			filteredMedicineList.add(medicine);
		}

		System.out.println("From Pagination " + firstRow);
		System.out.println("From Pagination Count " + rowsPerPage);
		System.out.println(filteredMedicineList);
		List<MedicineEntry> resultList = listPagination(firstRow, rowsPerPage, filteredMedicineList);
		System.out.println("list test" + resultList);
		return resultList;
	}

	public List<MedicineEntry> listPagination(int firstRow, int rowCount, List<MedicineEntry> medicineList) {
		List<MedicineEntry> resultList = new ArrayList<>();
		int listSize = medicineList.size();

		System.out.println("first: " + firstRow + "  count: " + rowCount + " listSize: " + listSize);

		if (firstRow >= 0 && firstRow <= listSize && rowCount >= 0) {
			int endIndex = Math.min(firstRow + rowCount, listSize);

			System.out.println("Sublist indices: " + firstRow + " to " + endIndex);
			resultList = medicineList.subList(firstRow, endIndex);
		} else {
			firstRow = 1;
			if (firstRow < listSize || rowCount <= listSize) {
				int endIndex = Math.min(firstRow + rowCount, listSize);
				System.out.println("Sublist indices: " + firstRow + " to " + endIndex);
				resultList = medicineList.subList(firstRow, endIndex);
			} else {
				System.out.println("Invalid indices for subList. Returning an empty list.");
			}
		}
		return resultList;
	}

	@Override
	public ProviderDetails searchProvider(String providerId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria cr = session.createCriteria(ProviderDetails.class);
		cr.add(Restrictions.eq("providerid", providerId));
		ProviderDetails prov = (ProviderDetails) cr.uniqueResult();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (prov != null) {
			sessionMap.put("providerName", prov.getFirstName());
			sessionMap.put("providerLast", prov.getLastName());
			sessionMap.put("provNumber", prov.getPhoneno());
			sessionMap.put("provmail", prov.getEmail());

			System.out.println("Provider First Name: " + prov.getFirstName());
			System.out.println("Provider Last Name: " + prov.getLastName());
		} else {
			System.out.println("Provider Not Found");
		}

		System.out.println("providerId:" + providerId);
		return prov;
	}

	@Override
	public RecipientDetails searchRecipient(String recipientId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria cr = session.createCriteria(RecipientDetails.class);
		cr.add(Restrictions.eq("recipientId", recipientId));
		RecipientDetails recipient = (RecipientDetails) cr.uniqueResult();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (recipient != null) {
			sessionMap.put("recipientFirst", recipient.getFirstName());
			sessionMap.put("recipientLast", recipient.getLastName());
			sessionMap.put("recphno", recipient.getPhoneno());
			sessionMap.put("recmail", recipient.getEmail());
			sessionMap.put("recadd", recipient.getAddress());

			System.out.println("RecipientFirst" + recipient.getFirstName());
			System.out.println("RecipientLast" + recipient.getLastName());
		} else {
			System.out.println("Recipient Not Found");
		}
		System.out.println("recipientId:" + recipientId);
		return recipient;
	}

	@Override
	public PharmacyEnroll searchPharm(String pharmacyId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria cr = session.createCriteria(PharmacyEnroll.class);
		cr.add(Restrictions.eq("pharmacyId", pharmacyId));
		PharmacyEnroll pharm = (PharmacyEnroll) cr.uniqueResult();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (pharm != null) {
			sessionMap.put("pharshop", pharm.getShopName());
			sessionMap.put("pharno", pharm.getPhone());
			sessionMap.put("pharmail", pharm.getEmail());
			sessionMap.put("pharadd", pharm.getAddress());

			System.out.println("pharshop" + pharm.getShopName());
		} else {
			System.out.println("Pharmacy Not Found");
		}
		System.out.println("pharmacyId: " + pharmacyId);
		return pharm;
	}

	public void plusOneQuantity(List<MedicineEntry> medicineList, int entryId) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.remove("quantityerror");

		for (MedicineEntry entry : medicineList) {
			if (entry.getEntryId() == entryId) {
				int currentQuantity = entry.getSaleQuantity();
				int availableQuantity = entry.getQuantity();

				if (currentQuantity < availableQuantity) {
					entry.setSaleQuantity(currentQuantity + 1);
				} else {
					sessionMap.put("quantityerror", "*Cannot exceed available quantity.");
				}
			}
		}
	}

	public void minusOneQuantity(List<MedicineEntry> medicineList, int entryId) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.remove("quantityerror");

		for (MedicineEntry entry : medicineList) {
			if (entry.getEntryId() == entryId) {
				int currentQuantity = entry.getSaleQuantity();

				if (currentQuantity > 0) {
					entry.setSaleQuantity(currentQuantity - 1);
				} else {
					sessionMap.put("quantityerror", "*Quantity cannot be negative.");
				}
			}
		}
	}

	public List<MedicineEntry> sortByMedId(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getMedId));
		return serviceList;
	}

	public List<MedicineEntry> sortByMedIddesc(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getMedId).reversed());
		return serviceList;
	}

	public List<MedicineEntry> sortByMedName(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getMedName));
		return serviceList;
	}

	public List<MedicineEntry> sortByMedNamedesc(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getMedName).reversed());
		return serviceList;
	}

	public List<MedicineEntry> sortByEntry(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getEntryDate));
		return serviceList;
	}

	public List<MedicineEntry> sortByEntrydesc(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getEntryDate).reversed());
		return serviceList;
	}

	public List<MedicineEntry> sortByMfg(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getMfgDate));
		return serviceList;
	}

	public List<MedicineEntry> sortByMfgdesc(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getMfgDate).reversed());
		return serviceList;
	}

	public List<MedicineEntry> sortByExp(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getExpiryDate));
		return serviceList;
	}

	public List<MedicineEntry> sortByExpdesc(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getExpiryDate).reversed());
		return serviceList;
	}

	public List<MedicineEntry> sortByQuantity(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getQuantity));
		return serviceList;
	}

	public List<MedicineEntry> sortByQuantitydesc(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getQuantity).reversed());
		return serviceList;
	}

	public List<MedicineEntry> sortByPrice(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getPrice));
		return serviceList;
	}

	public List<MedicineEntry> sortByPricedesc(int firstRow, int rowsPerPage) {
		List<MedicineEntry> serviceList = getListOfMedicine(firstRow, rowsPerPage);
		Collections.sort(serviceList, Comparator.comparing(MedicineEntry::getPrice).reversed());
		return serviceList;
	}

}