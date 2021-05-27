<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>

<%	request.setCharacterEncoding("UTF-8"); %>    

<html>
<head>
	<meta  charset="UTF-8">
	<title>회원 정보 출력창</title>
</head>
<body>
	<table style="border:1 grey solid; margin:auto;" >
		<tr align="center"  bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%" ><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
		</tr>
		<tr align="center">
			<td>${membersList[0].id}</td>
			<td>${membersList[0].password}</td>
			<td>${membersList[0].name}</td>
			<td>${membersList[0].email}</td>
		</tr>
		<tr align="center">
			<td>${membersList[1].id}</td>
			<td>${membersList[1].password}</td>
			<td>${membersList[1].name}</td>
			<td>${membersList[1].email}</td>
		</tr>
		
<%-- 	아래처럼 작성하면 오류발생
		<tr align="center">
			<td>${membersList.m1.id}</td>
			<td>${membersList.m1.password}</td>
			<td>${membersList.m1.name}</td>
			<td>${membersList.m1.email}</td>
		</tr> --%>
	</table>
</body>
</html>
