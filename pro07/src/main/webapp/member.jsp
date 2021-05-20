<%@ page language="java" contentType="text/html; charset=UTF-8"  
	import="java.util.List"
	import="java.sql.Date"
	import="sec02.ex02.MemberVO"
	import="sec02.ex02.MemberDAO"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		h1{
			text-align: center; //h1 태그의 텍스트 가운데 정렬
		}
	</style>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
	<h1>회원 정보 출력</h1>
<%
	request.setCharacterEncoding("utf-8");
	String _name=request.getParameter("name");
	MemberVO memberVO=new MemberVO();
	memberVO.setName(_name);
	MemberDAO dao=new MemberDAO();
	List<MemberVO> membersList=dao.listMembers(memberVO);
	
%>
	<table border=1 width=800 align=center>
		<tr align=center bgcolor="#FFFF66">
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>이메일</td>
		<td>가입일자</td>
		</tr>
	<%
		if(membersList.size()==0){	
	%>
	<tr align=center>
		<td colspan="5">찾는 회원의 정보가 없습니다.</td>
	</tr>
	<%
		}else{
			for(int i=0; i<membersList.size();i++){
				MemberVO vo=(MemberVO)membersList.get(i);
				String id=vo.getId();
				String password=vo.getPassword();
				String name=vo.getName();
				String email=vo.getEmail();
				Date joinDate=vo.getJoinDate();
		
	
	%>
	<tr align=center>
	
		<td><%= id %></td>
		<td><%= password %></td>
		<td><%= name %></td>
		<td><%= email %></td>
		<td><%= joinDate %></td>
		</tr>
	<%
			}
		}
	%>
	</table>
	<a href="search.jsp">다시 조회하기</a>
</body>
</html>