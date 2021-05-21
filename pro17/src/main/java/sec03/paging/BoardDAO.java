package sec03.paging;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public List<ArticleVO> selectAllArticles(Paging paging){
        //1번 페이지 1~10
        //2번 페이지 11~20        
        //int startNum = (page-1)*3+1;
        //int endNum = page*3;
		
		int startNum = paging.getStartNum();
        int endNum = paging.getEndNum();
		
        String sql = 
        		  " SELECT * "
        		+ " FROM (SELECT * "
        		+ "		  FROM (SELECT ROWNUM row_num, t.* "
        		+ "			    FROM t_board t) a "
        		+ " 	  WHERE a.row_num >= ?) b "
        		+ " WHERE b.row_num <= ?";
        
        List<ArticleVO> list = new ArrayList<ArticleVO>();
        try{
        	conn = dataFactory.getConnection();
        	pstmt= conn.prepareStatement(sql);
            pstmt.setInt(1, startNum);
            pstmt.setInt(2, endNum);
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                ArticleVO articleVO = new ArticleVO();
 		      
  		        articleVO.setLevel(rs.getInt("lvl"));
  		        articleVO.setArticleNO(rs.getInt("articleNO"));
  		        articleVO.setParentNO(rs.getInt("parentNO"));
  		        articleVO.setTitle(rs.getString("title"));
  		        articleVO.setId(rs.getString("id"));
  		        articleVO.setWriteDate(rs.getTimestamp("WRITEDATE"));
                
                list.add(articleVO);
            }
            rs.close();
 		    pstmt.close();
 		    conn.close();
 		    
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

	//데이터베이스에 저장된 게시판의 모든 글 개수 확인 
	public int selectTotArticles() {
		try {
			
			conn = dataFactory.getConnection();
			
			String query = "SELECT COUNT(*) FROM hr.t_board ";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
				return (rs.getInt(1));
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
}
