<%-- 
    Document   : userreport
    Created on : Apr 3, 2019, 6:12:14 PM
    Author     : 759388
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%-- any content can be specified here e.g.: --%>
<table border="1">



    <thead>

        <tr><td><h3>USER REPORT</h3></td></tr>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Rate</th>
            <th>Location</th>
            <th>Language</th>
            <th>Portfolio</th>
            <th>Position</th>
            <th>Genres</th>



        </tr>
    </thead>
    <tbody>

        <c:forEach var="acc" items="${filteredusers}">


            <tr>
                <td><c:out value="${acc.user.firstname}" /></td>
                <td><c:out value="${acc.user.lastname}" /></td>
                <td><c:out value="${acc.user.email}" /></td>
                <td><c:out value="${acc.user.rate}" /></td>
                <td><c:out value="${acc.user.location}" /></td>
                <td><c:forEach var="lang" items="${acc.languageList}"><c:out value="${acc.user.language}"/></c:forEach></td>
                <td><c:out value="${acc.user.portfolio}" /></td>
                <td><c:out value="${acc.user.position}" /></td>
                <td><c:forEach var="lang" items="${acc.genreList}"><c:out value="${acc.user.genre}" /></c:forEach></td>
            </tr>

        </c:forEach>
        <tr><td></tr>
        <tr><td></tr>
        <tr><td></tr>
        <tr><td>Report generated on: <fmt:formatDate type = "date" value = "${reportgendate}" /></td></tr>
    </tbody>
</table>