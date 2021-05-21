<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
		 
		 <!-- isELIgnored="false"  기본값이 false 이르므로, 기본적으로 표현언어가 표시됩니다. -->
		 
<%  request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 출력창</title>
</head>
<body>
	<table style="border:1 grey solid; margin:auto;" >
		<tr align="center" bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%" ><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
		</tr>
		<tr align="center">
			<td>${member1.id} </td>
			<td>${member1.password} </td>
			<td>${member1.name} </td>
			<td>${member1.email}</td>
		</tr>
		<tr align="center">
			<td>${member2.id} </td>
			<td>${member2.password} </td>
			<td>${member2.name} </td>
			<td>${member2.email}</td>
		</tr>
	</table>
</body>
</html>