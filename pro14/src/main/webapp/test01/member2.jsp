<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>

<% request.setCharacterEncoding("UTF-8");
%>    

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 목록창</title>
</head>
<body>
	<table style="margin:auto; width:100%;">
		<tr align="center" bgcolor="#99ccff">
			<td width="20%">아이디</td>
			<td width="20%">비밀번호</td>
			<td width="20%">이름</td>
			<td width="20%">이메일</td>
			<td width="20%">주소</td>
			<td width="20%">전화번호</td>
			<td width="20%">나이</td>
		</tr>
		<tr align="center">
			<td>${param.id}</td> 
			<td>${param.password}</td>
			<td>${param.name}</td>
			<td>${param.email}</td>
			<td>${requestScope.address}</td>
			<td>${sessionScope.tel}</td>
			<td>${applicationScope.age}</td>
			
			
		</tr>		
		<tr height="1" bgcolor="#99ccff">
			<td colspan="7"></td>
		</tr>
	</table>
</body>
</html>
