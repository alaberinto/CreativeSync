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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/fancybox/1.3.4/jquery.fancybox-1.3.4.pack.min.js"></script>
        <title>Artwork Detailed</title>
    </head>
    <body class="background-plain">
        <sync:navbar1></sync:navbar1>
            <div class="searchBarHeader"></div>

            <div class="container background-white pb-3">              

                <div class="hdev">
                    <div class="row">
                        <div class="col-12">
                            <h1 class="text-center mt-5 mb-4 text-uppercase font-weight-bold">                    
                            <c:if test="${roundsFilled == 1 || roundsFilled == 0}">
                                ${title.name}
                            </c:if>                      
                        </h1>    
                    </div>
                </div>

                <!--add/upload artwork-->
                <table border="0" width="100%">
                    <thead>
                        <tr>
                            <th>
                                <c:if test="${roundsFilled == 1}">
                                    <h2>Rounds</h2>
                                </c:if>
                            </th>
                            <th>
                                <div align ="right">
                                    <div class="col-4">
                                        <form method="POST" action="ArtworkDetailed">
                                            <div class="form-group">
                                                <input type="file" id="uploadArtwork" name="file" class="form-control-file" accept="image/png, image/jpeg">
                                                <input type="submit" id="submitArtwork" name="artUpload" value="Add Artwork" class="btn btn-block button-red-solid mt-1" disabled>                                                
                                                <input type="hidden" name="action" value="uploadArtwork">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </th>
                        </tr>
                    </thead>
                </table>
                <hr>
                <br>    

                <!--rounds-->
                



                    <c:if test="${showUpload != null}">
                        <c:if test="${position == 0}">
                            <h3>1</h3>   
                            <div style="width:100%; background-color: #f4efed;">
                                <table border="1" cellspacing="5" width="100%">
                                    <tbody>       
                                        <tr>
                                            <th colspan="2">        
                                                <div style="padding: 10px;">
                                                    <div align="center">                                    
                                                        <img class="fancybox" title="Artwork 1" src="css/Sdp_Kimmy_17.jpg" style="width: 250px; height:205px;" alt="Artwork 1"/>
                                                    </div>                                                                               
                                                </div>
                                            </th>                                    
                                        </tr> 
                                        <tr>  
                                            <td>
                                                <div align="center">                         
                                                    Waiting for feedback...
                                                </div>
                                            </td>
                                        </tr>
                                </table>
                            </div>
                        </c:if>
                        <c:if test="${position == 1}">
                            <h3>1</h3>   
                            <div style="width:100%; background-color: #f4efed;">
                                <table border="1" cellspacing="5" width="100%">
                                    <tbody>       
                                        <tr>
                                            <th colspan="2">        
                                                <div style="padding: 10px;">
                                                    <div align="center">                                    
                                                        <img class="fancybox" title="Artwork 1" src="css/Sdp_Kimmy_17.jpg" style="width: 250px; height:205px;" alt="Artwork 1"/>
                                                    </div>                                                                               
                                                </div>
                                            </th>                                    
                                        </tr> 
                                        <tr>  
                                            <td style="width: 70%">
                                                <div class="form-group">                                                                                 
                                                    <textarea rows="3" cols="50" class="form-control" form="statusApproveDeny" rows="5" id="comment" placeholder="Type your feedback here!" required></textarea>                                         
                                                </div>
                                            </td>
                                            <td>
                                                <div align="center">                         
                                                    <form action="ArtworkDetailed" method="post" id="statusApproveDeny">
                                                        <input type="submit" class="buttonApprove" name="approve" value="Approve">
                                                        <input type="submit" class="buttonDeny" name="deny" value="Deny"> 
                                                        <input type="hidden" name="action" value="approved">
                                                    </form>
                                                </div>
                                            </td>
                                        </tr>
                                </table>
                            </div>
                        </c:if>   
                    </c:if>
                    <c:if test="${approvedCookie != null}">
                        <c:if test="${position == 0}">
                            <h3>1</h3>   
                            <div style="width:100%; background-color: #f4efed;">
                                <table border="1" cellspacing="5" width="100%">
                                    <tbody>       
                                        <tr>
                                            <th colspan="2">        
                                                <div style="padding: 10px;">
                                                    <div align="center">                                    
                                                        <img class="fancybox" title="Artwork 1" src="css/Sdp_Kimmy_17.jpg" style="width: 250px; height:205px;" alt="Artwork 1"/>
                                                    </div>                                                                               
                                                </div>
                                            </th>                                    
                                        </tr> 
                                        <tr>                                      
                                            <td style="width: 70%">
                                                <div class="form-group">                                                                                 
                                                    <textarea readonly rows="3" cols="50" class="form-control" form="statusApproveDeny" rows="5" id="comment" placeholder="Great job, this was exactly what I was looking for!"></textarea>                                         
                                                </div>
                                            </td>
                                            <td>
                                                <div align="center">                         
                                                    <h1 style="color: #4CAF50">APPROVED</h1>
                                                </div>
                                            </td>
                                        </tr>
                                </table>
                            </div>
                        </c:if>
                        <c:if test="${position == 1}">
                            <h3>1</h3>   
                            <div style="width:100%; background-color: #f4efed;">
                                <table border="1" cellspacing="5" width="100%">
                                    <tbody>       
                                        <tr>
                                            <th colspan="2">        
                                                <div style="padding: 10px;">
                                                    <div align="center">                                    
                                                        <img class="fancybox" title="Artwork 1" src="css/Sdp_Kimmy_17.jpg" style="width: 250px; height:205px;" alt="Artwork 1"/>
                                                    </div>                                                                               
                                                </div>
                                            </th>                                    
                                        </tr> 
                                        <tr>                                      
                                            <td style="width: 70%">
                                                <div class="form-group">                                                                                 
                                                    <textarea readonly rows="3" cols="50" class="form-control" form="statusApproveDeny" rows="5" id="comment" placeholder="Great job, this was exactly what I was looking for!"></textarea>                                         
                                                </div>
                                            </td>
                                            <td>
                                                <div align="center">                         
                                                    <h1 style="color: #4CAF50">APPROVED</h1>
                                                </div>
                                            </td>
                                        </tr>
                                </table>
                                <div>
                                </c:if> 
                            </c:if>
                            <%--<c:if test="${roundsFilled == 0}">--%>
                                <!--<div align="center">-->
                                    <!--<h1>No artwork exists for this title!</h1>-->
                                <!--</div>-->
                            <%--</c:if>--%>
                            <c:if test="${uploaded != null}">
                                <div class="alert alert-success alert-dismissible fixed-bottom ml-2 mr-2">
                                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                    <strong>Success</strong> ${goodFeedback}
                                </div>
                            </c:if>
                            <c:if test="${failed != null}">
                                <div class="alert alert-danger alert-dismissible fixed-bottom ml-2 mr-2">
                                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                    <strong>Error</strong> ${badFeedback}
                                </div>
                            </c:if> 

                            <br>
                            <br>
                            <br>
                            <br>
                            <br>
                            <script type="text/javascript">
                                $('#uploadArtwork').on("change", function () {
                                    $('#submitArtwork').prop('disabled', !$(this).val());
                                });
                            </script>


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

                            <script type="text/javascript">
                                $(".alert-dismissible").fadeTo(2000, 500).slideUp(500, function () {
                                    $(".alert-dismissible").slideUp(500);
                                });
                            </script>


                        </div>
                    </div>
                    </body>

                    </html>
