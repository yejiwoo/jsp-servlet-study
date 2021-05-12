package sec03.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login090304")
public class SessionTest4 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession();
		
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
		if(session.isNew()){ //최초요청시
			//로그인 창에서 서블릿으로 요청했다면 id가 널이 아니므로 세션에 id를 바인딩한다.
			if(user_id!=null && user_id.length()!=0) {
				session.setAttribute("user_id", user_id); // 세션에 아이디 바인딩
				out.println("<a href='login090304'>로그인 상태 확인</a>");
				
			}else {
				out.print("<a href='login2.html'> 다시 로그인 하세욧!!!!</a>");
				session.invalidate();
			}
		}else { //재요청 시 세션에서 id를 가져와 이전에 로그인했는지 여부를 확인
			user_id=(String)session.getAttribute("user_id");
			if(user_id!=null && user_id.length()!=0) {
				out.print("안녕하세요 "+user_id+"님!!!!!!!!!!!!");
				
			}else {
				out.print("<a href='login2.html'>다시 로그인 하세요!!!!!!!!</a?");
				session.invalidate();
			}
		}
	}

}
