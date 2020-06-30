/**
 * @author pratiksha.datir
 */

testBedMonitorApp.controller("TestBedMonitorController",["$scope","$rootScope","$http","$compile","$state",
	"TestBedMonitorService", function($scope,$rootScope,$http,$compile,$state,testBedMonitorService){
	$scope.totalTestBed;
	$scope.totalTestBedCount;
	$rootScope.screenArray;
	$rootScope.hostname;
	$rootScope.testbed;
	$scope.init = function(){
		$.LoadingOverlay("show", spinner);
		testBedMonitorService.get('v1/testbeds/user').then(function(response){/*/user
*/			var resp = response.data;
			if(resp.returnCode == 1){
				if(resp.result!=null && resp.result.length!=0){
					$scope.totalTestBed = resp.result;
				}
			}
			$.LoadingOverlay("hide", spinner);
		}); 
	};
	$scope.init();
}]);