'use strict';

function getUploadUrl(uploadId, fallback) {
    if (uploadId) {
        return "/rest/v1/uploads/" + uploadId;
    } else {
        return fallback ? fallback : 'img/placeholder_big.jpg';
    }
}

function skipToContent(skipId){

}

function mapPostColors(user) {
    var colorMap = {
        TECHNOLOGY_IT: '#1eb6ec',
        COMMUNICATION: '#fed575',
        MANAGEMENT: '#835581',
        ARTS: '#63caa6',
        FILM_TV_GAMEDESIGN: '#fd7265'
    };
    var color = colorMap.TECHNOLOGY_IT;
    if (user.studyField) {
        color = colorMap[user.studyField.studyDirection];
    }
    // Markus forced me...
    if (user.id == "56dd40c3e986dcbb1e8b76e7") {
        color = colorMap.TECHNOLOGY_IT;
    }
    user.userStyle = {
        profilePictureBorder: {
            border: '2px solid ' + color
        },
        studyFieldInfo: {
            color: color
        }
    };
}

function genericValueMapping(value, key) {
    if (key == 'tags') {
        var tagIds = [];
        value.forEach(function (tag) {
            tagIds.push(tag.id);
        });
        if (tagIds.length > 0)
            this.tagIds = tagIds;
    } else if (key == 'tagIds' || key.startsWith('$')) {
        // Ignore
    } else {
        this[key] = value;
    }
}

function canWrite(accessLevel) {
    switch (accessLevel) {
        case 'WRITE':
            return true;
        default:
            return canEdit(accessLevel);
    }
}

function canEdit(accessLevel) {
    switch (accessLevel) {
        case 'EDIT':
            return true;
        default:
            return canDelete(accessLevel);
    }
}

function canDelete(accessLevel) {
    switch (accessLevel) {
        case 'DELETE':
            return true;
        case 'ALL':
            return true;
        default:
            return false;
    }
}

function mapCard(card) {
    card.pages = [];
    var tagIds = [];
    card.filter.forEach(function(value){
        if (value.charAt(0) == '#') { // Its a tag
            tagIds.push(value.substring(1));
        } else { // its a page
            card.pages.push(value);
        }
    }, this);
    this.getByIds(tagIds).then(function(data) {
        card.tags = data;
    });
}


angular.module('tagalong', [
        'ngRoute',
        'ngFileUpload',
        'ui.select',
        'ngImgCrop',
        'ngSanitize',
        'ng-showdown',
        'eventControllers',
        'eventServices',
        'userControllers',
        'userServices',
        'cardControllers',
        'cardServices',
        'commentServices',
        'pageControllers',
        'pageServices',
        'postServices',
        'postControllers',
        'searchControllers',
        'searchServices',
        'staticServices'
    ])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when("/events", {
                templateUrl: 'partials/events.html',
                controller: 'ViewEventsCtrl'
            })
            .when("/events/new", {
                templateUrl: 'partials/edit_event.html',
                controller: 'NewEventCtrl'
            })
            .when('/events/:id', {
                templateUrl: 'partials/event.html',
                controller: 'ViewEventCtrl'
            })
            .when("/events/:id/edit", {
                templateUrl: 'partials/edit_event.html',
                controller: 'EditEventCtrl'
            })
            .when("/cards", {
                templateUrl: 'partials/cards.html',
                controller: 'AllCardCtrl'
            })
            .when("/feed/:tagId", {
                templateUrl: 'partials/feed.html',
                controller: 'FeedCtrl'
            })
            .when("/pages", {
                templateUrl: 'partials/pages.html',
                controller: 'ShowPagesController'
            })
            .when("/pages/new", {
                templateUrl: 'partials/edit_page.html',
                controller: 'EditPageCtrl'
            })
            .when("/pages/:id", {
                templateUrl: 'partials/page.html',
                controller: 'PageCtrl'
            })
            .when("/pages/:id/edit", {
                templateUrl: 'partials/edit_page.html',
                controller: "EditPageCtrl"
            })
            .when("/", {
                templateUrl: 'partials/feed.html',
                controller: 'FeedCtrl'
            })
            .when("/cards/:id/edit", {
                templateUrl: 'partials/edit_card.html',
                controller: 'EditCardCtrl'
            })
            .when("/profile", {
                templateUrl: 'partials/other_profiles.html',
                controller: 'ShowUserCtrl'
            })
            .when("/profile/edit", {
                templateUrl: 'partials/edit_profile.html',
                controller: 'EditProfileCtrl'
            })
            .when("/profile/:id", {
                templateUrl: 'partials/other_profiles.html',
                controller: 'ShowUserCtrl'
            })
            .when("/faq", {
                templateUrl: 'partials/good_to_know.html'
            });
    }])
    .config(['$showdownProvider', function($showdownProvider) {
        $showdownProvider.loadExtension('youtube');
        $showdownProvider.loadExtension('imgur-webm');
    }])
    .run(['$rootScope', 'Static', 'Card', 'Post', 'sessionFactory', function($rootScope, Static, Card, Post, sessionFactory) {
        $rootScope.title = 'Tag along';

        $rootScope.setTitle = function(title) {
            $rootScope.title = 'Tag along' + (title ? ' - ' + title : '');
        };

        $rootScope.setBreadcrumb = function(name, url) {
            $rootScope.breadcrumb = {name: name, url: url};
        };

        function idMapping(v) {
            return this.find(function(e) {
                return e.id == v;
            });
        }


        $rootScope.allTags = Static.getAllTags();
        $rootScope.allTags.getByIds = function(ids) {
            return $rootScope.allTags.$promise.then(function(allTags) {
                return ids.map(idMapping, allTags);
            });
        };
        $rootScope.studyfields = Static.getAllStudyFields();
        $rootScope.studyfields.getByIds = function(ids) {
            return $rootScope.studyfields.$promise.then(function(allStudyFields) {
                return ids.map(idMapping, allStudyFields);
            });
        };

        $rootScope.$on('$routeChangeStart', function() {
            $rootScope.hooks = {
                postCreated: []
            };
            sessionFactory.validate();
        });

        $rootScope.updateCards = function() {
            $rootScope.cards = Card.all(function(data) {
                data.forEach(mapCard, $rootScope.allTags);
                data.forEach(function(card) {
                    card.toggleDeleteDialog = function(toggle, $event) {
                        if ($event)
                            $event.stopPropagation();
                        card.deleteDialog = toggle;
                    };

                    card.deleteCard = function($event){
                        if ($event)
                            $event.stopPropagation();
                        Card.delete({cardId: card.id}, $rootScope.updateCards);
                    };
                });
            });
        };
        $rootScope.updateCards();

        $rootScope.newpost = {};

        $rootScope.logout = sessionFactory.invalidate;

        $rootScope.addToCard = function(){
            $('.add-to-card-wrap').fadeIn();
            $('#darkOverlay').fadeIn();
        }; // END add to card
        $rootScope.openNewPost = function(doo){
            $('.new-post-wrap').fadeIn();
            $('#darkOverlay').fadeIn();
            // Sjekker om shortcuts skal kjøre eller ikke
            if( doo == 1) $rootScope.closeShortcuts(1);
            else if( doo != 0) $rootScope.openShortcuts(1);
        };
        $rootScope.scrollToPopup = function(){
            $('body').animate({scrollTop: 0}, 300);
        };
        $rootScope.closePopup = function(){
            $('.popup').fadeOut();
            $('#darkOverlay').fadeOut();
            $rootScope.closeShortcuts(1);
            $('#transparent-overlay').css('display', 'none');
        };
        $rootScope.createPost = function() {
            var newpost = {};
            angular.forEach($rootScope.newpost, genericValueMapping, newpost);
            newpost.parentId = $rootScope.me.id; // TODO: Select this
            Post.create(newpost, function() {
                $rootScope.hooks.postCreated.forEach(function(updateMethod) {
                    updateMethod();
                });
            });
        };

        var menuOpen = false;
        function transformBar(rotation, gap1, gap2) {
            $('.bar1').css({
                transform: 'rotate(' + rotation + 'deg)',
                top: gap1
            });
            $('.bar2').css({
                transform: 'rotate(-' + rotation + 'deg)',
                top: gap2
            });
        }
        $rootScope.toggleMobileMenu = function(cl){
            $(cl).fadeOut();
            if (menuOpen) {
                $('body').css('overflow', 'auto');
                $('.menu').fadeOut();
                transformBar(0, '0px', '20px');
                $('.bar3').fadeIn();
                menuOpen = false;
            } else {
                $('body').css('overflow', 'hidden');
                $('.menu').fadeIn();
                transformBar(45, '10px', '10px');
                $('.bar3').fadeOut();
                menuOpen = true;
            }
        };
        $rootScope.closeMobileMenu = function(cl) {
            if (menuOpen) {
                $rootScope.toggleMobileMenu(cl)
            }
        };

        var addNewOpen = false;
        $rootScope.openShortcuts = function(dark, doo){
            console.log('yolo');
            if( !addNewOpen ){
                console.log('openShortcuts');
                $('#darkOverlay').fadeIn();
                $('#newPostBtn').stop().css('display', 'block').animate({'bottom': '60px', 'opacity': '1'}, 300);
                $('#newPageBtn').delay(100).css('display', 'block').animate({'bottom': '110px', 'opacity': '1'}, 300);
                $('#newEventBtn').delay(200).css('display', 'block').animate({'bottom': '160px', 'opacity': '1'}, 300);
                $('#newSearchBtn').delay(300).css('display', 'block').animate({'bottom': '210px', 'opacity': '1'}, 300);
                addNewOpen = true;
            }else{
                console.log('close');
                if( dark != 1) $('#darkOverlay').fadeOut();
                if( doo != 1) $rootScope.closeShortcuts();
                if( doo == 1) $rootScope.closeShortcuts(1);
            }

        }; // END openShortcuts
        $rootScope.addCard = function() {
            $('.popup').fadeOut();
            $('.edit-card-wrap').fadeIn();
            $('#darkOverlay').fadeIn();
        };

        $rootScope.closeShortcuts = function(doo){
            addNewOpen = false;
            $('#newSearchBtn').animate({'bottom': '200px', 'opacity': '0'}, 300, function(){ $(this).css('display', 'none'); });
            $('#newEventBtn').delay(100).animate({'bottom': '150px', 'opacity': '0'}, 300, function(){ $(this).css('display', 'none'); });
            $('#newPageBtn').delay(200).animate({'bottom': '100px', 'opacity': '0'}, 300, function(){ $(this).css('display', 'none'); });
            $('#newPostBtn').delay(300).animate({'bottom': '50px', 'opacity': '0'}, 300, function(){ $(this).css('display', 'none'); });
            if( doo != 1) $('#darkOverlay').fadeOut();
        };

        var dropdownToggle = false;
        var dropdown = $('.dropdown-content');
        var dropdownArrow = $('.arrow-up');
        $rootScope.openNotifications = function() {
            if(dropdownToggle == false){
                dropdownArrow.fadeIn();
                dropdown.fadeIn();
                dropdownToggle = true;
                $('#transparent-overlay').css('display', 'block');
                console.log('open');
            }else{
                dropdown.fadeOut();
                dropdownArrow.fadeOut();
                dropdownToggle = false;
                $('#transparent-overlay').css('display', 'none');
                console.log('close');

            }
        };

        $(document).mouseup(function (e) {
            var whatIs = $(e.target).attr('id');
            if (whatIs == 'transparent-overlay') {
                dropdown.fadeOut();
                dropdownArrow.fadeOut();
                dropdownToggle = false;
            }
            if (whatIs == 'person-list') {
                $rootScope.openPersonDropdown();
            }
        });
        $rootScope.openCardShortcuts = function(){
            $('.card-shortcut-wrap').fadeIn();
        };
        var personDrop = false;
        $rootScope.openPersonDropdown = function(){
            if( personDrop == false ){
                $('.menu-person-dropdown-mobile').fadeIn(300);
                personDrop = true;
            }else{
                $('.menu-person-dropdown-mobile').fadeOut(300);
                personDrop = false;
            }
        }
    }])
    .factory('sessionFactory', ['User', '$rootScope', function(User, $rootScope) {
        return {
            invalidate: function () {
                User.logout(redirectLogin);
            },
            validate: function() {
                User.clearCache();
                $rootScope.me = User.find(function(user) {
                    var colorMap = {
                        TECHNOLOGY_IT: 'blue',
                        COMMUNICATION: 'yellow',
                        MANAGEMENT: 'purple',
                        ARTS: 'green',
                        FILM_TV_GAMEDESIGN: 'red'
                    };
                    function getColorByStudyDirection(studyDirection) {
                        var color = colorMap[studyDirection];
                        // Markus forced me...
                        if (user.id == "56dd40c3e986dcbb1e8b76e7") {
                            color = colorMap.TECHNOLOGY_IT;
                        }
                        return color ? color : "blue";
                    }
                    if (!user.id) {
                        redirectLogin();
                        return;
                    }
                    user.profilePictureUrl = getUploadUrl(user.profilePictureId, "img/user_placeholder.png");
                    user.profileHeaderPictureUrl = getUploadUrl(user.profileHeaderPictureId);

                    $rootScope.styleSheetId = getColorByStudyDirection(user.studyField.studyDirection);

                    $rootScope.goToUrl = function(url){
                        $rootScope.closePopup();
                        window.location = url;
                    };
                }, redirectLogin);
            }
        };
    }])
    .directive('galeryImage', function() {
        return {
            restrict: "A",
            link: function(scope, element) {
                scope.getWidth = function() {
                    return $(element).width();
                };
                scope.$watch(scope.getWidth, function(width) {
                    $(element).height(width);
                });
            }
        }
    })
    .directive('gfyId', ['$http', function($http) { // Not currently doing anything
        return {
            restrict: 'A',
            link: function(scope, element, attributes) {
                $http.get('https://gfycat.com/cajax/get/' + attributes.gfyId).then(function(result) {
                    element.attr("src", result.data.gfyItem.webmUrl)
                });
            }
        }
    }])
    .filter('capitalize', function() {
        return function(input) {
            return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
        }
    });
