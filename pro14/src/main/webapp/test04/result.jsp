<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<% request.setCharacterEncoding("utf-8"); %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결과출력창</title>
</head>
<body>
	<h1>결과 출력</h1>
<%--
<%
	String userId=request.getParameter("userId");
	String userPw=request.getParameter("userPw");
%>	
	<h1>아이디  : <%=userId %></h1>
	<h1>비밀번호: <%=userPw %></h1> --%>
	
	<h1>아이디 : ${param.userId}</h1>
	<h1>암호 : ${param.userPw}</h1>
	<hr>
	
	<h1>결과 출력</h1>
	<c:if test="${(empty param.userId)||(empty param.userPw)}">
		아이디와 암호를 입력하세요.<br><br>
		아이디 또는 암호가 틀립니다.<br>
		아이디 또는 암호를 확인 후 다시 로그인 해주세요.<br><br>
		<a href="/pro14/test04/login.jsp">로그인 창</a>
	</c:if>
	<c:if test="${(not empty param.userId)&&(not empty param.userPw)}">
		<h2>환영합니다. ${param.userId}님!!!</h2>
		<%-- <h2>환영합니다. <c:out value="${param.userId}"/> 님!!!</h2> --%>
		<a href="/pro14/test04/login.jsp">로그인 창</a>
	</c:if>
</body>
</html>
