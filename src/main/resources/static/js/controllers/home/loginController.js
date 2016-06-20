(function () {

    var injectParams = ['$rootScope', '$scope', '$http', '$location', '$route'];

    var LoginController = function($rootScope, $scope, $http, $location, $route) {

		$scope.tab = function(route) {
			return $route.current && route === $route.current.controller;
		};

		var authenticate = function(credentials, callback) {

			var headers = credentials ? {
				authorization : "Basic "
						+ btoa(credentials.username + ":"
								+ credentials.password)
			} : {};

			$http.get('user', {
				headers : headers
			}).success(function(data) {
				if (data.name) {
					$rootScope.authenticated = true;
				} else {
					$rootScope.authenticated = false;
				}
				callback && callback($rootScope.authenticated);
			}).error(function() {
				$rootScope.authenticated = false;
				callback && callback(false);
			});

		}

		authenticate();

		$scope.credentials = {};
		$scope.login = function() {
			authenticate($scope.credentials, function(authenticated) {
				if (authenticated) {
					console.log("Login succeeded")
					$location.path("/home");
					$scope.error = false;
					$rootScope.authenticated = true;
				} else {
					console.log("Login failed")
					$location.path("/login");
					$scope.error = true;
					$rootScope.authenticated = false;
				}
			})
		};

	};

    LoginController.$inject = injectParams;

    angular.module('1columnwide').controller('login', LoginController);

}());