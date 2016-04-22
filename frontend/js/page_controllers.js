'use strict';

var pageControllers = angular.module('pageControllers', []);

function redirectLogin() {
    window.location = "login.html";
}

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

    function refreshPage() {
        $scope.shoutOutEditVisible = false;
        $scope.page = Page.query({ pageId: $routeParams.id }, function(data) {
            $scope.setTitle(data.name);
            description = data.description;
            shortDescription = description.length > 100 ? description.substring(0, 100) : description + "...";
            $scope.description = shortDescription;
            data.logoPictureUrl = getUploadUrl(data.logoPictureId, "img/placeholder_thumb.jpg");
            data.coverPictureUrl = getUploadUrl(data.coverPictureId);
            data.canEdit = canEdit(data.accessLevel);
            data.canWrite = canWrite(data.accessLevel);
        });
    }
    
    refreshPage();

    $scope.hooks.postCreated.push(refreshPage);
    
    $scope.toggleShoutOutEdit = function() {
        $scope.shoutOutEditVisible = !$scope.shoutOutEditVisible;  
    };
    
    $scope.updateShoutOut = function() {
        Page.update({pageId: $scope.page.id}, {shoutOut: $scope.page.shoutOutEdited}, refreshPage);
    };

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

pageControllers.controller('ShowPagesController', ['$scope', 'Page', function($scope, Page) {
    $scope.setTitle("Pages");
    $scope.pages = Page.all(function(data) {
        data.forEach(function(page) {
            page.coverPictureUrl = getUploadUrl(page.coverPictureId);
        });
    });
}]);

pageControllers.controller('EditPageCtrl', ['$scope', '$routeParams', 'Page', 'Upload', function($scope, $routeParams, Page, Upload) {
    function isEdit() {
        return $routeParams.id;
    }
    
    $scope.isEdit = isEdit;
    
    var originalPage = null;
    $scope.update = function() {
        Page.query({pageId: $routeParams.id}, function(data) {
            if (!data.administrators) {
                data.administrators = [];
            }
            $scope.page = data;
            originalPage = $.extend({}, data);
            $scope.logoPictureUrl = getUploadUrl(data.logoPictureId, "img/placeholder_thumb.jpg");
            $scope.coverPictureUrl = getUploadUrl(data.coverPictureId);
        });
    };
    
    $scope.deletePage = function() {
        Page.deletePage({pageId: $scope.page.id}, function() {
            $scope.goToUrl('#/pages/');
        });
    };

    if (isEdit()) {
        $scope.update();
    } else {
        $scope.page = {
            administrators: []
        };
        $scope.logoPictureUrl = "img/placeholder_thumb.jpg";
        $scope.coverPictureUrl = "img/placeholder_big.jpg";
    }

    $scope.cropDialogState = function(cropId, state) {
        $(cropId).modal(state);
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
            if (isEdit()) {
                if (type == 'PROFILE_IMAGE') {
                    Page.update({pageId: originalPage.id}, {logoPictureId: extra.id}, $scope.update);
                } else {
                    Page.update({pageId: originalPage.id}, {coverPictureId: extra.id}, $scope.update);
                }
            } else {
                if (type == 'PROFILE_IMAGE') {
                    $scope.page.logoPictureId = extra.id;
                    $scope.logoPictureUrl = getUploadUrl(extra.id, "img/placeholder_thumb.jpg");
                } else {
                    $scope.page.coverPictureId = extra.id;
                    $scope.coverPictureUrl = getUploadUrl(extra.id);
                }
            }
            $scope.cropDialogState('#profile-picture-crop', 'hide');
            $scope.cropDialogState('#header-image-crop', 'hide');
        });
    };

    $scope.savePage = function() {
        function redirectToPage(result) {
            window.location = "#/pages/" + result.extra.id;
        }
        if (isEdit()) {
            if (originalPage == null)
                return;
            var updatedPage = {};
            angular.forEach($scope.page, function(value, key) {
                if (value != originalPage[key]) {
                    updatedPage[key] = value;
                }
            });

            if (!$.isEmptyObject(updatedPage)) {
                Page.update({pageId: originalPage.id}, updatedPage, redirectToPage);
            }
        } else {
            $scope.page.administrators.push({userId: $scope.me.id, accessLevel: "ALL"});
            Page.save($scope.page, redirectToPage);
        }
    }

}]);