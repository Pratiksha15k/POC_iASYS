/**
 * @author pratiksha.datir
 */
var testBedMonitorApp = angular.module('testBedMonitorApp', [ 'webix', 'ui.router' ]);
testBedMonitorApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise('/testBedMonitor');
	
	$stateProvider
	.state('testBedMonitor',{
		url: '/testBedMonitor',
		templateUrl : './resources/angular/views/TestBedMonitor.html',
		controller : 'TestBedMonitorController'
	})
	.state('testBed', {
        url: '/testBed',
        templateUrl: './resources/angular/views/TestBed.html',
        controller: 'TestBedController'
    })
}]);	
