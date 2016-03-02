'use strict';
angular.module('tagalong', [
    'ngRoute',
    'loginControllers',
    'loginServices',
    'eventControllers',
    'eventServices',
    'userControllers',
    'userServices'
])

    .config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider
            .when("/home", {
                templateUrl: 'partials/cards.html'
            })
            .when("/events", {
                templateUrl: 'partials/events.html',
                controller: 'ViewEventsCtrl'
            })
            .when("/login", {
                templatesUrl: 'partials/'
            })
            .otherwise({
                redirectTo: '/home'
            });
}]);