<%@ page language="java" contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("utf-8"); %>
	

<!DOCTYPE html>
<html>
<head>
	<title>점수 출력창</title>
	<meta charset="UTF-8">
</head>
<body>
<%-- 
<%	String strScore=request.getParameter("score"); %>
<%
	if(strScore==null || strScore.length()==0){
%>
		점수를 입력해 주십시오<br><br><br>
<%
	}else{
		int score=Integer.parseInt(strScore);
	
%>
		<h1>시험점수  <%=score %>점</h1><br>
<%
		if(score >= 90){
%>
			<h1>A학점입니다.</h1>
<%
		//}else if(score >= 80 && score < 90){
		}else if(score >= 80){
%>
			<h1> B학점입니다.</h1>
<%
		//}else if(score >= 70 && score < 80){
		}else if(score >= 70){
%>
			<h1> C학점입니다.</h1>
<%
		//}else if(score >= 60 && score < 70){
		}else if(score >= 60){
%>
			<h1> D학점입니다.</h1>
<%
		}else{
%>
			<h1> F학점입니다.</h1>
<%
		}
	}
%> --%>

	<c:if test = "${empty param.score}">
		점수(0~100 사이)를 입력세요.<br>
		<a href="/pro14/test04/scoreTest.jsp">점수 입력 창</a>
	</c:if>
	<c:if test = "${param.score >100 || param.score < 0}">
		0~100 사이의 점수를 입력세요.<br>
		<a href="/pro14/test04/scoreTest.jsp">점수 입력 창</a>
	</c:if>

	<c:set var="score" value="${param.score}" />
	
	<c:if test = "${not empty score && (score >= 0 && score <=100)}">
		
		<h1>시험점수  <c:out  value="${score}" /></h1><br>
		<c:choose>
			<c:when test="${score>=90 && score<100 }">
				<h1>A학점입니다.</h1>
			</c:when>    
			<c:when test="${score>=80}">
				<h1>B학점입니다.</h1>
			</c:when> 
			<c:when test="${score>=70}">
				<h1>C학점입니다.</h1>
			</c:when>
			<c:when test="${score>=60}">
				<h1>D학점입니다.</h1>
			</c:when>    
			<c:otherwise>	
				<h1>F학점입니다.</h1>
			</c:otherwise>    
		</c:choose>
		<a href="/pro14/test04/scoreTest.jsp">점수 입력 창</a>
	</c:if>    
	
	<br>
	<a href="/pro12/scoreTest.html">점수 입력창 표시</a>
</body>
</html>
