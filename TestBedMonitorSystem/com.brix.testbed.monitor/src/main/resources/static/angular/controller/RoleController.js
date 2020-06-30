/**
 * 
 */
userManagementApp.controller('RoleController', ['$scope', '$http', 'UserManagementService', function($scope, $http, userManagementService) {
	$scope.roleGridConfig = {
			rows:[{
				view: 'toolbar',
				id : 'roletoolbar',
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
						$scope.createRoleWindow();
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
						var selectedRow = $$("roleGrid").getSelectedItem();
						if(selectedRow != undefined && selectedRow.length != 0)
							$scope.editRoleWindow();
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
						var selectedRow = $$('roleGrid').getSelectedItem();
						if(selectedRow != undefined && selectedRow.length != 0){
							Lobibox.confirm({
								title : "Confirmation",
								msg : "Are you sure to delete role?",
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
										$$("roleGrid").getFilter("name").value = "";
										$$("roleGrid").getFilter("description").value = "";
										$$("roleGrid").getFilter("createdDate").value = "";
										$$("roleGrid").getFilter("updatedDate").value = "";
										$.LoadingOverlay("show", spinner);
										userManagementService.remove('./role/roleid/'+selectedRow.id).then(function(response) {
											if (response.returnCode == 1) {
												notifyBox('success', 'Role deleted successfully.', 'top right');
												$scope.getRolesList();
											}else if(response.returnCode == 4) {
												notifyBox('warning', 'Role is in use.', 'top right');
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
				id:'roleGrid',			
				view:'datatable',
				borderless:true,
				select:true,
				height : setHeightInPercentage(70),
				//width : 1100, 
				resizeColumn: { headerOnly:true },
				fixedRowHeight : false,
				scrollX: true,
				gravity:0.4,
				visibleBatch:1,
				css:'brix_datatable_2 admindatatable',
				columns:[
					{ id: "index", header: [{text: "Sr. No."}], sort: "int", batch: 1, fillspace: 1},
					{ id : "id", editor : "text", header:"Id", batch: 2,fillspace: 1},
					{ id : "name", editor : "text", header:["Role", {content:"textFilter"}], batch: 1 ,fillspace: 2},
					{ id:"description", editor: "popup",header:["Description",{content:"textFilter"}], batch: 1,fillspace: 2},
					{ id:"createdDate", editor: "popup",header:["Created Date",{content:"textFilter"}], batch: 1,fillspace: 1.5},
					{ id:"updatedDate", editor: "popup",header:["Updated Date",{content:"textFilter"}], batch: 1,fillspace: 1.5}],
					on : {
						"onItemDblClick" : function(id, ev){
							var selectedRow = $$("roleGrid").getSelectedItem();
							if(selectedRow != undefined && selectedRow.length != 0)
								$scope.editRoleWindow();
						}, 
						"data->onStoreUpdated":function(){
							this.data.each(function(obj, i){
								obj.index = i+1;
							})
						},
						"onAfterRender" : webix.once(function() {
							$scope.getRolesList();
						}),
						"onAfterFilter" : function(){
							var filteredRowId = $$("roleGrid").getIdByIndex(0);
							if(filteredRowId != undefined){
								$$("roleGrid").select(filteredRowId);
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

	$scope.createRoleWindow = function(){
		webix.ui({ 
			view:"window",
			id:"addRoleWindow",
			head : '<span id="addrole_id">Add Role</span><span class="closeicon" id="closeRoleWindow" >x</span>',
			css:'brix_popup loggingModalCss',
			modal:true,
			position: "center", 
			height: setHeightInPercentage(35),
			width: setWidthInPercentage(30), 
			move:true, 
			resize:true,
			headHeight:28,
			body:{
				rows : [{
					gravity : 9,
					view:"form",
					id:"add-role-form", 
					scroll:'auto',
					css:"brix_form user_add_form",
					rows:[{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'nameLabel',
							label:"<span id='role_name_id' class='brix_form_label'>Role</span> <span style='color : red;'> *</span>", 
							width: 150,
							minWidth : 250,
						},{
							view:"text", 
							name:"name", 
							id : "name",
							minWidth:140,
							invalidMessage: "This field can't be empty",
							required : true,
							validate : function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("name").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 256){
									$$("name").define("invalidMessage", "Role Name length must less than 256 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("name").define("invalidMessage", "Role name must contain some special characters.");
											return false;
										}else{
											$$("name").define("invalidMessage"," ");
										}
									}
									$$("name").define('invalidMessage',' ');
									return true;
								}
							},
							on: {
								"onBlur" : function(){
									this.validate();
								}
							}
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'descriptionLabel',
							label:"<span id='role_des_id' class='brix_form_label'>Description</span>", 
							width: 150,
							minWidth : 250,
						},{
							view:"text", 
							name:"description", 
							minWidth:140,
							id : "description",
							invalidMessage : "This field can't be empty",
							validate : function(value){ 
								value = value.trim();
								if(value.length > 1000){
									$$("description").define("invalidMessage", "Description length must less than 1000 characters.");
									return false;
								}else{
									$$("description").define('invalidMessage',' ');
									return true;
								}
							},
							on : {
								"onBlur" : function(){
									this.validate();
								}
							}
						}]
					}],
				},{
					cols : [{},{
						view:"button", 
						label:"<span>Save</span>", 
						css : 'brix_button customiconbutton',
						autowidth : true,
						id : 'add_button',
						type : 'icon',
						icon : 'save',
						click : function(){
							let rolejson = $$("add-role-form").getValues();
							if($$("add-role-form").validate()){
								$.LoadingOverlay("show", spinner);
								userManagementService.create('role', rolejson).then(function(response){
									if(response.returnCode == 1){
										notifyBox('success', 'Role Created Successfully.', 'top right');
										$scope.getRolesList();
										closeWindows('addRoleWindow','add-role-form',35,30);
									}else if(response.returnCode == 3){
										notifyBox('warning', 'Role Already Exist.', 'top right');
									}
									$.LoadingOverlay("hide", spinner);
								});
							}
						}
					},{
						view:"button", 
						label:"<span id='addRolecancel'>Cancel</span>", 
						css : 'brix_button customiconbutton',
						autowidth : true,
						id : 'cancelButton',
						type : 'icon',
						icon : 'close',
						click : function(){
							closeWindows('addRoleWindow','add-role-form',35,30);
						} 
					},{}]
				}]
			},
			on:{
				'onShow' : function(){
					$$("addRoleWindow").resize();
					$('#closeRoleWindow').click(function(){
						closeWindows('addRoleWindow','add-role-form',35,30);
					})
				},
				'onBeforeShow' : function(){
				},
				"onViewMove" : function(e){
				}
			}
		}).show();
	}

	$scope.editRoleWindow = function(){
		webix.ui({ 
			view:"window",
			id:"editRoleWindow",
			head : '<span id="role_AddNew_id">Edit Role</span><span class="closeicon" id="closeRoleWindow" >x</span>',
			css:'brix_popup loggingModalCss',
			modal:true,
			position: "center", 
			height: setHeightInPercentage(35),
			width: setWidthInPercentage(30), 
			move:true, 
			resize:true,
			headHeight:28,
			body:{
				rows : [{
					gravity : 9,
					view:"form",
					id:"add-role-form", 
					scroll:'auto',
					css:"brix_form user_add_form",
					rows:[{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'nameLabel',
							label:"<span id='role_name_id' class='brix_form_label'>Role</span> <span style='color : red;'> *</span>", 
							width: 150,
							minWidth : 250,
						},{
							view:"text", 
							name:"name", 
							id : "name",
							minWidth:140,
							invalidMessage: "This field can't be empty",
							required : true,
							validate : function(value){
								value = value.trim();
								if(!webix.rules.isNotEmpty || value.length <= 0){
									$$("name").define("invalidMessage","This field can't be empty");
									return false;
								}else if(value.length > 256){
									$$("name").define("invalidMessage", "Role Name length must less than 256 characters.");
									return false;
								}else{
									for(var i=0; i<specialCharacters.length; i++){
										if(value.indexOf(specialCharacters[i]) >= 0){
											$$("name").define("invalidMessage", "Role name must contain some special characters.");
											return false;
										}else{
											$$("name").define("invalidMessage"," ");
										}
									}
									$$("name").define('invalidMessage',' ');
									return true;
								}
							},
							on: {
								"onBlur" : function(){
									this.validate();
								}
							}
						}]
					},{
						view:"flexlayout", 
						gravity:1,
						cols : [{
							view : 'label',
							id : 'descriptionLabel',
							label:"<span id='role_des_id' class='brix_form_label'>Description</span>", 
							width: 150,
							minWidth : 250,
						},{
							view:"text", 
							name:"description", 
							minWidth:140,
							id : "description",
							invalidMessage : "This field can't be empty",
							validate : function(value){ 
								value = value.trim();
								if(value.length > 1000){
									$$("description").define("invalidMessage", "Description length must less than 1000 characters.");
									return false;
								}else{
									$$("description").define('invalidMessage',' ');
									return true;
								}
							},
							on : {
								"onBlur" : function(){
									this.validate();
								}
							}
						}]
					}],
				},{
					cols : [{},{
						view:"button", 
						label:"<span>Save</span>", 
						css : 'brix_button customiconbutton',
						autowidth : true,
						id : 'add_button',
						type : 'icon',
						icon : 'save',
						click : function(){
							let rolejson = $$("add-role-form").getValues();
							if($$("add-role-form").validate()){
								$.LoadingOverlay("show", spinner);
								userManagementService.create('./role/update/', rolejson).then(function(response){
									if(response.returnCode == 1){
										notifyBox('success', 'Role Updated Successfully.', 'top right');
										$scope.getRolesList();
										closeWindows('editRoleWindow','add-role-form',35,30);
									}
									$.LoadingOverlay("hide", spinner);
								});
							}
						}
					},{
						view:"button", 
						label:"<span id='addRolecancel'>Cancel</span>", 
						css : 'brix_button customiconbutton',
						autowidth : true,
						id : 'cancelButton',
						type : 'icon',
						icon : 'close',
						click : function(){
							closeWindows('editRoleWindow','add-role-form',35,30);
						} 
					},{}]
				}]
			},
			on:{
				'onShow' : function(){
					var selectedRow = $$("roleGrid").getSelectedItem();
					$$("add-role-form").setValues(selectedRow);
				},
				'onBeforeShow' : function(){
					$('#closeRoleWindow').click(function(){
						closeWindows('editRoleWindow','add-role-form',35,30);
					})
				},
				"onViewMove" : function(e){
				}
			}
		}).show();
	}

	$scope.getRolesList = function(){
		$.LoadingOverlay("show", spinner);
		userManagementService.get('role').then(function(response){
			if(response.returnCode == 1){
				$$("roleGrid").clearAll();
				$$("roleGrid").refresh();
				$$("roleGrid").parse(response.result);
				if($$("roleGrid").serialize().length != 0)
					$$("roleGrid").select($$("roleGrid").getFirstId());
			}
			$.LoadingOverlay("hide", spinner);
		});
	
		
	}
}]);

