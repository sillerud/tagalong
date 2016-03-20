'use strict';

var userControllers = angular.module('userControllers', []);

userControllers.controller("CreateUserCtrl", ['$scope', 'User', function($scope, User) {
    $scope.roles = "READ_COMMENT,NEW_COMMENT,READ_FEED,NEW_FEED,EDIT_FEED,READ_LINK,NEW_LINK,READ_PAGE,NEW_PAGE,EDIT_PAGE,READ_POST,NEW_POST,EDIT_POST,NEW_STUDYFIELD,READ_STUDYFIELD,READ_UPLOAD,NEW_UPLOAD,READ_ALL_FILES,READ_FILE,READ_SELF,READ_USER,READ_ALL_USERS,READ_USER,NEW_USER,DELETE_USER]";
    $scope.create = function() {
        var user = {
            firstname: $scope.user.firstname,
            surname: $scope.user.surname,
            email: $scope.user.email,
            password: $scope.user.password,
            gender: $scope.user.gender,
            accountLocked: false,
            enabled: true,
            authorities: $scope.roles.split(",")
        };
        User.create(user);
    }
}]);

userControllers.controller("ShowUserCtrl", ['$scope', '$rootScope', '$routeParams', 'User', function($scope, $rootScope, $routeParams, User) {
    if ($routeParams.id) {
        $scope.user = User.find({userId: $routeParams.id}, function(data) {
            data.profilePictureUrl = getUploadUrl(data.profilePictureId, "img/user_placeholder.png");
            data.profileHeaderPictureUrl = getUploadUrl(data.profileHeaderPictureId);
        });
    } else {
        $scope.user = $scope.me; // avoid showing your own name before loading the other person's name
    }
    $rootScope.breadcrumb = {name: "Profile", url: "#/profile"};

    $scope.setShowContactInfo = function(val) {
        $scope.showContactInfo = val;
    }
}]);

userControllers.controller("EditProfileCtrl", ['$scope', '$rootScope', '$routeParams', 'User', 'Upload', 'Static', function($scope, $rootScope, $routeParams, User, Upload, Static) {
    var datetimepicker = $('#bornDate');
    var studyStartYear = $('#studyStartYear');

    datetimepicker.datetimepicker({
        format: 'DD/MM/YYYY'
    });
    studyStartYear.datetimepicker({
        format: 'YYYY',
        minDate: moment().subtract(5, 'years'),
        maxDate: moment()
    });

    var dtpData = datetimepicker.data("DateTimePicker");

    $rootScope.afterSelfUpdate = function() {
        if ($scope.me.born) {
            dtpData.date(moment($scope.me.born));
        }
        $scope.user = {
            email: $scope.me.email,
            gender: $scope.me.gender,
            city: $scope.me.city,
            interests: $scope.me.interests,
            personalInfo: $scope.me.personalInfo,
            contactInfo: $scope.me.contactInfo.slice(0)
        };
        $scope.studyfields.getByIds([$scope.me.studyFieldId]).then(function(studyFields) {
            $scope.user.studyField = studyFields[0];
        });
    };

    $scope.openCropDialog = function() {
        $("#image-crop").modal("show");
    };

    $scope.closeCropDialog = function () {
        $("#image-crop").modal("hide");
    };

    $scope.updateProfile = function() {
        var updatedInfo = {};
        angular.forEach($scope.user, function(value, key) {
            if (key == "studyField") {
                value = value.id;
            }
            if ($.isArray(value)) {
                if (!($(value).not($scope.me[key]).length === 0 && $($scope.me[key]).not(value).length === 0)) {
                    updatedInfo[key] = value;
                }
            } else if ($scope.me[key] != value && key != 'email') {
                updatedInfo[key] = value;
            }
        });
        if (dtpData.date() && dtpData.date().valueOf() != $scope.me.born) {
            updatedInfo.born = dtpData.date().utcOffset();
        }
        if (!$.isEmptyObject(updatedInfo)) {
            User.update(updatedInfo, $scope.updateSelf);
        }
    };

    $scope.uploadFile = function(data, name) {
        Upload.upload({
            url: "/rest/v1/uploads",
            data: {
                file: Upload.dataUrltoBlob(data, name),
                name: name,
                imageType: 'PROFILE_IMAGE'
            }
        }).then(function(result) {
            var extra = result.data.extra;
            User.update({profilePictureId: extra.id}, $scope.updateSelf);
        });
    };

    $scope.addItem = function() {
        $scope.user.contactInfo.push({description: "", value: ""});
    };
    $scope.deleteItem = function(item) {
        $scope.user.contactInfo.splice($scope.user.contactInfo.indexOf(item), 1);
    };
}]);

userControllers.controller('UserPostFeed', ['$scope', '$q', 'Post', function($scope, $q, Post) {
    function mapPost(post) {
        post.user = $scope.user;
        if (post.user.id == $scope.me.id) {
            post.delete = function() {
                Post.remove({postId: post.id});
            };
        }
        if (post.upvotes) {
            post.upvotes.forEach(function(value) {
                if (value.userId == $scope.me.id) {
                    post.upvoted = "tagged-along";
                }
            });
        }
        post.tags = [];
        post.tags = $scope.allTags.getByIds(post.tagIds);
        post.upvote = function() {
            post.$upvote({upvote: !post.upvoted}, updatePosts);
        };
    }
    function updatePosts()  {
        $scope.posts = Post.find({parentId: $scope.user.id}, function(data) {
            data.forEach(mapPost);
        });
    }
    $q.all([$scope.me.$promise, $scope.user.$promise, $scope.allTags.$promise]).then(updatePosts);
}]);