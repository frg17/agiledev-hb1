<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <title>Project Title</title>
    </head>
    <body>
    <header>
        <c:choose>
            <c:when test="${loggedIn}">
                <form id="logout-form" method="POST" action="/project/logout">
                    <input type="submit" value="Log out"/>
                </form>
            </c:when>
        </c:choose>
        
    </header>
    <h1>HBV501G Project Spring Boot Skeleton</h1>
    <p>This skeleton of a Spring Boot Web project was made to help groups get started on their projects without to much hassle.</p>

    <ul>
    </ul>
    </body>
    <footer>Class HBV501G, University of Iceland</footer>
</html>
