package sec03.brd07;

import java.util.List;

public class BoardService {
	
	//맴버 변수(필드)로 DAO 타입변수 선언
	BoardDAO boardDAO;

	public BoardService() {
		//BoardService 생성 시, BoardDAO 객체도 생성됨.
		boardDAO = new BoardDAO();
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
	public List<Integer> removeArticle(int  articleNO) {
		//컨트롤러로부터 삭제할 글번호를 전달받아
		//DAO의 selectRemovedArticles(articleNO)메소드를 호출하여
		//삭제할 글번호들을 리스트에 저장
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		
		//DAO의 deleteArticle() 메소드를 호출하여 해당 글 및 관련 답글 삭제 
		boardDAO.deleteArticle(articleNO);
		
		//업로드 파일 삭제를 위해 사용됨
		//삭제된 글의 글번호 목록을 컨트롤러에 반환함// 
		return articleNOList;
	}
	
	//답글 추가 서비스
	public int addReply(ArticleVO articleVO) {
		
		//DAO의 insertNewArticle(article) 메소드를 호출하여 답글 저장 
		return boardDAO.insertNewArticle(articleVO);
	}
}
