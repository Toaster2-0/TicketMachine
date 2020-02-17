<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:forward page="<c:out value="${forward }"/>">
<jsp:param name="error" value="<c:out value="${error }"/>" />
<jsp:param name="coin" value="<c:out value="${null }"/>" />
<jsp:param name="ticketname" value="<c:out value="${null }"/>" />
</jsp:forward>
