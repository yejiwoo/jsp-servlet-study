<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
		 
<%	request.setCharacterEncoding("utf-8"); %>   

<!DOCTYPE html>
<html>
<head>
	<title>단수 입력창</title>
	<meta charset="UTF-8">
</head>
<body>
	<h1> 구구단의 단수를 입력하세요.</h1>

	<!-- <form name=frmGugu method=get action="/pro14/test04/guguResult1.jsp"> -->
	<form name=frmGugu method=get action="/pro14/test04/guguResult2.jsp">
		<input type="number"  name="dan" placeholder="구구단수를 입력하세요.."/><br>
<!-- 		<input type ="submit" value="출력하기"> -->
		<input type ="button" value="출력하기" onclick="valueCheck()">		 			 
	</form>

<!-- 자바스크립트 : 입력값 검사 방법1 -->

 	<script type="text/javascript">
		function valueCheck(){
			var frmGugu=document.frmGugu;
			var dan=frmGugu.dan.value;
   			
			if(dan.length ==0){
				alert("단수를 입력하세요");
			}else{ 	   		
				frmGugu.method="get";
				//frmGugu.action="/pro14/test04/guguResult2.jsp";
				frmGugu.action="/pro14/test04/guguResult2.jsp";
				frmGugu.submit();
   	   		} 
   		}
	</script> 
 


<!-- 자바스크립트 : 방법2 -->

<!--
	<script type="text/javascript">
		function isNumber(num) {
		//value += '';
		num = String(num);
		num = num.replace(/^\s*|\s*$/g, "");//모든 공백 제거
		if(num.length == 0 || num == null){
			return false;			
		} else if (isNaN(Number(num))) {
			return false;
		} else 
			return true;
		}

	function valueCheck(){
			var frmGugu=document.frmGugu;
			var dan=frmGugu.dan.value;
			
			if(!isNumber(dan)){
				alert("숫자로 된 단수를 하나만 입력하세요");
	   		}else{ 	 
	   			dan = String(dan);
	   			dan = dan.replace(/^\s*|\s*$/g, "");//모든 공백 제거
	   			dan = Number(dan)
	   			frmGugu.dan.value = dan ;
	   			frmGugu.method="get";
	   			frmGugu.action="gugu.jsp";
	   			frmGugu.submit();
	   		} 
		}
	</script>
-->
</body>
</html>