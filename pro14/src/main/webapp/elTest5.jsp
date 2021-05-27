<%@ page language="java" contentType="text/html; charset=UTF-8"
	 pageEncoding="UTF-8" isELIgnored="false"
	 import="sec01.ex01.MemberBean"
	 import="java.util.ArrayList" %>
    
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
    
<jsp:useBean id="memberBean" class="sec01.ex01.MemberBean" scope="page"  />
<jsp:setProperty name="memberBean" property="name" value="이순신"/>

<jsp:useBean id="memberList"  class="java.util.ArrayList" scope="page"/>				 

<%-- <% MemberBean mi = new MemberBean(); %> --%>

<%
	MemberBean mi = new MemberBean();
	mi.setId("orange");
	request.setAttribute("mi", mi);
%>




<html>
<head>
   <meta charset="UTF-8">
   <title>표현언어의 여러 가지 연산자들</title>
</head>
<body>
<% 
int a  ;   //초기화 안 함, 메모리할당도 안 됨. 값이 주어질 때 메모리 할당 됨.
int b = 10 ; 
int a2  = 11 ;
Integer c = null; 
Integer d = 10 ; 
double e ;    //초기화 안 함
double f = 10.1 ; 
float g ;    //초기화 안 함
float h = 10.1F ; 
boolean i  ;    //초기화 안 함
boolean j = false ; 
String k1 = null;
String k2 = ""; 
String l ="신상현";

//request.setAttribute("a", a); //컴파일 오류, 기본타입 변수를 초기화 하지 않았으므로
request.setAttribute("b", b);
request.setAttribute("c", c);
request.setAttribute("d", d);
//request.setAttribute("e", e); //컴파일 오류, 기본타입 변수를 초기화 하지 않았으므로
request.setAttribute("f", f);
//request.setAttribute("g", g); //컴파일 오류, 기본타입 변수를 초기화 하지 않았으므로
request.setAttribute("h", h);
//request.setAttribute("i", i); //컴파일 오류, 기본타입 변수를 초기화 하지 않았으므로
request.setAttribute("j", j);
request.setAttribute("k1", k1);
request.setAttribute("k2", k2);
request.setAttribute("l", l);
%>

	<h1>empty 연산자</h1>
	<h2>
	   <!-- useBean 액션태그로 생성한 빈 객체의 경우에 empty 연산자가 정상적으로
	                작동합니다.
		--> 
		\${b } 자바int변수 b의 값표시: ${b }  <br>
		\${l } 자바 String변수 l 값표시: ${l } <br>
		\${empty k1 }자바변수의 값존재 유무확인 : ${empty k1 } <br>
		\${empty a }자바변수의 값존재 유무확인 : ${empty a } <br>
		\${(a2==b)}  : ${(a2==b)} <br>
		\${(a2!=b)}  : ${(a2!=b)} <br>
		
		\${mi.id } : ${mi.id } <br>
		\${empty mi.id } : ${empty mi.id } <br>
		\${empty mi.name } : ${empty mi.name } <br>
	
		\${empty memberBean.id } : ${empty memberBean.id } <br>
		\${empty memberBean.name } : ${empty memberBean.name } <br>
		
		\${not empty memberBean } : ${not empty memberBean } <br><br>

		\${empty memberList } : ${empty memberList } <br>
		\${not empty memberList} : ${not empty memberList } <br><br>


		<%=mi.getId() %><br>
		\${mi.id}:  ${mi.id} <br>
		\${memberBean.name}: ${memberBean.name}<br>
		\${empty mi}: ${empty mi}<br>



		\${empty "hello"} : ${empty "hello" }<br>
		\${empty 0} : ${empty 0 }<br>
		\${empty 1} : ${empty 1 }<br>
		\${empty null} : ${empty null } <br>
		\${empty ""} : ${empty "" } <br>
   
   
   <!-- 위에서 선언된 자바의 기본 데이터유형의 변수에 empty 연산자를 -->

<%-- <% 
int a  ;   //초기화 않함
int b = 10 ; 
Integer c = null; 
Integer d = 10 ; 
double e ;    //초기화 않함
double f = 10.1 ; 
float g ;    //초기화 않함
float h = 10.1F ; 
boolean i  ;    //초기화 않함
boolean j = false ; 
String k1 = null;
String k2 = ""; 
String l ="신상현";
%> --%>

   \${empty a} : 값없음 : ${empty a } <br>
   \${empty b} : 값있음 : ${empty b } <br>
   \${empty c} : 값없음 : ${empty c } <br>
   \${empty d} : 값있음 : ${empty d } <br>
   \${empty e} : 값없음 : ${empty e } <br>
   \${empty f} : 값있음 : ${empty f } <br>
   \${empty g} : 값없음 : ${empty g } <br>
   \${empty h} : 값있음 : ${empty h } <br>
   \${empty i} : 값없음 :  ${empty i } <br>
   \${empty j} : 값있음(false): ${empty j } <br>
   \${empty k1 null}: ${empty k1 } <br>
   \${empty k2: ""} : ${empty k2 } <br>
   \${empty l} : 값있음 : ${empty l } <br>
</h2>
<hr>











<h2>
<%-- <c:set  var="m"  value=""  scope="page" />
<c:set  var="n"  value="${null}"  scope="page" />
<c:set  var="o"  value="${'신상현'}"  scope="page" />
<c:set  var="p"  value="${22}"  scope="page" />

     \${empty m} : ${empty m } <br>
     \${empty n} : ${empty n } <br>
     \${empty o} : ${empty o } <br>
     \${empty p} : ${empty p } <br> 
--%>
</h2>
</body>
</html>









