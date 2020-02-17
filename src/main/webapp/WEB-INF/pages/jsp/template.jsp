<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html lang="de">
  <head>
    <!-- Required meta tags -->
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <title>Tickets</title>
    <link rel="STYLESHEET" type="text/css" href="<c:url value="/include/stylesheet.css"/>">
  </head>
       <body> 
            <div id="inhalt">
                <c:import url="${url}"></c:import>
            </div>    
    </body>
</html>