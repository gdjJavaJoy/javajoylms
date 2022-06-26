<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getNoticeByPage</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
	<h1>getNoticeByPage</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>공지사항 번호</th>
				<th>회원아이디</th>
				<th>제목</th>
				<th>생성날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${list}">
				<tr>
					<td>${c.boardNo}</td>
					<td><a href="${pageContext.request.contextPath}/getNoticeOne?boardNo=${c.boardNo}">${c.memberId}</a></td>
					<td><a href="${pageContext.request.contextPath}/getNoticeOne?boardNo=${c.boardNo}">${c.boardTitle}</a></td>
					<td><a href="${pageContext.request.contextPath}/getNoticeOne?boardNo=${c.boardNo}">${c.createDate}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pager">
		<c:if test="${currentPage > 1}">
			<li class="previous"><a href="${pageContext.request.contextPath}/getNoticeByPage?currentPage=${currentPage-1}">이전</a></li>
		</c:if>
		<c:if test="${currentPage < lastPage}">
			<li class="next"><a href="${pageContext.request.contextPath}/getNoticeByPage?currentPage=${currentPage+1}">다음</a></li>
		</c:if>
	</ul>
	<div>
	<c:if test="${level eq 1}">
		<a class="btn btn-default" href="${pageContext.request.contextPath}/addNotice">공지 입력</a>
	</c:if>
	</div>
</div>
</body>
</html>