<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id=request.getParameter("id");
	String password=request.getParameter("password");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center">
		<tr align="center" bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%"><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
		</tr>
		<tr align=align>
			<td>${param.id }</td>
			<td>${param.password }</td>
			<td>${param.name }</td>
			<td>${param.email }</td>
		</tr>
		<tr>
			<td><%=id %></td>
			<td><%=password %></td>
			<td><%=name %></td>
			<td><%=email %></td>
		</tr>
	</table>
</body>
</html>