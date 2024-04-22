package com.java.ejb;

import java.sql.SQLException;
import java.util.List;

public interface PatientDao {

	String addPatientDao(PatientMaster patientmaster) throws ClassNotFoundException, SQLException;
//	List<PatientMaster> showPatientDao() throws ClassNotFoundException, SQLException;
	PatientMaster searchPatientDao(String pId) throws ClassNotFoundException, SQLException;
}
