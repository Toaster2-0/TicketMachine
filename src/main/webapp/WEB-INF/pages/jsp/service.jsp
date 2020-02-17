<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
total Value: <c:out value="${totalValue }€"></c:out><br>
<table>
	<c:forEach var="coin" items="${coins }">
		<tr>
			<td ALIGN="RIGHT">
				<c:out value="${coin.getValue() }x"></c:out>
			</td>
			<td ALIGN="RIGHT">
				<c:out value="${coin.getKey() }c"></c:out>
			</td>
			<td ALIGN="RIGHT">
				<form action='<c:url value='/service.do'/>' method="post">
					<input type = "submit" value = "-"><input name="coin" type = "hidden" value = "${coin.getKey() }"><input name="change" type = "hidden" value = "minus">
				</form>
			</td>
			<td ALIGN="RIGHT">
				<form action='<c:url value='/service.do'/>' method="post">
					<input type = "submit" value = "+"><input name="coin" type = "hidden" value = "${coin.getKey() }"><input name="change" type = "hidden" value = "plus">
				</form>
			</td>
			<td ALIGN="RIGHT">
				<form action='<c:url value='/service.do'/>' method="post">
					<input name="newAmmount" type = "text" value = "" placeholder="new ammount" required><input type = "submit" value = "set"><input name="coin" type = "hidden" value = "${coin.getKey() }">
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<br>
Offers:
<form action="<c:url value='/service.do'/>" method="post" id="newOffer"></form>
<table>
	<tr>
		<td ALIGN="RIGHT">title</td><td ALIGN="RIGHT">Cost</td>
	</tr>
	<c:forEach var="thisOffer" items="${offers }">
			<tr>
				<td ALIGN="RIGHT">
					<c:out value="${thisOffer.getKey()}"/>
				</td>
				<td ALIGN="RIGHT">
					<c:out value="${thisOffer.getValue()}" />
				</td>
				<td ALIGN="RIGHT">
					<form action="<c:url value='/service.do'/>" method="post" id="deleteOffer"></form>
					<input type="hidden" name="removeOffer" value="<c:out value='${thisOffer.getKey()}'/>" form="deleteOffer"/>
					<input type = "submit" value = "remove" form="deleteOffer">
				</td>
			</tr>
	</c:forEach>
		<tr>
			<td ALIGN="RIGHT">
				<input name="ticketname" type="text" value="" placeholder="Ticketname" required form="newOffer">
			</td>
			<td ALIGN="RIGHT">
				<input name="ticketcost" type="text" value="" placeholder="Cost in Cent" required form="newOffer">
			</td>
			<td ALIGN="RIGHT">
				<input type = "submit" value = "add" form="newOffer">
			</td>
		</tr>
</table>
