'use strict';

var userControllers = angular.module('userControllers', []);

userControllers.controller("CreateUserCtrl", ['$scope', 'User', function($scope, User) {
    $scope.roles = "READ_COMMENT,NEW_COMMENT,READ_FEED,NEW_FEED,EDIT_FEED,READ_LINK,NEW_LINK,READ_PAGE,NEW_PAGE,EDIT_PAGE,READ_POST,NEW_POST,EDIT_POST,NEW_STUDYFIELD,READ_STUDYFIELD,READ_UPLOAD,NEW_UPLOAD,READ_ALL_FILES,READ_FILE,READ_SELF,READ_USER,READ_ALL_USERS,READ_USER,NEW_USER,DELETE_USER]";
    $scope.create = function() {
        var userInfo = {
            email: $scope.user.email,
            gender: $scope.user.gender,
            firstname: $scope.user.firstname,
            surname: $scope.user.surname,
            showEmail: true,
            enabled: true
        };

        User.create({
            user: userInfo,
            accountLocked: false,
            grantedAuthorities: $scope.roles.split(","),//$scope.user.roles.split(","),
            passwordHash: $scope.user.password
        });
    }
}]);

userControllers.controller("UserInfoCtrl", ['$scope', "User", function($scope, User) {
    $scope.me = User.find(function(data) {
        if (!data.email) {
            redirectLogin();
            return;
        }
        if (data.profilePictureId) {
            data.profilePictureUrl = "/rest/v1/uploads/" + data.profilePictureId;
        } else {
            data.profilePictureUrl = "img/user_placeholder.png";
        }
        if (data.profileHeaderPictureId) {
            data.profileHeaderPictureUrl = "/rest/v1/uploads/" + data.profileHeaderPictureId;
        } else {
            data.profileHeaderPictureUrl = "img/pageimage_placeholder.png";
        }
    }, redirectLogin);
    $scope.logout = function() {
        User.logout(redirectLogin);
    };
    $scope.addToCard = function(){
        $('.add-to-card-wrap').fadeIn();
    }; // END add to card
    $scope.openNewPost = function(doo){
        $('.new-post-wrap').fadeIn();
        $('#darkOverlay').fadeIn();
        // Sjekker om shortcuts skal kjøre eller ikke
        if( doo != 0) $scope.openShortcuts(1);
    };
    $scope.closePopup = function(){
        $('.popup').fadeOut();
        $('#darkOverlay').fadeOut();
    };

    var addNewOpen = false;
    $scope.openShortcuts = function(dark){

        if( !addNewOpen ){
            $('#darkOverlay').fadeIn();
            $('#newPostBtn').stop().animate({'bottom': '60px', 'opacity': '1'}, 300);
            $('#newPageBtn').delay(100).animate({'bottom': '110px', 'opacity': '1'}, 300);
            $('#newEventBtn').delay(200).animate({'bottom': '160px', 'opacity': '1'}, 300);
            $('#newSearchBtn').delay(300).animate({'bottom': '210px', 'opacity': '1'}, 300);
            addNewOpen = true;
        }else{
            if( dark != 1) $('#darkOverlay').fadeOut();
            $('#newSearchBtn').animate({'bottom': '200px', 'opacity': '0'}, 300);
            $('#newEventBtn').delay(100).animate({'bottom': '150px', 'opacity': '0'}, 300);
            $('#newPageBtn').delay(200).animate({'bottom': '100px', 'opacity': '0'}, 300);
            $('#newPostBtn').delay(300).animate({'bottom': '50px', 'opacity': '0'}, 300);
            addNewOpen = false;
        }

    }; // END openShortcuts

}]);


userControllers.controller("ShowUserCtrl", ['$scope', '$routeParams', 'User', function($scope, $routeParams, User) {
    if ($routeParams.id) {
        $scope.user = User.find({userId: $routeParams.id}, function(data) {
            if (data.profilePictureId) {
                data.profilePictureUrl = "/rest/v1/uploads/" + data.profilePictureId;
            } else {
                data.profilePictureUrl = "img/user_placeholder.png";
            }
            if (data.profileHeaderPictureId) {
                data.profileHeaderPictureUrl = "/rest/v1/uploads/" + data.profileHeaderPictureId;
            } else {
                data.profileHeaderPictureUrl = "img/pageimage_placeholder.png";
            }
        });
    } else {
        $scope.user = $scope.me; // avoid showing your own name before loading the other person's name
    }

    $scope.setShowContactInfo = function(val) {
        $scope.showContactInfo = val;
    }
}]);

userControllers.controller("EditProfileCtrl", ['$scope', '$routeParams', 'User', 'Upload', function($scope, $routeParams, User, Upload) {
    var datetimepicker = $('#datetimepicker1');
    var dtpData = datetimepicker.data("DateTimePicker");

    datetimepicker.datetimepicker({
        format: 'DD/MM/YYYY'
    });

    $scope.me.$promise.then(function() {
        //$('#gender-field').val($scope.me.gender);
        if ($scope.me.born) {
            dtpData.date(new Date($scope.me.born));
        }
        $scope.user = {
            email: $scope.me.email,
            gender: $scope.me.gender,
            city: $scope.me.city
        };
    });

    $scope.updateProfile = function() {
        var updatedInfo = {};
        angular.forEach($scope.editUserForm, function(value, key) {
            if(key[0] == '$') return;
            if (value && !value.$pristine)
                updatedInfo[key] = value;
        });
        console.log(updatedInfo);
    };

    $scope.uploadFile = function(file) {
        Upload.upload({
            url: "/rest/v1/uploads",
            data: {
                file: file,
                name: file.name,
                attachment: false
            }
        }).then(function(result) {
            console.log(result);
            var extra = result.data.extra;
            User.update({profilePictureId: extra.id}, function(result) {
                console.log(result);
            });
        });
    }
}]);