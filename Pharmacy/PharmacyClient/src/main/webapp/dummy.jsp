<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
<head>
<f:facet name="first">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</f:facet>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Details</title>
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
	background-size: cover;
	background-color: #222;
	background-image: url("Background 1.jpg");
	font-size: 15px;
}
</style>

</head>
<body>

	<h:form id="form">
		<center>
			<h:commandButton action="AddMedicine" value="Add Medicines" />
			<h:commandButton action="AddEquipments" value="Add Equipments" />

		</center>
	</h:form>

</body>
	</html>
</f:view>