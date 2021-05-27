package sec03.brd01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board170301/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardService boardService;
	
	ArticleVO articleVO;

	public void init(ServletConfig config) throws ServletException {
		//서블릿 초기화 시 BoardService 객체를 생성합니다.
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	
			throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//boardService = new BoardService();
		
		//호출(포워딩)할 단위기능에 대한 경로/모듈정보의 문자열이 저장될 변수 선언 
		String nextPage = null;
		
		//request로부터 요구된 작업이름의 정보를 가져와서 
		//문자열 변수인 action에 저장 
		String action = ""; 
		action = request.getPathInfo();//요청 작업명을 가져옵니다.
		System.out.println("action:" + action);
		
		try {
			//게시판 정보를 저장할 ArrayList 객체를 생성합니다.
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			
			//아래의 if{}는 request에 요청된 작업이 없을 때(action값이 널) 
			//전체 게시판 글을 표시합니다.
			if((action == null) || (action.equals("/")) || (action.equals("/listArticles.do"))) {
				//BoardService의 listArticles()메소드를 호출하여
				//게시판 글 정보를 저장합니다.
				articlesList = boardService.listAllArticles();
				
				//request에 articlesList 속성으로, 
				//읽어 온  게시판 글 정보를 설정합니다.
				request.setAttribute("articlesList", articlesList);
				
				//호출(포워딩)할 페이지 위치정보를 nextPage문자열에 저장합니다.
				//이 후에, dispatcher를 통해 /board02/listArticles.jsp가 호출(포워딩)되어
				//읽어온 게시판 글을 표시됩니다.
				nextPage = "/board02/listArticles.jsp";

			}  
			//위의 각 사용자 요청에 해당하는(nextPage 변수에 저장된 경로와모듈이름) 
			//서블릿이나 JSP 페이지를 호출(포워드)합니다.
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
