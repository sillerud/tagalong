'use strict';

var postControllers = angular.module('postControllers', []);

postControllers.controller("FeedCtrl", ['$scope', '$routeParams', 'Post', 'User', 'Comment', function($scope, $routeParams, Post, User, Comment) {
    $scope.filteredTags = [];
    $scope.filteredPages = [];
    function mapPost(post) {
        post.comment = function() {
            Comment.create({parentId: post.id, content: post.comment_body}, refresh);
        };
        if (post.upvotes) {
            post.upvotes.forEach(function(value) {
                if (value.userId == $scope.me.id) {
                    post.upvoted = "tagged-along";
                }
            });
        }
        post.tags = $scope.allTags.getByIds(post.tagIds);
        post.upvote = function() {
            post.$upvote({upvote: !post.upvoted}, refresh);
        };
        post.comments = Comment.getByPost({postId: post.id}, function(comments) {
            comments.forEach(function(comment) {
                User.find({userId: comment.userId}, function(user) {
                    user.profilePictureUrl = getUploadUrl(user.profilePictureId, "img/user_placeholder.png");
                    comment.user = user;
                    if (comment.userId == $scope.me.id) {
                        comment.delete = function() {
                            Comment.deleteComment({commentId: comment.id}, refresh);
                        }
                    }
                });
            });
        });
    }
    
    function refresh() {
        if ($routeParams.tagId) {
            $scope.selectCard({filter: ['#' + $routeParams.tagId]});
            $scope.setTitle('Tag feed');
        } else {
            $scope.selectCard($scope.currentCard);
            $scope.setTitle('Card feed');
        }
    }
    
    $scope.selectCard = function(card) {
        $scope.filteredTags = [];
        $scope.filteredPages = [];
        $scope.currentCard = card;
        $scope.cards.forEach(function(card) {
            if (card == $scope.currentCard)  {
                card.selected = "card-selected"
            } else {
                card.selected = "";
            }
        });
        card.filter.forEach(function(filter) {
            if (filter.startsWith('#')) {
                $scope.filteredTags.push(filter.substring(1));
            }
        });

        console.log($scope.filteredTags);
        $scope.allTags.getByIds($scope.filteredTags).then(function(tags) {
            $scope.displayTags = tags;
        });
        console.log($scope.displayTags);

        Post.find({tagIds: $scope.filteredTags.join()}, function(posts) {
            posts.forEach(function(post) {
                User.find({userId: post.userId}, function(user) {
                    user.profilePictureUrl = getUploadUrl(user.profilePictureId, "img/user_placeholder.png");
                    if (user.id == $scope.me.id) {
                        post.delete = function() {
                            Post.remove({postId: post.id}, refresh);
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
            $scope.currentCard = cards[0];
            refresh();
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