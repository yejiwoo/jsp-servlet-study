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


@WebServlet("/memberAdd070202")
public class MemberAddServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, 
                   HttpServletResponse response)
                         throws ServletException, IOException {
      
      request.setCharacterEncoding("utf-8");
      //response.setContentType("text/html;charset=utf-8");
      
      //PrintWriter out=response.getWriter();
      
      String id = request.getParameter("id");
      String password = request.getParameter("password");
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      
      MemberVO memberVO = new MemberVO();
      
      memberVO.setId(id);
      memberVO.setPassword(password);
      memberVO.setName(name);
      memberVO.setEmail(email);
      
           
      //MemberDAO 객체를 생성합니다.
      MemberDAO dao=new MemberDAO();
      
      dao.addMember(memberVO);
      
      response.sendRedirect("/pro07/memberList070202");

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}