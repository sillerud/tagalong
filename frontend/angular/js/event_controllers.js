'use strict';

var eventControllers = angular.module('eventControllers', []);

eventControllers.controller('ViewEventsCtrl', ['$scope', 'Event', function ($scope, Event) {
    $scope.getRandomNumber = function(){
        return 4;
    }
}]);

eventControllers.controller('EditEventCtrl', ['$scope', 'Event', 'Upload', function ($scope, Event, Uplaod) {
    $scope.edit = true;
    var startDatePicker = $('#eventStartDate');
    startDatePicker.datetimepicker();
    console.log("im not a error hehe1 ");
}]);

eventControllers.controller('NewEventCtrl', ['$scope', 'Event', 'Upload', function($scope, Event, Upload) {
    var startDatePicker = $('#eventStartDate');
    startDatePicker.datetimepicker();
    console.log("im not a error hehe ");
}]);