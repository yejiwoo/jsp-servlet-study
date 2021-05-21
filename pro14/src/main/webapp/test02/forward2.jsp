<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" 
		 import="sec01.ex01.MemberBean" %>
		 
<%	request.setCharacterEncoding("utf-8"); %>

<%
	MemberBean memberBean = new MemberBean("lee", "1234", "이순신", "lee@test.com");
%>
	<jsp:useBean id="memBean" class="sec01.ex01.MemberBean" scope="page" />
<%--	<jsp:setProperty name="memBean" property="id" value='<%="cha" %>'/>
		<jsp:setProperty name="memBean" property="password" value='<%="1234" %>'/>
		<jsp:setProperty name="memBean" property="name" value='<%="차범근"%>'/>
 		<jsp:setProperty name="memBean" property="email" value='<%="cha01@goott.com"%>'/> --%>
		<jsp:setProperty name="memBean" property="id" value="${'cha'}"/>
		<jsp:setProperty name="memBean" property="password" value='${"1234"}'/>
		<jsp:setProperty name="memBean" property="name" value='${"차범근"}'/>
		<jsp:setProperty name="memBean" property="email" value='${"cha01@goott.com" }'/>
		

<%
	request.setAttribute("member1", memberBean);
	request.setAttribute("member2", memBean);
%>
    
<html>
<head>
	<meta charset="UTF-8">
	<title>forward2</title>
<body>
	<jsp:forward page="/test02/member2.jsp" />
</body>
</html>
