<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:base title="Gallery App - List">
    <jsp:attribute name="content">
        <div ng-controller="imageController" ng-init="init('${imageHash}')" ng-cloak>
            <!--header-->
            <t:header/>
           <div class="py-1 text-center">
                <p class="lead">
                    <br>
                </p>
            </div>
            <div class="row">
                <div class="col-8 image-container">
                    <img class="cell-image" ng-src="/images/{{page.imageHash}}" >
                </div>
                <div class="col-4 coment-panel scroller">
                   <ul id="comments-list" class="comments-list">
                        <li ng-repeat="comment in page.comments track by $index" >
                            <div class="comment-box">
                                <div class="comment-head">
                                    <span>{{ comment.date }}</span>
                                </div>
                                <div class="comment-content">
                                    {{ comment.text }}
                                </div>

                            </div>
                        </li>
                   </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-8">
                    <div class="like-panel">
                         <span>
                            {{ page.views }}
                           <i class="far fa-2x fa-eye"></i>
                        </span>
                        <a ng-click="likePage()">
                            {{ page.totalLikes }}
                            <i ng-if="!page.isLike" class="far fa-2x fa-thumbs-up"></i>
                            <i ng-if="page.isLike" class="fas fa-2x fa-thumbs-up"></i>
                        </a>
                        <a ng-click="unlikePage()">
                            {{ page.totalUnlikes }}
                            <i ng-if="!page.isUnlike" class="far fa-2x fa-thumbs-down"></i>
                            <i ng-if="page.isUnlike" class="fas fa-2x fa-thumbs-down"></i>
                        </a>
                    </div>
                </div>
                <div class="col-4 float-right">
                    <div class="form-group purple-border">
                        <textarea class="form-control mt-2 text-area"
                                  ng-model="page.targetComment"
                                  maxlength="50"
                                  rows="3"></textarea>
                        <button class="btn-comment"
                                ng-disabled="!page.targetComment"
                                ng-click="addComent()">comment!</button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:base>