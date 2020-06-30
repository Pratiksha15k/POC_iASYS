

statistics.controller("statisticsController",["$scope","$http","$compile",function($scope,$http,$compile){
	$scope.statisticConfig = {
			//view : "container",
			autoheight : true,
			rows : [{
				height : 30,
			},{
				rows : [{
				height : 150,
				cols : [{},{
					rows :[{height :30},{
						view : "label",
						id : "labelId",
						label : "How Many No of Records Do You Want?",
						width : 300,
						required : true
					},{
						view:"text",
						id : "textId",
						width : 100,
					}]
				},{
					width : 30
				},{
					view:"datatable", 
					id : "textData",
					height : 100,
					width : 600,
					scroll : false,
					css : 'datatableCss',
					columns:[
					         { id:"requestTime",    header:"Request Time",  editor : "text",width:200},
					         { id:"processedTime",   header:"Processed Time", editor : "text",    width:200},
					         { id:"responseTime",   header:"Response Time",  editor : "text",  width:200},
					        // { id:"responseTimeAfterRender",   header:"Rendering Time",  editor : "text",  width:200},
					         ],
				},{width : 30},{
					rows : [{},{
						view:"button", 
						id:"dynamic", 
						value:"Dynamic", 
						css:"webix_primary", 
						inputWidth:100,
						height : 40,
						click : function(){
							
						}
					},{height:30},{
						view:"button", 
						id:"static", 
						value:"Static", 
						css:"webix_primary", 
						inputWidth:100,
						height : 40,
						click : function(){
							var date = new Date();
							var timeInMilliSecond = date.getTime();
							var noOfRecords = $$("textId").getValue();
							$.ajax({
								url : "getStaticData/"+timeInMilliSecond+"/"+noOfRecords,
								cache : false,
								success : function(response){
									var resp = JSON.parse(response);
									var date = new Date();
									var millis = date.getTime();
									var respTime = millis - resp.responseTime;
									
									$$("StaticData").refresh();
									$$("StaticData").clearAll();
									for(var i=0; i < noOfRecords; i++){
										$$("StaticData").add({ attribute1 : resp.rowData[i].attribute1, attribute2 : resp.rowData[i].attribute2, attribute3 : resp.rowData[i].attribute3, 
											attribute4 : resp.rowData[i].attribute4, attribute5 : resp.rowData[i].attribute5, attribute6 : resp.rowData[i].attribute6,
											attribute7 : resp.rowData[i].attribute7, attribute8 : resp.rowData[i].attribute8, attribute9 : resp.rowData[i].attribute9, attribute10 : resp.rowData[i].attribute10},i);
									}
								/*	var date = new Date();
									var millisAfterRender = date.getTime();
									var respTimeAfterRender = millisAfterRender - respTime;*/
									$$("textData").refresh();
									$$("textData").clearAll();
									$$("textData").add({requestTime : resp.requestTime, processedTime : resp.processedTime, /*responseTimeAfterRender : respTimeAfterRender + " (ms)",*/ responseTime : respTime + "(ms)"});
								},
								error : function(response){

								}
							});
						}
					},{					
					}]
				}]
			}]
			},{
				height : 30
			},{
				cols :[{
					width : 300
				},{
					view : "datatable",
					id : "StaticData",
					height : 500,
					width : 1110,
					scrollY : true,
					scrollX : false,
					columns : [
					{id: "index", header: [{text: "Sr. No."}], sort: "int"},
					{id : "attribute1", header : "Attribute 1", editor : "text",width : 100},
					{id : "attribute2", header : "Attribute 2", editor : "text",width : 100},
					{id : "attribute3", header : "Attribute 3", editor : "text",width : 100},
					{id : "attribute4", header : "Attribute 4", editor : "text",width : 100},
					{id : "attribute5", header : "Attribute 5", editor : "text",width : 100},
					{id : "attribute6", header : "Attribute 6", editor : "text",width : 100},
					{id : "attribute7", header : "Attribute 7", editor : "text",width : 100},
					{id : "attribute8", header : "Attribute 8", editor : "text",width : 100},
					{id : "attribute9", header : "Attribute 9", editor : "text",width : 100},
					{id : "attribute10", header : "Attribute 10", editor : "text",width : 100},
					],
					on : {
						"data->onStoreUpdated":function(){
							this.data.each(function(obj, i){
								obj.index = i+1;
							})
						},
					}
				},{
					
				}]
			}]
	}
}]);



