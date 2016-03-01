'use strict';
angular.module('tagalong', [
    'ngRoute',
    'loginControllers',
    'loginServices',
    'eventControllers',
    'eventServices'
])

    .config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider
            .when("/events", {
                templateUrl: 'partials/events.html',
                controller: 'ViewEventsCtrl'
            })
            .when("/login", {
                templatesUrl: 'partials/'
            });
}]);