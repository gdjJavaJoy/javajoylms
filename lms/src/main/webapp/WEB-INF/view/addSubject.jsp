<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert Subject(운영자)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
</head>
<body>
<div class="container">
    <h1>강좌 추가</h1>
	    <div>
	   		<a class="btn btn-primary" href="${pageContext.request.contextPath}/getSubjectByPage">이전</a>
	    </div>
    <form id="addSubject" method="post" name="addSujectForm" action="${pageContext.request.contextPath}/addSubject">
    	<table>
    		<tr>
    			<th>담임 강사</th>
    			<td>
    				<input type="text" id="teacherId" name="teacherId" class="form-control" placeholder="강사이름">
    				<span id="teacherIdHelper" class="helper"></span>
    			</td>
    		</tr>
    		<tr>
    			<th>개설 운영자</th>
    			<td>
    				<input type="text" id="adminId" name="adminId" class="form-control" placeholder="개설 운영자 이름">
    				<span id="adminIdHelper" class="helper"></span>
    			</td>
    		</tr>
    		<tr>
    			<th>강좌 이름</th>
    			<td>
    				<input type="text" id="subjectName" name="subjectName" class="form-control" placeholder="강좌 이름">
    				<span id="subjectNameHelper" class="helper"></span>
    			</td>
    		</tr>
    		<tr>
    			<th>총원</th>
    			<td>
    				<input type="text" id="subjectStudentMax" name="subjectStudentMax" class="form-control" placeholder="총원">
    				<span id="subjectStudentMaxHelper" class="helper"></span>
    			</td>
    		</tr>
    		<tr>
    			<th>강좌 설명</th>
    			<td>
    				<textarea id="subjectInfo" name="subjectInfo" class="form-control" placeholder="강좌 설명"></textarea>
    				<span id="subjectInfoHelper" class="helper"></span>
    			</td>
    		</tr>
    		<tr>
    			<th>개강 일</th>
    			<td>
    				<input type="text" id="subjectStartDate" name="subjectStartDate" class="form-control" placeholder="개강 일">
    				<span id="subjectStartDateHelper" class="helper"></span>
    			</td>
    		</tr>
    		<tr>
    			<th>종강 일</th>
    			<td>
    				<input type="text" id="subjectFinishDate" name="subjectFinishDate" class="form-control" placeholder="종강 일">
    				<span id="subjectFinishDateHelper" class="helper"></span>
    			</td>
    		</tr>
    		<tr>
    			<th>강의 시작 시간</th>
    			<td>
    				<input type="text" id="subjectStartTime" name="subjectStartTime" class="form-control" placeholder="강의 시작 시간">
    				<span id="subjectStartTimeHelper" class="helper"></span>
    			</td>
    		</tr>
    		<tr>
    			<th>강의 마감 시간</th>
    			<td>
    				<input type="text" id="subjectEndTimee" name="subjectEndTime" class="form-control" placeholder="강의 마감 시간">
    				<span id="subjectEndTimeeHelper" class="helper"></span>
    			</td>
    		</tr>
    	</table>
    </form>
</div>
<!-- 강좌 입력 유효성 검사 -->
	<script type="text/javascript">	
		// 유효성 검사 코드 추가 예쩡...
	</script>
</body>
</html>