package sec03.brd03;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;

	public BoardService() {
		//BoardService() 생성자 호출 시(즉,BoardService() 객체가 생성될 때),
		//BoardDAO() 객체도 생성됩니다.
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
	//새글을 DAO 객체의 메소드를 호출하여 입력하고, 새글번호(articleNO)를 반환
	public int addArticle(ArticleVO articleVO){
		//새글을 데이터베이스에 저장합니다.
		return boardDAO.insertNewArticle(articleVO);		
	}

}
