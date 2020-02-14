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