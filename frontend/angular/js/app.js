'use strict';
angular.module('tagalong', [
    'ngRoute',
    'loginControllers',
    'loginServices'
])

    .config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider
            .when("/login", {
                templatesUrl: 'partials/'
            });
}]);