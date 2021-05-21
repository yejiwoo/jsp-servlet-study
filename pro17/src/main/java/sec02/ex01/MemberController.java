package sec02.ex01;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//이 컨트롤러 서블릿은 회원(member)과 관련된 기능을 제어하는 컨트롤러로 사용되는
//의미를 전달하기 위하여 "/member/*"의 URL 패턴으로 된 매핑이름을 지정합니다.
//브라우저에서 요청 시, http://서버이름:포트번호/pro17/member/작업.do"로 입력하면
//이 서블릿이 호출됩니다.
@WebServlet("/member170201/*")  
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//MemberDAO memberDAO;

	public void init() throws ServletException {
		//memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		

		//Controller서블릿에서 사용자 요청을 처리하는 
		//다른 서블릿이나 JSP 페이지의 URL 요청명을 저장할 문자열 변수 
		String nextPage = null;
		
		//브라우저의 request로부터 매핑이름을 가져와 변수에 저장합니다.
		//매핑이름은 사용자가 요청한 작업입니다
		String action = request.getPathInfo(); //예, /listMebers.do 또는 /addMember.do 
		System.out.println("action:" + action);

		MemberDAO memberDAO = new MemberDAO();
		
		//최초 요청이거나 action 값이 /memberList.do이면
		//회원목록을 출력합니다.
		if (action == null || action.equals("/listMembers.do")) {
			//맴버 정보를 membersList 객체에 저장합니다.
			List<MemberVO> membersList = memberDAO.listMembers();
			//membersList 객체를  request에 memsList 매개변수(속성)로 설정합니다.
			request.setAttribute("memsList", membersList);
			
			//"/test02/listMembers.jsp"로 포워딩하기 위하여
			//"/test02/listMembers.jsp" 경로를 nextPage 변수에 담습니다.
			nextPage = "/test02/listMembers.jsp";
			
			//if - else 블록 처리 후의 다음의 포워딩 실행문을 통해
			//nextPage 변수의 문자열 서블릿이나 JSP 페이지로 결과를 포워딩합니다.
			//RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			//dispatcher.forward(request, response);
		} 
		  //아래의 else if {} 는
		  //action 값이 /memberForm.do 이면  
		  //회원 가입창을 호출(포워드)하여 브라우저에 표시합니다.
		  else if (action.equals("/memberForm.do")) {

			  //회원 가입창(/test02/listMembers.jsp)을 호출(포워드)하기 위하여
			  //"/test02/listMembers.jsp" 경로를 nextPage 변수에 담습니다.
			  nextPage = "/test02/memberForm.jsp";    
		 }		
		  //아래의 else if {} 는
		  //action 값이 /addMember.do 이면 전송된 사용자의 입력 정보를 가져와서 
		  //테이블에 추가하는 addMembers.do 매핑이름의 기능을 수행합니다.
		  else if (action.equals("/addMember.do")) {
			//회원 가입창의 request에 있는 값들을 파라미터를 이용하여 
			//가져와서 변수에 각각 저장합니다.
			String id = request.getParameter("id"); 
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//회원가입 정보를 VO 객체에 저장합니다.
			MemberVO memberVO = new MemberVO(id, pwd, name, email);

			//회원을 가입시킵니다(데이터베이스에 저장)
			memberDAO.addMember(memberVO);
			
			//회원가입처리 후, /member/listMembers.do 로 포워딩 하기 위하여
			//"/member/listMembers.do" 경로를 nextPage 변수에 담습니다.
			nextPage = "/member170201/listMembers.do";
		}
		   //아래의 else {}는 action 값이 
		   //null, /listMembers.do, /addMember.do, /memberForm.do가 아닐 경우,
		   //맴버의 목록정보를 호출하는 jsp 페이지를 호출(포워드)하여 브라우저에 표시합니다.
		   else {
				//맴버 정보를 membersList 객체에 저장합니다.
				List<MemberVO> membersList = memberDAO.listMembers();
		
				//membersList 객체를  request에 memsList 매개변수(속성)로 설정합니다.
				request.setAttribute("memsList", membersList);
				
				//"/test02/listMembers.jsp"로 포워딩하기 위하여
				//"/test02/listMembers.jsp" 경로를 nextPage 변수에 담습니다.
				nextPage = "/test02/listMembers.jsp";
		}
		//위의 각 사용자 요청에 해당하는(nextPage 변수에 저장된 경로와모듈이름) 
		//서블릿이나 JSP 페이지를 호출(포워드)합니다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}