app.service('imageService', function($http) {
   return {
     /**
      * upload new image
      */
     uploadImage: function(file) {
        var fd = new FormData();
        fd.append('file', file);
       return $http.post('/uploadFile', fd,{
           headers: {
               'Content-Type': undefined
           }
       })
       .then(function(result) {
               return result.data;
       });
     },
     /**
      * return list of images.
      */
     getImages: function () {
        return $http.get('/images')
            .then(function (result) {
                return result.data;
            });
     }
   }
});