package sec03.brd03;
//오라클 시퀀스를 사용한 게시판 Service
import java.util.List;

public class BoardService2 {
	BoardDAO2 boardDAO;

	public BoardService2() {
		//BoardService() 생성자 호출 시(즉,BoardService() 객체가 생성될 때),
		//BoardDAO() 객체도 생성됩니다.
		boardDAO = new BoardDAO2();
	}

	//BoardController 컨트롤러에 의해서 호출됩니다.
	public List<ArticleVO2> listAllArticles() {
		//BoardDAO 클래스의 selectAllArticles() 메소드를 호출하여
		//<ArticleVO>를 요소로 갖는 리스트 객체를 반환받습니다.
		List<ArticleVO2> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	//BoardController 컨트롤러에 의해서 호출됩니다.
	public void addArticle(ArticleVO2 articleVO){
		//새글을 데이터베이스에 저장합니다.
		boardDAO.insertNewArticle(articleVO);		
	}
	
	public int getArticleNO(){
		//새글을 데이터베이스에 저장합니다.
		return boardDAO.getNewArticleNO();		
	}

}







