package com.java.ejb;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class HospitalImpl implements PatientDao{

	
	SessionFactory sf;
	Session ses;
	PatientMaster patient;
	
	public static int generateOtp() {
		Random r = new Random(System.currentTimeMillis() );
	    return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}
	
	@Override
	public String addPatientDao(PatientMaster patientmaster) throws ClassNotFoundException, SQLException {
		String pwd = EncryptPassWord.getCode(patientmaster.getPassword());
		patientmaster.setPassword(pwd);
		sf=SessionHelper.getConnection();
		ses=sf.openSession();
		Transaction trans= ses.beginTransaction();
		ses.save(patientmaster);
		trans.commit();
		String body = "Welcome to Mr/Miss  " + patientmaster.getName() + "Your Account Created Successfully...";
		MailSend.mailOtp(patientmaster.getEmail(), "Account Created Succesfully...", body);
		return "ShowPatient.jsp?faces-redirect=true";
	}
	
	
	public String validateOtp(String user, int otpCode) {
		sf=SessionHelper.getConnection();
		ses=sf.openSession();
	    Transaction tx = null;
	    try {
	        tx = ses.beginTransaction();
	        Criteria criteria = ses.createCriteria(PatientMaster.class);
	        criteria.add(Restrictions.eq("email", user));
	        criteria.add(Restrictions.eq("otp", otpCode));
	        List<PatientMaster> otpList = criteria.list();

	        if (otpList.size() == 1) {
	            PatientMaster patientOtp = searchPatientDao(user);
	            patient.setStatus("ACTIVE");
	            ses.update(patient);
	            tx.commit();
	            MailSend.mailOtp(patient.getEmail(), "Otp Verified Successfully...", "You Can Use the Account Now...");
	            return "OTP Verified Account Active...";
	        } else {
	            tx.rollback();
	            patient = searchPatientDao(user);
	            MailSend.mailOtp(patient.getEmail(), "Otp is wrong...", "Please Provide Proper Details...");
	            return "Invalid Otp or UserName details provided...";
	        }
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	        return "Error occurred during OTP validation.";
	    } finally {
	        ses.close();
	    }
	}



//	@Override
//	public List<PatientMaster> showPatientDao() throws ClassNotFoundException, SQLException {
//		
//	}
//
//
//
	@Override
	public PatientMaster searchPatientDao(String pId) throws ClassNotFoundException, SQLException {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		String username = (String) sessionMap.get("email");
		sf = SessionHelper.getConnection();
		ses = sf.openSession();
		Criteria criteria = ses.createCriteria(PatientMaster.class);

		criteria.add(Restrictions.eq("email", username));
		PatientMaster pData = (PatientMaster) criteria.uniqueResult();
		sessionMap.put("pId", pData.getpId());
		return patient;
	}
	
	public List<PatientMaster> showPatientEjb() throws NamingException{
		HospitalBeanRemote remote=RemoteHelper.lookupRemoteStatelessEmploy();
		return remote.showPatient();
	}
	
	public List<DoctorMaster> showDoctorEjb() throws NamingException{
		HospitalBeanRemote remote=RemoteHelper.lookupRemoteStatelessEmploy();
		return remote.showDoctor();
	}
	
	public List<RoomMaster> showRoomEjb() throws NamingException{
		HospitalBeanRemote remote=RemoteHelper.lookupRemoteStatelessEmploy();
		return remote.showRoom();
	}
	
//	public String searchEmployEjb(int empno) throws NamingException, ClassNotFoundException, SQLException {
//		EmployBeanRemote remote = RemoteHelper.lookupRemoteStatelessEmploy();
//		Employ employ=remote.searchEmploy(empno);
//		Map<String, Object> sessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//		sessionMap.put("editEmploy", employ);
//		return "UpdateEmploy.jsp?faces-redirect=true";
//	}
//	public String addEmployEjb(Employ employ) throws NamingException, ClassNotFoundException, SQLException {
//		EmployBeanRemote remote = RemoteHelper.lookupRemoteStatelessEmploy();
//		remote.addEmploy(employ);
//		return "ShowEmploy.jsp?faces-redirect=true";
//	}
//	public String updateEmployEjb(Employ empNew) throws NamingException, ClassNotFoundException, SQLException {
//		EmployBeanRemote remote = RemoteHelper.lookupRemoteStatelessEmploy();
//		remote.updateEmploy(empNew);
//		return "ShowEmploy.jsp?faces-redirect=true";
//	}
//	public String deleteEmployEjb(int empno) throws NamingException, ClassNotFoundException, SQLException {
//		EmployBeanRemote remote = RemoteHelper.lookupRemoteStatelessEmploy();
//		remote.deleteEmploy(empno);
//		return "ShowEmploy.jsp?faces-redirect=true";
//	}
}
