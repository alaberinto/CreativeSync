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
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <title>Title Detailed</title>
    </head>
    <body class="background-plain">
        <sync:navbar1></sync:navbar1>
            <div class="searchBarHeader"></div>

            <div class="container bg-white pb-3">
                <div class="row">
                    <div class="col-12">
                        <div class="page-header border-bottom mb-3 text-center">${view.title.name}</div>
                    <div class="progress mb-1">
                        <div class="progress-bar bg-danger" style="width:${datepercentage}%;"></div>
                    </div>
                </div>
            </div>
            <div class="row border-danger">
                <div class="col-4">
                    <p><b>Start:</b> <fmt:formatDate type = "date"
                                    value = "${view.title.startDate}" /></p>
                </div>
                <div class="col-4 text-center" >
                    <p><b>${timeLeft}</b> Days Left!</p>
                </div>
                <div class="col-4 text-right" >
                    <p><b>End:</b> <fmt:formatDate type = "date"
                                    value = "${view.title.endDate}" /></p>
                </div>
            </div>
            <div class="row mt-2">
                <div class="col-12">
                    <div class="form-group">
                        <label for="exampleFormControlTextarea1"><b>Design Info</b></label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="6" disabled>${view.title.designInfo}</textarea>
                    </div>
                </div>
            </div>

            <div class="accordion mt-4" id="accordion">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <button class="btn btn-block btn-secondary col-2" type="button" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                            Members
                        </button>
                    </div>
                    <div id="collapse1" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <div class="row mt-2 pl-3 pr-3">
                                <div class="col-sm-12 col-med-12 col-lg-6 col-xl-6 mb-5">
                                    <div class="row">
                                        <div class="col-6 text-center">
                                            <h3><b>Design Lead</b></h3>
                                            <a href="UserDetailed?name=${view.lead.firstname} ${view.lead.lastname}">
                                                <img class="img-fluid rounded-circle title-user mb-1" src="css/profile-placeholder.png" alt="">
                                            </a>
                                            <h4>${view.lead.firstname} ${view.lead.lastname}</h4>
                                        </div>
                                        <div class="col-6 text-center border-right">
                                            <h3><b>Coordinator</b></h3>
                                            <a href="UserDetailed?name=${view.coor.firstname} ${view.coor.lastname}">
                                                <img class="img-fluid rounded-circle title-user mb-1" src="css/profile-placeholder.png" alt="">
                                            </a>
                                            <h4>${view.coor.firstname} ${view.coor.lastname}</h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 text-center">
                                    <h3><b>Designers</b></h3>
                                    <div class="d-flex justify-content-center">
                                        <c:forEach items="${frees}" var="free">
                                            <div class="ml-2 mr-2">
                                                <a href="UserDetailed?name=${free.account.firstname} ${free.account.lastname}">
                                                    <img class="img-fluid rounded-circle title-user mb-1" src="css/profile-placeholder.png" alt="">
                                                </a>
                                                <h4>${free.account.firstname} ${free.account.lastname}</h4>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headerTwo">
                        <button class="btn btn-block btn-secondary col-2" data-toggle="collapse" data-target="#collapse2" aria-expanded="false" aria-controls="collapse2">
                            Assets
                        </button>
                    </div>
                    <div id="collapse2" class="collapse">
                        <div class="card-body pb-1 pl-3">
                            <div class="row border-bottom pb-2">
                                <div class="col-4">

                                    <c:if test="${user.position.positionId != 4}">
                                        <form method="POST" action="TitleDetailed" enctype="multipart/form-data">
                                            <input type="submit" id="submitAsset" value="Upload Asset" class="btn btn-block button-red-solid mb-1 col-6">
                                            <input type="file" id="uploadAsset" name="file" class="form-control-file" accept="image/png, image/jpeg">
                                            <input type="hidden" name="action" value="uploadAsset">
                                        </form>
                                    </c:if>
                                </div>
                                <div class="col-6">

                                </div>
                                <div class="col-2">
                                    <form method="POST" action="TitleDetailed">
                                        <button type="submit" name="action" value="downloadAllAssets" class="btn btn-block button-red-solid">
                                            Download All
                                        </button>
                                    </form>
                                </div>
                            </div>

                        </div>

                        <div class="d-flex flex-wrap row-hl">
                            <c:forEach items="${assets}" var="asset">
                                <div class="title-asset m-2">
                                    <div class="asset-image">
                                        <div class="overlay">
                                            To download asset:<br/>
                                            1. Right click "Download Asset"<br/>
                                            2. Click "Save Link As.."<br/><br/>
                                            To view asset:<br/>
                                            1. Left click "Download Asset"<br/>
                                            <a class="link" href="${asset}" download target="_blank">
                                                <button type="button" class="btn btn-block button-red-solid">
                                                    Download Asset
                                                </button>
                                            </a>
                                            <c:if test="${user.position.positionId != 4}">
                                                <form method="post" action="TitleDetailed">
                                                    <input type="submit" value="Delete Asset" class="btn btn-block button-red-solid mt-3">
                                                    <input type="hidden" name="action" value="deleteAsset">
                                                    <input type="hidden" name="assetName" value="${asset}">
                                                </form>
                                            </c:if>
                                        </div>
                                        <img src="${asset}" class="asset item-h1" alt="BROKEN IMAGE"/>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>                         
            <form method="GET" action="ArtworkDetailed" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="submit" id="viewArtwork" value="View Artwork" class="btn btn-block button-red-solid mt-2">
                    <input type="hidden" name="action" value="uploadArtwork">
                </div> 
            </form> 
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
    </body>

    <script type="text/javascript">
        $('.collapse').on('shown.bs.collapse', function () {
            $(this).parent().find(".glyphicon-plus").removeClass("glyphicon-plus").addClass("glyphicon-minus");

        }).on('hidden.bs.collapse', function () {
            $(this).parent().find(".glyphicon-minus").removeClass("glyphicon-minus").addClass("glyphicon-plus");
        });

        $('#uploadAsset').on("change", function () {
            $('#submitAsset').prop('disabled', !$(this).val());
        });

        $('#uploadArtwork').on("change", function () {
            $('#submitArtwork').prop('disabled', !$(this).val());
        });

        $(".alert-dismissible").fadeTo(2000, 500).slideUp(500, function () {
            $(".alert-dismissible").slideUp(500);
        });
    </script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>
</html>
