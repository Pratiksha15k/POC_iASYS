/**
 * @author pratiksha.datir
 */
var testBedApp = angular.module('testBedApp', [ 'webix', 'ui.router' ]);

testBedApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise('/testBed');
	
	$stateProvider
	.state('testBed', {
        url: '/testBed',
        templateUrl: './resources/angular/views/TestBed.html',
        controller: 'TestBedController'
    })
}]);	
