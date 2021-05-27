<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false" %>

<% request.setCharacterEncoding("UTF-8"); %>  

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입창</title>
</head>
<body>
	<form name="frmMember" >
		<table style="margin:auto;"> 
			<caption>회원 가입창</caption>
				<tr>
					<td>아이디</td>
					<!-- <td><input type="text" name="userId"></td> -->
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>

					<td colspan="2" style="text-align:center">
						<input type="button" value="가입하기" onclick="sendMember()">
						&nbsp;&nbsp;
						<input type="reset" value="다시입력">
					<!-- 	<input type="hidden" name="command" value="addMember" /> -->
					</td>
				</tr>
		</table>
	
	</form>
	<script type="text/javascript">
		function sendMember(){
   			var frmMember=document.frmMember;
			var id=frmMember.id.value;
			var password=frmMember.password.value;
			var name=frmMember.name.value;
			var email=frmMember.email.value;
			
			if(id.length==0 ||id==""){
				alert("아이디는 필수입니다.");
			} else if(password.length==0 ||password==""){
				alert("비밀번호는 필수입니다.");
			} else if(name.length==0 ||name==""){
				alert("이름은 필수입니다.");
			} else if(email.length==0 ||email==""){
				alert("이메일은 필수입니다.");
			} else {
				frmMember.method="post";
				frmMember.action="/pro14/test03/member9.jsp";
				frmMember.submit();
			} 
		}
	</script>

</body>
</html>

