package sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//Statement 유형의 SQL문 처리
public class MemberDAO {

	/**
	 * 데이터베이스 접속 시 필요한 정보
	 */
	//드라이버 클래스 정보
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	//사용할 jdbc 드라이버 정보 및 접속 정보
	private static final String url="jdbc:oracle:thin:@localhost:1521:xe";
	//데이터베이스 접속 계정명
	private static final String user="hr";
	//데이터베이스 접속 계정 암호
	private static final String pwd="oracle4U";
	
	//데이터베이스 접속 객체 저장 필드
	private Connection con;
	
	//SQL문장이 저장되는 객체 2가지. 2가지 유형 중 하나를 선택하여 사용해야 함.
	//1.Statement 유형: 정적 SQL문장
	//2.PreparedStatment 유형: 동적 SQL 문장(바인딩 처리 문장, OLTP 환경에서 일반적으로 많이 사용)
	private Statement stmt;
	
	public List<MemberVO> listMembers() {
		List<MemberVO> list =new ArrayList<MemberVO>();
		try {
			//데이터베이스 연결
			//드라이브 로드, con객체 생성, stmt 객체 생성
			connDB();
			
			String query="select * from hr.t_members where pwd='1212';";
			System.out.println(query);
			
			//select문 처리 결과를 저장할 ResultSet객체
			//문장 객체의 executeQuery(SQL문)메소드의 처리결과로 초기화
			ResultSet rs=stmt.executeQuery(query);
			
			//ResultSet객체의 행동을 하나씩 꺼내어
			//행당 하나의 memberVO객체에 저장
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				Date joinDate=rs.getDate("email");
				
				MemberVO vo=new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				
				list.add(vo);
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list; //조회한 레코드의 개수만큼 memberVO객체를 저장한 ArrayList 반환
	}
	
	//데이터베이스 접속 메소드
	private void connDB() {
		try {
			//ojdbc 드라이버 로드
			Class.forName(driver);
			System.out.println("==============Oracle 드라이버 로딩 성공===============");
			
			//드라이버를 통해 오라클 데이터베이스와 접속 객체 생성
			con=DriverManager.getConnection(url,user,pwd);
			System.out.println("==============Connection 생성 성공");
			
			//접속 객체를 통해서 요청할 Statement유형의 SQL문장 객체 생성
			stmt=con.createStatement();
			System.out.println("=================Statement 객체 생성 성공");
		}catch(Exception e) {e.printStackTrace();}
		
	}
}
