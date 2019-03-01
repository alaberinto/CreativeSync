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
        <title>Title Detailed</title>
    </head>
    <body class="background-plain">
        <sync:navbar></sync:navbar>
            <div class="container background-white pb-3">
                <div class="row">
                    <div class="col-12">
                        <h1 class="text-center mt-5 mb-4 text-uppercase font-weight-bold">${titlename}</h1>
                    <div class="progress mb-1">
                        <div class="progress-bar bg-danger" style="width:${datepercentage}%;"></div>
                    </div>
                </div>
            </div>
            <div class="row border-danger">
                <div class="col-6">
                    <p>Start: <fmt:formatDate type = "date" 
                                    value = "${startdate}" /></p>
                </div>
                <div class="col-6 text-right" >
                    <p>End: <fmt:formatDate type = "date" 
                                    value = "${enddate}" /></p>
                </div>
            </div>
            <div id="accordion">
                <div class="card">
                    <div class="card-header">
                        <h5>
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapse1" aria-expanded="false" aria-controls="collapse1">
                                <i class="fa" aria-hidden="true"></i>
                                Members
                            </button>
                        </h5>
                    </div>

                    <div id="collapse1" class="collapse show">
                        <div class="card-body">
                            <div class="row mt-2 pl-3 pr-3">
                                <div class="col-sm-12 col-med-12 col-lg-6 col-xl-6 mb-5">
                                    <div class="row">
                                        <div class="col-6 text-center lead">
                                            <h3>Lead</h3>
                                            <a href="%">
                                                <div class="title-user">
                                                    <img class="prof" src="css/images/User/male1084427996429.jpg" alt="IMAGE"/>
                                                </div>
                                            </a>
                                        </div>
                                        <div class="col-6 text-center coor">
                                            <h3>Coordinator</h3>
                                            <a href="#">
                                                <div class="title-user">
                                                    <img class="prof" src="css/images/User/male20161086537831756.jpg" alt=""/>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 text-center des">
                                    <h3>Designers</h3>
                                    <div class="d-flex flex-wrap row-hl">
                                        <div class="p-2 item-hl title-user">
                                            <img class="prof" src="css/images/User/male20161086693679535.jpg" alt=""/>
                                        </div>
                                        <div class="p-2 item-hl title-user">
                                            <img class="prof" src="css/images/User/female1021755931884.jpg" alt=""/>
                                        </div>
                                        <div class="p-2 item-hl title-user">
                                            <img class="prof" src="css/images/User/male20171084101124939.jpg" alt=""/>
                                        </div>
                                        <div class="p-2 item-hl title-user">
                                            <img class="prof" src="css/images/User/female1022890589508.jpg" alt=""/>
                                        </div>
                                        <div class="p-2 item-hl title-user">
                                            <img class="prof" src="css/images/User/female20091023302387177.jpg" alt=""/>
                                        </div>
                                        <div class="p-2 item-hl title-user">
                                            <img class="prof" src="css/images/User/female20151024302703033.jpg" alt=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <h5>
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse2" aria-expanded="false" aria-controls="collapse2">
                                <i class="fa" aria-hidden="true"></i>
                                Assets
                            </button>
                        </h5>
                    </div>
                    <div id="collapse2" class="collapse">
                        <div class="card-body">
                            <form method="post" action="TitleDetailed" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="file">File input</label>
                                    <input type="file" id="file" name="file" class="form-control-file">
                                    <small class="form-text text-muted" id="fileHelp">Max 3mb size</small>
                                    <button class="btn btn-success" type="submit" name="action" value="upload">Upload</button>
                                </div>
                            </form>
                            <div class="d-flex flex-wrap row-hl">
                                <c:forEach items="${assetFiles}" var="file">
                                    <div class="title-asset m-2">
                                        <div class="asset-image">
                                            <img class="asset item-h1" src="css/images/Title/${titlename}/asset/${file}" alt="ASSET IMAGE">
                                        </div>
                                        <div class="asset-image-buttons">
                                            <form method="post" action="TitleDetailed">
                                                <button class="asset-image-btn" value="deleteAsset" name="action" type="submit">Delete</button>
                                                <input type="hidden" name="assetName" value="${file}">
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        <h5>
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapse3" aria-expanded="false" aria-controls="collapse3">
                                <i class="fa" aria-hidden="true"></i>
                                Artwork
                            </button>
                        </h5>
                    </div>

                    <div id="collapse3" class="collapse">
                        <div class="card-body">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Veritatis ea iste a doloremque, cumque, debitis eum vel ipsum architecto
                            aut, recusandae totam ullam aperiam. Nesciunt expedita officiis animi quam corporis optio inventore facilis sint
                            et nulla in, repellat debitis dolor at nisi quo, unde temporibus. Quos nisi nostrum officia, illo.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript">
    $('.collapse').on('shown.bs.collapse', function () {
        $(this).parent().find(".glyphicon-plus").removeClass("glyphicon-plus").addClass("glyphicon-minus");

    }).on('hidden.bs.collapse', function () {
        $(this).parent().find(".glyphicon-minus").removeClass("glyphicon-minus").addClass("glyphicon-plus");
    });
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
crossorigin="anonymous"></script>
</html>
