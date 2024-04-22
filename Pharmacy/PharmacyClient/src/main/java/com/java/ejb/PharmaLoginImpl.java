package com.java.ejb;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class PharmaLoginImpl implements PharmaLoginDAO {

	SessionFactory sf;
	Session session;

	@Override
	public String validateOtp(PharmacyLogin pharma) {
		sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(PharmacyLogin.class);
		cr.add(Restrictions.eq("email", pharma.getEmail()));
		cr.add(Restrictions.eq("otp", pharma.getOtp()));
		cr.setProjection(Projections.rowCount());
		long count = (long) cr.uniqueResult();
		if (count == 1) {
			return "DashBoard.jsp?faces-redirect=true";

		} else {

			Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessionMap.put("passWordErr", "Enter a valid password and try again.");
			return "PharmaValidate.jsp?faces-redirect=true";
		}
	}

	@Override
	public String loginDao(PharmacyLogin pharma) {
		sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(PharmacyLogin.class);
		cr.add(Restrictions.eq("email", pharma.getEmail()));
		cr.add(Restrictions.eq("password", pharma.getPassword()));
		cr.setProjection(Projections.rowCount());
		long count = (long) cr.uniqueResult();

		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (count == 1) {
			Criteria pharmIdCriteria = session.createCriteria(PharmacyLogin.class);
			pharmIdCriteria.add(Restrictions.eqOrIsNull("email", pharma.getEmail()));
			pharmIdCriteria.setProjection(Projections.property("pharmId"));
			String pharmId = (String) pharmIdCriteria.uniqueResult();

			if (pharmId != null) {
				Criteria enrollCriteria = session.createCriteria(PharmacyEnroll.class);
				enrollCriteria.add(Restrictions.eq("pharmacyId", pharmId));
				PharmacyEnroll enroll = (PharmacyEnroll) enrollCriteria.uniqueResult();

				sessionMap.put("pharmalogged", pharma.getEmail());
				sessionMap.put("pharmacylogin", enroll);
				sessionMap.put("pharmId", pharmId);
				sessionMap.put("PharmaName", enroll.getShopName());

				return "DashBoard.jsp?faces-redirect=true";
			}
		}

		else {
			sessionMap.put("passWordErr", "Enter a valid password and try again.");
			return "PhrmaLogin.jsp?faces-redirect=true";
		}
		return null;
	}

}
