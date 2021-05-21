<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 스크립트릿 안의 일부내용을 JSP 주석으로 처리할 수는 없습니다. -->

<!-- 스크립트릿 안의 내용은 자바주석으로 주석처리 합니다. -->
<!-- 스크립트릿 전체 내용을 주석처리할 때 JSP 주석으로만 처리합니다 -->

<%--<%! 
	String name="이순신";
	public String getName(){
		return name;
	}
%> --%>

<% String age=request.getParameter("age");%>

<%! 
	String name="이순신";
	public String getName(){
		return name;
	}
%>

<!-- HTML 주석입니다. HTML 주석안에 포함된 스크립트릿의 내용은 주석으로 처리되지 않습니다.-->

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