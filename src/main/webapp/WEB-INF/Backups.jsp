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
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.4.0/css/bootstrap4-toggle.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <title>Backups</title>
    </head>
    <body class="background-plain">
        <sync:navbar1></sync:navbar1>
            <div class="searchBarHeader"></div>
            <div class="container bg-white" style="height: 84vh;">
                <div class="page-header border-bottom text-center">BACKUPS</div>
                <form method="get" action="Backups">
                    <input type="submit" name="backupDatabase" class="btn btn-danger mt-2 mb-2" value="Backup database">
                </form>
                <table class="table">
                    <thead>
                        <tr class="table-secondary">
                            <th scope="col">File Name</th>
                            <th scope="col">Creation Date</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${backups}" var="backup">
                        <tr>
                            <td><b>${backup.backupName}</b></td>
                            <td><fmt:formatDate type = "both" value="${backup.backupDate}" /></td>
                            <td class="flex-row-reverse">
                                <form class="float-right" method="post" action="Backups">
                                    <input type="hidden" name="backupId" value="${backup.backupId}">
                                    <input type="submit" class="btn btn-secondary" value="Restore database" onclick="warnUser();">
                                    <script type = "text/javascript">
                                        function warnUser() {
                                            var confrimAction = confirm("YOU ARE ABOUT TO RESTORE THE DATABASE! DO YOU WISH TO CONTINUE?");
                                            if (confrimAction === false) {
                                                event.preventDefault();
                                                return false;
                                            } else {
                                                alert("Restoring database");
                                                return true;
                                            }
                                        }
                                    </script>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
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
</html>
