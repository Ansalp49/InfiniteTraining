package com.java.ejb;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class PrescriptionDAOImpl {

	SessionFactory sf;
	Session session;

	public List<Prescription> getListOfPres(String prescriptionId, int firstRow, int rowCount) {

		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Prescription.class);

		if (prescriptionId != null && !prescriptionId.isEmpty()) {
			System.out.println("Prescription List..");
			cr.add(Restrictions.eq("prescriptionId", prescriptionId));
			sessionMap.put("prescriptionId", prescriptionId);
			System.out.println("Prescription Id: " + prescriptionId);
			cr.setFirstResult(firstRow);
			cr.setMaxResults(rowCount);
		}

		if (orderbyprescription_Id.equals("asc")) {
			System.out.println("Order by Prescription Id " + orderbyprescription_Id);
			cr.addOrder(Order.asc("prescriptionId"));
		} else if (orderbyprescription_Id.equals("desc")) {
			cr.addOrder(Order.desc("prescriptionId"));
		}

		else if (orderbyproviderid.equals("asc")) {
			System.out.println("Order by Provider Id " + orderbyproviderid);
			cr.addOrder(Order.asc("providerid"));
		} else if (orderbyproviderid.equals("desc")) {
			cr.addOrder(Order.desc("providerid"));
		}

		else if (orderbyrecipentId.equals("asc")) {
			System.out.println("Order by Patient Id " + orderbyrecipentId);
			cr.addOrder(Order.asc("recipentId"));
		} else if (orderbyrecipentId.equals("desc")) {
			cr.addOrder(Order.desc("recipentId"));
		}

		else if (orderbyappointmentId.equals("asc")) {
			System.out.println("Order by Appointment Id " + orderbyappointmentId);
			cr.addOrder(Order.asc("appointmentId"));
		} else if (orderbyappointmentId.equals("desc")) {
			cr.addOrder(Order.desc("appointmentId"));
		}

		else if (orderbyprescribed_Date.equals("asc")) {
			System.out.println("Order by Date  " + orderbyprescribed_Date);
			cr.addOrder(Order.asc("prescribedDate"));
		} else if (orderbyprescribed_Date.equals("desc")) {
			cr.addOrder(Order.desc("prescribedDate"));
		}

		cr.setFirstResult(firstRow);
		cr.setMaxResults(rowCount);
		List<Prescription> cdList = cr.list();
		return cdList;
	}

	public int countRows(String prescriptionId) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Prescription.class);
			if (criteria != null) {
				if (prescriptionId.length() > 0) {
					criteria.add(Restrictions.eq("prescriptionId", prescriptionId));
					System.out.println(prescriptionId);
				}
				return criteria.list().size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	static String orderbyprescription_Id = "sort";
	static String orderbyproviderid = "sort";
	static String orderbyrecipentId = "sort";
	static String orderbyappointmentId = "sort";
	static String orderbyprescribed_Date = "sort";

	public String sortbyPriscriptionId() {
		;
		System.out.println("Orderbyprescription_Id : " + orderbyprescription_Id);
		if (orderbyprescription_Id.length() == 4) {
			orderbyprescription_Id = "asc";

			orderbyproviderid = "sort";
			orderbyrecipentId = "sort";
			orderbyappointmentId = "sort";
			orderbyprescribed_Date = "sort";
		} else if (orderbyprescription_Id.equals("asc")) {
			orderbyprescription_Id = "desc";

			orderbyproviderid = "sort";
			orderbyrecipentId = "sort";
			orderbyappointmentId = "sort";
			orderbyprescribed_Date = "sort";
		}

		System.out.println("test1 " + orderbyprescription_Id);
		return "ShowPrescription.jsp?faces-redirect=true";
	}

	public String sortbyProviderId() {
		System.out.println("orderbyproviderid : " + orderbyproviderid);
		if (orderbyproviderid.length() == 4) {
			orderbyproviderid = "asc";

			orderbyprescription_Id = "sort";
			orderbyrecipentId = "sort";
			orderbyappointmentId = "sort";
			orderbyprescribed_Date = "sort";
		} else if (orderbyproviderid.equals("asc")) {
			orderbyproviderid = "desc";

			orderbyprescription_Id = "sort";
			orderbyrecipentId = "sort";
			orderbyappointmentId = "sort";
			orderbyprescribed_Date = "sort";
		}

		System.out.println("test1 " + orderbyproviderid);
		return "ShowPrescription.jsp?faces-redirect=true";
	}

	public String sortbyPatientId() {
		System.out.println("orderbyrecipentId : " + orderbyrecipentId);
		if (orderbyrecipentId.length() == 4) {
			orderbyrecipentId = "asc";
			orderbyprescription_Id = "sort";
			orderbyproviderid = "sort";
			orderbyappointmentId = "sort";
			orderbyprescribed_Date = "sort";
		} else if (orderbyrecipentId.equals("asc")) {
			orderbyrecipentId = "desc";
			orderbyprescription_Id = "sort";
			orderbyproviderid = "sort";
			orderbyappointmentId = "sort";
			orderbyprescribed_Date = "sort";
		}

		System.out.println("test1 " + orderbyrecipentId);
		return "ShowPrescription.jsp?faces-redirect=true";
	}

	public String sortbyAppointmentId() {
		System.out.println("orderbyappointmentId : " + orderbyappointmentId);
		if (orderbyappointmentId.length() == 4) {
			orderbyappointmentId = "asc";
			orderbyprescription_Id = "sort";
			orderbyrecipentId = "sort";
			orderbyproviderid = "sort";
			orderbyprescribed_Date = "sort";
		} else if (orderbyappointmentId.equals("asc")) {
			orderbyappointmentId = "desc";
			orderbyprescription_Id = "sort";
			orderbyrecipentId = "sort";
			orderbyproviderid = "sort";
			orderbyprescribed_Date = "sort";
		}

		System.out.println("test1 " + orderbyappointmentId);
		return "ShowPrescription.jsp?faces-redirect=true";
	}

	public String sortbyDate() {
		System.out.println("orderbyprescribed_Date : " + orderbyprescribed_Date);
		if (orderbyprescribed_Date.length() == 4) {
			orderbyprescribed_Date = "asc";
			orderbyprescription_Id = "sort";
			orderbyrecipentId = "sort";
			orderbyproviderid = "sort";
			orderbyappointmentId = "sort";
		} else if (orderbyprescribed_Date.equals("asc")) {
			orderbyprescribed_Date = "desc";
			orderbyprescription_Id = "sort";
			orderbyrecipentId = "sort";
			orderbyproviderid = "sort";
			orderbyappointmentId = "sort";
		}

		System.out.println("test1 " + orderbyprescribed_Date);
		return "ShowPrescription.jsp?faces-redirect=true";
	}

}
