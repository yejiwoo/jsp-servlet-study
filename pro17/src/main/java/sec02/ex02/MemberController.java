package sec02.ex02;

import java.io.IOException;
import java.sql.Date;
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
//브라우저에서 요청 시, http://서버이름:포트번호/pro17/member/"로 입력하면
//이 서블릿이 호출됩니다.
//@WebServlet("*.do")
//@WebServlet("/member/*.do") <--이런 형식은 유효하지 않음.
@WebServlet("/member170202/*")
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
		String action = request.getPathInfo();
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
			nextPage = "/test03/listMembers.jsp";
			
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
			  nextPage = "/test03/memberForm.jsp";    
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
			
			//회원 목록창으로 회원 가입 작업 완료 메시지를 전달하기 위하여 
			//request에 "회원가입 성공!!""addMember" 라는 문자열을 msg 매개변수로 추가합니다.
			request.setAttribute("msg", "memAddedSuccess"); 
						
			//회원가입처리 후, /member/listMembers.do 로 포워딩 하기 위하여
			//"/member/listMembers.do" 경로를 nextPage 변수에 담습니다.
			nextPage = "/member170202/listMembers.do";
		}
		  //아래의 else if {} 는
		  //action 값이 /modMemberForm.do 이면 전송된 사용자ID를 이용하여 
		  //사용자의 입력 정보를 찾아서, 요청에 찾은 사용자 정보를 추가하여
		  //사용자 수정페이지를 호출(포워딩)하는 작업을 수행합니다.
		  else if(action.equals("/modMemberForm.do")){
			//회원 아이디 값을 가져와서 mid 변수에 저장
			String id=request.getParameter("id");
			
			//MemberDAO 클래스의 findMember()메소드를 호출하여
			//해당 ID의 맴버정보를 가자와 MemberVO 객체에 저장합니다.
			MemberVO memberVO = memberDAO.findMember(id);
			
			//찾은 맴버정보가 저장된 VO객체(memInfo)를
			//request에 memInfo 매개변수로 설정합니다.
			request.setAttribute("memInfo", memberVO);
			
			//사용자 수정페이지(/test03/modMemberForm.jsp)를 호출(포워딩)하기 위하여
			//"/test03/modMemberForm.jsp" 경로를 nextPage 변수에 담습니다.
			nextPage="/test03/modMemberForm.jsp";
		}
		  //아래의 else if {} 는
		  //action 값이 /modMember.do 이면, modMemberForm을 거쳐 
		  //전송된 사용자 정보를 수정한 후 
		  //맴버 목록 정보를 호출(포워딩)하는 작업을 수행합니다.
		  else if(action.equals("/modMember.do")){
			//사용자 수정페이지(/test03/modMemberForm.jsp)로 부터 전달받은
			//VO 객체로부터 각 매개변수의 값을 호출하여 변수에 저장합니다.
			String id=request.getParameter("id");
			//System.out.println("아이디(id)="+ id);
			
			String afterModPwd=request.getParameter("pwd");
			
			System.out.println("수정 후 암호="+ afterModPwd);
			String name= request.getParameter("name");
			String afterModEmail= request.getParameter("email");
			Date joinDate= (Date) request.getAttribute("joinDate");
			System.out.println("joinDate="+ joinDate);

			//전달받은 값을 VO 객체에 저장합니다.
			MemberVO memberVO = new MemberVO(id, afterModPwd, name, afterModEmail, joinDate);
			
			//MemberDAO 클래스의 modMember() 메소드를 호출하여 
			//수정된 VO 객체의 값으로 맴버 정보를 수정합니다.
			memberDAO.modMember(memberVO);
			
			//request에 수정완료 메시지를 msg 매개변수로 설정합니다. 
			request.setAttribute("msg", "memModifiedSuccess");
			
			//사용자 목록페이지(/member/listMembers.do)를 호출(포워딩)하기 위하여
			//"/member/listMembers.do" 경로를 nextPage 변수에 담습니다.
			nextPage="/member170202/listMembers.do";
		}
		  //아래의 else if {} 는
		  //action 값이 /delMember.do 이면, 
		  //맵버ID를 이용하여 맴버를 삭제한 후,
		  //맴버 목록 정보를 호출(포워딩)하는 작업을 수행합니다.
		  else if(action.equals("/delMember.do")){
			//회원 아이디 값을 가져와서 mid 변수에 저장
			String id=request.getParameter("id");
			
			//MemberDAO 클래스의 delMember() 메소드를 호출하여 
			//맴버ID에 해당하는 맴버 정보를 삭제합니다.
			memberDAO.delMember(id);

			//request에 삭제완료 메시지를 msg 매개변수로 설정합니다.
			request.setAttribute("msg", "memDeletedSuccess");
			
			//사용자 목록페이지(/member/listMembers.do)를 호출(포워딩)하기 위하여
			//"/member/listMembers.do" 경로를 nextPage 변수에 담습니다.
			nextPage="/member170202/listMembers.do";
		}
		   //아래의 else {}는 action 값이 
		   // null, /listMembers.do, /addMember.do, /memberForm.do,
		   // /modMemberForm.do, /modMember.do, /delMember.do 가 아닐 경우,
		   //맴버의 목록정보를 호출하는 jsp 페이지를 호출(포워드)하여 브라우저에 표시합니다.
		  else {
			//맴버 정보를 membersList 객체에 저장합니다.
			List<MemberVO> membersList = memberDAO.listMembers();
		
			//membersList 객체를  request에 memsList 매개변수(속성)로 설정합니다.
			request.setAttribute("memsList", membersList);
				
			//"/test02/listMembers.jsp"로 포워딩하기 위하여
			//"/test02/listMembers.jsp" 경로를 nextPage 변수에 담습니다.
			/* nextPage = "/test03/listMembers.jsp"; */
			nextPage = "/member170202/listMembers.do";
		}
		//위의 각 사용자 요청에 해당하는(nextPage 변수에 저장된 경로와모듈이름) 
		//서블릿이나 JSP 페이지를 호출(포워드)합니다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
}