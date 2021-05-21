<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
	<%
	request.setCharacterEncoding("utf-8");
	int dan=Integer.parseInt(request.getParameter("dan"));
	%>

<!DOCTYPE html>     
<html>
<head>
	<meta charset="UTF-8">
	<title>구구단 출력창</title>
</head>
<body>
	<table border="solid 1px black">
		<tr bgcolor="lightgray" align="center"> 
			<td colspan="2"><%=dan %> 단 출력  </td>
		</tr>
	<%
	for(int i=1; i<10;i++){
		int result = i * dan;
	%>
	<%
		if(i%2==1){  
	%>
		<tr bgcolor="lightyellow" align="center">
	<%
		} else {
	%>
		<tr bgcolor="lightgreen" align="center">
	<%
		}
	%>
			<td width="400"> 
				<%=dan %> * <%=i %>    
			</td>
			<td width="400">
				<%=i*dan %>
			</td>
		</tr>
	<%
	}
	%>
	</table>
	<a href="/pro12/gugu.html">단 입력 페이지로 돌아가기</a>
</body>
</html>
