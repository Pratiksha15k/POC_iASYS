/**
 * 
 */

userManagementApp.controller('TestBedOperationsController', ['$scope', '$http', 'UserManagementService', 
	function($scope, $http, userManagementService) {
	$scope.testBedGridConfig = {
			rows:[{
				view: 'toolbar',
				id : 'testbedtoolbar',
				css : 'admintoolbar',
				borderless : true,
				elements:[{
					view: 'button', 
					label: 'Create', 
					type: 'icon', 
					icon: 'plus', 
					autowidth: true,  
					css: 'brix_button customiconbutton', 
					id: 'createbtn',
					click: function(){
						$scope.createTestBedWindow();
					}
				},{
					view: 'button', 
					label: 'Edit', 
					type: 'icon', 
					icon: 'pencil', 
					autowidth: true,  
					css: 'brix_button customiconbutton', 
					id: 'editbtn',
					click: function(){
						var selectedRow = $$('testBedGrid').getSelectedItem();
						if(selectedRow != undefined && selectedRow.length!=0)
							$scope.editTestBedWindow();
					}
				},{
					view: 'button', 
					label: 'Delete', 
					type: 'icon', 
					icon: 'trash', 
					autowidth: true,  
					css: 'brix_button customiconbutton', 
					id: 'deletebtn',
					click: function(){
						var selectedRow = $$('testBedGrid').getSelectedItem();
						if(selectedRow != undefined && selectedRow.length != 0){
							Lobibox.confirm({
								title : "Confirmation",
								msg : "Are you sure to delete test bed?",
								buttons : {
									yes : {
										'class': 'lobibox-btn lobibox-btn-yes',
										text: "Yes",
										closeOnClick : true,
									},
									no : {
										'class': 'lobibox-btn lobibox-btn-no',
										text: "No",
										closeOnClick: true
									},
								},
								callback : function(lobibox,type,result){
									if(type == 'yes'){
										$$("testBedGrid").getFilter("name").value = "";
										$$("testBedGrid").getFilter("hostname").value = "";
										$$("testBedGrid").getFilter("description").value = "";
										$.LoadingOverlay("show", spinner);
										userManagementService.remove('./testbeds/testbed/testbedid/'+selectedRow.id).then(function(response) {
											if (response.returnCode == 1) {
												notifyBox('success', 'TestBed deleted successfully.', 'top right');
												$scope.getTestBeds();
											}else if(response.returnCode == 4){
												notifyBox('warning', 'TestBed is in use.', 'top right');
											}
										});
										$.LoadingOverlay("hide", spinner);
									}else if(type == 'no'){
										$.LoadingOverlay("hide", spinner);
									}
								}
							});
						}
					}
				}]	
			},{height:5},{
				id:'testBedGrid',			
				view:'datatable',
				borderless:true,
				select:true,
				height: setHeightInPercentage(70),
				//height :480,
				//width : 1100, 
				resizeColumn: { headerOnly:true },
				fixedRowHeight : false,
				scrollX: false,
				gravity:0.4,
				visibleBatch:1,
				css:'brix_datatable_2 admindatatable',
				columns:[
					{ id:"index", header: [{text: "Sr. No."}], sort: "int", batch: 1, fillspace : 1},
					{ id:"id", editor : "text", header:"Id", batch: 2,fillspace : 1},
					{ id:"name", editor:"popup", header:["TestBed Name",{content:"textFilter"}], batch: 1,fillspace : 2},
					{ id:"hostname", editor:"popup", header:["Host Name",{content:"textFilter"}], batch: 1,fillspace : 2},
					{ id:"description", editor:"popup", header:["Description",{content:"textFilter"}], batch: 1,fillspace :3}],
					on : {
						"onItemDblClick" : function(id, ev){
							var selectedRow = $$('testBedGrid').getSelectedItem();
							if(selectedRow != undefined && selectedRow.length!=0)
								$scope.editTestBedWindow();
						}, 
						"data->onStoreUpdated":function(){
							this.data.each(function(obj, i){
								obj.index = i+1;
							})
						},
						"onAfterRender" : webix.once(function() {
							$scope.getTestBeds();
						}),
						"onAfterFilter" : function(){
							var filteredRowId = $$("testBedGrid").getIdByIndex(0);
							if(filteredRowId != undefined){
								$$("testBedGrid").select(filteredRowId);
								$$('deleteBtn').enable();
								$$('editBtn').enable();
							}else{
								$$('deleteBtn').disable();
								$$('editBtn').disable();
							}
						},
						"onBeforeRender" : webix.once(function(){

						})				
					},
					gravity : 1,
			}]
	}


	$scope.createTestBedWindow = function(){
		webix.ui({ 
			view:"window",
			id:"addTestBedWindow",
			head : '<span id="addUserHead">Add Test Bed</span><span class="closeicon" id="closeTestBed">x</span>' ,
			height: setHeightInPercentage(40),
			width: setWidthInPercentage(35), 
			move:true, 
			resize:true,
			position:"center",  
			css : 'brix_window loggingModalCss',
			modal:true,
			headHeight:28,
			body:{
				rows : [{
					gravity : 9,
					view:"form",
					id:"addTestBedForm", 
					css:"brix_form user_add_form",
					scroll : "auto",
					scrollX : false,
					rows:[{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'testBedNameLabel',
							label : "<span class='brix_form_label'>Test Bed Name </span> <span style='color : red;'> *</span>",
							width: 180,
							minWidth:250,
						},{
							view : "text",
							id : "testbedname",
							name : "name",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("testbedname").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 256){
									$$("testbedname").define("invalidMessage","Test Bed name length must less than 256 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("testbedname").define("invalidMessage", "Test Bed name must contain some special charaters.");
											return false;
										}else{
											$$("testbedname").define("invalidMessage"," ");
										}
									}
									$$("testbedname").define("invalidMessage"," ");
									return true;
								}
							},
							on: {
								"onBlur" : function(){
									this.validate();
								}
							}
						},{
							width:10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'hostNameLabel',
							label : "<span class='brix_form_label'>Host Name</span><span style='color:red;'> *</span>",
							width: 180,
							minWidth : 250,
						},{
							view : "text",
							id : "hostname",
							name : "hostname",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("hostname").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 256){
									$$("hostname").define("invalidMessage","Host name length must less than 256 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("hostname").define("invalidMessage", "Host name must contain some special charaters.");
											return false;
										}else{
											$$("hostname").define("invalidMessage"," ");
										}
									}
									$$("hostname").define("invalidMessage"," ");
									return true;
								}
							},
							on: {
								"onBlur" : function(){
									this.validate();
								},
							}
						},{
							width : 10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'descriptionLabel',
							label : "<span class='brix_form_label'>Description</span>",
							width: 180,
							minWidth : 250,
						},{
							view : "text",
							name : "description",
							id : "description",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){ 
								value = value.trim();
								if(value.length > 1024){
									$$("description").define("invalidMessage", "Description must less than 1024 characters.");
									return false;
								}else{
									/*for(var i=0; i<specialCharArray.length; i++){
										if(value.indexOf(specialCharArray[i]) >= 0){
											$$("description").define("invalidMessage", "Description must contains somcharacters.");
											return false;
										}else{
											$$("description").define("invalidMessage"," ");
										}
									}*/
									$$("description").define('invalidMessage',' ');
									return true;
								}
							},
							on: {
								"onBlur" : function(){
									this.validate();
								}
							}
						},{
							width : 10
						}]
					}],
				},{
					cols : [{},{
						view:"button", 
						label:"<span id='userAddBut_id'>Save</span>", 
						css : 'brix_button customiconbutton',
						autowidth : true,
						id : 'add_button',
						type : 'icon',
						icon : 'save',
						click : function(){
							var testbedjson = $$("addTestBedForm").getValues();
							if($$('addTestBedForm').validate()){
								$.LoadingOverlay("show", spinner);
								userManagementService.create('testbeds/testbed', testbedjson).then(function(response){
									if(response.returnCode == 1){
										notifyBox('success', 'Test Bed Created Successfully.', 'top right');
										$scope.getTestBeds();
										closeWindows('addTestBedWindow','addTestBedForm',40,35);
									}else if(response.returnCode == 3){
										notifyBox('warning', 'Test Bed Already Exist.', 'top right');
									}
									$.LoadingOverlay("hide", spinner);
								});
							}
						}
					},{
						view:"button", 
						label:"<span id='canceladduser'>Cancel</span>", 
						css : 'brix_button customiconbutton',
						autowidth : true,
						id : 'cancelButton',
						type : 'icon',
						icon : 'close',
						//  gravity : 4.8,
						click : function(){
							closeWindows('addTestBedWindow','addTestBedForm',40,35);
						} 
					},{}]
				}]
			},
			/**
			 * Responsive Add User Window issue closed.
			 */
			on:{
				'onShow' : webix.once(function(){
					$$('addTestBedWindow').resize();
					$('#closeTestBed').click(function(){
						closeWindows('addTestBedWindow','addTestBedForm',40,35);
					});
				}),
				'onBeforeShow' : function(){},
			},
		}).show();
	}

	$scope.editTestBedWindow = function(){
		webix.ui({ 
			view:"window",
			id:"editTestBedWindow",
			head : '<span id="addUserHead">Add Test Bed</span><span class="closeicon" id="closeTestBed">x</span>' ,
			height: setHeightInPercentage(40),
			width: setWidthInPercentage(35), 
			move:true, 
			resize:true,
			position:"center",  
			css : 'brix_window loggingModalCss',
			modal:true,
			headHeight:28,
			body:{
				rows : [{
					gravity : 9,
					view:"form",
					id:"editTestBedForm", 
					css:"brix_form user_add_form",
					scroll : "auto",
					scrollX : false,
					rows:[{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'testBedNameLabel',
							label : "<span class='brix_form_label'>Test Bed Name </span> <span style='color : red;'> *</span>",
							width: 180,
							minWidth:250,
						},{
							view : "text",
							id : "testbedname",
							name : "name",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("testbedname").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 256){
									$$("testbedname").define("invalidMessage","Test Bed name length must less than 256 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("testbedname").define("invalidMessage", "Test Bed name must contain some special charaters.");
											return false;
										}else{
											$$("testbedname").define("invalidMessage"," ");
										}
									}
									$$("testbedname").define("invalidMessage"," ");
									return true;
								}
							},
							on: {
								"onBlur" : function(){
									this.validate();
								}
							}
						},{
							width:10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'hostnameLabel',
							label : "<span class='brix_form_label'>Host Name</span><span style='color:red;'> *</span>",
							width: 180,
							minWidth : 250,
						},{
							view : "text",
							id : "hostname",
							name : "hostname",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("hostname").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 256){
									$$("hostname").define("invalidMessage","Host name length must less than 256 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("hostname").define("invalidMessage", "Host name must contain some special charaters.");
											return false;
										}else{
											$$("hostname").define("invalidMessage"," ");
										}
									}
									$$("hostname").define("invalidMessage"," ");
									return true;
								}
							},
							on: {
								"onBlur" : function(){
									this.validate();
								},
							}
						},{
							width : 10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'descriptionLabel',
							label : "<span class='brix_form_label'>Description</span>",
							width: 180,
							minWidth : 250,
						},{
							view : "text",
							name : "description",
							id : "description",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){ 
								value = value.trim();
								if(value.length > 1024){
									$$("description").define("invalidMessage", "Description must less than 1024 characters.");
									return false;
								}else{
									/*for(var i=0; i<specialCharArray.length; i++){
										if(value.indexOf(specialCharArray[i]) >= 0){
											$$("description").define("invalidMessage", "Description must contains somcharacters.");
											return false;
										}else{
											$$("description").define("invalidMessage"," ");
										}
									}*/
									$$("description").define('invalidMessage',' ');
									return true;
								}
							},
							on: {
								"onBlur" : function(){
									this.validate();
								}
							}
						},{
							width : 10
						}]
					}],
				},{
					cols : [{},{
						view:"button", 
						label:"<span id='userAddBut_id'>Save</span>", 
						css : 'brix_button customiconbutton',
						autowidth : true,
						id : 'add_button',
						type : 'icon',
						icon : 'save',
						click : function(){
							var testbedjson = $$("editTestBedForm").getValues();
							if($$('editTestBedForm').validate()){
								$.LoadingOverlay("show", spinner);
								userManagementService.create('./testbeds/testbed/update/', testbedjson).then(function(response){
									if(response.returnCode == 1){
										notifyBox('success', 'Test Bed Updated Successfully.', 'top right');
										$scope.getTestBeds();
										closeWindows('editTestBedWindow','editTestBedForm',40,35);
									}
									$.LoadingOverlay("hide", spinner);
								});
							}
						}
					},{
						view:"button", 
						label:"<span id='canceladduser'>Cancel</span>", 
						css : 'brix_button customiconbutton',
						autowidth : true,
						id : 'cancelButton',
						type : 'icon',
						icon : 'close',
						//  gravity : 4.8,
						click : function(){
							closeWindows('editTestBedWindow','editTestBedForm',40,35);
						} 
					},{}]
				}]
			},
			/**
			 * Responsive Add User Window issue closed.
			 */
			on:{
				'onShow' : webix.once(function(){
					$$('editTestBedWindow').resize();
					$('#closeTestBed').click(function(){
						closeWindows('editTestBedWindow','editTestBedForm',40,35);
					});
				}),
				'onBeforeShow' : function(){
					var selectedRow = $$("testBedGrid").getSelectedItem();
					$$("editTestBedForm").setValues(selectedRow);
				},
			},
		}).show();
	}

	$scope.getTestBeds = function(){
		$.LoadingOverlay("show", spinner);
		userManagementService.get('testbeds/testbed').then(function(response){
			if(response.returnCode == 1){
				$$("testBedGrid").clearAll();
				$$("testBedGrid").refresh();
				$$("testBedGrid").parse(response.result);
				testBedList = response.result;
				if($$("testBedGrid").serialize().length != 0)
					$$("testBedGrid").select($$("testBedGrid").getFirstId());
			}
			$.LoadingOverlay("hide", spinner);
		});
	};
}]);