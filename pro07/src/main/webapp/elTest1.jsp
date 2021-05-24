<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어에서 사용되는 데이터들</title>
</head>
<body>
<h1>
\${100 }: ${100 }<br>
\${"hi" }: ${"hi" }<br>
\${10+1 }: ${10+1 }<br>
\${"10"+1 }: ${"10"+1 }<br>
\${null+10 }: ${null+10 }<br>
\${null*10 }: ${null*10 }<br>

<%-- \${"hi"+10 }: ${"hi"+10 }<br> --%>
\${"hi"+"world" }: ${"hi"+"world" }<br>

</h1>
</body>
</html>