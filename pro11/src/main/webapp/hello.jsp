<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 요청에대한 인코딩은 설정할 수 있는 속성이 없으므로 아래처럼 코드로 명시해야 한다. -->
<% request.setCharacterEncoding("utf-8"); %>
    

<%-- <%@include file="./include.jsp" %> --%><!-- 상대경로 -->
<%@include file="include.jsp" %> <!-- 절대경로 -->

	<h1>hello JSP!!</h1>
	<h1>JSP 실습입니다!</h1>
</body>
</html>