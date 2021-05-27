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
	<title>답글쓰기 페이지</title>
	<script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">

		function backToList(obj){
			obj.action="${contextPath}/boards/listArticles.do";
			obj.submit();
		}

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function (e) {
					$('#preview').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}  
	</script>
	<style>table{margin: auto;}</style>
</head>
<body>
<h1 style="text-align: center">답글쓰기</h1>
<form name="frmReply" method="post" action="${contextPath}/boards/addReply.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td align="right" valign="top">글쓴이&nbsp;</td>
			<td colspan="2"><input type="text" style="height: 24px;" size="50" value="lee" readonly/></td>
		</tr>
		<tr>
			<td align="right" valign="top">글제목&nbsp;</td>
			<td colspan="2"><input type="text" style="height: 24px;" size="50" maxlength="100" name="title"/></td>
		</tr>
		<tr>
			<td align="right" valign="top">글내용&nbsp;</td>
			<td colspan="2"><textarea name="content" rows="10" cols="52" maxlength="4000"></textarea></td>
		</tr>
		<tr>
			<td align="right" valign="top">이미지파일&nbsp;<br>첨부&nbsp;</td>
			<td><img id="preview" src="#" width="200px" height="200px"/></td>			
			<td valign="top">
				<input type="file" name="imageFileName" onchange="readURL(this);"/></td>
			
		</tr>
		<tr>
			<td align="right"> </td>
			<td>
				<input type="submit" value="답글반영하기"/>&nbsp;
				<input type="button" value="취소" style="width: 90px;" onClick="backToList(this.form)"/>
				
			</td>
		</tr>
	</table>
</form>
</body>
</html>