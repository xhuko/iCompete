<%-- 
    Document   : new
    Created on : Dec 15, 2016, 12:01:47 PM
    Author     : Xhulio
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
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
                    <th>Name</td>
                    <th>Sport</th>
                    <th>Capacity</th>
                    <th>Empty places</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Address</th>
                        <c:if test="${not empty authenticatedUser}">
                        <th>Register</th>
                        </c:if>
                         <c:if test="${not empty authenticatedUser && authenticatedUser.userType == UserType.ADMIN}">
                        <th>Delete</th>
                        </c:if>

                </tr>
            </thead>
            <c:forEach items="${events}" var="event" varStatus="ic">
                <tr>
                    <td>${ic.count}</td>
                    <td><c:out value="${event.name}"/></td>
                    <td><c:out value="${event.sport.name}"/></td>
                    <td><c:out value="${event.capacity}"/></td>
                    <td class="emptyPlaces"><c:out value="${eventEmptyPlaces[event.id]}"/>
                    <td><fmt:formatDate value="${event.startDate}" type="date" dateStyle="medium"/></td>
                    <td><fmt:formatDate value="${price.endDate}" type="date" dateStyle="medium"/></td>
                    <td><c:out value="${event.address}"/></td>
                    <c:if test="${not empty authenticatedUser}">
                        <td>
                            <c:choose>
                                <c:when test="${userRegisteredMap[event.id]}">
                                    <button type="button" class="btn btn-primary deregisterUser" data-event="${event.id}">Deregister</button>
                                </c:when>    
                                <c:otherwise>
                                    <button type="button" class="btn btn-primary registerUser" data-event="${event.id}">Register</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:if>
<c:if test="${not empty authenticatedUser && authenticatedUser.userType == UserType.ADMIN}">
                        <button type="button" class="btn btn-primary deleteEvent" data-event="${event.id}">Delete</button>
                        </c:if>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${not empty authenticatedUser}">
            <my:a href="/event/new"><button type="button" class="btn btn-primary">Create event</button></my:a>
        </c:if>

    </jsp:attribute>
</my:pagetemplate>