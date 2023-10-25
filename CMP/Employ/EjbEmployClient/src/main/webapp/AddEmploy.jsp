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
			<h:outputText value="Add Employ" />
		</h2>
		<h:form id="form">
			<h:outputText value="Employ No " />
			<h:inputText id="empno" value="#{employ.empno}" />

			<br />
			<br />
			<h:outputText value="Name " />
			<h:inputText id="name" value="#{employ.name}" />

			<br>
			<br>
			<h:outputText value="Gender " />
			<h:inputText id="gender" value="#{employ.gender}" />

			<br>
			<br>
			<h:outputText value="Department " />
			<h:inputText id="dept" value="#{employ.dept}" />
			<br>
			<br>
			<h:outputText value="Designation " />
			<h:inputText id="desig" value="#{employ.desig}" />
			<br />
			<br />
			<h:outputText value="Basic" />
			<h:inputText id="basic" value="#{employ.basic}" />

			<br />
			<br />
			<h:commandButton action="#{ejbImpl.addEmployEjb(employ)}"
				value="Add Employ" />
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<h:commandButton type="reset" value="Reset"></h:commandButton>
		</h:form>
	</center>
</body>
	</html>

</f:view>