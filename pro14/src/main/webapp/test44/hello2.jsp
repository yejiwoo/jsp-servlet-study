<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! String name="듀크";
	public String getName(){
		return name;
	}
%>
<%--<% String age=request.getParameter("age"); --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트릿 실습</title>
</head>
<body>
	<h2>안녕하세요. <%=name %>님!!</h2>
	<% String age=request.getParameter("age"); %>
	<h2>나이는 <%=age %>살입니다!</h2>
</body>
</html>