<%-- 
    Document   : ArtworkDetailedSelect
    Created on : Apr 1, 2019, 12:23:00 PM
    Author     : Matthew
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <link rel="stylesheet" href="css/navbar.css" type="text/css">
        <link rel="stylesheet" href="css/Artwork.css" type="text/css">
        <link rel="stylesheet" href="css/Userpage.css" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <title>Artwork Detail</title>
    </head>
    <body class="background-plain">
        <sync:navbar1></sync:navbar1>
            <div class="container background-white pb-3">
                <h1 align="center">Feedback</h1>

                <div class="hdev">
                    <div align="center">
                        <h3>Select a Title to review their artwork</h3>    

                        <br>
                        <table border="1" cellspacing="5">                   
                            <tbody>
                                <tr>
                                    <td>
                                    <c:forEach var="titles" items="${titles}">
                                        <form action="TitleSelect" method="post">
                                            <div style="width:300px; display:table">
                                                <input type="submit" name="title_select" value="${titles.title.name}" style="display:table-cell; width:100%">                 
                                                <input type="hidden" name="title_select_id" value="${titles.title.titleId}">
                                            </div>
                                        </form>
                                    </c:forEach>
                                    <a href="ArtworkDetailed">Click here to provide a freelancer feedback</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <script>
                    ;
                    (function ($) {

                        /**
                         * Store scroll position for and set it after reload
                         *
                         * @return {boolean} [loacalStorage is available]
                         */
                        $.fn.scrollPosReload = function () {
                            if (localStorage) {
                                var posReader = localStorage["posStorage"];
                                if (posReader) {
                                    $(window).scrollTop(posReader);
                                    localStorage.removeItem("posStorage");
                                }
                                $(this).click(function (e) {
                                    localStorage["posStorage"] = $(window).scrollTop();
                                });

                                return true;
                            }

                            return false;
                        }

                        /* ================================================== */

                        $(document).ready(function () {
                            $('body').scrollPosReload();
                        });

                    }(jQuery));
                </script>            

            </div>
        </div>
    </body>
</html>
