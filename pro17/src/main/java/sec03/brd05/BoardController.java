package sec03.brd05;

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

@WebServlet("/board170305/*")
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

		//request의 작업정보
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		try {
			
			//게시판 정보를 저장할 ArrayList 객체를 생성.
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();

			//전체 게시판 글을 표시
			if((action == null) || 
			   (action.equals("/")) ||
			   (action.equals("/listArticles.do"))) {

				articlesList = boardService.listArticles();

				request.setAttribute("articlesList", articlesList);

				nextPage = "/board04/listArticles.jsp";
				
			}//글쓰기 창을 호출
			 else if (action.equals("/articleForm.do")) {
				nextPage = "/board04/articleForm.jsp";
				
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
						 +" location.href='"+request.getContextPath()+"/board170305/listArticles.do';"
				         +"</script>");

				return; //dispatcher 포워딩이 실행되지 않습니다.
				
			}//글 상세 정보 표시(글목록에서 상세 정보 표시 호출)
			 else if(action.equals("/viewArticle.do")){
				//request의 articleNO 값 저장
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));

				//상세 글 정보 데이터 저장
				articleVO = boardService.viewArticle(articleNO);

				//반환된 articleVO를 article 속성으로 request에 바인딩합니다.
				request.setAttribute("articleVO",articleVO);

				//호출(포워딩)페이지 이름 저장
				nextPage = "/board04/viewArticle.jsp";
			} //아래의 else if{}는  action에 /modArticle.do 값이 있을 때
			  //수정된 사용자의 글을 데이터베이스에 저장시킵니다. 
			  else if (action.equals("/modArticle.do")) {
				
				//첨부 이미지 파일 업로드를 위해  upload() 메소드를 호출하여
				//파일 업로드 및 업로드 파일과 같이 전달된 수정된 글정보를  
				//키와 값으로 구성된 Map 객체에 저장합니다.
				//upload 메소드는 아래에 정의되어 있습니다.
				Map<String, String> articleMap = upload(request, response);
				
				//맵 정보로부터 글번호를 가져와 정수로 파싱한 후, articleNO 변수에 저장합니다.
								
				//VO 객체에 setter를 이용하여 articleNO 값을 저장합니다.
								
				//맵 객체로부터 title, content, imageFileName 키의 값을 가져와
				//변수에 저장합니다.
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				//VO 객체에 수정된 글정보를 저장합니다.
				articleVO.setArticleNO(articleNO);
				articleVO.setParentNO(0);	//수정 글의 부모 글 번호를 0으로 설정합니다.
				articleVO.setId("hong");	//수정 글의 작성자 ID를 hong으로 설정합니다.
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				//전송된 수정글 정보를 이용해 글을 수정합니다.
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
				         +"/board170305/viewArticle.do?articleNO="+ articleNO + "';"
				         +"</script>");
				return;
			}//else if (action.equals("/modArticle.do")) 종료

			//각 작업과 관련되어 필요한 모듈을 호출(포워딩)합니다. 
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
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
		
		//인코딩 값을 변수로 생성해 놓음.
		String encoding = "utf-8";
		
		//File 클래스는 파일이나 폴더에 대한 제어를 하는데 사용되는 클래스입니다. 
		//업로드 경로를 저장합니다.
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		
		//업로드 저장소 리소스 구성 
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//저장소 설정
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		
		//업로드 요청 처리 객체 생성 
		ServletFileUpload upload = new ServletFileUpload(factory);

		
		//이미지 파일과 같이 전송된 새글 정보를 (key, value)로 저장할 Map 객체를 생성합니다.
		Map<String, String> articleMap = new HashMap<String, String>();
		
		try {
			//새 게시판 입력 내용 정보 저장 
			List<FileItem> items = upload.parseRequest(request);
			
			for (int i = 0; i < items.size(); i++) {
				//입력정보 각각을 저장 
				FileItem fileItem = (FileItem) items.get(i);
				
				//항목의 입력 정보가 사용자 입력 폼인 경우
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					//입력 정보 항목을 Map 객체에 저장
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				}//사용자 입력 값이 아닌 파일 인 경우
			  	  else {
					System.out.println("파라미터명:" + fileItem.getFieldName());
					System.out.println("파일명:" + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					//업로드 파일이 있는 경우 수행됨
					if (fileItem.getSize() > 0) {
						//경로명을 제외한 파일이름 저장
						//윈도우즈
						int idx = fileItem.getName().lastIndexOf("\\");
						//idx 변수값이 -1인 경우
						if (idx == -1) {
							//리눅스인 경우
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("파일명:" + fileName);
						//파일이름을 맵 객체에 추가
						articleMap.put(fileItem.getFieldName(), fileName);  //익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장

						//업로드되는 파일 정보(경로+파일이름)가 저장된 File 객체 생성
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						//업로드 수행
						fileItem.write(uploadFile);

					} // end if
				} // end else
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap; 
	}
}