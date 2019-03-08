<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:base title="Gallery App - List">
    <jsp:attribute name="content">
        <div ng-controller="imageController" ng-init="init('${imageHash}')" >
            <!--header-->
            <t:header/>
           <div class="py-1 text-center">
                <p class="lead">
                    <br>
                </p>
            </div>
            <div class="row">
                <div class="col-8">
                    <img class="cell-image" ng-src="/images/{{mainImage}}" >
                </div>
                <div class="col-4 coment-panel">
                   <ul id="comments-list" class="comments-list">
                        <li ng-repeat="comment in comments track by $index" >
                            <div class="comment-box">
                                <div class="comment-content">
                                    {{ comment }}
                                </div>
                            </div>
                        </li>
                   </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-8"></div>
                <div class="col-4 float-right">
                    <div class="form-group purple-border">
                        <textarea class="form-control mt-2"
                                  ng-model="targetComment"
                                  maxlength="50"
                                  rows="3"></textarea>
                        <button class="btn-comment"
                                ng-disabled="!targetComment"
                                ng-click="addComent()">comment!</button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</t:base>