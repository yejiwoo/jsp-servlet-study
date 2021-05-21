package sec04_seq.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/file170401/downloads.do")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String ARTICLE_IMAGE_REPO = "C:\\boards\\article_images";

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
		
		//이미지 파일 이름과 글 번호를 가져옵니다.
		String imageFileName = (String) request.getParameter("imageFileName");
		String articleNO = request.getParameter("articleNO");
		System.out.println("imageFileName=" + imageFileName);
		
		//response.OupPutStream 메소드를 이용하는 OutputStream 객체를 생성합니다.
		OutputStream out = response.getOutputStream();
		
		//글 번호에 대한 파일 경로를 설정하여 path 변수에 저장합니다.
		String articlefileName = ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + imageFileName;
		
		//위에서 생성한 image파일을 액세스할 수 있는 File 객체를 생성합니다.
		File fileNameRrc = new File(articlefileName);
		
		//브라우저가 캐시 기능을 사용하지 않도록 설정합니다.
		response.setHeader("Cache-Control", "no-cache");
		//이미지 파일을 내려받는데 필요한 response 해더 정보를 설정합니다.
		response.addHeader("Content-disposition", "attachment;fileName=" + imageFileName);
		
		//표시할 이미지파일을 읽어올 FileInputStream 객체를 생성합니다.
		FileInputStream inImageFile = new FileInputStream(fileNameRrc);
		
		//버퍼을 이용해 한번에 8KB씩 전송합니다.
		byte[] fileReadBuffer = new byte[1024 * 8];
		while (true) {
			int count = inImageFile.read(fileReadBuffer);
			if (count == -1)
				break;
			out.write(fileReadBuffer, 0, count);
		}
		inImageFile.close();
		out.close();
	}

}
