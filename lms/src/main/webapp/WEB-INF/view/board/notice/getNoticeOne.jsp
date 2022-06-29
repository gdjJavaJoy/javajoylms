<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getNoticeOne</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script>




</script>

</head>
<body>
<div class="container">
	<h1>getNoticeOne</h1>
	<a href="${pageContext.request.contextPath}/getNoticeByPage">목록</a>
		<C:forEach var="n" items="${board}">
			<table class="table table=striped">
				<tr>
					<td>번호</td>
					<td>${n.boardNo}</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>운영자</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${n.boardTitle}</td>
				</tr>
				<tr>
					<td>생성날짜</td>
					<td>${n.createDate}</td>
				</tr>
				<tr>
					<td>수정날짜</td>
					<td>${n.updateDate}</td>
				</tr>
				<C:if test="${fileTotalCount ne 0}">
				<tr>
					<td>첨부파일</td>
					<td>
						<C:forEach var="boardfile" items="${boardfile}">
							<div>
								<a href="${pageContext.request.contextPath}/file/boardFile/${boardfile.boardFileName}${nr.boardFileType}" download="${boardfile.boardFileName}${nr.boardFileType}">${boardfile.boardFileOriginalName}${nr.boardFileType}</a>
							</div>
						</C:forEach>
					</td>	
				</tr>
				</C:if>
				<tr>
					<td>내용</td>
					<td>${n.boardContent}</td>
				</tr>
			</table>
		<C:if test="${level eq 1}">
				<a href="${pageContext.request.contextPath}/removeNotice?boardNo=${n.boardNo}">삭제</a>
				<a href="${pageContext.request.contextPath}/modifyNotice?boardNo=${n.boardNo}">수정</a>
		</C:if>
		</C:forEach>	
</div>
</body>
</html>