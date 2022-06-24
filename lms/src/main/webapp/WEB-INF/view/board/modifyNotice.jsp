<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>addNotice</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<h1>modifyNotice</h1>
<a href="${pageContext.request.contextPath}/getNoticeByPage">목록</a>
	<form method="post" action="${pageContext.request.contextPath}/modifyNotice">
		<C:forEach var="n" items="${board}">
			<table class="table table=striped">
				<tr>
					<td>번호</td>
					<td>
						<input type="number" value="${n.boardNo}" name="boardNo" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<input value="${n.memberId}" name="memberId" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" value="${n.boardTitle}" name="boardTitle">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="boardContent" >${n.boardContent}</textarea>
					</td>
				</tr>
			</table>
			<button type="submit">게시글 수정</button>
		</C:forEach>
	</form>
	<div>첨부파일</div>
	<C:forEach var="boardfile" items="${boardfile}">
				<div>
					${boardfile.boardFileName}${boardfile.boardFileType}<a href="${pageContext.request.contextPath}/removeNoticefile?boardFileNo=${boardfile.boardFileNo}&boardNo=${boardfile.boardNo}">삭제</a>
				</div>
	</C:forEach>
	
</body>
</html>