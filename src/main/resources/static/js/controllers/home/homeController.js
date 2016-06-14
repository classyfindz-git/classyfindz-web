(function () {

    var injectParams = ['$rootScope','$scope', '$http', '$location' ];

    var HomeController = function($rootScope, $scope, $http, $location) {
		$http.get('/resource/').success(function(data) {
			$scope.greeting = data;
		})
		$scope.logout = function() {
			$http.post('logout', {}).success(function() {
				$rootScope.authenticated = false;
				$location.path("/");
			}).error(function(data) {
				console.log("Logout failed")
				$rootScope.authenticated = false;
			});
		}

	};

    HomeController.$inject = injectParams;

    angular.module('1columnwide').controller('home', HomeController);

}());