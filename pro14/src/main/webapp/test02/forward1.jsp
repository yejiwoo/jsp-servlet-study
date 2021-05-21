<%@	page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<%	request.setCharacterEncoding("utf-8");%>

<%
	request.setAttribute("id","hong");
	request.setAttribute("pwd", "1234");
	session.setAttribute("name", "홍길동");
	application.setAttribute("email", "hong@test.com");
%>    

<html>
<head>
	<meta  charset="UTF-8">
	<title>forward1</title>
</head>
<body>
	<%-- <% response.sendRedirect("/pro14/test02/member1.jsp"); %> --%>
	<jsp:forward page="/test02/member1.jsp" />
</html>
