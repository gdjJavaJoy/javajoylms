<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>sbujectNoticeList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>강의공지사항</h1>
    <table class="table table-striped">
        <thead>
        <tr>
			<th>공지사항번호</th>
			<th>분반</th>
			<th>아이디</th>
			<th>공지사항제목</th>
			<th>공지사항내용</th>
			<th>작성일</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="s" items="${list}">
                <tr>
                   <td>${s.subjectNoticeNo}</td>
                   <td>${s.subjcetNo}</td>
                   <td>${s.memberId}</td>
                   <td>${s.subjectNoticeTitle}</td>
                   <td>${s.subjectNoticeContent}</td>
                   <td>${s.createDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
   <ul class="pager">
      <li class="previous"><a href="${pageContext.request.contextPath}/curriculumList?currentPage=${currentPage-1}">이전</a></li>
      <li class="next"><a href="${pageContext.request.contextPath}/curriculum?currentPage=${currentPage+1}">다음</a></li>
   </ul>
</div>
</body>
</html>