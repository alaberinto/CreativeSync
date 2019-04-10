<%-- 
    Document   : userreport
    Created on : Apr 3, 2019, 6:12:14 PM
    Author     : 759388
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
         
<table class="table table-striped">
    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Rate</th>
            <th>Location</th>
            <th>Language</th>
            <th>Position</th>
            <th>Genres</th>
            <th>Titles</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="acc" items="${list}">
            <tr>
                <td><c:out value="${acc.user.firstname}"/></td>
                <td><c:out value="${acc.user.lastname}"/></td>
                <td><c:out value="${acc.user.email}"/></td>
                <td><c:out value="${acc.user.rate}"/></td>
                <td><c:out value="${acc.user.location.locationDesc}"/></td>

                <td><c:forEach var="lang" items="${acc.user.languageList}">
                        <c:out value="${lang.languageName}"/>
                    </c:forEach></td>

                <td><c:out value="${acc.user.position.positionDesc}"/></td>

                <td><c:forEach var="genre" items="${acc.user.genreList}">
                        <c:out value="${genre.genreDesc}"/>
                    </c:forEach></td>

                <td><c:forEach items="${acc.titles}" var="title">
                        <c:out value="${title.title.name}"/>
                    </c:forEach></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

Report generated on: <fmt:formatDate type = "date" value = "${reportgendate}" />