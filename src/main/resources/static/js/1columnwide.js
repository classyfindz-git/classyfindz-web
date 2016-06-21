var app = angular.module('1columnwide', [  'ngRoute','ui.router', 'ui.bootstrap', 'ngResource' ])
		.config(function($routeProvider, $stateProvider, $urlRouterProvider, $httpProvider) {
    
	var viewBase = '/js/views/';
    var partialsBase = '/js/partials/';
	// For any unmatched url, redirect to /login
	$urlRouterProvider.otherwise( "/login");

	$stateProvider.state('login', {
	  url: "/login",
	  templateUrl: partialsBase + "home/login.html",
      controller: 'login',
      params: {
          autoActivateChild: 'login.search'
      }
	})
    .state('login.search', {
      templateUrl: partialsBase + "home/search-tags.html",
      controller: 'tagsTypeAhead',
      params: {
          autoActivateChild: 'login.search.tags'
      }
    })
    .state('login.search.tags', {
      views: {
    	  "login-search-tags": {
    	      templateUrl: partialsBase + "home/selected-tags.html"
    	  }
      }
    })
	.state('home', {
      url: "/home",
      templateUrl: partialsBase + "home/home.html",
      controller: 'home',
      params: {
          autoActivateChild: 'home.search'
      }
    })
    .state('home.search', {
      templateUrl: partialsBase + "home/search-tags.html",
      controller: 'tagsTypeAhead',
      params: {
          autoActivateChild: 'home.search.tags'
      }
    })
    .state('home.search.tags', {
        views: {
      	  "home-search-tags": {
              templateUrl: partialsBase + "home/selected-tags.html"
      	  }
        }
    });

	// 
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});


//run blocks
app.run(function($rootScope,$state) {
	// Support for autoActivateChild
	$rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
	    if(toState && toState.params && toState.params.autoActivateChild){
	        $state.go(toState.params.autoActivateChild);
	    }
	});
});


