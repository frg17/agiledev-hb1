<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <title>Project Party</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
    </head>
    <body>
    
    <h1>All Projects</h1>


    <c:choose>
        <c:when test="${not empty projects}">
            <table class="project">

                <c:forEach var="project" items="${projects}">
                    <tr>
                        <td><a href="/postit/${project.name}">${project.name}</a></td>
                        <td>${project.token}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>

        <c:otherwise>
            <h3>No projects!</h3>
        </c:otherwise>
    </c:choose>

    </body>
</html>
