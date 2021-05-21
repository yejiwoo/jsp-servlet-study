<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>

<!-- core 태그 라이브러리를 사용하기 위하여 taglib 디렉티브를 설정합니다. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>  


<% 
	String myAddr = "서울시 강남구"; 
	request.setAttribute("address", myAddr);
%>

<c:set var="id" value="hong" scope="page" />
<c:set var="password" value="1234" scope="page" />
<c:set var="name" value="${'홍길동'}"  scope="page" />
<%-- <c:set var="name" value="${null}" scope="page" /> --%>
<c:set var="age" value="${22}" scope="page" />
<c:set var="height" value="${177}" scope="page" />
<%-- <c:set var="address" value="${id}" scope="page" /> --%><!-- c:set으로 먼저 선언된 변수를 value 속성에 지정할 수 있습니다. -->
<c:set var="address" value="${myAddr}" scope="page" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 출력창</title>
</head>
<body>
	<table style="margin-left: auto; margin-right: auto;" border="1" >
	<!-- <table style="margin: auto;" border="1"  > -->
		<tr align="center" bgcolor="lightgreen" >
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>나이</b></td>
			<td width="7%"><b>키</b></td>
			<td width="7%"><b>주소</b></td>
		</tr>
		<tr align="center">
		<!-- 표현언어로 변수의 값 표시 -->
			<td>${id}</td>
			<td>${password}</td>
			<td>${name}</td>
			<td>${age}</td>
			<td>${height}</td>
			<td>${address}</td>
		</tr>
	</table>
</body>
</html>
