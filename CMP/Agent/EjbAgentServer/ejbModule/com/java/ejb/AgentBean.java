package com.java.ejb;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class AgentBean
 */
@Stateless
@Remote(AgentBeanRemote.class)
public class AgentBean implements AgentBeanRemote {

	@PersistenceContext(unitName = "AgMgmPU")
	private EntityManager entitymanager;

	/**
	 * Default constructor.
	 */
	public AgentBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Agent> showAgent() throws ClassNotFoundException, SQLException {
		System.out.println("Entity Manager is " + entitymanager);
		Query query = entitymanager.createQuery("Select a from Agent a");
		return (List<Agent>) query.getResultList();
	}

	@Override
	public Agent searchAgent(int agentId) throws ClassNotFoundException, SQLException {
		Agent agent = entitymanager.find(Agent.class, agentId);
		return agent;
	}

	@Override
	public String addAgent(Agent agent) throws ClassNotFoundException, SQLException {
		entitymanager.merge(agent);
		return "Agent inserted..";
	}

	@Override
	public String deleteAgent(int agentId) throws ClassNotFoundException, SQLException {
		Agent agFound = searchAgent(agentId);
		if (agFound != null) {
			entitymanager.remove(agFound);
			return "Agent record Deleted..";
		}
		return "Agent Not Found";
	}

	@Override
	public String updateAgent(Agent agentNew) throws ClassNotFoundException, SQLException {
		Agent agFound = searchAgent(agentNew.getAgentId());
		if (agFound != null) {
			entitymanager.merge(agentNew);
			return "Agent data updated..";
		}
		return "Agent Not Found..";
	}

}
