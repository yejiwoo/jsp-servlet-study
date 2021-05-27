<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"
		 import="sec01.ex01.MemberBean"
		 import="java.util.ArrayList" %>
		 
<%	request.setCharacterEncoding("UTF-8"); %>

<!-- scope 속성을 생략하면, 자동으로 scope 속성의 값이 page 로 설정됩니다. -->
	<jsp:useBean id="memberBean" class="sec01.ex01.MemberBean" scope="page" />
<!-- 회원가입입력폼의 input 이름들과 자바빈의 필드명이 동일합니다. -->
	<jsp:setProperty name="memberBean" property="*" />

<!-- ArrayList 객체 생성 -->
	<jsp:useBean id="membersList" class="java.util.ArrayList" scope="page"/>

<%
	//직접 생성한 bean 객체
	MemberBean memBean = new MemberBean("son", "1234", "손흥민", "son@test.com");
	membersList.add(memberBean); /* membersList[0] */
	membersList.add(memBean);    /* membersList[1] */
%>
<!DOCTYPE html>
<html>
<head>
	<meta  charset="UTF-8">
	<title>회원 정보 출력창</title>
</head>
<body>

<p>표현언어를 이용 시에 자바변수의 값을 표시할 수 없습니다.</p>
<% String strVal = "신상현"; %>
<p>JSP 기본 태그를 이용한 값표현: <strong><%=strVal %></strong></p>
<p>표현언어를 이용한 값표현: ${strVal}</p> 	<!-- 표현언어를 이용하여 선언된 자바변수인 strVal의 값을 표시할 수 없습니다.  -->
					<!-- 이 사항은 JSTL의 커스텀태그에서 해결합니다. -->
					
<% request.setAttribute("strVal", strVal); %>
<p>표현언어를 이용한 값표현2(request에 바인딩): <strong>${strVal}</strong></p>





	<table style="text-align: center; border: 1 solid ligthgrey"   >
		<tr bgcolor="#99ccff">
			<td width="20%"><b>아이디</b></td>
			<td width="20%"><b>비밀번호</b></td>
			<td width="20%" ><b>이름</b></td>
			<td width="20%"><b>이메일</b></td>
		</tr>
  		
		<tr>
			<td>${membersList[0].id}</td>  <!-- ${membersList[0].id}  -->
			<td>${membersList[0].password}</td>
			<td>${membersList[0].name}</td>
			<td>${membersList[0].email}</td>
		</tr>
		<tr >
			<td>${membersList[1].id}</td>
			<td>${membersList[1].password}</td>
			<td>${membersList[1].name}</td>
			<td>${membersList[1].email}</td>
		</tr> 


<!-- 위의 코드를 for 문으로 반복되도록 아래처럼 작성한 경우, 오류는 없지만, -->
<!-- 표헌언어에서는 변수 i 값을 가져올 수 없으로므로                                -->
<!-- 실행 시에 마무것도 표시되지 못합니다.                          -->
<!-- 이 문제는 JSTL에서 해결합니다. -->
<%
		for (int i=0; i<membersList.size();i++){
%>
		<tr>
			<td>${membersList[i].id}</td>  
			<td>${membersList[i].password}</td>
			<td>${membersList[i].name}</td>
			<td>${membersList[i].email}</td>
		</tr>
		
		<tr>
			<td>${membersList[0].id}</td>  <!-- ${membersList[0].id}  -->
			<td>${membersList[0].password}</td>
			<td>${membersList[0].name}</td>
			<td>${membersList[0].email}</td>
		</tr>
		<tr>
			<td>${membersList[1].id}</td>  <!-- ${membersList[1].id}  -->
			<td>${membersList[1].password}</td>
			<td>${membersList[1].name}</td>
			<td>${membersList[1].email}</td>
		</tr>
<%
		}
%>		
	</table>
</body>
</html>
