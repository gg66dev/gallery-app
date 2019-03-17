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
                    {{ siteData.totalImages }} images uploaded.
                    {{ siteData.totalViews }} views.
                    {{ siteData.totalComments }} comments.
                    {{ siteData.totalLikes}} likes.
                    {{ siteData.totalUnlikes }} unlikes.
                </p>
            </div>
            <div ng-repeat="row in grid">
               <div class="row">
                  <div class="preview-container  col-sm" ng-repeat="image in row">
                      <a href="/{{image.name}}" >
                        <img class="cell-image preview-image" ng-src="images/{{image.name}}">
                         <div class="middle">
                            <div class="text">
                                <span>
                                    <i class="far fa-eye"></i>
                                    {{ image.totalViews  }}
                                </span>
                                <span>
                                    <i class="far fa-comment"></i>
                                    {{ image.totalComments }}
                                </span>
                                <span>
                                    <i class="far fa-thumbs-up"></i>
                                    {{ image.totalLikes }}
                                </span>
                                <span>
                                    <i class="far fa-thumbs-down"></i>
                                    {{ image.totalUnlikes }}
                                </span>
                            </div>
                         </div>
                      </a>
                  </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:base>