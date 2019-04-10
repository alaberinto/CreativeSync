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
        <link rel="stylesheet" type="text/css" media="screen" href="http://cdnjs.cloudflare.com/ajax/libs/fancybox/1.3.4/jquery.fancybox-1.3.4.css" />
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/fancybox/1.3.4/jquery.fancybox-1.3.4.pack.min.js"></script>
        <title>Artwork Detail</title>
    </head>
    <body class="background-plain">
        <sync:navbar1></sync:navbar1>
            <div class="container background-white pb-3">
                <h1 align="center">Feedback</h1>

                <div class="hdev">
                    <div align="center">
                        <h3>
                        <c:if test="${rounds_filled == 1 || rounds_filled == 0 || titles_filled == 1}">
                            ${title.name} - 
                        </c:if>
                        ${username_fl}
                    </h3>    
                </div>
                <c:if test="${rounds_filled == 1}">
                    <h3>Round #</h3>
                </c:if>
                <hr>

                <br>                

                <c:if test="${rounds_filled == 1}">
                    <c:forEach var="rounds" items="${rounds}">
                        <h3>${rounds.getRound()}</h3>
                        <hr>
                        <div style="width:100%; background-color: #f4efed;">
                            <table border="1" cellspacing="5" width="100%">
                                <tbody>       
                                    <tr>
                                        <th colspan="2">        
                                            <div style="padding: 10px;">
                                                <div align="center" class="img_zoom">    
                                                    <c:forEach var="round_art" items="${round_art}">                                             
<!--                                                    <img class="fancybox" title="Picture 1" src="https://image.shutterstock.com/z/stock-photo-oil-painting-on-canvas-street-view-of-london-artwork-big-ben-couple-and-red-umbrella-bus-and-667859491.jpg" style="width: 250px; height:205px;" alt="pic1"/>
                                                    <img class="fancybox" title="Picture 2" src="https://image.shutterstock.com/image-photo/artists-living-room-minimal-style-450w-689978848.jpg" style="width: 250px; height:205px;" alt="pic2"/>-->
                                                    <img class="fancybox" title="French Bulldog" src="https://image.shutterstock.com/image-vector/french-bulldog-pop-art-colors-450w-1151676383.jpg" style="width: 250px; height:205px;" alt="pic3"/>
                                                    </c:forEach>
                                                </div>                                                                               
                                            </div>
                                        </th>                                    
                                    </tr>                             

                                    <tr>
                                        <td style="width: 70%">
                                            <div class="form-group">                                                                                 
                                                <textarea rows="3" cols="50" class="form-control" form="status_ad" rows="5" id="comment" name="comment" placeholder="Type your feedback here!" required style></textarea>                                         
                                            </div>
                                        </td>
                                        <td >
                                            <c:if test="${approve_deny_val == 0}">
                                                <div align="center">                         
                                                    <form action="ArtworkDetailed" method="post" id="status_ad">
                                                        <input type="submit" onclick="confirm_approve()" class="button_approve" name="approve" value="Approve">
                                                        <input type="submit" onclick="confirm_deny()" class="button_deny" name="deny" value="Deny">       
                                                    </form>
                                                </div>
                                            </c:if>
                                            <c:if test="${approve_deny_val == 1}">
                                                <div align="center">                         
                                                    <h1 style="color: #4CAF50">APPROVED</h1>
                                                </div>
                                            </c:if>
                                            <c:if test="${approve_deny_val == 2}">
                                                <div align="center">                         
                                                    <h1 style="color: #f44336">DENIED</h1>
                                                </div>
                                            </c:if>
                                        </td>  
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <br>
                    </c:forEach>  
                </c:if>

                <c:if test="${rounds_filled == 0}">
                    <div align="center">
                        <h1>No artwork exists for this title!</h1>
                    </div>
                </c:if>

                <c:if test="${titles_filled == 0}">
                    <div align="center">
                        <h1>No titles exist for this user!</h1>
                    </div>
                </c:if>

                ${comment}

                <br>
                <br>
                <br>
                <br>
                <br>
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



                <script>
                    $(function ($) {
                        var addToAll = false;
                        var gallery = true;
                        var titlePosition = 'inside';
                        $(addToAll ? 'img' : 'img.fancybox').each(function () {
                            var $this = $(this);
                            var title = $this.attr('title');
                            var src = $this.attr('data-big') || $this.attr('src');
                            var a = $('<a href="#" class="fancybox"></a>').attr('href', src).attr('title', title);
                            $this.wrap(a);
                        });
                        if (gallery)
                            $('a.fancybox').attr('rel', 'fancyboxgallery');
                        $('a.fancybox').fancybox({
                            titlePosition: titlePosition
                        });
                    });
                    $.noConflict();
                </script>


            </div>
        </div>
    </body>
</html>
