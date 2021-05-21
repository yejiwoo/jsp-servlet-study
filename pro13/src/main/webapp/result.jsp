<%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8" %>
    
<% request.setCharacterEncoding("utf-8");
	String msg="아이디를 입력하지 않았습니다. 아이디를 입력해 주세요.";
%>
 
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>결과창</title>
</head>
<body>
<%--
<%
   String userId = request.getParameter("userId");
   if(userId.length() == 0 && userId == ""){
      //request.getRequestDispatcher("매핑이름 또는 JSP 파일이름");
      //컨텍스트 이름이 포함되면 않됩니다.
      RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");  
      dispatcher.forward(request, response);
   }
%>
--%>


<%
   String userId = request.getParameter("userId");
   if(userId.length() == 0 && userId == ""){
%>
   <!-- page 속성의 값에 경로를 포함시킬 경우, root는 /pro13입니다. -->
   <!-- 즉, 컨텍스트 이름을 포함하면 안됩니다. -->
   <!-- 아래는 잘못된 경로 지정입니다. -->
   <%-- <jsp:forward page="/pro13/test02/login.jsp" /> --%>
   
   <%-- <jsp:forward page="test02/login.jsp" /> --%> 
   <jsp:forward page="/login.jsp" >
   	<jsp:param name="msg" value="<%=msg %>" />
   </jsp:forward>
<%   
   }
%>
   <h1>환영합니다   <%= userId %>님!! </h1>
</body>
</html>