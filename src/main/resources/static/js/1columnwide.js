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
          autoActivateChild: 'login.default'
      }
	})
    .state('login.default', {
  	  url: "/",
	  templateUrl: partialsBase + "home/login/default.html",
      controller: 'search',
      params: {
          autoActivateChild: 'login.default.search'
      }
    })
    .state('login.default.search', {
      views: {
    	  'login-search-tags': {
    	      templateUrl: partialsBase + "home/search/search-tags.html"
    	  },
    	  'login-selected-tags': {
    	      templateUrl: partialsBase + "home/search/selected-tags.html"
    	  },
    	  'login-listings': {
    	      templateUrl: partialsBase + "home/listings/alpha-num-tabs.html"
    	  }
      }
    })
	.state('home', {
      url: "/home",
      templateUrl: partialsBase + "home/default.html",
      controller: 'home',
      params: {
          autoActivateChild: 'home.search'
      }
    })
    .state('home.search', {
      templateUrl: partialsBase + "home/search/search-tags.html",
      controller: 'search',
      params: {
          autoActivateChild: 'home.search.tags'
      }
    })
    .state('home.search.tags', {
        views: {
      	  "home-search-tags": {
              templateUrl: partialsBase + "home/search/selected-tags.html"
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


