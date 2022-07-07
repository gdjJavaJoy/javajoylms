<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>curriculumList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script> 	
</head>
<body>
<div class="container">
    <h1>커리큘럼 리스트</h1>
    	<div><a href="${pageContext.request.contextPath}/getLanguageList">프로그래밍 언어 관리</a></div>
    	<div><a href="${pageContext.request.contextPath}/getBookList">교육 도서 관리</a></div>
    	<div><a href="${pageContext.request.contextPath}/addCurriculum?subjectNo=${subjectNo}">커리큘럼 추가</a></div>
    <table class="table table-striped">
        <thead>
        <tr>
        	<th>번호</th>
			<th>강사 이름</th>
			<th>교육 언어</th>
			<th>제목</th>
			<th>시작날짜</th>
			<th>종료날짜</th>
			<th></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="s" items="${curriculumList}" varStatus="cnt">
                <tr onClick="location.href='${pageContext.request.contextPath}/getCurriculumOne?CurriculumNo=${s.CurriculumNo}'" style="cursor:pointer;" class="text-gray-700 dark:text-gray-400">
                   <td class="px-4 py-3 text-sm">${cnt.index+1}</td>
                   <td>${s.teacherName}</td>
                   <td>${s.languageName}</td>
                   <td>${s.curriculumTitle}</td>
                   <td>${s.startDay}</td>
                   <td>${s.endDay}</td>
                   <td>삭제?수정?</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>