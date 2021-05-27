package sec03.paging;



public class BoardService {
	BoardDAO boardDAO;

	public BoardService() {
		//BoardService 생성 시, BoardDAO 객체도 생성됨.
		boardDAO = new BoardDAO();
	}
/*
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
*/
	/*
	//전체 글 목록 조회 서비스
	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	*/


}
