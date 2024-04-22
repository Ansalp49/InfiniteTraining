package com.java.ejb;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface HospitalBeanRemote {

	List<PatientMaster> showPatient();
	List<DoctorMaster> showDoctor();
	List<RoomMaster> showRoom();
}
