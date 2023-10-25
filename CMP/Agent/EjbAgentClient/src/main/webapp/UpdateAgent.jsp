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
		<h:form>
			<h2 style="text-align: center" class="col-sm-4">Update Records</h2>
			<hr />
	Agent Id:
		<h:inputText id="agentId" value="#{edit.agentId}" disabled="true" />
			<br />
			<br />
	Name:
		<h:inputText id="name" value="#{edit.name}" />
			<br />
			<br />
	City:
		<h:inputText id="city" value="#{edit.city}" />
			<br />
			<br />
	Gender:
		<h:selectOneRadio value="#{edit.gender}">
				<f:selectItem itemValue="MALE" itemLabel="Male" />
				<f:selectItem itemValue="FEMALE" itemLabel="Female" />
			</h:selectOneRadio>
			<br />
			<br />
	Marital Status:
		<h:selectOneRadio value="#{edit.maritalStatus}">
				<f:selectItem itemValue="1" itemLabel="YES" />
				<f:selectItem itemValue="0" itemLabel="NO" />
			</h:selectOneRadio>
			<br />
			<br />
	Premium:
		<h:inputText id="premium" value="#{edit.premium}" />
			<br />
			<br />
			<h:commandButton value="Update"
				action="#{agentImpl.updateAgentEjb(edit)}">
			</h:commandButton>
		</h:form>
	</center>
</body>
	</html>

</f:view>