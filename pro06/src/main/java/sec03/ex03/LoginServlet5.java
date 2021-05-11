package sec03.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login060303")
public class LoginServlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메소드 호출");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id=request.getParameter("user_id");
		String pw=request.getParameter("user_pw");
		String address=request.getParameter("user_address");
		
		System.out.println("아이디: "+id);
		System.out.println("비번: "+pw);
		System.out.println("주소: "+address);
		
		String data="<!DOCTYPE html>"
				+"<html>"
				+"<body>"
				+"아이디: "+id+"<br>"
				+"비번: "+pw+"<br>"
				+"주소: "+address+"<br>"
				+"</body>"
				+"</html>";
		
		
	}

}
