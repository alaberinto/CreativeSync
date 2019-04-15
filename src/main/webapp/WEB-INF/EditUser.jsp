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
        <title>Edit User</title>
    </head>
    <body class="background-plain">
        <sync:navbar1>
        </sync:navbar1>
        <div class="searchBarHeader"></div>

        <div class="container fullContainer bg-white">
            <div class="row">
                <div class="col-12 pt-4 pb-3 mb-2 border-bottom">
                    <h3 class="text-center">Edit User</h3>
                </div>
            </div>

            <form method="post" action="EditUser">
                <div class="row mt-3">
                    <div class="col-6">
                        <div class="form-group mb-0">
                            <label for="firstName"><h1 class="mb-0">First Name</h1></label>
                            <input class="form-control text-light-gray-full mb-0" id="firstName" type="text" name="firstname" value="${myUser.firstname}" required>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group mb-0">
                            <label for="lastName"><h3 class="mb-0">Last Name</h3></label>
                            <input class="form-control text-light-gray-full mb-0" id="lastName" type="text" name="lastname" value="${myUser.lastname}" required>
                        </div>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-6">
                        <label class="" for="email"><h3 class="mb-0">Email</h3></label>
                        <input type="email" class="form-control text-light-gray-full" id="email" name="email" value="${myUser.email}" disabled>
                    </div>
                    <div class="col-6">
                        <label class="" for="pass"><h3 class="mb-0">Password</h3></label>
                        <input type="text" class="form-control text-light-gray-full" id="pass" name="password" value="${password}" minlength="8" required>
                    </div>
                </div>
                    <div class="row mt-3">
                        <div class="col-4">
                            <label class="country" for="country"><h3 class="mb-0">Country</h3></label>
                            <select class="form-control text-light-gray-full" id="country" name="country" required>
                                <option value="" selected disabled hidden>Choose Country</option>
                                <c:forEach items="${locations}" var="location">                            
                                    <option value="${location.locationId}" ${location.locationId == selectLocation ? 'selected':''}>${location.locationDesc}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-4">
                            <label for="language"><h3 class="mb-0">Language</h3></label>
                            <select class="selectpicker form-control" name="language" id="language" multiple data-live-search="true" title="Select Languages" id="x" data-header="Select Languages" required>
                                <c:forEach items="${languages}" var="lang">
                                    <c:if test="${langIds != null}">
                                        <c:forEach items="${langIds}" var="langId">
                                            <option value="${lang.languageId}" ${lang.languageId == langId ? 'selected' : ''}>${lang.languageName}</option>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${langIds == null}">
                                        <option value="${lang.languageId}">${lang.languageName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-4">
                            <label for="genres"><h3 class="mb-0">Genres</h3></label>
                            <select class="selectpicker form-control" name="genres" id="genres" multiple data-live-search="true" title="Select Genres" id="y" data-header="Select Genres" required>
                                <option value="" selected disabled hidden>Choose Genres</option>
                                <c:forEach items="${genres}" var="gen">
                                    <c:if test="${genreIds != null}">
                                        <c:forEach items="${genreIds}" var="genreId">
                                            <option value="${gen.genreId}" ${gen.genreId == genreId ? 'selected' : ''}>${gen.genreDesc}</option>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${genreIds == null}">
                                        <option value="${gen.genreId}">${gen.genreDesc}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row mt-3">
                        <div class="col-6">
                            <label class="" for="pos"><h3 class="mb-0">Position</h3></label>
                            <select class="form-control text-light-gray-full" id="pos" name="position" disabled>
                                <option value="" selected disabled hidden>Choose Position</option>
                                <c:forEach items="${positions}" var="pos">
                                    <option value="${pos.positionId}" ${pos.positionId == selectPosition ? 'selected':''}>${pos.positionDesc}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-6">
                            <label class="" for="rate"><h3 class="mb-0">Hourly Rate</h3></label>
                            <div class="input-icon">    
                                <input type="number" class="form-control text-light-gray-full" id="rate" name="rate" value="${myUser.rate}" min="1" disabled>
                                <i>$</i>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5">
                        <div class="col-12">
                            <input type="submit" class="btn btn-block button-red-solid" placeholder="Create User">
                            <input type="hidden" name="myUser" value="${myUser.userId}">
                                   
                        </div>
                        
                    </div>                              

            </form>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="upload-image.js"></script>
</html>
