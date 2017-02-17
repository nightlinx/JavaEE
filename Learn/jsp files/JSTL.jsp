<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p> Witaj świecie </p>
	<c:out value="${user1.userName}" default="brak imienia" escapeXml="true">
	</c:out>
	<c:if test="${user1.userName eq 'Ania'}">
		<p> Witaj, Aniu </p>
	</c:if>
	<c:set var="imieUzytkownika" scope="request" value="${user1.userName}" />
	${imieUzytkownika}
	
	<c:set target="${user1}" property="userName" value="Ania" />
	${user1.userName}
	
	<c:catch var="wyjatek">
		<c:set target="${user1}" property="123" value="pesel" />
	</c:catch>
	${wyjatek}
	
	<c:choose>
		<c:when test="${user1.userName eq 'Ania'}">
			<p>Uzytkownik ma na imie Ania</p>
		</c:when>
		
		<c:when test="${user1.userName eq 'Jan'}">
			<p>Uzytkownik ma na imie Jan</p>
		</c:when>	

		<c:otherwise>
			<p>Uzytkownik ma inaczej na imie</p>
		</c:otherwise>
	</c:choose>
	
	<c:forEach items="${allUsers}" var="u">
		${u.userName} ${u.surname} <br />
	</c:forEach>
	
</body>
</html>