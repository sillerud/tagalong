'use strict';
var loginController = angular.module('loginControllers', []);
loginController.controller('LoginCtrl', ['$scope', '$http', 'Login', function($scope, $http, Login){
    //Login.doget();
    $http.get("http://localhost:8080/login");
    $scope.login = function(credentials) {
        //Login.login(credentials);
        $http.post("http://localhost:8080/login", $scope.credentials);
    }
}]);