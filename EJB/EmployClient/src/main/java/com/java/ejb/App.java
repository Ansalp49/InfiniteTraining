package com.java.ejb;

import java.util.List;

import javax.naming.NamingException;

public class App {

	public static void main(String[] args) {
		try {
			EmployImpl impl = new EmployImpl();

			List<Employ> showEmp = impl.showEmployEjb();
			for (Employ show : showEmp) {
				System.out.println(show);
			}
			try {
				Thread.sleep(100000000);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
