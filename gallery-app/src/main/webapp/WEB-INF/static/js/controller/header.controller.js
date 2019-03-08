
/***
 *  Controller from header
 */
app.controller('headerController', function($scope, imageService) {

    /***
     * upload a image, call load of parent controller
     */
    $scope.uploadImage = function(file) {
       if(!file) return;
       imageService.uploadImage(file).then(function (data) {
           $scope.$parent.load();
       });
    };


});