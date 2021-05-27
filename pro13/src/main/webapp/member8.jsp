<%@ page language="java"  contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"
       import="java.util.List"
       import="sec01.ex01.MemberDAO"
       import="sec01.ex01.MemberBean" %>
    
          
<%   request.setCharacterEncoding("UTF-8"); %>     

   <jsp:useBean id="memberBean" class="sec01.ex01.MemberBean" 
              scope="page"/>

<!-- MemberBean 클래스의 맴버필드명과   요청페이지의 값을 전달하는 
     form의 input 이름이  동일하면, property에 * 을 설정할 수 있습니다.
-->
   <jsp:setProperty name="memberBean" property="*" />

<%--               
   <jsp:setProperty name="memberBean" property="id" />
   <jsp:setProperty name="memberBean" property="password" />
   <jsp:setProperty name="memberBean" property="name" />
   <jsp:setProperty name="memberBean" property="email"/>
                
--%>


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
         
      //MemberBean memBean = (MemberBean) membersList.get(i);

%>
      <jsp:useBean id="memBean" class="sec01.ex01.MemberBean" scope="page"/>
<%

      memBean = (MemberBean) membersList.get(i);
%>
<%-- 
   jsp:getProperty 액션태그로 값을 못가져옵니다.
   왜: userBean 태그로 생성된 Bean이 아니라 List 객체의 MemberBean 유형의 객체로 바뀌었기
         때문에 값을 못가져옵니다. 
   
      <tr>
         <td> <jsp:getProperty name="memBean" property="id"/> </td>
             <td> <jsp:getProperty name="memBean" property="password" />  </td>
             <td> <jsp:getProperty name="memBean" property="name" /> </td>
             <td> <jsp:getProperty name="memBean" property="email" /> </td>
             <td> <jsp:getProperty name="memBean" property="joinDate" /> </td>
      </tr> --%>

      <tr align="center">
         <td><%=memBean.getId() %></td>
         <td><%=memBean.getPassword() %></td>
         <td><%=memBean.getName() %></td>
         <td><%=memBean.getEmail() %></td>
         <td><%=memBean.getJoinDate() %></td>
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