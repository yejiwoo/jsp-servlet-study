package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
		if( user_id!=null && (user_id.length()!=0)) {
			if(user_id.equals("admin")) {
				out.print("<html>");
				out.print("<body>");
				out.print("<font size='12>관리자로 로그인 하셨어용!!</font>");
				out.print("<br>");
				out.print("<input type=button value='회원정보 수정하기' />");
				out.print("<input type=button value='회원정보 학제하기' />");
				out.print("</body>");
				out.print("</html>"); 
			}else {
				out.print("<html>");
				out.print("<body>");
				out.print(user_id+"님! 로그인 했엉.");
				out.print("</body>");
				out.print("</html>"); 
			}
		}else {
			out.print("<html>");
			out.print("<body>");
			out.print("아이디 입력 해!!!");
			out.print("<br>");
			out.print("<a href='http://localhost:8080/pro06/test-1/login.html'>로그인 창으로 이동</a>");
			out.print("</body>");
			out.print("</html>");
		}
	}

}
