/***
 * @author pratiksha.datir
 */

/*var $scope.screens=[];*/
/***
 * code for opening new browser window
 * var wnd = window.open("about:blank", "", "_blank");
	wnd.document.write(html);
 */
var screenVal ;
var channelInterval;
var alarmInterval;
var isDown = false;
testBedApp.controller("TestBedController",["$scope","$rootScope","$http","$compile","$state","$timeout","$interval","TestBedService",
	function($scope,$rootScope,$http,$compile,$state,$timeout,$interval,testBedService){
	$scope.columnToBeRemoved=6;
	$scope.isFirst = true;
	$scope.channels;
	$scope.testBedName = testBedName;
	$scope.channelsLength;
	$scope.selectedTheme;
	$scope.testBedLog;
	$scope.logs = [];
	$scope.fixedChannels = [];
	$scope.screens = [];
	$scope.themes = [];
	$scope.alarms = [];
	$scope.selectedScreen;
	$scope.init = function(){
		$scope.getScreenList();		
		$scope.getThemeList();
		$scope.getTestBedAlarms();
	}

	$scope.sortChannels = function(arr){
		arr = arr.sort(function(a,b){
		    return a.seqNo > b.seqNo;
		});
		return arr;
	}
	
	$scope.getChannelsList = function(){
		clearInterval(channelInterval);
		$.LoadingOverlay("show", spinner);
		if($scope.selectedScreen != null && $scope.selectedScreen!=""){
			channelInterval = setInterval(function(){
				testBedService.get('./v1/testbeds/host/'+testBedHostName+'/screens/'+$scope.selectedScreen+'/channels').then(function(response){
					var resp = response.data;
					if(resp.returnCode == 1){
						if(resp.result!=null && resp.result.length!=0){
							
							if(resp.result.alarmActive === 1)
								$('.activeAlarm').removeClass('visibleAlarm');
							
							$scope.channels=resp.result.channelList;
							$scope.channelsLength=$scope.channels.length;
							$scope.fixedChannels = $scope.sortChannels($scope.channels);
							for(var i=0; i<$scope.channels.length; i++){
								var note = "";
								var styleclass = "";
								
								if($scope.channels[i].alarmValue == 1 ){
									note = "Lower Alarm";//Lower Alarm - ALARM_LOLO
								}else if($scope.channels[i].alarmValue == 2 ){
									note = "Lower Warning";//Lower Warning - ALARM_LO
								}else if( $scope.channels[i].alarmValue == 3 ){
									note = "Upper Warning ";//Higher Warning - ALARM_HI
								}else if($scope.channels[i].alarmValue == 4 ){
									note = "Upper Alarm";//Higher Alarm - ALARM_HIHI
								}

								if($scope.channels[i].dataType == 0){
									$scope.channels[i]['value'] = $scope.channels[i].doubleValue;
								}else if($scope.channels[i].dataType == 1){
									$scope.channels[i]['value'] = $scope.channels[i].strValue;
								}

								styleclass = "cltrTheme";
								if($scope.channels[i].alarmValue == 1 ||  $scope.channels[i].alarmValue == 4){
									styleclass = "cltrAlarmTheme";
									if($scope.channels[i].alarmAckflag == 2 || $scope.channels[i].alarmAckflag == 3){
										styleclass = "cltrAlarmAknowledgeTheme";
									}
								}else if($scope.channels[i].alarmValue == 2 || $scope.channels[i].alarmValue == 3){
									styleclass = "cltrWarningTheme";
									if($scope.channels[i].alarmAckflag == 2 || $scope.channels[i].alarmAckflag == 3){
										styleclass = "cltrWarningAknowledgeTheme";
									}
								}
								
								$scope.channels[i]['note'] = note;
								$scope.channels[i]['styleclass'] = styleclass;
							}
						}
					}
				});
				$.LoadingOverlay("hide", spinner);
			}, 1000);
		}
	}

	$scope.setAppliedTheme = function(){
		var head = document.getElementsByTagName('HEAD')[0]; 
		var versionNumber = Math.floor(Math.random() * 100) + 1;
		// Create new link Element 
		var link = document.createElement('link'); 
		if($scope.selectedTheme === 'dark'){
			// set the attributes for link element  
			link.rel = 'stylesheet';  
			link.type = 'text/css'; 
			link.href = './resources/css/darkTheme.css?version='+versionNumber;  
			// Append link element to HTML head 
			head.appendChild(link); 
		}else if($scope.selectedTheme === 'light'){
			// set the attributes for link element  
			link.rel = 'stylesheet';  
			link.type = 'text/css'; 
			link.href = './resources/css/lightTheme.css?version='+versionNumber;
			// Append link element to HTML head 
			head.appendChild(link); 
		}
	}

	$scope.getLogMessages = function(){
		testBedService.get('./v1/testbeds/host/'+testBedHostName+'/logmessage').then(function(response){
			var resp = response.data;
			if(resp.returnCode == 1){
				for(var i=0; i<resp.result.logMessage.length; i++){
					let icon = '';
					let iconcolor = '';
					let textcolor = '';
					let fontweight = 'normal';
					if(resp.result.logMessage[i].type == 0){
						textcolor = 'blue';
						icon = 'fa fa-info-circle';
						iconcolor = 'darkgrey';
					}else if(resp.result.logMessage[i].type == 1){
						textcolor = 'red';
						icon = 'fa fa-close';
						iconcolor ='red';
					}else if(resp.result.logMessage[i].type == 2 || resp.result.logMessage[i].type == 3){
						textcolor ='blue';
						icon = 'fa fa-exclamation-triangle';
						iconcolor = '#fdbf03';
					}else if(resp.result.logMessage[i].type == 4){
						textcolor = 'red';
						icon = 'fa fa-bell';
						iconcolor = 'red';
					}

					if(resp.result.logMessage[i].severity == 1){
						fontweight = 'bold';
					}

					$scope.logs.push({'value':resp.result.logMessage[i].value, 'icon': icon,
						'iconcolor' : iconcolor, 'textcolor': textcolor, 'fontweight': fontweight,
						'dateTime' : resp.result.logMessage[i].dateTime, 'seqNo' : resp.result.logMessage[i].seqNo});
				}
				$scope.testBedLog = $scope.logs;
				
			}
		});
	}
	
	$timeout(function () {
		$scope.showLogWindow();//DOM has finished rendering
	});

	$scope.getScreenList = function(){
		if(testBedHostName !=null && testBedHostName!=""){
			$.LoadingOverlay("show", spinner);
			testBedService.get('./v1/testbeds/host/'+testBedHostName+'/screens').then(function(response){
				var resp = response.data;
				if(resp.returnCode == 1){
					if(resp.result!=null && resp.result.length!=0){
						for(var i=0; i<resp.result.length; i++){
							$scope.screens.push({"id":resp.result[i].screen, "value":resp.result[i].screen})
						}
						$scope.selectedScreen = $scope.screens[0].id;
						$scope.getChannelsList();
					}
				}
				$.LoadingOverlay("hide", spinner);
			});
		}
	}
	
	$scope.getThemeList = function(){
		$scope.themes = [{'id':'dark', 'value':'Dark Theme'},{'id':'light', 'value':'Light Theme'}];
		$scope.selectedTheme = $scope.themes[0].id;
		return $scope.themes;
	}

	$scope.showLogWindow = function () {
		//$("#tabs").tabs();
		$('#log').lobiPanel({
			reload: false,
			close: false,
			editTitle: false,
			tooltips: false,
			expand: false,
			unpin: false,
		});
		
		var instance = $('#log').data('lobiPanel');
		instance.enableDrag();
		instance.enableResize();
		
		var editIcon = $('.dropdown-menu').find('li>a[data-func="editTitle"]');
		if(editIcon!=null && editIcon.length!=0)
			$(editIcon[0]).remove();
		
		var unpinIcon = $('.dropdown-menu').find('li>a[data-func="unpin"]');
		if(unpinIcon!=null && unpinIcon.length!=0)
			$(unpinIcon[0]).remove();
		
		var reloadIcon = $('.dropdown-menu').find('li>a[data-func="reload"]');
		if(reloadIcon!=null && reloadIcon.length!=0)
			$(reloadIcon[0]).remove();
		
		var closeIcon = $('.dropdown-menu').find('li>a[data-func="close"]');
		if(closeIcon!=null && closeIcon.length!=0)
			$(closeIcon[0]).remove();
		
		var changeStyleIcon = $('.dropdown-menu').find('li>a[data-func="changeStyle"]');
		if(changeStyleIcon!=null && changeStyleIcon.length!=0)
			$(changeStyleIcon[0]).remove();
		
		var fullScreenIcon = $('.dropdown-menu').find('li>a[data-func="expand"]');
		if(fullScreenIcon!=null && fullScreenIcon.length!=0)
			$(fullScreenIcon[0]).remove();
		
		$('#log').on('beforeMinimize.lobiPanel', function(ev, lobiPanel){
			$('.panelbody').addClass('panelHeightOnMinimize');
			$('.panelbody').removeClass('panelHeightOnMaximize');
		});
		
		$('#log').on('beforeMaximize.lobiPanel', function(ev, lobiPanel){
			$('.panelbody').addClass('panelHeightOnMaximize');
			$('.panelbody').removeClass('panelHeightOnMinimize');
		});
		
		$('#log').on('resizeStart.lobiPanel', function(ev, lobiPanel){
			isDown = false;
		});
		
		$('#log').on('resizeStop.lobiPanel.lobiPanel', function(ev, lobiPanel){
			isDown = false;
		});
		
		moveLogWindow();
		
		$scope.getLogMessages();
		
		$interval(function(){
			$scope.logs = [];
			$scope.getLogMessages();
			if($('.tab-content').get(0).scrollHeight > $('.tab-content').height()){
				$('.tab-content').scrollTop($('.tab-content')[0].scrollHeight);
			}
		},1000);
	}
	
	$scope.getTestBedAlarms = function(){
		alarmInterval = setInterval(function(){
			testBedService.get('./v1/testbeds/host/'+testBedHostName+'/alarms').then(function(response){
				if(response != null){
					$scope.alarms=[];
					var styleclass = "cltrAlarmViewer";
					if(response.data.result != null && response.data.result.length != 0){
						if(response.data.result.alarms != null && response.data.result.alarms.length != 0){
							for(var i=0; i<response.data.result.alarms.length; i++){
								var currentValue;
								if(response.data.result.alarms[i].dataType == 1)
									currentValue = response.data.result.alarms[i].strValue;
								if(response.data.result.alarms[i].dataType == 0)
									currentValue = response.data.result.alarms[i].doubleValue;
								
								if(response.data.result.alarms[i].alarmValue == 1){
									styleclass = "cltrLowerAlarmViewer";
									if(response.data.result.alarms[i].alarmAckflag == 2 || 
										response.data.result.alarms[i].alarmAckflag == 3){
											styleclass = "cltrAknwolegeLowerAlarmViewer";
									}
								}else if(response.data.result.alarms[i].alarmValue == 2){
									styleclass = "cltrLowerWarningViewer";
									if(response.data.result.alarms[i].alarmAckflag == 2 || 
											response.data.result.alarms[i].alarmAckflag == 3){
										styleclass = "cltrAknowledgeLowerWarningViewer";
									}
								}else if(response.data.result.alarms[i].alarmValue == 3){
									styleclass = "cltrUpperWarningViewer";
									if(response.data.result.alarms[i].alarmAckflag == 2 || 
											response.data.result.alarms[i].alarmAckflag == 3){
										styleclass = "cltrAknowledgeUpperWarningViewer";
									}
								}else if(response.data.result.alarms[i].alarmValue == 4){
									styleclass = "cltrUpperAlarmViewer";
									if(response.data.result.alarms[i].alarmAckflag == 2 || 
											response.data.result.alarms[i].alarmAckflag == 3){
										styleclass = "cltrAknowledgeUpperAlarmViewer";
									}
								}
								
								$scope.alarms.push({"Tag Name": response.data.result.alarms[i].name,
													"Upper Alarm": response.data.result.alarms[i].upperAlarm,
													"Upper Warning": response.data.result.alarms[i].upperWarning,
													"Lower Alarm": response.data.result.alarms[i].lowerAlarm,
													"Lower Warning": response.data.result.alarms[i].lowerWarning,
													"Current Tag Value": currentValue,
													"styleClass": styleclass});
							}
						}
					}
					//$scope.columnToBeRemoved = Object.keys($scope.alarms[0]).length-1;
				}
			});
		},2000);
	}
	
	$scope.init();
}]);

function moveLogWindow(){
	var logwin = document.getElementById("logWindow");
	var panel = document.getElementsByClassName("panel");
	panel = panel[0];
	var mousePosition;
	var offset = [0,0];

	panel.addEventListener('mousedown', function(e) {
		isDown = true;
		offset = [
			logwin.offsetLeft - e.clientX,
			logwin.offsetTop - e.clientY
		];
	}, true);
	
	document.addEventListener('mouseup', function() {
		isDown = false;
	}, true);

	document.addEventListener('mousemove', function(event) {
		event.preventDefault();
		if (isDown) {
			mousePosition = {
					x : event.clientX,
					y : event.clientY
			};
			var left = (mousePosition.x + offset[0]);
			var top = (mousePosition.y + offset[1]);
			if(left > 1220){
				logwin.style.left = '10px';
				logwin.style.top  = '10px';
				panel.style.left = '10px';
				panel.style.top  = '10px';
			}else if(top > 225){
				logwin.style.left = '0px';
				logwin.style.top  = '10px';
				panel.style.left = '0px';
				panel.style.top  = '10px';
			}else if(top < -310){
				logwin.style.left = '0px';
				logwin.style.top  = '10px';
				panel.style.left = '0px';
				panel.style.top  = '10px';
			}else{
				logwin.style.left = left + 'px';
				logwin.style.top  = top + 'px';
				panel.style.left = left + 'px';
				panel.style.top  = top + 'px';
			}
		}
	}, true);
}