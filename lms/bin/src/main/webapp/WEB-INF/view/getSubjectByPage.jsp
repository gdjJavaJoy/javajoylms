<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getSubjectByPage</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
</head>
<body>
<div class="container">
    <h1>강좌 리스트</h1>
	    <div>
	   		<a class="btn btn-primary" href="${pageContext.request.contextPath}/addSubject">강좌 추가</a>
	    </div>
    <table class="table table-striped">
        <thead>
        <tr>
               <th>번호</th>
               <th>담당 강사</th>
               <th>개설 운영자</th>
               <th>강좌 이름</th>
               <th>총원</th>
               <th>설명</th>
               <th>강좌 시작일</th>
               <th>강좌 수료일</th>
               <th>강좌 시작시간</th>
               <th>강좌 종료시간</th>
               <th>create_date</th>
               <th>update_date</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="s" items="${list}">
                <tr>
                   <td>${s.subjectNo}</td>
                   <td>${s.teacherId}</td>
                   <td>${s.adminId}</td>
                   <td>${s.subjectName}</td>
                   <td>${s.subjectStudentMax}</td>
                   <td>${s.subjectInfo}</td>
                   <td>${s.subjectStartDate}</td>
                   <td>${s.subjectFinishDate}</td>
                   <td>${s.subjectStartTime}</td>
                   <td>${s.subjectEndTime}</td>
                   <td>${s.createDate}</td>
                   <td>${s.updateDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>
    	강좌 검색
    </div>
   <ul class="pager">
      <li class="previous"><a href="${pageContext.request.contextPath}/getSubjectByPage?currentPage=${currentPage-1}">이전</a></li>
      <li class="next"><a href="${pageContext.request.contextPath}/getSubjectByPage?currentPage=${currentPage+1}">다음</a></li>
   </ul>
</div>
</body>
</html>