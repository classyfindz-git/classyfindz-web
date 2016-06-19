(function () {

    var injectParams = ['$scope', '$http'];

    var TagsTypeAheadController = function($scope, $http) {
    	  var _selected;

    	  $scope.selected = undefined;
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

    	}
    TagsTypeAheadController.$inject = injectParams;

    angular.module('1columnwide').controller('tagsTypeAhead', TagsTypeAheadController);

}());