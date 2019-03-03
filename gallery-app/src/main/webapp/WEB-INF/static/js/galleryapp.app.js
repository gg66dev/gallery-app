var app = angular.module("gallery-app", []);


/***
 * Directives
 */
app.directive('fileUpload', function () {
            return {
                scope: {
                    onFinish: '='
                },
                template: "<div class='upload-btn-wrapper'>" +
                    "<form method='POST' action='uploadFile' enctype='multipart/form-data'>" +
                    "<input type='file' accept='image/x-png,image/gif,image/jpeg'>" +
                    "<button class='btn'>Upload image</button>" +
                    "</form>" +
                    "</div>",
                link: function (scope, element) {
                    element.bind('change', function (e) {
                        scope.file = (e.srcElement || e.target).files[0];
                        if (scope.file) {
                            scope.onFinish(scope.file);
                        }
                    });
                }
            };
        });
