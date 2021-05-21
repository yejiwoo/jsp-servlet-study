<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%	request.setCharacterEncoding("utf-8"); %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>여러 가지 문자열 함수 기능</title>
</head>
<body>
<c:set var="title1" value="   hello world!   " />
<%-- <c:set var="title2" value="열심히 공부합시다." /> --%>
<c:set var="title2" value="열심히 공부합시다. java 여러분. java jsp jstl java jsp" />
<c:set var="str1"   value="java" />
	
	<h2>여러 가지 문자열 함수 기능</h2>
	
	title1="   hello world!   "<br>
	title2="쇼핑몰 중심 JSP 입니다.!"<br>
	str1="중심"<br><br>
	
	fn:length(title1)글자수=${fn:length(title1) } <br>
	fn:length(title2)글자수=${fn:length(title2) } <br>
	fn:toUpperCase(title1)=${fn:toUpperCase(title1)}<br>
	fn:toLowerCase(title1)=${fn:toLowerCase(title2)}<br><br>
	
	fn:substring(title1,3,6)=${fn:substring(title1,3,6)}<br>
	fn:trim(title1)=${fn:trim(title1)}<br>
	fn:replace(title2,"java","servlet")=${fn:replace(title2,"java","servlet")}<br><br>
	

<h2>split 예제</h2>

      <c:set var = "string1" value = "This is first String."/>
      <c:set var = "string2" value = "${fn:split(string1, ' ')}" />
      <c:set var = "string3" value = "${fn:join(string2, '-')}" />

		${string2}

      <p>String (3) : ${string3}</p>

      <c:set var = "string4" value = "${fn:split(string3, '-')}" />
      <c:set var = "string5" value = "${fn:join(string4, ' ')}" />

      <p>String (5) : ${string5}</p>

	
<%-- fn:split(title2,"java")=${fn:toString({fn:split(title2,"java")})}<br><br> --%>

fn:split(title2,"java")=${fn:split(title2,"java")}<br><br>
	
	<c:set var="strs" value="${fn:split(title2,'java')}" />
	
	<c:forTokens var="str" items="${strs}" delims="">
		${str}<br>
	</c:forTokens>
	<hr>
	
	<c:set var="strs" value="${fn:split(title2,' ')}" />
	
	<c:forEach var="str" items="${strs}">
		${str}<br>
	</c:forEach>

<br>
	
<c:set var="title2" value="열심히 공부합시다. java 여러분. java jsp jstl java jsp" />
<c:set var="str1"   value="java" />

	fn:indexOf(title2,str1)=${fn:indexOf(title2,str1) }<br>
	fn:contains(title1,str1)=${fn:contains(title1,str1) }<br>
	fn:contains(title2,str1)=${fn:contains(title2,str1) }<br>
</body>
</html>
