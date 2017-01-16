<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>
<%@ attribute name="state" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${state eq 'NOT_STARTED'}">Not started</c:when>
    <c:when test="${state eq 'ONGOING'}">Ongoing</c:when>
    <c:when test="${state eq 'FINISHED'}">Finished</c:when>
    <c:otherwise>Undefined</c:otherwise>
</c:choose>