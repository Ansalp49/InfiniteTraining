package com.java.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class PharmaBean
 */
@Stateless
@Remote(PharmaBeanRemote.class)
public class PharmaBean implements PharmaBeanRemote {

	@PersistenceContext(name = "Pharmacy")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PharmaBean() {
	}

	@Override
	public String addMedicine(MedicineEntry entry) {
		entry.setEntryDate(new Date());
		System.out.println("em: " + entityManager);
		entityManager.persist(entry);
		return "Medicines Inserted..";
	}

	@Override
	public String addSaleHistory(MedicineSales sales) {
		Query query = entityManager.createQuery("SELECT MAX(s.saleId) FROM MedicineSales s");
		List<String> resultList = query.getResultList();
		String saleId = resultList.get(0);

		if (saleId == null) {
			saleId = "S001";
		} else {
			int num = Integer.parseInt(saleId.substring(1, 4));
			saleId = String.format("S%03d", num + 1);
		}

		sales.setSaleId(saleId);
		sales.setSaleDate(new Date());

		entityManager.persist(sales);
		return "Sales History Recorded..";
	}

}
