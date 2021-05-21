<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<%	request.setCharacterEncoding("UTF-8");%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- <c:set var="articleNO" value="${articleVO.articleNO}" /> --%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글 보기</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
		
		function backToList(obj){
			obj.action="${contextPath}/board170308/listArticles.do";
			obj.submit();
		}
 
		//텍스트 박스의 id로 접근해 disabled 속성을 false로 설정합니다.
		//수정하기 클릭 시 텍스트 박스를 활성화 시킵니다.
		function inputEnable(obj){
			document.getElementById("i_title").disabled=false;
			document.getElementById("i_content").disabled=false;
			document.getElementById("i_imageFileName").disabled=false;
			//document.getElementById("tr_btn_modify").style.display="block";
			document.getElementById("tr_btn_modify").style.display="inline";
			document.getElementById("tr_btn").style.display="none";
		}
		
		//사진표시
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function (e) {
					$('#preview').attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
		
		
		//수정반영하기 클릭 시 컨트롤러에 수정 데이터를 전송합니다. 
		function modifyArticle(obj){
			obj.action="${contextPath}/board170308/modArticle.do";
			obj.submit();
		}

		function removeArticle(articleNO){
			var sendform = document.createElement("form");
			sendform.setAttribute("method", "post");
			sendform.setAttribute("action", "${contextPath}/board170308/removeArticle.do");
			sendform.setAttribute("enctype", "application/www-form-urlencoded");
			var articleNOInput = document.createElement("input");
			articleNOInput.setAttribute("type","hidden");
			articleNOInput.setAttribute("name","articleNO");
			articleNOInput.setAttribute("value",articleNO);
			
			sendform.appendChild(articleNOInput);
			document.body.appendChild(sendform);
			sendform.submit();
		}

		
		//함수 호출 시 전달된 parentNO 값을 생성된 form 의 input 태그를 통해 
		//컨트롤러에 전달합니다.
		function callReplyForm(){
			//자바스크립트를 이용해 동적으로 form 태그를 생성합니다.
			sendform2=document.createElement("form");
			//전송방식을 post로 form 태그의 method 속성값이 설정합니다.
			sendform2.setAttribute("method", "post");
			//전달된 요청명을 form 태그의 action 속성값이 설정합니다.
			sendform2.setAttribute("action", "${contextPath}/board170308/replyForm.do?parentNO=${articleVO.articleNO}");
			sendform2.setAttribute("enctype", "application/www-form-urlencoded");
			
			//자바스크립트를 이용해 동적으로 input 태그를 생성합니다.
			//parentNOInput=document.createElement("input");
			//생성된 input 요소에 type, name, value 속성을 각각 설정(바인딩)합니다. 
			//parentNOInput.setAttribute("type","hidden");
			//parentNOInput.setAttribute("name","parentNO");
			//parentNOInput.setAttribute("value",parentNO);
			
			//동적으로 생성한 input 태그를 동적으로 생성한 form 태그에 추가합니다.
			//sendform2.appendChild(parentNOInput);
			//form 태그를 body 태그에 추가한 후 컨트롤러에게 전송합니다.
			document.body.appendChild(sendform2);
			sendform2.submit();
		}
 
 	</script>
	<style>
		table{margin:auto; border:0px; width:80%;}
		img{width:200px; height:100px;}
		#tr_btn_modify{display:none;}
	</style>
	
</head>
<body>


	<form name="frmArticle" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td width="150px" align="center" bgcolor="#FF9933">글번호</td>
				<td>
					<input type="text" value="${articleVO.articleNO}" disabled />
					<input type="hidden" name="articleNO" value="${articleVO.articleNO}" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">작성자 아이디</td>
				<td>
					<input type="text" value="${articleVO.id}" name="id" disabled />
					<input type="hidden" name="id" value="${articleVO.id}" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">글제목</td>
				<td>
					<input type="text" value="${articleVO.title}" name="title" id="i_title" disabled />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">글내용</td>
				<td>																	<!-- 글내용을 표시합니다. -->
					<textarea rows="20" cols="60" name="content" id="i_content" disabled>${articleVO.content}</textarea>
				</td>  
			</tr>
<!-- imageFileName의 값이 있으면, 이미지를 표시합니다. -->
			<c:choose>
				<c:when test="${not empty articleVO.imageFileName}">
					<tr>
						<td width="20%" align="center" bgcolor="#FF9933"  rowspan="2">이미지</td>
						<td>
							<input type= "hidden" name="originalFileName" value="${articleVO.imageFileName}" />
							<img src="${contextPath}/download170304.do?imageFileName=${articleVO.imageFileName}&articleNO=${articleVO.articleNO}" 
							     alt="이미지가 없습니다" width="200px" height="100px" id="preview" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readURL(this);" />
						</td>
					</tr>
				</c:when>
				<c:when test="${empty articleVO.imageFileName}">
					<tr>
						<td width="20%" align="center" bgcolor="#FF9933" rowspan="2">이미지</td>
						<td>
							<input type="file" name="imageFileName" id="i_imageFileName" onchange="readURL(this);" />
						</td>
					</tr>
					<tr>
						<td>
							<img id="preview" src="#" alt="업로드 이미지가 표시됩니다." width="150px" height="150px"/>
						</td>
					</tr>
				</c:when>
			</c:choose>
<!--  -->
			<tr>
				<td width="20%" align="center" bgcolor="#FF9933">등록일자</td>
				<td>
					<input type="text" value="<fmt:formatDate value='${articleVO.writeDate}' pattern='YYYY/MM/dd HH:mm:ss'/>" disabled />
					<input type="hidden" name="writeDate" value="${articleVO.writeDate}" />
				</td>
			</tr>
			
			<tr id="tr_btn_modify" >
				<td colspan="2" >
					<input type=button value="수정반영하기" onClick="modifyArticle(frmArticle)"  >
					<input type=button value="취소" onClick="backToList(frmArticle)">
				</td>   
			</tr>
			<tr id="tr_btn">
				<td colspan=2 >
					<input type=button value="수정하기" onClick="inputEnable(this.form)" />
					<input type=button value="삭제하기" onClick="removeArticle(${articleVO.articleNO})" />
					<%-- <input type="button" value="답글쓰기" onClick="callReplyForm(${articleVO.articleNO})" /> --%>
					<input type="button" value="답글쓰기" onClick="callReplyForm()" />
					<input type="button" value="리스트로 돌아가기" onClick="backToList(this.form)" />
				</td>
			</tr>
	</table>
	</form>
</body>
</html>
				