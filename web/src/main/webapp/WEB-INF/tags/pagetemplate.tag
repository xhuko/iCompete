<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${title}"/></title>
        <!-- bootstrap loaded from content delivery network -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <!-- navigation bar -->
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">iCompete</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><my:a href="/event/show">Events</my:a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Administrator<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><my:a href="/event/new">New event</my:a></li>
                                <li><my:a href="/user/list">Sports</my:a></li>
                                <li><my:a href="/product/list">Users</my:a></li>
                                </ul>
                            </li>
                        </ul>
                    <c:choose>
                        <c:when test="${empty authenticatedUser}">
                            <form class="navbar-form pull-right" action="${pageContext.request.contextPath}/login/trylogin" method ="post">
                                <input class="span2" type="text" placeholder="Login name" name="logname">
                                <input class="span2" type="password" placeholder="Password" name="password">
                                <button type="submit" class="btn">Sign in</button>
                            </form>
                        </c:when>    
                        <c:otherwise>
                            <form class="navbar-text pull-right" method="post" action="${pageContext.request.contextPath}/login/logout">
                                Logged in as <c:out value="${authenticatedUser.firstName} ${authenticatedUser.lastName}"/>
                                &nbsp;&nbsp;
                                <button type="submit" class="btn">Logout</button>
                            </form>
                        </c:otherwise>
                    </c:choose>

                </div><!--/.nav-collapse -->

            </div>
        </nav>

        <div class="container">

            <!-- page title -->
            <c:if test="${not empty title}">
                <div class="page-header">
                    <h1><c:out value="${title}"/></h1>
                </div>
            </c:if>

            <!-- alerts -->
            <c:if test="${not empty alert_danger}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <c:out value="${alert_danger}"/></div>
                </c:if>
                <c:if test="${not empty alert_info}">
                <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
            </c:if>
            <c:if test="${not empty alert_success}">
                <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
            </c:if>
            <c:if test="${not empty alert_warning}">
                <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
            </c:if>

            <!-- page body -->
            <jsp:invoke fragment="body"/>

            <!-- footer -->
            <footer class="footer">
                <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Masaryk University</p>
            </footer>
        </div>
        <!-- javascripts placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script>
            $(function () {
                $(document).on("click",".registerUser",function () {
                    let eventId = $(this).data("event");
                    let userId = "${authenticatedUser.id}";
                    let $this = $(this);
                    let deregisterButton = $('<button type="button" class="btn btn-primary deregisterUser" data-event="'+eventId+'">Deregister</button>');
                    $.ajax({
                        url: "${pageContext.request.contextPath}/event/registerAjax",
                        type: "post",
                        data: {
                            "user.id": userId,
                            "event.id": eventId
                        },
                        success: function (res) {
                            debugger;
                            if(res && res.success){
                                $this.closest("tr").find("td.emptyPlaces").text(res.emptyPlaces);
                                $this.replaceWith(deregisterButton);
                            }
                        }
                    });
                });
                
                $(document).on("click",".deregisterUser",function () {
                    let eventId = $(this).data("event");
                    let userId = "${authenticatedUser.id}";
                    let $this = $(this);
                    let registerButton = $('<button type="button" class="btn btn-primary registerUser" data-event="'+eventId+'">Register</button>');
                    $.ajax({
                        url: "${pageContext.request.contextPath}/event/deregisterAjax",
                        type: "post",
                        dataType: 'json',
                        data: {
                            "user.id": userId,
                            "event.id": eventId
                        },
                        success: function (res) {
                           if(res && res.success){
                                $this.closest("tr").find("td.emptyPlaces").text(res.emptyPlaces);
                                $this.replaceWith(registerButton);
                            }
                        }
                    });
                });
                
                
                $(".deleteEvent").click(function () {
                    debugger;
                    let eventId = $(this).data("event");
                    let $this = $(this);
                    $.ajax({
                        url: "${pageContext.request.contextPath}/event/delete",
                        type: "get",
                        dataType: 'json',
                        data: {
                            "eventId": eventId
                        },
                        success: function () {
                            $this.replaceWith("Deleted");
                        }
                    });
                });
            });
        </script>
    </body>

</html>

