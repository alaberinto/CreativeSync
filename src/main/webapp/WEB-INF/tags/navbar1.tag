<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav id="nav" class="navbar navbar-expand-md navbar-light sticky-top custom-container ml-5 mr-5">
    <a href="Titles" class="navbar-brand">
        <img class="logo" src="css/Netflix_Logo_RGB.png" alt="Netflix"/>
    </a>

    <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div id="navbarNav" class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="Titles">Titles</a>
            </li>

            <c:if test="${user.position.positionId != 4}">
                <li class="nav-item">
                    <a class="nav-link" href="Users">Users</a>
                </li>
            </c:if>

            <c:if test="${user.position.positionId < 3 }">
                <li class="nav-item">
                    <a class="nav-link" href="Reports">Reports</a>
                </li>
            </c:if>

            <c:if test="${user.position.positionId == 1}">
                <li class="nav-item">
                    <a class="nav-link" href="Backup">Backups</a>
                </li>
            </c:if>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item ml-auto dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">${user.firstname} ${user.lastname}</a>
                <div class="dropdown-menu">
                    <a class="nav-link dropdown-item" href="MyAccount">My Account</a>
                    <a class="nav-link dropdown-item" href="Login">Logout</a>
                </div>
            </li>
        </ul>
    </div>
</nav>