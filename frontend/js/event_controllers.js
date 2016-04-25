'use strict';

var eventControllers = angular.module('eventControllers', []);

eventControllers.controller('ViewEventsCtrl', ['$scope', 'Event', function ($scope, Event) {
    $scope.setTitle('Events');
    $scope.me.$promise.then(function() {
        $scope.filter = {
            order: "recent"
        };
        function mapEvent(event) {
            event.coverImageUrl = getUploadUrl(event.coverImageId);
            $scope.allTags.getByIds(event.tagIds).then(function(tags){
                event.tags = tags;
            });
            if (event.attending && event.attending.length > 0) {
                event.selfAttending = $.inArray($scope.me.id, event.attending) != -1;
                event.attendingStyle = {
                    'background-color': event.selfAttending ? 'green' : '#333'
                }
            }
            event.attend = function() {
                console.log({eventId: event.id, attend: !event.selfAttending});
                Event.attendEvent({eventId: event.id, attend: !event.selfAttending}, {}, $scope.filterUpdated);
            };
        }

        function mapEvents(events) {
            events.forEach(mapEvent);
            $scope.events = events;
        }

        $scope.events = Event.all(mapEvents);
        $scope.filterUpdated = function() {
            Event.all({orderBy: $scope.filter.order}, mapEvents);
        }
    });
}]);

eventControllers.controller('ViewEventCtrl', ['$scope', '$routeParams', 'Event', function($scope, $routeParams, Event) {
    $scope.event = Event.getById({eventId: $routeParams.id}, function(event) {
        event.coverImageUrl = getUploadUrl(event.coverImageId);
        event.canEdit = canEdit(event.accessLevel);
        event.canWrite = canWrite(event.accessLevel);
        $scope.setTitle(event.title);
        $scope.allTags.getByIds(event.tagIds).then(function(tags) {
            $scope.tags = tags;
        });
    });
}]);

function updateEvent(originalId, $scope, Event, Upload) {
    $scope.isEdit = function() {
        return originalId ? true : false;
    };
    $scope.setTitle($scope.isEdit() ? 'Edit event' : 'Create event');
    var startDatePicker = $('#eventStartDate');
    startDatePicker.datetimepicker();
    var endDatePicker = $('#eventEndDate');
    endDatePicker.datetimepicker();
    startDatePicker = startDatePicker.data("DateTimePicker");

    endDatePicker = endDatePicker.data("DateTimePicker");

    $scope.cropDialogState = function(cropId, state) {
        $(cropId).modal(state);
    };

    $scope.uploadFile = function(data, name) {
        Upload.upload({
            url: "/rest/v1/uploads",
            data: {
                file: Upload.dataUrltoBlob(data, name),
                name: name,
                imageType: 'HEADER_IMAGE'
            }
        }).then(function(result) {
            console.log(result);
            $scope.event.coverImageId = result.data.extra.id;
            $scope.event.coverImageUrl = getUploadUrl($scope.event.coverImageId);
            $scope.cropDialogState('#header-image-crop', 'hide');
        });
    };

    if (originalId) {
        $scope.event = Event.getById({eventId: originalId}, function(event) {
            startDatePicker.date(moment(event.startDate));
            if (event.endDate && event.endDate != 0) {
                endDatePicker.date(moment(event.endDate));
            }
            $scope.allTags.getByIds(event.tagIds).then(function(tags) {
                event.tags = tags;
            });
            event.coverImageUrl = getUploadUrl(event.coverImageId);
        });
    } else {
        $scope.event = {};
        $scope.event.coverImageUrl = getUploadUrl($scope.event.coverImageId);
    }
    
    $scope.deleteEvent = function() {
        Event.deleteEvent({eventId: $scope.event.id}, function() {
            $scope.goToUrl("#/events");
        });
    };

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
            Event.update(event, function(data) {
                window.location = "#/events/" + data.extra.id
            });
        } else {
            Event.create(event, function(data) {
                window.location = "#/events/" + data.extra.id
            });
        }
    };
}

eventControllers.controller('EditEventCtrl', ['$scope', '$routeParams', 'Event', 'Upload', function($scope, $routeParams, Event, Upload) {
    $scope.title = "Edit event";
    updateEvent($routeParams.id, $scope, Event, Upload);
}]);

eventControllers.controller('NewEventCtrl', ['$scope', 'Event', 'Upload', function($scope, Event, Upload) {
    $scope.title = "New event";
    updateEvent(null, $scope, Event, Upload);
}]);