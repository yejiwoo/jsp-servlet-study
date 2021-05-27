package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oraclehr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> listMembers() {
		
		List<MemberVO> membersList = new ArrayList<MemberVO>();
		
		try {
			conn = dataFactory.getConnection();
			
			/* String query = "SELECT * FROM hr.t_member ORDER BY mjoinDate DESC"; */
			
			String query = "SELECT * FROM hr.t_member ";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
				
				membersList.add(memberVO);
			}
			Collections.sort(membersList);
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}

	public void addMember(MemberVO memberVO) {
		try {
			conn = dataFactory.getConnection();
			
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			String query = 
					"INSERT INTO hr.t_member(id, pwd, name, email) " 
			     +  "VALUES(?, ? ,? ,?)";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public MemberVO findMember(String fid) {
		MemberVO memberVO = null;
		try {
			conn = dataFactory.getConnection();
			
			String query = "SELECT * FROM hr.t_member WHERE id=?";
						
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fid);
			System.out.println(query);
			
			//한명의 회원 정보가 레코드에 저장됩니다.
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate");
			
			//레코드의 정보를 memInfo 이름의  MemberVO 객체에 저장합니다.
			memberVO = new MemberVO(id, pwd, name, email, joinDate);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}

	public void modMember(MemberVO memberVO) {
		
		//수정하려는 맴버 정보가 저장된 memberVO 객체로부터 
		//수정 후 값을 변수에 저장합니다.
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		//String name = memberVO.getName();
		String email = memberVO.getEmail();
		//Date joinDate= memberVO.getJoinDate();
		
		try {
			conn = dataFactory.getConnection();
			//UPDATE 문을 query 변수에 저장합니다. 
			String stmUpdateMember = 
					" UPDATE hr.t_member "
				  + " SET pwd=?, email=? "
				  + " WHERE id=?";
			pstmt = conn.prepareStatement(stmUpdateMember);
			pstmt.setString(1, pwd);
			pstmt.setString(2, email);
			pstmt.setString(3, id);
			
			System.out.println(stmUpdateMember);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delMember(String id) {
		try {
			conn = dataFactory.getConnection();

			String stmDeleteMember = "DELETE FROM hr.t_member WHERE id=?";

			pstmt = conn.prepareStatement(stmDeleteMember);
			pstmt.setString(1,id);

			System.out.println(stmDeleteMember);

			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
