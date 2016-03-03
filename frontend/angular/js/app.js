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
        'pageControllers'
    ])

    .config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider
            .when("/home", {
                templateUrl: 'partials/cards.html',
                controller: 'AllCardCtrl'
            })
            .when("/events", {
                templateUrl: 'partials/events.html',
                controller: 'ViewEventsCtrl'
            })
            .when("/pages/:pageId", {
                templateUrl: 'partials/page.html',
                controller: 'PageCtrl'
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
