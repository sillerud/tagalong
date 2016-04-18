'use strict';

var postControllers = angular.module('postControllers', []);

postControllers.controller("FeedCtrl", ['$scope', 'Post', 'User', function($scope, Post, User) {
    var filteredTags = [];
    var filteredPages = [];
    function mapPost(post) {
        if (post.upvotes) {
            post.upvotes.forEach(function(value) {
                if (value.userId == $scope.me.id) {
                    post.upvoted = "tagged-along";
                }
            });
        }
        post.tags = $scope.allTags.getByIds(post.tagIds);
        post.upvote = function() {
            post.$upvote({upvote: !post.upvoted}, function() {
                $scope.selectCard($scope.currentCard);
            });
        };
    }
    $scope.selectCard = function(card) {
        filteredTags = [];
        filteredPages = [];
        $scope.currentCard = card;
        console.log(card);
        $scope.cards.forEach(function(card) {
            if (card == $scope.currentCard)  {
                card.selected = "card-selected"
            } else {
                card.selected = "";
            }
        });
        console.log($scope.currentCard);
        card.filter.forEach(function(filter) {
            if (filter.startsWith('#')) {
                filteredTags.push(filter.substring(1));
            }
        });
        Post.getByTags({tags: filteredTags.join()}, function(posts) {
            posts.forEach(function(post) {
                User.find({userId: post.userId}, function(user) {
                    user.profilePictureUrl = getUploadUrl(user.profilePictureId, "img/user_placeholder.png");
                    if (user.id == $scope.me.id) {
                        post.delete = function() {
                            Post.remove({postId: post.id});
                        };
                    }
                    post.user = user;
                });
                mapPost(post);
            });
            $scope.posts = posts;
        });
    };

    $scope.cards.$promise.then(function(cards) {
        $scope.me.$promise.then(function() {
            $scope.selectCard(cards[0]);
        });
    });
    var openSidebar = false;
    $scope.sidebarFeed = function(){
        if( openSidebar ){
            $('.sidebar-all-card').slideUp(300);
            openSidebar = false;
            $('.sidebar-mobile').text('Show your cards');
        }
        else{
          $('.sidebar-all-card').slideDown(300);
          openSidebar = true;
          $('.sidebar-mobile').text('Hide your cards');
        }
    }; // END sidebarFeed

}]); // END postControllers