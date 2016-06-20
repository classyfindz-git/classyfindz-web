angular.module('1columnwide', [ 'ngRoute', 'ui.router', 'ui.bootstrap', 'ngResource' ])
		.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
    
	var viewBase = '/js/views/';
    var partialsBase = '/js/partials/';
	// For any unmatched url, redirect to /login
	$urlRouterProvider.otherwise(viewBase + "/login");

	$stateProvider.state('login', {
	  url: "/login",
	  templateUrl: partialsBase + "home/login.html"
      controller: 'login'
	})
    .state('home', {
      url: "/home",
      templateUrl: partialsBase + "home/home.html",
      controller: 'home'
    })
    .state('home.search', {
      url: "/home-search",
      templateUrl: partialsBase + "home/search-tags.html",
      controller: 'tagsTypeAhead'
    });

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});