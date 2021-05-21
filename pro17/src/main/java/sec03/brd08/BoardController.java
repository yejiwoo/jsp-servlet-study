package sec03.brd08;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
//import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/board170308/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//글에 첨부한 이미지 저장 위치를 상수로 선언(클래스 내부에서만 사용됨)
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_images";

	//BoardService 및 ArticleVO 클래스 타입 필드(맴버변수)를 선언 
	private BoardService boardService;
	private ArticleVO articleVO;

	public void init(ServletConfig config) throws ServletException {
		//서블릿 초기화 시 BoardService 객체와 ArticleVO 객체를 생성합니다.
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, 
			  HttpServletResponse response) 
					  throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		//호출(포워딩)할 단위기능에 대한 경로/모듈정보의 문자열이 저장될 변수 선언
		String nextPage = "";
		
		//답글에 대한 부모 글번호를 저장하기 위해 세션 객체를 사용합니다.
		HttpSession session;
		
		//request의 작업정보
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		try {
			//전체 게시판 글을 표시합니다.
			if ((action == null) || 
				(action.equals("/")) ||
				(action.equals("/listArticles.do"))) {
								
				//최초 요청 시 또는 /listArticle.do 로 요청 시
				//section 값과 pageNum 값을 구해서 변수에 저장합니다.
				String _section = request.getParameter("section");
				String _pageNum = request.getParameter("pageNum");
				
				//최초 요청 시 section 값과 pageNum 값이 없으면, //각각 1로 초기화합니다.				
				int section = 
						Integer.parseInt(((_section == null)? "1":_section));
				int pageNum = 
						Integer.parseInt(((_pageNum == null)? "1":_pageNum));
				
				//section 값과 pageNum 값을 HashMap에 저장한 후, 메소드로 넘깁니다.
				Map<String, Integer> pagingMap = 
						new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				
				//section 값과 pageNum값으로 해당 섹션과 페이지에 해당되는 글 목록을 조회합니다.
				Map<String, Object> articlesMap = 
						boardService.listArticles(pagingMap);
				
				//브라우저에서 전송된 section과 pageNum 값을 articlesMap에 저장한 후,
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				
				//조회된 글 목록을 articlesMap으로 바인딩합니다.
				request.setAttribute("articlesMap", articlesMap);
				
				
				///board07/listArticles.jsp을 호출(포워딩)하여 
				//바인딩된 articlesMap을 포워딩합니다.
				nextPage = "/board07/listArticles.jsp";
				
								
			}//글쓰기 창을 호출합니다.
			 else if (action.equals("/articleForm.do")) {
				nextPage = "/board07/articleForm.jsp";
				
			}//새글 추가 작업(글쓰기 창의 내용을 데이터베이스에 저장)
			 else if (action.equals("/addArticle.do")) {
				int articleNO=0;

				//파일 upload 메소들 호출하여 입력값을 Map객체에 저장
				Map<String, String> articleMap = upload(request, response);
				
				//articleMap에 저장된 글 정보를 다시 가져와서 변수에 저장합니다.
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				//VO 객체에 글정보를 저장합니다.
				articleVO.setParentNO(0);	//새글의 부모 글 번호를 0으로 설정합니다.
				articleVO.setId("hong");	//새글 작성자 ID를 hong으로 설정합니다.
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);

				//반환 받은 새글에 대한 글번호를 articleNO를 저장합니다.
				articleNO= boardService.addArticle(articleVO);
				//이미지 파일을 첨부한 경우에만 수행합니다.
				if(imageFileName!=null && imageFileName.length()!=0) {
				    //temp 폴더에 임시로 업로드 된 파일 객체를 생성합니다.
				    File srcFile = new 	File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+imageFileName);
				    //새로 생성할 디렉토리 이름이 저장된 파일 객체를 생성합니다.
				    File destDir = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO);
				    //ARTICLE_IMAGE_REPO 디렉토리에 글번호의 폴더를 생성합니다.
					//destDir.mkdirs();
					//temp 폴더의 파일을 생성된 글번호 폴더로 이동시킵니다.
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					//temp 폴더에 저장된 파일을 삭제합니다.
					//srcFile.delete();
				}
				
				//아래의 자바 스크립트 코드를 작성할 PrintWriter 객체를 생성합니다.
				PrintWriter pw = response.getWriter();
				//새 글 등록 메시지를 나타낸 후, 글 목록을 요청하는 자바스크립트를
				//전송
				pw.print("<script>" 
				         +" alert('새글을 추가했습니다.');" 
						 +" location.href='"+request.getContextPath()+"/board170308/listArticles.do';"
				         +"</script>");

				return; //dispatcher 포워딩이 실행되지 않습니다.
				
			}//글 상세 정보 표시(글목록에서 상세 정보 표시 호출)
			 else if(action.equals("/viewArticle.do")){
				//request의 articleNO 값 저장
				int articleNO = Integer.parseInt((String) request.getParameter("articleNO"));

				//상세 글 정보 데이터 저장
				articleVO = boardService.viewArticle(articleNO);

				//반환된 articleVO를 article 속성으로 request에 바인딩합니다.
				request.setAttribute("articleVO",articleVO);

				//호출(포워딩)페이지 이름 저장
				nextPage = "/board07/viewArticle.jsp";
				
			}//수정된 사용자의 글을 데이터베이스에 저장시킵니다. 
			  else if (action.equals("/modArticle.do")) {
				
				//첨부 이미지 파일 업로드를 위해  upload() 메소드를 호출하여
				//파일 업로드 및 업로드 파일과 같이 전달된 수정된 글정보를  
				//키와 값으로 구성된 Map 객체에 저장합니다.
				//upload 메소드는 아래에 정의되어 있습니다.
				Map<String, String> articleMap = upload(request, response);
				
				//맵 정보로부터 글번호를 가져와 정수로 파싱한 후, articleNO 변수에 저장합니다.
				int articleNO = Integer.parseInt((String) articleMap.get("articleNO"));
								
				
				//맵 객체로부터 title, content, imageFileName 키의 값을 가져와
				//변수에 저장합니다.
				String title = (String) articleMap.get("title");
				String content = (String) articleMap.get("content");
				String imageFileName = (String) articleMap.get("imageFileName");
				
				//VO 객체에 수정된 글정보를 setter를 이용하여 저장합니다.
				articleVO.setArticleNO(articleNO);
				articleVO.setParentNO(0);	//수정 글의 부모 글 번호를 0으로 설정합니다.
				articleVO.setId("hong");	//수정 글의 작성자 ID를 hong으로 설정합니다.
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				//BoardService 클래스의 modArticle 메소드를 호출하여
				//수정글(articleVO)을 데이터베이스에 저장합니다.
				boardService.modArticle(articleVO);
				
				//수정글에 이미지 파일이 첨부된 경우에만 수행합니다.
				if (imageFileName != null && imageFileName.length() != 0) {
					
					//수정글에 originalFileName 키의 수정 전 이미지 파일이름을 저장합니다.
					String originalFileName = articleMap.get("originalFileName");
					
					//temp 폴더에 임시로 새로 업로드 된 파일 객체를 생성합니다.
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					
					//새로 생성할 디렉토리 이름이 저장된 파일 객체를 생성합니다.
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					
					//ARTICLE_IMAGE_REPO 디렉토리에 글번호의 폴더를 생성합니다.
					//destDir.mkdirs();
					
					//temp 폴더의 새로(수정된) 업로드 된 파일을 
					//생성된 글번호 폴더로 이동시킵니다.
					FileUtils.moveFileToDirectory(srcFile, destDir, true);

					
					//전송된 originalImageFileName을 이용해 기존의 파일을 삭제합니다.
					//새로 생성한 사용자 글번호 디렉토리에 기존 파일에 대한 객체를 생성합니다.
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
					
					//이전 파일을 삭제합니다.
					oldFile.delete();
					//temp 폴더에 저장된 파일을 삭제합니다.
					//srcFile.delete();
				}
				//아래의 자바 스크립트 코드를 작성할 PrintWriter 객체를 생성합니다.
				PrintWriter pw = response.getWriter();

				
				//아래의 자바스크립트는 새 글 등록 메시지를 나타낸 후, 
				//자바스크립트의  location 객체의 href 속성을 이용해
				//글 상세 화면을 나타냅니다.
				pw.print("<script>" 
				         +" alert('글을 수정했습니다.');" 
						 +" location.href='"
				         + request.getContextPath()
				         +"/board170308/viewArticle.do?articleNO="+ articleNO + "';"
				         +"</script>");
				return;
			}//글 삭제 (해당 글의 자식글까지 삭제, viewArticle.jsp 페이지 요청으로 삭제됨) 
			  else if (action.equals("/removeArticle.do")) {
				  
				//맵 정보로부터 글번호를 가져와 정수로 파싱한 후, articleNO 변수에 저장합니다.
				//int articleNO = Integer.parseInt((String) request.getParameter("articleNO"));
				String strArticleNO = (String) request.getParameter("articleNO");
					
				System.out.println(strArticleNO);
				
				int articleNO = Integer.parseInt(strArticleNO);	
				
				//삭제 글 및 자식 글의 글번호를 저장하기 위한 리스트 객체 생성
				List<Integer> articleNOList = boardService.removeArticle(articleNO);
				
				//삭제된 글번호들의 업로드 파일들을 for문을 이용해 삭제합니다. 
				for (int _articleNO : articleNOList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
					
					if (imgDir.exists()) {
						//디렉토리 내 파일까지 모두 삭제
						FileUtils.deleteDirectory(imgDir);
					}
				}
				
				//아래의 자바 스크립트 코드를 작성할 PrintWriter 객체를 생성합니다.
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
				         +" alert('글을 삭제했습니다.');" 
						 +" location.href='"
				         + request.getContextPath()
				         +"/board170308/listArticles.do';"
				         +"</script>");
				return;//추가적인 포워딩이 수행되지 않습니다.
				
				
				//답글도 새 글이므로 답글 기능도 새 글 쓰기 기능과 비슷합니다. 
				//다른 점은 답글창 요청(/replyForm.do) 시, 미리 부모 글 번호를 parentNO 속성으로 
				//세션(session)에 저장해 놓고, 답글을 작성한 후 등록을 요청(/addReply.do)하면 
				//세션에서 parentNO를 가져와 테이블에 추가한다는 점입니다.

			} //아래의 else if{}는  action에 /replyForm.do 값이 있을 때
	          //답글을 작성할 수 있는 답글쓰기 창이 호출됩니다.
			
			  //답글도 새 글이므로 답글 기능은 새 글 쓰기 기능과 비슷합니다. 
	          //다른 점은, 답글창 요청(/replyForm.do) 시, 
	          //미리 부모 글 번호를 parentNO 속성으로 session 객체에 저장해 놓고, 
	          //답글을 작성한 후, 답글의 등록이 요청(/addReply.do)되면, 
	          //session 객체로부터 parentNO를 가져와, 답글의 정보로 테이블에 추가한다는 점입니다.
			  else if (action.equals("/replyForm.do")) {
			  
				//부모글 번호를 가져와 변수에 저장합니다. 
				int parentNO = Integer.parseInt((String) request.getParameter("parentNO"));
				
				System.out.println(parentNO);
				
				//세션 객체를 호출합니다. 
				session = request.getSession();
				
				//세션 객체에 parentNO 속성으로 부모글번호를 바인딩합니다.
				session.setAttribute("parentNO", parentNO);
				
				//호출(포워딩)할 페이지 위치정보를 nextPage문자열에 저장합니다.
		        //이 후에, dispatcher를 통해 /board06/replyForm.jsp가 호출(포워딩)되어
		        //답글입력창이 표시됩니다.
				nextPage = "/board07/replyForm.jsp";
				
			} //아래의 else if{}는  action에 /addReply.do 값이 있을 때
	          //작성된 답글을 데이터베이스에 저장합하고 글목록 페이지가 호출됩니다. 
	          else if (action.equals("/addReply.do")) {
	        	  
	        	//세션 객체를 호출합니다.
				session = request.getSession();
				
				//세션 객체로부터 부모글 번호를 가져와 변수에 저장합니다.
				int parentNO = (Integer) session.getAttribute("parentNO");
				
				//세션 객체로부터 부모글 번호가 바인딩된 parentNO 속성을 제거합니다.
				session.removeAttribute("parentNO");
				
				//답글 입력 시, 첨부 이미지 파일 업로드를 위해  upload() 메소드를 호출하여
		        //파일 업로드 및 업로드 파일과 같이 전달된 수정된 글정보를  
		        //키와 값으로 구성된 Map 객체에 저장합니다.
		        //upload 메소드는 아래에 정의되어 있습니다.
				Map<String, String> articleMap = upload(request, response);
				
				//맵 객체로부터 title, content, imageFileName 키의 값을 가져와
		        //변수에 저장합니다.
				String title = articleMap.get("title");
				String content = articleMap.get("content");

				//업로드하는 이미지파일이름을 저장합니다.
				String imageFileName = articleMap.get("imageFileName");
				
				//파일이름에 특수문자가 있을 경우, 파일이름을 UTF-8로 인코딩합니다
				if (imageFileName != null) {
					imageFileName = URLEncoder.encode(imageFileName, "UTF-8");
				}
		        								
				//VO 객체에 작성된 답글정보를 저장합니다.
				articleVO.setParentNO(parentNO);
				articleVO.setId("lee");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				//boardService.addReply() 메소드를 호출하여 , 답글 VO를
		        //데이터베이스에 저장하고, 글번호를 반환받습니다.
				int articleNO = boardService.addReply(articleVO);
				
				//아래의 if{}는 이미지 파일을 첨부한 경우에만 수행합니다.
		        //imageFileName에 값이 있으면,
				if (imageFileName != null && imageFileName.length() != 0) {
					
					//임시로, 사용자에 의해 서버에 업로드된 파일 객체를 temp 폴더에 생성합니다.
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					
					//디렉토리 정보(사용자 글번호 디렉토리가 포함)가 저장된 파일 객체를 새로 생성합니다.
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					
					//ARTICLE_IMAGE_REPO 디렉토리에 사용자의 글번호 폴더를 생성합니다.
					destDir.mkdirs();
					
					//temp 폴더의 업로드 된 파일을 새로 생성된 글번호 폴더로 이동시킵니다.
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					
					//temp 폴더에 저장된 업로드 파일을 삭제합니다.
					srcFile.delete();
				}
				
				//아래의 자바 스크립트 코드를 작성할 PrintWriter 객체를 생성합니다.
				PrintWriter pw = response.getWriter();
				
				//아래의 자바스크립트는 브라우저의 경고창에 글 삭제 완료 메시지를 나타낸 후, 
				//자바스크립트  location 객체의 href 속성을 이용해
				//글 목록을 요청합니다.
				pw.print("<script>" 
						+ " alert('답글을 추가했습니다.');" 
						+ " location.href='" + request.getContextPath()
						+ "/board170308/viewArticle.do?articleNO="+articleNO+"';"
						+ "</script>");
				return; //추가적인 포워드가 발생되지 않습니다.
			}
			
			//위의 각 작업에 대한 요청된 페이지를 포워딩합니다.
			//단 return 문으로 끝난 경우, 아래의 포워딩은 수행되지 않습니다.
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//게시판 글 등록 및 수정 시 관련된 이미지 파일업로드메소드 입니다.
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//이미지 파일과 같이 전송된 새글 정보를 (key, value)로 저장할 Map 객체를 생성합니다.
		Map<String, String> articleMap = new HashMap<String, String>();
		
		//인코딩 값을 변수로 생성해 놓음.
		String encoding = "utf-8";
		
		//File 클래스는 파일이나 폴더에 대한 제어를 하는데 사용되는 클래스입니다. 
		//파일이 업로드 되어 저장되는 디렉토리 경로가 저장된 File 클래스 객체를 생성합니다. 
		//즉, 업로드 경로를 저장합니다.
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		
		//DiskFileItemFactory 클래스는 업로드 된 파일이 저장되는 저장소와 관련된 작업을
		//처리하는 클래스입니다
		//업로드 되는 파일이 저장되는 저장소를 구성하기 위하여 
		//DiskFileItemFactory 클래스 객체인 factory를 생성합니다.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//저장소로 사용될 factory 객체에, 업로드 되는 파일이 저장될 디렉토리가 설정된 File객체를
		//설정합니다. ==> 업로드 파일이 저장되는 디렉토리를 저장소 객체에 설정
		factory.setRepository(currentDirPath);
		
		//저장소에 최대 업로드 가능한 파일 크기(1MB) 설정.
		factory.setSizeThreshold(1024 * 1024);
		
		//저장소 객체에 업로드 요청을 처리할 ServletFileUpload 클래스 객체 생성 
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			
			//업로드 요청을 파싱하여, 업로드 되는 FileItem 객체가 저장된 리스트 객체 생성
			//--> request에서 매개변수들(새 게시판글과 관련된 정보)이 저장된 리스트를 생성합니다. 
			List<FileItem> items = upload.parseRequest(request);
			
			for (int i = 0; i < items.size(); i++) {
				
				//파일리스트로부터 아이템 하나(새글 정보 중 항목하나)를 가져와서 
				//FileItem 타입 변수에 저장 
				FileItem fileItem = (FileItem) items.get(i);
				
				//가져온 아이템이 Form(폼)으로부터 입력되어 전달된 항목인 경우
				//즉, 게시판 글정보 인 경우,
				if (fileItem.isFormField()) {
					
					//UTF-8로 인코딩하여 가져온 글정보의 각 필드이름과  
					//해당 값을 콘솔에 표시 
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));

					//파일 업로드로 같이 전송된 새 글 관련된 각 항목을 
					//HashMap에 (key, value)로 저장.
					//즉, 새 글 정보를 Map에 저장합니다.
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				} //위의 if 절은, 폼 필드로 전송된 새글 정보의 각 항목을 (key, value)로 Map에 저장.

				  //아래 else 절은, 파일 크기가 0 보다 큰 경우 업로드 수행
			  	  else {
			  		  
			  		//업로드된 파일이름에 대한 매개변수 이름을 콘솔에  표시
					System.out.println("파라미터명:" + fileItem.getFieldName());
					
					//업로드된 파일이름을 콘솔에 표시
					System.out.println("파일명:" + fileItem.getName());
					
					//업로드된 파일의 크기를 콘솔에 표시
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					
					//articleMap.put(fileItem.getFieldName(), fileItem.getName());
					
					//아래의 if 절은 업로드한 파일이름을 가져와서 업로드를 수행합니다.
					//업로드 파일의 크기가 0보다 크면, 다음을 수행
					if (fileItem.getSize() > 0) {
						
						//익스플로러에서 업로드 파일의 경로를 제거하기 위하여 이 코드가 포함됨
						//파일이름에서 마지막 \\위치 확인
						//\\이 없는 경우, idx에 -1이 할당
						int idx = fileItem.getName().lastIndexOf("\\");
						
						//idx 변수값이 -1인 경우 다음을 수행
						if (idx == -1) {
							
							// 파일이름에서 마지막 /위치 확인
							// /이 없는 경우,  idx에 -1이 할당
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						//fileItem의 파일이름에서 idx+1 한 위치부터 끝까지
						//추출된 문자열(경로명이 없는 파일이름)을 fileName변수에 저장
						String fileName = fileItem.getName().substring(idx + 1);
						
						//콘솔에 파일이름 표시
						System.out.println("파일명:" + fileName);
						
						//articleMap에 파라미터이름과 파일이름을 저장합니다.
						//업로드된 파일의 파일이름을 Map에 (key, value)형식의 
						//("imageFileName","업로드파일이름")로 저장합니다.
						articleMap.put(fileItem.getFieldName(), fileName);  //익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장
						
						// 업로드되는 파일 정보(경로+파일이름)가 저장된 File 객체 생성
						//임시 디렉토리에 업로드 파일 객체를 생성합니다.
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						
						//최종 업로드되는 파일이름(경로+이름)으로 FileItem 객체를 서버에 생성 저장
						fileItem.write(uploadFile);

					} // end if
					  //업로드 한 파일이 존재하는 경우, 업로드한 파일의 파일이름으로
					  //저장소에 업로드합니다.
					  //if 파일크기가 0보다 크지 않으면, if 절 전체를 수행하지 않습니다.
					
				} // end else  //파일업로드 기능을 수행합니다.
				
			} // end for  // 업로드한 파일 전체에 대하여 반복됨.
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;//파일 업로드 완료 후, 새글 정보 및 업로드 파일 정보가 저장된 
	}					  //articleMap 을 반환합니다.
}