package com.java.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class HospitalBean
 */
@Stateless
@Remote(HospitalBeanRemote.class)
public class HospitalBean implements HospitalBeanRemote {

	@PersistenceContext(name = "HmsPU")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public HospitalBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PatientMaster> showPatient() {
		System.out.println("Entity Manager is " + entityManager);
		Query query = entityManager.createQuery("Select p from PatientMaster p");
		return (List<PatientMaster>) query.getResultList();
	}

	@Override
	public List<DoctorMaster> showDoctor() {
		System.out.println("Entity Manager is " + entityManager);
		Query query = entityManager.createQuery("Select d from DoctorMaster d");
		return (List<DoctorMaster>) query.getResultList();
	}

	@Override
	public List<RoomMaster> showRoom() {
		System.out.println("Entity Manager is " + entityManager);
		Query query = entityManager.createQuery("Select r from RoomMaster r");
		return (List<RoomMaster>) query.getResultList();
	}

}
