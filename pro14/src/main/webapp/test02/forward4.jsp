<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
		 
<%	request.setCharacterEncoding("utf-8");%>

<%
	//JSP page 내장객체에는 값을 바인딩 시킬 수 없습니다.
	
	request.setAttribute("id","hong");
	request.setAttribute("password", "1234");
	request.setAttribute("address","서울시 강남구");
	session.setAttribute("name", "홍길동");
	session.setAttribute("address","수원시 팔달구");
	application.setAttribute("email", "hong@test.com");
%>    

<html>
<head>
	<meta  charset="UTF-8">
	<title>forward2</title>
</head>
<body>
	<jsp:forward page="/test02/member4.jsp" />
</body>
</html>