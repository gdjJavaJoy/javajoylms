<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<h1>login</h1>
	
	관리자로그인
	<form action="${pageContext.request.contextPath}/login">
		ID : <input type="text" name="memerId">
		PW : <input type="text" name="memberPw">
		
		<button type="submit">로그인</button>
	</form>
	
	강사로그인
	<form action="${pageContext.request.contextPath}/login">
		ID : <input type="text" name="memerId">
		PW : <input type="text" name="memberPw">
		
		<button type="submit">로그인</button>
	</form>
	
	학생로그인
	<form action="${pageContext.request.contextPath}/login">
		ID : <input type="text" name="memerId">
		PW : <input type="text" name="memberPw">
		
		<button type="submit">로그인</button>
	</form>
	
</body>
</html>