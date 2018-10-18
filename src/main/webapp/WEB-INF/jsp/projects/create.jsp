<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Project Party</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
    </head>
    <body>
    
    <h1>Create Project</h1>

    <sf:form method="POST" modelAttribute="project" action="/projects/create" accept-charset="UTF-8">
        <label>Project name: </label>
        <sf:input path="name" type="text" placeholder="Enter name"/><br>
        <label>Project token: </label>
        <sf:input path="token" type="text" placeholder="Make it hard to guess."/><br>
        <input type="submit" VALUE="Create"/>
    </sf:form>

    </body>
</html>
