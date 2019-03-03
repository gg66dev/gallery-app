app.controller('homeController', function($scope, imageService) {

    $scope.init = function () {
        console.log("hola mundo homeController");
    };


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
        console.log("refresh the grid");
    }

});