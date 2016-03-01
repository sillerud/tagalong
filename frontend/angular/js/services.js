var baseUrl = "http://localhost:82";
var restUrl = baseUrl + "/rest/v1";

var loginServices = angular.module('loginServices', ['ngResource']);
var postServices = angular.module('postServices', ['ngResource']);
var eventServices = angular.module('eventServices', ['ngResource']);

var transform = function(data){
    return $.param(data);
};

loginServices.factory('Login', ['$resource', function($resource) {
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

loginServices.factory('Event', ['$resource', function($resource) {
    return $resource(url('/event'), {}, {

    });
}]);

function url(v) {
    return restUrl + v;
}