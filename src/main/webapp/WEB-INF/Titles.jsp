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
        <title>Titles</title>
    </head>
    <body class="background-plain">
        <sync:navbar1></sync:navbar1>
            <div class="searchBarHeader">
                <div class="container p-0">
                    <div class="row">
                        <div class="col-12 p-0">
                            <form method="get" action="Titles">
                                <div class="form-group">
                                    <input class="form-control searchBar" type="text" name="searchBar" value="${searchBar}" placeholder="Search By Name">
                                <input type="submit"  style="visibility: hidden;">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="container fullContainer bg-white">
            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 mt-4">
                    <c:choose>
                        <c:when test="${completed != null}">
                            <h1 class="list-header-n">Completed</h1>
                            <div class="list-group list-n">
                                <c:forEach items="${completed}" var="cTit">
                                    <a href="TitleDetailed?name=${cTit.title.name}" class="list-group-item list-group-item-action list-item-n">
                                        <h2 class="list-item-header-n">${cTit.title.name}</h2>
                                        <div class="row">

                                            <div class="col-6">
                                                <b class="list-item-sub-n ml-2">Status: </b> ${cTit.status.statusDesc}<br>
                                                <object><a href="UserDetailed?name=${cTit.lead.firstname} ${cTit.lead.lastname}">
                                                        <b class="ml-2">Proj. Lead:</b>  ${cTit.lead.firstname} ${cTit.lead.lastname}
                                                    </a>
                                                </object> 
                                            </div>

                                            <div class="col-6">
                                                <b class="list-item-sub-n ml-2">End:</b> <fmt:formatDate type = "date" value="${cTit.title.endDate}"/><br>
                                                <object><a href="UserDetailed?name=${cTit.coor.firstname} ${cTit.coor.lastname}">
                                                        <b class="ml-2">Coordinator:</b> ${cTit.coor.firstname}  ${cTit.coor.lastname}
                                                    </a>
                                                </object>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${assignedTitles != null}">
                                <h1 class="list-header-n">Assigned Titles</h1>
                                <div class="list-group list-n" name="titlesList">
                                    <c:forEach items="${assignedTitles}" var="aTit">
                                        <a href="TitleDetailed?name=${aTit.title.name}" class="list-group-item list-group-item-action list-item-n">
                                            <h2 class="list-item-header-n">${aTit.title.name}</h2>
                                            <div class="row">

                                                <div class="col-6">
                                                    <b class="list-item-sub-n ml-2">Status: </b> ${aTit.status.statusDesc}<br>
                                                    <object><a href="UserDetailed?name=${aTit.lead.firstname} ${aTit.lead.lastname}">
                                                            <b class="ml-2">Proj. Lead:</b>  ${aTit.lead.firstname} ${aTit.lead.lastname}
                                                        </a>
                                                    </object> 
                                                </div>

                                                <div class="col-6">
                                                    <b class="list-item-sub-n ml-2">End:</b> <fmt:formatDate type = "date" value="${aTit.title.endDate}"/><br>
                                                    <object><a href="UserDetailed?name=${aTit.coor.firstname} ${aTit.coor.lastname}">
                                                            <b class="ml-2">Coordinator:</b> ${aTit.coor.firstname}  ${aTit.coor.lastname}
                                                        </a>
                                                    </object>
                                                </div>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>

                            </c:if>
                            <c:if test="${unassignedTitles != null}">
                                <c:if test="${user.position.positionId != 1}">
                                    <br>
                                    <h1 class="list-header-n">Unassigned Titles</h1>
                                </c:if>
                                <c:if test="${user.position.positionId == 1}">
                                    <h1 class="list-header-n">All Titles</h1>
                                </c:if>
                                <div class="list-group list-n">
                                    <c:forEach items="${unassignedTitles}" var="uTit">
                                        <a href="TitleDetailed?name=${uTit.title.name}" class="list-group-item list-group-item-action list-item-n">
                                            <h2 class="list-item-header-n">${uTit.title.name}</h2>
                                            <div class="row">

                                                <div class="col-6">
                                                    <b class="list-item-sub-n ml-2">Status: </b> ${uTit.status.statusDesc}<br>
                                                    <object><a href="UserDetailed?name=${uTit.lead.firstname} ${uTit.lead.lastname}">
                                                            <b class="ml-2">Proj. Lead:</b>  ${uTit.lead.firstname} ${uTit.lead.lastname}
                                                        </a>
                                                    </object> 
                                                </div>
                                                <div class="col-6">
                                                    <b class="list-item-sub-n ml-2">End:</b> <fmt:formatDate type = "date" value="${uTit.title.endDate}"/><br>
                                                    <object><a href="UserDetailed?name=${uTit.coor.firstname} ${uTit.coor.lastname}">
                                                            <b class="ml-2">Coordinator:</b> ${uTit.coor.firstname}  ${uTit.coor.lastname}
                                                        </a>
                                                    </object>
                                                </div>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>
                            </c:if>
                            <c:if test="${unassignedTitles == null && assignedTitles == null}">
                                <h1>No Titles Found</h1>
                            </c:if>
                            </form>
                            <br> 
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 mt-4">
                    <c:if test="${user.position.positionId != 4 && user.position.positionId != 3 && completed == null}">
                        <form method="post" action="Titles">
                            <button type="submit" class="btn btn-block button-red-solid mb-2" name="action" value="compTitle">View Completed</button>
                        </form>
                    </c:if>
                    <c:if test="${completed != null}">
                        <form method="get" action="Titles">
                            <button type="submit" class="btn btn-block button-red-solid mb-2">View Ongoing</button>
                        </form>
                    </c:if>
                    <c:if test="${user.position.positionId != 4 && user.position.positionId != 3}">
                        <form method="post" action="Titles">
                            <button type="submit" class="btn btn-block button-red-solid" name="action" value="addTitle">Add Title</button>
                        </form>
                    </c:if>
                    <h3 class="mt-2"><b>Genres</b></h3>
                    <form>
                        <c:forEach items="${genres}" var="genre">
                            <div class="checkbox mt-1">
                                <input type="checkbox" id="${genre.genreId}" value="${genre.genreId}">
                                <label for="${genre.genreId}">${genre.genreDesc}</label>
                            </div>
                        </c:forEach>
                    </form>
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
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
</html>
