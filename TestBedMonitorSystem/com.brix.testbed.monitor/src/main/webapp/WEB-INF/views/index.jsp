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
			<%@ include file="header.jsp"%>
		</div>
	<div ng-app="testBedMonitorApp"
		style="height: 90%; width: 100%; overflow: none">
		<div ui-view></div>
	</div>
</body>
</html>