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

<my:pagetemplate title="New event">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/event/create"
                   modelAttribute="eventCreate" cssClass="form-horizontal">
            <div class="form-group">
                <form:label path="sport.Id" cssClass="col-sm-2 control-label">Sport</form:label>
                    <div class="col-sm-10">
                    <form:select path="sport.id" cssClass="form-control">
                        <c:forEach items="${sport}" var="c">
                            <form:option value="${c.id}">${c.name}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="sport.id" cssClass="error"/></p>
                </div>
            </div>
            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                    <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${capacity_error?'has-error':''}" >
                <form:label path="capacity" cssClass="col-sm-2 control-label">Capacity</form:label>
                    <div class="col-sm-10">
                    <form:input path="capacity" cssClass="form-control"/>
                    <form:errors path="capacity" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${address_error?'has-error':''}" >
                <form:label path="address" cssClass="col-sm-2 control-label">Address</form:label>
                    <div class="col-sm-10">
                    <form:input path="address" cssClass="form-control"/>
                    <form:errors path="address" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${startDate_error?'has-error':''}">
                <form:label cssClass="col-sm-2 control-label" path="startDate">Start Date:</form:label>
                    <div class="col-sm-10">
                    <form:input path="startDate" cssClass="form-control datetimepicker" alt="dd-mm-yyyy" title="dd-mm-yyyy"/>
                    <form:errors path="startDate" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${endDate_error?'has-error':''}">
                <form:label cssClass="col-sm-2 control-label" path="endDate">End Date:</form:label>
                    <div class="col-sm-10">
                    <form:input path="endDate" cssClass="form-control datetimepicker" alt="dd-mm-yyyy" title="dd-mm-yyyy"/>
                    <form:errors path="endDate" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${description_error?'has-error':''}">
                <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
                    <div class="col-sm-10">
                    <form:textarea cols="80" rows="20" path="description" cssClass="form-control"/>
                    <form:errors path="description" cssClass="help-block"/>
                </div>
            </div>


            <button class="btn btn-primary" type="submit">Create event</button>

        </form:form>

    </jsp:attribute>
</my:pagetemplate>
