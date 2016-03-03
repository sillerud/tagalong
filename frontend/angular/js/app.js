'use strict';
angular.module('tagalong', [
        'ngRoute',
        'loginControllers',
        'loginServices',
        'eventControllers',
        'eventServices',
        'userControllers',
        'userServices',
        'cardControllers',
        'cardServices',
        'pageControllers',
        'pageServices'
    ])

    .config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider
            .when("/events", {
                templateUrl: 'partials/events.html',
                controller: 'ViewEventsCtrl'
            })
            .when("/pages/:id", {
                templateUrl: 'partials/page.html',
                controller: 'PageCtrl'
            })
            .when("/", {
                templateUrl: 'partials/cards.html',
                controller: 'AllCardCtrl'
            });
    }])
    .directive('galeryImage', function() {
        return {
            restrict: "A",
            link: function(scope, element) {
                scope.getWidth = function() {
                    return $(element).width();
                };
                scope.$watch(scope.getWidth, function(width) {
                    $(element).height(width);
                });
            }
        }
    });
