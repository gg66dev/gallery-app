var app = angular.module("gallery-app", []);


/***
 * Directives
 */
app.directive('fileUpload', function () {
            return {
                link: function (scope, element) {
                    scope.uploadFile = function(){

                        var file = document.getElementById('uploadFileInput').files[0]);

                    };
                }
            };
        });
