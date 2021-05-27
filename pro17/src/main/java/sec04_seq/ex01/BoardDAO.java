package sec04_seq.ex01;
//오라클 시퀀스를 사용한 게시판 VO
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public List<ArticleVO> selectAllArticles(Map<String, Integer> pagingMap){
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();

		//전송된 section과 pageNum 값을 가져옵니다.
		int section = (Integer)pagingMap.get("section");
		int pageNum=(Integer)pagingMap.get("pageNum");
		
		try{
		   conn = dataFactory.getConnection();
		   //페이징 기능 구현을 위한 SELECT문.
		   //예, 한 개의 섹션은 10개의 페이지로 이루어집니다.(교재ppt-17장 152p 참고)
		   //아래에서 ROWNUM은 오라클DB에만 있는 것으로, SELECT문의 처리 레코드를 구분하기 위해
		   //서버가 각 레코드마다 붙이는 값을 사용자가 호출할 떄 사용됩니다. 1부터 시작됩니다.
		   String query =
				   "SELECT * "
				 + "FROM (SELECT ROWNUM as recNum, LVL,"
					  + 		"articleNO, parentNO, title, id, writeDate "
					  +  "FROM (SELECT LEVEL as LVL, "
							  +		  "articleNO, parentNO, title, id, writeDate "
							  + "FROM hr.t_board " 
							  + "START WITH parentNO=0 "
							  + "CONNECT BY PRIOR articleNO = parentNO "
							  + "ORDER SIBLINGS BY articleNO DESC)"
					  + ") "                        
				 + "WHERE recNum BETWEEN ((?-1)*100 + (?-1)*10+1) AND ((?-1)*100 + ?*10)";
			     //section과 pageNum 값으로 레코드 번호의 범위를 조건으로 정합니다.
		   		 //이들 값이 각각 1로 전송되었으면, BETWEEN 1 AND 10이 됩니다)
		   //위의 SELECT문에 의하여 section번호와 해당 섹션의 pageNum 값에 따라 목록 페이지에
		   //표시되는 내용이 결정됩니다.
		   		
		   System.out.println(query);
		   
		   pstmt= conn.prepareStatement(query);
		   
		   pstmt.setInt(1, section);
		   pstmt.setInt(2, pageNum);
		   pstmt.setInt(3, section);
		   pstmt.setInt(4, pageNum);
		   
		   ResultSet rs =pstmt.executeQuery();
		   
		   while(rs.next()){
			   
		      int level = rs.getInt("lvl");	//계층쿼리의 레벨값
		      int articleNO = rs.getInt("articleNO");
		      int parentNO = rs.getInt("parentNO");
		      String title = rs.getString("title");
		      String id = rs.getString("id");
		      Timestamp writeDate= rs.getTimestamp("writeDate");
		      
		      ArticleVO articleVO = new ArticleVO();
		      
		      articleVO.setLevel(level);
		      articleVO.setArticleNO(articleNO);
		      articleVO.setParentNO(parentNO);
		      articleVO.setTitle(title);
		      articleVO.setId(id);
		      articleVO.setWriteDate(writeDate);
		      
		      articlesList.add(articleVO);	
		   } //end while
		   rs.close();
		   pstmt.close();
		   conn.close();
	  }catch(Exception e){
	     e.printStackTrace();	
	  }
	  return articlesList;  //글정보 목록이 반환됩니다.
    } 
	
	
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		try {
			
			conn = dataFactory.getConnection();
			
			String 
			query = 
				"SELECT LEVEL, ARTICLENO, PARENTNO, TITLE, "
			  + 	   "CONTENT, ID, WRITEDATE " 
			  + "FROM hr.t_board "
			  + "START WITH parentNO = 0 " 
			  + "CONNECT BY PRIOR articleNO = parentNO "
			  + "ORDER SIBLINGS BY articleNO DESC";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				//ResultSet의 각 값을 변수에 저장합니다.
				int level = rs.getInt("level");//각 글의 계층을 level 속성에 저장합니다.
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Timestamp writeDate = rs.getTimestamp("writeDate");
				
				//VO 객체를 생성합니다.
				ArticleVO articleVO = new ArticleVO();
				
				//SELECT로 가져와 변수에 저장한 값을 VO 객체에 저장합니다.
				articleVO.setLevel(level);
				articleVO.setArticleNO(articleNO);
				articleVO.setParentNO(parentNO);
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setId(id);
				articleVO.setWriteDate(writeDate);
				
				//VO 객체를 ArraryList 객체에 저장합니다.
				articlesList.add(articleVO);
			}//ResultSet의 모든 레코드가 처리될 때까지 반복됩니다.
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//모든 글 정보가 저장된 리스트 객체를 반환합니다.
		return articlesList;
	}

/*	//현재 저장된 마지막 글번호 반환 메소드
	private int getNewArticleNO() {
		try {
			
			conn = dataFactory.getConnection();
			
			String query = "SELECT max(articleNO) FROM hr.t_board ";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery(query);
			
			if (rs.next())
				return (rs.getInt(1) + 1);	//getInt(1)은 SELECT문의 레코드 중 첫번째 컬럼값이 저장된.
											//레코드의 첫번째 값을 의미(가장 큰 번호)
											//가장 큰 번호에 1을 더한 정수를 반환합니다.
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0; //if 절의 return이 실행되지 않은 경우에만,  
	}			  //0이 반환됨.
*/
	//특정 articleNO의 세부 글정보를 가져오는 메소드 입니다.
	public ArticleVO selectArticle(int articleNO) {
		
		//VO 객체를 생성합니다.
		ArticleVO articleVO = new ArticleVO();
		
		try {
			
			conn = dataFactory.getConnection();

			String query =
					"SELECT ARTICLENO, PARENTNO, TITLE, CONTENT, IMAGEFILENAME, ID, WRITEDATE " 
				  + "FROM hr.t_board " 
				  + "WHERE articleNO=?";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			//레코드로부터 각 필드값을 가져와 변수에 저장합니다.
			int _articleNO = rs.getInt("ARTICLENO");
			int parentNO = rs.getInt("PARENTNO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
		
			//파일이름을 저장합니다.
			String imageFileName = rs.getString("IMAGEFILENAME");
			
			//파일이름을 UTF-8로 인코딩합니다
			if (imageFileName != null) {
				imageFileName = URLEncoder.encode(imageFileName, "UTF-8");
			}
						
			String id = rs.getString("ID");
			Timestamp writeDate = rs.getTimestamp("WRITEDATE");

			//변수에 저장된 값을 setter를 이용해 articleVO에 저장합니다.
			articleVO.setArticleNO(_articleNO);
			articleVO.setParentNO(parentNO);
			articleVO.setTitle(title);
			articleVO.setContent(content);
			articleVO.setImageFileName(imageFileName);
			articleVO.setId(id);
			articleVO.setWriteDate(writeDate);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleVO; //세부글정보가 저장된 VO 객체를 반환
	}

	// 새글 등록(저장)
	public int insertNewArticle(ArticleVO articleVO) {
		
		int articleNO = 1;
		
		try {
			
			conn = dataFactory.getConnection();
			
			int parentNO = articleVO.getParentNO();
			
			String title = articleVO.getTitle();
			String content = articleVO.getContent();
			String id = articleVO.getId();
			String imageFileName = articleVO.getImageFileName();
			String query = 
					"INSERT INTO hr.t_board "
					+ "(articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES (hr.boardseq.nextval, ? ,?, ?, ?, ?)";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			/* pstmt.setInt(1, articleNO); */
			pstmt.setInt(1, parentNO);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setString(4, imageFileName);
			pstmt.setString(5, id);
			
			pstmt.executeUpdate();
			
			query = "SELECT hr.boardseq.currval FROM dual" ;
		
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			if ( rs.isBeforeFirst()) {
				rs.next();
				articleNO = rs.getInt(1);
				System.out.println("LastSequence Used In this Session: "+ articleNO);
			} else {
				articleNO = 1 ;
				System.out.println("NoValueCurrval: "+ articleNO);
			}

			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//새글을 추가(데이터베이스에 저장)한 후, 새 글의 글번호를 반환합니다.
		System.out.println("컨트롤러의 addArticle 메소드로 반환하는 입력글번호: "+ articleNO);
		return articleNO;
	}
	
	//데이터베이스에 수정글 저장 메소드
	public void updateArticle(ArticleVO articleVO) {
		
		//전달받은 VO 객체로부터 글 정보를 가져와서 변수에 저장
		int articleNO = articleVO.getArticleNO();
		String title = articleVO.getTitle();
		String content = articleVO.getContent();
		String imageFileName = articleVO.getImageFileName();
		
		try {
			conn = dataFactory.getConnection();
			
			String query = "UPDATE hr.t_board SET title=?,content=?";
			//다음의 if {}는 이미지파일에 대한 수정 유무를 지정합니다.
			//즉, 수정된 이미지 파일이있을 때만 if{}가 처리되어
			//imageFileName을 SQL문에 추가합니다.
			if (imageFileName != null && imageFileName.length() != 0) {
				//이미지파일이 있으면, UPDATE문에 이미지파일이름 수정도 추가.
				query += ",imageFileName=?";
			}
			//UPDATE문에 조건절 추가
			query += " WHERE articleNO=?";

			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			
			//이미지파일을 수정하는 경우와 그렇지 않은 경우를 구분해서 설정합니다.
			if (imageFileName != null && imageFileName.length() != 0) {
				
				//이미지 파일이 있으면, 다음의 2개 ? 값을 지정
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			} 
			  else {
				//이미지파일이 없으면, 다음 한 개의 ? 값을 지정
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//데이터베이스에서 글 삭제 메소드
	public void deleteArticle(int articleNO) {
		
		try {
			
			conn = dataFactory.getConnection();
			
			String query = 
					  "DELETE FROM hr.t_board ";
			query +=  "WHERE articleNO IN ( ";
			query += 					 	"SELECT articleNO FROM hr.t_board ";
			query += 						"START WITH articleNO = ?";
			query += 						"CONNECT BY PRIOR articleNO = parentNO )";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//삭제된 글번호 목록을 반환
	public List<Integer> selectRemovedArticles(int articleNO) {

		List<Integer> articleNOList = new ArrayList<Integer>();

		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT articleNO FROM hr.t_board  "
						 + "START WITH articleNO = ?"
						 + "CONNECT BY PRIOR articleNO = parentNO";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				articleNO = rs.getInt("articleNO");
				
				articleNOList.add(articleNO);
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNOList;
	}

	//데이터베이스에 저장된 게시판의 모든 글 개수 확인 
	public int selectTotArticles() {
		
		int totArticles = 0;
		
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT COUNT(articleNO) FROM hr.t_board ";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				totArticles = rs.getInt(1);
			} else {
				totArticles = 0;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totArticles;
	}

}
