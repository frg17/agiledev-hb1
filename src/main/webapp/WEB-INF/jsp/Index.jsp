<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Project Title</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
        <script src="<c:url value="/js/hehe.js"/>"/> </script>
    </head>
    <body>
        <c:choose>
            <c:when test="${loggedIn}">
                <header>
                    <form id="logout-form" method="POST" action="/projects/logout">
                        <a class="button" onclick="document.getElementById('logout-form').submit();">Log out</a>
                    </form>
                </header>

                <a class="button" href="/userstory/create">Create New User story</a>
                <!-- ALLAR USER STORIES FYRIR VERKEFNI -->
                <c:choose>

                    <c:when test="${not empty userStories}">
                        <hr>
                        <c:forEach var="story" items="${userStories}">
                            <p>${story.textContent}</p>
                            <p>${story.author}</p>
                            <p>Priority: ${story.priority}</p>
                            <a href="/userstory/edit/${story.id}"><button>Edit</button></a>
                            <hr>
                        </c:forEach>

                        <input type="button"  onclick="location.href='/estimation'" value="Estimate the User Stories" >
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
