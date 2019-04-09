<%-- 
    Document   : viewtitlereport
    Created on : Apr 4, 2019, 6:40:04 PM
    Author     : 759388
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<table border="1">


    <thead>
        <tr><td><h3>VIEW TITLE REPORT</h3></td></tr>
        <tr>
            <th>Title Name</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Coordinator Name</th>
            <th>Design Lead Name</th>
            <th>Freelancers Names</th>
            <th>Genre</th>
            <th>Title Man Hours</th>
            <th>Estimated Cost</th>

        </tr>
        
        <tr>
            <th>Title Name</th>
            <th>Run Report</th>
        </tr>
    </thead>
    <tbody>

        <c:forEach var="tit" items="${titles}">


            <tr>
                <td><c:out value="${tit.name}" /></td>
                <td><fmt:formatDate type = "date" value = "${startdate}" /></td>
                <td><fmt:formatDate type = "date" value = "${enddate}" /></td>
                <td><c:out value="${tit.coor}" /></td>
                <td><c:out value="${tit.lead}" /></td>
                <td><c:forEach var="free" items="${tit.freelancer}"><c:out value="${free.name}"/></c:forEach></td>
                <td><c:out value="${tit.genre}" /></td>
                <td><c:out value="${tit.manhours}" /></td>
                <td><c:out value="${tit.estcost}" /></td>
            </tr>

        </c:forEach>
        <tr><td></tr>
        <tr><td></tr>
        <tr><td></tr>
        <tr><td>Report generated on: <fmt:formatDate type = "date" value = "${reportgendate}"/></td></tr>

    </tbody>
</table>