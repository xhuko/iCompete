<%-- 
    Document   : register
    Created on : Dec 16, 2016, 9:04:50 PM
    Author     : Xhulio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New event">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/event/register"
                   modelAttribute="registration" cssClass="form-horizontal">
            <div class="form-group">
                <form:label path="event.Id" cssClass="col-sm-2 control-label">Event</form:label>
                    <div class="col-sm-10">
                    <form:select path="event.id" cssClass="form-control">
                        <c:forEach items="${events}" var="c">
                            <form:option value="${c.id}">${c.name}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="event.id" cssClass="error"/></p>
                </div>
            </div>
                <form:hidden path="user.id"  style="display:none" value="2"/>

            <button class="btn btn-primary" type="submit">Register</button>

        </form:form>

    </jsp:attribute>
</my:pagetemplate>
