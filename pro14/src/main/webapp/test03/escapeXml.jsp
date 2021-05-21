<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
		 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%	request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>escapeXml 실습하기</title>
</head>
<body>
	<h2>escapeXml 변환하기</h2>
	
	<hr>
	<h2>pre 태그 사용 않함 <br>
<  ssh  >    &    '   ''
&lt;     &gt;	
&amp; 	 &#039;           &#034;
	</h2>
	<hr>
	<pre>
	
	pre 태그 사용 <br>
	<     ssh  >    &    '   ''
&lt;     &gt;	&amp; 	 &#039;           &#034;
	
	</pre>
	
	<hr>
	<h2> pre 태그 사용 않고 c:out 사용<br>
		<c:out  value="&lt;" escapeXml="true" />
		<c:out  value="&gt;" />
		<c:out  value="&amp;"/>
		<c:out  value="&#039;"/>
	</h2>
		<hr>
	<h2>escapeXml="true" 일 때 &<br>
		
		<c:out  value=">"/>
		<c:out  value="<"/>
		<c:out  value="'"/>
		<c:out  value="&"/>
		<c:out  value="\""/>
	</h2>
		<hr>
	<h2>escapeXml="false" 일 때 &lt;<br>
		<c:out  value="&lt;" escapeXml="false" />
		<c:out  value="&gt;" escapeXml="false" />
		<c:out  value="&amp;" escapeXml="false" />
		<c:out  value="&#039;" escapeXml="false" />
		<c:out  value="&#034;" escapeXml="false" />
	</h2>
	<hr>
	<h2>escapeXml="false" 일 때 &<br>
		<c:out  value="<" escapeXml="false" />
		<c:out  value=">" escapeXml="false" />
		<c:out  value="&" escapeXml="false" />
		<c:out  value="'" escapeXml="false" />
		<c:out  value="\"" escapeXml="false" />
	</h2>
	<hr>
	<h2>pre 태그와 c:out 사용<br>
		<pre>
			<c:out  value="&lt;" escapeXml="true" />
			<c:out  value="&gt;" />
			<c:out  value="&amp;"/>
			<c:out  value="&#039;"/>
			<c:out  value="&#034;"/>
			<c:out  value="&lt;" escapeXml="false" />
			<c:out  value="&gt;" escapeXml="false" />
			<c:out  value="&amp;" escapeXml="false" />
			<c:out  value="&#039;" escapeXml="false" />
			<c:out  value="&#034;" escapeXml="false" />
		</pre>
	</h2>
	<hr>
	
	<h2>c:out 테그 사용 이유</h2>
	
	<h2>
	<c:out value="<script type='text/javascript'>alert(1);</script>" escapeXml="true"/>
	</h2>
	<hr>
	<h2>
	<%-- <c:out value="<script type='text/javascript'>alert(1);</script>" escapeXml="false"/> --%>
	<!-- <script type='text/javascript'>alert(1);</script> -->
	</h2>
	<hr>
	
	
	
	
	
	
	
</body>
</html>
