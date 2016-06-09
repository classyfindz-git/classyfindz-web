(function () {

    var injectParams = ['$scope', '$http'];

    var HomeController = function($scope, $http) {
		$http.get('/resource/').success(function(data) {
			$scope.greeting = data;
		})
	};

    HomeController.$inject = injectParams;

    angular.module('1columnwide').controller('home', HomeController);

}());