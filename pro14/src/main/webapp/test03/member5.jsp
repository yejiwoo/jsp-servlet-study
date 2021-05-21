<%@	page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
		  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>
  
<c:set var="id" value="hong" scope="page" />
<c:set var="password" value="1234" scope="page" />
<c:set var="name" value="${'홍길동'}" scope="page" />
<%-- <c:set var="name" value="${null}" scope="page" /> --%>
<c:set var="age" value="${22}" scope="page" />
<c:set var="height" value="${177}" scope="page" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 출력 창</title>
</head>
<body>
	<table style="margin: auto; border: 1 solid lightgrey; text-align:center;">
		<tr bgcolor="lightgreen">
	    	<td width="7%" ><b>아이디</b></td>
	      	<td width="7%" ><b>비밀번호</b></td>
	      	<td width="7%" ><b>이름</b></td>
	      	<td width="7%"><b>나이</b></td>
	      	<td width="7%" ><b>키</b></td>
		</tr>
		
		<!-- choose 내에서 필요한 경우, 제일 마지막에 otherwise를 추가할 수 있습니다. -->
		<!-- otherwise 다음에는 when을 사용할 수 없습니다. -->
		<!-- 오류 메시지일부:  Illegal "c:when" after "c:otherwise" tag in "c:choose" tag. -->

		<c:choose> 
			<%-- <c:when test="${name == null}"> --%>
			<c:when test="${empty name}"><!-- 권장 name 변수가 비어있으면 -->
				<tr align="center">
					<td colspan=5 >이름을 입력하세요!!</td> 
				</tr>
			</c:when>
<%-- 			
			<c:when test="${name != null}">
				<tr>
					<td>${id}</td>
					<td>${password}</td>
					<td>${name}</td>
					<td>${age}</td>
					<td>${height}</td>
				</tr>
			</c:when>
 --%>			
			<c:otherwise>
				<tr>
					<td>${id}</td>
					<td>${password}</td>
					<td>${name}</td>
					<td>${age}</td>
					<td>${height}</td>
				</tr>
			</c:otherwise>


		</c:choose>   
	</table>
</body>
</html>
