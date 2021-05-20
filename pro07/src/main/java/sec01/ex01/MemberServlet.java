package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member070101")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		//SQL문으로 조회할 MemberDAO 객체 생성
		MemberDAO dao=new MemberDAO();	
//		MemberDAOP dao=new MemberDAOP();
		
		List<MemberVO> list=dao.listMembers();
		
		out.print("<html><head><meta charset='utf-8'></head>"
				+"<body>"
				+ "<table style='margin:auto; text-align:center'; border=1>"
				+ "<tr align='center' bgcolor='lightgreen'>"
				+ "<td>아이디</td>"
				+ "<td>비밀번호</td>"
				+ "<td>이름</td>"
				+ "<td>이메일</td>"
				+ "<td>가입일</td>"
				+ "</tr>");
		
		for(int i=0;i<list.size();i++) {
			MemberVO memberVO=(MemberVO)list.get(i); //조회한 회원정보를 for문과 <tr>태그를 이용해 리스트로 출력
			String id=memberVO.getId();            //태그를 이용해 리스트로 출력합니다.
	         String password = memberVO.getPwd();
	         String name=memberVO.getName();
	         String email=memberVO.getEmail();
	         Date joinDate = memberVO.getJoinDate();
	         out.print("<tr>"
	                  + "<td>"+id+"</td>"
	                   + "<td>"+password+"</td>"
	                   + "<td>"+name+"</td>"
	                   + "<td>"+email+"</td>"
	                   + "<td>"+joinDate+"</td>"
	                + "</tr>");  
		}
		out.print("</table>"
	            + "</body>"
	            + "</html>");

		
	}
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doGet(request, response);
	   }

}
