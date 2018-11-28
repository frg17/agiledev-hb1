<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Project Title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto+Slab" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css'/>" />
        <script src="<c:url value='/js/script.js'/>"> </script>
    </head>
    
    <body>
        <%@ include file="./Header.jspf" %>

        <main class="main">
                <div class='view__row'>
                    <div class='view__col index__col'>
                        <div class='menu__markings'>
                            <h1 class='markings__title'>User Stories</h1>
                            <div class='markings__numbers'>
                                <h3 class='markings__estimates'>Estimate</h3>
                                <h3 class='markings__priority'>Priority</h3>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${loggedIn}">
                                <div class='stories'>
                                    <!-- ALLAR USER STORIES FYRIR VERKEFNI -->
                                    <c:choose>
                                        <c:when test="${not empty userStories}">
                                            <hr class='stories__spacer'>
                                            <c:forEach var="story" items="${userStories}">
                                                <div class='story'>
                                                    <div class='story__text-content'>
                                                        <p class='story__text'>${story.textContent}</p>
                                                        <div class='story__editing'>
                                                            <p class='story__author'>Author: ${story.author}</p>
                                                            <a class="button story__button" href="/userstory/edit/${story.id}">Edit</a>
                                                        </div>
                                                    </div>
                                                    <div class='story__numbers'>
                                                        <p class='story__estimate'>${story.planningPokerPriority}</p>
                                                        <p class='story__priority'>${story.priority}</p>
                                                    </div>
                                                </div>
                                                <hr class='stories__spacer'>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <p><b>No user stories! <i>Create the first one!</i></b></p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:when>
        
                            <c:otherwise>
        
                            </c:otherwise>
                        </c:choose>
                        <a class="button menu__story-button" href="/userstory/create">Create New User story</a>
                    </div>
                </div>
        </main>

        <%@ include file="./footer.jspf" %>
    
</body>

</html>