'use strict';

var searchControllers = angular.module('searchControllers', []);

searchControllers.controller("QuickSearchCtrl", ['$scope', 'Search', function($scope, Search) {
    function closeOverlay(thatClass){
        $(thatClass).fadeOut();
    }

    var searchField = $("#quickSearch");
    var timerId = 0;
    var lastKeyPress;
    var resultUpdated = true;
    var lastAutocompleted = false;

    $scope.closeSearch = function(){ // Lukke
        closeOverlay('.search-overlay-wrap');
        clearInterval(timerId);
        searchField.val("");
        delete $scope.searchResults;
    };

    function mapResult(result) {
        if (result.type == "user") {
            result.url = "profile/" + result.data.id;
            result.name = result.data.firstname + " " + result.data.surname;
            if (result.data.profilePictureId) {
                result.thumbnailUrl = "/rest/v1/uploads/" + result.data.profilePictureId;
            } else {
                result.thumbnailUrl = "img/user_placeholder.png";
            }
            if (result.data.studyFieldId) {
                result.studyFieldDisplay = $scope.studyfields.getById(result.data.studyFieldId).description;
            }
        } else if (result.type == "page") {
            result.url = "pages/" + result.data.customUrl;
            result.name = result.data.name;
        } else if (result.type == "tag") {
            var current = result.data;
            result.url = "feed/" + result.data.name;
            result.name = "";
            while (current) {
                result.name = "/#" + current.name + result.name;
                current = current.parent;
            }
            result.name = result.name.substring(1);
            if (result.data.description) {
                result.name += " - " + result.data.description;
            }
        }
    }

    $scope.openSearch = function(){ // Åpne
        $scope.closePopup();
        $('.search-overlay-wrap').fadeIn();
        searchField.focus();

        timerId = setInterval(function() {
            if (lastKeyPress + 200 < Date.now() && !resultUpdated) {
                if ($scope.searchText) {
                    Search.queryAll({query: $scope.searchText}, function(data) {
                        data.forEach(mapResult);
                        $scope.searchResults = data;
                    });
                }
                resultUpdated = true;
            }
        }, 100);
    };

    //S = 83
    $(document).keyup(function(e) {
        var input = $('input');
        var textarea = $('textarea');
        if( input.is(':focus') || textarea.is(':focus') ){

        }else{
            if (e.key == 's' || e.keyCode == 83 || e.keyCode == 115) {
                $scope.openSearch();
            }
        }

    });

    $scope.updateSearchResults = function() {
        lastKeyPress = Date.now();
        resultUpdated = false;
        lastAutocompleted = false;
        if (!$scope.searchText) {
            delete $scope.searchResults;
        }
    };

    $scope.runAutoComplete = function($event) {
        /*if (!$scope.searchResults) {
         return;
         }
         if ($event.ctrlKey && $event.keyCode == 32) {
         if ($scope.searchResults) {
         $("#quickSearch").val($scope.searchResults[0].name);
         $scope.searchResults = [$scope.searchResults[0]];
         lastAutocompleted = true;
         }
         }*/
        //maybe add quick search thingies for opening stuff like itslearning?
        if ($event.keyCode == 13) {
            if (searchField.val() == "its") {
                window.location = "https://woact.itslearning.com/";
            }
            if ($scope.searchResults && $scope.searchResults.length == 1  && $scope.searchResults[0].url) {
                window.location = "#/" + $scope.searchResults[0].url;
                $scope.closeSearch();
            }
        }
        // ESC
        if ($event.keyCode == 27) {
            $scope.closeSearch();
        }
    };
}]);