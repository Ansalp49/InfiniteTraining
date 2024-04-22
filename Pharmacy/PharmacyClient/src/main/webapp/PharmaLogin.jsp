<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<f:view>
	<html>
<head>
<title>Pharmacy Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">


<meta name="robots" content="noindex, follow">

</head>
<h:form id="form">
	<div class="limiter">
		<div class="container-login100"
			style="background-image: url('image/bg-01.jpg');">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
				<div class="login100-form validate-form">
					<span class="login100-form-title p-b-49"> Pharmacy Login </span>
					<div class="wrap-input100 validate-input m-b-23"
						data-validate="Username is reauired">
						<h5 style="color: #666; margin-bottom: 5%;">Email</h5>
						<i class="fa fa-user"></i>
						<h:inputText id="username" value="#{pharmacyLogin.email}" />
					</div>
					<h:message for="username" style="color: red;"></h:message>
					<div class="wrap-input100 validate-input m-b-23"
						data-validate="Password is required">
						<h5 style="color: #666; margin-bottom: 5%;">Password</h5>
						<i class="fa fa-lock"></i>
						<div class="icon">
							<i class="fa fa-eye" id="showPass"></i> <i
								class="fa fa-eye-slash" id="showPassSlash"
								style="display: none;"></i>
						</div>
						<div id="passInputWrapper">
							<h:inputSecret id="passInput" value="#{pharmacyLogin.password}" />
						</div>
					</div>
					<h:message for="passInput" style="color: red;"></h:message>

					<div class="text-right p-t-8 p-b-31">
						<a href="#"> Forgot password? </a>
					</div>
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<!-- <button class="login100-form-btn">
                                Login
                            </button> -->

							<h:commandButton action="#{mainImpl.loginDao(pharmacyLogin)}"
								value="Login"
								style="height:35px;
                                 outline: none;
                                  border: none;
                                  position: relative;
                                  background-color: rgba(0,0,0,-0.5);
                                     color: white;
                                  font-size: 20px;" />
						</div>
						<h:outputText value="#{passWordErr}" style="color: red;" />
					</div>
					<div class="text-right p-t-8 p-b-31">
						<a href="#"> Dont't have an account?Sign In. </a>
						<h:commandLink action="LoginThOtp">
							<h:outputText value="Login with Otp"></h:outputText>
						</h:commandLink>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>
	<script>
		$(document).ready(function() {
			$('#showPass, #showPassSlash').on('click', function() {
				var passInput = $("#passInputWrapper input");
				var showPassIcon = $("#showPass");
				var showPassSlashIcon = $("#showPassSlash");

				if (passInput.attr('type') === 'password') {
					passInput.attr('type', 'text');
					showPassIcon.hide();
					showPassSlashIcon.show();
				} else {
					passInput.attr('type', 'password');
					showPassIcon.show();
					showPassSlashIcon.hide();
				}
			});
		});
	</script>
</h:form>

	</html>
</f:view>