<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name=(String)session.getAttribute("name"); // 서블릿으로 변환 시 컨테이너에 의해 멤버변수가 된다.
	String address=(String)session.getAttribute("address");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session 내장객체 테스트2</title>
</head>
<body>
	이름은 <%=name %> 입니다. <br>
	주소는 <%=address %> 입니다. <br>
</body>
</html>