<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%	request.setCharacterEncoding("UTF-8"); %>    

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL 다국어 기능</title>
</head>
<body>
	<%-- <fmt:setLocale value="ko_KR" /> --%>
	<fmt:setLocale value="en_US" />
	
	
	<h1>
	 회원정보<br><br>
	<fmt:bundle basename="resource.member" > 
	이름:<fmt:message key="mem.name" /><br>
	주소:<fmt:message key="mem.address" /><br>
	직업:<fmt:message key="mem.job" /><br>
	phone:<fmt:message key="mem.tel" /><br>
	email:<fmt:message key="mem.email" /><br>
	</fmt:bundle>
	</h1>
</body>
</html>
