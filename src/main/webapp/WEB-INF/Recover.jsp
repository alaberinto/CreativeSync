<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sync" uri="/WEB-INF/tlds/synctags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Login Recovery</title>
    </head>
    <body>
        <div class="background">
            <div class="vertical-center">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12 col-md-12 col-lg-6 offset-lg-3 col-xl-6 offset-xl-3">
                            <c:choose>
                                <c:when test="${sent == null}">
                                    <h2 class="text-center text-light">Reset Password</h2>
                                    <p class="text-left text-light text-center">You will receive a confirmation code if the entered email is valid.</p>
                                    <form action="Recover" method="post">
                                        <input class="form-control" type="text" name="email" placeholder="Email" autofocus required>
                                        <button class="btn btn-block button-red" name="action" value="recover" type="submit">RESET PASSWORD</button>
                                    </form>
                                    <form action="Recover" method="post">
                                        <button class="mt-5 btn btn-block button-grey" name="action" value="login" type="submit">LOGIN</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <h2 class="text-center text-light">Thank You</h2>
                                    <h3 class="text-center text-light">Enter your secure code from the provided email address.</h3>
                                    <form action="Recover" method="post">
                                        <input class="form-control" type="text" name="rid" placeholder="Secure Code" autofocus required>
                                        <button class="btn btn-block button-red" name="action" value="codeRecover" type="submit">SUBMIT</button>
                                    </form>
                                    <form action="Recover" method="post">
                                        <button class="btn btn-block button-grey mt-5" name="action" value="retry" type="submit">RETRY</button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <c:if test="${goodFeedback != null}">
                        <div class="alert alert-success fixed-bottom ml-2 mr-2">
                            <strong>Success</strong> ${goodFeedback}
                        </div>
                    </c:if>
                    <c:if test="${badFeedback != null}">
                        <div class="alert alert-danger fixed-bottom ml-2 mr-2">
                            <strong>Error</strong> ${badFeedback}
                        </div>
                    </c:if> 
                </div>
            </div>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(".alert-dismissible").fadeTo(2000, 500).slideUp(500, function () {
            $(".alert-dismissible").slideUp(500);
        });
    </script>
</html>
