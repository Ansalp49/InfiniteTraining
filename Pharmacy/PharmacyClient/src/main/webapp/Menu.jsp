<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:p="http://primefaces.org/ui">
<h:head>
	<title>MenuBar</title>
	<link rel="stylesheet" href="menuBass.css" />
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.min.css" />
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

	<script>
		function search() {
			var searchTerm = document.getElementById('search-input').value
					.trim().toLowerCase();
			var searchResultsContainer = document
					.getElementById('search-results');
			searchResultsContainer.innerHTML = '';

			var itemsToSearch = [ {
				name : 'Pharmacy',
				link : 'PharmacyEnrollment.jsp'
			}, {
				name : 'Insurance',
				link : 'https://example.com/item2'
			}, {
				name : 'Admin',
				link : 'Admin.jsp'
			}, {
				name : 'Recipient',
				link : 'Recipient.jsp'
			}, {
				name : 'Pharmacy Login',
				link : 'PharmaLogin.jsf'
			}, {
				name : 'Admin Login',
				link : 'LoginPage.jsp'
			}, {
				name : 'Recipient Login',
				link : 'LoginPage.jsp'
			}, {
				name : 'Contact',
				link : 'contact.jsp'
			} ];

			itemsToSearch.forEach(function(item) {
				if (item.name.toLowerCase().includes(searchTerm)) {
					var resultItem = document.createElement('div');
					resultItem.className = 'result-item';
					resultItem.textContent = item.name;

					// Handle click event to navigate to the link
					resultItem.addEventListener('click', function() {
						window.location.href = item.link;
					});

					searchResultsContainer.appendChild(resultItem);
				}
			});

			// Display the results container
			searchResultsContainer.style.display = 'block';
		}

		// Hide the results container on clicks outside the input and results
		document.addEventListener('click', function(event) {
			var searchInput = document.getElementById('search-input');
			var searchResultsContainer = document
					.getElementById('search-results');

			if (event.target !== searchInput) {
				searchResultsContainer.style.display = 'none';
			}
			if (event.target !== searchResultsContainer) {
				searchResultsContainer.style.display = 'none';
			}
		});

		/*     // Automatically hide the results container on pressing Enter key
		 document.getElementById('search-input').addEventListener('keypress', function (event) {
		 if (event.key === 'Enter') {
		 var searchResultsContainer = document.getElementById('search-results');
		 searchResultsContainer.style.display = 'none';
		 }
		 });

		 // Show search results on input
		 document.getElementById('search-input').addEventListener('input', search); */
	</script>
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

		function showLoader() {
			document.getElementById('loader').style.display = 'block';
		}
		$(document).ready(function() {
			$(".loader").fadeOut("slow");
		});
	</script>
	<style>
#search-input {
	background:
		url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="%23424242" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8" stroke="%23424242"/><line x1="21" y1="21" x2="16.65" y2="16.65" stroke="%23424242"/></svg>')
		no-repeat 95% center;
	width: 600px;
	padding: 10px;
	margin: 20px auto;
	margin-right: 24%;
	box-sizing: border-box;
	border: 2px solid #04040461;
	border-radius: 5px;
	outline: none;
	transition: border-color 0.3s, box-shadow 0.3s;
	display: block;
	font-size: 16px;
	box-shadow: 0 0 10px rgb(0 0 0/ 68%);
}

#search-results {
	position: absolute;
	width: 600px;
	background-color: #e1d7d7;
	margin-left: 492px;
	font-family: 'Raleway', Helvetica, sans-serif;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	border: 1px solid #ccc;
	border-radius: 5px;
	z-index: 1;
	display: none;
	top: 24%; /* Place it directly under the input field */
}

.result-item {
	padding: 8px;
	cursor: pointer;
}

.result-item:hover {
	background-color: #f5f5f5;
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
</style>

</h:head>
<h:body>

	<style>
.health-quotes {
	width: 100%; /* Adjust the width as needed */
	overflow: hidden;
	white-space: nowrap;
}

.health-quotes p img {
	max-width: 32px; /* Adjust the maximum width of the images */
	vertical-align: middle;
	margin-right: 10px; /* Adjust spacing between image and text */
}

.health-quotes p {
	font-weight: 700;
	font-family: monospace;
	display: inline-block;
	margin-right: 40px; /* Adjust spacing between quotes */
	animation: marquee 15s linear infinite;
}

@keyframes marquee { 0% {
	transform: translateX(100%);}100%{
transform:translateX(-100%);}}

.articles {
	font-family: "Montserrat", Sans-serif;
	line-height: 26px;
}
</style>

	<div style="display: flex">

		<input id="search-input" oninput="search()" placeholder="Search"
			type="text" />
		<div id="search-results"></div>
		<div class="image-cont">
			<a href="Menu.jsp"> <img src="phar.png" alt="Website Logo"
				width="100" height="100"
				style="filter: drop-shadow(0px 0px 10px black);" />
			</a>
		</div>
	</div>
	<div class="health-quotes">
		<p>
			<img src="medicine_647237.png" alt="Medicine Icon" />"Take your
			medicine as prescribed."
		</p>
		<p>
			<img src="lazy_5692446.png" alt="Medicine Icon" />"Get plenty of
			rest for a healthy life."
		</p>
		<p>
			<img src="physical-activity_4310355.png" alt="Medicine Icon" />"Don't
			forget to exercise regularly."
		</p>
		<p>
			<img src="immune_9558188.png" alt="Medicine Icon" />"Take breaks,
			avoid prolonged sitting."
		</p>
		<p>
			<img src="heart_508735.png" alt="Medicine Icon" />"A healthy outside
			starts from the inside."
		</p>
		<p>
			<img src="hand-dryer_5610168.png" alt="Medicine Icon" />"Practice
			good hygiene for a healthier you."
		</p>
	</div>

	<ul class="menu">

		<li title="home"><a href="#" class="menu-button home">menu</a></li>
		<li title="login"><a href="#" class="active about">Login</a></li>
		<li title="archive"><a href="#" class="archive">archive</a></li>
		<li title="contact"><a href="contact.jsp" class="contact">contact</a></li>
	</ul>

	<ul class="menu-bar">
		<li><a href="PharmacyEnrollment.jsp" class="menu-button"
			onclick="showLoader()">Pharmacy</a></li>
		<li><a href="Provider.jsp">Provider</a></li>
		<li><a href="Recipient.jsp">Patient</a></li>
		<li><a href="#">Insurance</a></li>
	</ul>
	<ul class="menu-bar1">
		<li><a href="PharmaLogin.jsf" class="menu-button">Pharmacy
				Login</a></li>
		<li><a href="LoginPage.jsp">Provider Login</a></li>
		<li><a href="LoginPage.jsp">Patient Login</a></li>
		<li><a href="LoginPage.jsp">Admin Login</a></li>
	</ul>



	<div class="card-container">
		<div class="card">
			<div class="card-header">Get 15% off on Medicines</div>
			<div class="card-body">
				<a href="#"
					style="color: black; text-decoration: none; font-weight: 600;">
					Get flat 150/- off on Bills* </a>

			</div>
		</div>
		<div class="card">
			<div class="card-header">Hospital Visit</div>
			<div class="card-body">
				<a href="#"
					style="color: black; text-decoration: none; font-weight: 600;">
					Confirm appointment </a>
			</div>
		</div>
		<div class="card">
			<div class="card-header">Video Consult</div>
			<div class="card-body">
				<a href="#"
					style="color: black; text-decoration: none; font-weight: 600;">
					Connect with Doctors </a>
			</div>

		</div>
	</div>
	<header style="width:80%; margin-left: 12%;box-shadow: 0 0 10px rgb(0 0 0 / 68%);">
	<h1>
		Streamlining Healthcare Operations:<br /> The Role of Hospital
		Management Systems
	</h1>
	</header>

	<article class="articles"> <section>
	<h2>Understanding Hospital Management Systems</h2>
	<p class="scroll-p">Hospital Management Systems are comprehensive
		software solutions designed to automate and streamline the day-to-day
		operations of a healthcare facility. From patient registration and
		appointment scheduling to billing and inventory management, HMS
		integrates various functions into a unified platform.</p>
	</section> <section>
	<h2>Key Features and Benefits</h2>
	<ul>
		<li><strong>Patient Information Management:</strong> HMS enables
			healthcare providers to maintain accurate and up-to-date patient
			records. From demographics to medical history, all information is
			easily accessible, ensuring quick and informed decision-making.</li>
		<li><strong>Appointment Scheduling:</strong> Efficient
			appointment scheduling is critical for managing patient flow. HMS
			allows staff to schedule, reschedule, and track appointments
			seamlessly, reducing waiting times and improving patient
			satisfaction.</li>
		<li><strong>Billing and Invoicing:</strong> HMS automates the
			billing process, generating accurate invoices for services rendered.
			This not only reduces billing errors but also enhances financial
			transparency for both patients and the hospital.</li>
		<li><strong>Inventory Management:</strong> Tracking and managing
			medical supplies and equipment is simplified with HMS. Automated
			alerts for low stock levels prevent shortages, ensuring that
			healthcare providers have the necessary resources at all times.</li>
		<li><strong>Electronic Health Records (EHR):</strong> The
			transition from paper records to electronic health records has been a
			transformative aspect of HMS. EHRs streamline data retrieval,
			minimize errors, and support better collaboration among healthcare
			professionals.</li>
		<li><strong>Analytics and Reporting:</strong> Hospital
			administrators can leverage analytics tools within HMS to gain
			insights into hospital performance. Data-driven decisions contribute
			to resource optimization and improved patient outcomes.</li>
	</ul>
	</section> </article>
	<style>
.containers {
	display: flex;
	gap: 10px;
	margin-left: 19%;
}

.sections {
	background-color: #bec7e9;
	padding: 10px;
	text-align: center;
	border: 1px solid #000;
	border-radius: 8px;
	/* background-color: #4082895c; */
	width: 25%;
	box-shadow: 0 0 10px rgb(0 0 0/ 68%);
}

h2 {
	font-size: 24px;
	margin-bottom: 10px;
}
</style>

	<div class="containers">
		<div class="sections" id="patients">
			<h2>Patients</h2>
			<p>122250+</p>
		</div>
		<div class="sections" id="departments">
			<h2>Departments</h2>
			<p>22+</p>
		</div>
		<div class="sections" id="doctors">
			<h2>Doctors</h2>
			<p>40+</p>
		</div>
	</div>

	<article class="articles"> <section>
	<h2>Implementing HMS for Enhanced Patient Care</h2>
	<ul>
		<li><strong>Improved Efficiency:</strong> HMS reduces manual
			paperwork and administrative burdens, allowing healthcare
			professionals to allocate more time to patient care. Streamlined
			processes lead to faster service delivery and increased overall
			efficiency.</li>
		<li><strong>Enhanced Patient Experience:</strong> From seamless
			appointment scheduling to simplified billing processes, patients
			experience a higher level of convenience and satisfaction. This
			positive experience contributes to patient loyalty and trust.</li>
		<li><strong>Better Decision-Making:</strong> Access to real-time
			data and analytics empowers hospital administrators to make informed
			decisions. Whether optimizing resource allocation or identifying
			areas for improvement, data-driven insights are invaluable.</li>
		<li><strong>Ensuring Compliance:</strong> Hospital Management
			Systems often come equipped with features that ensure compliance with
			healthcare regulations. This not only reduces legal risks but also
			fosters a culture of accountability within the organization.</li>
	</ul>
	</section> <section>
	<h2>Additional Features and Advancements</h2>
	<p>As technology continues to evolve, Hospital Management Systems
		incorporate additional features and advancements to further enhance
		healthcare operations:</p>
	<ul>
		<li><strong>Interoperability:</strong> One of the key
			advancements in modern Hospital Management Systems is their ability
			to seamlessly integrate with other healthcare systems.
			Interoperability ensures a smooth exchange of patient information
			between different departments and external entities, promoting
			collaboration and continuity of care.</li>
		<li><strong>Mobile Accessibility:</strong> Many HMS solutions now
			offer mobile applications, allowing healthcare providers to access
			patient data, schedule appointments, and manage tasks on-the-go. This
			feature enhances the flexibility of healthcare professionals and
			contributes to more efficient patient care.</li>
		<li><strong>Telemedicine Integration:</strong> With the rise of
			telemedicine, integrating telehealth features into Hospital
			Management Systems has become essential. This integration allows
			healthcare providers to conduct virtual consultations, monitor
			patients remotely, and manage telehealth appointments within the same
			system.</li>
		<li><strong>Security Measures:</strong> Given the sensitivity of
			patient data, security is a paramount concern in healthcare. Hospital
			Management Systems employ robust security measures, including
			encryption and access controls, to safeguard patient information from
			unauthorized access and cyber threats.</li>
		<li><strong>Patient Portal:</strong> Many HMS solutions include
			patient portals, providing individuals with secure access to their
			health records, test results, and appointment schedules. Patient
			portals enhance communication between patients and healthcare
			providers, fostering a more collaborative approach to healthcare.</li>
		<li><strong>Cost Savings:</strong> While the initial investment
			in a Hospital Management System may seem substantial, the long-term
			cost savings are significant. Automation of processes, reduction in
			paperwork, and improved resource utilization contribute to overall
			cost-effectiveness in the long run.</li>
	</ul>
	</section> </article>

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
</h:body>
</html>
