package com.java.ejb;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;

public class Ejbimpl {

	public List<Employ> showEmployEjb() throws NamingException{
		EmployBeanRemote remote=RemoteHelper.lookupRemoteStatelessEmploy();
		return remote.showEmploy();
	}
	
	public String searchEmployEjb(int empno) throws NamingException, ClassNotFoundException, SQLException {
		EmployBeanRemote remote = RemoteHelper.lookupRemoteStatelessEmploy();
		Employ employ=remote.searchEmploy(empno);
		Map<String, Object> sessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("editEmploy", employ);
		return "UpdateEmploy.jsp?faces-redirect=true";
	}
	public String addEmployEjb(Employ employ) throws NamingException, ClassNotFoundException, SQLException {
		EmployBeanRemote remote = RemoteHelper.lookupRemoteStatelessEmploy();
		remote.addEmploy(employ);
		return "ShowEmploy.jsp?faces-redirect=true";
	}
	public String updateEmployEjb(Employ empNew) throws NamingException, ClassNotFoundException, SQLException {
		EmployBeanRemote remote = RemoteHelper.lookupRemoteStatelessEmploy();
		remote.updateEmploy(empNew);
		return "ShowEmploy.jsp?faces-redirect=true";
	}
	public String deleteEmployEjb(int empno) throws NamingException, ClassNotFoundException, SQLException {
		EmployBeanRemote remote = RemoteHelper.lookupRemoteStatelessEmploy();
		remote.deleteEmploy(empno);
		return "ShowEmploy.jsp?faces-redirect=true";
	}
}
