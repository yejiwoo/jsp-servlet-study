package sec03.brd08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;

	public BoardService() {
		//BoardService 생성 시, BoardDAO 객체도 생성됨.
		boardDAO = new BoardDAO();
	}

	//글 목록과 글 총수 
	public Map<String, Object> listArticles(Map<String, Integer> pagingMap) {
		
		Map<String, Object> articlesMap = new HashMap<String, Object>();
		
		
		//전달된 pagingMap을 사용해 글 목록을 조회합니다.
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pagingMap);
		
		//테이블에 존재하는 전체 글 수를 조회합니다.
		int totArticles = boardDAO.selectTotArticles();
		
		//조회된 글 목록을 ArrayList에 저장한 후 다시 articlesMap에 저장합니다.
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("totArticles", totArticles);
		
		//articlesMap.put("totArticles", 170);
		return articlesMap; //articleMap을 반환합니다.
	}

	//전체 글 목록 조회 서비스
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}

	//새글 추가 서비스
	public int addArticle(ArticleVO articleVO) {
		return boardDAO.insertNewArticle(articleVO);
	}

	//글 세부 정보 조회 서비스
	public ArticleVO viewArticle(int articleNO) {
		//전달받은 ArticleVO를 저장할 변수를 선언합니다.
		ArticleVO articleVO = null;
		
		//전달받은 articleNO에 해당하는 글정보를 
		//BoardDAO의 select Article()메소드를
		//이용하여 가져와 article 변수에 저장합니다.
		articleVO = boardDAO.selectArticle(articleNO);
		return articleVO; //ArticleVO를 반환합니다.
	}

	//글 수정 서비스
	public void modArticle(ArticleVO articleVO) {
		//글 수정을 위해 DAO 객체의 updateArticle을 호출합니다.
		boardDAO.updateArticle(articleVO);
	}

	//글 삭제 서비스
	public List<Integer> removeArticle(int articleNO) {
		//삭제할 글번호들(글 및 답글)을 리스트에 저장
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		//글 및 관련 답글 삭제 
		boardDAO.deleteArticle(articleNO);
		//업로드 파일 삭제를 위해 사용됨
		//삭제된 글의 글번호 목록을 컨트롤러에 반환함
		return articleNOList;
	}

	//답글추가 서비스
	public int addReply(ArticleVO articleVO) {
		return boardDAO.insertNewArticle(articleVO);
	}

}
