<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<%	request.setCharacterEncoding("UTF-8");%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- HashMap은 최종적으로 컨트롤러가 이 페이지로 전달함 -->
<!-- HashMap의 내용은 BoardService를 통해 BoardDAO의 메소드에 의해서 -->
<!-- 전달된 값들이 채워져 있음. -->
<!-- HashMap으로 저장해서 넘어온 값들은 이름이 길어 사용하기 불편하므로 -->
<!-- c:set 태그를 이용해 각 값들을 짧은 변수 이름으로 저장합니다. -->
<!-- articleList: 글목록정보저장 -->
<!-- totArticles: 전체 글의 수 -->
<!-- section: 섹션 번호(목록창에 표시될 페이지 번호들의 내부섹션 번호-->
<!-- pageNum: 페이지번호(목록창에 현재 표시되는 페이지의 번호 -->
<c:set var="articlesList" value="${articlesMap.articlesList}" />
<c:set var="totArticles" value="${articlesMap.totArticles}" />
<c:set var="section" value="${articlesMap.section}" />
<c:set var="pageNum" value="${articlesMap.pageNum}" />

<!DOCTYPE html>
<html>
<head>
	<style>
		table {margin: auto; border: 1px; width: 80%}
		.no-uline {text-decoration:none;}
		.sel-page{text-decoration:none;color:red;}  /* 선택된 페이지 번호를 빨간색으로 표시합니다. */
		.cls1 {text-decoration:none;}
		.cls2{text-align:center; font-size:30px;}
  	</style>
	<meta charset="UTF-8">
	<title>게시판 글목록창</title>
</head>

<body>

<h1>페이지번호: ${pageNum}</h1>
<h1>섹션번호: ${section}</h1>

<!-- 글목록 표시 테이블 시작 -->
<table>
	<tr height="10px" align="center" bgcolor="lightgreen">
		<td>페이지 글번호</td>
		<td>저장된 글번호</td>
		<td>작성자</td>              
		<td>제목</td>
		<td>작성일</td>
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
		<c:forEach var="article" items="${articlesList}" varStatus="articleNum">
			<tr align="center">
				<td width="5%">${articleNum.count}</td>
				<td width="5%">${article.articleNO}</td>
				<td width="10%">${article.id }</td>
				<td width="35%" align="left">
					<span style="padding-right:30px"></span>    
				<c:choose>
					<c:when test='${article.level > 1 }'>  
						<c:forEach begin="1" end="${article.level}" step="1">
							<span style="padding-left:10px"></span> 
						</c:forEach>
						<span style="font-size:12px;">[답변]</span>
						<a class="cls1" href="${contextPath}/board170308/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
					</c:when>
					<c:otherwise>
						<a class="cls1" href="${contextPath}/board170308/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
					</c:otherwise>
				</c:choose>
				</td>
				<td width="10%"><fmt:formatDate value="${article.writeDate}"/></td> 
			</tr>
		</c:forEach>
	</c:when>
</c:choose>
</table>

<jsp:include page="/boardpaging/paging.jsp">
    <jsp:param value="${paging.page}" name="page"/>
    <jsp:param value="${paging.beginPage}" name="beginPage"/>
    <jsp:param value="${paging.endPage}" name="endPage"/>
    <jsp:param value="${paging.prev}" name="prev"/>
    <jsp:param value="${paging.next}" name="next"/>
</jsp:include>






</body>
</html>