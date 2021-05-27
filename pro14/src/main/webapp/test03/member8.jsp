<%@ 
	page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false"
		 import="java.util.List"
		 import="java.util.ArrayList"
		 import="sec01.ex01.MemberBean" 
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%
   request.setCharacterEncoding("UTF-8");

	List<MemberBean> membersList = new ArrayList<MemberBean>();	
	MemberBean memberBean1 = new MemberBean("son","1234", "손흥민", "son@test.com");
	MemberBean memberBean2 = new MemberBean("ki","4321", "기성용", "ki@test.com");
	MemberBean memberBean3 = new MemberBean("park", "1212", "박지성", "park@test.com");
	membersList.add(memberBean1);
	membersList.add(memberBean2);
	membersList.add(memberBean3);
%>
<!-- 생성된 ArrayList 객체를 호출할 변수를, c:set 태그로 선언합니다. -->
<c:set var="memLst" value="<%= membersList%>" />


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 출력 창</title>
</head>
<body>
	<table border="1"  style="margin: center;">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="5%" ><b>이름</b></td>
			<td width="5%"><b>이메일</b></td>
		</tr>
		<!-- c:set 태그로 선언한 ArrayList 객체(membersList)의 변수이름(memLst)을 -->
		<!-- items 속성에 표현언어로 명시합니다. -->
		<!-- items 속성에, 표현언어로 ArrayList 객체이름을 직접명시하면 -->
		<!-- 오류는 없지만 값이 표시되지 않습니다. -->
	<c:forEach var="mem" items="${memLst}" >	
		<tr align="center">
			<td>${mem.id}</td>
			<td>${mem.pwd}</td>
			<td>${mem.name}</td>
			<td>${mem.email}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>
