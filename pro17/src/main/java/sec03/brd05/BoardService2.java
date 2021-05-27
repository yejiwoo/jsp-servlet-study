package sec03.brd05;

import java.util.List;


public class BoardService2 {
	BoardDAO2 boardDAO;

	public BoardService2() {
		boardDAO = new BoardDAO2();
	}

	//전체 글 목록 조회 서비스
	public List<ArticleVO2> listArticles() {
		List<ArticleVO2> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}

	//새글 추가 서비스
	public int addArticle(ArticleVO2 articleVO) {
		return boardDAO.insertNewArticle(articleVO);
	}

	//글 세부 정보 조회 서비스
	public ArticleVO2 viewArticle(int articleNO) {
		//전달받은 ArticleVO를 저장할 변수를 선언합니다.
		ArticleVO2 articleVO = null;
		//전달받은 articleNO에 해당하는 글정보를 
		//BoardDAO의 select Article()메소드를
		//이용하여 가져와 article 변수에 저장합니다.
		articleVO = boardDAO.selectArticle(articleNO);
		return articleVO; //ArticleVO를 반환합니다.
	}
	//글 수정
	public void modArticle(ArticleVO2 articleVO) {
		//글 수정을 위해 DAO 객체의 updateArticle을 호출합니다. 
		boardDAO.updateArticle(articleVO);
	}

}
