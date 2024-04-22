package com.java.ejb;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class Dummy {
	public static RecipientDetails searchRecipient(String recipientId) {
		SessionFactory sf;
		Session session;
		 sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria cr = session.createCriteria(RecipientDetails.class);
		cr.add(Restrictions.eq("recipientId", recipientId));
		RecipientDetails recipient = (RecipientDetails) cr.uniqueResult();
		return recipient;
		
	}
	public static void main(String[] args) {
//		searchRecipient("IN0002");
       PharmacyDao dao = new PharmacyImpl();
       System.out.println(dao.searchRecipient("IN0002"));
	}

}
