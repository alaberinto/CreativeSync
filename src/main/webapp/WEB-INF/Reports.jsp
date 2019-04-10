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
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Reports</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.1.0/jspdf.plugin.autotable.js"></script>

        <script>

            $(document).ready(function () {
                var doc = new jsPDF();


                $('#freelancersreportbutton').click(function () {

                    doc.autoTable({
                        html: '#freelancetable',
                        headStyles: {

                            fillColor: '#E50914'
                        }
                    });
                    doc.save('freelancer.pdf');
                });

                $('#titlesreportbutton').click(function () {

                    doc.autoTable({
                        html: '#titlesreporttable',
                        headStyles: {

                            fillColor: '#E50914'
                        }
                    });
                    doc.save('titles.pdf');
                });

            });
        </script>    </head>
    <body class="background-plain">
        <sync:navbar1></sync:navbar1>
            <div class="searchBarHeader"></div>
            <div class="container fullContainer bg-white" >
                <div class="row">
                    <div class="col-3">
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Select Report
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
                    </div>
                    <div class="col-9">
                    <c:choose>
                        <c:when test="${name == 'activeUsers'}">
                            <form method="post" action="Reports">
                                <button type="submit" name="reportInput" value="activeUsers" class="btn btn-block button-red-solid mb-2">
                            </form>
                        </c:when>
                        <c:when test="${name == 'userPosition'}">
                            <sync:userpositioninput></sync:userpositioninput>
                        </c:when>
                        <c:when test="${name == 'specificUsers'}">
                            <form method="post" action="Reports">
                                <label for="users"><h3 class="mb-0">Users</h3></label>
                                <select class="selectpicker form-control" name="users" id="users" multiple data-live-search="true" title="Select Users" id="y" data-header="Select Users" required>
                                    <c:forEach items="${allUsers}" var="user">
                                        <option value="${user.userId}">${user.firstname + ' ' + user.lastname}</option>
                                    </c:forEach>
                                </select>
                                <button type="submit" name="reportInput" value="specificUsers" class="btn btn-block button-red-solid mb-2">
                            </form>
                        </c:when>

                        <c:when test="${name == 'activeTitles'}">
                            <form method="post" action="Reports">
                                <button type="submit" name="reportInput" value="activeTitles" class="btn btn-block button-red-solid mb-2">
                            </form>
                        </c:when>
                        <c:when test="${name == 'compTitles'}">
                            <form method="post" action="Reports">
                                <button type="submit" name="reportInput" value="compTitles" class="btn btn-block button-red-solid mb-2">
                            </form>
                        </c:when>
                        <c:when test="${name == 'specificTitles'}">
                            <form method="post" action="Reports">
                                <label for="titles"><h3 class="mb-0">Titles</h3></label>
                                <select class="selectpicker form-control" name="titles" id="titles" multiple data-live-search="true" title="Select Genres" id="y" data-header="Select Genres" required>
                                    <c:forEach items="${allTitles}" var="title">
                                        <option value="${title.titleId}">${title.name}</option>
                                    </c:forEach>
                                </select>
                                <button type="submit" name="reportInput" value="specificUsers" class="btn btn-block button-red-solid mb-2">
                            </form>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div>
                <c:choose>
                    <c:when test="${name == 'activeUsers'}">
                        <sync:userreport></sync:userreport>
                    </c:when>
                    <c:when test="${name == 'userPosition'}">
                        <sync:userpositioninput></sync:userpositioninput>
                    </c:when>
                    <c:when test="${name == 'specificUsers'}">
                        <sync:viewuserinput></sync:viewuserinput>
                    </c:when>
                    <c:when test="${name == 'activeTitles'}">
                        <sync:activetitlesreport></sync:activetitlesreport>
                    </c:when>
                    <c:when test="${name == 'compTitles'}">
                        <sync:completedtitlesreport></sync:completedtitlesreport>
                    </c:when>
                    <c:when test="${name == 'specificTitles'}">
                        <sync:titleinput></sync:titleinput>
                    </c:when>
                </c:choose>
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
