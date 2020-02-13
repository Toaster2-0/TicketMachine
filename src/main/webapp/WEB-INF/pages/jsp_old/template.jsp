<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html lang="de">
  <head>
    <title>Tickets</title>
    <link rel="STYLESHEET" type="text/css" href="<c:url value="/include/stylesheet.css"/>">
  </head>
       <body> 
       <c:out value="${message}" />
       	<c:forEach var="thisOffer" items="${offer}">
     		<form action="<c:url value='/tickets.do'/>" method="post">
	       		<input type="hidden" name="chosenOffer" value="<c:out value='${thisOffer.getKey()}'/>" />
	       		<c:out value="${thisOffer.getKey()}/><c:out value="for" /><c:out value="${thisOffer.getValue()}" />
	       		<input type = "submit" value = "buy">
	       	</form>
     	</c:forEach>
     	<c:if test="${chosenOffer!=null}">
     		<form action="<c:url value='/tickets.do'/>" method="post">
     			<input type="hidden" name="chosenOffer" value="<c:out value='${chosenOffer}'/>" />
     			<input type="hidden" name="toPay" value="<c:out value='${toPay}'/>" />
	       		<input type="text" name="coin" value="" placeholder="Type in your coin" required />
	       		<input type = "submit" value = "pay">
	       	</form>
     	</c:if>
     	<c:out value="your Change" />
       	<c:forEach var="coin" items="${change}">
     		<c:out value="Here is one " /><c:out value="${coin}" /><c:out value="cent coin" />
     	</c:forEach>
    </body>
</html>