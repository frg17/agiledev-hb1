<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Edit user story</title>

    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css'/>">
    <script src="<c:url value='/js/script.js'/>"></script>
</head>

<body>
    <div class='view__container'>
        
        <%@ include file="../Header.jspf" %>
        <div class='view__row'>
            
            <div class='view__col'>
                
        <h1>Create user story</h1>
                <div class='write'>
                    <div class='write__row'>
                        <div class='write__col'>

                            <sf:form class='write__form' method="POST" modelAttribute="userStory" action="/userstory/create" accept-charset="UTF-8">
                                <label>User story: </label>
                                <sf:textarea path="textContent" type="text" value="${userStory.textContent}"></sf:textarea>
                                <label>Author: </label>
                                <sf:input path="author" type="text" value="${userStory.author}"></sf:input>
                                <input type="Submit" value="Create" class="button" />
                                <a class="button" href="/">Go back</a>
                            </sf:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../SettingsTab.jspf" %>
</body>

</html>