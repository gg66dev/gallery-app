<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:base title="Gallery App - List">
    <jsp:attribute name="content">
        <div ng-controller="homeController" ng-init="init()" >

            <t:header/>

            <div class="py-1 text-center">
                <p class="lead">
                    1.000.000 images uploaded.
                    21.000.000 likes
                    80.000.000 unlikes
                    500 comments
                </p>
            </div>



           <div class="row">
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
            </div>
            <div class="row">
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
            </div>
            <div class="row">
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
              <div class="col-sm">
                  <img class="cell-image" src="images/2mitts1366x768.jpg">
              </div>
            </div>

            <%--<div>
                <a href="welcome">Click here to See Welcome Message... </a>(to
                check Spring MVC Controller... @RequestMapping("/welcome"))
            </div>
            <form method="POST" action="uploadFile" enctype="multipart/form-data">
                    File to upload:
                <input type="file"
                       accept="image/x-png,image/gif,image/jpeg"
                       name="file"><br />
                <input type="submit" value="Upload"> Press here to upload the file!
            </form>--%>
        </div>
    </jsp:attribute>
</t:base>