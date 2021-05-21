<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<%	request.setCharacterEncoding("UTF-8");%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
  
<!DOCTYPE html>
<html>
<head>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		function backToList(obj){
			obj.action="${contextPath}/board170304/listArticles.do";
			obj.submit();
		}
	</script>
	<style>
		table{margin:auto; border:0px; width:80%;}
		img{width:200px; height:100px;}
	</style>
	
	<meta charset="UTF-8">
	<title>글 보기</title>
</head>
<body>
	<form name="frmArticle" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td width="150px" align="center" bgcolor="#FF9933">글번호</td>
				<td>
					<input type="text" value="${article.articleNO}" disabled />
					<input type="hidden" name="articleNO" value="${article.articleNO}" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">작성자 아이디</td>
				<td>
					<input type="text" value="${article.id}" name="id" disabled />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">글제목</td>
				<td>
					<input type="text" value="${article.title}" name="title" id="i_title" disabled />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">글내용</td>
				<td>																	<!-- 글내용을 표시합니다. -->
					<textarea rows="20" cols="60" name="content" id="i_content" disabled>${article.content}</textarea>
				</td>  
			</tr>
		<!-- imageFileName의 값이 있으면, 이미지를 표시합니다. -->
		<c:if test="${not empty article.imageFileName && article.imageFileName != 'null'}">  
			<tr>
				<td width="20%" align="center" bgcolor="#FF9933"  rowspan="2">이미지</td>
				<td><!-- hidden 태그에 원래 이미지 파일 이름을 저장합니다. -->
					<input type= "hidden" name="originalFileName" value="${article.imageFileName}" />
					<img src="${contextPath}/download170304.do?imageFileName=${article.imageFileName}&articleNO=${article.articleNO}" 
					     alt="이미지가 없습니다" width="200px" height="100px" id="preview" />
					    <!-- FileDownloadController 서블릿에 이미지 파일이름과 -->
				</td>   <!-- 글번호를 전송해 이미지를 img 태그에 표시합니다. -->
			</tr>  
	 		<tr>
				<td>
					<input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);" />
				</td>
			</tr> 
		</c:if>
			<tr>
				<td width="20%" align="center" bgcolor="#FF9933">등록일자</td>
				<td>
					<input type="text" value="<fmt:formatDate value='${article.writeDate}' pattern='YYYY/MM/dd HH:mm:ss'/>" disabled />
				</td>
			</tr>
			<tr id="tr_btn">
				<td colspan=2 align="center">
	 				<input type=button value="수정하기" onClick="fn_enable(this.form)">
					<input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board170304/removeArticle.do', ${article.articleNO})">
					<input type=button value="리스트로 돌아가기" onClick="backToList(this.form)">
					<input type=button value="답글쓰기" onClick="fn_reply_form('${contextPath}/board170304/replyForm.do', ${article.articleNO})">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>