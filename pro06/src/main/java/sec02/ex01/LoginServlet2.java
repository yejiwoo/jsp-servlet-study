package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login2060201")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet2() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
		response.setContentType("text/html; charset=utf-8"); //응답할 데이터 종류가 html임을 설정. 클라이언트에게 전송할 데이터의 종류를 지정. MIME-TYPE지정
		PrintWriter out=response.getWriter();
		
		String data="<!DOCTYPE html> <html>";
		data+="<head>";
		data+="<meta charset=\"UTF-8\">";
		data+="</head>";
		data+="<body>";
		data+="아이디: "+user_id;
		data+="<br>";
		data+="패스워드: "+user_pw;
		data+="</body>";
		data+="</html>";
		
		out.print(data);	//PrintWriter의 print()를 이용해 HTML태그 문자열을 웹 브라우저로 출력	
		out.close();
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
