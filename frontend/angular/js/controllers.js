'use strict';
var loginController = angular.module('loginControllers', []);
loginController.controller('LoginCtrl', ['$scope', '$http', 'Login', function($scope, $http, Login){
    Login.doget();
    $scope.login = function() {
        Login.login($scope.credentials,
            function(data) {
                alert("Logged in!");
            },
            function(error) {
                alert("Failed to log in. " + error);
                console.log(error);
            }
        );
    }
}]);