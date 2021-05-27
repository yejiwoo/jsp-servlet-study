package sec03.brd02;

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
				" SELECT LEVEL, ARTICLENO, PARENTNO, "
			  + "        TITLE, "
			  + "        CONTENT, ID, WRITEDATE " 
			  + " FROM hr.t_board "
			  + " START WITH parentNO = 0 " 
			  + " CONNECT BY PRIOR articleNO = parentNO "
			  + " ORDER SIBLINGS BY articleNO DESC";

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
				ArticleVO articleVO = new ArticleVO();
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
	
	
	//새글의 글번호(articleNO)를 얻어오는 메소드
	
	int lastArticleNO = 0 ;
	
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
	
/*			if (rs.next()) {
				return (rs.getInt(1) + 1);	//getInt(1)은 SELECT문의 레코드 중 첫번째 컬럼을 의미.
			rs.close();						//따라서, 가장 큰 번호에 1을 더한 번호를 반환합니다.
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
*/
	}

	//BoardController의 action 값이 /addArticle.do 인경우 
	//BoardService의 addArticle(ArticleVO article) 메소드에 의해 호출됨
	public void insertNewArticle(ArticleVO articleVO) {
		try {
			//데이터베이스에 새글을 입력(저장)하기 전에
			//기존 제일 마지막 글의 글번호를 가져와 articleNo에 저장합니다.
			int articleNO = getNewArticleNO();
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

			conn = dataFactory.getConnection();
			
			pstmt = conn.prepareStatement(strInsertNewArticle);
			
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			
			pstmt.executeUpdate(); //데이터베이스에 새글을 추가합니다.
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
