var baseUrl = "http://localhost";
var restUrl = baseUrl + "/rest/v1";

var loginService = angular.module('loginServices', ['ngResource']);

var transform = function(data){
    return $.param(data);
};

loginService.factory('Login', ['$resource', function($resource) {
    return $resource(baseUrl + "/rest/login", {}, {
        login: {
            method: 'POST',
            isArray: false,
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            transformRequest: transform
        },
        doget: {method: 'GET'}
    })
}]);

function url(v) {
    return restUrl + v;
}