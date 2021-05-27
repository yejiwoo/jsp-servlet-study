<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("utf-8"); %>
   
<!DOCTYPE html>  
<html>
<head>
	<meta charset="UTF-8">
	<title>구구단 출력창</title>
</head>
<body>
<%-- 
<%	int dan = Integer.parseInt(request.getParameter("dan")); %>

	<table border=1 >
		<tr align=center bgcolor="#FFFF66"> 
			<td colspan=2><%= dan %>단 출력  </td>
		</tr>
<%
		for(int i=1; i<10;i++){
			int result = i * dan; 
%> 
		<tr align="center"> 
			<td width="200"> 
				<%= dan %> * <%=i %>    
			</td>
			<td width="200">
				<%= result %>
			</td>
		</tr>
<%
		}
%>
	</table> --%>

<c:set var="dan" value="${param.dan}" />

	<table border="1" style="margin: auto;width: 800;">
		<tr align="center" bgcolor="lightgreen"> 
			<td colspan="2"><c:out value="${dan}" />단 출력  </td>
		</tr>
		
		<c:forEach var="i"  begin="1" end="9" step="1"  >  
			<tr align="center"> 
				<td width="400"> 
					${dan} * ${i}
					<%-- <c:out value="${dan}" /> * <c:out value="${i}" /> --%>
				</td>
				<td width="400">
					${i*dan}
					<%-- <c:out value="${i*dan }" /> --%>
				</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<a href="/pro14/test04/gugu.jsp">단 입력 페이지로 돌아가기</a>
</body>
</html>
