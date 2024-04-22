package com.java.ejb;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


public class MedicinesController {
	
	private  MedicineEntry medicines =new MedicineEntry();
	private MainImpl impl=new MainImpl();
	
	public MedicineEntry getMedicines() {
		return medicines;
	}
	public void setMedicines(MedicineEntry medicines) {
		this.medicines = medicines;
	}
	public MainImpl getImpl() {
		return impl;
	}
	public void setImpl(MainImpl impl) {
		this.impl = impl;
	}
	
	JsfPagination bean = new JsfPagination();
	
	public JsfPagination getBean() {
		return bean;
	}
	public void setBean(JsfPagination bean) {
		this.bean = bean;
	}

	TotalPagination totbean = new TotalPagination();
	
	public TotalPagination getTotbean() {
		return totbean;
	}
	
	public void setTotbean(TotalPagination totbean) {
		this.totbean = totbean;
	}
	
	public List<MedList> addSearchTot( String medName){
		if(addSearch1(medName)) {
			return totbean.getMedicinesTotalList(medName);		
		}
		return null;
	}
	
	public boolean addSearch1( String medName) {
	FacesContext context = FacesContext.getCurrentInstance();
	 boolean flag=true;
	 
	 if ( medName == null || medName.trim().isEmpty()) {
			context.addMessage("medform:medName", new FacesMessage("PLEASE ENTER ATLEAST ONE PARAMETER."));
			flag = false;
		}
	 return flag;
}
	
	
	
	public List<MedicineEntry> addSearchh( String medName) {
		if(addSearch(medName)) {
			return bean.getMedicinesList(medName);		
		}
		return null;
	}
	
	public boolean addSearch( String medName) {
	FacesContext context = FacesContext.getCurrentInstance();
	 boolean flag=true;
	 
	 if ( medName == null || medName.trim().isEmpty()) {
			context.addMessage("medform:medName", new FacesMessage("PLEASE ENTER ATLEAST ONE PARAMETER."));
			flag = false;
		}
	 return flag;
}
	
	
	
	public String addMedicines( String medId, String medName, String type, int quantity, Date mfgDate, Date expiryDate, double price) throws ParseException  {
		if(addValid(medId, medName, type, quantity, mfgDate, expiryDate, price)) {
			return impl.Updatemedicine(medId, medName, type, quantity, mfgDate, expiryDate, price);		
		}
		return "";
	}
	
	
	
	public boolean addValid(String medId, String medName, String type, int quantity, Date mfgDate, Date expiryDate, double price)
	{
		System.out.println("Medicine is  " +medicines);
	FacesContext context = FacesContext.getCurrentInstance();
	 
		 boolean flag=true;		 
		 
			
			Date today = new Date();
			
//		 	mfgDate cannot be Null
			if (mfgDate == null) {
				context.addMessage("form:mfgDate", new FacesMessage("Please Enter Manufacture Date."));
				flag = false;
			}
			
			
			if (mfgDate == null || mfgDate.after(today) || isToday(mfgDate)) {
			    context.addMessage("form:mfgDate", new FacesMessage("MfgDate cannot be in the Future or Today."));
			    flag = false;
			}

			
			if (mfgDateAlreadyExists(mfgDate)) {
				context.addMessage("form:mfgDate", new FacesMessage("Value Already Exists"));
				flag = false;
			}

	 
			if (expiryDate == null) {
				context.addMessage("form:expiryDate", new FacesMessage("Please Enter Expiry Date."));
				flag = false;
			}
			
			
			if (expiryDateAlreadyExists(expiryDate)) {
				context.addMessage("form:expiryDate", new FacesMessage("Value Already Exists"));
				flag = false;
			}
			
			// Additional Validation: Check if the expire date is within 1 month
			Calendar oneMonth = Calendar.getInstance();
			oneMonth.add(Calendar.MONTH, 1);

			if (expiryDate == null || expiryDate.before(oneMonth.getTime())) {
				context.addMessage("form:expiryDate", new FacesMessage("ExpiryDate must be at least 1 Month to Today's Date"));
				flag = false;
			}			
			
		
		// 	Quantity cannot be Null
		if(quantity == 0) {
			 context.addMessage("form:quantity",new FacesMessage("Please Enter Quantity in Integer"));
			 flag= false;
		}
		
		if(quantity < 5) {
			 context.addMessage("form:quantity",new FacesMessage("Quantity must be at least 5."));
			 flag= false;
		}
				
//	 	Price cannot be Null
			if(price == 0) {
				 context.addMessage("form:price",new FacesMessage("Please Enter the Price in Numeric"));
				 flag= false;
			}

		 return flag;
			
		
}

	public String addEquipment(String medId, String medName, String type, int quantity, Date mfgDate, Date expiryDate, double price) throws ParseException {
		
		if(addEquip(medId, medName, type, quantity, mfgDate,expiryDate, price)) {
			System.err.println("Equipment method");
			return impl.Updatemedicine(medId, medName, type, quantity, mfgDate, expiryDate, price);
			
		}
		return "";
	}
	public boolean addEquip(String medId, String medName, String type, int quantity, Date mfgDate, Date expiryDate, double price)
	{
		System.out.println("Medicine is  " +medicines);
	FacesContext context = FacesContext.getCurrentInstance();


		 boolean flag=true;
		 
		Date today = new Date();
		
		 if (mfgDate == null) {
				context.addMessage("form1:mfgDate", new FacesMessage("Please Enter Manufacture Date."));
				flag = false;
			}
	 
			if (mfgDate == null || mfgDate.after(today) || isToday(mfgDate)) {
				context.addMessage("form1:mfgDate", new FacesMessage("MfgDate cannot be in the Future or Today."));
				flag = false;
			}
			
			
			if (mfgDateAlreadyExists(mfgDate)) {
				context.addMessage("form1:mfgDate", new FacesMessage("Value Already Exists"));
				flag = false;
			}
			
			// 	Quantity cannot be Null
			if(quantity == 0) {
				 context.addMessage("form1:quantity",new FacesMessage("Please Enter Quantity in Integer"));
				 flag= false;
			}
			
			if(quantity < 5) {
				 context.addMessage("form1:quantity",new FacesMessage("Quantity must be at least 5."));
				 flag= false;
			}
			
					
//		 	Price cannot be Null
				if(price == 0) {
					 context.addMessage("form1:price",new FacesMessage("Please Enter the Price in Numeric"));
					 flag= false;
				}

			 return flag;
				
			
	}
	
	public boolean isToday(Date mfgDate) {
	    Calendar calendarDate = Calendar.getInstance();
	    calendarDate.setTime(mfgDate);

	    Calendar todayCalendar = Calendar.getInstance();

	    return todayCalendar.get(Calendar.YEAR) == calendarDate.get(Calendar.YEAR) &&
	           todayCalendar.get(Calendar.MONTH) == calendarDate.get(Calendar.MONTH) &&
	           todayCalendar.get(Calendar.DAY_OF_MONTH) == calendarDate.get(Calendar.DAY_OF_MONTH);
	}
	
	public boolean mfgDateAlreadyExists( Date mfgDate) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(MedicineEntry.class);
	
		criteria.add(Restrictions.eq("mfgDate", mfgDate));
			
		criteria.setProjection(Projections.rowCount());

		Long count = (Long) criteria.uniqueResult();
		return count != null && count > 0;
	}
	
	
	public boolean expiryDateAlreadyExists( Date expiryDate) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(MedicineEntry.class);

		criteria.add(Restrictions.eq("expiryDate", expiryDate));
		
		criteria.setProjection(Projections.rowCount());

		Long count = (Long) criteria.uniqueResult();
		return count != null && count > 0;
	}
	
	//-------------------------------------------------------------------------------------------
	

		public String addMed( String medId, String medName, String type, int quantity, Date mfgDate, Date expiryDate, double price) throws ParseException  {
			if(medValid(medId, medName, type, quantity, mfgDate, expiryDate, price)) {
				return impl.Updatemedicine(medId, medName, type, quantity, mfgDate, expiryDate, price);		
			}
			return "";
		}
		
		

		public boolean medValid( String medId, String medName, String type, int quantity, Date mfgDate, Date expiryDate, double price) {
			FacesContext context = FacesContext.getCurrentInstance();
			

		//	String medIdRegex = ".{4}$";
		//	String medNameRegex = "^.+$";
			Date today = new Date();
	
			boolean flag = true;

			if (medId == null) {
				System.out.println("err");
				context.addMessage("form:medId", new FacesMessage("Please enter Id."));
				flag = false;
			}

			

//			if (!Pattern.matches(medIdRegex, medId)) {
//				context.addMessage("form:medId", new FacesMessage("Invalid Id format."));
//				flag = false;
//			}
			
//			if (!Pattern.matches(medNameRegex, medName)) {
//				context.addMessage("form:medName", new FacesMessage("Please enter Medicine Name."));
//				flag = false;
//			}

			// Check if medId already exists in the database
			if (isMedIdAlreadyExists(medId)) {
				context.addMessage("form:medId", new FacesMessage("MedId already exists."));
				flag = false;
			}

			// Check if medName already exists in the database
			if (isMedNameAlreadyExists(medName)) {
				context.addMessage("form:medName", new FacesMessage("MedName already exists."));
				flag = false;
			}


			
//		 	mfgDate cannot be Null
			if (mfgDate == null) {
				context.addMessage("form:mfgDate", new FacesMessage("Please Enter Manufacture Date."));
				flag = false;
			}
			
			
			if (mfgDate == null || mfgDate.after(today) || isToday(mfgDate)) {
			    context.addMessage("form:mfgDate", new FacesMessage("MfgDate cannot be in the Future or Today."));
			    flag = false;
			}

			
			if (mfgDateAlreadyExists(mfgDate)) {
				context.addMessage("form:mfgDate", new FacesMessage("Value Already Exists"));
				flag = false;
			}

	 
			if (expiryDate == null) {
				context.addMessage("form:expiryDate", new FacesMessage("Please Enter Expiry Date."));
				flag = false;
			}
			
			
			if (expiryDateAlreadyExists(expiryDate)) {
				context.addMessage("form:expiryDate", new FacesMessage("Value Already Exists"));
				flag = false;
			}
			
			// Additional Validation: Check if the expire date is within 1 month
			Calendar oneMonth = Calendar.getInstance();
			oneMonth.add(Calendar.MONTH, 1);

			if (expiryDate == null || expiryDate.before(oneMonth.getTime())) {
				context.addMessage("form:expiryDate", new FacesMessage("ExpiryDate must be at least 1 Month to Today's Date"));
				flag = false;
			}			
			
		
		// 	Quantity cannot be Null
		if(quantity == 0) {
			 context.addMessage("form:quantity",new FacesMessage("Please Enter Quantity in Integer"));
			 flag= false;
		}
		
		if(quantity < 5) {
			 context.addMessage("form:quantity",new FacesMessage("Quantity must be at least 5."));
			 flag= false;
		}
				
//	 	Price cannot be Null
			if(price == 0) {
				 context.addMessage("form:price",new FacesMessage("Please Enter the Price in Numeric"));
				 flag= false;
			}

			return flag;
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
}
