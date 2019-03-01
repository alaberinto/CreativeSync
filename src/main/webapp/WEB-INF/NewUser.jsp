<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sync" uri="/WEB-INF/tlds/synctags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New User</title>
    </head>
    <body>
        <form action="NewUser" method="post">
            <h1>Registration Page</h1>
            First name
            <input type="text" name="fname">
            <br>
            Last name
            <input type="text" name="lname" >
            <br>
            Email
            <input type="email" name="email"  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
            <br>
            Password
            <input type="text" name="password">

            <br>
            Location
            <input type="text" name="location">
            <br>
            Rate
            <input type="number" name="rate" step=".01">
            Position
            <fieldset id="position">
                <input type="radio" name="position" value="10">Admin
                <input type="radio" name="position" value="13">Coordinator
                <input type="radio" name="position" value="12">Design lead
                <input type="radio" name="position" value="11">FreeLancer
            </fieldset>
            <input type="Submit">
        </form>
        <sync:nav></sync:nav>
    </body>
</html>
