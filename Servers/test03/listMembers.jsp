<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false" 
		 import="java.util.List"
		 import="sec02.ex02.MemberVO" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> <!-- /pro17  -->
  
<!DOCTYPE html>  
<html>
<head>
	<!-- 회원 추가 수정 삭제 작업 후, 컨트롤러에서 전송한 -->
	<!-- msg 값에 따라 작업 결과 메시지를 브라우저 경고창에 표시합니다. -->
	<c:choose>
		<c:when test='${msg=="memAddedSuccess" }'>
			<script>
				window.onload=function(){
					alert("회원을 등록했습니다.");
				}
			</script>
		</c:when>
		<c:when test='${msg=="memModifiedSuccess" }'>
			<script>
				window.onload=function(){
					alert("회원 정보를 수정했습니다.");
				}
			</script>
		</c:when>
		<c:when test= '${msg=="memDeletedSuccess" }'>
			<script>
				window.onload=function(){
					alert("회원 정보를 삭제했습니다.");
				}
			</script>
		</c:when>
	</c:choose>
	
	<meta charset="UTF-8">
	<title>회원 정보 출력창</title>

</head>
<body>
	<p class="cls1">회원정보</p>
	<table>
		<tr align="center" bgcolor="lightgreen">
			<td width="7%" ><b>아이디</b></td>
			<td width="7%" ><b>비밀번호</b></td>
			<td width="7%" ><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%" ><b>가입일</b></td>
			<td width="7%" ><b>수정</b></td>
			<td width="7%" ><b>삭제</b></td>
		</tr>
	<c:choose>
		<c:when test="${memsList == null}" >
			<tr>
				<td colspan=5>
					<b>등록된 회원이 없습니다.</b>
				</td>
			</tr>
		</c:when>  
		<c:when test="${memsList != null}" >
			<c:forEach var="mem" items="${memsList}" >
				<tr align="center">
					<td>${mem.id}</td>
					<td>${mem.pwd}</td>
					<td>${mem.name}</td>
					<td>${mem.email}</td>
					<td>${mem.joinDate}</td>
					<!-- 맴버ID를 전달해 수정과 삭제를 요청합니다. -->
						<!-- 아래의 링크를 클릭하면, 일단 MemberController 서블릿이  -->
						<!-- 호출(매핑이름:/member170202/*.do)되고(포워드되고), -->
						<!-- 이 페이지의 request의 요청경로는 /modmemberForm.do가 설정되어 -->
						<!-- MemberController 서블릿의 getPathInfo() 메소드로 요청경로가 추출되어
						<!-- action 변수에 /modmemberForm.do 값이 저장됩니다.-->
						<!-- action 변수값이 /modmemberForm.do 일 때 해당되는 부분이 실행되어 맴버 수정이 수행됩니다.  -->
						<td><form method="post" action="${contextPath}/member170202/modMemberForm.do">
								<input type="hidden" name="id" value="${mem.id}" />
								<input type="submit" value="수정">
							</form>
						</td>
					<%-- <td><a href="${contextPath}/member170202/modMemberForm.do?id=${mem.id}">수정</a></td> --%>
						<!-- 아래의 링크를 클릭하면, 일단 MemberController 서블릿이  -->
						<!-- 호출(매핑이름: /member/*.do)되고(포워드되고), -->
						<!-- 이 페이지의 request의 요청경로는 /delMember.do가 설정되어 -->
						<!-- MemberController 서블릿의 getPathInfo() 메소드로 요청경로가 추출되어
						<!-- action 변수에 /delMember.do 값이 저장됩니다.-->
						<!-- action 변수 값이 /delMember.do 일 때 해당되는 부분이 실행되어 맴버 삭제가 수행됩니다.  -->
						
						<td><form method="post" action="${contextPath}/member170202/delMember.do">
								<input type="hidden" name="id" value="${mem.id}" />
								<input type="submit" value="삭제" />
							</form>
						</td>
		   			<%-- <td><a href="${contextPath}/member170202/delMember.do?id=${mem.id}">삭제</a></td> --%>
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
	</table><br>  
	<!-- 아래의 링크를 클릭하면, 일단 MemberController 서블릿이  -->
	<!-- 호출(매핑이름: /member/*)되고(포워드되고), -->
	<!-- 이 페이지의 request의 요청경로는 /memberForm.do가 설정되어 -->
	<!-- MemberController 서블릿의 getPathInfo() 메소드로 요청경로가 추출되어
	<!-- action 변수에 /memberForm.do 값이 저장됩니다.-->
	<%-- <div class="cls2"><a href="${contextPath}/member170202/memberForm.do">회원 가입하기</a></div> --%>
	<form class="cls2" method="post" action="${contextPath}/member170202/memberForm.do">
		<input type="submit" value="회원가입" width="400px" />
	</form>
</body>
</html>










