package com.java.ejb;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class MainImpl {

	static SessionFactory sf;
	static Session session;

	private static String medName;

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		MainImpl.medName = medName;
	}

	private static String medName1;

	public String getMedName1() {
		return medName1;
	}

	public void setMedName1(String medName1) {
		MainImpl.medName1 = medName1;
	}

//-------------------------Delete Expired Medicine-----------------------------------

	public String deleteExpiredMedicinesFromDatabase() {

		sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();

		Query query = session.createQuery("FROM Medicines WHERE expiryDate < CURRENT_DATE");

		List<MedicineEntry> expiredMedicines = query.list();

		// Delete each expired medicine
		for (MedicineEntry medicine : expiredMedicines) {
			session.delete(medicine);
		}

		trans.commit();
		session.close();
		sf.close();

		return "deleted";

	}

//-----------------------------Redirect Method --------------------------------------------

	public boolean isExpiryWithinOneMonthh(Date expiryDate) {
		Calendar oneMonthAhead = Calendar.getInstance();
		oneMonthAhead.add(Calendar.MONTH, 1);

		Calendar today = Calendar.getInstance();

		return expiryDate != null && expiryDate.after(today.getTime()) && expiryDate.before(oneMonthAhead.getTime());
	}

	public boolean isExpiredd(Date expiryDate) {
		Calendar today = Calendar.getInstance();
		return expiryDate != null && expiryDate.before(today.getTime());
	}

	public String redirecttoUpdateMedicine(String medId, String medName, String type, int quantity, Date mfgDate,
			Date expiryDate, double price) {

		System.out.println("Hittingggg");
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		FacesContext context = FacesContext.getCurrentInstance();

		sessionMap.put("medId", medId);
		sessionMap.put("medName", medName);
		sessionMap.put("type", type);
		sessionMap.put("quantity", quantity);
		sessionMap.put("mfgDate", mfgDate);
		sessionMap.put("expiryDate", expiryDate);
		sessionMap.put("price", price);

		if (quantity < 10) {
			System.out.println("Hiting redirect method");
			if (medId.startsWith("E0")) {
				return "EquipmentUpdate.jsf?faces-redirect=true";
			} else {
				return "UpdateMedicine.jsf?faces-redirect=true";
			}

		}
		if (isExpiryWithinOneMonthh(expiryDate)) {
			System.out.println("Hiting redirect method");
			if (medId.startsWith("E0")) {
				return "EquipmentUpdate.jsf?faces-redirect=true";
			} else {
				return "UpdateMedicine.jsf?faces-redirect=true";
			}
		}
		if (isExpiredd(expiryDate)) {
			System.out.println("Hiting redirect method");
			if (medId.startsWith("E0")) {
				return "EquipmentUpdate.jsf?faces-redirect=true";
			} else {
				return "UpdateMedicine.jsf?faces-redirect=true";
			}
		}
		context.addMessage(null, new FacesMessage(
				"UPDATE ONLY IF QUANTITY IS LESS THEN 10 (OR) EXPIRY DATE IS LESS THEN ONE MONTH TO TODAY'S DATE."));
		System.out.println("Hiting redirecttttttttt method");

		return null;

	}

//---------------------------------Update Method --------------------------------------------------------

	public String Updatemedicine(String medId, String medName, String type, int quantity, Date mfgDate, Date expiryDate,
			double price) throws ParseException {

		sf = SessionHelper.getConnection();
		session = sf.openSession();

		System.out.println("Hiiiiiiiii");

		MedicineEntry medicines = new MedicineEntry();
		medicines.setMedId(medId);
		medicines.setMedName(medName);
		medicines.setType(type);
		medicines.setQuantity(quantity);
		medicines.setMfgDate(mfgDate);
		medicines.setExpiryDate(expiryDate);
		medicines.setPrice(price);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sdate = sdf.format(date);
		Date utildate = sdf.parse(sdate);
		java.sql.Date sqlentryDate = new java.sql.Date(utildate.getTime());
		medicines.setEntryDate(sqlentryDate);

		Transaction trans = session.beginTransaction();
		session.save(medicines);
		trans.commit();
		session.close();
		sf.close();

		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

//--------------------------Show Pagination--------------------------------------

	public List<MedicineEntry> searchByMedName(int firstRow, int rowCount, String medName) {

		System.out.println("test1 " + medName);
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(MedicineEntry.class);
//		Date datee = new Date();
//		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
//		String sdate = sdft.format(datee);
//		Date utildate = sdft.parse(sdate);
//		java.sql.Date sqlentryDate = new java.sql.Date(utildate.getTime());
//		criteria.add(Restrictions.ge("expiryDate", sqlentryDate));	

		if (medName.length() > 0 || medName != null) {
			System.out.println("working");
			criteria.add(Restrictions.like("medName", medName + "%"));
			criteria.setFirstResult(firstRow);
			criteria.setMaxResults(rowCount);

		}
		// sorting by medId
		if (orderbyMedId.equals("asc")) {
			System.out.println("order: " + orderbyMedId);
			criteria.addOrder(Order.asc("medId"));
		}

		else {
			System.out.println("Done");
		}
		// sorting by medIdDes

		if (orderbyMedIdDes.equals("desc")) {
			System.out.println("order: " + orderbyMedIdDes);
			criteria.addOrder(Order.desc("medId"));
		} else {
			System.out.println("Done");
		}

		// sorting by medName
		if (orderbyMedName.equals("asc")) {
			System.out.println("order: " + orderbyMedName);
			criteria.addOrder(Order.asc("medName"));
		} else {
			System.out.println("Done");
		}

		// sorting by medNameDes
		if (orderbyMedNameDes.equals("desc")) {
			System.out.println("order: " + orderbyMedNameDes);
			criteria.addOrder(Order.desc("medName"));
		} else {
			System.out.println("Done");
		}
		// sorting by EntryId
		if (orderbyEntryID.equals("asc")) {
			System.out.println("order: " + orderbyEntryID);
			criteria.addOrder(Order.asc("entryId"));
		} else {
			System.out.println("Done");
		}

		// sorting by EntryIdDes
		if (orderbyEntryIDDes.equals("desc")) {
			System.out.println("order: " + orderbyEntryIDDes);
			criteria.addOrder(Order.desc("entryId"));
		} else {
			System.out.println("Done");
		}

		// sorting by Type
		if (orderbyType.equals("asc")) {
			System.out.println("order: " + orderbyType);
			criteria.addOrder(Order.asc("type"));
		} else {
			System.out.println("Done");
		}

		// sorting by TypeDes
		if (orderbyTypeDes.equals("desc")) {
			System.out.println("order: " + orderbyTypeDes);
			criteria.addOrder(Order.desc("type"));
		} else {
			System.out.println("Done");
		}

		// sorting by Quantity
		if (orderbyQuantity.equals("asc")) {
			System.out.println("order: " + orderbyQuantity);
			criteria.addOrder(Order.asc("quantity"));
		} else {
			System.out.println("Done");
		}

		// sorting by QuantityDes
		if (orderbyQuantityDes.equals("desc")) {
			System.out.println("order: " + orderbyQuantityDes);
			criteria.addOrder(Order.desc("quantity"));
		} else {
			System.out.println("Done");
		}

		// sorting by EntryDate
		if (orderbyEntryDate.equals("asc")) {
			System.out.println("order: " + orderbyEntryDate);
			criteria.addOrder(Order.asc("entryDate"));
		} else {
			System.out.println("Done");
		}

		// sorting by EntryDateDes
		if (orderbyEntryDateDes.equals("desc")) {
			System.out.println("order: " + orderbyEntryDateDes);
			criteria.addOrder(Order.desc("entryDate"));
		} else {
			System.out.println("Done");
		}

		// sorting by mfgDate
		if (orderbyManfactureDate.equals("asc")) {
			System.out.println("order: " + orderbyManfactureDate);
			criteria.addOrder(Order.asc("mfgDate"));
		} else {
			System.out.println("Done");
		}

		// sorting by mfgDateDes
		if (orderbyManfactureDateDes.equals("desc")) {
			System.out.println("order: " + orderbyManfactureDateDes);
			criteria.addOrder(Order.desc("mfgDate"));
		} else {
			System.out.println("Done");
		}

		// sorting by expiryDate
		if (orderbyExpiryDate.equals("asc")) {
			System.out.println("order: " + orderbyExpiryDate);
			criteria.addOrder(Order.asc("expiryDate"));
		} else {
			System.out.println("Done");
		}

		// sorting by expiryDateDes
		if (orderbyExpiryDateDes.equals("desc")) {
			System.out.println("order: " + orderbyExpiryDateDes);
			criteria.addOrder(Order.desc("expiryDate"));
		} else {
			System.out.println("Done");
		}

		// sorting by Price
		if (orderbyPrice.equals("asc")) {
			System.out.println("order: " + orderbyPrice);
			criteria.addOrder(Order.asc("price"));
		} else {
			System.out.println("Done");
		}

		// sorting by PriceDes
		if (orderbyPriceDes.equals("desc")) {
			System.out.println("order: " + orderbyPriceDes);
			criteria.addOrder(Order.desc("price"));
		} else {
			System.out.println("Done");
		}

		criteria.setFirstResult(firstRow);
		criteria.setMaxResults(rowCount);
		System.out.println(criteria.list());

		return criteria.list();
	}

	public int countRows(String medName) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(MedicineEntry.class);
			if (criteria != null) {
				if (medName.length() > 0 || medName != null) {
					criteria.add(Restrictions.like("medName", medName + "%"));

				}

				return criteria.list().size();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

//------------------------------------------------------------

// SHOW SORTING...
	static String orderbyEntryID = "sort";
	static String orderbyMedId = "sort";
	static String orderbyMedName = "sort";
	static String orderbyType = "sort";
	static String orderbyQuantity = "sort";
	static String orderbyEntryDate = "sort";
	static String orderbyManfactureDate = "sort";
	static String orderbyExpiryDate = "sort";
	static String orderbyPrice = "sort";

	static String orderbyEntryIDDes = "sort";
	static String orderbyMedIdDes = "sort";
	static String orderbyMedNameDes = "sort";
	static String orderbyTypeDes = "sort";
	static String orderbyQuantityDes = "sort";
	static String orderbyEntryDateDes = "sort";
	static String orderbyManfactureDateDes = "sort";
	static String orderbyExpiryDateDes = "sort";
	static String orderbyPriceDes = "sort";

	public String sortByEntryId() {
		System.out.println("sort..");
		if (orderbyEntryID.length() == 4) {
			orderbyEntryID = "asc";

			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyEntryID.equals("desc")) {
			orderbyEntryID = "asc";

			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByMedId() {
		System.out.println("sort..");
		if (orderbyMedId.length() == 4) {
			orderbyMedId = "asc";

			orderbyEntryID = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyMedId.equals("desc")) {
			orderbyMedId = "asc";

			orderbyEntryID = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByMedName() {
		System.out.println("sort..");
		if (orderbyMedName.length() == 4) {
			orderbyMedName = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyMedName.equals("desc")) {
			orderbyMedName = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";
		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByType() {
		System.out.println("sort..");
		if (orderbyType.length() == 4) {
			orderbyType = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyType.equals("desc")) {
			orderbyType = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByQuantity() {
		System.out.println("sort..");
		if (orderbyQuantity.length() == 4) {
			orderbyQuantity = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyQuantity.equals("desc")) {
			orderbyQuantity = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByEntryDate() {
		System.out.println("sort..");
		if (orderbyEntryDate.length() == 4) {
			orderbyEntryDate = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyEntryDate.equals("desc")) {
			orderbyEntryDate = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByManufactureDate() {
		System.out.println("sort..");
		if (orderbyManfactureDate.length() == 4) {
			orderbyManfactureDate = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyManfactureDate.equals("desc")) {
			orderbyManfactureDate = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByExpiryDate() {
		System.out.println("sort..");
		if (orderbyExpiryDate.length() == 4) {
			orderbyExpiryDate = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyExpiryDate.equals("desc")) {
			orderbyExpiryDate = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByPrice() {
		System.out.println("sort..");
		if (orderbyPrice.length() == 4) {
			orderbyPrice = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyPrice.equals("desc")) {
			orderbyPrice = "asc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByEntryIdDes() {
		System.out.println("sort..");
		if (orderbyEntryIDDes.length() == 4) {
			orderbyEntryIDDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyEntryIDDes.equals("asc")) {
			orderbyEntryIDDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyMedIdDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByMedIdDes() {
		System.out.println("sort..");
		if (orderbyMedIdDes.length() == 4) {
			orderbyMedIdDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyMedIdDes.equals("asc")) {
			orderbyMedIdDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";
		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByMedNameDes() {
		System.out.println("sort..");
		if (orderbyMedNameDes.length() == 4) {
			orderbyMedNameDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyMedNameDes.equals("asc")) {
			orderbyMedNameDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByTypeDes() {
		System.out.println("sort..");
		if (orderbyTypeDes.length() == 4) {
			orderbyTypeDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyTypeDes.equals("asc")) {
			orderbyTypeDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByQuantityDes() {
		System.out.println("sort..");
		if (orderbyQuantityDes.length() == 4) {
			orderbyQuantityDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyQuantityDes.equals("asc")) {
			orderbyQuantityDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByEntryDateDes() {
		System.out.println("sort..");
		if (orderbyEntryDateDes.length() == 4) {
			orderbyEntryDateDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyEntryDateDes.equals("asc")) {
			orderbyEntryDateDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";
		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByManufactureDateDes() {
		System.out.println("sort..");
		if (orderbyManfactureDateDes.length() == 4) {
			orderbyManfactureDateDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyManfactureDateDes.equals("asc")) {
			orderbyManfactureDateDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyExpiryDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByExpiryDateDes() {
		System.out.println("sort..");
		if (orderbyExpiryDateDes.length() == 4) {
			orderbyExpiryDateDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyPriceDes = "sort";

		} else if (orderbyExpiryDateDes.equals("asc")) {
			orderbyExpiryDateDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyPriceDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

	public String sortByPriceDes() {
		System.out.println("sort..");
		if (orderbyPriceDes.length() == 4) {
			orderbyPriceDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";

		} else if (orderbyPriceDes.equals("asc")) {
			orderbyPriceDes = "desc";

			orderbyEntryID = "sort";
			orderbyMedId = "sort";
			orderbyMedName = "sort";
			orderbyType = "sort";
			orderbyQuantity = "sort";
			orderbyEntryDate = "sort";
			orderbyManfactureDate = "sort";
			orderbyExpiryDate = "sort";
			orderbyPrice = "sort";

			orderbyEntryIDDes = "sort";
			orderbyMedId = "sort";
			orderbyMedNameDes = "sort";
			orderbyTypeDes = "sort";
			orderbyQuantityDes = "sort";
			orderbyEntryDateDes = "sort";
			orderbyManfactureDateDes = "sort";
			orderbyExpiryDateDes = "sort";

		}
		return "ShowMedicinesPagination.jsp?faces-redirect=true";

	}

//------------------------------Total Pagination -----------------------------------

	public List<MedList> totalMedicinesList(int firstRow, int rowCount, String medName1) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria criteria = session.createCriteria(MedicineEntry.class);

		// Calculate the date that is 20 days from today
		LocalDate today = LocalDate.now();
		LocalDate expiryThreshold = today.plusDays(20);

		// Group by medId, medName, and type; and sum the quantity
		criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("medId").as("medId"))
				.add(Projections.groupProperty("medName").as("medName"))
				.add(Projections.groupProperty("type").as("type")).add(Projections.sum("quantity").as("quantity")));

		criteria.setResultTransformer(Transformers.aliasToBean(MedList.class));

		if (medName1 != null && medName1.length() > 0) {
			criteria.add(Restrictions.like("medName", medName1 + "%"));
		}

		// sorting by medId
		if (orderbyMedIdTot.equals("asc")) {
			System.out.println("order: " + orderbyMedIdTot);
			criteria.addOrder(Order.asc("medId"));
		}

		else {
			System.out.println("Done");
		}
		// sorting by medIdDes

		if (orderbyMedIdTotDes.equals("desc")) {
			System.out.println("order: " + orderbyMedIdTotDes);
			criteria.addOrder(Order.desc("medId"));
		} else {
			System.out.println("Done");
		}

		// sorting by medName
		if (orderbyMedNameTot.equals("asc")) {
			System.out.println("order: " + orderbyMedNameTot);
			criteria.addOrder(Order.asc("medName"));
		} else {
			System.out.println("Done");
		}

		// sorting by medNameDes
		if (orderbyMedNameTotDes.equals("desc")) {
			System.out.println("order: " + orderbyMedNameTotDes);
			criteria.addOrder(Order.desc("medName"));
		} else {
			System.out.println("Done");
		}

		// sorting by Type
		if (orderbyTypeTot.equals("asc")) {
			System.out.println("order: " + orderbyTypeTot);
			criteria.addOrder(Order.asc("type"));
		} else {
			System.out.println("Done");
		}

		// sorting by TypeDes
		if (orderbyTypeTotDes.equals("desc")) {
			System.out.println("order: " + orderbyTypeTotDes);
			criteria.addOrder(Order.desc("type"));
		} else {
			System.out.println("Done");
		}

		// sorting by Quantity
		if (orderbyQuantityTot.equals("asc")) {
			System.out.println("order: " + orderbyQuantityTot);
			criteria.addOrder(Order.asc("quantity"));
		} else {
			System.out.println("Done");
		}

		// sorting by QuantityDes
		if (orderbyQuantityTotDes.equals("desc")) {
			System.out.println("order: " + orderbyQuantityTotDes);
			criteria.addOrder(Order.desc("quantity"));
		} else {
			System.out.println("Done");
		}

		// Add restrictions for expiry date
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.gt("expiryDate",
				Date.from(expiryThreshold.atStartOfDay(ZoneId.systemDefault()).toInstant())));
		or.add(Restrictions.isNull("expiryDate"));
		criteria.add(or);

		criteria.setFirstResult(firstRow);
		criteria.setMaxResults(rowCount);

		return criteria.list();

	}

	public int countRowsList(String medName1) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(MedicineEntry.class);

			LocalDate today = LocalDate.now();
			LocalDate expiryThreshold = today.plusDays(20);

			criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("medId").as("medId"))
					.add(Projections.groupProperty("medName").as("medName"))
					.add(Projections.groupProperty("type").as("type")).add(Projections.sum("quantity"), "quantity"));

			criteria.setResultTransformer(Transformers.aliasToBean(MedList.class));

			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.gt("expiryDate",
					Date.from(expiryThreshold.atStartOfDay(ZoneId.systemDefault()).toInstant())));
			or.add(Restrictions.isNull("expiryDate"));
			criteria.add(or);

			if (criteria != null) {
				if (medName1.length() > 0 || medName1 != null) {
					// if (medName1 != null && medName1.length() > 0) {
					criteria.add(Restrictions.like("medName", medName1 + "%"));
				}

				return criteria.list().size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

//------------------------------------------------------------------------------------------------------------------

//----------------------------- Total Sorting ------------------------------------------------

	static String orderbyMedIdTot = "sort";
	static String orderbyMedNameTot = "sort";
	static String orderbyTypeTot = "sort";
	static String orderbyQuantityTot = "sort";

	static String orderbyMedIdTotDes = "sort";
	static String orderbyMedNameTotDes = "sort";
	static String orderbyTypeTotDes = "sort";
	static String orderbyQuantityTotDes = "sort";

	public String sortByMedIdTot() {
		System.out.println("sort..");
		if (orderbyMedIdTot.length() == 4) {
			orderbyMedIdTot = "asc";

			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		} else if (orderbyMedIdTot.equals("desc")) {
			orderbyMedIdTot = "asc";

			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		}
		return "TotalQuantity.jsp?faces-redirect=true";

	}

	public String sortBymedNameTot() {
		System.out.println("sort..");
		if (orderbyMedNameTot.length() == 4) {
			orderbyMedNameTot = "asc";

			orderbyMedIdTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		} else if (orderbyMedNameTot.equals("desc")) {
			orderbyMedNameTot = "asc";

			orderbyMedIdTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		}
		return "TotalQuantity.jsp?faces-redirect=true";

	}

	public String sortByTypeTot() {
		System.out.println("sort..");
		if (orderbyTypeTot.length() == 4) {
			orderbyTypeTot = "asc";

			orderbyMedNameTot = "sort";
			orderbyMedIdTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		} else if (orderbyTypeTot.equals("desc")) {
			orderbyTypeTot = "asc";

			orderbyMedNameTot = "sort";
			orderbyMedIdTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		}
		return "TotalQuantity.jsp?faces-redirect=true";

	}

	public String sortByQuantityTot() {
		System.out.println("sort..");
		if (orderbyQuantityTot.length() == 4) {
			orderbyQuantityTot = "asc";

			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyMedIdTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		} else if (orderbyQuantityTot.equals("desc")) {
			orderbyQuantityTot = "asc";

			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyMedIdTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		}
		return "TotalQuantity.jsp?faces-redirect=true";

	}

	public String sortByMedIdTotDes() {
		System.out.println("sort..");
		if (orderbyMedIdTotDes.length() == 4) {
			orderbyMedIdTotDes = "desc";

			orderbyMedIdTot = "sort";
			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		} else if (orderbyMedIdTotDes.equals("asc")) {
			orderbyMedIdTotDes = "desc";

			orderbyMedIdTot = "sort";
			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";
		}
		return "TotalQuantity.jsp?faces-redirect=true";

	}

	public String sortByMedNameTotDes() {
		System.out.println("sort..");
		if (orderbyMedNameTotDes.length() == 4) {
			orderbyMedNameTotDes = "desc";

			orderbyMedIdTot = "sort";
			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		} else if (orderbyMedNameTotDes.equals("asc")) {
			orderbyMedNameTotDes = "desc";

			orderbyMedIdTot = "sort";
			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedIdTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyQuantityTotDes = "sort";
		}
		return "TotalQuantity.jsp?faces-redirect=true";

	}

	public String sortByTypeTotDes() {
		System.out.println("sort..");
		if (orderbyTypeTotDes.length() == 4) {
			orderbyTypeTotDes = "desc";

			orderbyMedIdTot = "sort";
			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedNameTotDes = "sort";
			orderbyMedIdTotDes = "sort";
			orderbyQuantityTotDes = "sort";

		} else if (orderbyTypeTotDes.equals("asc")) {
			orderbyTypeTotDes = "desc";

			orderbyMedIdTot = "sort";
			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedNameTotDes = "sort";
			orderbyMedIdTotDes = "sort";
			orderbyQuantityTotDes = "sort";
		}
		return "TotalQuantity.jsp?faces-redirect=true";

	}

	public String sortByQuantityTotDes() {

		System.out.println("sort..");
		if (orderbyQuantityTotDes.length() == 4) {
			orderbyQuantityTotDes = "desc";

			orderbyMedIdTot = "sort";
			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyMedIdTotDes = "sort";

		} else if (orderbyQuantityTotDes.equals("asc")) {
			orderbyQuantityTotDes = "desc";

			orderbyMedIdTot = "sort";
			orderbyMedNameTot = "sort";
			orderbyTypeTot = "sort";
			orderbyQuantityTot = "sort";

			orderbyMedNameTotDes = "sort";
			orderbyTypeTotDes = "sort";
			orderbyMedIdTotDes = "sort";
		}

		return "TotalQuantity.jsp?faces-redirect=true";

	}

//------------------------------ Reset Update --------------------------------------

	public void clear2() throws IOException {
		System.out.println("Clear");

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);

		if (session != null) {
			// Remove specific attributes from the session map
			session.removeAttribute("quantity");
			session.removeAttribute("price");
			session.removeAttribute("mfgDate");
			session.removeAttribute("expiryDate");

			// Redirect to the current page
			externalContext.redirect(((HttpServletRequest) externalContext.getRequest()).getRequestURI());
		}
	}

}
