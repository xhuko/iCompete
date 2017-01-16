<%-- 
    Document   : new
    Created on : Dec 16, 2016, 12:01:47 PM
    Author     : Xhulio
--%>

<%@ page contentType="text/html;charset=UTF-8" 
         pageEncoding="utf-8" 
         trimDirectiveWhitespaces="false"
         import="com.icompete.enums.UserType"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Event - ${event.name} - Live">
    <jsp:attribute name="body">
        <div class="row">
            <div class="col-md-4">
                <h3>Information</h3>
                <table class="table table-condensed">
                    <tr><th>Name:</th><td><c:out value="${event.sport.name}"/></td></tr>
                    <tr><th>Capacity:</th><td><c:out value="${event.capacity}"/></td></tr>
                    <tr><th>Start date:</th><td><fmt:formatDate value="${event.startDate}" type="date" dateStyle="medium"/></td></tr>
                    <tr><th>End date:</th><td><fmt:formatDate value="${event.endDate}" type="date" dateStyle="medium"/></td></tr>
                    <tr><th>Address:</th><td><c:out value="${event.address}"/></td></tr>
                </table>
            </div>
            <div class="col-md-3">
                <h3>Description</h3>
                <p><c:out value="${event.description}"/></p>
            </div>
            <div class="col-md-5">
                <h3>Rules</h3>
                <ul>
                    <c:forEach items="${event.rules}" var="rule" varStatus="ic">
                        <li>
                            <c:out value="${rule.text}"/>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="row">
            <c:if test="${not (event.state.name() == 'NOT_STARTED')}">
                <div class="col-md-6">
                    <h3>Results</h3>
                    <form:form method="post" action="${pageContext.request.contextPath}/event/${event.id}/results">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>Position</th>
                                <th>Sportman</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${results}" var="result" varStatus="ic">
                                <tr>
                                    <td>
                                        <input type="text" name="results[${ic.count - 1}].value" value="<c:if test="${result.position == null}">?</c:if><c:if test="${not (result.position == null)}"><c:out value="${result.position}"/></c:if>"/>
                                        <input type="hidden" name="results[${ic.count - 1}].userId" value="${result.user.id}"/>
                                    </td>
                                    <td>
                                        <c:out value="${result.user.firstName} ${result.user.lastName}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <button class="btn btn-primary" type="submit">Update result</button>
                    </form:form>
                </div>
            </c:if>
        </div>
    </jsp:attribute>
</my:pagetemplate>
