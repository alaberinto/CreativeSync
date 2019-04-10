<%-- 
    Document   : viewtitlereport
    Created on : Apr 4, 2019, 6:40:04 PM
    Author     : 759388
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<table id="titlesreporttable" border="1">
    <thead>
        <tr><td><h3>VIEW TITLE REPORT</h3></td></tr>
        <tr>
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
                <td><c:out value="${tit.title.name}" /></td>
                <td><fmt:formatDate type = "date" value = "${tit.title.startDate}" /></td>
                <td><fmt:formatDate type = "date" value = "${tit.title.endDate}" /></td>
                <td><c:out value="${tit.coor.firstname} ${tit.coor.lastname}" /></td>
                <td><c:out value="${tit.lead.firstname} ${tit.lead.lastname}" /></td>
                <td><c:forEach var="free" items="${tit.freelancers}"><c:out value="${free.firstname} ${free.lastname}"/></c:forEach></td>
                <td><c:forEach var="genre" items="${tit.title.genreList}"><c:out value="${genre.genreDesc}"/></c:forEach></td>
            </tr>
        </c:forEach>
        <tr><td></tr>
        <tr><td></tr>
        <tr><td></tr>
        <tr><td>Report generated on: <fmt:formatDate type = "date" value = "${reportgendate}"/></td></tr>

    </tbody>
</table>
        <br>
        