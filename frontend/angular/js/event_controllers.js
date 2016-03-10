'use strict';

var eventControllers = angular.module('eventControllers', []);

eventControllers.controller('ViewEventsCtrl', ['$scope', 'Event', function ($scope, Event) {
    $scope.getRandomNumber = function(){
        return 4;
    }
}]);

eventControllers.controller('EditEventCtrl', ['$scope', 'Event', 'Upload', function ($scope, Event, Uplaod) {
    $scope.title = "Edit event";
    $scope.edit = true;
    var startDatePicker = $('#eventStartDate');
    startDatePicker.datetimepicker();
    var endDatePicker = $('#eventEndDate');
    endDatePicker.datetimepicker();
    console.log("im not a error hehe1 ");
}]);

eventControllers.controller('NewEventCtrl', ['$scope', 'Event', 'Upload', function($scope, Event, Upload) {
    $scope.title = "Create event";
    var startDatePicker = $('#eventStartDate');
    startDatePicker.datetimepicker();
    var endDatePicker = $('#eventEndDate');
    endDatePicker.datetimepicker();
    console.log("im not a error hehe ");
}]);