<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:base title="Gallery App - List">
    <jsp:attribute name="content">
        <div ng-controller="homeController" ng-init="init()" >
        <h1>This is the List of image in Gallery App</h1>

        <h3>
            <a href="welcome">Click here to See Welcome Message... </a>(to
            check Spring MVC Controller... @RequestMapping("/welcome"))
        </h3>


        <form method="POST" action="uploadFile" enctype="multipart/form-data">
                File to upload:
            <input type="file"
                   accept="image/x-png,image/gif,image/jpeg"
                   name="file"><br />
            <input type="submit" value="Upload"> Press here to upload the file!
        </form>
        </div>
    </jsp:attribute>
</t:base>