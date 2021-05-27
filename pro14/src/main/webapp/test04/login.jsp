<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("utf-8"); %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인창</title>
</head>
<body>
	<form name="frmLogin" method="post" action="/pro14/test04/result2.jsp">
	<!-- <form name="frmLogin" method="post" action="/pro14/test04/result.jsp"> -->
		<input type="text" name="userId" placeholder="아이디를 입력하세요...">
		<br>
		<input type="password" name="userPw" placeholder="암호를 입력하세요...">
		<br>
		<input type="submit" value="로그인">
		<input type="reset" value="다시입력">
	</form>
</body>
</html>