<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! 
	String name="이순신";
	public String getName(){
		return name;
	}
%>
<% String age=request.getParameter("age");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현식 연습</title>
</head>
<body>
	<h2>안녕하세요 <%=name %>님!!</h2>
	<h2>나이는 <%=age %>살입니다.</h2>
	<h2>키는 <%=180 %>cm 입니다!!</h2>
	<h2>10년 후 나이는 <%=Integer.parseInt(age)+10 %>살입니다.</h2>
	<h2><%=10+"20" %></h2>
	<h2><%="10"+"20" %></h2>
	<h2><%="10"+"년" %></h2>
	<h2><%=10+"년" %></h2>
	<h2><%="년"+10 %></h2>
</body>
</html>