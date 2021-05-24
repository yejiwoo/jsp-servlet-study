<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 첫번쨰 jsp 페이지에서 포워딩 된 request 객체에서 정보를 가져온다.
	String name=(String)request.getAttribute("name"); 
	String address=(String)request.getAttribute("address");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이름은 <%=name %>입니다. </h1>
	<h1>주소는 <%=address %>입니다. </h1>
</body>
</html>