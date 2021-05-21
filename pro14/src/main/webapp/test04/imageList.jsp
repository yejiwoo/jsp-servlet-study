<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %> 


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>이미지리스트창</title>
	<style>
	.lst_type{overflow:hidden;width:70%;padding:0 10px 10px; margin:0 auto}
	.lst_type li{overflow:hidden;clear:both;margin:10px 0 0;color:#2d2c2d;
				 font-family:'돋움',Dotum;font-size:12px;line-height:80px;
				 list-style:none ; border-bottom: 2px solid lightgray;position:relative; }
	.lst_type li img{display:inline;float:left;position:absolute; }
	.lst_type li a{color:#2d2c2d;text-decoration:none; margin-left:180px}
	.lst_type li a:hover{text-decoration:underline}
	/* .lst_type li span{color:blue; margin-left:175px;font-family:'돋움',Dotum;font-size:14px;} */
	.lst_type li span{color:blue; font-family:'돋움',Dotum;font-size:14px;}
	</style>
</head>

<!-- <body style="border: 2px solid red"> -->
<body>
	<ul class="lst_type">
    	<li>
      		<span style='margin-left:60px' >이미지 </span>
      		<span style='margin-left:130px'>이미지 이름</span>
      		<span style='margin-left:178px'>선택하기</span>
    	</li>
    	
<%-- <%
	for(int i=0 ; i<10; i++){
%>
		<li>
			<a href="#" style="margin-left:50px"  >
			<img src="/pro14/image/img100.jpg"
				 width="100px" height="50px" alt="이미지없음" /></a>
			<a href="#" ><strong>이미지 이름: 이미지<%=i %> </strong></a>
			<a href="#" > <input type="checkbox" name="chk<%=i %>" /></a>
		</li>
<%
	}
%>
 --%>
  	<c:forEach var="i" begin="1" end="8" step="1" >
		<li>
			<a href="" style='margin-left:50px'>
				<img src="../image/img100.jpg" width="70" height="70" alt="그림이 없습니다." /></a>
			<a href="#title1" ><strong>이미지 이름: 꽃${i} </strong></a>
		    <a href="#"><input name='chk${i}' type='checkbox'  /></a>
		</li>
	</c:forEach>
	
	</ul>
	
</body>
</html>
