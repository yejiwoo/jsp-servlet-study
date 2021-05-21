<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" 
		 import="java.util.Date" %>
		 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>  

<%
	Date date1 = new Date();
	System.out.println(date1);

	request.setAttribute("date1", date1);
%>

<%=request.getAttribute("date1") %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>포매팅 태그 라이브러리 예제</title>
</head>
<body>
     <h2>formatDate 예제</h2> 
     
<c:set var="now" value="<%=new Date() %>" />
	기본1:<fmt:formatDate value="${now}" /><br>
	기본2:<fmt:formatDate value="${now}" type="date"/><br>
	기본3:<fmt:formatDate value="${now}" type="time"/><br>
	기본4:<fmt:formatDate value="${now}" type="both"/><br>
	<hr>
	MEDIUM: <fmt:formatDate value="${now}" type="date" dateStyle="medium" /><br>
	FULL : <fmt:formatDate value="${now}" type="date" dateStyle="full"/><br>
	LONG: <fmt:formatDate value="${now}" type="date" dateStyle="long" /><br>
	SHORT: <fmt:formatDate value="${now}" type="date" dateStyle="short" /><br>
	<hr>
	MEDIUM: <fmt:formatDate value="${now}" type="both" dateStyle="medium" /><br>
	FULL : <fmt:formatDate value="${now}" type="both" dateStyle="full"/><br>
	LONG: <fmt:formatDate value="${now}" type="both" dateStyle="long" /><br>
	SHORT: <fmt:formatDate value="${now}" type="both" dateStyle="short" /><br>
	<hr>
	MEDIUM: <fmt:formatDate value="${now}" type="both" timeStyle="medium" /><br>
	FULL : <fmt:formatDate value="${now}" type="both" timeStyle="full"/><br>
	LONG: <fmt:formatDate value="${now}" type="both" timeStyle="long" /><br>
	SHORT: <fmt:formatDate value="${now}" type="both" timeStyle="short" /><br>
	<hr>
	
	FULL : <fmt:formatDate value="${now}" type="both" dateStyle="full"
						   timeStyle="full"  /><br>
	SHORT: <fmt:formatDate value="${now}" type="both" dateStyle="short"
						   timeStyle="short"  /><br>
						   
	한국 현재 시간:
	<fmt:formatDate value="${now}" type="both" /><br><br>
	<fmt:formatDate value="${now}" type="both" timeStyle="short"/><br><br>
	<fmt:formatDate value="${now}" type="both" dateStyle="full"  timeStyle="full"/><br><br>

	<!-- 시간대 변경  -->
	<fmt:timeZone value="America/New York" >
		뉴욕 현재 시간:	
		<fmt:formatDate value="${now}" type="both" /><br><br>
		<fmt:formatDate value="${now}" type="both" dateStyle="short"  timeStyle="short"/><br><br>
		<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
	</fmt:timeZone>

	<!-- pattern을 이용한 원하는 형식 지정 -->
	<fmt:formatDate  value="${now}" pattern="YYYY'년'MM-dd 'T:'HH:mm:ss" /><br>
	<fmt:timeZone value="America/New York" >
		<fmt:formatDate  value="${now}" pattern="YYYY-MM-dd HH:mm:ss" /><br>
	</fmt:timeZone>
     
    <hr><hr>



	<h2>fmt의 number 태그를 이용한 숫자 포맷팅 예제.</h2>

<c:set var="price" value="100000000" />

	<fmt:formatNumber value="${price}" /><br>
	<hr>
	<fmt:formatNumber type="currency" value="${price}"/><br>
	<fmt:formatNumber type="percent" value="${price}"/><br>
	<hr>
	통화로 표현 시1 : 
	<fmt:formatNumber type="currency" value="${price}"  groupingUsed="false"/><br>
	통화로 표현 시2 :
	<fmt:formatNumber type="currency" value="${price}" currencySymbol="KRW" /><br>
	퍼센트로 표현 시1 : 
	<fmt:formatNumber type="percent" value="${price}" groupingUsed="false"/><br>
	<hr>
	<fmt:formatNumber type="number" value="${price}" var="priceNumber1" groupingUsed="false"/>
	일반 숫자로 표현 시1: ${priceNumber1}<br>
	<fmt:formatNumber type="number" value="${price}" var="priceNumber2" />
	일반 숫자로 표현 시2 : ${priceNumber2}<br>
</body>
</html>
