<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:base title="Gallery App - List">
    <jsp:attribute name="content">
        <div ng-controller="homeController" ng-init="init()" >
            <!--header-->
            <t:header/>
            <!--site main statistic -->
            <div class="py-1 text-center">
                <p class="lead">
                    1.000.000 images uploaded.
                    21.000.000 likes
                    80.000.000 unlikes
                    500 comments
                    1.000.000.000 views.
                </p>
            </div>
            <div ng-repeat="row in grid">
               <div class="row">
                  <div class="col-sm" ng-repeat="image in row">
                      <a href="/{{image.name}}" >
                        <img class="cell-image" ng-src="images/{{image.name}}">
                      </a>
                  </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:base>