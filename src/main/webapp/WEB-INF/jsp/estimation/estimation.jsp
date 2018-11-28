<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Priority Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto+Slab" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css'/>" />
    <script src="<c:url value='/js/script.js'/>" />
    </script>
</head>

<body>
    <%@ include file="../Header.jspf" %>
    <%@ include file="../SettingsTab.jspf" %>
    <main class="main">
                <div class='view__row'>
                    <div class='view__col'>
        
                        <c:choose>
                            <c:when test="${phase == 1}">
                                <%-- FOR PRIORITY ESTIMATES --%>
                                <c:choose>
                                    <c:when test="${not empty userStories}">
        
                                        <div class='priority'>
                                            <h1>Estimate the priorities test</h1>
                                            <div class='estimates__outer'>
                                                <c:forEach var="story" items="${userStories}">
                                                    <div class='estimation'>
                                                        <div class='story estimation__story'>
                                                            <p>User Story:</p>
                                                            <div class='story__text-content'>
                                                                <p class='story__text'>${story.textContent}</p>
                                                                <p class='story__author'>Author: ${story.author}</p>
                                                            </div>
                                                        </div>
        
                                                        <sf:form class='estimation__form' method="POST" modelAttribute="priorityEstimate"
                                                            action="/priority/estimate" accept-charset="UTF-8">
                                                            <sf:input path="userStory.id" type="hidden" value="${story.id}"></sf:input>
                                                            <div class='estimation__choice'>
                                                                <label class='estimation__label' for="priority">Estimation:</label>
                                                                <sf:select path="estimate" id="priority">
                                                                    <option value="0">0</option>
                                                                    <option value="10">10</option>
                                                                    <option value="20">20</option>
                                                                    <option value="30">30</option>
                                                                    <option value="40">40</option>
                                                                    <option value="50">50</option>
                                                                    <option value="60">60</option>
                                                                    <option value="70">70</option>
                                                                    <option value="80">80</option>
                                                                    <option value="90">90</option>
                                                                    <option value="100">100</option>
                                                                </sf:select>
        
                                                            </div>
        
                                                            <div class='estimation__explanation'>
                                                                <label for="explanation">Explanation:</label>
                                                                <sf:textarea class='explanation__input' path="explanation" type="text"
                                                                    id="explanation"></sf:textarea>
                                                            </div>
                                                            <input class="button estimation__submit" type="Submit" value="Submit" />
                                                        </sf:form>
                                                        <div class="estimates">
                                                            <c:forEach var="estimate" items="${story.priorityEstimates}">
                                                                <div class="estimate" id="${estimate.id}">
                                                                        <p class="estimate__nr">Estimate: ${estimate.estimate}</p>
                                                                        <div class="estimate__explanation" style="display: none;">Explanation:
                                                                            <p class='explanation__text'>
                                                                                ${estimate.explanation}
                                                                            </p>
                                                                        </div>
                                                                    <sf:form method="DELETE" modelAttribute="priorityEstimate"
                                                                        action="/priority/estimate" accept-charset="UTF-8">
                                                                        <sf:input path="userStory.id" type="hidden" value="${story.id}"></sf:input>
                                                                        <sf:input path="id" type="hidden" value="${estimate.id}"></sf:input>
                                                                        <Button class="button estimate__delete" type="Submit"
                                                                            value="Delete this estimate" >
                                                                            <image src="<c:url value="/img/garbage.png"/>" alt="Delete"></image>
                                                                        </Button>
                                                                    </sf:form>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </c:forEach>
        
                                            </div>
                                            <sf:form method="PATCH" modelAttribute="priorityEstimates" action="priority/finalizeEstimates"
                                                    accept-charset="UTF-8">
                                                    <input class="button" type="Submit" value="Finalize estimates" />
                                                </sf:form>
                                        </div>
        
                                    </c:when>
                                    <c:otherwise>
                                        <p><b>No user stories!</b></p>
                                        <input class="button" type="button" onclick="location.href='/userstory/create'" value="Create the first one">
                                    </c:otherwise>
        
                                </c:choose>
        
                            </c:when>
                            <c:when test="${phase == 2}">
                                <%-- FOR PLANNING POKER ESTIMATES --%>
                                <c:choose>
                                    <c:when test="${not empty userStories}">
        
                                        <div class='priority'>
                                            <h1>Planning Poker</h1>
                                            <div class='estimates__outer'>
        
                                                <c:forEach var="story" items="${userStories}">
                                                    <div class='estimation'>
                                                        <div class='story estimation__story'>
                                                            <p>User Story:</p>
                                                            <div class='story__text-content'>
                                                                <p class='story__text'>${story.textContent}</p>
                                                                <p class='story__author'>Author: ${story.author}</p>
                                                            </div>
                                                        </div>
                                                        <sf:form class='estimation__form' method="POST" modelAttribute="planningPokerEstimate" action="/planningpoker/estimate"
                                                            accept-charset="UTF-8">
                                                            <sf:input path="userStory.id" type="hidden" value="${story.id}"></sf:input>
                                                            <div class='estimation__choice'>
                                                                <label for="planningPoker">Your estimation:</label>
                                                                <sf:select path="estimate" id="planningPoker">
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
                                                            </div>
        
                                                            <div class='estimation__explanation'>
                                                                <label for="planningPokerExplanation">Your explanation:</label>
                                                                <sf:textarea class='explanation__input' path="explanation" type="text"
                                                                    id="explanation"></sf:textarea>
        
                                                            </div>
                                                            <input class="button estimation__submit" type="Submit" value="Submit estimate" />
                                                        </sf:form>
                                                        <div class="estimates">
                                                            <c:forEach var="estimate" items="${story.planningPokerEstimates}">
                                                                <div class="estimate" id="$estimate.id">
                                                                        <p class="estimate__nr">Estimate: ${estimate.estimate}</p>
                                                                        <div class="estimate__explanation" style="display: none;">Explanation:
                                                                            <p class='explanation__text'>
                                                                                ${estimate.explanation}
                                                                            </p>
                                                                        </div>
                                                                    <sf:form method="DELETE" modelAttribute="planningPokerEstimate"
                                                                        action="/planningpoker/estimate" accept-charset="UTF-8">
                                                                        <sf:input path="userStory.id" type="hidden" value="${story.id}"></sf:input>
                                                                        <sf:input path="id" type="hidden" value="${estimate.id}"></sf:input>
                                                                        <Button class="button estimate__delete" type="Submit"
                                                                            value="Delete this estimate" >
                                                                            <image src="<c:url value="/img/garbage.png"/>" alt="Delete"></image>
                                                                        </Button>
                                                                    </sf:form>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </c:forEach>
        
                                            </div>
                                            <sf:form method="PATCH" modelAttribute="planningPokerEstimates" action="/planningpoker/finalizeEstimates"
                                                    accept-charset="UTF-8">
                                                    <input class="button" type="Submit" value="Finalize Planning Poker" />
                                                </sf:form>
                                        </div>
        
                                    </c:when>
                                    <c:otherwise>
                                        <p><b>No user stories!</b></p>
                                        <input class="button" type="button" onclick="location.href='/userstory/create'" value="Create the first one">
                                    </c:otherwise>
        
                                </c:choose>
        
                            </c:when>
                        </c:choose>
        
                    </div>
                </div>
        
        </main>
        
        <%@ include file="../footer.jspf" %>    
</body>

</html>