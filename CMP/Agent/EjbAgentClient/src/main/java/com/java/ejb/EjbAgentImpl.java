package com.java.ejb;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;

public class EjbAgentImpl {

	public List<Agent> showAgentEjb() throws NamingException, ClassNotFoundException, SQLException{
		AgentBeanRemote remote=RemoteHelper.lookupRemoteStatelessEmploy();
		return remote.showAgent();
	}
	public String searchAgentEjb(int agentId) throws NamingException, ClassNotFoundException, SQLException {
		AgentBeanRemote remote=RemoteHelper.lookupRemoteStatelessEmploy();
		Agent agent=remote.searchAgent(agentId);
		Map<String, Object> sessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("edit", agent);
		return "UpdateAgent.jsp?faces-redirect=true";
	}
	public String deleteAgentEjb(int agentId) throws NamingException, ClassNotFoundException, SQLException {
		AgentBeanRemote remote=RemoteHelper.lookupRemoteStatelessEmploy();
		return remote.deleteAgent(agentId);
	}
	public String addAgentEjb(Agent agent) throws ClassNotFoundException, SQLException, NamingException {
		AgentBeanRemote remote=RemoteHelper.lookupRemoteStatelessEmploy();
		remote.addAgent(agent);
		return "ShowAgent.jsp?faces-redirect=true";
	}
	public String updateAgentEjb(Agent agent) throws ClassNotFoundException, SQLException, NamingException {
		AgentBeanRemote remote=RemoteHelper.lookupRemoteStatelessEmploy();
		remote.updateAgent(agent);
		return "ShowAgent.jsp?faces-redirect=true";
	}
}
