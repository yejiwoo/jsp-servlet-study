<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		request.setCharacterEncoding("utf-8");
		int dan =Integer.parseInt(request.getParameter("dan"));
  
%>   
<!DOCTYPE html>  
<html>
<head>
<meta charset="UTF-8">
<title>구구단 출력창</title>
</head>
<body>
	<table border=1 >
		<tr align=center bgcolor="#FFFF66"> 
			<td colspan=2><%= dan %>단 출력  </td>
		</tr>
		<%
			for(int i=1; i<10;i++){
				int result = i * dan; 
		%> 
		<tr align="center"> 
			<td width="200"> 
				<%= dan %> * <%=i %>    
	  		</td>
			<td width="200">
				<%= result %>
			</td>
		</tr>
		<%
			}
		%>
   </table>
   
   <a href="/pro12/gugu.html">단 입력 페이지로 돌아가기</a>
</body>
</html>
