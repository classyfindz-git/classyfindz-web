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
					$scope.error = false;
					$rootScope.authenticated = true;
					$location.state("/home");
				} else {
					console.log("Login failed")
					$scope.error = true;
					$rootScope.authenticated = false;
					$location.state("/login");
				}
			})
		};

	};

    LoginController.$inject = injectParams;

    angular.module('1columnwide').controller('login', LoginController);

}());