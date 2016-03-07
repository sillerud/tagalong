'use strict';

var eventControllers = angular.module('eventControllers', []);
var loginControllers = angular.module('loginControllers', []);
var userControllers = angular.module('userControllers', []);
var cardControllers = angular.module('cardControllers', []);
var pageControllers = angular.module('pageControllers', []);
var searchControllers = angular.module('searchControllers', []);

function redirectLogin() {
    window.location = "login.html";
}

loginControllers.controller('LoginCtrl', ['$scope', 'Login', 'User', function($scope, Login, User){
    var login = function() {
        $scope.login = function() {
            Login.login($scope.credentials,
                function(data) {
                    window.location = "./";
                },
                function(error) {
                    alert("Failed to log in. " + error);
                }
            );
        }
    };
    User.find(function(data) {
        if (data.email) {
            window.location = "./";
        } else {
            login();
        }
    }, login);
}]);
eventControllers.controller('ViewEventsCtrl', ['$scope', 'Event', function ($scope, Event) {
    $scope.getRandomNumber = function(){
        return Math.floor((Math.random()*400)+1);
    }
}]);

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
    $scope.openNewPost = function(){
        $('.new-post-wrap').fadeIn();
        $('#darkOverlay').fadeIn();
        $scope.openShortcuts(1);
    };
    $scope.closePopup = function(){
        $('.popup').fadeOut();
        $('#darkOverlay').fadeOut();
    }

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

pageControllers.controller("PageCtrl", ['$scope', '$routeParams', 'Page', 'Card', function($scope, $routeParams, Page, Card) {
    /*
     private String id;
     private String customUrl;
     private String name;
     private String description;
     private String userId;
     private String contactInfo;
     private PageLink[] links;
     */
    /*Page.create({
     name: 'fubar',
     customUrl: 'fubar',
     description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nobis quod eveniet totam ipsum provident vitae porro pariatur distinctio modi debitis, quas fuga culpa, odit ipsa sed eum placeat non dolor!/n' +
     'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sint velit iure cum itaque commodi assumenda deleniti laboriosam neque consequuntur aspernatur iste nihil, molestiae perspiciatis inventore dolorum est, reprehenderit magni omnis.',
     contactInfo: 'Galleri Oslo',
     links: [
     {
     url: "https://www.facebook.com/groups/537053489645341/",
     description: "Facebook"
     }
     ]
     });*/
    var shortDescription = "";
    var description = "";
    $scope.page = Page.query({ pageId: $routeParams.id }, function(data) {
        description = data.description;
        shortDescription = description.length > 100 ? description.substring(0, 100) : description + "...";
        $scope.description = shortDescription;
    });
    $scope.cards = Card.all();

    $('.thumb').each(function() {
        var elem = $(this);
        elem.removeAttr('style');
        if (elem.height() > elem.width()) {
            elem.css('width', '100%');
        } else {
            elem.css('height', '100%');
        }
    });

    $scope.readMore =  function(){
        $scope.description = $scope.showMoreText ? shortDescription : description;
        // TODO: redo the animation
        $scope.showMoreText = !$scope.showMoreText;
    }
}
]);
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
userControllers.controller("EditProfileCtrl", ['$scope', '$routeParams', 'User', 'Uploads', function($scope, $routeParams, User, Uploads) {
    $scope.uploadFile = function(file) {
        Uploads.upload({file: file, name: file.name, attachment: false}, function(data) {
            User.update({profilePictureId: data.id});
        });
    }
}]);

searchControllers.controller("QuickSearchCtrl", ['$scope', 'Search', function($scope, Search) {
    function closeOverlay(thatClass){
        $(thatClass).fadeOut();
    }

    var searchField = $("#quickSearch");
    var timerId = 0;
    var lastKeyPress;
    var resultUpdated = true;
    var lastAutocompleted = false;

    $scope.closeSearch = function(){ // Lukke
        closeOverlay('.search-overlay-wrap');
        clearInterval(timerId);
        searchField.val("");
        delete $scope.searchResults;
    };

    $scope.openSearch = function(){ // Åpne
        $('.search-overlay-wrap').fadeIn();
        searchField.focus();

        timerId = setInterval(function() {
            if (lastKeyPress + 200 < Date.now() && !resultUpdated) {
                if ($scope.searchText) {
                    Search.queryAll({query: $scope.searchText}, function(data) {
                        for (var i = 0; i < data.length; i++) {
                            var result = data[i];
                            if (result.type == "user") {
                                result.url = "profile/" + result.data.id;
                                result.name = result.data.firstname + " " + result.data.surname;
                            } else if (result.type == "page") {
                                result.url = "pages/" + result.data.customUrl;
                                result.name = result.data.name;
                            } else if (result.type == "tag") {
                                var current = result.data;
                                result.name = "";
                                while (current) {
                                    result.name = "/#" + current.name + result.name;
                                    current = current.parent;
                                }
                                if (result.data.description) {
                                    result.name += " - " + result.data.description;
                                }
                            }
                        }
                        $scope.searchResults = data;
                    });
                }
                resultUpdated = true;
            }
        }, 100);
    };

    //S = 83
    $(document).keyup(function(e) {
        if (e.key == 's' || e.keyCode == 83 || e.keyCode == 115) {
            $scope.openSearch();
        }
    });

    $scope.updateSearchResults = function() {
        lastKeyPress = Date.now();
        resultUpdated = false;
        lastAutocompleted = false;
        if (!$scope.searchText) {
            delete $scope.searchResults;
        }
    };

    $scope.runAutoComplete = function($event) {
        /*if (!$scope.searchResults) {
         return;
         }
         if ($event.ctrlKey && $event.keyCode == 32) {
         if ($scope.searchResults) {
         $("#quickSearch").val($scope.searchResults[0].name);
         $scope.searchResults = [$scope.searchResults[0]];
         lastAutocompleted = true;
         }
         }*/
        //maybe add quick search thingies for opening stuff like itslearning?
        if ($event.keyCode == 13) {
            if (searchField.val() == "its") {
                window.location = "https://woact.itslearning.com/";
            }
            if ($scope.searchResults && $scope.searchResults.length == 1  && $scope.searchResults[0].url) {
                window.location = "#/" + $scope.searchResults[0].url;
                $scope.closeSearch();
            }
        }
        // ESC
        if ($event.keyCode == 27) {
            $scope.closeSearch();
        }
    };
}]);

pageControllers.controller('ShowPagesController', ['$scope', 'Page', function($scope, Page) {
    $scope.pages = Page.all();
}]);