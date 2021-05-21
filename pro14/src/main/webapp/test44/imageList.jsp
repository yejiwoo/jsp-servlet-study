<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		/** {border: 2px solid red;}*/
		.lst_type{overflow:hidden;width:100%;padding:0 10px 10px; margin:0 auto}
		.lst_type li{overflow:hidden;clear:both;margin:10px 0 0;color:#2d2c2d;
					 font-family:'돋움',Dotum;font-size:12px;line-height:100px;
					 list-style:none ; border-bottom: 2px solid lightgray;
					 position:relative; }
		.lst_type li img{display:inline;float:left;position:absolute; }
		.lst_type li a{color:#2d2c2d;text-decoration:none; margin-left:150px}
		.lst_type li a:hover{text-decoration:underline}
		.lst_type li span{color:blue; margin-left:150px;font-family:'돋움',
						  Dotum;font-size:14px; /*border: 2px solid red;*/ }
	</style>
	<meta charset="UTF-8">
	<title>이미지리스트창</title>
</head>
<body /*style="border: 2px solid red"*/>
	<ul class="lst_type">
    	<li>
      		<span style='margin-left:60px' >이미지 </span>
      		<span style='margin-right:60px'>이미지 이름</span>
      		<span style='margin-right:60px'>선택하기</span>
    	</li>
<%
	for(int i=0 ; i<10; i++){
%>
		<li>
			<a href='#' style='margin-left:50px'  >
			<img src='image/duke.png' width='90' height='90' alt='' /></a>
			<a href='#' ><strong>이미지 이름: 듀크<%=i %> </strong></a>
			<a href='#' > <input  name='chk<%=i %>' type='checkbox'  /></a>
		</li>
<%
	}
%>
	</ul>
</body>
</html>
