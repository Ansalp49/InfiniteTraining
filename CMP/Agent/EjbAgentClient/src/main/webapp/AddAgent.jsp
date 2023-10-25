<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<center>
		<h2>
			<h:outputText value="Add Agent" />
		</h2>
		<h:form>
	Name:
	<h:inputText value="#{agent.name}"></h:inputText>
			<br />
			<br />
	City:
	<h:inputText value="#{agent.city}"></h:inputText>
			<br />
			<br />
	Gender:
	<h:selectOneRadio value="#{agent.gender}">
				<f:selectItem itemValue="MALE" itemLabel="Male" />
				<f:selectItem itemValue="FEMALE" itemLabel="Female" />
			</h:selectOneRadio>
			<br />
			<br />
	Marital Status:
	<h:selectOneRadio value="#{agent.maritalStatus}">
				<f:selectItem itemValue="1" itemLabel="YES" />
				<f:selectItem itemValue="0" itemLabel="NO" />
			</h:selectOneRadio>
			<br />
			<br />
	Premium:
	<h:inputText value="#{agent.premium}"></h:inputText>
			<br />
			<br />
			<h:commandButton action="#{agentImpl.addAgentEjb(agent)}" value="Add"></h:commandButton>
		</h:form>
	</center>
</body>
	</html>

</f:view>