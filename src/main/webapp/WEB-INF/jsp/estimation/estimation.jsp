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

        

        <c:choose>
            <c:when test="${phase == 1}"> <%-- FOR PRIORITY ESTIMATES --%>
                <c:choose>
                    <c:when test="${not empty userStories}">
                        <h1>Estimate the priorities</h1>
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

            </c:when>

            <c:when test="${phase == 2}"> <%-- FOR PLANNING POKER ESTIMATES --%>
                <c:choose>
                    <c:when test="${not empty userStories}">
                        <h1>Planning Poker Party Time</h1>
                        <c:forEach var="story" items="${userStories}">
                            <p>User Story:</p>
                            <p>${story.textContent}</p>
                            <p>${story.author}</p>
        
                            
                            <sf:form method="POST" modelAttribute="planningPokerEstimate" action="/planningpoker/estimate" accept-charset="UTF-8">
                                <sf:input path="userStory.id" type="hidden" value="${story.id}"></sf:input>
        
                                <label for="planningPoker">Your estimation:</label>
                                <sf:select path="estimate" id="planningPoker" >
                                    <option value="0">0</option>
                                    <option value="0.5">1/2</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="5">5</option>
                                    <option value="8">8</option>
                                    <option value="13">13</option>
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="100">100</option>
                                </sf:select>
                                <input class="button" type="Submit" value="Submit estimate" />
                            </sf:form>
        
                                <c:forEach var="estimate" items="${story.planningPokerEstimates}">
        
                                    <sf:form method="DELETE" modelAttribute="planningPokerEstimate" action="/planningpoker/estimate" accept-charset="UTF-8">
                                        <p>An estimate: ${estimate.estimate} </p>
                                        <sf:input path="userStory.id" type="hidden" value="${story.id}"></sf:input>
                                        <sf:input path="id" type="hidden" value="${estimate.id}"></sf:input>
                                        <input class="button" type="Submit" value="Delete this estimate" />
                                    </sf:form>
                                </c:forEach>
                        <hr>
                        </c:forEach>
        
                        <sf:form method="PATCH" modelAttribute="planningPokerEstimates" action="/planningpoker/finalizeEstimates" accept-charset="UTF-8">
                            <input class="button" type="Submit" value="Finalize Planning Poker" />
                        </sf:form>
        
                    </c:when>
                    <c:otherwise>
                        <p><b>No user stories!</b></p>
                        <input class="button" type="button"  onclick="location.href='/userstory/create'" value="Create the first one" >
                    </c:otherwise>
        
                </c:choose>

            </c:when>

        </c:choose>




        
    </body>

</html>