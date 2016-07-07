(function () {

    var injectParams = ['$scope', '$http', '$state', '$window', '$log', '$timeout', 'md5'];

    var SearchController = function($scope, $http, $state, $window, console, $timeout, md5) {
    	  var _selected;

    	  $scope.tagsList= [];
    	  
    	  $scope.selected = undefined;
      	  $scope.advertsDatabase = {};

    	  // Auto-complete function to get list of tags starting with user entries
    	  $scope.getTags = function(val) {
    		  return $http.get('//1columnwide.net.nz/public/services/tags', {
    	      params: {
    	        tagPart: val
    	      }
    	    }).then(function(response){
    	      return response.data.map(function(item){
    	        return item.text;
    	      });
    	    });
    	  };
    	  // End Auto-complete behavior

    	  // Selected tags badges
    	  $scope.addToTagList = function($item, $model, $label) {
    		$scope.tagsList.push($model);
    		refreshAdvertsDatabase();
    	  };
    	  $scope.removeFromTagList = function(index) {
    	      $scope.tagsList.splice(index, 1);
      		  refreshAdvertsDatabase();
      	  };
    	  // End selected tags badges

      	  // select tab by listing setups
     	  $scope.selectListingsTab = function($index) {
       	      //$window.alert('You\'ve selected the alert tab!' + $index);
       	  };
       	  // Convenience function for splitting words on space
		  $scope.splitWords = function(string) {
		    var array = string.split(' ');
		    return array[0];
		  }
		  // Category columns pagination
  		  $scope.setPage = function (pageNo) {
  			  $scope.currentPage = pageNo;
		  };
		  $scope.pageChanged = function() {
			  //$log.log('Page changed to: ' + $scope.currentPage);
		  };
		  // End category columns pagination

		  $scope.listingsTabs = [
      	  	       	    { title:'All', size: 33  },
      	  	       	    { title:'Alphabetical', size: 33  },
      	  	       	    { title:'Geographical', size: 33 }
      	  	       	  ];
      	  
      	  $scope.totalItems = 64;
      	  $scope.currentPage = 4;
		  $scope.maxSize = 5;
		  $scope.bigTotalItems = 175;
		  $scope.bigCurrentPage = 1;
		  
    	  $scope.refreshAdvertsDatabase = function() {
    		  return $http.get('//1columnwide.net.nz/public/services/adverts', {
    	      params: {
    	        tags : $scope.tagsList
    	      }
    	    }).then(function(response){
    	    	$scope.advertsDatabase = response.data;
    	    });
    	  };
      	  $scope.getAdvertsDatabaseCategoryList = function() {
      		  return Object.keys($scope.advertsDatabase);
      	  };
		  $scope.adapterService = function(category) {
			  var adapter = 'adapter-' + md5.createHash(category);
			  $scope[adapter] = {
				      remain: true					  
			  };
			  return adapter;
		  };
		  $scope.advertListDatasourceService = function(category) {
			  var get = function (index, count, success) {
					$timeout(function () {
					    var adapter = $scope.adapterService(category);
				        var i;
				        result = [];
				        if (index === -1) {
				        	result.push($scope.advertsDatabase[category].adverts[0]);
					        $scope[adapter].isEOF = function() {
					        	return true;
					        };
				        } else if (index > 0) {
					        for (i = index-1; i < $scope.advertsDatabase[category].adverts.length && i < index + count; i++) {
								var advert = $scope.advertsDatabase[category].adverts[i];
								result.push(advert);
							}
					        $scope[adapter].isBOF = function() {
					        	return true;
					        };
				        }
						success(result);
					}, 100);
				};
				return {
					get: get
				};
		  };
    }
    SearchController.$inject = injectParams;

    
    angular.module('1columnwide').controller('search', SearchController);

}());



