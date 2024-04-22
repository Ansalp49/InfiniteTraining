<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Show Prescription</title>
<style>
body {
	font-family: Consolas;
	background-image: url("ta.png");
	background-size: cover;
	background-color: #afdcde;
}

commandButton {
	padding: 8px 16px;
	font-size: 14px;
	cursor: pointer;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
}

commandButton:hover {
	background-color: #45a049;
}

.sortbutton {
	color: white;
	text-decoration: none;
}

.pagination-button {
	color: white;
	font-size: 16px;
	text-align: left;
	margin-left: 5px;
	/* Add any other styles you want for enabled buttons */
}

.pagination-button.disabled {
	background-color: #9ACD32; /* Light green for disabled */
	color: #333; /* Set color for disabled state */
	cursor: not-allowed; /* Change cursor for disabled state */
	/* Add any other styles you want for disabled buttons */
}

.pagination-button {
	color: white;
	font-size: 16px;
	text-align: left;
	background-color: red;
	padding: 5px 10px;
	border: none;
	cursor: pointer;
	margin-right: 5px;
	border: none;
	cursor: pointer;
	cursor: pointer;
}

.redirect {
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
	font-weight: bold;
	background-color: #fac32a;
}

.redirect:hover {
	background-color: #d6a724;
}

.static {
	color: #ff0000;
	border: none;
	padding: 10px 20px;
	text-align: center;
	display: inline-block;
	font-size: 16px;
	margin: 10px 0;
	cursor: default;
	border-radius: 4px;
	font-weight: bold;
	background-color: #fac32a;
}

.static:disabled {
	opacity: 0.5;
}

.pagination-button.disabled {
	color: #333; /* Set color for disabled state */
	background-color: #9ACD32; /* Light green for disabled buttons */
	cursor: not-allowed; /* Change cursor for disabled state */
}

.example2 {
	padding: 6px;
	padding-left: 40px;
	font-size: 14px;
	border: none;
	width: 24%;
	border-radius: 5px;
	background: #ffffff url("search.png") 8px 4px no-repeat;
	background-size: 18px 20px;
	margin-bottom: 6px;
}

.inputText {
	width: 100%;
	padding: 5px 8px;
	margin: 4px 0;
	display: inline-block;
	border-radius: 3px;
	box-sizing: border-box;
	border: 1px solid #ccc;
}

.resetButton {
	background-color: #e3e3e3;
	color: #000000;
	border: none;
	padding: 10px 20px;
	text-align: center;
	display: inline-block;
	font-size: 16px;
	margin-left: 10px;
	margin-top: 6px;
	cursor: pointer;
	border-radius: 3px;
}

.resetButton:hover {
	background-color: #cccccc;
}

.searchbutton {
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

.searchbutton:hover {
	background: #0b7dda;
}

.dataTable {
	width: 98%;
	margin-top: 6px;
	padding: 3%;
	text-align: center;
	border: 3px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	font-weight: bold;
	border-collapse: collapse;
	background-color: #ffffff;
}

.dataTable th {
	background-color: #4CAF50;
	padding: 6px;
	font-size: 15px;
	text-align: center;
	color: white;
	border: 1px solid #ddd;
}

.dataTable td {
	font-size: 15px;
	border: 1px solid #a6706c;
}

.dataTable tr:nth-child(even) {
	background-color: #f2f2f2;
}

footer {
	background-color: #300c0ca3;
	color: #fff;
	padding: 5px 0;
	border-radius: 10px;
}

.footer-content {
	display: flex;
	justify-content: space-around;
	max-width: 1200px;
	margin: 0 auto;
}

.footer-section {
	flex: 1;
	padding: 0 20px;
}

h3 {
	font-size: 18px;
}

ul {
	list-style-type: none;
	padding: 0;
}

li {
	margin-bottom: 10px;
}

a {
	color: #fff;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.social-icon {
	font-size: 24px;
	margin-right: 10px;
	color: #fff;
}

.footer-bottom {
	text-align: center;
	margin-top: 20px;
	font-size: 14px;
}
</style>
</head>
<body>
	<h:form>
		<center>
			<h1>
				<h:outputText value="Prescription Lists" />
			</h1>
			<h:outputText value="Enter Prescription Id (PS001,PS002,..)"
				style="color: #636363;font-size: 15px;margin-left: 10px;" />
			<br />
			<h:inputText value="#{res.prescriptionId}" autocomplete="off"
				styleClass="example2" />
			<br />
			<h:outputText value="#{presId}"
				style="color:red; background-color: #fcd4d5; margin-top: 6px;" />

			<br />

			<h:commandButton styleClass="searchbutton"
				action="#{controller.validatePres(res.prescriptionId)}"
				value="Search" />
			<h:commandButton action="#{res.resetButton()}" value="Reset"
				styleClass="resetButton" />

			<h:panelGroup rendered="#{empty presId}">
				<h:dataTable value="#{pageDao.getPresList(prescriptionId)}" var="d"
					border="1" styleClass="dataTable">
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="Prescription Id">
								<br />
								<h:commandLink action="#{presImpl.sortbyPriscriptionId()}"
									value="" styleClass="sortbutton">
									<span style="display: inline; margin: 0;">&#129035;</span>
									<span style="display: inline; margin: 0;">&#129033;</span>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{d.prescriptionId}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="Provider Id">
								<br />
								<h:commandLink action="#{presImpl.sortbyProviderId()}" value=""
									styleClass="sortbutton">
									<span style="display: inline; margin: 0;">&#129035;</span>
									<span style="display: inline; margin: 0;">&#129033;</span>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{d.providerid}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="Recipient Id">
								<br />
								<h:commandLink action="#{presImpl.sortbyPatientId()}" value=""
									styleClass="sortbutton">
									<span style="display: inline; margin: 0;">&#129035;</span>
									<span style="display: inline; margin: 0;">&#129033;</span>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{d.recipentId}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="Appointment Id">
								<br />
								<h:commandLink action="#{presImpl.sortbyAppointmentId()}"
									value="" styleClass="sortbutton">
									<span style="display: inline; margin: 0;">&#129035;</span>
									<span style="display: inline; margin: 0;">&#129033;</span>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{d.appointmentId}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputText value="Symptoms" />
						</f:facet>
						<h:outputText value="#{d.symptoms}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Medicine" />
						</f:facet>
						<h:outputText value="#{d.medicine}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Dosage" />
						</f:facet>
						<h:outputText value="#{d.dosage}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputLabel value="Prescribed Date">
								<br />
								<h:commandLink action="#{presImpl.sortbyDate()}" value=""
									styleClass="sortbutton">
									<span style="display: inline; margin: 0;">&#129035;</span>
									<span style="display: inline; margin: 0;">&#129033;</span>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText
							value="#{d.prescribedDate.toString().substring(0,10)}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Instructions" />
						</f:facet>
						<h:outputText value="#{d.instructions}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Sale" />
						</f:facet>
						<h:commandButton value="Sale" styleClass="redirect"
							action="#{impl.redirectToViewMedicine(d.prescriptionId)}"
							rendered="#{!controller.isPrescriptionSold(d.prescriptionId)}" />
						<h:outputText value="Sold" styleClass="static"
							rendered="#{controller.isPrescriptionSold(d.prescriptionId)}" />
					</h:column>
				</h:dataTable>

				<h:panelGroup rendered="#{pageDao.currentPage != 1}">
					<h:commandButton value="first" action="#{pageDao.pageFirst}"
						styleClass="pagination-button" />
					<h:commandButton value="prev" action="#{pageDao.pagePrevious}"
						styleClass="pagination-button" />
				</h:panelGroup>
				<h:outputText
					value="Page #{pageDao.currentPage} / #{pageDao.totalPages}"
					style="font-size: 16px; color: #fff; background-color: #333; padding: 5px; text-align: center;" />
				<h:panelGroup
					rendered="#{pageDao.currentPage != pageDao.totalPages}">
					<h:commandButton value="next" action="#{pageDao.pageNext}"
						styleClass="pagination-button" />
					<h:commandButton value="last" action="#{pageDao.pageLast}"
						styleClass="pagination-button" />
				</h:panelGroup>
			</h:panelGroup>

		</center>

	</h:form>
	<footer>
		<div class="footer-content">
			<div class="footer-section">
				<h3>Quick Links</h3>
				<ul>
					<li><a href="Menu.jsp">Home</a></li>
					<li><a href="#">Features</a></li>
					<li><a href="#">About Us</a></li>
					<li><a href="contact.jsp">Contact</a></li>
				</ul>
			</div>

			<div class="footer-section">
				<h3>Contact Us</h3>
				<p>Email: Info@hospitalmanagementsystem.com</p>
				<p>Phone: +91 123-456-7890</p>
				<p>Address: Kundahalli, EPIP Zone, Whitefield, Banglore</p>
			</div>

			<div class="footer-section">
				<h3>Connect with Us</h3>
				<a href="#" class="social-icon"><i
					class="fa-solid fa-magnifying-glass"></i></a> <a href="#"
					class="social-icon"><i class="fab fa-twitter"></i></a> <a href="#"
					class="social-icon"><i class="fab fa-linkedin"></i></a> <a href="#"
					class="social-icon"><i class="fab fa-instagram"></i></a>
			</div>
		</div>
		<p class="footer-bottom">&copy; 2023 Your Hospital Management
			System. All rights reserved.</p>
	</footer>
	<div id="loader">
		<div class="spinner"></div>
	</div>
</body>
	</html>
</f:view>