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
	<style>
		table{margin: auto; border: 1px; width: 80%;}
		.cls1{margin: auto; text-decoration:none;}
		.cls2{margin: auto; text-align:center; font-size:30px;}
	</style>
	<meta charset="UTF-8">
	<title>글목록창</title>
</head>
<body>
	<table>
		<tr height="10" align="center"  bgcolor="lightgreen">
			<td>글번호</td>
			<td>작성자</td>              
			<td>제목</td>
			<td>글작성일</td>
		</tr>
	<c:choose>
		<c:when test="${articlesList == null}" >
			<tr height="10">
				<td colspan="4">
				<p align="center">
					<strong><span style="font-size:9pt;">등록된 글이 없습니다.</span></strong>
				</p>
				</td>
			</tr>
		</c:when>
		<c:when test="${articlesList != null}">
			<!-- articleList로 포워딩된 글 목록을 forEach 태그를 이용해 표시합니다. -->
			<c:forEach var="article" items="${articlesList}" varStatus="articleNum" >
				<tr align="center">
					<!-- forEach 태그의 varStatus의 count 속성을 이용해 -->
					<!-- 글 번호를 1부터 자동으로 표시합니다. -->
					<td width="5%">${articleNum.count}</td>
					<td width="10%">${article.id}</td>
					<td align='left'  width="35%">
						<!-- 왼쪽으로 30px만큼 여백을 준 후 글 제목들을 표시합니다.-->
						<span style="padding-right:30px"></span>
						<!-- level 값이 1보다 큰 경우는 자식글(답글)이므로
						     level 값 만큼 부모 글 밑에 공백으로 들여쓰기하여 
						         자식 글(답글)임을 표시합니다.  -->
						<c:choose>
							<c:when test="${article.level > 1}">
								<!-- 부모 글 기준으로 왼쪽 여백을 level 값 만큼 채워
								         답글을 부모 글에 대해 들여 쓰기 합니다. -->  
								<c:forEach begin="1" end="${article.level}" step="1">
									<span style="padding-left:20px"></span>    
								</c:forEach>
								<span style="font-size:12px;">[답변]</span>
								<!-- 공백 다음에 자식 글을 표시합니다. -->
								<a class="cls1" href="${contextPath}/board170304/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
							</c:when>
							<c:otherwise>
								<a class="cls1" href="${contextPath}/board170304/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
							</c:otherwise>
						</c:choose>
					</td>
					<td width="10%"><fmt:formatDate value="${article.writeDate}" pattern="YYYY/MM/dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
	</table>
	<!-- 글쓰기를 클릭하면 글쓰기창인 articleForm.jsp가 표시됩니다. -->
	<div style="text-align:center; font-size:30px;">
		<p>board170303 서브릿 용</p>
		<a style="text-decoration:none;" 
		   href="${contextPath}/board170304/articleForm.do">글쓰기</a></div>
</body>
</html>