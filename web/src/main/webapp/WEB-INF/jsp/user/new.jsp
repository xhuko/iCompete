<%-- 
    Document   : event
    Created on : Jan 16, 2017, 1:18:57 AM
    Author     : Xhulio
--%>

<%@ page contentType="text/html;charset=UTF-8" 
         pageEncoding="utf-8" 
         trimDirectiveWhitespaces="false"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New user">
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/user/create" modelAttribute="userCreate" cssClass="form-horizontal">
            <div class="form-group ${userName_error?'has-error':''}">
                <form:label path="userName" cssClass="col-sm-2 control-label">Username</form:label>
                    <div class="col-sm-10">
                    <form:input path="userName" cssClass="form-control"/>
                    <form:errors path="userName" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${password_error?'has-error':''}">
                <form:label path="password" cssClass="col-sm-2 control-label">Password</form:label>
                    <div class="col-sm-10">
                    <form:password path="password" cssClass="form-control"/>
                    <form:errors path="password" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${passwordConfirm_error?'has-error':''}">
                <form:label path="passwordConfirm" cssClass="col-sm-2 control-label">Password confirm</form:label>
                    <div class="col-sm-10">
                    <form:password path="passwordConfirm" cssClass="form-control"/>
                    <form:errors path="passwordConfirm" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${firstName_error?'has-error':''}">
                <form:label path="firstName" cssClass="col-sm-2 control-label">First Name</form:label>
                    <div class="col-sm-10">
                    <form:input path="firstName" cssClass="form-control"/>
                    <form:errors path="firstName" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${lastName_error?'has-error':''}">
                <form:label path="lastName" cssClass="col-sm-2 control-label">Last Name</form:label>
                    <div class="col-sm-10">
                    <form:input path="lastName" cssClass="form-control"/>
                    <form:errors path="lastName" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${email_error?'has-error':''}">
                <form:label path="email" cssClass="col-sm-2 control-label">Email</form:label>
                    <div class="col-sm-10">
                    <form:input path="email" cssClass="form-control"/>
                    <form:errors path="email" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${address_error?'has-error':''}" >
                <form:label path="address" cssClass="col-sm-2 control-label">Address</form:label>
                    <div class="col-sm-10">
                    <form:input path="address" cssClass="form-control"/>
                    <form:errors path="address" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${birthDate_error?'has-error':''}">
                <form:label cssClass="col-sm-2 control-label" path="birthDate">Birthday:</form:label>
                    <div class="col-sm-10">
                    <form:input path="birthDate" cssClass="form-control" alt="dd-mm-yyyy" title="dd-mm-yyyy"/>
                    <form:errors path="birthDate" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="userType" cssClass="col-sm-2 control-label">User type</form:label>
                    <div class="col-sm-10">
                    <form:select path="userType" cssClass="form-control">
                        <c:forEach items="${userTypes}" var="type">
                            <form:option value="${type}">${type}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="userType" cssClass="error"/></p>
                </div>
            </div>


            <button class="btn btn-primary" type="submit">Create user</button>
        </form:form>
    </jsp:attribute>
</my:pagetemplate>