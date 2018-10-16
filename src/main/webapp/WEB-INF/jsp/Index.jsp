<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Project Title</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
    </head>
    <body>
        <c:choose>
            <c:when test="${loggedIn}">
                <header>
                    <form id="logout-form" method="POST" action="/projects/logout">
                        <a onclick="document.getElementById('logout-form').submit();">Log out</a>
                    </form>
                </header>

                <!-- NEW USER STORY FORM -->
                <sf:form method="POST" modelAttribute="userStory" action="/userstory/create" accept-charset="UTF-8">
                    <label>Author: </label>
                    <sf:input path="author" type="text" placeholder="Author name" /><br>
                    <label>Story: </label>
                    <sf:textarea path="textContent" type="text" placeholder="..." /><br>
                 
                    <input type="submit" VALUE="Share"/>

                </sf:form>

                <!-- ALLAR USER STORIES FYRIR VERKEFNI -->
                <c:choose>

                    <c:when test="${not empty userStories}">
                        <hr>
                        <c:forEach var="story" items="${userStories}">
                            <p>${story.textContent}</p>
                            <p>${story.author}</p>
                            <hr>
                        </c:forEach>
                    </c:when>

                    <c:otherwise>
                        <p><b>No user stories! <i>Create the first one!</i></b></p>
                    </c:otherwise>

                </c:choose>
            </c:when>

            <c:otherwise>
                
            </c:otherwise>
        </c:choose>
        
    
        <footer>Class HBV501G, University of Iceland</footer>
    </body>
</html>
