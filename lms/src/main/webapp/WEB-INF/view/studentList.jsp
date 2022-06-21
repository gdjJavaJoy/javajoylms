<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>studentList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>학생 리스트</h1>
    <table class="table table-striped">
        <thead>
        <tr>
			<th>분반</th>
			<th>아이디</th>
			<th>이름</th>
			<th>성별</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>상세주소</th>
			<th>이메일</th>
			<th>학력</th>
			<th>등록날짜</th>
			<th>수정일짜</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="s" items="${list}">
                <tr>
                   <td>${s.subjectNo}</td>
                   <td>${s.memberId}</td>
                   <td>${s.studentName}</td>
                   <td>${s.studentGender}</td>
                   <td>${s.studentPhone}</td>
                   <td>${s.studentAddress}</td>
                   <td>${s.studentDetailAddress}</td>
                   <td>${s.studentEmail}</td>
                   <td>${s.studentEducation}</td>
                   <td>${s.studentRegisterDate}</td>
                   <td>${s.updateDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
   <ul class="pager">
      <li class="previous"><a href="${pageContext.request.contextPath}/studentList?currentPage=${currentPage-1}">이전</a></li>
      <li class="next"><a href="${pageContext.request.contextPath}/studentList?currentPage=${currentPage+1}">다음</a></li>
   </ul>
</div>
</body>
</html>