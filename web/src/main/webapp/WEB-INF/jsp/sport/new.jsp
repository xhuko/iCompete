<%-- 
    Document   : new.jsp
    Author     : Sekan
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

<my:pagetemplate title="New sport">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/sport/create" modelAttribute="sport" cssClass="form-horizontal">
            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                    <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${description_error?'has-error':''}">
                <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
                    <div class="col-sm-10">
                    <form:textarea cols="80" rows="20" path="description" cssClass="form-control"/>
                    <form:errors path="description" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${type_error?'has-error':''}">
                <form:label path="type" cssClass="col-sm-2 control-label">Type</form:label>
                <div class="col-sm-10">
                    <form:select path="type" cssClass="form-control">
                        <c:forEach items="${sportTypes}" var="c">
                            <form:option value="${c}">${c}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="type" cssClass="error"/></p>
                </div>
            </div>

            <button class="btn btn-success" type="submit">Create sport</button>
            <my:a href="/sport/list"><button type="button" class="btn btn-default">Cancel</button></my:a>
        </form:form>

    </jsp:attribute>
</my:pagetemplate>
