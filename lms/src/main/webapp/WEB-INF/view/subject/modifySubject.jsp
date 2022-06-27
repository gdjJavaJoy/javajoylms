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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h1>${loginUser}님의 강좌 수정</h1>
	    <div>
	   		<a href="${pageContext.request.contextPath}/getSubjectByPage">이전</a>
	    </div>
		    <form id="modifySubjectForm" method="post" name="modifySubjectForm" action="${pageContext.request.contextPath}/modifySubject">
		    	<!-- subject_no값넘기기 -->
		    	<input type="hidden" name="subjectNo" readonly="readonly" value="${requestScope.subject.subjectNo}" required>
		    	<!-- 강사선택, 기본값:현재강사 -->
		    	<select class="form-control" id="teacherId" name="teacherId">
		    		<option value="${requestScope.subject.teacherId}">${requestScope.subject.teacherId}</option>
		    			<c:forEach var="c" items="${teacherList}">
		    				<option value="${c}">${c}</option>
		    			</c:forEach>
		    	</select> 
		    	<div>
		   			<input type="text" id="adminId" name="adminId" class="form-control" value="${loginUser}">
		   		 	<span id="adminIdHelper" class="helper"></span>	
		    	</div>
		    	<div>  	
		    		<input type="text" id="subjectName" name="subjectName" class="form-control" value="${requestScope.subject.subjectName}">
		    		<span id="subjectNameHelper" class="helper"></span>	
		    	</div>
		    	<div>	
					<input type="number" id="subjectStudentMax" name="subjectStudentMax" class="form-control" value="${requestScope.subject.subjectStudentMax}">
					<span id="subjectStudentMaxHelper" class="helper"></span>
		    	</div>
		    	<div>
		    		<input type="date" id="subjectStartDate" name="subjectStartDate" class="form-control" value="${requestScope.subject.subjectStartDate}">
		    		<span id="subjectStartDateHelper" class="helper"></span>	
		    	</div>
		    	<div> 		
		    		 <input type="date" id="subjectFinishDate" name="subjectFinishDate" class="form-control" value="${requestScope.subject.subjectFinishDate}">
		    		 <span id="subjectFinishDateHelper" class="helper"></span>	
		    	</div>
		    	<div>
		    		 <input type="time" id="subjectStartTime" name="subjectStartTime" class="form-control" value="${requestScope.subject.subjectStartTime}">
		    		 <span id="subjectStartTimeHelper" class="helper"></span>	
		    	</div>
		    	<div>
		    		 <input type="time" id="subjectEndTime" name="subjectEndTime" class="form-control" value="${requestScope.subject.subjectEndTime}">
		    		 <span id="subjectEndTimeHelper" class="helper"></span>
		    	</div>
		    	<div>
		    		<textarea id="subjectInfo" name="subjectInfo" class="form-control">${requestScope.subject.subjectInfo}</textarea>
		    		<span id="subjectInfoHelper" class="helper"></span>
		    	</div>
		    	<div class="form-group">
					 <button type="submit">강좌 수정</button>
				</div>
		    </form>
		</div>
</body>
</html>