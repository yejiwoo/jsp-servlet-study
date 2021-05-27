<%@ page language="java" contentType="text/html; charset=UTF-8"  
		 pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인창</title>
</head>
<body>
	<form action="result.jsp">
		<input type="text" size=20 placeholder="아이디를 입력하세요" name="id"/><br>
		<input type="password"  size=20 placeholder="암호를 입력하세요" name="password"/><br>
		<input  type="submit" value="로그인" /> <input type="reset" value="다시입력"  />
	</form> 
	
	<br><br>
	
	<h1><%=request.getContextPath() %></h1>    <!-- /pro14  -->
	
	<h1>${pageContext.request.contextPath}</h1><!-- /pro14  -->
	
	<a href="http://172.16.5.31:8080/pro14/test01/memberForm.jsp">회원가입하기</a><br><br>
	<a href="http://localhost:8080/pro14/test01/memberForm.jsp">회원가입하기</a><br><br>
	<a href="/pro14/test01/memberForm.jsp">회원가입하기</a><br><br>
	<a href="<%=request.getContextPath() %>/test01/memberForm.jsp">회원가입하기</a><br><br> 
	<a href="${pageContext.request.contextPath}/test01/memberForm.jsp">회원가입하기</a><br>
</body>
</html>
