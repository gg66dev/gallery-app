/***
 * controller from image detail view
 */
app.controller('imageController', function($scope, $window, imageService) {

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
                page.comments = data.comments;
                page.loaded = true;
            });
    };

    /**
     * load event after a image is loaded (redirect to home)
     */
    $scope.load = function () {
       $window.location.href = '/';
    };

    /***
     * like page
     */
    $scope.likePage = function () {
        var page = $scope.page;
        page.isLike = !page.isLike;
      //cant like and unlike an image
      if (page.isUnlike) {
          page.isUnlike = false;
          page.totalUnlikes = page.totalUnlikes - 1;
      }
      //update count and save the like
       if (page.isLike) {
           page.totalLikes = page.totalLikes + 1;
       } else {
           $scope.page.totalLikes = page.totalLikes - 1;
       }
       imageService.updatePageView({
           like: page.isLike,
           unlike: page.isUnlike,
           page: {
               url: page.imageHash
           }
       });
    };

    /**
     * unlike page
     */
    $scope.unlikePage = function () {
        var page = $scope.page;
        page.isUnlike = !page.isUnlike;
        //cant like and unlike an image
        if (page.isLike) {
          page.isLike = false;
          page.totalLikes = page.totalLikes - 1;
       }
       //update status and save the like
        if (page.isUnlike) {
           page.totalUnlikes = page.totalUnlikes + 1;
       } else {
           page.totalUnlikes = page.totalUnlikes - 1;
       }
       imageService.updatePageView({
           like: page.isLike,
           unlike: page.isUnlike,
           page: {
               url: page.imageHash
           }
       });
    };

    /**
     * add new comment
     */
    $scope.addComent = function ( ) {
        var comment =  {
            message: $scope.page.targetComment,
            //createdDate: new Date(),
            url: $scope.page.imageHash
        };
        $scope.page.comments.push(comment);
        $scope.page.targetComment = "";
        //save comment
        imageService.saveComment(comment)
    }

});