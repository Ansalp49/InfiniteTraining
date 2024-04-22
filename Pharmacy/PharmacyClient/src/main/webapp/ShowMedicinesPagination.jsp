<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>


<script>
	function toggleSortingLinks(link) {
        // Get the parent label element
        var label = link.parentNode;
 
        // Toggle the visibility of sorting links within the label
        for (var i = 0; i < label.children.length; i++) {
            var child = label.children[i];
            if (child.tagName === 'A') {
                child.style.display = (child === link) ? 'none' : 'inline-block';
            }
        }
    }
	setTimeout(function() {
		var successMessage = document.getElementById('medform:added');
		if (successMessage) {
			successMessage.style.display = 'none';
		}
	}, 3500);
    
	</script>

<script>
    function isNumberKey(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
</script>


<style>
/* Reset some default styles */
body, h1, h2, h3, p, ul, li, table, form {
	margin: 0;
	padding: 0;
}

body {
	font-family: Arial, sans-serif;
	background-color: white; /* Light gray background */
	background-image: url("showpagination.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	margin: 0;
	padding: 0;
	overflow-x: hidden;
	margin-top: -100px;
}

form {
	width: 75%;
	margin: 100px auto 0;
	padding: 20px;
	border-radius: 8px;
}

.container {
	background-color: white;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	padding: 20px;
	margin: 20px;
}

.button4 {
	background-color: #008080; /* Light gray button */
	color: white; /* Dark gray text */
	border: 2px solid #666; /* Dark gray border */
	align: right;
}

.button4:hover {
	/*  background-color: #40E0D0;  */
	background-color: #006666;
	color: #fff; /* White text on hover */
}

.button4:disabled {
	opacity: 0.5; /* Reduce opacity for disabled buttons */
	cursor: not-allowed; /* Change cursor for disabled buttons */
}

.button5 {
	width: 133px;
	height: 32px;
	border-radius: 11px;
	background-color: #EF2222;
	color: white;
	border: 2px solid #666;
	align: right;
}

.button5:hover {
	background-color: black;
	color: #fff;
}

.button6 {
	background-color: white;
	color: black;
	border: 2px solid #666;
	align: right;
	width: 61px;
	height: 25px;
	border-radius: 6px;
	font-size: 13px;
}

.button6:hover {
	background-color: grey; /* Dark gray button on hover */
	color: #fff; /* White text on hover */
	cursor: pointer;
}

.button6:disabled {
	opacity: 0.5; /* Reduce opacity for disabled buttons */
	cursor: not-allowed; /* Change cursor for disabled buttons */
}

.button7 {
	background-color: white; /* Light gray button */
	color: black; /* Dark gray text */
	border: 2px solid #666; /* Dark gray border */
	align: right;
	width: 56px;
	height: 26px;
	border-radius: 6px;
	font-size: 14px;
}

.button7:hover {
	background-color: grey; /* Dark gray button on hover */
	color: #fff; /* White text on hover */
}

.button7:disabled {
	opacity: 0.5; /* Reduce opacity for disabled buttons */
	cursor: not-allowed; /* Change cursor for disabled buttons */
}

.button8 {
	background-color: white; /* Light gray button */
	color: black; /* Dark gray text */
	border: 2px solid #666; /* Dark gray border */
	align: right;
	width: 56px;
	height: 26px;
	border-radius: 6px;
	font-size: 14px;
	"
}

.button8:hover {
	background-color: grey; /* Dark gray button on hover */
	color: #fff; /* White text on hover */
}

.button8:disabled {
	opacity: 0.5; /* Reduce opacity for disabled buttons */
	cursor: not-allowed; /* Change cursor for disabled buttons */
}

.button9 {
	background-color: white;
	color: black;
	border: 2px solid #666;
	align: right;
	width: 61px;
	height: 25px;
	border-radius: 6px;
	font-size: 13px;
}

.button9:hover {
	background-color: grey; /* Dark gray button on hover */
	color: #fff; /* White text on hover */
	cursor: pointer;
}

.button9:disabled {
	opacity: 0.5; /* Reduce opacity for disabled buttons */
	cursor: not-allowed; /* Change cursor for disabled buttons */
}

.button16 {
	border: 2px solid #666; /* Dark gray border */
	align: right;
	width: 61px;
	height: 25px;
	border-radius: 4px;
	font-size: 18px;
	margin-top: -120px;
	background-color: #D3D3D3;
	color: #333;
}

.button16:hover {
	background-color: #B0B0B0;
	color: #fff; /* White text on hover */
	cursor: pointer;
}

.button10 {
	/*  background-color: white;  */
	background-color: #FF6347;
	color: white; /* Dark gray text */
	border: 2px solid #666; /* Dark gray border */
	align: right;
	width: 75px;
	height: 29px;
	border-radius: 4px;
	font-size: 18px;
	margin-top: -97px;
}

.button10:hover {
	background-color: #FF4500;
	color: #fff; /* White text on hover */
	cursor: pointer;
}

header {
	background-color: #333; /* Dark gray header background */
	color: #fff; /* White text */
	padding: 100px 0;
	text-align: center;
}

/* Example CSS for the main heading */
h1 {
	font-size: 40px;
	color: navy;
	text-shadow: 4px 4px 8px rgba(0, 0, 0, 0.5);
	margin-bottom: 50px;
	/* Additional styling as needed */
}

h2 {
	font-size: 16px;
	color: dimgrey;
	margin-left: -40px;
	/* Additional styling as needed */
}

/* Navigation menu styles */
nav {
	background-color: #333; /* Dark gray color */
	color: #fff;
	padding: 10px;
	text-align: center;
}

ul {
	list-style: none;
}

ul li {
	display: inline;
	margin-right: 20px;
}

/* Main content container */
.container {
	background-color: Brown;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	padding: 20px;
	margin: 20px;
}

/* Table styles */
table {
	width: 118%;
	border-collapse: collapse;
	margin-top: -10%;
	margin-left: -85px;
}

th, td {
	text-align: center;
	padding: 7px;
	background-color: white;
}

th {
	background-color: #D3D3D3;
	color: black;
	font-size: small;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:nth-child(odd) {
	background-color: #e2e2e2; /* Light gray color */
}

/* Button styles */
.button {
	background-color: blue; /* Blue color */
	color: #fff;
	border: none;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 5px 2px;
	cursor: pointer;
	border-radius: 4px;
}

.button:hover {
	background-color: #0056b3; /* Darker blue color on hover */
}

.example input[type=text] {
	padding: 5px;
	font-size: 15px;
	border: 1px;
	border-radius: 15px 0px 0px 15px;
	float: center;
	background: #ffffff url("searchicon.jpg") 8px 4px no-repeat;
	background-size: 18px 20px;
	padding-left: 30PX;
}

.dummy {
	padding: 5px;
	border-radius: 0px 15px 15px 0px;
	font-size: 15px;
	background-color: orange;
	/*  background-color: #40E0D0;  */
	/*  background:lightgreen; */
	color: white; /* Dark gray text */
	border: 2px solid #666; /* Dark gray border */
	align: right;
	margin-bottom: 120px;
}

.DUMMY:HOVER {
	/*  background: linear-gradient(to right, #2980b9, #5d6d7e); */
	/*  background: goldenrod; */
	/*  background-color: #FFA500; */
	background-color: #D3D3D3;
}

.triangle {
	width: 0;
	height: 0;
	/*   border-width: 0 5.5px 13px 5.5px; */
	border-width: 0px 5.5px 11px 6.5px;
	border-color: transparent transparent #0A0101 transparent;
	border-style: solid;
	/*    margin-left: 5px; */
}

.triangle:hover {
	border-color: transparent transparent blue transparent;
}

.triangleDown {
	width: 0;
	height: 0;
	/*   border-width: 13px 5.5px 0 5.5px; */
	border-width: 11px 5.5px 0px 6.5px;
	border-color: #0A0101 transparent transparent transparent;
	border-style: solid;
	/*  margin-left: 5px; */
}

.triangleDown:hover {
	border-color: blue transparent transparent transparent;
}

.Sort {
	display: inline-flex;
}

.style {
	width: 140px;
	height: 35px;
	border-radius: 20px;
}

.elegant-button {
	/*  background: linear-gradient(to right, #08141c, #6a89cc); */
	background: darkblue;
	/*     border: 2px solid #3498db; */
	/* border: 2px solid #336699; */
	/*  border: 2px solid #444; */
	border: 2px solid white;
	color: #fff;
	padding: 7px 10px;
	border-radius: 11px;
	font-size: 15px;
	font-weight: bold;
	cursor: pointer;
	transition: background 0.3s, color 0.3s;
	width: 151px;
	height: 36px;
}

.elegant-button:hover {
	/*  background: linear-gradient(to right, #2980b9, #5d6d7e); */
	background-color: #3399FF;
	/*   background-color: #001F3F; */
	color: #fff;
}

.elegant-button:active {
	transform: scale(0.95);
}

/* Additional style for the search icon */
.elegant-button i {
	margin-right: 10px;
	/* Adjust the space between the button text and the icon */
}

.elegant-buttonR {
	background: linear-gradient(to right, #F2A43E, #F29B29);
	border: 2px solid #3498db;
	color: #fff;
	padding: 5px;
	border-radius: 0px 15px 15px 0px;
	font-size: 15px;
	font-weight: bold;
	cursor: pointer;
	transition: background 0.3s, color 0.3s;
	align: right;
	width: 68px;
	height: 30px;
}

.elegant-buttonR:hover {
	background: linear-gradient(to right, #2980b9, red);
	color: #fff;
}

.elegant-buttonR:active {
	transform: scale(0.95);
}

/* Additional style for the search icon */
.elegant-buttonR i {
	margin-right: 10px;
	/* Adjust the space between the button text and the icon */
}

}
#error-message {
	color: red;
}

.back-arrow {
	width: 15px;
	height: 15px;
	transform: rotate(135deg);
	-webkit-transform: rotate(135deg);
	border: solid #55c0ff;
	border-width: 0 4px 4px 0;
	display: inline-block;
	padding: 4px;
	position: fixed; /* Set the position to fixed */
	top: 10px; /* Adjust the top property as needed */
	left: 10px; /* Adjust the left property as needed */
}

.success {
	color: #00b831;
	font-size: 30px;
	background-color: #87faa6;
	width: 100%;
}
</style>


</head>
<body>
	<h:form>
		<h:commandLink action="DashBoard">
			<div class="back-arrow"></div>
		</h:commandLink>
	</h:form>

	<h:form id="medform">

		<center>
			<div class="example" style="margin-bottom: 50px;">
				<h:outputText value="#{added}" styleClass="success" id="added" />
				<h1>
					<h:outputText value="AVAILABLE STOCK" />
				</h1>

				<h2>
					<h:outputText value="ENTER STOCK NAME" />
				</h2>
				<h:outputLabel for="medName"></h:outputLabel>

				<h:inputText id="medName" value="#{mainshow.medName}" maxlength="30"
					styleClass="example">

				</h:inputText>

				<h:commandButton
					action="#{medController.addSearchh(mainshow.medName)}"
					value="Search" styleClass="dummy "
					actionListener="#{pagebean.setShowErrorMessage(true)}"></h:commandButton>

				<br />
				<div class="example" style="margin-bottom: 120px;">
					<!-- ... (other code) ... -->
					<h:commandButton action="#{pagebean.clear()}" value="RESET"
						styleClass="button10"></h:commandButton>
				</div>
				<div style="margin-top: -160px;">
					<h:messages id="globalMessages" globalOnly="true"
						style="color:red;font-weight:bolder"></h:messages>
					<h:message for="medform:medName"
						style="color:red;font-weight:bolder"></h:message>
				</div>
				<div style="height: 70px;"></div>
			</div>
		</center>
		<center>
			<h:panelGroup
				rendered="#{empty pagebean.getMedicinesList(mainshow.medName) and pagebean.showErrorMessage }">
				<h:outputText value="No Records Found"
					style="color:red;font-weight:bold;" />

				<div style="margin-bottom: 120px;"></div>
			</h:panelGroup>

			<h:panelGroup
				rendered="#{not empty pagebean.getMedicinesList(mainshow.medName)}">
				<h:dataTable value="#{pagebean.getMedicinesList(mainshow.medName)}"
					var="i" border="3">
					<h:column>
						<f:facet name="header">

							<h:outputLabel value="ENTRYID"
								style="display: flex; align-items: center;">&nbsp;&nbsp;
            <h:commandLink action="#{mainshow.sortByEntryId()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangle"></div>
								</h:commandLink>
								<h:commandLink action="#{mainshow.sortByEntryIdDes()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangleDown"></div>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{i.entryId}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="STOCKID"
								style="display: flex; align-items: center;">&nbsp;&nbsp;
            <h:commandLink action="#{mainshow.sortByMedId()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangle"></div>
								</h:commandLink>
								<h:commandLink action="#{mainshow.sortByMedIdDes()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangleDown"></div>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{i.medId}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputLabel value="STOCKNAME"
								style="display: flex; align-items: center;">&nbsp;&nbsp;
            <h:commandLink action="#{mainshow.sortByMedName()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangle"></div>
								</h:commandLink>
								<h:commandLink action="#{mainshow.sortByMedNameDes()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangleDown"></div>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{i.medName}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputLabel value="TYPE"
								style="display: flex; align-items: center;margin-left:32px">&nbsp;&nbsp;
            <h:commandLink action="#{mainshow.sortByType()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangle"></div>
								</h:commandLink>
								<h:commandLink action="#{mainshow.sortByTypeDes()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangleDown"></div>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{i.type}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputLabel value="QUANTITY"
								style="display: flex; align-items: center;">&nbsp;&nbsp;
            <h:commandLink action="#{mainshow.sortByQuantity()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangle"></div>
								</h:commandLink>
								<h:commandLink action="#{mainshow.sortByQuantityDes()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangleDown"></div>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{i.quantity}"
							style="#{i.quantity <= 9 ? 'color: red;' : 'color: black;'}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputLabel value="ENTRYDATE"
								style="display: flex; align-items: center;">&nbsp;&nbsp;
            <h:commandLink action="#{mainshow.sortByEntryDate()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangle"></div>
								</h:commandLink>
								<h:commandLink action="#{mainshow.sortByEntryDateDes()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangleDown"></div>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{i.entryDate.toString().substring(0, 10)}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputLabel value="MANUFACTUREDATE"
								style="display: flex; align-items: center;">&nbsp;&nbsp;
            <h:commandLink action="#{mainshow.sortByManufactureDate()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangle"></div>
								</h:commandLink>
								<h:commandLink action="#{mainshow.sortByManufactureDateDes()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangleDown"></div>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{i.mfgDate.toString().substring(0, 10)}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputLabel value="EXPIRYDATE"
								style="display: flex; align-items: center;">&nbsp;&nbsp;
            <h:commandLink action="#{mainshow.sortByExpiryDate()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangle"></div>
								</h:commandLink>
								<h:commandLink action="#{mainshow.sortByExpiryDateDes()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangleDown"></div>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{i.expiryDate.toString().substring(0, 10)}"
							style="#{i.expiryWithinOneMonth or i.expired ? 'color: red;' : 'color: black;'}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputLabel value="PRICE"
								style="display: flex; align-items: center;">&nbsp;&nbsp;
            <h:commandLink action="#{mainshow.sortByPrice()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangle"></div>
								</h:commandLink>
								<h:commandLink action="#{mainshow.sortByPriceDes()}"
									onclick="toggleSortingLinks(this)" styleClass="sort-link">
									<div class="triangleDown"></div>
								</h:commandLink>
							</h:outputLabel>
						</f:facet>
						<h:outputText value="#{i.price}" />
					</h:column>

					<h:column>
						<f:facet name="header">
							<h:outputLabel value="UPDATE" />
						</f:facet>

						<h:commandButton
							action="#{mainshow.redirecttoUpdateMedicine(i.medId, i.medName, i.type, i.quantity, i.mfgDate, i.expiryDate, i.price)}"
							value="UPDATE" styleClass="button4">


						</h:commandButton>
					</h:column>
				</h:dataTable>

				<br>
				<br>
				<br>

				<!--The paging buttons-->

				<div
					style="margin-right: -90%; margin_bottom: -90%; bottom: 20px; right: 20px;">
					<h:commandButton styleClass="button6" value="FIRST"
						action="#{pagebean.pageFirst}"
						disabled="#{pagebean.firstRow == 0}"></h:commandButton>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<h:commandButton styleClass="button7" value="PREV"
						action="#{pagebean.pagePrevious}"
						disabled="#{pagebean.firstRow == 0}">
					</h:commandButton>
					&nbsp;

					<h:outputText value="&nbsp;" escape="false" />
					<h:commandButton styleClass="button8" value="NEXT"
						action="#{pagebean.pageNext}"
						disabled="#{pagebean.firstRow + pagebean.rowsPerPage >= pagebean.totalRows}">

					</h:commandButton>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<h:outputText value="&nbsp;" escape="false" />
					<h:commandButton styleClass="button9" value="LAST"
						action="#{pagebean.pageLast}"
						disabled="#{pagebean.firstRow + pagebean.rowsPerPage >= pagebean.totalRows}"></h:commandButton>

					<h:outputText value="&nbsp;" escape="false" />

					<h:outputText styleClass="colour"
						value="Page #{pagebean.currentPage} / #{pagebean.totalPages}" />
					<br /> <br>


					<!-- Set rows per page -->
					<h:outputLabel for="rowsPerPage" value="Rows Per Page" />
					<h:inputText id="rowsPerPage"
						onkeypress="return isNumberKey(event)"
						styleClass="rowperpageInput"
						style=" height: 25px; border-radius: 4px;"
						value="#{pagebean.rowsPerPage}" size="3" maxlength="3"
						converterMessage="Please enter numeric only" />
					&nbsp;&nbsp;
					<h:commandButton styleClass="button16" value="SET"
						action="#{pagebean.pageFirst}"></h:commandButton>

					<h:message for="form2:rowsPerPage"
						style="color:red;font-weight:bold" />
					<br />

				</div>
			</h:panelGroup>

			<br />
			<h:commandButton action="TotalQuantity.jsp" value="TOTAL QUANTITY"
				styleClass="elegant-button">
			</h:commandButton>
		</center>
	</h:form>
</body>
	</html>
</f:view>
