<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Edit user story</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">
        <script src="<c:url value="/js/script.js"/>"/> </script>
    </head>
    
    <body>
        <%@ include file="../Header.jspf" %>

        <h1>Edit user story</h1>
        
        <sf:form method="PATCH" modelAttribute="userStory" action="/userstory/edit" accept-charset="UTF-8">
            <sf:input path="id" type="hidden" value="${userStory.id}"/>
            <sf:textarea path="textContent" type="text" value="${userStory.textContent}"></sf:textarea><br>
            <label>Author: </label>
            <sf:input path="author" type="text" value="${userStory.author}"></sf:input><br>
            <input class="button" type="Submit" value="Edit" />
            <a class="button" href="/">Go back</a>
        </sf:form>
        
        <sf:form method="DELETE" modelAttribute="userStory" action="/userstory/delete" accept-charset="UTF-8">
            <sf:input path="id" type="hidden" value="${userStory.id}"/>
            <input class="button" type="Submit" value="Delete">
        </sf:form>



         <%@ include file="../SettingsTab.jspf" %>
    </body>
</html>
