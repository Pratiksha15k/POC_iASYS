<html>
<head>
<title>Test Bed Monitoring System</title>
</head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="./resources/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="./resources/css/style.css" type="text/css">
<link rel="stylesheet" href="./resources/css/lobibox.css" type="text/css">
<link rel="stylesheet" href="./resources/css/lobipanel.css" type="text/css">
<link rel="icon" href="./resources/images/AppLogo.bmp" type="image/gif" sizes="16x16">

<script src="./resources/js/angular.min.js" type="text/javascript"></script>
<script src="./resources/js/angular-ui-router.min.js"></script>
<script src="./resources/js/index.js" type="text/javascript"></script>
<script src="./resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="./resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="./resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="./resources/js/lobibox.js" type="text/javascript"></script>
<script src="./resources/js/lobipanel.js" type="text/javascript"></script>
<script type="text/javascript" src="./resources/js/loadingoverlay.min.js"></script>
<script type="text/javascript" src="./resources/js/xlsx.core.styles.min.js"></script>
<script src="./resources/angular/app.js"></script>
<script src="./resources/angular/service/TestBedMonitorService.js"></script>
<script src="./resources/angular/controller/TestBedMonitorController.js"></script>
<script src="./resources/angular/controller/TestBedInfoController.js"></script>

<script>
var spinner = {
		image: "",
		fontawesome: "fa fa-spinner fa-pulse fa-3x fa-fw",
		zIndex: 2147483647,
		fade: false,
		color: "rgba(0, 0, 0, 0.0)"
	};
</script>
<body class="container-fluid" style="overflow: none" id="indexpage">
	<div class="row" style="height: 10%">
		<div style="background-image: url(./resources/images/header.jpg);background-size: cover;
					border-width: 0px; height: 100% !important;overflow: hidden!important;">
		</div>
	</div>
	<div style="width: 80%; overflow: none;padding: 5% 5% 5% 25%;">
		<div class="panel panel-primary" style="height: 25%">
				<div class="panel-body">
					<p style="color: #9E9E9E; font-size: 20px; font-family: calibri;">
						<b id="logout">Logout  <span class="glyphicon glyphicon-log-out"></span></b>
					</p>
					<div class="panel-body"  style="padding: 0;">
						<div class="panel-body" style="padding-left: 15%;">
							<p>
								<b id="youloggedout">You are now logged out.</b>
							</p>
							<p>
								<span id="didntmean">Didn't mean to log out?</span> <a href="login" id="loginagain">Log in again.</a>
							</p>
						</div>
					</div>
				</div>
			</div>
	</div>
</body>
</html>