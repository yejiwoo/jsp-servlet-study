package sec02.ex02;

import java.sql.Date;

public class MemberVO implements Comparable<MemberVO>{
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
		
	public MemberVO() {
		System.out.println("MemberVO 생성자 호출");
	}
	
	public MemberVO(String id, String pwd, String name, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}
	
	public MemberVO(String id, String pwd, String name, String email, Date joinDate) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.joinDate = joinDate;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public int compareTo(MemberVO vo) {
		//가입입자로 오름차순 정렬
		//return this.joinDate.compareTo(vo.joinDate);
		//가입입자로 내림차순 정렬
		//return vo.joinDate.compareTo(this.joinDate);
		//아이디로 오름차순 정렬(대소문자 구분없이)
		//return this.id.toLowerCase().compareTo(vo.id.toLowerCase());
		//아이디로 내림차순 정렬(대소문자 구분없이)
		//return vo.id.toLowerCase().compareTo(this.id.toLowerCase());
		//이름으로 오름차순 정렬(대소문자 구분없이)
		//return this.name.toLowerCase().compareTo(vo.name.toLowerCase());
		//이름으로 내림차순 정렬(대소문자 구분없이)
		//return vo.name.toLowerCase().compareTo(this.name.toLowerCase());
		
		/*가입일로 정렬 후, 같은 가입일에서 아이디 값으로 정렬.
		String strMjoinDate1 = this.joinDate.toString();
		String strMjoinDate2 = vo.joinDate.toString();
		return (strMjoinDate1+this.id.toLowerCase()).compareTo(strMjoinDate2+vo.id.toLowerCase());
		*/
		
		//가입일로 정렬 후, 같은 가입일에서 이름 값으로 내림차순 정렬.
		String strMjoinDate1 = this.joinDate.toString();
		String strMjoinDate2 = vo.joinDate.toString();
		return (strMjoinDate2+vo.name.toLowerCase()).compareTo(strMjoinDate1+this.name.toLowerCase());
	}	
}
