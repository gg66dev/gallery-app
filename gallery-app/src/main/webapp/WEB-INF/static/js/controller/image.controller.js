/***
 * controller from image detail view
 */
app.controller('imageController', function($scope) {

    /**
     * main image of the page
     */
    $scope.mainImage = null;


    $scope.comments = [];

    $scope.targetComment = null;

    $scope.init = function (imageHash) {
        $scope.mainImage = imageHash;
        console.log(imageHash);
    };

    $scope.addComent = function ( ) {
        $scope.comments.push($scope.targetComment);
        $scope.targetComment = "";
    }

});