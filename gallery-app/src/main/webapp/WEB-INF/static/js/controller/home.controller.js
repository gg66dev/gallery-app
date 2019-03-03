app.controller('homeController', function($scope, imageService) {

    $scope.init = function () {
        _refreshImageGrid();
    };


    /***
     * upload a image, refresh grid if upload return success
     */
    $scope.uploadImage = function(file) {
       if(!file) return;
       imageService.uploadImage(file).then(function (data) {
           console.log(data);
           _refreshImageGrid();
       });
    };

    /***
     * refresh the image grid
     */
    var _refreshImageGrid = function () {
        imageService.getImages().then(function (data) {
            console.log(data);
        });
    }

});