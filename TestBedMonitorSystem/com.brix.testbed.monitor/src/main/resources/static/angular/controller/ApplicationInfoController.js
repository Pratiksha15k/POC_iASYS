/**
 * 
 */
userManagementApp.controller("ApplicationInfoController",['$scope','$timeout','UserManagementService',function($scope,$timeout,userManagementService) {
	$scope.applicationInfoGridConfig = {
			margin:0,
			id:"applicationInfo",
			rows:[{
				view : 'label',
				id : 'appInfoLabel',
				label : '<span id="appinfodata_id">Application Information</span>',
				css:"applicationInfo",
				inputWidth:100, 
				align:"left",
			},{
				view:"datatable", 
				id:"appInfo",
				tooltip : true,
				header : false,
				borderless : true,
				height: setHeightInPercentage(20),
				width: setWidthInPercentage(80),
				css:"brix_datatable_2 admindatatable" ,
				tooltip:true,
				select:true,
				scroll: false,
				columns:[
					{ id:"parameter",header:"",fillspace:true},
					{ id:"value",header:"",fillspace:true}
					],
					on : {
						"onAfterRender" : webix.once(function(){
							userManagementService.get("applicationInformation").then(function(response) {
								if(response!=null){
									if(response.returnCode == 1){
										var applicationInfo = response.result;
										$$('appInfo').clearAll();
										$$("appInfo").add({parameter: "<span id='version'>Version</span>", value: applicationInfo.version },1);
										$$("appInfo").add({parameter: "<span id='builder'>Build By</span>", value: applicationInfo.builder },3);
										$$("appInfo").add({parameter: "<span id='buildnumber'>Application Name</span>", value: applicationInfo.name},0);
										$$("appInfo").add({parameter: "<span id='builddate'>Build Date</span>", value: applicationInfo.buildDate},2);
									}
								}
							});
						})
					},
			}]
	}  

	webix.event(window, 'resize',function() {
		if($$("applicationInfo") !=undefined)
			$$("applicationInfo").adjust();
	});
}]);