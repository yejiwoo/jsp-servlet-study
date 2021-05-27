package sec03.brd04;
//글 상세보기 창 구현

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;

	public BoardService() {
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

}
