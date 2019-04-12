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
        <style>
            .pageA {
                display: none;
            }
            .pageA-active {
                display: block;
            }
            .pageU {
                display: none;
            }
            .pageU-active {
                display: block;
            }
            .paginationA {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                display: inline-block;
            }
            .paginationA li{
                display: inline;
            }
            .paginationA a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
            }
            .paginationA a.active {
                background-color: #e50914;
                color: white;
                border: 1px solid #4CAF50;
            }
            .paginationA a:hover:not(.active) {background-color: #ddd;}
            .paginationU {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                display: inline-block;
            }
            .paginationU li{
                display: inline;
            }
            .paginationU a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
            }

            .paginationU a.active {
                background-color: #e50914;
                color: white;
                border: 1px solid #4CAF50;
            }
            .paginationU a:hover:not(.active) {background-color: #ddd;}
        </style>
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
                    <!--PART ONE-->
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
                            <!--PART TWO-->


                            <!--The next part is to find the first, last and total pages of data, it is used primarly for later for displaying the buttons-->
                            <c:set var='PAGEBLOCK' value="4" />
                            <fmt:formatNumber var="totalPageA"  value="${((assignedTitles.size() - 1) / PAGEBLOCK)}" maxFractionDigits="1" pattern="######"/>
                            <c:set var="totalPageA" value="${totalPageA -(totalPageA % 1) }" />
                            <c:set var="totalPageA" value="${totalPageA + 1 }" />

                            <fmt:formatNumber var="firstPageA"  value="${((currentRequest.pageNum - 1) / PAGEBLOCK)}" maxFractionDigits="1" />
                            <c:set var="firstPageA" value="${firstPageA - (firstPageA % 1) }" /> <!--  floor -->
                            <c:set var="firstPageA" value="${firstPageA * PAGEBLOCK + 1 }" /> 
                            <c:if test="${firstPageA le 0}">
                                <c:set var="firstPageA" value="1" />
                            </c:if>
                            <c:set var="lastPageA" value="${totalPageA}" />
                            <script> var lastPageA =${lastPageA};</script>
                            <c:set var="startA" value="${1}" /> <!--  counter for the first item of a page -->
                            <c:set var="pageA" value="${1}" /> <!--  counter for the current page -->
                            <c:set var="endA" value="${startA + PAGEBLOCK }"/> <!--  counter for the last item of a page -->

                            <c:if test="${assignedTitles != null}">
                                <h1 class="list-header-n">Assigned Titles</h1>
                                <div class="list-group list-n pageA-active" id="pageA${pageA}" name="titlesList">
                                    <c:forEach items="${assignedTitles}" var="aTit">
                                        <c:if test="${startA eq endA}"> <!--When we create the last row of each page then we create a new div for the new page-->
                                            <c:set var="endA" value="${startA + PAGEBLOCK }"/>
                                            <c:set var="pageA" value="${pageA + 1 }"/>
                                        </div><div class="pageA" id="pageA${pageA}">

                                        </c:if>
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
                                        <c:set var="startA" value="${startA +1}" /> <!--  item is created so we increment by 1-->
                                    </c:forEach>
                                </div>
                            </c:if>
                            <!--<div class="list-group list-n">-->
                            <!--Here is the code for displaying the buttons, each button on click calls a javascript function to display the appropriate page-->
                            <c:if test="${lastPageA gt 1}"> <!--If the results are in less than a page, do not show the buttons to change page-->
                                <div>
                                    <ul class="paginationA">

                                        <!--<form method="post" action="Titles">-->
                                        <li><a href="javascript:goPrevA();">Previous</a></li> <!--The Previous Button-->
                                        <!--Here is the loop to display buttons for every page-->

                                        <c:set var="PA" value="${1}" />
                                        <c:forEach begin="${firstPageA}" end="${lastPageA}" step="1" varStatus="status">
                                            <c:choose>
                                                <c:when test="${currentRequest.pageNum eq status.index}">
                                                    <li class="PA${PA}" ><a href="javascript:goPageA(${status.index};">${status.index}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li><a class="PA${PA}" href="javascript:goPageA(${status.index});">${status.index}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:set var="PA" value="${PA+1}" />

                                        </c:forEach>
                                        <li><a href="javascript:goNextA();">Next</a></li><!--The Next Button-->
                                    </ul></div>
                                </c:if>

                            <!--PART THREE-->
                            <!--The next part is to find the first, last and total pages of data, it is used primarly for later for displaying the buttons-->
                            <fmt:formatNumber var="totalPageU"  value="${((unassignedTitles.size() - 1) / PAGEBLOCK)}" maxFractionDigits="1" pattern="######"/>
                            <c:set var="totalPageU" value="${totalPageU -(totalPageU % 1) }" />
                            <c:set var="totalPageU" value="${totalPageU + 1 }" />

                            <fmt:formatNumber var="firstPageU"  value="${((currentRequest.pageNum - 1) / PAGEBLOCK)}" maxFractionDigits="1" />
                            <c:set var="firstPageU" value="${firstPageU - (firstPageU % 1) }" /> <!--  floor -->
                            <c:set var="firstPageU" value="${firstPageU * PAGEBLOCK + 1 }" /> 
                            <c:if test="${firstPageU le 0}">
                                <c:set var="firstPageU" value="1" />
                            </c:if>
                            <%--<c:if test="${lastPageU gt totalPageU}">--%>
                            <c:set var="lastPageU" value="${totalPageU}" />
                            <script> var lastPageU =${lastPageU};</script>
                            <%--</c:if>--%>
                            <c:set var="startU" value="${1}" /> <!--  counter for the first item of a page -->
                            <c:set var="pageU" value="${1}" /> <!--  counter for the current page -->
                            <c:set var="endU" value="${startU + PAGEBLOCK }"/> <!--  counter for the last item of a page -->
                            <c:if test="${unassignedTitles != null}">
                                <c:if test="${user.position.positionId != 1}">
                                    <br>
                                    <h1 class="list-header-n">Unassigned Titles</h1>
                                </c:if>
                                <c:if test="${user.position.positionId == 1}">
                                    <h1 class="list-header-n">All Titles</h1>
                                </c:if>
                                <div class="list-group list-n pageU-active" id="pageU${pageU}" name="titlesList">



                                    <c:forEach items="${unassignedTitles}" var="uTit">
                                        <c:if test="${startU eq endU}"> <!--When we create the last row of each page then we create a new div for the new page-->
                                            <c:set var="endU" value="${startU + PAGEBLOCK }"/>
                                            <c:set var="pageU" value="${pageU + 1 }"/>
                                        </div><div class="pageU" id="pageU${pageU}">
                                        </c:if>
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
                                        <c:set var="startU" value="${startU +1}" /> <!--  item is created so we increment by 1-->
                                    </c:forEach>
                                </div>
                            </c:if>
                            <!--<div class="list-group list-n">-->
                            <!--Here is the code for displaying the buttons, each button on click calls a javascript function to display the appropriate page-->
                            <c:if test="${lastPageU gt 1}"> <!--If the results are in less than a page, do not show the buttons to change page-->
                                <div>
                                    <ul class="paginationU">

                                        <!--<form method="post" action="Titles">-->
                                        <li><a href="javascript:goPrevU();">Previous</a></li> <!--The Previous Button-->
                                        <!--Here is the loop to display buttons for every page-->

                                        <c:set var="PU" value="${1}" />
                                        <c:forEach begin="${firstPageU}" end="${lastPageU}" step="1" varStatus="status">
                                            <c:choose>
                                                <c:when test="${currentRequest.pageNum eq status.index}">
                                                    <li class="PU${PU}" ><a href="javascript:goPageU(${status.index};">${status.index}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li><a class="PU${PU}" href="javascript:goPageU(${status.index});">${status.index}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:set var="PU" value="${PU+1}" />

                                        </c:forEach>
                                        <li><a href="javascript:goNextU();">Next</a></li><!--The Next Button-->
                                    </ul></div>
                                </c:if>

                            <!--END-->
                            <c:if test="${unassignedTitles == null && assignedTitles == null}">
                                <h1>No Titles Found</h1>
                            </c:if>
                            <!--</form>-->
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
        <!-- This is the javascript code for changing pages-->
        <script language="javascript">
            var currentPageA = 1;
            //            When clicking on Next Button, if there is a Next page then it displays the Next page
            function goNextA() {
                if (currentPageA < lastPageA) {
                    currentPageA++;
                    goPageA(currentPageA);
                }
            }
//                        When clicking on Previous Button, if there is a previous page then it displays the previous page
            function goPrevA() {
                if (currentPageA > 1) {
                    currentPageA--;
                    goPageA(currentPageA);
                }
            }
            //            When clicking on a page button, it displays the appropriate page
            function goPageA(pageA) {
                currentPageA = pageA;
                $(".paginationA .active").removeClass("active");
                $(".PA" + pageA).addClass("active");
                $(".pageA-active").addClass("pageA");
                $(".pageA-active").removeClass("pageA-active");
                $("#pageA" + pageA).addClass("pageA-active");
            }
            var currentPageU = 1;
            //            When clicking on Next Button, if there is a Next page then it displays the Next page
            function goNextU() {
                if (currentPageU < lastPageU) {
                    currentPageU++;
                    goPageU(currentPageU);
                }
            }
            //            When clicking on Previous Button, if there is a previous page then it displays the previous page
            function goPrevU() {
                if (currentPageU > 1) {
                    currentPageU--;
                    goPageU(currentPageU);
                }
            }
            //            When clicking on a page button, it displays the appropriate page
            function goPageU(pageU) {
                currentPageU = pageU;
                $(".paginationU .active").removeClass("active");
                $(".PU" + pageU).addClass("active");
                $(".pageU-active").addClass("pageU");
                $(".pageU-active").removeClass("pageU-active");
                $("#pageU" + pageU).addClass("pageU-active");
            }
        </script>
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
    <script>goPageA(1);</script>
    <script>goPageU(1);</script>
</html>
