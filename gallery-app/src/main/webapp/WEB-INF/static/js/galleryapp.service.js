app.service('imageService', function($http) {
    return {
     /**
      * upload new image
      */
     uploadImage: function(file) {
        var fd = new FormData();
        fd.append('file', file);
       return $http.post('/api/uploadFile', fd,{
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
        return $http.get('/api/images')
            .then(function (result) {
                return result.data;
            });
     },
     /**
      * return the number of likes, unlikes and views from a page
      */
     getPageView: function (imageHash) {
        return $http.get('/api/pageview',
            {params: {url: imageHash}})
            .then(function (result) {
               return result.data;
            });
     },
     /**
      * save status of like/unlike of the page
      */
     updatePageView: function (pageView) {
         return $http.put('/api/pageview', pageView)
             .then(function (result) {
                return result.data;
             });
     }
   }
});