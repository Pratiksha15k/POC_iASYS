var userManagementApp = angular.module('UserManagementApp', [ 'webix', 'ui.router' ]);

userManagementApp.config(['$httpProvider', function ($httpProvider) {
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
}]); 

userManagementApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise('/user');
	
	$stateProvider
	.state('user', {
        url: '/user',
        templateUrl: './resources/angular/views/User.html',
        controller: 'UserController'
    })
    .state('testbed', {
        url: '/testbed',
        templateUrl: './resources/angular/views/TestBedOperations.html',
        controller: 'TestBedOperationsController'
    })
    .state('role', {
        url: '/role',
        templateUrl: './resources/angular/views/Role.html',
        controller: 'RoleController'
    })
    .state('applicationinfo', {
        url: '/applicationinfo',
        templateUrl: './resources/angular/views/ApplicationInfo.html',
        controller: 'ApplicationInfoController'
    })
}]);	
