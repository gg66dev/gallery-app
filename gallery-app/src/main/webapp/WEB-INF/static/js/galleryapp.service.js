app.service('ImageService', function($http) {
   return {
     /**
      * upload new image
      */
     uploadImage: function(file) {
       return $http.post('/uploadFile', file).then(function(result) {
           return result.data;
       });
     }
   }
});