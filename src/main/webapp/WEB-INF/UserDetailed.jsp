<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sync" uri="/WEB-INF/tlds/synctags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Detailed</title>
    </head>
    <body>
        <h1>User Detailed Page</h1>
        
        
        <form method="post" action="UserDetailed">
        First name<input type="text" name="fname" value="${fname}">
        <br>
        Last name<input type="text" name="lname" value="${lname}">
        <br>
        Email<input type="email" name="email" value="${email}">
        <br>
        Password<input type="text" name="password" value="${password}">
        <br>
        Location<input type="text" name="location" value="${location}">
        <input type="submit">
        </form>
        <sync:nav></sync:nav>
    </body>
</html>
