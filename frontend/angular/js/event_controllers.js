'use strict';

var eventControllers = angular.module('eventControllers', []);

eventControllers.controller('ViewEventsCtrl', ['$scope', 'Event', function ($scope, Event) {
    $scope.getRandomNumber = function(){
        return 4;
    }
}]);

eventControllers.controller('EditEventCtrl', ['$scope', '$q', 'Event', 'Static', 'Upload', function ($scope, $q, Event, Static, Uplaod) {
    $scope.title = "Edit event";
    $scope.edit = true;
    var startDatePicker = $('#eventStartDate');
    startDatePicker.datetimepicker();
    var endDatePicker = $('#eventEndDate');
    endDatePicker.datetimepicker();
}]);

eventControllers.controller('NewEventCtrl', ['$scope', 'Event', 'Static', 'Upload', function($scope, Event, Static, Upload) {
    $scope.title = "Create event";
    var startDatePicker = $('#eventStartDate');
    startDatePicker.datetimepicker();
    var endDatePicker = $('#eventEndDate');
    endDatePicker.datetimepicker();
    console.log("im not a error hehe ");
}]);