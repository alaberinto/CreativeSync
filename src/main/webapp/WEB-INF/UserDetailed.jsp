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
        <sync:navbar1></sync:navbar1>
            <div class="searchBarHeader"></div>
            <div class="container bg-white">
                <div class="page-header border-bottom mb-3 text-center">${myUser.user.firstname} ${myUser.user.lastname}</div>
            <div class="row">
                <div class="col-3 justify-content-center">

                    <div class="row">
                        <div class="float-center">
                            <img class="img-fluid rounded-circle" src="css/profile-placeholder.png" alt="">
                        </div>
                    </div>
                    <form action="UserDetailed" class="mt-3" method="post">
                        <c:if test="${user.position.positionId == 1 || user.position.positionId == 2 }">
                            <input class="btn btn-block bg-secondary" type="hidden" name="thisUser" value="${myUser.user.firstname} ${myUser.user.lastname}">
                            <button type="submit" name="action" value="delete" class="btn btn-block btn-secondary">Delete</button>
                        </c:if>
                    </form>
                    <form method="get" action="EditUser?name=${myUser.user.firstname} ${myUser.user.lastname}" class="mt-1">
                        <c:if test="${user.position.positionId == 1 || (user.position.positionId == 2 && user.position.positionId < myUser.user.position.positionId) || user.userId == myUser.user.userId}">
                            <a href="EditUser?name=${myUser.user.firstname} ${myUser.user.lastname}" <button type="submit" name="action" value="edit" class="btn btn-block button-red-solid">Edit</button></a>
                        </c:if>
                    </form>
                </div>
                <div class="col-5 pl-4 align-middle">
                    <h2 class="list-item-header-n border-bottom">Information</h2>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item borderless"><b>Email: </b>${myUser.user.email}</li>
                        <li class="list-group-item borderless"><b>Rate: </b>$${myUser.user.rate} USD</li>
                        <li class="list-group-item borderless"><b>Location: </b>${myUser.user.location.locationDesc}</li>
                        <li class="list-group-item borderless"><b>Position: </b>${myUser.user.position.positionDesc}</li>
                    </ul>
                </div>
                <div class="col-2">
                    <h2 class="list-item-header-n text-center border-bottom">Languages</h2>
                    <ul class="list-group list-group-flush text-center">
                        <c:forEach items="${myUser.user.languageList}" var="lang">
                            <li class="list-group-item borderless">${lang.languageName}</li>
                            </c:forEach>
                    </ul>
                </div>
                <div class="col-2">
                    <h2 class="list-item-header-n text-center border-bottom">Genres</h2>
                    <ul class="list-group list-group-flush text-center">
                        <c:forEach items="${myUser.user.genreList}" var="gen">
                            <li class="list-group-item borderless">${gen.genreDesc}</li>
                            </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-12">
                    <div class="list-group">
                        <table class="table text-center">
                            <tr class="table-secondary">
                                <th><b>Title Name</b></th>
                                <th><b>Status</b></th>
                                <th><b>End Date</b></th>
                                <th></th>
                            </tr>
                            <c:forEach items="${myUser.titles}" var="tit"  >
                                <tr>
                                    <td>
                                        <b>${tit.title.name}</b>
                                    </td>
                                    <td>
                                        ${tit.status.statusDesc}
                                    </td>
                                    <td>
                                        ${tit.title.endDate}
                                    </td>
                                    <td>
                                        <a href="TitleDetailed?name=${tit.title.name}"class="list-group-item-action">
                                            <button class="btn btn-block btn-secondary">View</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <c:if test="${empty myUser.titles || myUser.titles == null}">
                            <h3 class="text-center">No Titles Found</h3>
                        </c:if>
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
        </div>
    </body>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(".alert-dismissible").fadeTo(2000, 500).slideUp(500, function () {
            $(".alert-dismissible").slideUp(500);
        });
    </script>
</html>
