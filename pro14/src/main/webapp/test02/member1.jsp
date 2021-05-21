<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>
		 
<%   request.setCharacterEncoding("UTF-8"); %>

<%
// 표현식으로 값을 표시하려면, 아래의 코드로 변수에 할당해야 합니다.
/* String id= (String) request.getAttribute("id");
   String pwd= (String) request.getAttribute("pwd");
   String name= (String) session.getAttribute("name");
   String email= (String) application.getAttribute("email"); 
*/

%>   
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
<%--
		<tr align="center">
			<td><%=id %></td>
			<td><%=pwd %></td>
			<td><%=name %></td>
			<td><%=email %></td>
		</tr>
--%>
		<tr align="center">
		<!-- 속성이름을 이용한 표현언어로 바인딩한 값을 표시합니다. -->
			<td>${id}</td>
			<td>${pwd}</td>
			<td>${name}</td>
			<td>${email}</td>
		</tr>
		<tr align="center">
		<!-- 표현언어의 내장객체를 이용한 속성이름으로 바인딩한 값을 표시합니다. -->
			<td>${requestScope.id}</td>
			<td>${requestScope.pwd}</td>
			<td>${sessionScope.name}</td>
			<td>${applicationScope.email}</td>
		</tr>
	</table>
</body>
</html>