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
        <style>
            .page {
                display: none;
            }
            .page-active {
                display: block;
            }
            .pagination {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                display: inline-block;
            }
            .pagination li{
                display: inline;
            }
            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
            }

            .pagination a.active {
                background-color: #e50914;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {background-color: #ddd;}
        </style>    
    </head>
    <body class="background-plain">
        <sync:navbar1>
        </sync:navbar1>
        <div class="searchBarHeader">
            <div class="container p-0">
                <div class="row">
                    <div class="col-12 p-0">
                        <form method="get" action="Users">
                            <div class="form-group">
                                <input class="form-control searchBar" type="text" name="search" placeholder="Search User By Name Or Position">
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
                    <!--The next part is to find the first, last and total pages of data, it is used primarly for later for displaying the buttons-->
                    <c:set var='PAGEBLOCK' value="6" />
                    <fmt:formatNumber var="totalPage"  value="${((users.size() - 1) / PAGEBLOCK)}" maxFractionDigits="1" pattern="######"/>
                    <c:set var="totalPage" value="${totalPage -(totalPage % 1) }" />
                    <c:set var="totalPage" value="${totalPage + 1 }" />

                    <fmt:formatNumber var="firstPage"  value="${((currentRequest.pageNum - 1) / PAGEBLOCK)}" maxFractionDigits="1" />
                    <c:set var="firstPage" value="${firstPage - (firstPage % 1) }" /> <!--  floor -->
                    <c:set var="firstPage" value="${firstPage * PAGEBLOCK + 1 }" /> 
                    <c:if test="${firstPage le 0}">
                        <c:set var="firstPage" value="1" />
                    </c:if>
                    <c:set var="lastPage" value="${firstPage - 1 + PAGEBLOCK }" />
                    <%--<c:if test="${lastPage gt totalPage}">--%>
                        <c:set var="lastPage" value="${totalPage }" />
                        <script> var lastPage =${lastPage};</script>
                    <%--</c:if>--%>
                    <div class="list-group list-n">
                        <!--
                        var start = 1 for the index of the first item
                        var page = 1 keeps track of what page we are now
                        var end for the last item of the page
                        For a loop in the arrayList we cannot use index, so we start with variable start at 1
                        after displaying an item itss incremented by 1,
                        when start == end then we create another page and increment the value of end by 6
                        so that it keeps track of the next time we must create the next page.
                        page is giving a unique id to each page so that we can use it to display only the chosen one
                        -->
                        <c:set var="start" value="${1}" /> <!--  counter for the first item of a page -->
                        <c:set var="page" value="${1}" /> <!--  counter for the current page -->
                        <c:set var="end" value="${start + 6 }"/> <!--  counter for the last item of a page -->
                        
                        <h1 class="list-header-n">Users</h1>
                        <div class="page-active" id="page${page}"> <!--Each page has a unique ID -->
                            <c:forEach items="${users}" var="u">
                                <c:if test="${start eq end}"> <!--When we create the last row of each page then we create a new div for the new page-->
                                    <c:set var="end" value="${start + 6 }"/>
                                    <c:set var="page" value="${page + 1 }"/>
                                </div><div class="page" id="page${page}">

                                </c:if>
                                <!--  start of page -->
                                
                                <a href="UserDetailed?name=${u.user.firstname} ${u.user.lastname}" class="list-group-item list-group-item-action">
                                    <div class="row">
                                        <div class="col-6">
                                            <h2 class="list-item-header-n">${u.user.firstname} ${u.user.lastname}</h2>
                                        </div>
                                        <div class="col-6">
                                            <h2 class="list-item-header-n">${u.user.position.positionDesc}</h2>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-6">
                                            <b class="list-item-sub-n ml-2">Title</b><br>
                                            <c:forEach items="${u.titles}" var="tit">
                                                <object>
                                                    <a href="TitleDetailed?name=${tit.title.name}">
                                                        ${tit.title.name}<br>
                                                    </a>
                                                </object>
                                            </c:forEach>
                                        </div>
                                        <div class="col-6">
                                            <b class="list-item-sub-n ml-2">Status</b><br>
                                            <c:forEach items="${u.titles}" var="tit">
                                                ${tit.status.statusDesc}<br>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </a>
                                <c:set var="start" value="${start +1}" /> <!--  item is created so we increment by 1-->
                            </c:forEach>
                        </div>
                        <!--<div class="list-group list-n">-->
                        <!--Here is the code for displaying the buttons, each button on click calls a javascript function to display the appropriate page-->
                        <c:if test="${lastPage gt 1}"> <!--If the results are in less than a page, do not show the buttons to change page-->
                            <ul class="pagination">
                                <!--<form method="post" action="Titles">-->
                                <li><a href="javascript:goPrev();">Previous</a></li> <!--The Previous Button-->
                                <!--Here is the loop to display buttons for every page-->

                                <c:set var="P" value="${1}" />
                                <c:forEach begin="${firstPage}" end="${lastPage}" step="1" varStatus="status">
                                    <c:choose>
                                        <c:when test="${currentRequest.pageNum eq status.index}">
                                            <li class="P${P}" ><a href="javascript:goPage(${status.index};">${status.index}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li><a class="P${P}" href="javascript:goPage(${status.index});">${status.index}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:set var="P" value="${P+1}" />
                                    </c:forEach>
                                <li><a href="javascript:goNext();">Next</a></li><!--The Next Button-->
                            </ul>
                        </c:if>
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 mt-4">
                    <c:if test="${user.position.positionId != 4 && user.position.positionId != 3}">
                        <form method="post" action="Users">
                            <button type="submit" class="btn btn-block button-red-solid" name="action" value="addUser">Add User</button>
                        </form>
                    </c:if>

                    <h3 class="mt-2"> <b>Job Position</b></h3>
                    <form>
                        <c:forEach items="${positions}" var="pos">
                            <div class="checkbox mt-1">
                                <input type="checkbox" id="${pos.positionId}" value="${pos.positionId}">
                                <label for="${pos.positionId}">${pos.positionDesc}</label>
                            </div>
                        </c:forEach>
                    </form>
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
        <!-- This is the javascript code for changing pages-->
        <script language="javascript">
            var currentPage = 1;
            //            When clicking on Next Button, if there is a Next page then it displays the Next page
            function goNext() {
                if (currentPage < lastPage) {
                    currentPage++;
                    goPage(currentPage);
                }
            }
            //            When clicking on Previous Button, if there is a previous page then it displays the previous page
            function goPrev() {
                if (currentPage > 1) {
                    currentPage--;
                    goPage(currentPage);
                }
            }
            //            When clicking on a page button, it displays the appropriate page
            function goPage(page) {
                currentPage = page;
                $(".pagination .active").removeClass("active");
                $(".P" + page).addClass("active");
                $(".page-active").addClass("page");
                $(".page-active").removeClass("page-active");
                $("#page" + page).addClass("page-active");
            }
        </script>
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
    <script>goPage(1);</script>
    <script type="text/javascript">
        $(".alert-dismissible").fadeTo(2000, 500).slideUp(500, function () {
            $(".alert-dismissible").slideUp(500);
        });
    </script>
</html>