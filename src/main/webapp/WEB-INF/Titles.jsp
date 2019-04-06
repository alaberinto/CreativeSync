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
                    <c:if test="${assignedTitles != null}">
                        
                        <h1 class=" text-center mb-0">Assigned</h1>
                        <div class="list-group list-n" name="titlesList">
                            <c:forEach items="${assignedTitles}" var="aTit">
                                <a href="TitleDetailed?name=${aTit.title.name}" class="list-group-item list-group-item-action">
                                    <h2 class="list-item-header-n">${aTit.title.name}</h2>
                                    <div class="row">
                                        
                                        <div class="col-6">
                                            
                                            <b class="list-item-sub-n ml-2">Status: </b> ${aTit.status.statusDesc}
                                            <a href="UserDetailed?name=${aTit.lead.firstname} ${aTit.lead.lastname}">
                                                <b class="ml-2">Proj. Lead:</b>  ${aTit.lead.firstname} ${aTit.lead.lastname}
                                            </a>  
                                        </div>
                                            
                                        <div class="col-6">
                                            
                                            <b class="list-item-sub-n ml-2">End:</b> <fmt:formatDate type = "date" value="${aTit.title.endDate}"/>
                                            <a href="UserDetailed?name=${aTit.coor.firstname} ${aTit.coor.lastname}">
                                                <b class="ml-2">Coordinator:</b> ${aTit.coor.firstname}  ${aTit.coor.lastname}
                                            </a>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>

                    </c:if>
                    <c:if test="${unassignedTitles != null}">
                        <h1 class=" text-center mb-0">Unassigned</h1>
                        <div class="list-group list-n">
                            <c:forEach items="${unassignedTitles}" var="uTit">
                                <a href="TitleDetailed?name=${uTit.title.name}" class="list-group-item list-group-item-action">
                                    <h2 class="list-item-header-n">${uTit.title.name}</h2>
                                    <div class="row">
                                        <div class="col-6">
                                            <b class="list-item-sub-n ml-2">Status: </b> ${uTit.status.statusDesc}
                                            <form method="post" action="Titles">
                                                <input type="hidden" name="leadId" value="tit.lead.userId">
                                                <button name="action" value="clickLead" type="submit">
                                                    <b class="ml-2">Proj. Lead:</b> ${uTit.lead.firstname}  ${uTit.lead.lastname}
                                                </button>   
                                            </form>
                                        </div>
                                        <div class="col-6">
                                            <b class="ml-2">End:</b> <fmt:formatDate type = "date" 
                                                            value="${uTit.title.endDate}"/>
                                            <br>
                                            <form method="post" action="Titles">
                                                <input type="hidden" name="coorId" value="tit.coor.userId">
                                                <button name="action" value="clickCoor" type="submit">
                                                    <b class="ml-2">Coordinator:</b> ${uTit.coor.firstname}  ${uTit.coor.lastname}
                                                </button>   
                                            </form>
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
                </div>
                <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 mt-4">
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
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
</html>
