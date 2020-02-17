<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
total Value: <c:out value="${totalValue }"></c:out>
<c:forEach var="coin" items="${coins }">
	<c:out value="${coin.getValue() }x"></c:out><c:out value=" "></c:out><c:out value="${coin.getKey() }"></c:out>
	<form action='<c:url value='/service.do'/>' method="post">
		<input type = "submit" value = "-"><input name="coin" type = "hidden" value = "${coin }"><input name="change" type = "hidden" value = "minus">
	</form>
	<form action='<c:url value='/service.do'/>' method="post">
		<input type = "submit" value = "+"><input name="coin" type = "hidden" value = "${coin }"><input name="change" type = "hidden" value = "plus">
	</form>
	<form action='<c:url value='/service.do'/>' method="post">
		<input name="newAmmount" type = "text" value = "" placeholder="new ammount" required><input type = "submit" value = "set"><input name="coin" type = "hidden" value = "${coin }">
	</form>
</c:forEach>
<c:forEach var="thisOffer" items="${offers }">
	<form action="<c:url value='/service.do'/>" method="post">
		<c:out value="${thisOffer.getKey()}"/><c:out value=" for " /><c:out value="${thisOffer.getValue()}" />
		<input type="hidden" name="removeOffer" value="<c:out value='${thisOffer.getKey()}'/>" />
		<input type = "submit" value = "remove">
	</form>
</c:forEach>
Add Offer: 	<form action="<c:url value='/service.do'/>" method="post"><input name="ticketname" type="text" value="" placeholder="Ticketname" required><input name="ticketcost" type="text" value="" placeholder="Cost in Cent" required><input type = "submit" value = "add"></form>