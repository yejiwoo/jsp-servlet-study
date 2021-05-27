<%@ page language="java" contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding( "utf-8" ); %>

  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결과출력</title>   
</head>
<body>
<%-- 
<%
	String userId = request.getParameter("userId");
	String userPw = request.getParameter("userPw");
%>  
<%
	if(userId==null || userId.length()==0){
	//if(userId.length()==0){ 	//if{} 처리됨
	//if(userId==""){      		//if{} 처리됨
	//if(userId==null){    		//else{}처리됨
%>
	아이디 또는 암호가 틀립니다.<br>
	아이디 또는 암호를 확인 후 다시 로그인 해주세요.
	<br><br>  
	<a href="/pro14/test04/login.jsp">다시 로그인하기</a>
<%
	}else{
%>
	<h1> 환영합니다. <%=userId %> 님!!!</h1>
<%
    }
%> --%>



	<c:if test="${(empty param.userId)||(empty param.userPw)}">
		아이디와 암호를 입력하세요.<br><br>
		아이디 또는 암호가 틀립니다.<br>
		아이디 또는 암호를 확인 후 다시 로그인 해주세요.<br><br>
		<a href="/pro14/test04/login.jsp">로그인 창</a>
	</c:if>
	<c:if test="${(not empty param.userId)&&(not empty param.userPw)}">
		<h2>환영합니다. ${param.userId}님!!!</h2>
		<h2>환영합니다. <c:out value="${param.userId}"/> 님!!!</h2>
		<a href="/pro14/test04/login.jsp">로그인 창</a>
	</c:if>



<!-- /pro12/result3.jsp 내용입니다. -->
<%-- 
<%
	String userId = request.getParameter("userId");
	String userPw = request.getParameter("userPw");
%> 	

<%  //아이디가 정상적으로 입력되었는지 검사합니다.
	if(userId == null || userId.length()==0){
%>
		아이디 또는 암호가 틀립니다.<br>
		아이디 또는 암호를 확인 후 다시 로그인 해주세요.
		<br><br>
		<a href="/pro14/test04/login.jsp">다시 로그인하기</a>
<%
	}else{
		if(userId.equals("admin")){
%>
			<h1>관리자로 로그인 했습니다.</h1>
			<form> <!-- 아이디가 관리자인 경우, 관리자 페이지를 표시합니다.  -->
				<input type=button value="회원정보 삭제하기"  />
				<input type=button value="회원정보 수정하기"  />
			</form>
			<br>
			<a href="/pro14/test04/login.jsp">"로그아웃"</a>
<%
		}else{
%>
			<h1> 환영합니다. <%=userId %> 님!!!</h1>
			<br>
			<a href="/pro14/test04/login.jsp">"로그아웃"</a>
<%
		}
	}
%>
 --%>

	<c:if test = "${(empty param.userId)||(empty param.userPw)}">
		아이디와 암호를 입력하세요.<br>
		<a href="/pro14/test04/login.jsp">로그인 창 </a>
	</c:if>
	
	<c:if test="${(not empty param.userId)&&(not empty param.userPw)}">
		<c:if test="${param.userId =='admin'}">
			<h1>관리자로 로그인 했습니다.</h1>
			<form>
				<input type=button value="회원정보 삭제하기" />
				<input type=button value="회원정보 수정하기" /><br><br>
			</form>
			<a href="/pro14/test04/login.jsp">로그인 창</a>
		</c:if>
		<c:if test="${param.userId !='admin'}">  
			<h1> 환영합니다. <c:out value="${param.userId}" /> 님!!!</h1><br><br>
			<h1> 환영합니다. ${param.userId}님!!!</h1><br><br>
			<a href="/pro14/test04/login.jsp">로그인 창</a>
		</c:if>
	</c:if>

</body>
</html>
