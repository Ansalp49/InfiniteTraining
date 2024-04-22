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
<script type="text/javascript">
	setTimeout(function() {
		var successMessage = document.getElementById('form:medSale');
		if (successMessage) {
			successMessage.style.display = 'none';
		}
	}, 3500);
</script>
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
	background-size: cover;
	background-color: #222;
	background-image: url("Background 1.jpg");
	font-size: 15px;
}

.details {
	width: 60%;
	background: #ffffff url("phar.png") 100% 0 no-repeat;
	background-size: 100px 100px;
	font-size: 18px;
	padding: 12px;
	margin-top: 15px;
}

.h1 {
	color: #000000;
	font-family: monospace;
	font-size: 45px;
}

.success {
	color: #00b831;
	font-size: 30px;
	background-color: #87faa6;
	width: 100%;
}

.dataTable {
	width: 90%;
	margin-top: 6px;
	padding: 3%;
	text-align: center;
	border-collapse: collapse;
}

.dataTable th, .dataTable td {
	font-size: 15px;
	padding: 6px;
	font-family: monospace;
	text-align: center;
	color: #000000;
	border: none;
}

.dataTable th {
	border-right: 1px dotted #000000;
	border-bottom: 1px solid #000000;
	border-left: 1px dotted #000000;
}

.dataTable td {
	border-left: 1px dotted #000000;
	border-right: 1px dotted #000000;
}

.back {
	margin-top: 6px;
	background: #2196F3;
	color: #ffffff;
	display: inline-block;
	font-size: 16px;
	text-align: center;
	border: none;
	border-radius: 3px;
	cursor: pointer;
	padding: 10px 20px;
}

.back:hover {
	background: #0b7dda;
}
</style>

</head>
<body>

	<h:form id="form">
		<center>
			<h:outputText value="#{success}" id="medSale" styleClass="success" />
			<br />
			<div class="details">
				<strong><h:outputText value="View Invoice" styleClass="h1" /></strong>
				<div style="font-family: monospace; font-size: 14px;">
					<h:outputText value="#{pharshop}" />
					<br />
					<h:outputText value="+91-#{pharno}" />
					<br />
					<h:outputText value="#{pharmail}" />
					<br />
					<h:outputText value="Address: #{pharadd}" />
				</div>
				<br />
				<div style="margin-left: 80%; display: flex;">
					<div style="font-size: 15px;">
						<h:outputText value="Prescription Id: " />
					</div>
					<strong><h:outputText value="#{prescriptionId}" /></strong><br />
				</div>
				<div style="text-align: left; font-style: oblique;">
					<div style="font-size: 13px;">
						<strong><h:outputText value="#{providerId}" /></strong>
					</div>
					<h:outputText value="Dr.#{providerName}  #{providerLast}" />
					<div style="font-size: 13px;">
						<h:outputText value="#{provmail}" />
					</div>
					<div style="font-size: 13px;">
						<h:outputText value="+91-#{provNumber}" />
					</div>
				</div>
				<br /> <br />
				<div style="text-align: left; font-style: oblique;">
					<div style="font-size: 13px;">
						<h:outputText value="Id: " />
						<strong><h:outputText value="#{recipientId}" /></strong>
					</div>
					<h:outputText value="Name: #{recipientFirst} #{recipientLast}" />
					<div style="font-size: 14px;">
						<h:outputText value="+91-#{recphno}" />
						<div style="font-size: 13px;">
							<h:outputText value="#{recmail}" />
						</div>
					</div>
					<div style="font-size: 13px;">
						<h:outputText value="Address: #{recadd}" />
					</div>
				</div>
				<br />
				<h:dataTable
					value="#{impl.showDetails(prescriptionid,recipientId,providerId,pharmId)}"
					var="s" border="" styleClass="dataTable">
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="ID" />
						</f:facet>
						<h:outputText value="#{s.medId}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="Prescribed Medicines" />
						</f:facet>
						<h:outputText value="#{s.medName}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="Quantity" />
						</f:facet>
						<h:outputText value="#{s.quantity}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="Rate" />
						</f:facet>
						<h:outputText value="#{s.price}">
							<f:convertNumber pattern="#,##0.00" />
						</h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="Amount" />
						</f:facet>
						<h:outputText value="#{s.totalPrice}">
							<f:convertNumber pattern="#,##0.00" />
						</h:outputText>
					</h:column>
				</h:dataTable>
				<br />
				<div style="margin-left: 70%; font-size: 15px;">
					<h:outputText value="Total Price: #{totalprice}">
						<f:convertNumber pattern="#,##0.00" />
					</h:outputText>
				</div>

			</div>
			<br /> <br />
			<h:commandButton action="#{controller.back()}" value="Back"
				styleClass="back" />

		</center>
	</h:form>

</body>
	</html>
</f:view>