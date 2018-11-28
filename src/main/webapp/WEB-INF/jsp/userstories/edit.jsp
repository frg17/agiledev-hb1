<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Edit user story</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"> <script src="<c:url value="/js/script.js"/>"/>
        </script> </head> <body>
    <div class='view__container'>
        <%@ include file="../Header.jspf" %>
        <div class='view__row'>
            <div class='view__col'>
                <h1>Edit user story</h1>
                <div class='write'>
                    <div class='write__row'>
                        <div class='write__col'>

                            <sf:form class='write__form' method="PATCH" modelAttribute="userStory" action="/userstory/edit"
                                accept-charset="UTF-8">
                                <sf:input path="id" type="hidden" value="${userStory.id}" />
                                <label>User story: </label>
                                <sf:textarea path="textContent" type="text" value="${userStory.textContent}"></sf:textarea>
                                <label>Author: </label>
                                <sf:input path="author" type="text" value="${userStory.author}"></sf:input>
                                <input class="button" type="Submit" value="Edit" />
                                <a class="button" href="/">Go back</a>
                            </sf:form>

                            <sf:form method="DELETE" modelAttribute="userStory" action="/userstory/delete"
                                accept-charset="UTF-8">
                                <sf:input path="id" type="hidden" value="${userStory.id}" />
                                <input class="button" type="Submit" value="Delete">
                            </sf:form>



                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="../SettingsTab.jspf" %>
    </div>


    </body>


</html>