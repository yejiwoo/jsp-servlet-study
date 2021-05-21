<%@ page language="java" contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8" isELIgnored="false" %>

<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입창</title>
<body>
	<!-- <form method="post" action="/pro14/test01/member1.jsp"> --> 
	<!-- <form method="post" action="/pro14/test01/forward.jsp"> -->
	<!-- <form method="post" action="/pro14/test01/member3.jsp"> -->
	<!-- <form method="post" action="/pro14/test01/member4.jsp"> -->
	<!-- <form method="post" action="/pro14/test01/member5.jsp"> -->
	<form method="post" action="/pro14/test01/member66.jsp">
<!--  	<h1  style="text-align:center">회원 가입창</h1> -->
		<table>
			<tr>
				<td colspan="2"><h1  style="text-align:center">회원 가입창</h1></td>
			</tr>
			<tr>
				<td width="200"><p align="right">아이디</td>
				<td width="400"><input type="text" name="id"></td>
			</tr>
			<tr>
				<td width="200"><p align="right">비밀번호</td>
			    <td width="400"><input type="password"  name="password"></td>
			</tr>
			<tr>
			    <td width="200"><p align="right">이름</td>
			    <td width="400"><p><input type="text"  name="name"></td>
			</tr>
			<tr>
			    <td width="200"><p align="right">이메일</td>
			    <td width="400"><p><input type="text"  name="email"></td>
			</tr>
			
			 <!-- 도시이름, 우편번호를 입력하는 폼을 추가합니다. -->

			<tr>
			    <td width="200"><p align="right">도시명</td>
			    <td width="400"><p><input type="text"  name="city"></td>
			</tr>
			
			<tr>
			    <td width="200"><p align="right">우편번호</td>
			    <td width="400"><p><input type="text"  name="zipCode"></td>
			</tr>
			<tr>
			    <td width="200"><p>&nbsp;</p></td>
			    <td width="400">
					<input type="submit" value="가입하기">
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
