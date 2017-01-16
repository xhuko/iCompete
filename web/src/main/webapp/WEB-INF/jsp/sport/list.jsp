<%-- 
    Document   : list.jsp
    Author     : Sekan
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Sports">
    <jsp:attribute name="body">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Type</th>
                    <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
                        <th>Edit</th>
                    </c:if>
                     <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
                        <th>Delete</th>
                    </c:if>
                </tr>
            </thead>
            <c:forEach items="${sports}" var="sport" varStatus="ic">
                <tr>
                    <td>${ic.count}</td>
                    <td><c:out value="${sport.name}"/></td>
                    <td><c:out value="${sport.description}"/></td>
                    <td><c:out value="${sport.type}"/></td>
                    <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
                        <td>
                            <my:a href="/sport/edit?id=${sport.id}"><button type="button" class="btn btn-warning">Edit</button></my:a>
                        </td>
                    </c:if>
                    <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
                        <td>
                            <form:form method="post" action="${pageContext.request.contextPath}/sport/delete">
                                <input type="hidden" name="sportId" value="${sport.id}"/>
                                <button class="btn btn-danger" type="submit">Delete</button>
                            </form:form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
            <my:a href="/sport/new"><button type="button" class="btn btn-success">Create sport</button></my:a>
        </c:if>

    </jsp:attribute>
</my:pagetemplate>