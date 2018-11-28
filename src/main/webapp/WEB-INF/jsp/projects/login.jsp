<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Project Party</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto+Slab" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/> 
</head>

<body>
    <%@ include file="../Header.jspf" %> 

    <main class="main">
        <div class='login'>
            <div class='login__row'>
                <div class='login__col'>
                    <h1 class='login__title'>Login</h1>

                    <sf:form class='login__form' method="POST" modelAttribute="project" action="/projects/login">
                        <sf:input class='form__text' path="token" type="text" placeholder="Project token" />
                        <input class="button form__button" type="submit" VALUE="Login" />
                    </sf:form>

                    <a class="button" href="/projects/create">Create project</a>

                </div>
            </div>
        </div>
    </main>
    
    <%@ include file="../footer.jspf" %>
    </body>

</html>