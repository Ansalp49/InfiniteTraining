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
			<h:outputText value="Add Patient Details" />
		</h2>
		<h:form>
	Patient Id:
	<h:inputText value="#{patient.pId}"></h:inputText>
			<br />
			<br />
	Name:
	<h:inputText value="#{patient.name}"></h:inputText>
			<br />
			<br />
			Age:
	<h:inputText value="#{patient.age}"></h:inputText>
			<br />
			<br />
				Weight:
	<h:inputText value="#{patient.weight}"></h:inputText>
			<br />
			<br />
	
	Gender:<h:selectOneRadio value="#{patient.gender}">
				<f:selectItem itemValue="M" itemLabel="Male" />
				<f:selectItem itemValue="F" itemLabel="Female" />
			</h:selectOneRadio>
			<br />
			<br />

	Address:
	<h:inputText value="#{patient.address}"></h:inputText>
			<br />
			<br />
			Phone No:
	<h:inputText value="#{patient.phoneNo}"></h:inputText>
			<br />
			<br />
			Disease:
	<h:inputText value="#{patient.disease}"></h:inputText>
			<br />
			<br />
			Email:
	<h:inputText value="#{patient.email}"></h:inputText>
			<br />
			<br />
			Password:
	<h:inputText value="#{patient.password}"></h:inputText>
			<br />
			<br />
			Doctor Id:
	<h:inputText value="#{patient.docId}"></h:inputText>
			<br />
			<br />
			<h:commandButton action="#{hmsImpl.addPatientDao(patient)}" value="Add"></h:commandButton>
			
			<h:commandLink action="RoomAllocate" value="Allocate Room" />
		</h:form>
	</center>
</body>

	</html>

</f:view>