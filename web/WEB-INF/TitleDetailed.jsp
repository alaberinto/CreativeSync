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
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Title Detailed</title>
    </head>
    <body>
        <sync:navbar></sync:navbar>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-center">Title Name</h1>
                    <div class="progress">
                        <div class="progress-bar bg-danger" style="width:35%;"></div>
                    </div>
                </div>
            </div>
            <div class="row border-bottom border-danger">
                <div class="col-6">
                    <h3 class="text-left">Start: Date</h3>
                </div>
                <div class="col-6">
                    <h3 class="text-right">End: Date</h3>
                </div>
            </div>
            <div class="row test-border">
                <div class="col-sm-12 col-med-12 col-lg-6 col-xl-6">
                    <div class="row">
                        <div class="col-6 text-center test-border">
                            <h3>Lead</h3>
                            <a href="#">
                                <div class="title-user">
                                    Mason
                                </div>
                            </a>
                        </div>
                        <div class="col-6 text-center test-border">
                            <h3>Coordinators</h3>
                            <a href="#">
                                <div class="title-user">
                                    Cooper
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 text-center test-border">
                    <h3>Designers</h3>
                    <div class="d-flex flex-wrap row-hl">
                        <div class="p-2 item-hl test-border">Flex item</div>
                        <div class="p-2 item-hl test-border">Flex item</div>
                        <div class="p-2 item-hl test-border">Flex items</div>
                        <div class="p-2 item-hl test-border">Flex item</div>
                        <div class="p-2 item-hl test-border">Flex item</div>
                        <div class="p-2 item-hl test-border">Flex item</div>
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
</html>
