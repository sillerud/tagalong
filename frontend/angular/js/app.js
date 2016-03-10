'use strict';
angular.module('tagalong', [
        'ngRoute',
        'ngFileUpload',
        'ui.select',
        'ngSanitize',
        'eventControllers',
        'eventServices',
        'userControllers',
        'userServices',
        'cardControllers',
        'cardServices',
        'pageControllers',
        'pageServices',
        'searchControllers',
        'searchServices',
        'staticServices'
    ])

    .config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider
            .when("/events", {
                templateUrl: 'partials/events.html',
                controller: 'ViewEventsCtrl'
            })
            .when("/events/new", {
                templateUrl: 'partials/edit_event.html',
                controller: 'NewEventCtrl'
            })
            .when('/events/:id', {
                templateUrl: 'partials/event.html',
                controller: 'ViewEventCtrl'
            })
            .when("/events/:id/edit", {
                templateUrl: 'partials/edit_event.html',
                controller: 'EditEventCtrl'
            })
            .when("/pages", {
                templateUrl: 'partials/pages.html',
                controller: 'ShowPagesController'
            })
            .when("/pages/:id", {
                templateUrl: 'partials/page.html',
                controller: 'PageCtrl'
            })
            .when("/pages/:id/edit", {
                templateUrl: 'partials/edit_page.html',
                controller: "EditPageCtrl"
            })
            .when("/", {
                templateUrl: 'partials/cards.html',
                controller: 'AllCardCtrl'
            })
            .when("/cards/:id/edit", {
                templateUrl: 'partials/edit_card.html',
                controller: 'EditCardCtrl'
            })
            .when("/profile", {
                templateUrl: 'partials/other_profiles.html',
                controller: 'ShowUserCtrl'
            })
            .when("/profile/edit", {
                templateUrl: 'partials/edit_profile.html',
                controller: 'EditProfileCtrl'
            })
            .when("/profile/:id", {
                templateUrl: 'partials/other_profiles.html',
                controller: 'ShowUserCtrl'
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
    })
    .filter('capitalize', function() {
        return function(input) {
            return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
        }
    });
