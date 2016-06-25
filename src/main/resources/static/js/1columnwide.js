var app = angular.module('1columnwide', [  'ngRoute','ui.router', 'ui.bootstrap', 'ngResource' ])
		.config(function($routeProvider, $stateProvider, $urlRouterProvider, $httpProvider) {
    
	var viewBase = '/js/views/';
    var partialsBase = '/js/partials/';
	// For any unmatched url, redirect to /login
	$urlRouterProvider.otherwise( "/login");

	$stateProvider
	// VV Public states VV
	.state('login', {
	  url: "/login",
	  templateUrl: viewBase + "login.html",
      controller: 'login',
      params: {
          autoActivateChild: 'login.default'
      }
	})
    .state('login.default', {
  	  url: "/",
	  templateUrl: partialsBase + "home/default.html",
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
    	  'login-tab-categories': {
    	      templateUrl: partialsBase + "home/listings/alpha-num-tabs.html"
    	  },
    	  'login-ad-columns': {
    	      templateUrl: partialsBase + "home/listings/ad-columns.html"
    	  }
      }
    })
    // VV Secured states VV
	.state('home', {
      url: "/home",
      templateUrl: viewBase + "secure.html",
      controller: 'home',
      params: {
          autoActivateChild: 'home.default'
      }
    })
    .state('home.default', {
  	  url: "/",
	  templateUrl: partialsBase + "home/authenticated.html",
      controller: 'search',
      params: {
          autoActivateChild: 'home.default.search'
      }
    })
    .state('home.default.search', {
      views: {
    	  'home-search-tags': {
    	      templateUrl: partialsBase + "home/search/search-tags.html"
    	  },
    	  'home-selected-tags': {
    	      templateUrl: partialsBase + "home/search/selected-tags.html"
    	  },
    	  'home-tab-categories': {
    	      templateUrl: partialsBase + "home/listings/alpha-num-tabs.html"
    	  },
    	  'home-ad-columns': {
    	      templateUrl: partialsBase + "home/listings/ad-columns.html"
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