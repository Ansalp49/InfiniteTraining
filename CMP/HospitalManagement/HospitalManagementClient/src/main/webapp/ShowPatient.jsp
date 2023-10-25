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
				<h:outputText value="Patient Records" />
			</h2>

			<h:dataTable value="#{hmsImpl.showPatientEjb()}" var="p" border="3">
				<h:column>
					<f:facet name="header">
					<h:outputLabel value="Patient Id" /></f:facet>
					<h:outputText value="#{p.pId}" />
				</h:column>
				<h:column>
					<f:facet name="header">
					<h:outputLabel value="Name" /></f:facet>
					<h:outputText value="#{p.name}" />
				</h:column>
				<h:column>
					<f:facet name="header">
					<h:outputLabel value="Age" /></f:facet>
					<h:outputText value="#{p.age}" />
				</h:column>
				<h:column>
					<f:facet name="header">
					<h:outputLabel value="Weight"/></f:facet>
					<h:outputText value="#{p.weight}" />
				</h:column>
				<h:column>
					<f:facet name="header">
					<h:outputLabel value="Gender" /></f:facet>
					<h:outputText value="#{p.gender}" />
				</h:column>
				<h:column>
					<f:facet name="header">
					<h:outputLabel value="Address" /></f:facet>
					<h:outputText value="#{p.address}" />
				</h:column>
				<h:column>
					<f:facet name="header">
					<h:outputLabel value="Phone No" /></f:facet>
					<h:outputText value="#{p.phoneNo}" />
				</h:column>
				<h:column>
					<f:facet name="header">
					<h:outputLabel value="Disease" /></f:facet>
					<h:outputText value="#{p.disease}" />
				</h:column>
				<h:column>
					<f:facet name="header">
					<h:outputLabel value="Doctor Id" /></f:facet>
					<h:outputText value="#{p.docId}" />
				</h:column>
				
			</h:dataTable>
		</center>
	</h:form>
</body>
	</html>
</f:view>
