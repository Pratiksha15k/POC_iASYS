<title>Test Bed Monitoring System</title>

<script src="./resources/js/angular.min.js" type="text/javascript"></script>
<script src="./resources/js/angular-ui-router.min.js"></script>
<script src="./resources/js/webix.js" type="text/javascript"></script>
<script src="./resources/js/index.js" type="text/javascript"></script>
<script src="./resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="./resources/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="./resources/js/loadingoverlay.min.js"></script>
<script type="text/javascript" src="./resources/js/xlsx.core.styles.min.js"></script>

<link rel="stylesheet" href="./resources/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="./resources/css/webix.css" type="text/css">
<link rel="stylesheet" href="./resources/css/brixloader.css" type="text/css">
<link rel="stylesheet" href="./resources/css/brix.css" type="text/css">
<link rel="stylesheet" href="./resources/css/style.css" type="text/css">
<link rel="stylesheet" href="./resources/css/lobibox.css" type="text/css">

<!-- css required for lobipanel -->
<link rel="stylesheet" href="./resources/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="./resources/css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="./resources/css/jquery-ui.theme.min.css" type="text/css">
<link rel="stylesheet" href="./resources/css/lobipanel.min.css" type="text/css">
<link rel="icon" href="./resources/images/AppLogo.bmp" type="image/gif" sizes="16x16">

<!-- js required for lobipanel -->
<script src="./resources/js/jquery-ui.min.js" type="text/javascript"></script>
<script src="./resources/js/jquery.canvasjs.min.js" type="text/javascript"></script>
<script src="./resources/js/jquery.dd.min.js" type="text/javascript"></script>
<script src="./resources/js/jquery.ui.touch-punch.min.js" type="text/javascript"></script>
<script src="./resources/js/lobipanel.min.js" type="text/javascript"></script>

<script src="./resources/angular/testbed.js"></script>
<script src="./resources/angular/service/TestBedService.js"></script>
<script src="./resources/angular/controller/TestBedController.js"></script>
<script>
var testBedHostName = '<%= session.getAttribute("host") %>';
var testBedName = '<%= session.getAttribute("testbed") %>';
</script>
<!-- <body class="container-fluid" style="overflow: none"> -->
	<div style="border-width: 0px; height: 10% !important;overflow: hidden!important;padding-left: 15px;padding-right: 15px;">
		<div class="row" style="height: 100%">
			<%@ include file="header.jsp"%>
		</div>
	</div>
	<div ng-app="testBedApp" style="height: 90%; width: 100%; overflow: auto">
		<div ui-view></div>
	</div>
<!-- </body> -->
