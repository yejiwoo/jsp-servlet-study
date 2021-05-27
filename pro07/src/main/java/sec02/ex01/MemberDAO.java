package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//Connection Pool 및 PerparedStatement 유형의 SQL 문 처리 유형
public class MemberDAO {

   //데이터베이스 접속 객체 저장 필드
   private Connection con;
   
   private PreparedStatement pstmt;
   
   private DataSource dataFactory ;
   
   public MemberDAO() {
      try {
         Context ctx = new InitialContext();
         Context envContext = (Context) ctx.lookup("java:/comp/env");
         dataFactory = (DataSource) envContext.lookup("jdbc/hr");
      } catch(Exception e) {
         e.printStackTrace();
      }
      
   }
   public boolean isExisted(MemberVO memberVO) {
	   boolean result=false;
	   String id=memberVO.getId();
	   String pw=memberVO.getPassword();
	   System.out.println(id+pw);
	   try {
		   con=dataFactory.getConnection();
		   String query="select decode(count(*), 1, 'true', 'false') as result from t_members where id=? and password=?";
//		   System.out.println(query);
		   
		   pstmt=con.prepareStatement(query);
		   pstmt.setString(1, id);
		   pstmt.setString(2, pw);
		   ResultSet rs=pstmt.executeQuery();
		   
		   rs.next();
		   result=Boolean.parseBoolean(rs.getString("result"));
		   System.out.println("result="+result);
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   return result;
   }
   public List<MemberVO> listMembers() {
      
      List<MemberVO> list = new ArrayList<MemberVO>();
      
      try {
         //접속객체를 할당받음.
         con = dataFactory.getConnection();
         
         //String query = "select * from hr.t_member where password = ?";
         String query = "SELECT id, password, substr(name,1,1)||'**' AS name, email, joinDate " //여기서 맨 끝에 빈칸 안 주면 sql문 오류난다.
                   + "FROM hr.t_members";
         System.out.println(query);
         
         
         pstmt=con.prepareStatement(query);
         
         //pstmt.setString(1, "1212"); 

      
         //SELECT문 처리 결과를 저장할 ResultSet 객체를 선언하여
         //문장객체의 executeQuery(SQL문)메서드의 처리결과로 초기화합니다.
         ResultSet rs = pstmt.executeQuery(); //SQL문으로 회원 정보를 조회합니다.
          

         //ResultSet 객체의 행들을 하나씩 꺼내어
         //행당 하나의 memberVO 객체를 이용하여 저장
         while (rs.next()) {
            String id = rs.getString("id");   //조회한 레코드의 각 컬럼 값을 받아옵니다.
            String password = rs.getString("password");
            String name = rs.getString("name");
            String email = rs.getString("email");
            Date joinDate = rs.getDate("joinDate");
            
            MemberVO vo = new MemberVO();   //각 컬럼값을 다시 MemberVO() 객체의
            vo.setId(id);               // 속성에 설정합니다.
            vo.setPassword(password);
            vo.setName(name);
            vo.setEmail(email);
            vo.setJoinDate(joinDate);
            
            //값이 저장된 MemberVO 객체를 ArrayList 객체인 list에 저장합니다.
            list.add(vo);      
         }
         rs.close();
         pstmt.close();
         con.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return list;   //조회한 레코드의 개수만큼 MemberVO 객체를
   }               //저장한 ArrayList를 반환합니다.
      
   public List<MemberVO> listMembersJoinDateInterval(String startDay, String endDay) {
      
      List<MemberVO> list = new ArrayList<MemberVO>();
      
      try {
         //접속객체를 할당받음.
         con = dataFactory.getConnection();
         
         
         
         //String query = "select * from hr.t_member where password = ?";
         //String query = "SELECT id, password, substr(name,1,1)||'**' AS name, email, joinDate "
         //          + "FROM hr.t_member "
         //          + "WHERE joinDate BETWEEN TO_DATE('"+startDay+"','YYYY-MM-DD') AND TO_DATE('"+endDay+"','YYYY-MM-DD')";
         //System.out.println(query);
         
         
         String query = "SELECT id, password, substr(name,1,1)||'**' AS name, email, joinDate "
                + "FROM hr.t_members "
                + "WHERE joinDate BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')";
      System.out.println(query);
         
         
         pstmt=con.prepareStatement(query);
                  
         pstmt.setString(1, startDay); 
         pstmt.setString(2, endDay);

      
         //SELECT문 처리 결과를 저장할 ResultSet 객체를 선언하여
         //문장객체의 executeQuery(SQL문)메서드의 처리결과로 초기화합니다.
         ResultSet rs = pstmt.executeQuery(); //SQL문으로 회원 정보를 조회합니다.
          

         //ResultSet 객체의 행들을 하나씩 꺼내어
         //행당 하나의 memberVO 객체를 이용하여 저장
         while (rs.next()) {
            String id = rs.getString("id");   //조회한 레코드의 각 컬럼 값을 받아옵니다.
            String password = rs.getString("password");
            String name = rs.getString("name");
            String email = rs.getString("email");
            Date joinDate = rs.getDate("joinDate");
            
            MemberVO vo = new MemberVO();   //각 컬럼값을 다시 MemberVO() 객체의
            vo.setId(id);               // 속성에 설정합니다.
            vo.setPassword(password);
            vo.setName(name);
            vo.setEmail(email);
            vo.setJoinDate(joinDate);
            
            //값이 저장된 MemberVO 객체를 ArrayList 객체인 list에 저장합니다.
            list.add(vo);      
         }
         rs.close();
         pstmt.close();
         con.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return list;   //조회한 레코드의 개수만큼 MemberVO 객체를
   }               //저장한 ArrayList를 반환합니다.
   
   //신규회원 등록
   public void addMember(MemberVO memberVO) {
      
      try {
         //접속객체를 할당받음.
         con = dataFactory.getConnection();
         
         String id = memberVO.getId();
         String password = memberVO.getPassword();
         String name = memberVO.getName();
         String email = memberVO.getEmail();
         
         
         String insertStr = "INSERT INTO hr.t_members " +
                          "VALUES (?,?,?,?,DEFAULT)";
         
         pstmt=con.prepareStatement(insertStr);
         
         pstmt.setString(1, id); 
         pstmt.setString(2, password);
         pstmt.setString(3, name);
         pstmt.setString(4, email);

         pstmt.executeUpdate();
         
         System.out.println("새회원 정보가 DB에 저장되었습니다.");

         pstmt.close();
         con.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   
   //신규회원 등록
   public void delMember(String id) {
      
      try {
         //접속객체를 할당받음.
         con = dataFactory.getConnection();
         
      
         String deleteStr = "DELETE FROM hr.t_members " +
                          "WHERE id = ?";
         
         pstmt=con.prepareStatement(deleteStr);
         
         pstmt.setString(1, id); 

         pstmt.executeUpdate();
         
         System.out.println("회원 정보가 DB에서 삭제되었습니다.");

         pstmt.close();
         con.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}