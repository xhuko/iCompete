<%-- 
    Document   : new
    Created on : Dec 16, 2016, 12:01:47 PM
    Author     : Xhulio
--%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Login to iCompete">
<jsp:attribute name="body">
    
    <form method="post" action="${pageContext.request.contextPath}/login/trylogin" class="form-horizontal" style="text-align:center;">
          
        <div class="form-group">
            <label path="email" for="logname" class="col-sm-4 control-label">Email:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="logname" />
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-4 control-label">Password:</label>
            <div class="col-sm-4">
                <input type="password" name="password" class="form-control"/>
            </div>
        </div>
    <button class="btn btn-success" style="width:20%;" type="submit">Login</button>
    </form>
</jsp:attribute>
</my:pagetemplate>

