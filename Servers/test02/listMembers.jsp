<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false"
		 import="java.util.ArrayList"
		 import="java.util.List"
		 import="sec01.ex01.MemberVO" %>
		 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>  
<html>
<head>
	<meta  charset="UTF-8">
	<title>회원 정보 출력창</title>
	<style>
		table {margin: auto; border: 1px}
	    .cls1 {font-size:40px; text-align:center;}
	    .cls2 {font-size:20px; text-align:center;}
	</style>
</head>
<body>
	<p class="cls1">회원정보</p>
	<table>
		<tr align="center" bgcolor="lightgreen">
			<td width="7%" ><b>아이디</b></td>
			<td width="7%" ><b>비밀번호</b></td>
			<td width="7%" ><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%" ><b>가입일</b></td>
		</tr>
	
	<c:choose>
		<c:when test="${memsList==null}" >
			<tr>
			<td colspan=5>
			<b>등록된 회원이 없습니다.</b>
			</td>  
			</tr>
		</c:when>  
		<c:when test="${memsList != null}">
			<c:forEach var="mem" items="${memsList}">
				<tr align="center">
					<td>${mem.id}</td>
					<td>${mem.pwd}</td>
					<td>${mem.name}</td>     
					<td>${mem.email}</td>     
					<td>${mem.joinDate}</td>     
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
	</table>
	<hr>  
	<!-- 아래의 링크를 클릭하면, 일단 MemberController 서블릿이  -->
	<!-- 호출(매핑이름: /member/*)되고(포워드되고), -->
	<!-- 이 페이지의 request의 요청경로는 /memberForm.do가 설정되어 -->
	<!--  MemberController 서블릿의 getPathInfo() 메소드로 요청경로가 추출되어
	<!--  action 변수에 /memberForm.do 값이 저장됩니다.-->
	<a href="${contextPath}/member170201/memberForm.do"><p class="cls2">회원 가입하기</p></a>
</body>
</html>
