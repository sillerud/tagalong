'use strict';

if( localStorage.styleColor == 0 || localStorage.styleColor == null){
    localStorage.styleColor = 'blue';
}

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

userControllers.controller("UserInfoCtrl", ['$scope', "User", 'Static', 'Card', function($scope, User, Static, Card) {
    $scope.me = User.find(function(data) {
        if (!data.id) {
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


        $scope.changeStylesheet = function(color){
            localStorage.styleColor = color;
            $scope.stylesheetUrl = '../statisk/css/dynamic_colors_' + localStorage.styleColor + '.css';
        };

        $scope.changeStylesheet(localStorage.styleColor);

    }, redirectLogin);

    $scope.allTags = Static.getAllTags();
    $scope.allTags.getById = function(id) {
        return this.find(function(element) {
            return element.id == id;
        });
    };
    $scope.studyfields = Static.getAllStudyFields();
    $scope.studyfields.getById = function(id) {
        return this.find(function(element) {
            return element.id == id;
        });
    };

    $scope.logout = function() {
        User.logout(redirectLogin);
    };
    $scope.addToCard = function(){
        $('.add-to-card-wrap').fadeIn();
        $('#darkOverlay').fadeIn();
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
        $scope.closeShortcuts();
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
            $scope.closeShortcuts();
        }

    }; // END openShortcuts
    $scope.closeShortcuts = function(){
        $('#newSearchBtn').animate({'bottom': '200px', 'opacity': '0'}, 300);
        $('#newEventBtn').delay(100).animate({'bottom': '150px', 'opacity': '0'}, 300);
        $('#newPageBtn').delay(200).animate({'bottom': '100px', 'opacity': '0'}, 300);
        $('#newPostBtn').delay(300).animate({'bottom': '50px', 'opacity': '0'}, 300);
        addNewOpen = false;
    };


    var dropdownToggle = false;
    var dropdown = $('.dropdown-content');
    var dropdownArrow = $('.arrow-up');
    $scope.openNotifications = function() {
        dropdownArrow.fadeIn();
        dropdown.fadeIn();

        dropdownToggle = true;
    };

    $(document).mouseup(function (e) {
        if (dropdownToggle && !dropdown.is(e.target) && dropdown.has(e.target).length === 0) {
            dropdown.fadeOut();
            dropdownArrow.fadeOut();
            dropdownToggle = false;
        }
    });
    $scope.cards = Card.all();
    $scope.openCardShortcuts = function(){
        $('.card-shortcut-wrap').fadeIn();
    };

}]);


userControllers.controller("ShowUserCtrl", ['$scope', '$rootScope', '$routeParams', 'User', function($scope, $rootScope, $routeParams, User) {
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
    $rootScope.breadcrumb = {name: "Profile", url: "#/profile"};

    $scope.setShowContactInfo = function(val) {
        $scope.showContactInfo = val;
    }
}]);

userControllers.controller("EditProfileCtrl", ['$scope', '$routeParams', '$q', 'User', 'Upload', 'Static', function($scope, $routeParams, $q, User, Upload, Static) {
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

    $q.all([$scope.me.$promise, $scope.studyfields.$promise]).then(function() {
        //$('#gender-field').val($scope.me.gender);
        if ($scope.me.born) {
            dtpData.date(moment($scope.me.born));
        }
        $scope.user = {
            email: $scope.me.email,
            gender: $scope.me.gender,
            city: $scope.me.city,
            studyFieldId: $scope.studyfields.getById($scope.me.studyFieldId),
            contactInfo: $scope.me.contactInfo.slice(0)
        };
    });

    $scope.updateProfile = function() {
        var updatedInfo = {};
        //updatedInfo.id = $scope.me.id;
        angular.forEach($scope.user, function(value, key) {
            if (key == "studyFieldId") {
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
        if (dtpData.date() && dtpData.date().utcOffset(120).valueOf() != $scope.me.born) {
            console.log(dtpData.date().utcOffset(120).valueOf());
            updatedInfo.born = dtpData.date().utcOffset(120).valueOf();
        }
        if (!$.isEmptyObject(updatedInfo)) {
            User.update(updatedInfo);
        }
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
    };

    $scope.addItem = function() {
        $scope.user.contactInfo.push({description: "", value: ""});
    };
    $scope.deleteItem = function(item) {
        $scope.user.contactInfo.splice($scope.user.contactInfo.indexOf(item), 1);
    };
}]);