<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>

<%	request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
	<title>시험점수입력 페이지</title>
	<meta charset="UTF-8">
</head>
<body>
	<h1>시험 점수를 입력해 주세요</h1>
		<form method=get action="/pro14/test04/scoreResult1.jsp">
			<input type=text  name="score" placeholder="0~100 사이의 정수로 시험점수를 입력하세요.."/> <br><br>
			<input type ="submit" value="학점 변환">
			<input type="reset" value="다시 입력">	 			 
		</form>
</body>
</html>