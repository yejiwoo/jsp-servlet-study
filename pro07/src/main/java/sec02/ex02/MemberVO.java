package sec02.ex02;

import java.sql.Date;

public class MemberVO implements Comparable<MemberVO>{
   private String id;
   private String password;
   private String name;
   private String email;
   private Date joinDate ;
   
   @Override
   public int compareTo(MemberVO memberVO) {
      //가입입자 기준 오름차순 정렬
      //return this.joinDate.compareTo(memberVO.joinDate);
      //가입입자기준 내림차순 정렬
      return memberVO.joinDate.compareTo(this.joinDate);
      //아이디 기준 오름차순 정렬(대소문자 구분없이)
      //return this.id.toLowerCase().compareTo(memberVO.id.toLowerCase());
      //아이디기준 내림차순 정렬(대소문자 구분없이)
      //return memberVO.id.toLowerCase().compareTo(this.id.toLowerCase());
      
      //가입일로 오름차순정렬 후, 같은 가입일에서 아이디 값으로 오름차순정렬.
//      String strMjoinDate1 = this.joinDate.toString();
//      String strMjoinDate2 = memberVO.joinDate.toString();
//      return (strMjoinDate1+this.id.toLowerCase())
//             .compareTo(strMjoinDate2+memberVO.id.toLowerCase());
      //가입일로 내림차순정렬 후, 같은 가입일에서 아이디 값으로 내림차순정렬.
      //String strMjoinDate1 = this.joinDate.toString();
      //String strMjoinDate2 = memberVO.joinDate.toString();
      //return (strMjoinDate2+memberVO.id.toLowerCase())
      //      .compareTo(strMjoinDate1+this.id.toLowerCase());
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

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((email == null) ? 0 : email.hashCode());
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + ((joinDate == null) ? 0 : joinDate.hashCode());
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + ((password == null) ? 0 : password.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      MemberVO other = (MemberVO) obj;
      if (email == null) {
         if (other.email != null)
            return false;
      } else if (!email.equals(other.email))
         return false;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      if (joinDate == null) {
         if (other.joinDate != null)
            return false;
      } else if (!joinDate.equals(other.joinDate))
         return false;
      if (name == null) {
         if (other.name != null)
            return false;
      } else if (!name.equals(other.name))
         return false;
      if (password == null) {
         if (other.password != null)
            return false;
      } else if (!password.equals(other.password))
         return false;
      return true;
   }

   @Override
   public String toString() {
      return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", joinDate="
            + joinDate + "]";
   }
   
   
   

}