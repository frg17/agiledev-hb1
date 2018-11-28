<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Project Party</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>"/> </head> <body>
    <div class='view__container'>
        <div class='view__row'>
            <div class='view__col'>
                <div class='login'>
                    <div class='login__row'>
                        <div class='login__col'>
                            <h1 class='login__title'>Login</h1>

                                <sf:form class='login__form' method="POST" modelAttribute="project" action="/projects/login">
                                    <sf:input class='form__text' path="token" type="text" placeholder="Project token" />
                                    <input class="button form__button" type="submit" VALUE="Login" />
                                </sf:form>

                                <a href="/projects/create">Create project</a>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </body>

</html>