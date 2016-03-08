'use strict';

var eventControllers = angular.module('eventControllers', []);

eventControllers.controller('ViewEventsCtrl', ['$scope', 'Event', function ($scope, Event) {
    $scope.getRandomNumber = function(){
        return 4;
    }
}]);

eventControllers.controller('EditEventCtrl', ['$scope', 'Event', function ($scope, Event) {
    var datetimepicker = $('#datetimepicker1').datetimepicker();
}]);