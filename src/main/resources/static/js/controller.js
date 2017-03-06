app.controller('dashboardController', function($scope) {
	$scope.headingTitle = "DashboardList";
});

app.controller('Hello', function($scope, $http) {
	$http.get('https://jsonplaceholder.typicode.com/posts/1').then(
		function(response) {
			$scope.greeting = response.data;
		});
});


app.controller('generatorController', function($scope, $http) {
	$scope.dataStores = [];
	$http.get('http://localhost:8080/dataStores').then(function(response) {
		$scope.dataStores = response.data;
	});
});