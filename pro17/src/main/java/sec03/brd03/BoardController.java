package sec03.brd03;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/board170303/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//글에 첨부한 이미지 저장 위치를 상수로 선언(클래스 내부에서만 사용됨)
	private static final String ARTICLE_IMAGE_REPO = "C:\\board\\article_images";

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
		
		//request로부터 요구된 작업 이름의 정보를 가져와서 
		//문자열 변수인 action에 저장
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		try {
			//게시판 정보를 저장할 ArrayList 객체를 생성.
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();

			//아래의 if{}는 request에 요청된 작업이 없을 때(action값이 널) 또는 
			//action 값이 /listArticles.do 일 때,
			//전체 게시판 글을 표시합니다.
			if((action == null) || 
			   (action.equals("/")) ||
			   (action.equals("/listArticles.do"))) {
				//BoardService의 listArticles()메소드를 호출하여
				//가져온 게시판 글 정보를 리스트 객체에 저장합니다.
				articlesList = boardService.listAllArticles();
				//request에 articlesList 속성으로, 
				//읽어 온  게시판 글 정보를 설정합니다.
				request.setAttribute("articlesList", articlesList);
				//호출(포워딩)할 페이지 위치정보를 nextPage문자열에 저장합니다.
				//이 후에, dispatcher를 통해 /board02/listArticles.jsp가 호출(포워딩)되어
				//읽어온 게시판 글을 표시됩니다.
				nextPage = "/board02/listArticles.jsp";
				//RequestDispatcher dispatcherer = request.getRequestDispatcher("/board02/listArticles.jsp");
				//dispatcherer.forward(request, response);
			} //아래의 else if{}는  action 에/articleForm.do 값이 있을 때
			  //글쓰기 창을 호출합니다.
			  else if (action.equals("/articleForm.do")) {
				//호출(포워딩)할 페이지 위치정보를 nextPage문자열에 저장합니다.
				//이 후에, dispatcher를 통해 /board02/articleForm.jsp가 
				//호출(포워딩)됩니다.
				nextPage = "/board02/articleForm.jsp";
				//RequestDispatcher dispatcherer = request.getRequestDispatcher("/board02/articleForm.jsp");
				//dispatcherer.forward(request, response);
			} //아래의 else if{}는  action 에/addArticle.do 값이 있을 때
			  //새글 추가 작업(글쓰기 창의 내용을 데이터베이스에 저장)을 수행합니다.
			  else if (action.equals("/addArticle.do")) {
				//새글 번호를 저장할 변수를 선언합니다.
				int articleNO=0;
				//첨부 이미지 파일 업로드를 위해  upload() 메소드를 호출하여
				//파일 업로드 및 업로드 파일과 같이 전달된 새글정보를  
				//키와 값으로 구성된 Map 객체에 저장합니다.
				//upload 메소드는 아래에 정의되어 있습니다.
				Map<String, String> articleMap = upload(request, response);
				
				//articleMap에 저장된 글 정보를 다시 가져옵니다.
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				//VO 객체에 글정보를 저장합니다.
				articleVO.setParentNO(0);	//새글의 부모 글 번호를 0으로 설정합니다.
				articleVO.setId("hong");	//새글 작성자 ID를 hong으로 설정합니다.
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);

				//BoardService 클래스의 addArticle 메소드를 호출하여
				//새글(articleVO) 추가 작업(데이터베이스에 저장)을 수행하고,
				//반환 받은 새글에 대한 글번호를 articleNO를 저장합니다.
				articleNO= boardService.addArticle(articleVO);
				
				//이미지 파일을 첨부한 경우에만 수행합니다.
				if(imageFileName!=null && imageFileName.length()!=0) {
					//temp 폴더가 포함된 임시로 업로드 된 파일 객체를 생성합니다.
				    File srcFile = new 	File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+imageFileName);
				    //새로 생성할 디렉토리 이름이 저장된 파일 객체를 생성합니다.
					File destDir = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO);
					//ARTICLE_IMAGE_REPO 디렉토리에 글번호의 폴더를 생성합니다.
					destDir.mkdirs();
					//temp 폴더의 파일을 생성된 글번호 폴더로 이동시킵니다.
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				
				PrintWriter pw = response.getWriter();
				//새 글 등록 메시지를 나타낸 후, 자바스크립  location 객체의 href 속성을 이용해
				//글 목록을 요청합니다.
				pw.print("<script>" 
				         +"  alert('새글을 추가했습니다.');" 
						 +"  location.href='"+request.getContextPath()+"/board170303/listArticles.do';"
				         +"</script>");

				//return;
			} //if-End

			RequestDispatcher dispatcherer = request.getRequestDispatcher(nextPage);
			dispatcherer.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//게시판 이미지 파일 업로드
	private Map<String, String> upload(HttpServletRequest request, 
			   						   HttpServletResponse response) 
			   							   throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
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
				
				//리스트 객체로부터 아이템 하나(새글 정보 중 항목하나)를 가져와서 FileItem 타입 변수에 저장 
				FileItem fileItem = (FileItem) items.get(i);
				
				//가져온 아이템이 폼필드(입력값들)의 항목인 경우
				if (fileItem.isFormField()) {
					//가져온 아이템의 필드이름과 UTF-8로 인코딩하여 해당 값을 콘솔에 표시 
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					
					//폼필드 각 항목을   HashMap에 (key, value)로 저장.
					//즉, 새 글과 관련된 title, content, 업로드 파일이름을 Map에 저장합니다.
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				} //위의 if 절은, 폼 필드로 전송된 새글 정보의 각 항목을 (key, value)로 Map에 저장.
				//아래 else 절은, 폼 필드가 아니면, 즉, 파일이면 파일 업로드 기능을 수행합니다.
				  else {
					//업로드된 파일이름에 대한 매개변수 이름을 콘솔에  표시
					System.out.println("파라미터명:" + fileItem.getFieldName());
					//업로드된 파일이름을 콘솔에 표시
					System.out.println("파일명:" + fileItem.getName());
					//업로드된 파일의 크기를 콘솔에 표시
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					
					//아래의 if 절은 업로드한 파일이름을 가져와서 업로드를 수행합니다.
					//업로드 파일의 크기가 0보다 크면, 다음을 수행
					if (fileItem.getSize() > 0) {
						//파일이름에서 마지막 \\위치 확인
						//\\이 없는 경우,  idx에 -1이 할당
						int idx = fileItem.getName().lastIndexOf("\\");
						
						//idx 변수값이 -1인 경우 다음을 수행
						if (idx == -1) {
							// 파일이름에서 마지막 /위치 확인
							// /이 없는 경우,  idx에 -1이 할당
							idx = fileItem.getName().lastIndexOf("/");
						}
						//idx 값에 상관없이 다음은 무조건 수행
						//fileItem의 파일이름에서 idx+1 한 위치부터 끝까지
						//추출된 문자열을 fileName변수에 저장
						String fileName = fileItem.getName().substring(idx + 1);
						//콘솔에 업로드되는 파일이름 표시 
						System.out.println("파일명:" + fileName);
						//articleMap에 파라미터이름과 파일이름을 저장합니다.
						//업로드된 파일의 파일이름을 Map에 (key, value)형식의 
						//("imageFileName","업로드파일이름")로 저장합니다.
						articleMap.put(fileItem.getFieldName(), fileName);  //익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장
						
						//업로드되는 파일 정보(경로+파일이름)가 저장된 File 객체 생성(temp 폴더 삽입)
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						
						//최종 업로드되는 파일이름(경로+이름) FileItem 객체를 저장
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
