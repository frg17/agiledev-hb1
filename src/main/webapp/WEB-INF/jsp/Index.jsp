<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Project Title</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
        <script src="<c:url value="/js/script.js"/>"/> </script>
    </head>

    <body>
        <c:choose>
            <c:when test="${loggedIn}">
            
            <%@ include file="./Header.jspf" %>
            <h1>User Stories</h1>
                
                <a class="button" href="/userstory/create">Create New User story</a>
                <!-- ALLAR USER STORIES FYRIR VERKEFNI -->
                <c:choose>

                    <c:when test="${not empty userStories}">
                        <hr>
                        <c:forEach var="story" items="${userStories}">
                            <p>${story.textContent}</p>
                            <p>${story.author}</p>
                            <p>Planning poker estimate: ${story.planningPokerPriority}</p>
                            <p>Priority: ${story.priority}</p>
                            <a class="button" href="/userstory/edit/${story.id}">Edit</a>
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

        <%@ include file="./SettingsTab.jspf" %>
    
        <footer>Class HBV501G, University of Iceland</footer>
    </body>
</html>
