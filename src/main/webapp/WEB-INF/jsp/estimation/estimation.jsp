<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Priority Page</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
    </head>
    <body>
        <header>
            <form id="logout-form" method="POST" action="/projects/logout">
                <a class="button" onclick="document.getElementById('logout-form').submit();">Log out</a>
            </form>
        </header>

        <h1>Estimate the priorities</h1>

        <c:choose>
            <c:when test="${not empty userStories}">
                <hr>
                <c:forEach var="story" items="${userStories}">
                    <p>User Story:</p>
                    <p>${story.textContent}</p>
                    <p>${story.author}</p>

                    <sf:form method="POST" modelAttribute="priorityEstimate" action="/priority/estimate" accept-charset="UTF-8">
                        <sf:input path="userStory.id" type="hidden" value="${story.id}"></sf:input>

                        <label for="priority">Your estimation:</label>
                        <sf:input path="estimate" type="number" id="priority" min="1"></sf:input>
                        <input class="button" type="Submit" value="Submit estimate" />
                    </sf:form>
                        <c:forEach var="estimate" items="${story.priorityEstimates}">

                            <sf:form method="DELETE" modelAttribute="priorityEstimate" action="/priority/estimate" accept-charset="UTF-8">
                                <p>An estimate: ${estimate.estimate} </p>
                                <sf:input path="userStory.id" type="hidden" value="${story.id}"></sf:input>
                                <sf:input path="id" type="hidden" value="${estimate.id}"></sf:input>
                                <input class="button" type="Submit" value="Delete this estimate" />
                            </sf:form>
                        </c:forEach>
                <hr>
                </c:forEach>

                <sf:form method="PATCH" modelAttribute="priorityEstimates" action="priority/finalizeEstimates" accept-charset="UTF-8">
                    <input class="button" type="Submit" value="Finalize estimates" />
                </sf:form>

            </c:when>
            <c:otherwise>
                <p><b>No user stories!</b></p>
                <input class="button" type="button"  onclick="location.href='/userstory/create'" value="Create the first one" >
            </c:otherwise>

        </c:choose>
    </body>

</html>