'use strict';

var cardControllers = angular.module('cardControllers', []);

cardControllers.controller("AllCardCtrl", ['$scope', function($scope) {
    $scope.setTitle("Cards");
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

cardControllers.controller("AddCardCtrl", ['$scope', 'Card', 'Page', function($scope, Card, Page) {
    Page.all({maxResults: 1000}, function(pages) {
        $scope.allPages = pages;
    });
    $scope.createCard = function() {
        var filter = [];
        if ($scope.newcard.tags) {
            $scope.newcard.tags.forEach(function (tag) {
                filter.push("#" + tag.id);
            });
        }
        if ($scope.newcard.pages) {
            $scope.newcard.pages.forEach(function(page){
                filter.push('@' + page.id);
            });
        }
        if (filter.length < 1) {
            alert("You need either a tag or page to create a card.");
        }
        Card.create({
            userId: $scope.me.id,
            name: $scope.newcard.name,
            description: $scope.newcard.description,
            filter: filter
        }, function() {
            $scope.updateCards();
            $scope.closePopup();
        });
    }
}]);

cardControllers.controller("EditCardCtrl", ['$scope', 'Card', '$routeParams', function($scope, Card, $routeParams){
    $scope.card = Card.get({ cardId: $routeParams.id });


}]);