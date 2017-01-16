<%-- 
    Document   : show
    Created on : Jan 16, 2017, 1:02:49 AM
    Author     : Xhulio
--%>

<%@ page contentType="text/html;charset=UTF-8" 
         pageEncoding="utf-8" 
         trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th></th>
                    <th>Username</th>
                    <th>First Name</td>
                    <th>Last Name</th>
                    <th>Birthday</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>User Type</th>
                        <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
                        <th>Delete</th>
                        </c:if>

                </tr>
            </thead>
            <c:forEach items="${allUsers}" var="user" varStatus="ic">
                <tr data-toggle="collapse" data-target="#${user.id}" class="clickable">

                    <td>${ic.count}</td>
                    <td><c:out value="${user.userName}"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><fmt:formatDate value="${user.birthDate}" type="date" dateStyle="medium"/></td>
                    <td><c:out value="${user.address}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.userType}"/></td>

                    <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
                        <td>
                            <button type="button" class="btn btn-primary deleteUser" data-user="${user.id}">Delete</button>
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <td colspan="8" style="padding: 0 !important;text-align: center">
                        <div id="${user.id}" class="collapse">
                            <c:forEach items="${user.preferredSports}" var="sport" varStatus="ic">
                                <span>
                                    <c:out value="${sport.name}"/>
                                </span>
                                <br />
                            </c:forEach>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
            <my:a href="/user/new"><button type="button" class="btn btn-primary">Create user</button></my:a>
        </c:if>

    </jsp:attribute>
</my:pagetemplate>