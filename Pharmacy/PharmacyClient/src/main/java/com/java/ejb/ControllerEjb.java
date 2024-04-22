package com.java.ejb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ControllerEjb {

	private PharmacyImpl impl;
	private MedicineEntry entries;
	private MedicineSales sale;
	private Prescription pres;
	private JsfPaginationBean pageimpl;
	private boolean saleSuccessful;

	public JsfPaginationBean getPageimpl() {
		return pageimpl;
	}

	public void setPageimpl(JsfPaginationBean pageimpl) {
		this.pageimpl = pageimpl;
	}

	public Prescription getPres() {
		return pres;
	}

	public void setPres(Prescription pres) {
		this.pres = pres;
	}

	public boolean isSaleSuccessful() {
		return saleSuccessful;
	}

	public void setSaleSuccessful(boolean saleSuccessful) {
		this.saleSuccessful = saleSuccessful;
	}

	public PharmacyImpl getImpl() {
		return impl;
	}

	public void setImpl(PharmacyImpl impl) {
		this.impl = impl;
	}

	public MedicineEntry getEntries() {
		return entries;
	}

	public void setEntries(MedicineEntry entries) {
		this.entries = entries;
	}

	public MedicineSales getSale() {
		return sale;
	}

	public void setSale(MedicineSales sale) {
		this.sale = sale;
	}

	public List<Prescription> validatePres(String prescriptionId) {
		System.out.println("Prescription Controller hitted...");
		if (presValid(prescriptionId)) {
			return pageimpl.getPresList(prescriptionId);
		}
		return null;
	}

	public String addMedicine(MedicineEntry entries) throws NamingException {
		System.out.println("Hitted Entry Controller....");
		if (medValid(entries)) {
			return impl.addMedicineEjb(entries);
		}
		return "";
	}

	public String addEquip(MedicineEntry entries) throws NamingException {
		System.out.println("Hitted EquipEntry Controller....");
		if (equipValid(entries)) {
			return impl.addMedicineEjb(entries);
		}
		return "";
	}

	public String addSaleRecord(List<MedicineEntry> sale) throws NamingException {
		System.out.println("Hitted Sale Controller...");
		if (saleValid(sale)) {
			return impl.addSaleHistoryEjb(sale);
		}
		return "failureOutcome";
	}

	public boolean presValid(String presId) {
		System.out.println("Prescription Controller Hitted");
		boolean flag = true;
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		if (presId.trim().isEmpty()) {
			System.out.println("err in pres");
			sessionMap.put("presId", "*Please enter Prescription Id. e.g. PS001");
			flag = false;
		} else if (!presId.matches("(?i)^ps.{3}$")) {
			sessionMap.put("presId", "*Invalid Id. e.g. PS001");
			return false;
		} else {
			if (noRecordsFound(presId)) {
				sessionMap.put("presId", "*No records found for the Prescription Id.");
				flag = false;
			} else {
				sessionMap.put("presId", "");
			}
		}
		return flag;
	}

	public boolean noRecordsFound(String prescriptionId) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();

		Criteria criteria = session.createCriteria(Prescription.class);
		criteria.add(Restrictions.eq("prescriptionId", prescriptionId));
		criteria.setProjection(Projections.rowCount());

		Long count = (Long) criteria.uniqueResult();
		return count == null || count == 0;
	}

	public boolean medValid(MedicineEntry entries) {
		FacesContext context = FacesContext.getCurrentInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String medIdRegex = "^M.{3}$";
		String medNameRegex = "^.+$";
		Date today = new Date();
		System.out.println(entries.getMedId());
		boolean flag = true;

		if (entries.getMedId().trim().length() == 0) {
			System.out.println("err");
			context.addMessage("form:medId", new FacesMessage("*Please enter Id."));
			flag = false;
		} else {
			if (!Pattern.matches(medIdRegex, entries.getMedId())) {
				context.addMessage("form:medId", new FacesMessage("*Invalid Id format."));
				flag = false;
			}
		}

		if (!Pattern.matches(medNameRegex, entries.getMedName())) {
			context.addMessage("form:medName", new FacesMessage("*Please enter Medicine Name."));
			flag = false;
		}

		if (isMedIdAlreadyExists(entries.getMedId())) {
			context.addMessage("form:medId", new FacesMessage("*MedId already exists."));
			flag = false;
		}

		if (isMedNameAlreadyExists(entries.getMedName())) {
			context.addMessage("form:medName", new FacesMessage("*MedName already exists."));
			flag = false;
		}

		Date mfgDate = entries.getMfgDate();
		Date expDate = entries.getExpiryDate();

		if (mfgDate == null) {
			context.addMessage("form:mfgDate", new FacesMessage("*Please enter Mfg Date."));
			flag = false;
		}

		if (mfgDate == null || mfgDate.after(today)) {
			context.addMessage("form:mfgDate", new FacesMessage("*MfgDate cannot be in the future."));
			flag = false;
		}

		if (expDate == null) {
			context.addMessage("form:expiryDate", new FacesMessage("*Please enter Expiry Date."));
			flag = false;
		}

		if (expDate == null || expDate.before(mfgDate) || expDate.equals(today)) {
			context.addMessage("form:expiryDate", new FacesMessage("*ExpDate cannot be before MfgDate or today."));
			flag = false;
		}

		if (entries.getPrice() <= 0) {
			context.addMessage("form:price", new FacesMessage("*Invalid Price amount."));
			flag = false;
		}

		if (entries.getQuantity() < 5) {
			context.addMessage("form:quantity", new FacesMessage("*Quantity must be at least 5."));
			flag = false;
		}

		Calendar oneMonthAhead = Calendar.getInstance();
		oneMonthAhead.add(Calendar.MONTH, 1);

		if (expDate == null || expDate.before(oneMonthAhead.getTime())) {
			context.addMessage("form:expiryDate",
					new FacesMessage("*ExpDate must be at least 1 month ahead for sale."));
			flag = false;
		}

		return flag;
	}

	public boolean equipValid(MedicineEntry entries) {
		FacesContext context = FacesContext.getCurrentInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String medIdRegex = "^E.{3}$";
		String medNameRegex = "^.+$";
		Date today = new Date();
		System.out.println(entries.getMedId());
		boolean flag = true;

		if (entries.getMedId().trim().length() == 0) {
			System.out.println("equipErr");
			context.addMessage("form:medId", new FacesMessage("*Please enter Id."));
			flag = false;
		} else {
			if (!Pattern.matches(medIdRegex, entries.getMedId())) {
				context.addMessage("form:medId", new FacesMessage("*Invalid Id format."));
				flag = false;
			}
		}

		if (!Pattern.matches(medNameRegex, entries.getMedName())) {
			context.addMessage("form:medName", new FacesMessage("*Please enter Name."));
			flag = false;
		}

		if (isMedIdAlreadyExists(entries.getMedId())) {
			context.addMessage("form:medId", new FacesMessage("*Id already exists."));
			flag = false;
		}

		if (isMedNameAlreadyExists(entries.getMedName())) {
			context.addMessage("form:medName", new FacesMessage("*Name already exists."));
			flag = false;
		}

		Date mfgDate = entries.getMfgDate();

		if (mfgDate == null) {
			context.addMessage("form:mfgDate", new FacesMessage("*Please enter Mfg Date."));
			flag = false;
		}

		if (mfgDate == null || mfgDate.after(today)) {
			context.addMessage("form:mfgDate", new FacesMessage("*MfgDate cannot be in the future."));
			flag = false;
		}

		if (entries.getPrice() <= 0) {
			context.addMessage("form:price", new FacesMessage("*Invalid Price amount."));
			flag = false;
		}

		if (entries.getQuantity() < 5) {
			context.addMessage("form:quantity", new FacesMessage("*Quantity must be at least 5."));
			flag = false;
		}

		return flag;
	}

	public boolean saleValid(List<MedicineEntry> sale) {
		boolean flag = true;
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		if (!sessionMap.containsKey("entryId")) {
			System.out.println("EntryId not found in sessionMap");
			flag = false;
			return flag;
		}

		for (MedicineEntry medEntry : sale) {
			if (medEntry.isExpired() || medEntry.isExpiryWithinOneMonth() || medEntry.isQuantityZero(0)) {
			} else {
				if (medEntry.getSaleQuantity() <= 0) {
					System.out.println("Please enter a valid quantity for each medicine.");
					sessionMap.put("quantityerror", "*Please enter quantity.");
					flag = false;
					break;
				}
			}

			if (medEntry.getSaleQuantity() > medEntry.getQuantity()) {
				System.out.println("Sale Quantity: " + medEntry.getSaleQuantity());
				System.out.println("Available Quantity: " + medEntry.getQuantity());
				sessionMap.put("quantityerror", medEntry.getMedName() + "*Required Quantity is not available.");
				flag = false;
				break;
			}
		}

		String prescriptionId = (String) sessionMap.get("prescriptionId");
		if (isPrescriptionSold(prescriptionId)) {
			sessionMap.put("prescriptionSoldError", "*Prescription already sold.");
			flag = false;
		}

		return flag;
	}

	public MedicineEntry searchMedicine(int entryId) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(MedicineEntry.class);
		cr.add(Restrictions.eqOrIsNull("entryId", entryId));
		MedicineEntry quan = (MedicineEntry) cr.uniqueResult();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("entryId", entryId);
		System.out.println("entry2:" + entryId);
		return quan;
	}

	public boolean isMedIdAlreadyExists(String medId) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(MedicineEntry.class);
		criteria.add(Restrictions.eq("medId", medId));
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count != null && count > 0;
	}

	public boolean isMedNameAlreadyExists(String medName) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(MedicineEntry.class);
		criteria.add(Restrictions.eq("medName", medName));
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count != null && count > 0;
	}

	public boolean isPrescriptionSold(String prescriptionId) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(MedicineSales.class);
		criteria.add(Restrictions.eq("prescriptionId", prescriptionId));
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count != null && count > 0;
	}

	public boolean isQuantityZero(int quantity) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(MedicineEntry.class);
		criteria.add(Restrictions.eq("quantity", 0));
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count != null && count > 0;
	}
	
	public String back() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.remove("prescriptionId");
		return "ShowPrescription.jsp?faces-redirect=true";
	}
}
