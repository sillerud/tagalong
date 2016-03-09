'use strict';

var loginControllers = angular.module('loginControllers', []);

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
        if (data.id) {
            window.location = "./";
        } else {
            login();
        }
    }, login);
}]);

angular.module('tagalongLogin', [
    'userServices',
    'loginControllers',
    'loginServices'
]);