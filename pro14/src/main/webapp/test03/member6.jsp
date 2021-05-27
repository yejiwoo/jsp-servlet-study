<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" 
		 import="java.util.List"
		 import="java.util.ArrayList" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>

<%
	List<String> dataList = new ArrayList<String>();
	dataList.add("hello");
	dataList.add("world");
	dataList.add("안녕하세요!!");
	//request.setAttribute("dataList", dataList);
%>

<c:set var="dataList" value="<%=dataList %>" />
<%-- <c:set var="dataList" value="${dataList}" /> --%>
	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>반복문 실습</title>
</head>
<body>
	<c:forEach  var="i" begin="4" end="6" step="1"  varStatus="loop">
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${loop.count}: ${loop.count} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${loop.first}: ${loop.first} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${loop.last}: ${loop.last} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${loop.current}: ${loop.current} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${loop.index}: ${loop.index} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${loop.begin}: ${loop.begin} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${loop.end}: ${loop.end} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${loop.step}: ${loop.step} <br>
	</c:forEach>
	<hr>
	<c:forEach  var="i" begin="1" end="10" step="2" varStatus="status">
		5 * ${i} = ${5*i}<br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${status.count}: ${status.count} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${status.first}: ${status.first} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${status.last}: ${status.last} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${status.current}: ${status.current} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${status.index}: ${status.index} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${status.begin}: ${status.begin} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${status.end}: ${status.end} <br>
		i=  ${i}   &nbsp;&nbsp;&nbsp;  \${status.step}: ${status.step} <br>
	</c:forEach>
	<hr>
	<c:forEach  var="data" items="${dataList}" varStatus="status" >
		${data} <br>
		&nbsp;&nbsp;&nbsp;  \${status.count}: ${status.count} <br>
		&nbsp;&nbsp;&nbsp;  \${status.first}: ${status.first} <br>
		&nbsp;&nbsp;&nbsp;  \${status.last}: ${status.last} <br>
		&nbsp;&nbsp;&nbsp;  \${status.current}: ${status.current} <br>
		&nbsp;&nbsp;&nbsp;  \${status.index}: ${status.index} <br>
		&nbsp;&nbsp;&nbsp;  \${status.begin}: ${status.begin} <br>
		&nbsp;&nbsp;&nbsp;  \${status.end}: ${status.end} <br>
		&nbsp;&nbsp;&nbsp;  \${status.step}: ${status.step} <br> 
	</c:forEach>
	<hr>
	
<c:set var="fruits" value="사과:     파인애플:  바나나: 		망고:     귤"  />
	
	<c:forTokens  var="fruit" items="${fruits}" delims=":" varStatus="status" >
		${fruit} <br>
		&nbsp;&nbsp;&nbsp;  \${status.count}: ${status.count} <br>
		&nbsp;&nbsp;&nbsp;  \${status.first}: ${status.first} <br>
		&nbsp;&nbsp;&nbsp;  \${status.last}: ${status.last} <br>
		&nbsp;&nbsp;&nbsp;  \${status.current}: ${status.current} <br>
		&nbsp;&nbsp;&nbsp;  \${status.index}: ${status.index} <br>
		&nbsp;&nbsp;&nbsp;  \${status.begin}: ${status.begin} <br>
		&nbsp;&nbsp;&nbsp;  \${status.end}: ${status.end} <br>
		&nbsp;&nbsp;&nbsp;  \${status.step}: ${status.step} <br> 
	</c:forTokens>
</body>
</html>
