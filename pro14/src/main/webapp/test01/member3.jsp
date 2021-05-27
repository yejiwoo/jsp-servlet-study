<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="memberBean" class="sec01.ex01.MemberBean"/>
<jsp:setProperty name="memberBean" property="*"/>

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
		<tr align=align> <!-- 표현언어 이용 -->
			<td>${memberBean.id }</td>
			<td>${memberBean.password }</td>
			<td>${memberBean.name }</td>
			<td>${memberBean.email }</td>
		</tr>
		<tr align=align> <!-- 표현식 이용 -->
 			<td><%=memberBean.getId() %></td> 
 			<td><%=memberBean.getPassword() %></td>
			<td><%=memberBean.getName() %></td> 
 			<td><%=memberBean.getEmail() %></td> 
		</tr> 
	</table>
</body>
</html>