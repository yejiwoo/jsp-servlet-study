package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns={"/login090102"}, loadOnStartup=1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		String user_address=request.getParameter("user_address");
		String user_email=request.getParameter("user_email");
		String user_hp=request.getParameter("user_hp");
		
		String data="안녕하세요!<br>로그인하셨습니다.<br><br>"
				+"<html><body>"
				+"아이디: "+user_id+"<br>"
				+"비밀번호: "+user_pw+"<br>"
				+"주소: "+user_address+"<br>"
				+"이메일: "+user_email+"<br>"
				+"전화: "+user_hp+"<br>";
		
		out.print(data);
		
		//GET 방식으로 전송되는 데이터에 포함된 한글을 정상적으로 전송하기 위해 인코딩 처리를 한다.
		user_address=URLEncoder.encode(user_address,"utf-8");
		out.print("<a href='/pro09/second090102?user_id="+user_id+"&user_pw="+user_pw
			+"&user_address="+user_address+"'> 두 번째 서블릿으로 보내기</a>");
		
		data="</body></html>";
		out.print(data);
		
		out.close();
	}

}
