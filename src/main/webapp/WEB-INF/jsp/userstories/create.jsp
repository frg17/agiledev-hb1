<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Edit user story</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto+Slab" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css'/>">
    <script src="<c:url value='/js/script.js'/>"></script>
</head>

<body>
    <%@ include file="../Header.jspf" %>
    <%@ include file="../SettingsTab.jspf" %>
    <main class="main">
            <h1 class="heading--create">Create user story</h1>
            <div class='write'>
                <sf:form class='write__form' method="POST" modelAttribute="userStory" action="/userstory/create" accept-charset="UTF-8">
                    <label>User story: </label>
                    <sf:textarea path="textContent" class="write__form__text" type="text" value="${userStory.textContent}"></sf:textarea>
                    <div class="write__author">
                        <label>Author: </label>
                        <sf:input path="author" type="text" value="${userStory.author}"></sf:input>
                        <div class="container--buttons">
                            <input type="Submit" value="Create" class="button" />
                            <a class="button" href="/">Go back</a>
                        </div>
                    </div>
                    
                </sf:form>
            </div>
    </main>

    <%@ include file="../footer.jspf" %>
    
</body>

</html>