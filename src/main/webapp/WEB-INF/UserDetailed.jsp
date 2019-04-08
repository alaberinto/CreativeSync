<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sync" uri="/WEB-INF/tlds/synctags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" 
              integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>My Account</title>
    </head>
    <body class="background-plain">
        <sync:navbar1>
        </sync:navbar1>
        <div class="searchBarHeader">
            <div class="container">

            </div>
        </div>

        <div class="container fullContainer bg-white">
            <div class="row">
                <div class="col-3">
                    Prof Pic<br>
                    <form action="UserDetailed" method="post">
                        <button type="submit" name="action" value="edit">Edit</button>
                        <c:if test="${user.position.positionId == 1}">
                            <button type="submit" name="action" value="delete">Delete</button>
                        </c:if>
                        <input type="hidden" name="thisUser" value="${myUser.user.firstname} ${myUser.user.lastname}">
                    </form>
                </div>
                <div class="col-3">
                    ${myUser.user.firstname} ${myUser.user.lastname}<br>
                    ${myUser.user.email}
                    ${myUser.user.rate}
                    ${myUser.user.location.locationDesc}
                    ${myUser.user.position.positionDesc}
                </div>
                <div class="col-3">
                    <c:forEach items="${myUser.user.languageList}" var="lang">
                        ${lang.languageName}
                    </c:forEach>
                </div>
                <div class="col-3">
                    <c:forEach items="${myUser.user.genreList}" var="genre">
                        ${genre.genreDesc}
                    </c:forEach>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="list-group">
                        <c:forEach items="${myUser.titles}" var="tit">
                            <c:if test="${empty myUser.titles || myUser.titles == null}">
                                No Titles Found
                            </c:if>

                            <a href="TitleDetailed?name=${tit.title.name}" class="list-group-item list-group-item-action">
                                ${tit.title.name} ${tit.status.statusDesc} ${tit.title.endDate}
                            </a>
                        </c:forEach>
                    </div>
                </div>
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
    </body>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
</html>
