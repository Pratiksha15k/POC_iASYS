<!-- div class="col-sm-2 col-md-2 col-lg-2" style="padding: 0;">
				<input type="image" id="appLogoImg" src="./resources/images/AppLogo.bmp" height="108%">
			</div>
			<div class="col-sm-10 col-md-10 col-lg-10" style="padding: 0;">
				<div class="row" style="height: 60%;background-color: white;font-size: x-large;font-weight: 600;
    									color: #176d59;line-height: 1.5;margin:0;">
					<div class="col-sm-4 col-md-4 col-lg-4">
						Test Bed Monitoring System
					</div>
					<div class="col-sm-8 col-md-8 col-lg-8" style="height: 86%;background-color: white;">
						<input type="image" id="companyLogoImg" src="./resources/images/iasys.png"
							style="height: 100%;margin-left: 89%;">
					</div>
				</div>
				<div class="row" style="background-color: #176d59;"></div>
			</div> -->
			
			
			
<script type="text/javascript" src="./resources/js/header.js"></script>		
<script type="text/javascript">
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();  
});
var spinner = {
	image : "",
	fontawesome : "fa fa-spinner fa-pulse fa-3x fa-fw",
	zIndex : 2147483647,
	fade : false,
	color : "rgba(0, 0, 0, 0.0)"
};
</script>

<div class="headerImage" style="background-image: url(./resources/images/header.jpg);">
	<div class="row" id="headerempty" style="height: 70%;"></div>
	<div class="row" id="headertoolbar">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<div class="col-sm-10 col-md-10 col-lg-10"></div>
			<div class="col-sm-2 col-md-2 col-lg-2">
				<a target="_blank" href="logout" style="float: right; font-size: large;color: white;"
				 data-toggle="tooltip" data-placement="left" title="LogOut" id="logoutIcon">
				<span class="fa fa-sign-out"></span></a>
				<a target="_blank" href="usermanagement" style="float: right; font-size: large;color: white;margin-right: 10%"
				 data-toggle="tooltip" data-placement="left" title="Administrator" id="admininstratorIcon">
				<span class="fa fa-cog"></span></a>
			</div>
		</div> 
	</div>
</div>