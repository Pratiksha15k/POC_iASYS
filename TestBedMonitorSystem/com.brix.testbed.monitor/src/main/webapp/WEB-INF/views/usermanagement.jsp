<title>Test Bed Monitoring System</title>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="./resources/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="./resources/css/webix.css" type="text/css">
<link rel="stylesheet" href="./resources/css/style.css" type="text/css">
<link rel="stylesheet" href="./resources/css/brixloader.css" type="text/css">
<link rel="stylesheet" href="./resources/css/brix.css" type="text/css">
<link rel="stylesheet" href="./resources/css/usermanagement.css" type="text/css">
<link rel="stylesheet" href="./resources/css/lobibox.css" type="text/css">
<link rel="stylesheet" href="./resources/css/lobipanel.css" type="text/css">
<link rel="icon" href="./resources/images/AppLogo.bmp" type="image/gif" sizes="16x16">

<script src="./resources/js/angular.min.js" type="text/javascript"></script>
<script src="./resources/js/angular-ui-router.min.js"></script>
<script src="./resources/js/webix.js" type="text/javascript"></script>
<script src="./resources/js/index.js" type="text/javascript"></script>
<script src="./resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="./resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="./resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="./resources/js/lobiboxes.js" type="text/javascript"></script>
<script src="./resources/js/lobibox.js" type="text/javascript"></script>
<script src="./resources/js/lobipanel.js" type="text/javascript"></script>
<script type="text/javascript" src="./resources/js/loadingoverlay.min.js"></script>
<script type="text/javascript" src="./resources/js/xlsx.core.styles.min.js"></script>
<script src="./resources/angular/usermanagement.js"></script>
<script src="./resources/angular/service/UserManagementService.js"></script>
<script src="./resources/angular/controller/UserController.js"></script>
<script src="./resources/angular/controller/RoleController.js"></script>
<script src="./resources/angular/controller/TestBedOperationsController.js"></script>
<script src="./resources/angular/controller/ApplicationInfoController.js"></script>
<script type="text/javascript">
	var spinner = {
		image : "",
		fontawesome : "fa fa-spinner fa-pulse fa-3x fa-fw",
		zIndex : 2147483647,
		fade : false,
		color : "rgba(0, 0, 0, 0.0)"
	};
</script>
<div style="border-width: 0px; height: 10% !important;overflow: hidden!important;padding-left: 15px;padding-right: 15px;">
		<div class="row" style="height: 100%">
			<%@ include file="header.jsp"%>
		</div>
	</div>
<div ng-app="UserManagementApp">
	<div class="adminheader"><span style="margin-left: 1%;">Administration</span></div>
	<div class="col-sm-12 col-md-12 col-lg-12">
		<div class="col-sm-2 col-md-2 col-lg-2 adminsidebar">
			<ul class="nav nav-pills nav-stacked adminnav">
				<li role="presentation" ui-sref-active="active">
					<a ui-sref="user"><span id='userListItem'>User</span></a>
				</li>
				<li role="presentation" ui-sref-active="active">
					<a ui-sref="role"><span id='roleListItem'>Role</span></a>
				</li>
				<li role="presentation" ui-sref-active="active">
					<a ui-sref="testbed"><span id='testbedListItem'>TestBed</span></a>
				</li>
				<li role="presentation" ui-sref-active="active">
					<a ui-sref="applicationinfo"><span id='appicationInfoListItem'>Application Information</span></a>
				</li>
			</ul>
		</div>
		<div class="col-sm-10 col-md-10 col-lg-10">
			<div ui-view></div>
		</div>
	</div>
</div>