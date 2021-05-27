<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
		 import="java.util.List"
		 import="sec02.ex01.MemberDAO"
		 import="sec02.ex01.MemberBean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<%	request.setCharacterEncoding("UTF-8"); %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
	<table border="1" style="margin: auto;" >
		<tr align="center" bgcolor="lightgreen" >
			<td width="7%" ><b>아이디</b></td>
			<td width="7%" ><b>비밀번호</b></td>
			<td width="7%" ><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%" ><b>가입일</b></td>
		</tr>  
	<c:choose>
		<%--
		 ArrayList list =request.getAttribute("membersList");
		--%>
		<c:when test="${membersList==null}" >
			<tr>
				<td colspan=5>
				<b>등록된 회원이 없습니다.</b>
				</td>  
			</tr>
		</c:when>
		<c:otherwise>
		<%-- <c:when test="${membersList!= null}" > --%>
			<c:forEach  var="member" items="${membersList}" >
				<tr align="center">
					<td>${member.id }</td>
					<td>${member.password}</td>
					<td>${member.name}</td>     
					<td>${member.email}</td>     
					<td>${member.joinDate}</td>     
				</tr>
			</c:forEach>
		</c:otherwise>
		<%-- </c:when> --%>
		</c:choose>
	</table>
	<hr><hr>
	<table border="1" style="margin: auto;" >
		<tr align="center" bgcolor="lightgreen" >
			<td width="7%" ><b>아이디</b></td>
			<td width="7%" ><b>비밀번호</b></td>
			<td width="7%" ><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%" ><b>가입일</b></td>
		</tr>  
	<c:choose>
		<%--
		 ArrayList list =request.getAttribute("membersList");
		--%>
		<c:when test="${empty membersList}" >
			<tr>
				<td colspan=5>
				<b>등록된 회원이 없습니다.</b>
				</td>  
			</tr>
		</c:when>
		<c:otherwise>
		<%-- <c:when test="${membersList!= null}" > --%>
			<c:forEach  var="member" items="${membersList}" >
				<tr align="center">
					<td>${member.id }</td>
					<td>${member.password}</td>
					<td>${member.name}</td>     
					<td>${member.email}</td>     
					<td>${member.joinDate}</td>     
				</tr>
			</c:forEach>
		</c:otherwise>
		<%-- </c:when> --%>
		</c:choose>
	</table>
	<hr>
	<hr>
		<table border="1" style="margin: auto;" >
		<tr align="center" bgcolor="lightgreen" >
			<td width="7%" ><b>아이디</b></td>
			<td width="7%" ><b>비밀번호</b></td>
			<td width="7%" ><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%" ><b>가입일</b></td>
		</tr>  
	<c:choose>
		<%--
		 ArrayList list =request.getAttribute("membersList");
		--%>
		<c:when test="${not empty membersList}" >
			<c:forEach  var="member" items="${membersList}" >
				<tr align="center">
					<td>${member.id }</td>
					<td>${member.password}</td>
					<td>${member.name}</td>     
					<td>${member.email}</td>     
					<td>${member.joinDate}</td>     
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
		<%-- <c:when test="${membersList!= null}" > --%>
		<tr>
				<td colspan=5>
				<b>등록된 회원이 없습니다.</b>
				</td>  
			</tr>
		</c:otherwise>
		<%-- </c:when> --%>
		</c:choose>
	</table>
	  
</body>
</html>