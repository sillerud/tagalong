'use strict';

var cardControllers = angular.module('cardControllers', []);

cardControllers.controller("AllCardCtrl", ['$scope', 'Card', function($scope, Card) {
    // Åpne og lukke edit/add new card
    $scope.addCard = function() {
        $('.popup').fadeOut();
        $('.edit-card-wrap').fadeIn();
        $('#darkOverlay').fadeIn();
    };
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

    $scope.createCard = function() {
        var filter = [];
        $scope.newcard.tags.forEach(function(tag) {
            filter.push("#" + tag.id);
        });
        Card.create({
            userId: $scope.me.id,
            name: $scope.newcard.name,
            description: $scope.newcard.description,
            filter: filter
        });
    }
}]);

cardControllers.controller("EditCardCtrl", ['$scope', 'Card', '$routeParams', function($scope, Card, $routeParams){
    $scope.card = Card.get({ cardId: $routeParams.id });


}]);