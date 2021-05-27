<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" 
		 import="java.util.List"
		 import="java.util.ArrayList"
		 import="sec01.ex01.MemberBean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%	request.setCharacterEncoding("UTF-8"); %>

<!-- 아래의 membersList 를 useBean 액션태그로 생성하면 표현언어에서 c:set 변수선언없이 사용할 수 있ㅅ브니다 -->

<%-- <jsp:useBean id="membersList" class="java.util.ArrayList"/> --%>

<%
	List<MemberBean> membersList = new ArrayList<MemberBean>();	

	MemberBean memberBean1 = new MemberBean("park", "1212", "박지성", "park@test.com");
	MemberBean memberBean2 = new MemberBean("ki","4321", "기성용", "ki@test.com");
	MemberBean memberBean3 = new MemberBean("son","1234", "손흥민", "son@test.com");
	
	membersList.add(memberBean1);
	membersList.add(memberBean2);
	membersList.add(memberBean3);
	
	//request.setAttribute("membersList", membersList);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 출력 창</title>
</head>
<body>
	<table style="margin:center; text-align:center;" border=1 >
		<tr bgcolor="lightgreen">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="5%" ><b>이름</b></td>
			<td width="5%"><b>이메일</b></td>
		</tr>

<c:set var="membersList" value="<%=membersList %>"/>

<%-- 위의 코드를 아래처럼 작성하면 될까요 ? --%>
<%-- <c:set var="membersList" value="${membersList}"/> --%> 

<%--
	<c:forEach var="i" begin="0" end="2" step="1" >
	개수를 알면 위처럼 작성할 수 있지만 개수를 모르면 아래처럼 작성해야 됩니다. --%>
		
<%-- 	<c:forEach var="i" begin="0" end="${membersList.size()-1}" step="1" >
	 	<tr>
			<td>${membersList[i].id}</td>
			<td>${membersList[i].password}</td>
			<td>${membersList[i].name}</td>
			<td>${membersList[i].email}</td>
		</tr>
	</c:forEach> --%>

	<c:forEach var="member" items="${membersList}" >
		<tr>
			<td>${member.id}</td>
			<td>${member.password}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
		</tr>
	</c:forEach>

	</table>
</body>
</html>
