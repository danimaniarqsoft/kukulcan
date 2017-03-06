var app = angular.module('app', ['ngRoute','ngResource']).constant("APP_URL", 'http://localhost:8080');
app.config(function($routeProvider){
    $routeProvider
        .when('/wizard',{
            templateUrl: 'wizard.html',
            controller: 'dashboardController'
        })
        .when('/home',{
            templateUrl: '/views/wizard.html',
            controller: 'dashboardController'
        })
        .otherwise(
            { redirectTo: '/wizard'}
        );
});