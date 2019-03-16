<%@tag pageEncoding="UTF-8" %>
<%@attribute name="content" %>
<%@attribute name="title" type="java.lang.String" %>

<!doctype html>
<html lang="en">
<link>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/galleryapp.css">
    <title>${title}</title>
</head>
<body ng-app="gallery-app">

<div class="container" ng-cloak>
    ${content}
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="/webjars/jquery/3.3.1/dist/jquery.slim.min.js"></script>
<script src="/webjars/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="/webjars/angularjs/1.7.7/angular.min.js"></script>
<!--my js-->
<script src="/static/js/galleryapp.app.js"></script>
<script src="/static/js/galleryapp.service.js"></script>
<script src="/static/js/controller/home.controller.js"></script>
<script src="/static/js/controller/image.controller.js"></script>
<script src="/static/js/controller/header.controller.js"></script>
</body>
</html>