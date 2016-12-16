<%-- 
    Document   : home
    Created on : Dec 15, 2016, 11:07:15 PM
    Author     : Xhulio
--%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="title">
<jsp:attribute name="body">

    
    <div class="jumbotron">
        <h1>Welcome to SpringMVC !</h1>
        <p class="lead">In this seminar, the mysteries of Spring MVC will be revealed to you. </p>
        
    </div>


    <div class="row">
        <c:forEach begin="1" end="12" var="i">
        <div class="col-xs-12 col-sm-6 col-md-2 col-lg-1">
            <p><button class="btn btn-default">Button ${i}</button></p>
        </div>
        </c:forEach>
    </div>

</jsp:attribute>
</my:pagetemplate>

