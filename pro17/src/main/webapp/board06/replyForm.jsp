<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<% request.setCharacterEncoding("UTF-8");%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<%-- <c:set var="parentNO" value="${session.getAttribute('parentNO')}"  /> --%>
					<%-- <%=session.getAttribute("parentNO")%> --%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>답글 쓰기창</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		//제이쿼리를 이용해 이미지 파일 첨부 시 미리 보기 기능을 구현합니다.
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function (e) {
					$('#preview').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		
		function backToList(obj){
			obj.action="${contextPath}/board170307/listArticles.do";
			obj.submit();
		}
	</script>
 	<style>
		table{margin: auto; border: 0px; width: 80%;}
		textarea, #titleinput {width: 500px;}
		.btn {width: 100px}
		.cls1{text-decoration:none;}
		.cls2{text-align:center; font-size:30px;}
	</style>
</head>
<body>
	<h1 style="text-align:center">답글쓰기</h1>
	
	<!-- 답글 입력 후 전송 시 컨트롤러에 addReply.do로 요청합니다. -->
	
	<h1><%=session.getAttribute("parentNO")%></h1>
	
	<form name="frmReply" method="post" 
		  action="${contextPath}/board170307/addReply.do" 
	      enctype="multipart/form-data">
		<table style="margin: auto; border: 0px;">
			<tr>
				<td align="right">글쓴이:&nbsp;</td>
				<td><input type="text" size="5" value="${parentNO}" disabled /></td>
			</tr>
			<tr>
				<td align="right">글제목:&nbsp;</td>
				<td><input type="text" size="67"  maxlength="100" name="title" /></td>
			</tr>
			<tr>
				<td align="right" valign="top"><br>글내용:&nbsp;</td>
				<td><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
			</tr>
			<tr>
				<td align="right">이미지파일 첨부:</td>
				<td><input type="file" name="imageFileName"  onchange="readURL(this);"/></td>
				<td><img id="preview" src="#" width="200px" height="200px"/></td>
			</tr>
			<tr>
				<td align="right"> </td>
				<td>
					<input type="submit" value="답글반영하기" />
					<input type="button" value="취소" onClick="backToList(this.form)" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>