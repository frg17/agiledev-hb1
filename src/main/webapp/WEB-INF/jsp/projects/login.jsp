<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Project Party</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/>
        <script src="<c:url value="/js/hehe.js"/>"/> </script>
    </head>
    <body>

    <h1>Login</h1>

    <sf:form method="POST" modelAttribute="project" action="/projects/login">
        <sf:input path="token" type="text" placeholder="Project token"/><br>
        <input class="button" type="submit" VALUE="Login"/>
    </sf:form>

    <a href="/projects/create">Create project</a>

    </body>
</html>
