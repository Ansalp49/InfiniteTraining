<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<f:view>
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Equipments</title>
<link rel="stylesheet" type="text/css"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="WEB-INF/DatePicker.css" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(document).ready(function() {
		$(".datepicker").datepicker({
			dateFormat : 'yy-mm-dd',
			changeMonth : true,
			changeYear : true,
			yearRange : "2020:2040"
		});
	});
	function validateNumericInput(event) {
		var charCode = (event.which) ? event.which : event.keyCode;
		return (charCode >= 48 && charCode <= 57) || charCode == 8
				|| charCode == 0;
	}
	function validateDoubleInput(event) {
		var charCode = (event.which) ? event.which : event.keyCode;
		return (charCode >= 48 && charCode <= 57) || charCode == 46
				|| charCode == 8 || charCode == 0;
	}
</script>
<style>
.ui-datepicker {
	font-size: 15px;
	background-color: #5a7c8a;
}

.ui-datepicker-header {
	background-color: #e9f7ea;
	color: #111211;
}

.ui-datepicker-title {
	color: #111211;
}

.ui-datepicker-calendar tbody td a {
	color: #111211;
}

.ui-datepicker-calendar tbody td a:hover {
	background-color: #e9f7ea;
	color: #111211;
}

body {
	font-family: Arial, sans-serif;
	background-image: url("Background 1.jpg");
	background-size: cover;
	background-color: #5a7c8a;
}

form {
	margin: 20px auto;
	padding: 10px;
	background-color: rgba(255, 255, 255, 0.8);
	border: 1px solid #ddd;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 50%;
}

.h2 {
	color: #c43953;
	font-family: DejaVu Sans Mono;
	text-align: center;
}

.inputText, .selectOneMenu, .inputTextarea {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	border: 1px solid #ccc;
	border-radius: 4px;
	border-radius: 4px;
}

.commandButton {
	background-color: #007bff;
	color: #fff;
	border: none;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 10px 0;
	cursor: pointer;
	border-radius: 4px;
	transition: background-color 0.3s ease;
}

.commandButton:hover {
	background-color: #0056b3;
}

.message {
	color: red;
	font-family: Miriam Libre;
}

.back {
	color: #272947;
	font-weight: bold;
	text-decoration: none;
	font-size: 25px;
	padding-left: -100px;
}
</style>
</head>

<body>

	<h:form id="form">
		<h:commandLink action="DashBoard" value="" styleClass="back">
			<span>&#129128;</span>
		</h:commandLink>
		<center>
			<h1>

				<h:outputText value="Add Equipments" styleClass="h2" />

			</h1>
		</center>
		<strong><h:outputText value="Equipment Id " /></strong>
		<h:outputText value="(e.g. E001,E002,...)" style="color:#c7c7c7" />
		<h:inputText id="medId" value="#{entry.medId}" autocomplete="off"
			styleClass="inputText inputTextarea" />
		<h:message for="form:medId" styleClass="message" />
		<br />
		<br />
		<strong><h:outputText value="Equipment Name " /></strong>
		<h:inputText id="medName" value="#{entry.medName}" autocomplete="off"
			styleClass="inputText inputTextarea" />
		<h:message for="form:medName" styleClass="message" />
		<br />
		<br />
		<strong><h:outputText value="Type " /></strong>
		<h:selectOneMenu value="#{entry.type}" styleClass="selectOneMenu">
			<f:selectItem itemValue="Diagnostic Equipment"
				itemLabel="Diagnostic Equipment" />
			<f:selectItem itemValue="Surgical Instruments"
				itemLabel="Surgical Instruments" />
			<f:selectItem itemValue="Monitoring Devices"
				itemLabel="Monitoring Devices" />
			<f:selectItem itemValue="Orthopedic Devices"
				itemLabel="Orthopedic Devices" />
			<f:selectItem itemValue="Dental Equipment"
				itemLabel="Dental Equipment" />
			<f:selectItem itemValue="Laboratory Equipment"
				itemLabel="Laboratory Equipment" />
			<f:selectItem itemValue="Patient Transport Equipment"
				itemLabel="Patient Transport Equipment" />
		</h:selectOneMenu>
		<br />
		<br />
		<strong><h:outputText value="Quantity " /></strong>
		<h:inputText id="quantity" value="#{entry.quantity}"
			autocomplete="off" styleClass="inputText inputTextarea" />
		<h:message for="form:quantity" styleClass="message" />

		<br />
		<br />

		<strong><h:outputText value="Mfg Date  " /></strong>
		<h:inputText id="mfgDate" value="#{entry.mfgDate}" autocomplete="off"
			styleClass="datepicker inputText inputTextarea">
			<f:convertDateTime pattern="yyyy-MM-dd" />
		</h:inputText>


		<h:message for="form:mfgDate" styleClass="message" />

		<br />
		<br />
		<strong><h:outputText value="Price " /></strong>
		<h:inputText id="price" value="#{entry.price}" autocomplete="off"
			styleClass="inputText inputTextarea" />
		<h:message for="form:price" styleClass="message" />
		<br />
		<br />

		<h:commandButton action="#{controller.addEquip(entry)}"
			onclick="validateForm();" value="Add Equipments"
			styleClass="commandButton" />

	</h:form>

</body>

	</html>

</f:view>