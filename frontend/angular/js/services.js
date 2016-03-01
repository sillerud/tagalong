var baseUrl = "http://localhost";
var restUrl = baseUrl + "/rest/v1";

var loginService = angular.module('loginServices', ['ngResource']);

loginService.factory('Login', ['$resource', function($resource) {
    return $resource(baseUrl + "/login", {}, {
        login: {method: 'POST', isArray: false },
        doget: {method: 'GET'}
    })
}]);

function url(v) {
    return restUrl + v;
}