<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Edit user story</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">
    </head>
    
    <body>
        <header>
            <form id="logout-form" method="POST" action="/projects/logout">
                <a class="button" onclick="document.getElementById('logout-form').submit();">Log out</a>
            </form>
        </header>

        <h1>Edit user story</h1>
        
        <sf:form method="PATCH" modelAttribute="userStory" action="/userstory/edit" accept-charset="UTF-8">
            <sf:input path="id" type="hidden" value="${userStory.id}"/>
            <sf:input path="projectId" type="hidden" value="${userstory.projectId}" />
            <sf:textarea path="textContent" type="text" value="${userStory.textContent}"></sf:textarea><br>
            <label>Author: </label>
            <sf:input path="author" type="text" value="${userStory.author}"></sf:input><br>
            <input type="Submit" value="Edit" />
        </sf:form>
    </body>
</html>
