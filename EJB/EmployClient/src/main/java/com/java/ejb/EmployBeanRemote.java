package com.java.ejb;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EmployBeanRemote {

	List<Employ> showEmploy();
	Employ searchEmploy(int empno);
	String addEmploy(Employ employ);
	String updateEmploy(Employ empNew);
	String deleteEmploy(int empno);
}
