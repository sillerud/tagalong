var baseUrl = "http://localhost";
var restUrl = baseUrl + "/rest/v1";

var loginServices = angular.module('loginServices', ['ngResource']);
var postServices = angular.module('postServices', ['ngResource']);
var eventServices = angular.module('eventServices', ['ngResource']);
var userServices = angular.module('userServices', ['ngResource']);
var cardServices = angular.module('cardServices', ['ngResource']);

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
        }
    })
}]);

loginServices.factory('Event', ['$resource', function($resource) {
    return $resource(url('/event'), {}, {

    });
}]);

userServices.factory('User', ['$resource', function($resource) {
    return $resource(url("/users/:userId"), {}, {
        create: {
            url: url("/users"),
            method: 'POST',
            isArray: false
        },
        find: {
            method: 'GET',
            isArray: false,
            params: {userId: 'me'}
        },
        logout: {
            url: baseUrl + "/rest/logout",
            method: 'POST'
        }
    })
}]);

cardServices.factory('Card', ['$resource', function($resource) {
    return $resource(url("/cards"), {}, {
        create: {
            method: 'POST',
            isArray: false
        },
        all: {
            method: 'GET',
            isArray: true
        }
    })
}]);

function url(v) {
    return restUrl + v;
}