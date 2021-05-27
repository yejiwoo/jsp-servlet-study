package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/show070501")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		String id="",pw=""; //String id=null, pw=null; 과 같다
		Boolean isLogon=false;
		
		//브라우저 접속하면 세션은 만들어진다. 따라서 아래처럼은 의미가 없다. 세션객체는 항상 있다.
		HttpSession session=request.getSession(false); // 이미 세션이 존재하면 세션을 반환, 없으면 null 반환
		if(session!=null) {
			isLogon=(Boolean)session.getAttribute("islogon"); //원래 반환값은 Object
			if(isLogon==true) {
				id=(String)session.getAttribute("login.id");
				pw=(String)session.getAttribute("login.pw");
				out.print("<html><body>");
				out.print("아이디: "+id+"<br>");
				out.print("비밀번호: "+pw+"<br>");
				out.print("</body></html>");
			}else {
				System.out.println("브라우저에서 showMember가 직접 호출된 경우입니다.");
				
				//기존 세션 무효화
				session.invalidate();
				//로그인 페이지로 리다이렉트
				response.sendRedirect("/pro07/login3.html");//로그인 상태가 아니면 로그인창으로 이동
			}
		}else {
			response.sendRedirect("/pro07/login3.html"); //세션이 생성되지 않았으면 로그인 창으로 이동
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
