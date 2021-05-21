<%@ 
	page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:url var="url1" value="/test01/member1.jsp">
	<c:param name="id" value="hong" />
	<c:param name="pwd" value="1234" />
	<c:param name="name" value="홍길동" />
	<c:param name="email" value="hong@test.com" />
	<c:param name="contextPath" value="${contextPath}" />
</c:url>

<c:url var="url2" value="/test01/member1.jsp" />
<c:url var="url3" value="https://www.naver.com/" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> c:url 태그 실습</title>
</head>
<body>
<%--아래의 코드가 url 태그에 설정된 정보를 통해 간단해 집니다.
	<a href='${contextPath}/test01/member1.jsp'>회원정보출력</a>
--%>
	<a href="${url1}">회원정보출력(url1)</a>
	<hr>
	<a href="${url2}">회원정보출력(url2)</a>
	<hr>
	<a href="${url3}">네이버(url3)</a>
</body>
</html>
