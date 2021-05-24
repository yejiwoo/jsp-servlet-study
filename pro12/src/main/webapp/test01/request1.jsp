<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="javax.servlet.RequestDispatcher"
    pageEncoding="UTF-8"%>
    
<%
	// 데이터를 HttpServletRequest타입 객체인 request에 바인딩
	request.setAttribute("name","이순신");
	request.setAttribute("address","서울시 강남구");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

/**
*** request객체를 다른 jsp로 포워딩
**/

// 아래 3개 다 가능 ("test01/request2.jsp"는 안 됨)
// RequestDispatcher dispatch=request.getRequestDispatcher("./request2.jsp");
// RequestDispatcher dispatch=request.getRequestDispatcher("request2.jsp");
RequestDispatcher dispatch=request.getRequestDispatcher("/test01/request2.jsp");
dispatch.forward(request, response);
%>
</body>
</html>