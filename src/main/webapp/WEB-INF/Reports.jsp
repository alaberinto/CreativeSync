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
<!--        <link rel="stylesheet" href="css/style.css" type="text/css">-->
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

            <div id="editor"></div>
            <h1>Reports</h1>

            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Select Report
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="Reports?name=activeUsers">Active Users Report</a>
                    <a class="dropdown-item" href="Reports?name=userPosition">Users By Position Report</a>
                    <a class="dropdown-item" href="Reports?name=specificUsers">Specific User Report</a>
                    <a class="dropdown-item" href="Reports?name=activeTitles">Active Titles Report</a>
                    <a class="dropdown-item" href="Reports?name=compTitles">Completed Titles Report</a>
                    <a class="dropdown-item" href="Reports?name=specificTitles">Specific Titles Report</a>
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
        <c:if test='${sessionScope.filteredusers !=null && sessionScope.filteredusers.size() > 0 }'>

            <br>
            <input type="button" id="freelancersreportbutton" value="Download Report " name="download" />
        </c:if>


        <c:if test='${ sessionScope.titleReportList !=null && sessionScope.titleReportList.size() > 0 }'>

            <input type="button" id="titlesreportbutton" value="Download Report " name="download" />
        </c:if>

    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
</html>
