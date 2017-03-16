var app = angular.module('app', ['ngRoute','ngResource']).constant("APP_URL", 'http://localhost:8080');
app.config(function($routeProvider){
    $routeProvider
        .when('/',{
            templateUrl: '/app/componets/dataStore.view.html',
            controller: 'dataStore.controller'
        })
        .when('/home',{
        	templateUrl: '/app/componets/dataStore.view.html',
            controller: 'dataStore.controller'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});