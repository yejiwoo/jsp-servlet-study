<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding("UTF-8"); %>

<!-- contextPath(어플리캐이션 이름, 예,pro17)를 가져와 변수에 저장 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />

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
		.sel-page{text-decoration:none;text-color:red;}  /* 선택된 페이지 번호를 빨간색으로 표시합니다. */
		.cls1 {text-decoration:none;}
		.cls2{text-align:center; font-size:30px; text-decoration:none;}
		.cls3{text-align:center; font-size:25px; text-decoration:none;}
  	</style>
	<meta charset="UTF-8">
	<title>게시판 글목록창</title>
</head>
<body>
<h1 style="text-align:center">글 목록</h1>
<!-- 글목록 표시 테이블 시작 -->
<table>
	<tr height="10px" align="center" bgcolor="lightgreen">
		<td>글번호</td>
		<td>글 ID</td>
		<td>작성자</td>              
		<td>제목</td>
		<td>작성일</td>
	</tr>
<c:choose>
	<c:when test="${articlesList == null}">
		<tr height="10px">
			<td colspan="5">
				<p align="center">
					<b><span style="font-size:19px;">등록된 글이 없습니다.</span></b>
				</p>
			</td>
		</tr>
	</c:when>
	<c:when test="${articlesList != null}">
		<c:forEach var="article" items="${articlesList }" varStatus="articleNum">
			<tr align="center">
				<td width="5%">${articleNum.count}</td>
				<td width="10%">${article.articleNO}</td>
				<td width="10%">${article.mid }</td>
				<td width="35%" align="left">
					<span style="padding-right:30px"></span>    
				<c:choose>
					<c:when test='${article.level > 1 }'>  
						<c:forEach begin="1" end="${article.level}" step="1">
							<span style="padding-left:10px"></span> 
						</c:forEach>
						<span style="font-size:12px;">[답변]</span>
						<a class='cls1' href="${contextPath}/boards/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
					</c:when>
					<c:otherwise>
						<a class='cls1' href="${contextPath}/boards/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
					</c:otherwise>
				</c:choose>
				</td>
				<td width="10%"><fmt:formatDate value="${article.writeDate}"/></td> 
			</tr>
		</c:forEach>
	</c:when>
</c:choose>
</table>
<hr width="80%" align="center">
<br>

<!-- 테이블 표시 후, 페이지 이동 번호 표시 부분(하나의 섹션에서의 표시되는 글목록의 페이지 번호 입니다. -->
<!-- 첫 페이지는 1번 섹션의 1~10까지의 글 목록 페이지 번호가 표시되고, -->
<!-- 테이블에는 해당 페이지의 글목록 내용이 표시됩니다. -->

<!-- 이동 페이지 번호 표시 시작-->
<!-- 전체 글수에 따라 페이징 표시를 다르게 합니다. -->
<div class="cls3">
	<c:if test="${totArticles != null }" >
		<c:choose>
			<c:when test="${totArticles > 100}"><!-- 총 글 개수가 100 개를 초과한 경우 -->
				<c:forEach var="page" begin="1" end="10" step="1">
					<c:if test="${section > 1 && page == 1}"><!-- 섹션값 2부터는 앞 섹션으로 이동할 수 있는 [pre]를 표시합니다.-->
						<a class="no-uline" href="${contextPath}/boards/listArticles.do?section=${section - 1}&pageNum=${(section-1)*10 + 1}">&nbsp;[pre]</a>
					</c:if><!-- 섹션이 무조건 1일 때 -->
						<a class="no-uline" href="${contextPath}/boards/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10 + page}</a>
					<c:if test="${page == 10}"><!-- 페이지가 10인 경우 --><!-- 다음 섹션으로 이동할 수 있는 [next]를 표시합니다 -->
						<a class="no-uline" href="${contextPath}/boards/listArticles.do?section=${section + 1}&pageNum=${section*10 + 1}">&nbsp;[next]</a>
					</c:if>
				</c:forEach>
			</c:when>
			<c:when test="${totArticles == 100}" ><!--등록된 글 개수가 100개인경우, 섹션값은 무조건 1, 페이지 번호는1~10 표시-->
				<c:forEach var="page" begin="1" end="10" step="1">
					<a class="no-uline" href="#">${page}</a><!-- 페이지번호는 1~ 10까지 중, 현재 페이지 제외한 나머지 페이지의 라인은 표시하지 않음  -->
				</c:forEach>
			</c:when>
			<c:when test="${totArticles < 100 }"><!--등록된 글 개수가 100개 미만인 경우, 섹션값은 무조건 1, 페이지 번호는 10 이하 표시-->
				<c:forEach var="page" begin="1" end="${totArticles/10 + 1}" step="1" >
	         		<c:choose>
						<c:when test="${page == pageNum}">
							<a class="sel-page" href="${contextPath}/boards/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
						</c:when>
						<c:otherwise>
							<a class="no-uline" href="${contextPath}/boards/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
		</c:choose>
	</c:if>
</div>
<!-- 새글쓰기 창으로 이동하는 링크 표시부분 -->
<br><br>
	<!-- <a class="cls1" href="${contextPath}/boards/articleForm.do"><p class="cls2">글쓰기</p></a> -->
	<div align="center"><a class="cls2" href="${contextPath}/boards/articleForm.do">글쓰기</a></div>
</body>
</html>