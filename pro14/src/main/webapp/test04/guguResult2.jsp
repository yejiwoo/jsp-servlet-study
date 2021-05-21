<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>구구단 출력창</title>
</head>
<body>
<%-- 
<% int dan = Integer.parseInt(request.getParameter("dan")); %>

	<table border="solid 1px black">
		<tr bgcolor="lightgray" align="center"> 
			<td colspan="2"><%=dan %> 단 출력  </td>
		</tr>
<%
		for(int i=1; i<10;i++){
			int result = i * dan;
%>
<%
			if(i%2==1){  
%>
				<tr bgcolor="lightyellow" align="center">
<%
			} else {
%>
				<tr bgcolor="lightgreen" align="center">
<%
			}
%>
				<td width="400"> 
					<%=dan %> * <%=i %>    
				</td>
				<td width="400">
					<%=i*dan %>
				</td>
			</tr>
<%
		}
%>
	</table> --%>

<c:set var="dan" value="${param.dan}" />

 	<table border="1" style="margin: auto;width: 800;">
		<tr align="center" bgcolor="lightgreen"> 
			<td colspan="2">
				<c:out value="${dan}" />단 출력  
			</td>
		</tr>
		<c:forEach var="i"  begin="1" end="9" step="1"  >
			<c:if test="${i%2==0}">    
				<tr align="center" bgcolor="#CCFF66">
					<td width="400"> 
						<c:out value="${dan}" /> * <c:out value="${i}" />    
					</td>
					<td width="400">
						<c:out value="${i*dan}" />
				</tr>
			</c:if>
			<c:if test="${i%2==1}">    
				<tr align="center" bgcolor="#CCCCFF">
					<td width="400"> 
						<c:out value="${dan}" /> * <c:out value="${i}" />    
					</td>
					<td width="400">
						<c:out value="${i*dan}" />
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<hr>
 	<table border="1" style="margin: auto;width: 800;">
	<tr align="center" bgcolor="lightgreen"> 
		<%-- <td colspan="2"><c:out value="${dan}" />단 출력  </td> --%>
		<td colspan="2">${dan+1}단 출력  </td>
	</tr>
	<c:forEach var="i"  begin="1" end="9" step="1"  >
		<c:if test="${i%2==0}">    
			<tr align="center" bgcolor="#CCFF66">
		</c:if>
		<c:if test="${i%2==1}">    
			<tr align="center" bgcolor="#CCCCFF">
		</c:if>
				<td width="400"> 
					<c:out value="${dan+1}" /> * <c:out value="${i}" />    
				</td>
				<td width="400">
					<c:out value="${i*(dan+1)}" />
				</td>
			</tr>
	</c:forEach>
	</table>
	<hr>
 	
	<table border="1" style="margin: auto;width: 800;">
	<tr align="center" bgcolor="lightgreen"> 
		<%-- <td colspan="2"><c:out value="${dan+2}" />단 출력  </td> --%>
		<td colspan="2">${dan+2}단 출력  </td>
	</tr>
	<c:forEach var="i"  begin="1" end="9" step="1"  >
	<c:choose>
		<c:when test="${i%2==0}">    
			<tr align="center" bgcolor="#CCFF66">
				<td width="400"> 
					<c:out value="${dan+2}" /> * <c:out value="${i}" />    
				</td>
				<td width="400">
					<c:out value="${i*(dan+2)}" />
				</td>
			</tr>
		</c:when>
		<c:otherwise>    
			<tr align="center" bgcolor="#CCCCFF">
				<td width="400"> 
					<c:out value="${dan+2}" /> * <c:out value="${i}" />    
				</td>
				<td width="400">
					<c:out value="${i*(dan+2)}" />
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
	</c:forEach>
	</table>

	<hr>
	<a href="/pro14/test04/gugu.jsp">단 입력 페이지로 돌아가기</a>
</body>
</html>
