/**
 * @author pratiksha.datir
 */

testBedMonitorApp.config(['$httpProvider', function ($httpProvider) {
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
}]); 


testBedMonitorApp.service("TestBedMonitorService", ["$http", function($http) {
    return {
        get: function(url){
            return $http({
                method: 'GET',
                cache: false,
                url: url,
            }).then(function success(response) {
                return response;

            }, function error(response) {
                return response;
            });
        },
    }
}])