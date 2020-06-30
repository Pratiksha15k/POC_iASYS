/**
 * 
 */
userManagementApp.service('UserManagementService', ['$http', function($http){
	var userManagementService = {
			create : function(url, userjson){
				return $http({
					method: 'POST',
					url: url,
					data: userjson
				}).then(function successCallback(response){
					return response.data;
				}, function errorCallback(response){
					return response;
				});
			},
			remove : function(url){
				return $http({
					method: 'GET',
					url: url,
				}).then(function successCallback(response){
					return response.data;
				}, function errorCallback(response){
					return response;
				});
			},
			get : function(url){
				return $http({
					method: 'GET',
					url: url,
				}).then(function successCallback(response){
					return response.data;
				}, function errorCallback(response){
					return response;
				});
			},
	}
	return userManagementService;
}]);