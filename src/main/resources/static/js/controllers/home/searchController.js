(function () {

    var injectParams = ['$scope', '$http', '$state', '$window', '$log', '$timeout'];

    var SearchController = function($scope, $http, $state, $window, console, $timeout) {
    	  var _selected;

    	  $scope.tagsList= [];
    	  
    	  $scope.selected = undefined;

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
    	  $scope.modelOptions = {
    	    debounce: {
    	      default: 500,
    	      blur: 250
    	    },
    	    getterSetter: true
    	  };
    	  // End Auto-complete behavior

    	  // Selected tags badges
    	  $scope.addToTagList = function($item, $model, $label) {
    		$scope.tagsList.push($model);
    	  };
    	  $scope.removeFromTagList = function(index) {
    	      $scope.tagsList.splice(index, 1);
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
      	  $scope.advertList = {
      			  	'Category A' : [
     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  }
     	  	       	],      	  
          			'Category B' : [
     	  	       	    { heading:'My second advert', text: "So lets  try to sell something here.."  },
     	  	       	    { heading:'My second advert', text: "So lets  try to sell something here.."  },
     	  	       	    { heading:'My second advert', text: "So lets  try to sell something here.."  },
     	  	       	    { heading:'My second advert', text: "So lets  try to sell something here.."  },
     	  	       	    { heading:'My second advert', text: "So lets  try to sell something here.."  }
          	     	],
				  	'Category C' : [
			     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
			     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
			     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
			     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
			     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
			     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  }
			     	  	       	],      	  
      			  	'Category D' : [
      		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
			     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  }
      		     	  	       	],      	  
      			  	'Category E' : [
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  }
      	     	  	       	],      	  
      			  	'Category F' : [
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  }
      	     	  	       	]
      	  };
      	  
      	  $scope.totalItems = 64;
      	  $scope.currentPage = 4;
		  $scope.maxSize = 5;
		  $scope.bigTotalItems = 175;
		  $scope.bigCurrentPage = 1;

    }
    SearchController.$inject = injectParams;

    angular.module('1columnwide').controller('search', SearchController);
    angular.module('1columnwide').factory('advertListDatasource', ['$log', '$timeout', function (console, $timeout) {
			var get = function (index, count, success) {
				$timeout(function () {
					var result = [];
					var data = [
		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
		     	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  }
		     	  	       	];
					for (var i = index; i <= index + count - 1; i++) {
						result.push(data[0]);
					}
					success(result);
				}, 100);
			};

			return {
				get: get
			};
		}
    ]);
}());



