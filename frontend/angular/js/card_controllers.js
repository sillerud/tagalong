'use strict';

var cardControllers = angular.module('cardControllers', []);

cardControllers.controller("AllCardCtrl", ['$scope', 'Card', function($scope, Card) {
    // Åpne og lukke edit/add new card
    $scope.addCard = function() {
        $('.edit-card-wrap').fadeIn();
        $('#darkOverlay').fadeIn();
    };
    $scope.cards = Card.all();
    // ESC
    $(document).keyup(function(e) {
        if ( e.keyCode == 27) {
            $scope.closeCard();
        }
    });

    $scope.closeCard = function(){
        $('.edit-card-wrap').fadeOut();
        $('#darkOverlay').fadeOut();
    };
}]);

cardControllers.controller("AddCardCtrl", ['$scope', 'Card', function($scope, Card) {
    var filter = [];

    filter.push("#java");

    $scope.createCard = function() {
        Card.create({
            userId: $scope.me.id,
            name: $scope.newcard.title,
            description: $scope.newcard.description,
            filter: filter
        });
    }
}]);