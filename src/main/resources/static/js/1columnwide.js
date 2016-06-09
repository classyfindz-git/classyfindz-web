angular.module('1columnwide', 
		[ 'ngRoute' ]

).config(function($routeProvider, $httpProvider) {
    var viewBase = '/js/views/';

	$routeProvider.when('/home', {
		controller : 'home',
        templateUrl: viewBase + 'home/home.html',
        controllerAs: 'vm'
	}).when('/login', {
		templateUrl : viewBase + 'home/login.html',
		controller : 'navigation',
		controllerAs: 'vm'
	}).otherwise({ redirectTo: '/home' });

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});
