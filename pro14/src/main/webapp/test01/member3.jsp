<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>

<% request.setCharacterEncoding("UTF-8"); %>

    
<jsp:useBean id="memberBean" class="sec01.ex01.MemberBean" scope="page" />
<!-- 회원가입입력폼의 input 이름들과 자바빈의 필드명이 동일합니다. -->
<jsp:setProperty name="memberBean" property="*" />

<html>
<head>
	<meta  charset="UTF-8">
	<title>회원 정보 출력창</title>
</head>
<body>
	<table style="margin:auto" border="1" >
		<tr align="center" bgcolor="#99ccff">
			<td width=20%><b>아이디</b></td>
			<td width="20%" ><b>비밀번호</b></td>
			<td width="20%" ><b>이름</b></td>
			<td width="20%" ><b>이메일</b></td>		
		</tr>
		<tr align="center">
			<!-- JSP표현식 태그를 이용한 값 표시 -->
			<td><%=memberBean.getId() %> </td>
			<td><%=memberBean.getPassword() %></td>
			<td><%=memberBean.getName() %></td>
			<td><%=memberBean.getEmail() %></td>
		</tr>
		<tr align="center">
			<!-- 표현언어를 이용한 값 표시. -->
			<td>${memberBean.id} </td>
			<td>${memberBean.password} </td>
			<td>${memberBean.name}</td>
			<td>${memberBean.email}</td>
		</tr>
	</table>
</body>
</html>
