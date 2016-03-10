'use strict';

var eventControllers = angular.module('eventControllers', []);

eventControllers.controller('ViewEventsCtrl', ['$scope', 'Event', function ($scope, Event) {
    $scope.getRandomNumber = function(){
        return 4;
    }
}]);

function updateEvent(originalId, $scope, Event, Static, Upload) {
    $scope.title = "Create event";
    if (originalId) {
        $scope.event = Event.getEvent(originalId);
    } else {
        $scope.event = {};
    }

    var startDatePicker = $('#eventStartDate');
    startDatePicker.datetimepicker();
    var endDatePicker = $('#eventEndDate');
    endDatePicker.datetimepicker();
    startDatePicker = startDatePicker.data("DateTimePicker");
    endDatePicker = endDatePicker.data("DateTimePicker");
    $scope.createEvent = function() {
        var event = {};
        if (!startDatePicker.date()) {
            // TODO: Inform user
            console.log("No start date");
            return;
        }
        event.startDate = startDatePicker.date();
        if (endDatePicker.date()) {
            event.endDate = endDatePicker.date();
        }
        angular.forEach($scope.event, function(value, key) {
            if (key == 'tagIds') {
                var tagIds = [];
                value.forEach(function(tag) {
                    tagIds.push(tag.id);
                });
                if (tagIds.length > 0)
                    event.tagIds = tagIds;
            } else {
                event[key] = value;
            }
        });
        console.log(event);
        if (!event.tagIds) {
            console.log("No tags");
            // TODO: Inform user
            return;
        }
        if (!event.title) {
            console.log("No title");
            // TODO: Inform user
            return;
        }
        if (!event.description) {
            console.log("No description");
            // TODO: Inform user
            return;
        }
        Event.create(event);
        //console.log(JSON.stringify(event));
    };
}

eventControllers.controller('EditEventCtrl', ['$scope', '$requestParam', 'Event', 'Static', 'Upload', function ($scope, $requestParam, Event, Static, Upload) {
    updateEvent($requestParam.id, $scope, Event, Static, Upload)
}]);

eventControllers.controller('NewEventCtrl', ['$scope', 'Event', 'Static', 'Upload', function($scope, Event, Static, Upload) {
    updateEvent(null, $scope, Event, Static, Upload);
}]);