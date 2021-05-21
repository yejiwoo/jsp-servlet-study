package sec03.brd04;
//글 상세보기 창 구현

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oraclehr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ArticleVO> selectAllArticles() {
		
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		
		try {
			conn = dataFactory.getConnection();
			
			String strSelectAllarticles =
				"SELECT LEVEL, ARTICLENO, PARENTNO, TITLE, CONTENT, ID, WRITEDATE " 
			  + "FROM hr.t_board "
			  + "START WITH parentNO = 0 " 
			  + "CONNECT BY PRIOR articleNO = parentNO "
			  + "ORDER SIBLINGS BY articleNO DESC";

			System.out.println(strSelectAllarticles);
			
			pstmt = conn.prepareStatement(strSelectAllarticles);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {

				int level = rs.getInt("LEVEL");
				int articleNO = rs.getInt("ARTICLENO");
				int parentNO = rs.getInt("PARENTNO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String id = rs.getString("ID");
				Timestamp writeDate = rs.getTimestamp("WRITEDATE");
				
				//VO 객체를 생성합니다.
				ArticleVO article = new ArticleVO();
				//SELECT로 가져온 값을 VO 객체에 저장합니다.
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				
				//VO 객체를 ArraryList 객체에 저장합니다.
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	//새글번호를 저장할 필드 선언
	int lastArticleNO = 0 ;
	
	//새글 번호 반환 메소드
	private int getNewArticleNO() {
		
		try {
			
			conn = dataFactory.getConnection();
			
			String strSelectLastArticleNo = "SELECT MAX(ARTICLENO) FROM hr.t_board ";

			System.out.println(strSelectLastArticleNo);

			pstmt = conn.prepareStatement(strSelectLastArticleNo);
			ResultSet rs = pstmt.executeQuery(strSelectLastArticleNo);
		
			
			if (rs.next()) {
				lastArticleNO = rs.getInt(1) + 1;
			} else {
				lastArticleNO = 1 ;
			}
			rs.close();
			pstmt.close();
			conn.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(lastArticleNO);
		return lastArticleNO ;
	}

	// 새글 등록(저장)
	public int insertNewArticle(ArticleVO articleVO) {

		//데이터베이스에 새글을 입력(저장)하기 전에
		//기존 제일 마지막 글의 글번호를 가져와 articleNo에 저장합니다.
		//int articleNO = getNewArticleNO();
		lastArticleNO = getNewArticleNO();
		
		try {
			
			conn = dataFactory.getConnection();
			
			int parentNO = articleVO.getParentNO();
			String title = articleVO.getTitle();
			String content = articleVO.getContent();
			String id = articleVO.getId();
			String imageFileName = articleVO.getImageFileName();

			String strInsertNewArticle = 
					"INSERT INTO hr.t_board "
					+ "(articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES (?, ? ,?, ?, ?, ?)";
			
			System.out.println(strInsertNewArticle);
			
			pstmt = conn.prepareStatement(strInsertNewArticle);
			
			//pstmt.setInt(1, articleNO);
			pstmt.setInt(1, lastArticleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//새글을 추가(데이터베이스에 저장)한 후, 새 글의 글번호를 반환합니다.
		return lastArticleNO;
	}
	
	//글 세부 정보 조회
	public ArticleVO selectArticle(int articleNO){
		
		//VO 객체를 생성합니다.
		ArticleVO articleVO = new ArticleVO();

		try{
			conn = dataFactory.getConnection();
			
			String strSelectArticle =
					"SELECT ARTICLENO, PARENTNO, TITLE, CONTENT, IMAGEFILENAME, ID, WRITEDATE " 
				  + "FROM hr.t_board " 
				  + "WHERE articleNO=?";
			System.out.println(strSelectArticle);
			
			pstmt = conn.prepareStatement(strSelectArticle);
			pstmt.setInt(1, articleNO);
			
			ResultSet rs =pstmt.executeQuery();
			
			rs.next();
			
			int _articleNO =rs.getInt("ARTICLENO");
			int parentNO=rs.getInt("PARENTNO");
			String title = rs.getString("TITLE");
			String content =rs.getString("CONTENT");
		    String imageFileName = rs.getString("IMAGEFILENAME"); 
			String id = rs.getString("ID");
			Timestamp writeDate = rs.getTimestamp("WRITEDATE");
	
			articleVO.setArticleNO(_articleNO);
			articleVO.setParentNO (parentNO);
			articleVO.setTitle(title);
			articleVO.setContent(content);
			articleVO.setImageFileName(imageFileName);
			articleVO.setId(id);
			articleVO.setWriteDate(writeDate);
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();	
		}
	return articleVO; //세부글정보가 저장된 VO 객체를 반환
	}
}
