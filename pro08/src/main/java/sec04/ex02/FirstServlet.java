package sec04.ex02;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/first080402")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//브라우저에서 요청한 request객체에 address의 값으로 "서울시성북구" 바인딩
		request.setAttribute("address", "서울시 성북구"); 
		
		// redirect, refresh, location 방식은 값 전달 안 됨
//		response.sendRedirect("second080401");
		
		//dispatcher방법은 전달 됨
		RequestDispatcher dispatch=request.getRequestDispatcher("second080402");
		dispatch.forward(request, response);
		
	}

	

}
