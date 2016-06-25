(function () {

    var injectParams = ['$scope', '$http', '$state','$window'];

    var SearchController = function($scope, $http, $state, $window) {
    	  var _selected;

    	  $scope.tagsList= [];
    	  
    	  $scope.selected = undefined;
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

    	  $scope.addToTagList = function($item, $model, $label) {
    		$scope.tagsList.push($model);
    	  };

    	  $scope.removeFromTagList = function(index) {
    	      $scope.tagsList.splice(index, 1);
      	  };

      	  $scope.tabs = [
      	  	       	    { title:'Dynamic Title 1', content:'Dynamic content 1' },
      	  	       	    { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
      	  	       	  ];

      	  	       	  $scope.alertMe = function() {
      	  	       	    setTimeout(function() {
      	  	       	      $window.alert('You\'ve selected the alert tab!');
      	  	       	    });
      	  	       	  };

      	  	       	  $scope.model = {
      	  	       	    name: 'Tabs'
      	  	       	  };
    }
    SearchController.$inject = injectParams;

    angular.module('1columnwide').controller('search', SearchController);

}());