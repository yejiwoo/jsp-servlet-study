<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  />     

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입창</title>
	<style type="text/css">
	    /* * {border: 1px solid silver;} */
	    form {border: 1px solid silver;}
		table {margin: auto;}
		.clstd1 {width: 200px; align="right"}
		.clstd2 {width: 300px;}
		#p1 {text-align: right;padding-right: 10px; height: 30px;}
		input{height: 30px;}
	</style>
</head>
<body>
	<!-- 이 페이지가 호출되어 사용자가 값 입력 후, 가입하기를 클릭하면,  -->
	<!-- form에 설정된 action 속성의 값(/pro17/member/addMember.do) 값에 의하여
	<!-- MemberController 서블릿이 호출(매핑이름: /member/*)되고(포워드되고), -->
	<!-- 이 페이지의 request의 요청경로는 /addMember.do가 설정되어 -->
	<!--  MemberController 서블릿의 getPathInfo() 메소드로 요청경로가 추출되어
	<!--  action 변수에 /addMember.do 값이 저장됩니다.-->
	<form method="post" action="${contextPath}/member170201/addMember.do">
		<h1 style="text-align:center">회원 가입창</h1>
		<table>
			<tr>
				<td class="clstd1"><p id="p1">아이디</p></td>
				<td class="clstd2"><input type="text" name="id"></td>
			</tr>
			<tr>
				<td class="clstd1"><p id="p1">비밀번호</p></td>
				<td class="clstd2"><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td class="clstd1"><p id="p1">이름</p></td>
				<td class="clstd2"><input type="text" name="name"></td>
			</tr>
			<tr>
				<td class="clstd1"><p id="p1">이메일</p></td>
				<td class="clstd2"><input type="text" name="email"></td>
			</tr>
			<tr>
<!-- 			<td class="cls_td1"><p>&nbsp;</p></td>
				<td class="cls_td2"> -->
				<td colspan="2" align="center" height= "40px">
					<input type="submit" value="가입하기">&nbsp;
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
