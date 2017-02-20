<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p> Witaj świecie </p>
	<c:url value="/users">
		<c:param name="id" value="123"></c:param>
	</c:url>
	<c:import url="users" var="strona" scope="request"></c:import>
	${strona}
	
	<p> Liczba wszystkich uzytkownikow </p>
	${fn:length(allUsers)}
	
	<p> Kwota </p>	
	<fmt:formatNumber value="${kwota}" type="currency" minFractionDigits="2" maxFractionDigits="5" currencyCode="PLN" />
	
	<p> Data i godzina </p>	
	<fmt:formatDate value="${data}" type="both" dateStyle="long"/>
	
	<p> godzina </p>
	<fmt:formatDate value="${data}" type="time"/>
		
</body>
</html>