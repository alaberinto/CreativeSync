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
            <form action="EditUser" method="post">
                <div class="row">
                    <div class="col-3">
                        Prof Pic<br>
                        <button type="submit" name="action" value="edit">Update</button>
                        <input type="hidden" name="thisUser" value="${requestScope.myUser.getFirstname()} ${requestScope.myUser.getLastname()}">
                    </div>
                    <div class="col-3">
                        First Name:<input type="text" value="${myUser.getFirstname()}" name="firstname" required><br>
                        Last Name:<input type="text" value="${myUser.getLastname()}" name="lastname" required><br>
                        Email: <input type="email" value="${myUser.getEmail()}" name="email" disabled><br>
                        Rate: <input type="number" value="${myUser.getRate()}" name="rate" required><br>
                        <label class="location" for="location"><h3 class="mb-0">Country</h3></label>

                        <select class="form-control text-light-gray-full" id="location" name="location" required>
                            <option value="" selected disabled hidden>Choose Country</option>
                            <c:forEach items="${locations}" var="location">                            
                                <option value="${location.locationId}">${location.locationDesc}</option>
                            </c:forEach>
                        </select>

                        <label class="" for="pos"><h3 class="mb-0">Position</h3></label>
                        <select class="form-control text-light-gray-full" id="pos" name="position" required>
                            <option value="" selected disabled hidden>Choose Position</option>
                            <c:forEach items="${positions}" var="pos">
                                <c:if test="${pos.positionId == selected}">
                                    <option value="${pos.positionId}"selected="${selected}">${pos.positionDesc}</option>
                                </c:if>
                                <option value="${pos.positionId}">${pos.positionDesc}</option>
                            </c:forEach>
                        </select>

                    </div>
                    <div class="col-3">
                        <label for="language"><h3 class="mb-0">Language</h3></label>
                        <select class="selectpicker form-control" name="language" id="language" multiple data-live-search="true" title="Select Languages" id="x" data-header="Select Languages" required>
                            <option value="" selected disabled hidden>Choose Language</option>
                            <c:forEach items="${languages}" var="lang">
                                <option value="${lang.languageId}">${lang.languageName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label for="genres"><h3 class="mb-0">Genres</h3></label>
                        <select class="selectpicker form-control" name="genres" id="genres" multiple data-live-search="true" title="Select Genres" id="y" data-header="Select Genres" required>
                            <c:forEach items="${genres}" var="gen">
                                <option value="${gen.genreId}">${gen.genreDesc}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form>
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
    </body>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
</html>
