(function () {

    var injectParams = ['$scope', '$http', '$state','$window'];

    var SearchController = function($scope, $http, $state, $window) {
    	  var _selected;

    	  $scope.tagsList= [];
    	  
    	  $scope.selected = undefined;

    	  // Auto-complete function to get list of tags starting with user entries
    	  $scope.getTags = function(val) {
//    		  return val + 'hello';
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

      	  $scope.listingsTabs = [
      	  	       	    { title:'A-B-C-D', size: 33  },
      	  	       	    { title:'E-F-G-H', size: 33 },
      	  	       	    { title:'I-J-K-L', size: 33  },
      	  	       	    { title:'M-N-O-P-Q', size: 33  },
      	  	       	    { title:'R-S-T-U', size: 0  },
      	  	       	    { title:'V-W-X-Y-Z', size: 33  },
      	  	       	    { title:'0-9 !-@-#-$-%-^-&-*', size: 33  }
      	  	       	  ];

       	  $scope.selectListingsTab = function($index) {
       	      //$window.alert('You\'ve selected the alert tab!' + $index);
       	  };
		  $scope.splitWords = function(string) {
		    var array = string.split(' ');
		    return array[0];
		  }
      	  $scope.advertList = [
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  },
      	      	  	       	    { heading:'My first advert', text: "So lets  try to sell something here.."  }
      	      	  	       	  ];
    }
    SearchController.$inject = injectParams;

    angular.module('1columnwide').controller('search', SearchController);

}());