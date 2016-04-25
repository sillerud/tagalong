'use strict';

var userControllers = angular.module('userControllers', []);

userControllers.controller("CreateUserCtrl", ['$scope', 'User', function($scope, User) {
    $scope.user = {};
    $scope.user.roles = "READ_COMMENT,NEW_COMMENT,READ_FEED,NEW_FEED,EDIT_FEED,READ_LINK,NEW_LINK,READ_PAGE,NEW_PAGE,EDIT_PAGE,READ_POST,NEW_POST,EDIT_POST,NEW_STUDYFIELD,READ_STUDYFIELD,READ_UPLOAD,NEW_UPLOAD,READ_ALL_FILES,READ_FILE,READ_SELF,READ_USER,READ_ALL_USERS,READ_USER,NEW_USER,DELETE_USER]";
    $scope.create = function() {
        var scopeUser = $scope.user;
        var user = {
            firstname: scopeUser.firstname,
            surname: scopeUser.surname,
            email: scopeUser.email,
            password: scopeUser.password,
            gender: scopeUser.gender,
            accountLocked: false,
            enabled: true,
            authorities: scopeUser.roles.split(",")
        };
        User.create(user);
    }
}]);

userControllers.controller("ShowUserCtrl", ['$scope', '$rootScope', '$routeParams', 'User', 'feedHelper', function($scope, $rootScope, $routeParams, User, feedHelper) {
    if ($routeParams.id) {
        $scope.user = User.find({userId: $routeParams.id}, function(data) {
            data.profilePictureUrl = getUploadUrl(data.profilePictureId, "img/user_placeholder.png");
            data.profileHeaderPictureUrl = getUploadUrl(data.profileHeaderPictureId);
        });
    } else {
        $scope.user = $scope.me; // avoid showing your own name before loading the other person's name
    }

    $scope.user.$promise.then(function (user) {
        $scope.setTitle(user.firstname + " " + user.surname);
        feedHelper.updatePosts($scope.user.id, $scope);
    });

    $scope.setBreadcrumb("Profile", "#/profile");

    $scope.setShowContactInfo = function(val) {
        $scope.showContactInfo = val;
    }
}]);

userControllers.controller("EditProfileCtrl", ['$scope', '$routeParams', 'User', 'Upload', 'sessionFactory', function($scope, $routeParams, User, Upload, sessionFactory) {
    $scope.setTitle("Edit profile");
    var bornDate = $('#bornDate');
    var studyStartYear = $('#studyStartYear');

    bornDate.datetimepicker({
        format: 'DD/MM/YYYY'
    });
    studyStartYear.datetimepicker({
        format: 'YYYY',
        minDate: moment().subtract(5, 'years'),
        maxDate: moment()
    });

    bornDate = bornDate.data("DateTimePicker");

    $scope.me.$promise.then(function() {
        $scope.user = {};
        $scope.user = angular.copy($scope.me, $scope.user);
        $scope.studyfields.getByIds([$scope.me.studyFieldId]).then(function(studyFields) {
            $scope.user.studyField = studyFields[0];
        });
        if ($scope.me.born) {
            delete $scope.user.born;
            bornDate.date(moment($scope.me.born));
        }
        if (!$scope.user.contactInfo) {
            $scope.user.contactInfo = [];
        }
    });

    $scope.cropDialogState = function(cropId, state) {
        $(cropId).modal(state);
    };

    $scope.updateProfile = function() {
        var updatedInfo = {};
        angular.forEach($scope.user, function(value, key) {
            if (key == "studyField") {
                key = "studyFieldId";
                value = value.id;
            }
            if ($.isArray(value)) {
                updatedInfo[key] = value; // Disable validation
            } else if ($scope.me[key] != value && key != 'email') {
                updatedInfo[key] = value;
            }
        });
        if (bornDate.date() && bornDate.date().valueOf() != $scope.me.born) {
            updatedInfo.born = bornDate.date();
        }
        if (!$.isEmptyObject(updatedInfo)) {
            User.update(updatedInfo, function() {
                $scope.goToUrl('#/profile/' + $scope.user.id);
            });
        }
    };

    $scope.uploadFile = function(data, name, type) {
        Upload.upload({
            url: "/rest/v1/uploads",
            data: {
                file: Upload.dataUrltoBlob(data, name),
                name: name,
                imageType: type 
            }
        }).then(function(result) {
            var extra = result.data.extra;
            if (type == 'PROFILE_IMAGE') {
                User.update({profilePictureId: extra.id}, sessionFactory.validate);
            } else {
                User.update({profileHeaderPictureId: extra.id}, sessionFactory.validate);
            }
        });
    };

    $scope.addItem = function() {
        $scope.user.contactInfo.push({});
    };
    $scope.deleteItem = function(item) {
        $scope.user.contactInfo.splice($scope.user.contactInfo.indexOf(item), 1);
    };
}]);