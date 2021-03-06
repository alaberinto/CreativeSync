<%-- 
    Document   : userreport
    Created on : Apr 3, 2019, 6:12:14 PM
    Author     : 759388
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<table id="usersreporttable" class="table">
    <thead>
    <div class="text-center w-100">
        <h2 class="text-center">Viewing Users Information</h2>
    </div>

    <tr class="table-secondary text-center">
        <th><b>First Name</b></th>
        <th><b>Last Name</b></th>
        <th><b>Email</b></th>
        <th><b>Rate</b></th>
        <th><b>Location</b></th>
        <th><b>Position</b></th>
        <th><b>Genres</b></th>
        <th><b>Titles</b></th>
    </tr>
</thead>
<tbody>
    <c:forEach var="acc" items="${list}">
        <tr>
            <td><b><c:out value="${acc.user.firstname}"/></b></td>
            <td><b><c:out value="${acc.user.lastname}"/></b></td>
            <td><c:out value="${acc.user.email}"/></td>
            <td>$<c:out value="${acc.user.rate}"/></td>
            <td><c:out value="${acc.user.location.locationDesc}"/></td>
            <td><c:out value="${acc.user.position.positionDesc}"/></td>

            <td>
                <ul>
                    <c:forEach var="genre" items="${acc.user.genreList}">
                        <li>
                            <c:out value="${genre.genreDesc}"/>
                        </li>
                    </c:forEach>
                </ul>
            </td>
            <td>
                <ul>
                    <c:forEach items="${acc.titles}" var="title">
                        <li>
                            <c:out value="${title.title.name}"/>
                        </li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
</tbody>
</table>