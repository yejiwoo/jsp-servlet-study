package sec01.ex01;

import java.sql.Date;

public class MemberBean {

	private String id;
	private String password;
	private String name;
	private String email;
	private int score=10; // getter 클래스가 없으면 jsp가 표현언어로 값을 못 읽어 온다..
	private Date joinDate;
	
	public MemberBean() {
		
	}

	public MemberBean(String id, String password, String name, String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
