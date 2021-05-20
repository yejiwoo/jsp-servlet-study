package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login070501")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPassword(user_pw);
		
		MemberDAO dao=new MemberDAO();
		boolean result=dao.isExisted(memberVO);
		if(result) {
			HttpSession session=request.getSession();
			session.setAttribute("islogon", true);
			session.setAttribute("login.id", user_id);
			session.setAttribute("login.pw", user_pw);
			
			out.print("<html><body>");
			out.print("안녕하세요. "+user_id+"님!!<br>");
			out.print("<a href='/pro07/show070501'>회원정보 보기</a>");
			out.print("</body></html>");
		}else {
			out.print("<html><body><center>회원 아이디가 틀립니다.");
			out.print("<a href='/pro07/login3.html'>다시 로그인하기</a>");
			out.print("</body></html>");
		}
	}
	
}





//pro07/src/main/java/sec02.ex02.LoginServlet.java
//package sec02.ex02;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/login070501")
//public class LoginServlet extends HttpServlet {
//   private static final long serialVersionUID = 1L;
//
//   protected void doGet(HttpServletRequest request, 
//                   HttpServletResponse response) 
//                         throws ServletException, IOException {
//
//      request.setCharacterEncoding("utf-8");
//      response.setContentType("text/html;charset=utf-8");
//
//   
//      //로그인 창에서 전송된 아이디와 패스워드를 가져옵니다.
//      String id = request.getParameter("id");
//      String password = request.getParameter("password");
//      String name = request.getParameter("name");
//      
//      System.out.println("사용자 입력ID: "+ id);
//      System.out.println("사용자 입력암호: "+ password);
//      System.out.println("테스트한글: "+ name);
//      
//      //MemberVO 객체를 생성하고 속성에 아이디와 패스워드를 설정합니다.
//      MemberVO memberVO = new MemberVO();
//      
//      memberVO.setId(id);
//      memberVO.setPassword(password);
//      //memberVO.setName(name);
//   
//
//      //MemberDAO의 isExisted()메소드에, 
//      //아이디와 패스워드가 저장된 MemberVO 객체를 전달하여 처리합니다.   
//      MemberDAO memberDAO = new MemberDAO();
//      
//      //맴버가 존재하는지 확인하는 메소드를 호출합니다.
//      boolean result = memberDAO.isMemberExist(memberVO);
//      
//      
//      PrintWriter out = response.getWriter();
//      
//      //웹브라우저의 세션정보가 있는 세션 객체(HttpSession 객체)를 호출합니다.
//      HttpSession session = request.getSession();
//      
//      //memberDAO.isMemberExist(memberVO) 처리 결과가 true이면,
//      if (result) {
//      
//         System.out.println("생성된 세션 아이디 : " + session.getId());
//         
//         //isLogin 속성에 true 값을 지정하여 세션객체에 저장합니다.
//         //로그인아이디와패스워드도 세션 객체에 저장합니다.
//         session.setAttribute("isLogon", true); 
//         session.setAttribute("login.id", id);
//         session.setAttribute("login.password", password);
//         session.setAttribute("login.name", name);
//
//         //response.encodeURL("호출되는서블릿매핑이름")
//         //String url=response.encodeURL("show090501");
//         //System.out.println("생성된 세션 아이디 : " + session.getId());
//         //System.out.println("encodeURL() 값 : " + url);
//         
//         out.print("<html><head><meta charset=\"utf-8\"></head><body>");
//         out.print("안녕하세요 " + id + "님!!!<br>");
//         out.print("<a href='show070501'>회원정보보기(encodeURL처리않함)</a>");
//         out.print("</body></html>");
//
//         //memeberDAO.isMemberExist(memberVO) 처리 결과가 false이면,
//      } else {
//         out.print("<html><head><meta charset='utf-8'></head><body>회원 아이디 또는 암호가 틀립니다.");
//         out.print("<a href='/pro07/login3.html'> 다시 로그인하기</a>");
//         out.print("</body></html>");
//      }
//   }
//
//   protected void doPost(HttpServletRequest request, 
//                    HttpServletResponse response) 
//                          throws ServletException, IOException {
//      doGet(request, response);
//   }
//
//}
//
