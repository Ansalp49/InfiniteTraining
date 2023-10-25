package com.java.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class EmployBean
 */
@Stateless
@Remote(EmployBeanRemote.class)
public class EmployBean implements EmployBeanRemote {

	@PersistenceContext(unitName = "EmpMgmtPU")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public EmployBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employ> showEmploy() {
		System.out.println("Entity Manager is " + entityManager);
		Query query = entityManager.createQuery("Select e from Employ e");
		return (List<Employ>) query.getResultList();
	}

	@Override
	public Employ searchEmploy(int empno) {
		Employ employee = entityManager.find(Employ.class, empno);
		return employee;
	}

	@Override
	public String addEmploy(Employ employ) {
		entityManager.merge(employ);
		return "Employ Record Inserted...";
	}

	@Override
	public String updateEmploy(Employ empNew) {
		Employ empFound = searchEmploy(empNew.getEmpno());
		if (empFound != null) {
			entityManager.merge(empNew);
			return "Employ Record Updated..";
		}
		return "Not Found...";
	}

	@Override
	public String deleteEmploy(int empno) {
		Employ empFound = searchEmploy(empno);
		if (empFound != null) {
			entityManager.remove(empFound);
			return "Employ Record Deleted..";
		}
		return "Not Found...";
	}
}
