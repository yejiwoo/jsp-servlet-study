<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<% request.setCharacterEncoding("UTF-8");%>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글쓰기창</title>
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
			obj.action="${contextPath}/board1703052/listArticles.do";
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
	<title>새글 쓰기 창</title>
</head>
<body>
	<h1 style="text-align:center">새글 쓰기</h1>
	<form name="articleForm" method="post" 
			action="${contextPath}/board1703052/addArticle.do"
			enctype="multipart/form-data"><!-- 파일업로드를 위한 옵션입니다. -->
		<table>
			<tr>
				<td align="right" valign="top">글제목: </td>
				<td colspan="2"><input id="titleinput" type="text" size="67"  maxlength="500" name="title" /></td>
			</tr>
			<tr>
				<td align="right" valign="top"><br>글내용: </td>
				<td colspan=2><textarea name="content" rows="10" cols="68" maxlength="4000" style="resize: none"></textarea> </td>
			</tr>
			<tr>
				<td align="right" valign="top">이미지파일 첨부:  </td>
				<td colspan="2">
					<input type="file" name="imageFileName" onchange="readURL(this);" /><br><br>
					<img id="preview" src="#" alt="업로드 이미지가 표시됩니다." width="150px" height="150px"/></td>
			</tr><!-- 위의 라인은 첨부한 이미지를 미리 보기로 표시합니다. -->
			<tr><td><br><br></td><td></td><td></td></tr>
			<tr><td><br><br></td><td></td><td></td></tr>
			<tr>
				<td align="right" valign="top"> </td>
				<td colspan="2">
					<input class="btn" type="submit" value="글쓰기" width="100px" />
					<input class="btn" type="button" value="목록보기" width="100px" onClick="backToList(this.form)" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>