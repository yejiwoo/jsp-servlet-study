<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"
		 import="sec01.ex02.MemberBean"
		 import="sec01.ex02.Address"
		 import="java.util.ArrayList"
		 import="java.util.HashMap" %>

		 <!-- isELIgnored="false" -->

<%	request.setCharacterEncoding("UTF-8"); %>

<!-- AddressBean 생성, MemberBeam의 addr 필드명과 동일한 이름으로  빈 생성 -->
<jsp:useBean id="addr" class="sec01.ex02.Address"/>
	<jsp:setProperty name="addr" property="city" value="서울"/>
	<jsp:setProperty name="addr" property="zipCode" value="07654"/>
	
<!-- MemberBean 생성 -->
<jsp:useBean id="memberBean" class="sec01.ex02.MemberBean"  scope="page" />
	<jsp:setProperty name="memberBean" property="*" />

<%
	//Address 타입의 addr 객체를 memberBean.addr 필드에 저장
	memberBean.setAddr(addr);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 출력창</title>
</head>
<body>
	<table style="text-align:center; border: 1px solid grey; margin:auto;">
		<tr bgcolor="#99ccff" >
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="5%" ><b>이름</b></td>
			<td width="5%"><b>이메일</b></td>
			<td width="5%" ><b>도시</b></td>
			<td width="5%" ><b>우편번호</b></td>
		</tr>
		<tr>
			<td>${param.id } </td>
			<td>${param.password } </td>
			<td>${param.name } </td>
			<td>${param.email}</td>
			<td><%=memberBean.getAddr().getCity() %></td>
			<td><%=memberBean.getAddr().getZipCode() %></td>
   
		</tr>
		<tr>
			<td>${memberBean.id } </td>
			<td>${memberBean.password } </td>
			<td>${memberBean.name } </td>
			<td>${memberBean.email}</td>
			<td><%=memberBean.getAddr().getCity() %></td>
			<td><%=memberBean.getAddr().getZipCode() %></td>
   
		</tr>
		<tr>
			<td>${memberBean.id } </td>
			<td>${memberBean.password } </td>
			<td>${memberBean.name} </td>
			<td>${memberBean.email}</td>
			<td>${memberBean.addr.city}</td>
			<td>${memberBean.addr.zipCode}</td>

		</tr>
	</table>
</body>
</html>
