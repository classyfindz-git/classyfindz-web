angular.module('1columnwide', [ 'ngRoute', 'ui.bootstrap', 'ngResource' ]).config(function($routeProvider, $httpProvider) {
    var viewBase = '/js/views/';

	$routeProvider.when('/login', {
		templateUrl : viewBase + 'home/login.html',
		controller : 'login',
		controllerAs: 'vm'
	}).when('/home', {
		controller : 'home',
        templateUrl: viewBase + 'home/home.html',
        controllerAs: 'vm'
	}).otherwise({ redirectTo: '/login' });

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});
