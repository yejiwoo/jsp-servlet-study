<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding("UTF-8"); %> 


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head> 
	<meta charset="UTF-8">
	<title>새 글 쓰기창</title>

	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
	
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
			obj.action="${contextPath}/boards/listArticles.do";
			obj.submit();
		}
	</script>

	<style>table {margin: auto; border: 0px}</style>
</head>

<body>
<h1 style="text-align:center">새글 쓰기</h1>
<form name="frmArticle" method="post" action="${contextPath}/boards/addArticle.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td align="right">글제목:</td>
			<td colspan="2"><input type="text" size="60" maxlength="450" name="title"/></td>
		</tr>
		<tr>
			<td align="right" valign="top"><br>글내용: </td>
			<td colspan=2><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
		</tr>
		<tr>
			<td align="right">이미지파일 첨부:</td>
			<td><input type="file" name="imageFileName" onchange="readURL(this);"/></td>
			<td><img id="preview" src="#" width="200px" height="200px"/></td>
		</tr>
		<tr>
			<td align="right"> </td>
			<td colspan="2">
				<input type="submit" value="글쓰기" />
				<input type="button" value="목록보기" onClick="backToList(this.form)"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>