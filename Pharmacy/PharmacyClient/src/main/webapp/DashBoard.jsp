<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.min.css" />
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style type="text/css">
* {
	box-sizing: border-box;
}

body {
	font-family: 'Raleway', Helvetica, sans-serif;
	font-size: 16px; /* Set the default font size */
	line-height: 1.6; /* Set the line height for better readability */
	color: #333; /* Set the text color */
	background-image: url("Background.jpg");
	background-size: cover;
}

.grid-wrap {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	width: 960px;
	margin: 0 auto;
}

.card {
	border: 1px solid #e0e0e0;
	border-radius: 5px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	margin: .5em;
	transition: 0.3s;
	padding: .5em .5em;
	grid-template-rows: 100px 250px;
}

.card:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.4);
}

.card-head {
	background-color: #336ae9;
	border-radius: 5px;
}

.card-body {
	text-align: center;
	margin-bottom: 2em;
}
</style>

<style>
.menu, .menu-bar, .menu-bar1 {
	position: fixed;
	top: 0;
	left: 0;
	height: 100%;
	list-style-type: none;
	margin: 0;
	padding: 0;
	background: #f7f7f7;
	z-index: 10;
	overflow: hidden;
	box-shadow: 2px 0 18px rgba(0, 0, 0, 0.26);
}

.menu li a {
	display: block;
	text-indent: -500em;
	height: 5em;
	width: 5em;
	line-height: 5em;
	text-align: center;
	color: #72739f;
	position: relative;
	border-bottom: 1px solid rgba(0, 0, 0, 0.05);
	transition: background 0.1s ease-in-out;
}

.menu li a:before {
	font-family: FontAwesome;
	speak: none;
	text-indent: 0em;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	font-size: 1.4em;
}

.menu li a.search:before {
	content: "\f002";
}

.menu li a.archive:before {
	content: "\f187";
}

.menu li a.pencil:before {
	content: "\f040";
}

.menu li a.contact:before {
	content: "\f003";
}

.menu li a.about:before {
	content: "\f007";
}

.menu li a.home:before {
	content: "\f039";
}

.menu-bar li a:hover, .menu-bar1 li a:hover, .menu li a:hover, .menu li:first-child a
	{
	background: #267fdd;
	color: #fff;
}

.menu-bar {
	overflow: hidden;
	left: 5em;
	z-index: 5;
	width: 0;
	height: 0;
	transition: all 0.1s ease-in-out;
}

.menu-bar1 {
	overflow: hidden;
	left: 5em;
	z-index: 5;
	width: 0;
	height: 0;
	transition: all 0.1s ease-in-out;
}

.menu-bar li a {
	display: block;
	height: 4em;
	line-height: 4em;
	text-align: center;
	color: #72739f;
	text-decoration: none;
	position: relative;
	font-family: verdana;
	border-bottom: 1px solid rgba(0, 0, 0, 0.05);
	transition: background 0.1s ease-in-out;
}

.menu-bar1 li a {
	display: block;
	height: 4em;
	line-height: 4em;
	text-align: center;
	color: #72739f;
	text-decoration: none;
	position: relative;
	font-family: verdana;
	border-bottom: 1px solid rgba(0, 0, 0, 0.05);
	transition: background 0.1s ease-in-out;
}

.menu-bar li:first-child a {
	height: 5em;
	background: #408289;
	color: #fff;
	line-height: 5
}

.menu-bar1 li:first-child a {
	height: 5em;
	background: #408289;
	color: #fff;
	line-height: 5
}

.para {
	color: #033f72;
	padding-left: 100px;
	font-size: 3em;
	margin-bottom: 20px;
}

.open {
	width: 10em;
	height: 100%;
}

@media all and (max-width: 500px) {
	.container {
		margin-top: 100px;
	}
	.menu {
		height: 5em;
		width: 100%;
	}
	.menu li {
		display: inline-block;
		float: left;
	}
	.menu-bar li a {
		width: 100%;
	}
	.menu-bar1 li a {
		width: 100%;
	}
	.menu-bar {
		width: 100%;
		left: 0;
		height: 0;
	}
	.menu-bar1 {
		width: 100%;
		left: 0;
		height: 0;
	}
	.open {
		width: 100%;
		height: auto;
	}
	.para {
		padding-left: 5px;
	}
}

@media screen and (max-height: 34em) {
	.menu li, .menu-bar, .menu-bar1 {
		font-size: 70%;
	}
}

@media screen and (max-height: 34em) and (max-width: 500px) {
	.menu {
		height: 3.5em;
	}
}

.image-cards {
	width: 180px;
	height: auto;
	margin-left: 52px;
	margin-top: 8px;
}

/* Additional styling for the buttons within the .pharmacy-button container */
.pharmacy-box {
	display: inline-flex;
	gap: 17%;
	margin-bottom: 17px;
	justify-content: center; /* Center the buttons horizontally */
	width: 71%;
	margin-left: 215px;
	padding: 10px 20px;
	background-color: #9ccae98a;
	border-radius: 5px;
	box-shadow: 0 4px 8px 0 rgb(0 0 0/ 81%);
	text-align: center;
}

/* Hover effect for buttons */
.pharmacy-box:hover {
	background-color: #297fb8;
}

.custom-button {
	background-color: #4CAF50; /* Green background */
	border: none; /* Remove borders */
	color: white; /* White text */
	padding: 6px 12px; /* Padding inside the button */
	text-align: center; /* Center text */
	text-decoration: none; /* Remove underline */
	display: inline-block; /* Display as inline-block for proper spacing */
	font-size: 16px; /* Font size */
	cursor: pointer; /* Cursor style */
	border-radius: 5px; /* Rounded corners */
}

/* Hover effect */
.custom-button:hover {
	background-color: #45a049; /* Darker green on hover */
}

.popup {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
}

.popup-content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	text-align: center;
}

.close {
	position: absolute;
	top: 10px;
	right: 10px;
	font-size: 20px;
	cursor: pointer;
}

.add {
	background-color: #006dcc;
	border: none;
	color: white;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	cursor: pointer;
	border-radius: 5px;
	border: none;
}

.add:hover {
	background-color: #004c8f;
}
</style>
<script>
	$(document).ready(function() {
		$(".menu-button").click(function() {
			$(".menu-bar").toggleClass("open");
			$(".menu-bar1").removeClass("open"); // Close the other menu if it's open
		});

		$(".active").click(function() {
			$(".menu-bar1").toggleClass("open");
			$(".menu-bar").removeClass("open"); // Close the other menu if it's open
		});

		$(document).click(function(e) {
			// Collapse the menus when clicking anywhere on the document
			if (!$(e.target).closest('.menu, .menu-bar, .active').length) {
				$(".menu-bar").removeClass("open");
				$(".menu-bar1").removeClass("open");
			}
		});
	});
	function openPopup() {
		var popup = document.getElementById("customPopup");
		popup.style.display = "block";
	}

	function closePopup() {
		var popup = document.getElementById("customPopup");
		popup.style.display = "none";
	}
</script>
</head>
<body>

	<div id="customPopup" class="popup" style="display: none;">
		<div class="popup-content">
			<span class="close" onclick="closePopup()">×</span><br /> <a
				href="AddMedicine.jsf" class="add">Add Medicines</a>
			&nbsp;&nbsp;&nbsp;&nbsp; <a href="AddEquipments.jsf" class="add">Add
				Equipments</a>
		</div>
	</div>
	<ul class="menu">

		<li title="home"><a href="#" class="menu-button home">menu</a></li>

		<li title="insurance"><a href="#" class="search">insurances</a></li>
		<li title="login"><a href="#" class="active about">Login</a></li>
		<li title="archive"><a href="#" class="archive">archive</a></li>
		<li title="contact"><a href="contact.jsp" class="contact">contact</a></li>
	</ul>

	<ul class="menu-bar">
		<li><a href="PharmacyEnrollment.jsp" class="menu-button">Pharmacy</a></li>
		<li><a href="Provider.jsp">Provider</a></li>
		<li><a href="Recipient.jsp">Patient</a></li>
		<li><a href="#">Insurance</a></li>
	</ul>
	<ul class="menu-bar1">
		<li><a href="PharmaLogin.jsp" class="menu-button">Pharmacy
				Login</a></li>
		<li><a href="PharmaLogin.jsp">Provider Login</a></li>
		<li><a href="PharmaLogin.jsp">Patient Login</a></li>
		<li><a href="PharmaLogin.jsp">Admin Login</a></li>
	</ul>

	<h2
		style="font-family: impact, sans-serif; text-transform: uppercase; color: #007bff; font-size: 4.4rem; margin-top: 10px; margin-left: 28%; text-shadow: 0 4px 26px #000; -webkit-text-stroke: 1px #000000ad;">
		<h:outputText value="#{PharmaName}" />
	</h2>
	<div style="position: fixed; right: 26px; top: 25px; display: grid;">
		<a href="ResetPassWordPharmacythroughOld.jsp" class="custom-button">Reset
			Password</a>
	</div>

	<div class="pharmacy-box">
		<button onclick="openPopup()" class="custom-button">Add Stocks</button>
		<a href="ShowMedicinesPagination.jsf" class="custom-button">View Stocks</a> 
		<a href="ShowPrescription.jsf" class="custom-button">Sales</a>
		<a href="SearchSales.jsf" class="custom-button">View Sales</a>
	</div>
	<h:commandButton action="#{enrollController.logout() }" value="Logout"
		styleClass="custom-button"
		style="background-color: red;text-decoration: none;margin: 10px;margin-left: 47.5%;" />




	<article class="grid-wrap">
		<div class="card">
			<div class="card-head">
				<img class="image-cards" src="babycare.png" alt="Image Alt Text"
					style="margin-bottom: 35px; margin-top: 35px;">
			</div>
			<div class="card-body">
				<h3>Baby Care</h3>
				<p>Embrace the Joy of Parenthood-Nurturing Every Precious
					Moment, Every Step of the Way</p>
			</div>
		</div>

		<div class="card">
			<div class="card-head">
				<img class="image-cards" src="HealthDrinksSupplements.png"
					alt="Image Alt Text">
			</div>
			<div class="card-body">
				<h3>Health Drinks / Supplements</h3>
				<p>Elevate Your Wellness Journey with Our Premium Selection of
					Health Drinks and Supplements</p>
			</div>
		</div>

		<div class="card">
			<div class="card-head">
				<img class="image-cards" src="HealthDevices.png"
					alt="Image Alt Text" style="margin-bottom: 17px; margin-top: 10px;">
			</div>
			<div class="card-body">
				<h3>Health Devices</h3>
				<p>Empower Your Well-being with Cutting-Edge Health Devices</p>
			</div>
		</div>
		<div class="card">
			<div class="card-head">
				<img class="image-cards" src="Ayurveda.png" alt="Image Alt Text"
					style="margin-bottom: 35px; margin-top: 35px;">
			</div>
			<div class="card-body">
				<h3>Ayurveda</h3>
				<p>Rediscover Holistic Harmony with the Timeless Wisdom of
					Ayurveda</p>
			</div>
		</div>
		<div class="card">
			<div class="card-head">
				<img class="image-cards" src="GeneralMedicine.png"
					alt="Image Alt Text" style="margin-bottom: 1x; margin-top: -48px;">
			</div>
			<div class="card-body">
				<h3>Generic Medicine</h3>
				<p>Explore a World of Trusted Solutions with Our Range of
					Generic Medicines</p>
			</div>
		</div>
		<div class="card">
			<div class="card-head">
				<img class="image-cards" src="glucoses.png" alt="Image Alt Text">
			</div>
			<div class="card-body">
				<h3>Glucometers / Test Strips</h3>
				<p>From Accurate Glucometers / Test Strips to Cutting-Edge
					Medical Machines</p>
			</div>
		</div>
	</article>
</body>
	</html>
</f:view>