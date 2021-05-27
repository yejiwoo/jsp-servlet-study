<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>회원 가입창</title>
<body>
<!-- <form method="post" action="member1.jsp"> -->
<!-- <form method="post" action="forward.jsp"> -->
<!-- <form method="post" action="member3.jsp"> -->
<!-- <form method="post" action="member5.jsp"> -->
<form method="post" action="member7.jsp">
	<h1  style="text-align:center">회원 가입창</h1>
	<table  align="center">
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
	        <tr>
	        <td width="200"><p align="right">도시</td>
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
