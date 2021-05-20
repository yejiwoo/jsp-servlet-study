package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memberList070202")
public class MemberListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, 
                   HttpServletResponse response)
                         throws ServletException, IOException {
      
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      
      String startDay = request.getParameter("startDay");
      String endDay = request.getParameter("endDay");
      
      System.out.println("startDay: "+startDay);
      System.out.println("endDay:   "+endDay);
      
      PrintWriter out=response.getWriter();
      
      //MemberDAO 객체를 생성합니다.
      MemberDAO dao=new MemberDAO();
        
      
      //MemberDAO.listMembers() 메소드로 회원 정보를 조회합니다.
      
      List<MemberVO> list = null;
      
   
   
      //기간 검색 페이지를 거치지 않은 경우(startDay, endDay가 Null)를 제외하기 위한 if 조건 
      if( startDay !=null && endDay !=null ) {
         //기간 검색 페이지를 통해 startDay, endDay에 값이 입력된 경우, 
         if (startDay.length() != 0 && endDay.length()!=0) {
            list =dao.listMembersJoinDateInterval(startDay, endDay);   
         } else {
            list=dao.listMembers();
         }
        //기간 검색 페이지를 거치지 않았거나, startDay와 endDay에 값이 입력되지 않은 경우 
      } else {
         list=dao.listMembers();
      }
   
      out.print("<html><head><meta charset='utf-8'><style>td {padding:0;} input, form {margin:0;}</style></head>"
            + "<body>"
               + "<table style='margin:auto;text-align:center;font-size:13px' border=1>"
                  + "<tr style='margin:auto;text-align:center;font-size:15px; background-color:lightgreen'>"
                     + "<td>아이디</td>"
                     + "<td>비밀번호</td>"
                     + "<td>이름</td>"
                     + "<td>이메일</td>"
                     + "<td>가입일</td>"
                     + "<td>회원삭제</td>"
                  + "</tr>");
     
      for (int i=0; i<list.size();i++){
         
         MemberVO memberVO=(MemberVO) list.get(i);    //조회한 회원 정보를 for문과 <tr>
         
         String id=memberVO.getId();            //태그를 이용해 리스트로 출력합니다.
         String password = memberVO.getPassword();
         String name=memberVO.getName();
         String email=memberVO.getEmail();
         Date joinDate = memberVO.getJoinDate();
         
         out.print("<tr>"
                  + "<td>"+id+"</td>"
                   + "<td>"+password+"</td>"
                   + "<td>"+name+"</td>"
                   + "<td>"+email+"</td>"
                   + "<td>"+joinDate+"</td>"
                   + "<td><button><a href='memberDel070202?id="+id+"'>회원삭제</a></button>" //get방식 
                   //+ "</td>"
                   //+ "<td>" 
                   + "      <form method='post' action='memberDel070202'>"         //post방식: 입력, 수정, 삭제는 post방식 써야 한다.
                   + "         <input type='hidden' name='id' value='"+id+"'>"
                   + "         <input type='submit' value='회원삭제'>"
                   + "      </form>"
                   + "</td>"
                   
                + "</tr>");      
      }
      out.print("</table>"
            + "<br><br>"
//            + "<h2><a href='/pro07/memberForm.html'>회원가입</a></h2>"
            + "<h2 style='text-align:center;'><button><a href='/pro07/memberForm.html'>회원가입</a></button></h2>"
            + "</body>"
            + "</html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}