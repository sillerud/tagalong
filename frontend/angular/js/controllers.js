'use strict';
var loginController = angular.module('loginControllers', []);
loginController.controller('LoginCtrl', ['$scope', '$http', 'Login', function($scope, $http, Login){
    //Login.doget();
    $http.get("http://localhost/login");
    $scope.login = function(credentials) {
        //Login.login(credentials);
        $http.post("http://localhost/login", $scope.credentials);
    }
}]);