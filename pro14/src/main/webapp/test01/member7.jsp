<%@ page language="java"   
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 isELIgnored="false"
		 import="sec01.ex02.MemberBean"
		 import="sec01.ex02.Address"
		 import="java.util.ArrayList"
		 import="java.util.HashMap"
%>

<!-- memberForm2.jsp 이용 -->

<%
	request.setCharacterEncoding("UTF-8");
%>    
	<jsp:useBean  id="m" class="sec01.ex02.MemberBean" />
	<jsp:setProperty  name="m" property="*" />
	<jsp:useBean   id="addr" class="sec01.ex02.Address"/>
	
	<jsp:setProperty  name="addr" property="*" />

<!-- 아래의  3 줄 대신 위의 jsp:setProperty 액션태그 명령줄 한줄로 고쳤습니다.-->

<%-- 
	<jsp:setProperty   name="addr" property="city" value="서울"/>
	<jsp:setProperty   name="addr" property="zipcCde" value="07654"/>
<%
	m.setMaddr(maddr);
%> --%> 


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 출력창</title>
</head>
<body>
	<table border=1 align="center"   >
			<tr align="center"  bgcolor="#99ccff" >
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="5%" ><b>이름</b></td>
			<td width="5%"><b>이메일</b></td>
			<td width="5%" ><b>도시</b></td>
			<td width="5%" ><b>우편번호</b></td>
		</tr>
		<tr align="center">
			<td>${m.id } </td>
			<td>${m.password } </td>
			<td>${m.name } </td>
			<td>${m.email}</td>
		<%--<td><%=m.getMaddr().getCity() %></td>
			<td><%=m.getMaddr().getZipCode() %></td> --%>
			<td><%=addr.getCity() %></td>
			<td><%=addr.getZipCode() %></td>   
		</tr>
		<tr align="center">
			<td>${m.id } </td>
			<td>${m.password } </td>
			<td>${m.name} </td>
			<td>${m.email}</td>
		<%--<td>${m.addr.city}</td>
			<td>${m.addr.zipCode}</td> --%>
			<td>${addr.city}</td>
			<td>${addr.zipCode}</td>
		</tr>
	</table>
</body>
</html>
