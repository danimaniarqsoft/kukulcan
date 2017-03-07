app.controller('dashboardController', function($scope) {
	$scope.headingTitle = "DashboardList";
});

app.controller('createDataStoreController', function($scope, $http) {
	$http.get('http://localhost:8080/dataStores/newDataStore').then(function(response) {
		$scope.datastore = response.data;
	});
	
	$http.get('http://localhost:8080/dataStoreTypes').then(function(response) {
		$scope.datastoretype = response.data;
	});
	$scope.submit = function() {		
		$http.post('http://localhost:8080/dataStores', $scope.datastore).then(function(response) {
			console.log("ok");
		}, function(response) {
			console.log(response);
		});
	};
});