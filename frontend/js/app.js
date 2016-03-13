'use strict';

function getUploadUrl(uploadId, fallback) {
    if (uploadId) {
        return "/rest/v1/uploads/" + uploadId;
    } else {
        return fallback ? fallback : 'img/placeholder_big.jpg';
    }
}

function genericValueMapping(value, key) {
    if (key == 'tagIds') {
        var tagIds = [];
        value.forEach(function(tag) {
            tagIds.push(tag.id);
        });
        if (tagIds.length > 0)
            this.tagIds = tagIds;
    } else {
        this[key] = value;
    }
}

angular.module('tagalong', [
        'ngRoute',
        'ngFileUpload',
        'ui.select',
        'ngImgCrop',
        'ngSanitize',
        'ng-showdown',
        'eventControllers',
        'eventServices',
        'userControllers',
        'userServices',
        'cardControllers',
        'cardServices',
        'pageControllers',
        'pageServices',
        'postServices',
        'postControllers',
        'searchControllers',
        'searchServices',
        'staticServices'
    ])
    .config(['$routeProvider', function($routeProvider) {
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
            .when("/feed", {
                templateUrl: 'partials/feed.html',
                controller: 'FeedCtrl'
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
