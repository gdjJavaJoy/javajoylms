<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>subjectList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>강좌 리스트</h1>
    <table class="table table-striped">
        <thead>
        <tr>
			<th>강좌게시판 번호</th>
			<th>강좌번호</th>
			<th>아이디</th>
			<th>제목</th>
			<th>생성날짜</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="s" items="${list}">
                <tr>
                   <td>${s.subjectDataNo}</td>
                   <td>${s.subjectNo}</td>
                   <td>${s.memberId}</td>
                   <td>${s.subjectDataTitle}</td>
                   <td>${s.createDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
   <ul class="pager">
      <li class="previous"><a href="${pageContext.request.contextPath}/subjectList?currentPage=${currentPage-1}">이전</a></li>
      <li class="next"><a href="${pageContext.request.contextPath}/subjectList?currentPage=${currentPage+1}">다음</a></li>
   </ul>
</div>
</body>
</html>