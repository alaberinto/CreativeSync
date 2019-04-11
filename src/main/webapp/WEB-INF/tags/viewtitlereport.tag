<%-- 
    Document   : viewtitlereport
    Created on : Apr 4, 2019, 6:40:04 PM
    Author     : 759388
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<table id="titlesreporttable" class="table">
    <thead>
    <div class="text-center w-100">
        <h2 class="text-center">Viewing Titles Information</h2>
    </div>

    <tr class="table-danger text-center">
        <th>Title Name</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Coordinator Name</th>
        <th>Design Lead Name</th>
        <th>Freelancers</th>
        <th>Genres</th>
    </tr>
</thead>
<tbody>
    <c:forEach var="tit" items="${list}">
        <tr>
            <td><b><c:out value="${tit.title.name}" /></b></td>
            <td><fmt:formatDate type = "date" value = "${tit.title.startDate}" /></td>
            <td><fmt:formatDate type = "date" value = "${tit.title.endDate}" /></td>
            <td><c:out value="${tit.coor.firstname} ${tit.coor.lastname}" /></td>
            <td><c:out value="${tit.lead.firstname} ${tit.lead.lastname}" /></td>

            <td>
                <ul>
                    <c:forEach var="free" items="${tit.freelancers}">
                        <li>
                            <c:out value="${free.firstname} ${free.lastname}"/>
                        </li>
                    </c:forEach>
                </ul>
            </td>
            <td>
                <ul>
                    <c:forEach var="genre" items="${tit.title.genreList}">
                        <li><c:out value="${genre.genreDesc}"/></li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
</tbody>
</table>
<br>
