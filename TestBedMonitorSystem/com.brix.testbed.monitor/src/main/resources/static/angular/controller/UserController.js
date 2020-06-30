/**
 * 
 */         
var testBedList;
var rolesList;
userManagementApp.controller('UserController', ['$scope', '$http', 'UserManagementService', 
	function($scope, $http, userManagementService) {
	$scope.userGridConfig = {
			rows:[{
				view: 'toolbar',
				id : 'usertoolbar',
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
						$scope.createUserWindow();
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
						var selectedRow = $$("userGrid").getSelectedItem();
						if(selectedRow != undefined && selectedRow.length!=0)
							$scope.editUserWindow();
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
						var selectedRow = $$('userGrid').getSelectedItem();
						var username = selectedRow.userName;
						if(selectedRow != undefined && selectedRow.length != 0){
							Lobibox.confirm({
								title : "Confirmation",
								msg : "Are you sure to delete user?",
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
										$$("userGrid").getFilter("userName").value = "";
										$$("userGrid").getFilter("name").value = "";
										$$("userGrid").getFilter("surName").value = "";
										$$("userGrid").getFilter("emailId").value = "";
										$$("userGrid").getFilter("mobileNumber").value = "";
										$$("userGrid").getFilter("department").value = "";
										//$$("userGrid").getFilter("createdDate").value = "";
										//$$("userGrid").getFilter("updatedDate").value = "";
										$.LoadingOverlay("show", spinner);
										userManagementService.remove('./user/userid/'+selectedRow.id+'/username/'+username).then(function(response) {
											if (response.returnCode == 1) {
												notifyBox('success', 'User deleted successfully.', 'top right');
												$scope.getUsers();
											}else if(response.returnCode == 4) {
												notifyBox('warning', 'Logged in user can not be deleted.', 'top right');
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
				id:'userGrid',			
				view:'datatable',
				borderless:true,
				select:true,
				height: setHeightInPercentage(70),
				//height :485,
				//width : 1100, 
				resizeColumn: { headerOnly:true },
				fixedRowHeight : false,
				scrollX: true,
				gravity:0.4,
				visibleBatch:1,
				css:'brix_datatable_2 admindatatable',
				columns:[
					{ id:"index", header: [{text: "Sr. No."}], sort: "int", batch: 1, width : 100},
					{ id:"id", editor : "text", header:"Id", batch: 2,width : 100},
					{ id:"userName", editor : "text", header:["User Name", {content:"textFilter"}], batch: 1 , width : 150},
					{ id:"name", editor:"popup", header:["First Name",{content:"textFilter"}], batch: 1, width : 150},
					{ id:"surName", editor:"popup", header:["Last Name",{content:"textFilter"}], batch: 1, width : 150},
					{ id:"emailId", header:["Email",{content:"textFilter"}], batch: 1, width : 250},
					{ id:"mobileNumber", header:["Contact",{content:"textFilter"}], batch: 1, width : 150},
					//{ id:"createdDate", header:["Created Date",{content:"textFilter"}], batch: 1,width : 150},
					//{ id:"updatedDate", header:["Updated Date",{content:"textFilter"}], batch: 1,width : 150},
					{ id:"department", header:["Department",{content:"textFilter"}], batch: 1, width : 150},
					{ id:"testbeds", header:["TestBeds",{content:"textFilter"}], batch: 1, width : 220},
					{ id:"testbedsid", header:["TestBeds",{content:"textFilter"}], batch: 2,fillspace :1.5},
					{ id:"roles", header:["Roles",{content:"textFilter"}], batch: 1, width : 220},
					{ id:"rolesid", header:["Roles",{content:"textFilter"}], batch: 2,fillspace :1.5}], 
					on : {
						"onItemDblClick" : function(id, ev){
							var selectedRow = $$("userGrid").getSelectedItem();
							if(selectedRow != undefined && selectedRow.length!=0)
								$scope.editUserWindow();
						}, 
						"data->onStoreUpdated":function(){
							this.data.each(function(obj, i){
								obj.index = i+1;
							})
						},
						"onAfterRender" : webix.once(function() {
							$scope.getUsers();
						}),
						"onAfterFilter" : function(){
							var filteredRowId = $$("userGrid").getIdByIndex(0);
							if(filteredRowId != undefined){
								$$("userGrid").select(filteredRowId);
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

	$scope.createUserWindow = function(){
		webix.ui({ 
			view:"window",
			id:"addUserWindow",
			head : '<span id="addUserHead">Add User</span><span class="closeicon" id="closeAddUser">x</span>' ,
			height: setHeightInPercentage(93), 
			width: setWidthInPercentage(36),
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
					id:"add-user-form", 
					css:"brix_form user_add_form",
					scroll : "auto",
					scrollX : false,
					rows:[{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'usernameLabel',
							label : "<span class='brix_form_label'>User Name </span> <span style='color : red;'> *</span>",
							width: 185,
							minWidth:250,
						},{
							view : "text",
							id : "addUsername",
							name : "userName",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("addUsername").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 256){
									$$("addUsername").define("invalidMessage","User name length must less than 256 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("addUsername").define("invalidMessage", "User name must contain some special charaters.");
											return false;
										}else{
											$$("addUsername").define("invalidMessage"," ");
										}
									}
									$$("addUsername").define("invalidMessage"," ");
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
							id : 'passwordLabel',
							label : "<span class='brix_form_label'>Password</span><span style='color:red;'> *</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							type : "password",
							id : "password",
							name : "password",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								//var cpassword = $$("confirm-password").getValue().trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("password").setValue("");
									$$('password').define("invalidMessage", "This field can't be empty");
									return false;
								}else if(value.length > 100){
									$$("password").define("invalidMessage", "Password length must less than 100 characters.");
									return false;
								}/*else if (cpassword == value){
									$$("confirm-password").define("invalidMessage", " ");
									$$("confirm-password").refresh();
									return true;
								}else*/{
									$$("password").define("invalidMessage", " ");
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
						template:"<span class='userPersonalDetails'>Personal Details</span>", 
						type:"section",
						id : 'personaldetailsTemplate'
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'firstnameLabel',
							label : "<span class='brix_form_label'>First Name</span><span style='color:red;'> *</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "name",
							id : "add-first-name",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("add-first-name").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 100){
									$$("add-first-name").define("invalidMessage","First Name length must less than 100 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("add-first-name").define("invalidMessage", "First Name must contain some special characters.");
											return false;
										}else{
											$$("add-first-name").define("invalidMessage"," ");
										}
									}
									$$("add-first-name").define("invalidMessage","");
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
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'lastnameLabel',
							label : "<span class='brix_form_label'>Last Name</span><span style='color:red;'> *</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "surName",
							id : "add-last-name",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("add-last-name").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 100){
									$$("add-last-name").define("invalidMessage", "Last Name length must less than 100 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("add-last-name").define("invalidMessage", "Last Name must contain some special characters.");
											return false;
										}else{
											$$("add-last-name").define("invalidMessage"," ");
										}
									}
									$$("add-last-name").define("invalidMessage","");
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
					},{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'departmentLabel',
							label : "<span id='user_editDep_id' class='brix_form_label'>Department</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "department",
							id : "departmentId",
						},{
							width : 10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'mailLabel',
							label : "<span class='brix_form_label'>Email</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "emailId",
							id : "add-mail-id",
							invalidMessage : "Enter valid Email id",
							validate : function(value){
								value = value.trim();
								var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
								if(!value || value.match(regex))// || webix.rules.isEmail(value)
									return true;
								else
									return false;
							},
							on : {
								"onBlur" : function(){
									this.validate();
								}
							}
						},{
							width : 10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'contactLabel',
							label : "<span class='brix_form_label'>Contact</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "mobileNumber",
							id : "add-contactNo",
							validate: function(value){
								value=value.trim();
								if(value.length>0){
									if(!webix.rules.isNumber(value)){
										$$("add-contactNo").define("invalidMessage", "Contact number allowed only numbers.");
										return false;
									}else if(value.length > 10){
										$$("add-contactNo").define("invalidMessage", "Contact number allowed only 10 numbers.");
										return false;
									}else{
										$$("add-contactNo").define("invalidMessage", "");
										return true;
									}
								}else{
									$$("add-contactNo").define("invalidMessage", "");
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
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'testbedLabel',
							label : "<span id='testbed_id' class='brix_form_label'>TestBed</span> <span style='color : red;'> *</span>",
							width: 185,
							minWidth:250,
						},{
							view : "multiselect",
							name : "testbeds",
							id : "testbeds",
							invalidMessage : "This field can't be empty",
							required : true,
							css : 'multiselect_css',
							validation : webix.rules.isNotEmpty,
							on:{
								"onBlur" : function(){
									this.validate();
								},
								"onAfterRender" : webix.once(function(){
									$scope.getTestBeds();
								})
							}
						},{
							width : 10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'roleLabel',
							label : "<span id='role_id' class='brix_form_label'>Role</span> <span style='color : red;'> *</span>",
							width: 185,
							minWidth:250,
						},{
							view : "multiselect",
							name : "roles",
							id : "roles",
							invalidMessage : "This field can't be empty",
							required : true,
							css : 'multiselect_css',
							validation : webix.rules.isNotEmpty,
							on:{
								"onBlur" : function(){
									this.validate();
								},
								"onAfterRender" : webix.once(function(){
									$scope.getRoles();
								})
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
							let testbeds = [];
							let list = $$("testbeds").getValue();
							list = list.split(",");
							for(var i=0; i<list.length; i++){
								testbeds.push({"id":list[i]});
							}
							
							let roles = [];
							let list1 = $$("roles").getValue();
							list1 = list1.split(",");
							for(var i=0; i<list1.length; i++){
								roles.push({"id":list1[i]});
							}
							
							let userjson = $$("add-user-form").getValues();
							delete userjson['testbeds'];
							userjson['testbeds'] = testbeds;
							delete userjson['roles'];
							userjson['roles'] = roles;
							
							if($$("add-user-form").validate()){
								$.LoadingOverlay("show", spinner);
								userManagementService.create('user', userjson).then(function(response){
									if(response.returnCode == 1){
										notifyBox('success', 'User Created Successfully.', 'top right');
										$scope.getUsers();
										closeWindows('addUserWindow','add-user-form',85,36);
									}else if(response.returnCode == 3){
										notifyBox('warning', 'User Already Exist.', 'top right');
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
							closeWindows('addUserWindow','add-user-form',85,36);
						} 
					},{}]
				}]
			},
			/**
			 * Responsive Add User Window issue closed.
			 */
			on:{
				'onShow' : function(){
					$$('addUserWindow').resize();
					$('#closeAddUser').click(function(){
						closeWindows('addUserWindow','add-user-form',93,36);
					});
				},
				'onBeforeShow' : function(){},
			},
		}).show();
	}

	$scope.editUserWindow = function(){
		webix.ui({ 
			view:"window",
			id:"editUserWindow",
			head : '<span id="addUserHead">Edit User</span><span class="closeicon" id="closeAddUser">x</span>' ,
			height: setHeightInPercentage(93),
			width: setWidthInPercentage(36), 
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
					id:"add-user-form", 
					css:"brix_form user_add_form",
					scroll : "auto",
					scrollX : false,
					rows:[{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'usernameLabel',
							label : "<span class='brix_form_label'>User Name </span> <span style='color : red;'> *</span>",
							width: 185,
							minWidth:250,
						},{
							view : "text",
							id : "addUsername",
							name : "userName",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("addUsername").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 256){
									$$("addUsername").define("invalidMessage","User name length must less than 256 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("addUsername").define("invalidMessage", "User name must contain some special charaters.");
											return false;
										}else{
											$$("addUsername").define("invalidMessage"," ");
										}
									}
									$$("addUsername").define("invalidMessage"," ");
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
					}/*,{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'passwordLabel',
							label : "<span class='brix_form_label'>Password</span><span style='color:red;'> *</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							type : "password",
							id : "password",
							name : "password",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								//var cpassword = $$("confirm-password").getValue().trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("password").setValue("");
									$$('password').define("invalidMessage", "This field can't be empty");
									return false;
								}else if(value.length > 100){
									$$("password").define("invalidMessage", "Password length must less than 100 characters.");
									return false;
								}else if (cpassword == value){
									$$("confirm-password").define("invalidMessage", " ");
									$$("confirm-password").refresh();
									return true;
								}else{
									$$("password").define("invalidMessage", " ");
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
					},*/,{ 
						template:"<span class='userPersonalDetails'>Personal Details</span>", 
						type:"section",
						id : 'personaldetailsTemplate'
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'firstnameLabel',
							label : "<span class='brix_form_label'>First Name</span><span style='color:red;'> *</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "name",
							id : "add-first-name",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("add-first-name").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 100){
									$$("add-first-name").define("invalidMessage","First Name length must less than 100 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("add-first-name").define("invalidMessage", "First Name must contain some special characters.");
											return false;
										}else{
											$$("add-first-name").define("invalidMessage"," ");
										}
									}
									$$("add-first-name").define("invalidMessage","");
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
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'lastnameLabel',
							label : "<span class='brix_form_label'>Last Name</span><span style='color:red;'> *</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "surName",
							id : "add-last-name",
							invalidMessage : "This field can't be empty",
							required : true,
							validate:function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("add-last-name").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 100){
									$$("add-last-name").define("invalidMessage", "Last Name length must less than 100 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("add-last-name").define("invalidMessage", "Last Name must contain some special characters.");
											return false;
										}else{
											$$("add-last-name").define("invalidMessage"," ");
										}
									}
									$$("add-last-name").define("invalidMessage","");
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
					},{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'departmentLabel',
							label : "<span id='user_editDep_id' class='brix_form_label'>Department</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "department",
							id : "departmentId",
						},{
							width : 10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'mailLabel',
							label : "<span class='brix_form_label'>Email</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "emailId",
							id : "add-mail-id",
							invalidMessage : "Enter valid Email id",
							validate : function(value){
								value = value.trim();
								var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
								if(!value || value.match(regex))// || webix.rules.isEmail(value)
									return true;
								else
									return false;
							},
							on : {
								"onBlur" : function(){
									this.validate();
								}
							}
						},{
							width : 10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'contactLabel',
							label : "<span class='brix_form_label'>Contact</span>",
							width: 185,
							minWidth : 250,
						},{
							view : "text",
							name : "mobileNumber",
							id : "add-contactNo",
							validate: function(value){
								value=value.trim();
								if(value.length>0){
									if(!webix.rules.isNumber(value)){
										$$("add-contactNo").define("invalidMessage", "Contact number allowed only numbers.");
										return false;
									}else if(value.length > 10){
										$$("add-contactNo").define("invalidMessage", "Contact number allowed only 10 numbers.");
										return false;
									}else{
										$$("add-contactNo").define("invalidMessage", "");
										return true;
									}
								}else{
									$$("add-contactNo").define("invalidMessage", "");
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
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'testbedLabel',
							label : "<span id='testbed_id' class='brix_form_label'>TestBed</span> <span style='color : red;'> *</span>",
							width: 185,
							minWidth:250,
						},{
							view : "multiselect",
							name : "testbeds",
							id : "testbeds",
							multi: true,
							invalidMessage : "This field can't be empty",
							required : true,
							css : 'multiselect_css',
							validation : webix.rules.isNotEmpty,
							on:{
								"onBlur" : function(){
									this.validate();
								},
								/*"onAfterRender" : webix.once(function(){
									$scope.getTestBeds();
								})*/
							}
						},{
							width : 10
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols:[{
							view : 'label',
							id : 'roleLabel',
							label : "<span id='role_id' class='brix_form_label'>Role</span> <span style='color : red;'> *</span>",
							width: 185,
							minWidth:250,
						},{
							view : "multiselect",
							name : "roles",
							id : "roles",
							invalidMessage : "This field can't be empty",
							required : true,
							css : 'multiselect_css',
							validation : webix.rules.isNotEmpty,
							on:{
								"onBlur" : function(){
									this.validate();
								},
								/*"onAfterRender" : webix.once(function(){
									$scope.getRoles();
								})*/
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
							let testbeds = [];
							let list = $$("testbeds").getValue();
							if(list.indexOf(",") != -1)
								list = list.split(",");
							for(var i=0; i<list.length; i++){
								testbeds.push({"id":list[i].trim()});
							}
							let roles = [];
							let list1 = $$("roles").getValue();
							if(list1.indexOf(",") != -1)
								list1 = list1.split(",");
							for(var i=0; i<list1.length; i++){
								roles.push({"id":list1[i].trim()});
							}
							
							let userjson = $$("add-user-form").getValues();
							delete userjson['testbeds'];
							userjson['testbeds'] = testbeds;
							delete userjson['roles'];
							userjson['roles'] = roles;
							if($$("add-user-form").validate()){
								$.LoadingOverlay("show", spinner);
								userManagementService.create('./user/update/', userjson).then(function(response){
									if(response.returnCode == 1){
										notifyBox('success', 'User Updated Successfully.', 'top right');
										$scope.getUsers();
										closeWindows('editUserWindow','add-user-form',93,36);
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
							closeWindows('editUserWindow','add-user-form',85,36);
						} 
					},{}]
				}]
			},
			/**
			 * Responsive Add User Window issue closed.
			 */
			on:{
				'onShow' : function(){
					$$('editUserWindow').resize();
					$('#closeAddUser').click(function(){
						closeWindows('editUserWindow','add-user-form',85,36);
					});
					var selectedRow = $$("userGrid").getSelectedItem();
					
					$scope.getTestBeds();
					$scope.getRoles();
					var testbed = selectedRow.testbedsid;
					testbed = testbed.trim().split(",");
					for(var i=0; i<testbed.length; i++){
						testbed[i] = testbed[i].trim();
					}
					var role = selectedRow.rolesid;
					role = role.trim().split(",");
					for(var i=0; i<role.length; i++){
						role[i] = role[i].trim();
					}
					
					$$("add-user-form").setValues(selectedRow);
					setTimeout(function(){
						$$("testbeds").setValue(testbed);
						$$("roles").setValue(role);
					}, 100);
				},
				'onBeforeShow' : function(){},
			},
		}).show();
	}

	$scope.getUsers = function(){
		$.LoadingOverlay("show", spinner);
		userManagementService.get('user').then(function(response){
			if(response.returnCode == 1){
				$$("userGrid").clearAll();
				$$("userGrid").refresh();
				for(var i=0; i<response.result.length; i++){
					let testbedarray = response.result[i].testbeds;
					let testbeds = "";
					let testbedsid = "";
					for(var j=0;j<testbedarray.length;j++){
						testbeds = testbeds + testbedarray[j].name + ", ";
						testbedsid = testbedsid + testbedarray[j].id + ", ";
					}
					testbeds = testbeds.replace(/,\s*$/, "");
					testbedsid = testbedsid.replace(/,\s*$/, "");
					delete response.result['testbeds'];
					response.result[i]['testbeds'] = testbeds;
					response.result[i]['testbedsid'] = testbedsid;
					
					let rolearray = response.result[i].roles;
					let roles = "";
					let rolesid = "";
					for(var j=0;j<rolearray.length;j++){
						roles = roles + rolearray[j].name + ", ";
						rolesid = rolesid + rolearray[j].id + ", ";
					}
					roles = roles.replace(/,\s*$/, "");
					rolesid = rolesid.replace(/,\s*$/, "");
					delete response.result['roles'];
					response.result[i]['roles'] = roles;
					response.result[i]['rolesid'] = rolesid;
				}
				$$("userGrid").parse(response.result);
				if($$("userGrid").serialize().length != 0)
					$$("userGrid").select($$("userGrid").getFirstId());
			}
			$.LoadingOverlay("hide", spinner);
		});
	}

	$scope.getTestBeds = function(){
		userManagementService.get('testbeds/testbed').then(function(response){
			let testBeds = [];
			if(response.returnCode == 1){
				testBedList = response.result;
				if(testBedList != undefined && testBedList.length != 0){
					for(var i=0; i<testBedList.length; i++){
						testBeds.push({"id":testBedList[i].id , "value": testBedList[i].name});
					}
					$$("testbeds").define("options", testBeds);
				}
			}
		});
	};
	
	$scope.getRoles = function(){
		userManagementService.get('role').then(function(response){
			let roles = [];
			if(response.returnCode == 1){
				rolesList = response.result;
				if(rolesList != undefined && rolesList.length != 0){
					for(var i=0; i<rolesList.length; i++){
						roles.push({"id":rolesList[i].id , "value": rolesList[i].name});
					}
					$$("roles").define("options", roles);
				}
			}
		});
	
		
	}
}]);

