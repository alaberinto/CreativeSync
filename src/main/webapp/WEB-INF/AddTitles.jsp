<%-- 
    Document   : AddTitles
    Created on : Feb 19, 2019, 10:04:25 PM
    Author     : 731866
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="AddTitles">
            <h1>Add Title</h1>
            Name<input type="text" name="name">
            <br>
            End Date<input type="date" name="date">
            <br>
            Make Title a priority<fieldset>
                <input type="radio" name="priority" value="0"> Not a priority
                <input type="radio" name="priority" value="1"> Is a priority
            </fieldset>
            <br>
            Design info<input type="text" name="info" rows="4" cols="50">
            <input type="submit">
            <c:forEach items="${users}" var="user">

            </c:forEach>
        </form>


    </body>
</html>
