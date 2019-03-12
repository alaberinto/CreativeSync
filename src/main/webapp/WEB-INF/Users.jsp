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
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Users</title>
    </head>
    <body class="background-plain">
        <sync:navbar></sync:navbar>
            <div class="searchBarHeader">
                <div class="container p-0">
                    <row>
                        <div class="col-12 p-0">
                            <form>
                                <div class="form-group">
                                    <input class="form-control searchBar" type="text" id="search" placeholder="Search User By Keyword">
                                </div>
                            </form>
                        </div>
                    </row>
                </div>
            </div>
            <div class="container fullContainer bg-white">
                <div class="row">
                    <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 mt-4">
                        <ul class="list-group list-group-flush">
                            <form method="post" action="Titles">
                            <c:forEach items="${users}" var="u">
                                <li class="list-group-item">
                                    <div class="row top-bottom-border background-gray">
                                        <div class="col-6">
                                            <h3 class="list-item-head">${u.user.firstname} ${u.user.lastname}</h3>
                                        </div>
                                        <div class="col-6">
                                            <h3 class="list-item-head">${u.user.position.positionDesc}</h3>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-6">
                                            <b class="bold-head ml-2">Title</b>
                                            <c:forEach items="${u.titles}" var="tit">
                                                <h6 class="ml-2">${tit.title.name}</h6>
                                            </c:forEach>
                                        </div>
                                        <div class="col-6">
                                            <b class="bold-head ml-2">Status</b>
                                            <c:forEach items="${u.titles}" var="tit">
                                                <h6 class="ml-2">${tit.status.statusDesc}</h6>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </form>
                        <br>
                    </ul>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 mt-4">
                    <h3><b>Job Position</b></h3>
                    <form>
                        <c:forEach items="${positions}" var="pos">
                            <div class="checkbox mt-1">
                                <input type="checkbox" id="${pos.positionId}" value="${pos.positionId}">
                                <label for="${pos.positionId}">${pos.positionDesc}</label>
                            </div>
                        </c:forEach>
                    </form>
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