<%@ page language="java" contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8" isELIgnored="false"
		 import="java.util.ArrayList"
		 import="java.util.HashMap"
		 import="sec01.ex01.MemberBean" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>    


<!-- membersMap, membersList 객체 생성  -->
<jsp:useBean  id="membersMap" class="java.util.HashMap" />
<jsp:useBean  id="membersList" class="java.util.ArrayList"  />
<%	
    membersMap.put("id", "park2");
    membersMap.put("password", "4321");
    membersMap.put("name", "박지성");
    membersMap.put("email", "park2@test.com");
    
	/* memberBean 객체 생성  */    
    MemberBean memberBean1 = new MemberBean("son", "1234", "손흥민", "son@test.com");
    MemberBean memberBean2 = new MemberBean("ki", "4321", "기성용", "ki@test.com");
    
    /* memberBean 객체를 membersList 객체에 추가 */
    membersList.add(memberBean1);
    membersList.add(memberBean2); 
    
    /* membersList 객체를  memsList 키로  membersMap 객체에  추가 */
    membersMap.put("memsList",  membersList);  
%>


<!DOCTYPE html>
<html>
<head>
	<meta  charset="UTF-8">
	<title>회원 정보 출력 창</title>
</head>
<body>
	<table style="margin: auto;" border="1" >
		<tr align="center" bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%" ><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
		</tr>
		<tr align="center">
			<td>${membersMap.id}</td>
			<td>${membersMap.password}</td>
			<td>${membersMap.name}</td>
			<td>${membersMap.email}</td> 
		</tr>
		
<!-- membersMap 객체의 memsList 키의 ArrayList 객체를  	-->
<!-- memList 변수에 저장 -->
<!-- 아래에서 표현언어로 설정된 membersMap.memsList 은 변수가 아니라 -->
<!-- HashMap 객체의 키이므로 가능 -->
<c:set var="memList1" value="${membersMap.memsList}"  />
		
		<tr align="center">
			<td>${memList1[0].id}</td>
			<td>${memList1[0].password}</td>
			<td>${memList1[0].name}</td>
			<td>${memList1[0].email}</td>
		</tr>
		<tr align="center">
			<td>${memList1[1].id}</td>
			<td>${memList1[1].password}</td>
			<td>${memList1[1].name}</td>
			<td>${memList1[1].email}</td>
		</tr>


<!-- membersMap 객체의 memsList 키의 ArrayList 객체를  	-->
<!-- memList2 변수에 저장 -->
<c:set var="memList2" value="${membersMap.memsList}"  />

<!-- memList2 변수에 있는 ArrayList 객체의 요소인 memberBean 객체를  -->
<!-- member 변수에 저장하여 반복  -->
	<c:forEach var="member" items="${memList2}" >
		<tr align="center">
			<td>${member.id}</td>
			<td>${member.password}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
		</tr>
	</c:forEach>
		
	</table>
</body>
</html>
