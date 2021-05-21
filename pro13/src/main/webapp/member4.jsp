<%@ page language="java"  contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"
       import="java.util.List"
       import="sec01.ex01.MemberDAO"
       import="sec01.ex01.MemberBean" %>
       
<%   
	request.setCharacterEncoding("UTF-8"); 
%>    

	<!-- 기본 생성자 사용 -->
	<jsp:useBean id="memberBean" class="sec01.ex01.MemberBean" scope="page" />

	<!-- 회원 가입창에서 전달된 매개변수 이름과 속성 이름이 같으면 같은 이름으로 값을 설정-->
	<jsp:setProperty name="memberBean" property="id" param="id"/>
    <jsp:setProperty name="memberBean" property="password" param="password"/>
    <jsp:setProperty name="memberBean" property="name" param="name"/>
    <jsp:setProperty name="memberBean" property="email" param="email"/>
   
                
<%
   MemberDAO memberDAO = new MemberDAO();
   memberDAO.addMember(memberBean);
   List<MemberBean> membersList = memberDAO.listMembers();   
%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>회원 목록창</title>
</head>
<body>
   <table style="margin:auto; width:100%;">
      <tr align="center" bgcolor="#99ccff">
         <td width="7%" >아이디</td>
         <td width="7%">비밀번호</td>
         <td width="5%" >이름</td>
         <td width="11%" >이메일</td>
         <td width="5%" >가입일</td>
      </tr>
<%
   if(membersList.size() == 0){
%>
      <tr>
         <td colspan="5">
            <p align="center">
               <b><span style="font-size:9pt;">
               등록된 회원이  없습니다.</span></b>
            </p>
         </td>
      </tr>
<%
   } else {
      for( int i = 0; i < membersList.size(); i++ ) {
         
         MemberBean bean = (MemberBean) membersList.get(i);
%>
      <tr align="center">
         <td><%=bean.getId() %></td>
         <td><%=bean.getPassword() %></td>
         <td><%=bean.getName() %></td>
         <td><%=bean.getEmail() %></td>
         <td><%=bean.getJoinDate() %></td>
      </tr>
<%
      } // end for
   
   } // end if
%>
      <tr height="1" bgcolor="#99ccff">
         <td colspan="5"></td>
      </tr>
   </table>
</body>
</html>