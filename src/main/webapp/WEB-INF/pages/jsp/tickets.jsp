<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:if test="${message!=null }">
	<c:out value="${message}" /><br>
</c:if>
<%--showing Offers --%>
 <c:forEach var="thisOffer" items="${offer}">
	<form action="<c:url value='/tickets.do'/>" method="post">
     		<input type="hidden" name="chosenOffer" value="<c:out value='${thisOffer.getKey()}'/>" />
     		<c:out value="${thisOffer.getKey()}"/><c:out value=" for " /><c:out value="${thisOffer.getValue()}c" />
     		<input type = "submit" value = "buy">
     	</form>
</c:forEach>

<%--paying --%>
<c:if test="${chosenOffer!=null&&toPay>0}">
	<form action="<c:url value='/tickets.do'/>" method="post">
	<c:out value="you have ${toPay }c left to pay"/><br>
		<input type="hidden" name="chosenOffer" value="<c:out value='${chosenOffer}'/>" />
		<input type="hidden" name="toPay" value="<c:out value='${toPay}'/>" />
     	<input type="text" name="coin" value="" placeholder="Type in your coin" autofocus="autofocus" required />
     	<input type = "submit" value = "pay">
     </form>
     	<c:forEach var="thisCoin" items="${money }">
	      	<form action="<c:url value='/tickets.do'/>" method="post">
	     	 	<input type="hidden" name="chosenOffer" value="<c:out value='${chosenOffer}'/>" />
				<input type="hidden" name="toPay" value="<c:out value='${toPay}'/>" />
		      	<input type="hidden" name="coin" value="${thisCoin }"/>
		      	<input type="submit" value="${thisCoin }">
	      	</form>
     	</c:forEach>
</c:if>

<%--returnging change --%>
<c:if test="${toPay<=0 }">
	<c:if test="${toPay<0 }">
			<c:out value="your Change:" /><br>
		</c:if>
  		<c:forEach var="changeCoin" items="${change}">
			<c:out value="Here is one " /><c:out value="${changeCoin}" /><c:out value=" cent coin" /><br>
		</c:forEach>
</c:if>