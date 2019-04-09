<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sync" uri="/WEB-INF/tlds/synctags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" 
              integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans" rel="stylesheet">
        <link rel="stylesheet" href="css/loginStyle.css" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

        <title>Login</title>
    </head>
    <body>
        <div class="background">
            <div class="vertical-center">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12 col-md-12 col-lg-6 offset-lg-3 col-xl-6 offset-xl-3">
                            <h2 class="text-center text-light">Netflix Sync Login</h2>
                            <p class="text-left text-light">PLEASE SIGN IN</p>
                            <form action="Login" method="post">
                                <input class="form-control" type="email" name="username" placeholder="Email" autofocus required>
                                <input class="form-control" type="password" name="password" placeholder="Password" required>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <button class="btn btn-block button-red" name="action" value="login" type="submit">PARTNER SIGN IN</button>
                                    </div>
                            </form>

                            <div class="col-lg-6">
                                <form action="Login" method="post">
                                    <button class="btn btn-block button-grey" name="action" value="recover" type="submit">FORGOT PASSWORD</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <c:if test="${goodFeedback != null}">
                    <div class="alert alert-success alert-dismissible fixed-bottom ml-2 mr-2">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Success</strong> ${goodFeedback}
                    </div>
                </c:if>
                <c:if test="${badFeedback != null}">
                    <div class="alert alert-danger alert-dismissible fixed-bottom ml-2 mr-2">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Error</strong> ${badFeedback}
                    </div>
                </c:if>
            </div>
        </div>
    </div>

</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
crossorigin="anonymous"></script>
</html>
