<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sync" uri="/WEB-INF/tlds/synctags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
        <title>Add Title</title>
    </head>
    <body class="background-plain">
        <sync:navbar1></sync:navbar1>
            <div class="searchBarHeader"></div>

            <div class="container fullContainer bg-white">
                <div class="row">
                    <div class="col-12 pt-4 pb-3 mb-2 border-bottom">
                        <h1 class="text-center">Add Title</h1>
                    </div>
                </div>

                <form method="post" action="AddTitle">
                    <div class="row mt-3">
                        <div class="col-12">
                            <div class="form-group mb-0">
                                <label for="titleName"><h3 class="mb-0">Name</h3></label>
                                <input class="form-control text-light-gray-full mb-0" id="titleName" type="text" name="name" value="${name}" required>
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <label class="" for="startDate"><h3 class="mb-0">Start Date</h3></label>
                        <input type="date" class="form-control text-light-gray-full" id="startDate" name="startDate" value="${startDate}" required>
                    </div>
                    <div class="col-6">
                        <label class="" for="endDate"><h3 class="mb-0">End Date</h3></label>
                        <input type="date" class="form-control text-light-gray-full" id="endDate" name="endDate" value="${endDate}" required>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-3">
                        <label class="" for="coors"><h3 class="mb-0">Coordinator</h3></label>
                        <select class="form-control text-light-gray-full" id="coors" name="coorId" required>
                            <option value="-1">None</option>
                            <c:forEach items="${coors}" var="coor">
                                <option value="${coor.userId}" ${coor.userId == coorId ? 'selected':''}>${coor.firstname} ${coor.lastname}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3">
                        <label class="" for="leads"><h3 class="mb-0">Design Lead</h3></label>
                        <select class="form-control text-light-gray-full" id="leads" name="leadId" required="">
                            <option selected value="-1">None</option>
                            <c:forEach items="${leads}" var="lead">
                                <option value="${lead.userId}" ${lead.userId == leadId ? 'selected':''}>${lead.firstname} ${lead.lastname}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label for="maxFree"><h3 class="mb-0"># Freelancers</h3></label>
                        <input type="number" class="form-control text-light-gray-full" id="maxFree" name="numberOfFreelancers" value="${numberOfFreelancers}" min="1" max="10" required>
                    </div>
                    <div class="col-3">
                        <label for="p"><h3 class="mb-0">Priority</h3></label><br>
                        <input class="form-control" type="checkbox" id="p" data-toggle="toggle" name="priority" data-onstyle="danger" ${checked}>
                    </div>

                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <label for="test"><h3 class="mb-0">Freelancers</h3></label>
                        <select name="freelancers" class="selectpicker form-control text-light-gray-full" id="test" multiple data-live-search="true" title="Select Freelancers" data-header="Select Freelancers">
                            <c:forEach items="${freelancers}" var="free">
                                <c:if test="${freeIds != null}">
                                    <c:forEach items="${freeIds}" var="freeId">
                                        <option value="${free.userId}" ${free.userId == freeId ? 'selected' : ''}>${free.firstname} ${free.lastname}</option>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${freeIds == null}">
                                    <option value="${free.userId}">${free.firstname} ${free.lastname}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-6">
                        <label for="genres"><h3 class="mb-0">Genres</h3></label>
                        <select class="selectpicker form-control" name="genres" id="genres" multiple data-live-search="true" title="Select Genres" id="y" data-header="Select Genres" required>
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
                    <div class="col-12">
                        <div class="form-group">
                            <label for="info"><h3 class="mb-0">Design Info.</h3></label>
                            <textarea class="form-control" id="info" name="info" rows="6" placeholder="Enter Notes Here">${info}</textarea>
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <input type="submit" class="btn btn-block button-red-solid">
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
    </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.4.0/js/bootstrap4-toggle.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<script type="text/javascript">
    $(".alert-dismissible").fadeTo(2000, 500).slideUp(500, function () {
        $(".alert-dismissible").slideUp(500);
    });
</script>
</html>
