package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


// Connection Pool을 이용하여 데이터베이스 접속합니다.
// pro12의 pro12의 memberDAO.listMembers(MemberVO memberVO) 메서드를 포함(이름검색기능)

public class MemberDAO {

   //데이터베이스 접속 객체 저장 필드
   private Connection con;
   
   private PreparedStatement pstmt;  //PreparedStatement 타입 변수 선언(SQL문 객체)
   
   private DataSource dataFactory;   //DataSource 타입 변수 선언(Connection Pool 객체)
   
   //접속 가능한 Connection Pool 이름 확인(톰캣의 Context.xml)
   public MemberDAO() {
      try {
         Context ctx = new InitialContext();
         Context envContext = (Context) ctx.lookup("java:/comp/env");
         dataFactory = (DataSource) envContext.lookup("jdbc/hr");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   //전체 멤버 목록 표시
   public List<MemberBean> listMembers() {
      
      List<MemberBean> memberList = new ArrayList<MemberBean>();
      
      try {
         
         //접속객체를 할당받음.
         con=dataFactory.getConnection(); 
         
         //String query = "select * from hr.t_member where password = ?";
         String query = "SELECT id, password, name, email, joinDate "
                    + "FROM hr.t_members ";
         
         System.out.println("preparedStatement: " + query);
         
         pstmt = con.prepareStatement(query); //SQL문을 담은 문장 객체 생성
         
         //pstmt.setString(1, "1212"); 
         
         //SQL문 실행 결과가 담긴 ResultSet 객체 생성
         //즉, SELECT문 처리 결과를 저장할 ResultSet 객체를 선언하여
         //문장객체의 executeQuery(SQL문)메서드의 처리결과로 초기화합니다.
         ResultSet rs = pstmt.executeQuery(); 
         
         //ResultSet 객체의 행들을 하나씩 꺼내어
         //행당 하나의 memberVO 객체를 이용하여 저장 후
         //memberVO 객체를 ArrayList 객체에 저장 
         while (rs.next()) {
            String id = rs.getString("id");   //조회한 레코드의 각 컬럼 값을 받아옵니다.
            String password = rs.getString("password");
            String name = rs.getString("name");
            String email = rs.getString("email");
            Date joinDate = rs.getDate("joinDate");
            
            MemberBean memberBean = new MemberBean();   //각 컬럼값을 다시 MemberVO 객체의
                                    //속성에 설정합니다.
            memberBean.setId(id);                   
            memberBean.setPassword(password);
            memberBean.setName(name);
            memberBean.setEmail(email);
            memberBean.setJoinDate(joinDate);
            
            //값이 저장된 MemberVO 객체를 다시 ArrayList에 저장합니다.
            memberList.add(memberBean);
            
            //memberList 객체에 저장된 VO를 정렬시킵니다.
            //Collections.sort(memberList);  //정렬
         }
         rs.close();      //ResultSet 리소스 닫음.
         pstmt.close();   //PreparedStatement 리소스 닫음.
         con.close();   //접속 객체 닫음.

      } catch (Exception e) {
         e.printStackTrace();
      }
      return memberList;   //조회한 레코드의 개수만큼 MemberVO 객체를 저장한 
   }                   //ArrayList를 반환합니다.
   
   //회원 목록  표시: name 검색
   public List<MemberBean> listMembers(MemberBean memberBean) {

      List<MemberBean> memberList = new ArrayList<MemberBean>();

      String _name = memberBean.getName();
      
      try {
         con=dataFactory.getConnection(); //Connection Pool 접속 객체 생성
         
         String searchedMemberSql =
               "SELECT * FROM hr.t_members ";
         //_name 값이 존재하면, 
         //SELECT문에 이름으로 검색하는 WHERE 절을 추가하여 실행
         if(_name != null && _name.length() != 0) {
            searchedMemberSql += " WHERE UPPER(name) = ?"; //문자열의 맨 앞에 빈칸이 포함됩니다. 
            
            pstmt = con.prepareStatement(searchedMemberSql);
            
            pstmt.setString(1, _name);
            
         }//_name 값이 없으면, WHERE 절 없는 SELECT문을 실행
         else {
         
            pstmt = con.prepareStatement(searchedMemberSql);
         }
         
         System.out.println("preparedStatement: " + searchedMemberSql);
         
         ResultSet rs = pstmt.executeQuery(); //SQL문 실행 결과가 담긴 ResultSet 객체 생성
         
         //ResultSet 객체에 담긴 결과를 ArrayList 객체에 옮기기 
         while (rs.next()) {
            String id = rs.getString("id");   //조회한 레코드의 각 컬럼 값을 받아옵니다.
            String password = rs.getString("password");
            String name = rs.getString("name");
            String email = rs.getString("email");
            Date joinDate = rs.getDate("joinDate");
            
            MemberBean memBean = new MemberBean();   //각 컬럼값을 다시 MemberVO 객체의
                                    //속성에 설정합니다.
            memBean.setId(id);                   
            memBean.setPassword(password);
            memBean.setName(name);
            memBean.setEmail(email);
            memBean.setJoinDate(joinDate);
            
            memberList.add(memBean);          //설정된 MemberVO 객체를 다시 ArrayList에 저장합니다.
            //Collections.sort(memberList);  //정렬
         }
         rs.close();      //ResultSet 리소스 닫음.
         pstmt.close();   //PreparedStatement 리소스 닫음.
         con.close();   //접속 객체 닫음.
      } catch (Exception e) {
         e.printStackTrace();
      }
      return memberList;   //조회한 레코드의 개수만큼 MemberVO 객체를 저장한 
   }               //ArrayList를 반환합니다.

   
   
   public void addMember(MemberBean memberBean) {
      
      try {
         //접속객체를 할당받음.
         con = dataFactory.getConnection();
         
   
         String insertMemberStr = "INSERT INTO hr.t_members values(?,?,?,?,DEFAULT)";
         
         System.out.println("prepareStatememt: " + insertMemberStr);
         
         pstmt = con.prepareStatement(insertMemberStr);
         
         //String id = memberVO.getId();
         //String pwd = memberVO.getPassword();
         //String name = memberVO.getName();
         //String email = memberVO.getEmail();
         
         //pstmt.setString(1, id);
         //pstmt.setString(2, pwd);
         //pstmt.setString(3, name);
         //pstmt.setString(4, email);
         
         pstmt.setString(1, memberBean.getId());
         pstmt.setString(2, memberBean.getPassword());
         pstmt.setString(3, memberBean.getName());
         pstmt.setString(4, memberBean.getEmail());
         
         pstmt.executeUpdate();   //행 입력 수행
         
         System.out.println("새회원 정보가 DB에 저장되었습니다.");
         
         pstmt.close();          //행 입력 후, 문장 객체 리소스 닫음. 
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void delMember(String id) {
      
      try {
         //접속객체를 할당받음.
         con = dataFactory.getConnection();
         
         String deleteMemberStr = "DELETE FROM hr.t_members WHERE id=?";
         System.out.println("prepareStatememt:" + deleteMemberStr);
         
         pstmt = con.prepareStatement(deleteMemberStr);
         
         pstmt.setString(1, id);
         
         pstmt.executeUpdate();   //행 삭제 수행
         
         System.out.println("회원 정보가 DB에서 삭제되었습니다.");
         
         pstmt.close();         //행 삭제 후, 문장 객체 리소스 닫음.
         con.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public boolean isMemberExist(MemberBean memberBean) {
      
      boolean result = false;
      
      try {
         con = dataFactory.getConnection();
         //String query =
         //             " SELECT DECODE(COUNT(*),1,'true','false') AS RESULT "
         //           + " FROM hr.t_member";
         
         String selectTrueFalse = 
                 "SELECT (CASE WHEN COUNT(*) = 1 THEN 'true' "
                        + "ELSE 'false' END) AS RESULT "
               + " FROM hr.t_members "
               + " WHERE id=? AND password=?";
         
         System.out.println(selectTrueFalse);
         
         pstmt = con.prepareStatement(selectTrueFalse);
         
         //String id = memberVO.getId();
         //String password = memberVO.getPassword();
         
         //pstmt.setString(1, id);
         //pstmt.setString(2, password);
         
         //SELECT문의 ?를 값으로 바인딩 시킵니다.
         pstmt.setString(1, memberBean.getId());
         pstmt.setString(2, memberBean.getPassword());
         
         
         //pstmt.executeQuery()는 ResultSet 객체를 반환하므로
         //String 객체에 값을 담을 수 없습니다.
         //String strResult = pstmt.executeQuery(); //오류
         
         //ResultSet 객체에는 "true" 또는 "false" 문자열이 저장되어 있습니다.
         ResultSet rs = pstmt.executeQuery();
         
         //rs.first(); //커서를 첫번째 레코드로 위치시킵니다.
         rs.next(); //커서를 첫번째 레코드로 위치시킵니다.
         
         result = Boolean.parseBoolean(rs.getString("result"));
         
         //System.out.println("result=" + result);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return result;  //boolean 유형의 true/false 값이 전달됩니다.
   }
}