<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sync" uri="/WEB-INF/tlds/synctags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" 
              integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Nunito+Sans" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.4.0/css/bootstrap4-toggle.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.1.0/jspdf.plugin.autotable.js"></script>

        <title>Reports</title>
        <script>

            $(document).ready(function () {
                var doc = new jsPDF();


                $('#usersreportbutton').click(function () {

                    doc.autoTable({
                        html: '#usersreporttable',
                        headStyles: {

                            fillColor: '#E50914'
                        }
                    });
                    doc.save('userreport.pdf');
                });

                $('#titlesreportbutton').click(function () {

                    doc.autoTable({
                        html: '#titlesreporttable',
                        headStyles: {

                            fillColor: '#E50914'
                        }
                    });
                    doc.save('titlesreport.pdf');
                });

            });
        </script>    
    </head>
    <body class="background-plain">
        <sync:navbar1></sync:navbar1>
            <div class="searchBarHeader"></div>
            <div class="container custom-container bg-white" >
                <div class="page-header border-bottom">REPORTS</div>
                <div class="row">
                    <div class="col-3">
                        <div class="dropdown mt-2">
                            <button class="btn btn-secondary dropdown-toggle btn-block" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:choose>
                                <c:when test="${name == 'activeUsers'}">
                                    Active Users
                                </c:when>
                                <c:when test="${name eq 'userPosition'}">
                                    Find User By Position
                                </c:when>
                                <c:when test="${name == 'specificUsers'}">
                                    Find Users
                                </c:when>
                                <c:when test="${name == 'activeTitles'}">
                                    Active Titles
                                </c:when>
                                <c:when test="${name == 'compTitles'}">
                                    Completed Titles
                                </c:when>
                                <c:when test="${name == 'specificTitles'}">
                                    Find Titles
                                </c:when>
                                <c:otherwise>
                                    Select A Report
                                </c:otherwise>

                            </c:choose>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a name="activeUsers" class="dropdown-item" href="Reports?name=activeUsers">Active Users</a>
                            <a name="usersPosition" class="dropdown-item" href="Reports?name=userPosition">Find User By Position</a>
                            <a name="specificUsers" class="dropdown-item" href="Reports?name=specificUsers">Find Users</a>

                            <a name="activeTitles" class="dropdown-item" href="Reports?name=activeTitles">Active Titles</a>
                            <a name="compTitles" class="dropdown-item" href="Reports?name=compTitles">Completed Titles</a>
                            <a name="specificTitles" class="dropdown-item" href="Reports?name=specificTitles">Find Titles</a>
                        </div>
                    </div>

                    <div>
                        <c:choose>
                            <c:when test="${name == 'activeUsers'}">
                                <form method="post" action="Reports">
                                    <button type="submit" name="reportInput" value="activeUsers" class="btn btn-block button-red-solid">Generate</button>
                                </form>
                            </c:when>
                            <c:when test="${name eq 'userPosition'}">
                                <sync:userpositioninput></sync:userpositioninput>
                            </c:when>
                            <c:when test="${name == 'specificUsers'}">
                                <form method="post" action="Reports">
                                    <select class="selectpicker form-control" name="users" id="users" multiple data-live-search="true" title="Select Users" id="y" data-header="Select Users" required>
                                        <c:forEach items="${allUsers}" var="user">
                                            <option value="${user.userId}">${user.firstname} ${user.lastname}</option>
                                        </c:forEach>
                                    </select>
                                    <button type="submit" name="reportInput" value="specificUsers" class="btn btn-block button-red-solid mb-2">Generate</button>
                                </form>
                            </c:when>

                            <c:when test="${name == 'activeTitles'}">
                                <form method="post" action="Reports">
                                    <button type="submit" name="reportInput" value="activeTitles" class="btn btn-block button-red-solid mb-2">Generate</button>
                                </form>
                            </c:when>
                            <c:when test="${name == 'compTitles'}">
                                <form method="post" action="Reports">
                                    <button type="submit" name="reportInput" value="compTitles" class="btn btn-block button-red-solid mb-2">Generate</button>
                                </form>
                            </c:when>
                            <c:when test="${name == 'specificTitles'}">
                                <form method="post" action="Reports">
                                    <select class="selectpicker form-control" name="titles" id="titles" multiple data-live-search="true" title="Select Genres" id="y" data-header="Select Genres" required>
                                        <c:forEach items="${allTitles}" var="title">
                                            <option value="${title.titleId}">${title.name}</option>
                                        </c:forEach>
                                    </select>
                                    <button type="submit" name="reportInput" value="specificUsers" class="btn btn-block button-red-solid mb-2">Generate</button>
                                </form>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
                <div class="col-1">

                </div>
                <div class="col-8">
                    <div class="row">
                        <c:choose>
                            <c:when test="${reportType == 'activeUsers' || reportType == 'userPosition' || reportType == 'specificUsers'}">
                                <sync:userreport></sync:userreport>
                            </c:when>

                            <c:when test="${reportType == 'activeTitles' || reportType == 'compTitles' || reportType == 'specificTitles'}">
                                <sync:viewtitlereport></sync:viewtitlereport>
                            </c:when>
                            <c:otherwise>
                                <h2 class="text-right">Select A Report To Continue</h2>
                            </c:otherwise>
                        </c:choose>
                    </div>
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
    <script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.4.0/js/bootstrap4-toggle.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.1.0/jspdf.plugin.autotable.js"></script>
    <script src="upload-image.js"></script>
</html>
