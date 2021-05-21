package sec03.brd03;
//오라클 시퀀스를 사용한 게시판 DAO
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO2 {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;

	public BoardDAO2() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oraclehr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ArticleVO2> selectAllArticles() {
		
		List<ArticleVO2> articlesList = new ArrayList<ArticleVO2>();
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

				int level = rs.getInt("LEVEL");//각 글의 계층을 level 속성에 저장합니다.
				int articleNO = rs.getInt("ARTICLENO");
				int parentNO = rs.getInt("PARENTNO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String id = rs.getString("ID");
				Timestamp writeDate = rs.getTimestamp("WRITEDATE");
				
				//VO 객체를 생성합니다.
				ArticleVO2 articleVO = new ArticleVO2();
				//SELECT로 가져온 값을 VO 객체에 저장합니다.
				articleVO.setLevel(level);
				articleVO.setArticleNO(articleNO);
				articleVO.setParentNO(parentNO);
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setId(id);
				articleVO.setWriteDate(writeDate);
				
				//VO 객체를 ArraryList 객체에 저장합니다.
				articlesList.add(articleVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	
	//시퀀스를 이용하여 새글의 글번호(articleNO)를 얻어오는 메소드
	int getNewArticleNO() {
	
		int newArticleNo =0 ;
		
		try {
			
			conn = dataFactory.getConnection();
			
			String strGetNewArticleNo =
					"SELECT hr.boardArticleNOSeq.NEXTVAL FROM dual ";

			System.out.println(strGetNewArticleNo);

			pstmt = conn.prepareStatement(strGetNewArticleNo);
			ResultSet rs = pstmt.executeQuery(strGetNewArticleNo);
			
			rs.next();
			newArticleNo = rs.getInt(1);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(newArticleNo);
		return newArticleNo ;
	}

	//BoardController의 action 값이 /addArticle.do 인경우 
	//BoardService의 addArticle(ArticleVO article) 메소드에 의해 호출됨
	// 새글 등록(저장)
	public void insertNewArticle(ArticleVO2 articleVO) {
		
		try {
			
			conn = dataFactory.getConnection();
			
			int articleNO = articleVO.getArticleNO();
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
			
			pstmt.setInt(1, articleNO);
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
	}

}
