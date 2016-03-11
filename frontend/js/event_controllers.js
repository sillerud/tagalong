'use strict';

var eventControllers = angular.module('eventControllers', []);

eventControllers.controller('ViewEventsCtrl', ['$scope', 'Event', function ($scope, Event) {
    $scope.me.$promise.then(function() {
        $scope.filter = {
            order: "recent"
        };
        function mapEvent(event) {
            event.coverImageUrl = getUploadUrl(event.coverImageId);
            event.tags = [];
            event.tagIds.forEach(function(tagId) {
                this.push($scope.allTags.getById(tagId));
            }, event.tags);
            if (event.attending && event.attending.length > 0) {
                event.attendingStyle = {
                    'background-color': $.inArray($scope.me.id, event.attending) != -1 ? 'green' : '#FFF'
                }
            }
        }

        function mapEvents(events) {
            events.forEach(mapEvent);
        }

        $scope.events = Event.all(mapEvents);
        $scope.filterUpdated = function() {
            $scope.events = Event.all({orderBy: $scope.filter.order}, mapEvents);
        }
    });
}]);

eventControllers.controller('ViewEventCtrl', ['$scope', '$routeParams', 'Event', function($scope, $routeParams, Event) {
    $scope.event = Event.getById({eventId: $routeParams.id}, function(event) {
        event.coverImageUrl = getUploadUrl(event.coverImageId);
        event.tags = [];
        event.tagIds.forEach(function(tagId) {
            event.tags.push($scope.allTags.getById(tagId));
        });
    });
}]);

function updateEvent(originalId, $scope, Event, Static, Upload) {
    $scope.title = "Create event";

    var startDatePicker = $('#eventStartDate');
    startDatePicker.datetimepicker();
    var endDatePicker = $('#eventEndDate');
    endDatePicker.datetimepicker();
    startDatePicker = startDatePicker.data("DateTimePicker");
    endDatePicker = endDatePicker.data("DateTimePicker");

    if (originalId) {
        $scope.event = Event.getById({eventId: originalId}, function(event) {
            console.log(event);
            startDatePicker.date(moment(event.startDate));
            if (event.endDate && event.endDate != 0) {
                endDatePicker.date(moment(event.endDate));
            }
            var tagIds = [];
            event.tagIds.forEach(function(tag) {
                tagIds.push($scope.allTags.getById(tag));
            });
            event.tagIds = tagIds;
        });
    } else {
        $scope.event = {};
    }

    $scope.createEvent = function() {
        var event = {};
        angular.forEach($scope.event, genericValueMapping, event);
        if (!startDatePicker.date()) {
            // TODO: Inform user
            console.log("No start date");
            return;
        }
        event.startDate = startDatePicker.date();
        if (endDatePicker.date()) {
            console.log(endDatePicker.date());
            event.endDate = endDatePicker.date();
        }
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
        if (originalId) {
            Event.update(event);
        } else {
            Event.create(event);
        }
    };
}

eventControllers.controller('EditEventCtrl', ['$scope', '$routeParams', 'Event', 'Static', 'Upload', function($scope, $routeParams, Event, Static, Upload) {
    updateEvent($routeParams.id, $scope, Event, Static, Upload)
}]);

eventControllers.controller('NewEventCtrl', ['$scope', 'Event', 'Static', 'Upload', function($scope, Event, Static, Upload) {
    updateEvent(null, $scope, Event, Static, Upload);
}]);