'use strict';

var postControllers = angular.module('postControllers', []);

postControllers.controller("FeedCtrl", ['$scope', '$routeParams', 'Post', 'User', 'Comment', 'Page', function($scope, $routeParams, Post, User, Comment, Page) {
    $scope.filteredTags = [];
    $scope.filteredPages = [];
    function mapPost(post) {
        User.find({userId: post.userId}, function(user) {
            user.profilePictureUrl = getUploadUrl(user.profilePictureId, "img/placeholder_thumb.jpg");
            if (user.id == $scope.me.id) {
                post.delete = function() {
                    Post.remove({postId: post.id}, refresh);
                };
            }
            mapPostColors(user);
            post.user = user;
        });
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
        $scope.allTags.getByIds(post.tagIds).then(function (tags) {
            post.tags = tags;
        });
        post.upvote = function() {
            Post.upvote({postId: post.id, upvote: !post.upvoted}, {}, refresh);
        };
        post.comments = Comment.getByPost({postId: post.id}, function(comments) {
            comments.forEach(function(comment) {
                User.find({userId: comment.userId}, function(user) {
                    user.profilePictureUrl = getUploadUrl(user.profilePictureId, "img/placeholder_thumb.jpg");
                    mapPostColors(user);
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
        } else if ($routeParams.feedId) {
            $scope.selectCard($scope.cards.find(function (card) {
                return card.id == $routeParams.feedId;
            }));
            $scope.setTitle('Card feed');
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
        $scope.displayPages = [];
        card.filter.forEach(function(filter) {
            if (filter.startsWith('#')) {
                $scope.filteredTags.push(filter.substring(1));
            } else if (filter.startsWith('@')) {
                var pageId = filter.substring(1);
                $scope.filteredPages.push(pageId);
                $scope.displayPages.push(Page.query({pageId: pageId}));
            }
        });

        $scope.allTags.getByIds($scope.filteredTags).then(function(tags) {
            $scope.displayTags = tags;
        });

        var filter = {};
        if ($scope.filteredTags.length > 0) {
            filter.tagIds = $scope.filteredTags.join();
        }
        if ($scope.filteredPages.length > 0) {
            filter.parentIds = $scope.filteredPages.join();
        }

        Post.find(filter, function(posts) {
            posts.forEach(function(post) {
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
    $scope.hooks.postCreated.push(refresh);

}]); // END postControllers