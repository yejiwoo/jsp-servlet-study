<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"
		 import="sec01.ex01.MemberBean"
		 import="java.util.ArrayList"
		 import="java.util.HashMap"
%>
<%	request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="memberBean" class="sec01.ex01.MemberBean" scope="page" />
<jsp:setProperty name="memberBean" property="*" />

<jsp:useBean  id="membersList" class="java.util.ArrayList" />
<jsp:useBean  id="membersMap" class="java.util.HashMap" />

<%	
   membersMap.put("id", "park2");
   membersMap.put("password", "4321");
   membersMap.put("name","박지성");
   membersMap.put("email","park2@test.com");
   
   MemberBean memBean = new MemberBean("son", "1234", "손흥민", "son@test.com");
   membersList.add(memberBean);
   membersList.add(memBean); 
   
   membersMap.put("membersList",  membersList);  
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 출력창</title>
</head>
<body>
	<table style="text-align: center; border: 1 solid ligthgrey"   >
		<tr bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%" ><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
		</tr>
		<tr align=center>
		      <td>${membersMap.id}</td>
		      <td>${membersMap.password}</td>
		      <td>${membersMap.name}</td>
		      <td>${membersMap.email }</td> 
		</tr>
		    <tr align=center>
		      <td>${membersMap.membersList[0].id}</td>
		      <td>${membersMap.membersList[0].password}</td>
		      <td>${membersMap.membersList[0].name}</td>
		      <td>${membersMap.membersList[0].email}</td>
		   </tr>
		   <tr align=center>
		      <td>${membersMap.membersList[1].id}</td>
		      <td>${membersMap.membersList[1].password}</td>
		      <td>${membersMap.membersList[1].name}</td>
		      <td>${membersMap.membersList[1].email}</td>
		</tr>
	</table>
</body>
