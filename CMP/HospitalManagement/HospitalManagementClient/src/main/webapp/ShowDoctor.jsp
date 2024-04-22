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
	<h:form>
		<center>
			<h2>
				<h:outputText value="Doctor Records" />
			</h2>

			<h:dataTable value="#{hmsImpl.showDoctorEjb()}" var="d" border="3">
				<h:column>
					<f:facet name="header">
						<h:outputLabel value="Doctor Id" />
					</f:facet>
					<h:outputText value="#{d.drId}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputLabel value="Name" />
					</f:facet>
					<h:outputText value="#{d.drName}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputLabel value="Department" />
					</f:facet>
					<h:outputText value="#{d.dept}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputLabel value="Book Appointment" />
					</f:facet>
					<h:commandButton action="AddPatient"
						value="Book Appointment" />
				</h:column>
			</h:dataTable>
		</center>
	</h:form>
</body>
	</html>
</f:view>
