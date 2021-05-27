<%@ page language="java" contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
    	 import="java.util.List"
		 import="sec02.ex01.MemberBean"
		 import="sec02.ex01.MemberDAO" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>


		 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새회원 요청처리 JSP</title>
</head>
<body>
<jsp:useBean id="memberBean" class="sec02.ex01.MemberBean" />
<jsp:setProperty name="memberBean" property="*"  />
<jsp:useBean id="memberDAO" class="sec02.ex01.MemberDAO" />

<%
	//MemberDAO memberDAO=new MemberDAO();

	//데이터베이스에 새 회원 정보를 추가합니다.
	memberDAO.addMember(memberBean);
	//데이터베이스에 회원 정보를 조회합니다.
	List<MemberBean> membersList =memberDAO.listMembers();
	//List<MemberBean> membersList = null;
	//회원 목록 정보를 request에 바인딩 합니다.
	request.setAttribute("membersList", membersList);
%>

	<!-- memberList.jsp 파일로 포워딩합니다. -->
	<!-- page 속성에는 컨텍스트 이름을 포함할 수 없습니다. -->
	<jsp:forward page="/test06/membersList.jsp" />

</body>
</html>