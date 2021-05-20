<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 검색 창</title>
</head>
<body>
 <form name="frmSearchName">
 	이름:<input type="text" name="name"><br>
 	<!-- <input type="button" value="조회하기" onCLick="changeToUpperCase()"> --> <!-- 이렇게 쓰지말고 아래처럼 쓰라는데 왜였는지가 기억 안 남 -->
 	<button type="button" onCLick="changeToUpperCase()">조회하기</button>
 </form>
 
 <script type="text/javascript">
 	function changeToUpperCase(){
 		var inputName=frmSearchName.name.value;
 		
 		frmSearchName.name.value=inputName.toUpperCase();
 		
 		frmSearchName.method="post";
 		frmSearchName.action="/pro07/member.jsp";
 		frmSearchName.submit();
 	}
 </script>
</body>
</html>