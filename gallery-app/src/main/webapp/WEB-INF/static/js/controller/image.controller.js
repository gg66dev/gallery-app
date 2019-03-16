/***
 * controller from image detail view
 */
app.controller('imageController', function($scope, imageService) {

    /**
     * object with functional elements of the page
     */
    $scope.page = {
        /**
         * if page is loaded
         */
        loaded: false,
        /**
         * total of views
         */
        views: 0,
        /**
         * if image have like
         */
        isLike: false,
        /***
         * if image have unlike
         */
        isUnlike: false,
        /**
         * total of likes
         */
        totalLikes: 0,
        /**
         * total of unlikes
         */
        totalUnlikes: 0,
         /**
         * main image of the page
         */
        imageHash: null,
        /**
         * list of comment of the image
         */
        comments: [],
        /**
         * new comment (before of submit to the list of comment)
         */
        targetComment: null
    };

    $scope.init = function (imageHash) {
        $scope.page.imageHash = imageHash;
        imageService.getPageView(imageHash)
            .then(function (data){
                var page = $scope.page;
                page.isLike = data.like;
                page.isUnlike = data.unlike;
                page.views = data.numViews;
                page.totalLikes = data.totalLikes;
                page.totalUnlikes = data.totalUnlikes;
                page.loaded = true;
            });
    };

    /***
     * like page
     */
    $scope.likePage = function () {
      $scope.page.isLike = !$scope.page.isLike;
      //cant like and unlike an image
      if ($scope.page.isUnlike) {
          $scope.page.isUnlike = false;
          $scope.page.totalUnlikes = $scope.page.totalUnlikes - 1;
      }
      //update count and save the like
       if ($scope.page.isLike) {
           $scope.page.totalLikes = $scope.page.totalLikes + 1;
       } else {
           $scope.page.totalLikes = $scope.page.totalLikes - 1;
       }
       //imageService.updatePageView()
    };

    /**
     * unlike page
     */
    $scope.unlikePage = function () {
        $scope.page.isUnlike = !$scope.page.isUnlike;
        //cant like and unlike an image
        if ($scope.page.isLike) {
          $scope.page.isLike = false;
          $scope.page.totalLikes = $scope.page.totalLikes - 1;
       }
       //update status and save the like
        if ($scope.page.isUnlike) {
           $scope.page.totalUnlikes = $scope.page.totalUnlikes + 1;
       } else {
           $scope.page.totalUnlikes = $scope.page.totalUnlikes - 1;
       }
    };

    /**
     * add new comment
     */
    $scope.addComent = function ( ) {
        $scope.page.comments.push({
            text: $scope.page.targetComment,
            date: new Date()
        });
        $scope.page.targetComment = "";
    }

});