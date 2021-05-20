package sec01.ex02;

import java.sql.Date;

public class MemberVO {
   private String id;
   private String password;
   private String name;
   private String email;
   private Date joinDate ;
   

//   public MemberVO(String id, String password, String name, String email, Date joinDate) {
//      this.id = id;
//      this.password = password;
//      this.name = name;
//      this.email = email;
//      this.joinDate = joinDate;
//   }

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