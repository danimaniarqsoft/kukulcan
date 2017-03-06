var app = angular.module('app', ['ngRoute','ngResource']).constant("APP_URL", 'http://207.249.127.180:8080');
app.config(function($routeProvider){
    $routeProvider
        .when('/wizard',{
            templateUrl: '/views/wizard.html',
            controller: 'dashboardController'
        })
        .otherwise(
            { redirectTo: '/wizard'}
        );
});