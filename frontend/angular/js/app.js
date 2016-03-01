'use strict';
angular.module('tagalong', [
    'ngRoute',
    'loginControllers',
    'loginServices'
])

    .config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $httpProvider.defaults.withCredentials = true;
        $routeProvider
            .when("/login", {
                templatesUrl: 'partials/'
            });
}]);