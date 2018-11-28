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
        <div class='create'>
            <h1 class="create__heading">Create Project</h1>
            <div class='create__row'>
                <div class='create__col'>
                    
                    <sf:form class='create__form' method="POST" modelAttribute="project" action="/projects/create" accept-charset="UTF-8">
                        <label>Project name: </label>
                        <sf:input path="name" type="text" placeholder="Enter name" />
                        <label>Project token: </label>
                        <sf:input path="token" type="text" placeholder="Make it hard to guess." />
                        <input class="button" type="submit" VALUE="Create" />
                    </sf:form>

                    <a class="button" href="/">Back</a>
                </div>
            </div>
        </div>
    </main>

    <%@ include file="../footer.jspf" %>
    
    </body>

</html>