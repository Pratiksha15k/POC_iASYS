<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Bed Monitoring System</title>
</head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css"	type="text/css">
<link rel="stylesheet" href="./resources/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="./resources/css/style.css" type="text/css">
<link rel="stylesheet" href="./resources/css/lobibox.css" type="text/css">
<link rel="stylesheet" href="./resources/css/lobipanel.css"	type="text/css">
<link rel="icon" href="./resources/images/AppLogo.bmp" type="image/gif"	sizes="16x16">

<script src="./resources/js/angular.min.js" type="text/javascript"></script>
<script src="./resources/js/angular-ui-router.min.js"></script>
<script src="./resources/js/index.js" type="text/javascript"></script>
<script src="./resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="./resources/js/bootstrap.min.js" type="text/javascript"></script>
<script	src="./resources/js/loadingoverlay.min.js" type="text/javascript"></script>
<script src="./resources/js/lobibox.js" type="text/javascript"></script>
<script src="./resources/js/lobipanel.js" type="text/javascript"></script>

<script type="text/javascript">
var spinner = {
		image : "",
		fontawesome : "fa fa-spinner fa-pulse fa-3x fa-fw",
		zIndex : 2147483647,
		fade : false,
		color : "rgba(0, 0, 0, 0.0)"
	};
	$.LoadingOverlay("show", spinner);
	$(window).bind("load", function() {
	$.LoadingOverlay("hide", spinner);
});
</script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body class="container-fluid img-rounded loginImage" style="overflow: hidden;
	background-image: url(./resources/images/login_screen_edited.png);" id="loginpage">
		<div class="row" style="height: 10%">
			<div style="background-image: url(./resources/images/header.jpg);background-size: cover;
					border-width: 0px; height: 100% !important;overflow: hidden!important;">
		</div>
		</div>
		<div class="row" ng-app>
			<div class="col-xs-6 col-md-6 col-lg-6"></div>
			<div class="col-xs-6 col-md-6 col-lg-6">
				<div class="loginFormContainer">
					<!-- main app container -->
					
					<form class="loginform" name="loginForm" method="post" action="/tmsapp/login" novalidate>
						<div class="form-group">
							<label>Username</label> <input type="text" class="form-control"
								ng-model="user.userName" ng-required="true" name="username"
								id="username" /> <span style="color: red;"
								ng-show="loginForm.username.$touched && loginForm.username.$error.required">Username
								is required.</span>
						</div>
						<div class="form-group">
							<label>Password</label> <input type="password"
								class="form-control" ng-model="user.password" ng-required="true" name="password" id="password" /> 
								<span style="color: red;" ng-show="loginForm.password.$touched && loginForm.password.$error.required">Password is required.</span>
						</div>
						<c:if test="${not empty errorMessge}"><div class="form-group" style="color:red;">${errorMessge}</div></c:if>
						<div class="form-group">
							<button class="btn btn-primary">Login</button>
						</div>
					</form>
					
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</div>
			</div>
		</div>
</body>