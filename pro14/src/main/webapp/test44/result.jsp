<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<%
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결과출력창</title>
</head>
<body>
	<h1>결과 출력</h1>
	<c:if test = "${empty param.user_id}">
		아이디를 입력하세요.<br>
		<a href="login.html">로그인 창</a>
	</c:if>
	<c:if test = "${not empty param.user_id}">
		<h2>환영합니다. <c:out value="${param.user_id}"/> 님</h2>
		<a href="login.html">로그인 창</a>
	</c:if>
</body>
</html>
