<%-- 
    Document   : new
    Created on : Dec 15, 2016, 12:01:47 PM
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
                    <th>Name</th>
                    <th>Sport</th>
                    <th>Capacity</th>
                    <th>Empty places</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Address</th>
                    <th>State</th>
                    <th>Info</th>
                    <c:if test="${not empty authenticatedUser}">
                        <th>Register</th>
                        </c:if>
                    <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
                        <th>Update results</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </c:if>
                </tr>
            </thead>
            <c:forEach items="${events}" var="event" varStatus="ic">
                <tr data-toggle="collapse" data-target="#${event.id}" class="clickable">
                    <td>${ic.count}</td>
                    <td><c:out value="${event.name}"/></td>
                    <td><c:out value="${event.sport.name}"/></td>
                    <td><c:out value="${event.capacity}"/></td>
                    <td class="emptyPlaces"><c:out value="${eventEmptyPlaces[event.id]}"/></td>
                    <td><fmt:formatDate value="${event.startDate}" type="date" dateStyle="medium"/></td>
                    <td><fmt:formatDate value="${event.endDate}" type="date" dateStyle="medium"/></td>
                    <td><c:out value="${event.address}"/></td>
                    <td><my:eventstate state="${event.state.name()}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${event.state.name() eq 'NOT_STARTED'}">
                                <button type="button" class="btn btn-default resultButton" disabled="disabled"><c:out value="${event.remains}"/> remains</button>
                            </c:when>
                            <c:when test="${event.state.name() eq 'ONGOING'}">
                                <my:a href="/event/${event.id}"><button type="button" class="btn btn-danger resultButton">Live results</button></my:a>
                            </c:when>
                            <c:otherwise>
                                <my:a href="/event/${event.id}"><button type="button" class="btn btn-success resultButton">Show results</button></my:a>
                            </c:otherwise>
                        </c:choose>
                    </td>
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
                     <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
                        <td>
                            <my:a href="/event/${event.id}/results"><button type="button" class="btn btn-danger">Results</button></my:a>
                        </td>
                        <td>
                            <my:a href="/event/edit?eventId=${event.id}"><button type="button" class="btn btn-primary editEvent" data-event="${event.id}">Edit</button></my:a>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary deleteEvent" data-event="${event.id}">Delete</button>
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <td colspan="9" style="padding: 0 !important;text-align: center">
                        <div id="${event.id}" class="collapse"><c:out value="${event.description}"/></div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
            <my:a href="/event/new"><button type="button" class="btn btn-primary">Create event</button></my:a>
        </c:if>

    </jsp:attribute>
</my:pagetemplate>