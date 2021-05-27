package sec03.brd02;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	
	//BoardService() 생성자 호출 시(즉,BoardService() 객체가 생성될 때),
	//BoardDAO() 객체도 생성됩니다.
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	//BoardController 컨트롤러에 의해서 호출됩니다.
	public List<ArticleVO> listAllArticles() {
		//BoardDAO 클래스의 selectAllArticles() 메소드를 호출하여
		//<ArticleVO>를 요소로 갖는 리스트 객체를 반환받습니다.
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	//BoardController 컨트롤러에 의해서 호출됩니다.
	public void addArticle(ArticleVO articleVO){
		//새글을 데이터베이스에 저장합니다.
		boardDAO.insertNewArticle(articleVO);		
	}

}
