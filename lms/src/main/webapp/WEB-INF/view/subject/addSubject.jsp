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
    <h1>강좌 추가</h1>
	    <div>
	   		<a href="${pageContext.request.contextPath}/getSubjectByPage">이전</a>
	    </div>
		    <form id="addSubjectForm" method="post" name="addSujectForm" action="${pageContext.request.contextPath}/addSubject">
		    	<select class="form-control" id="teacherId" name="teacherId">
		    		<option value="" selected disabled>강사 선택</option>
		    			<c:forEach var="c" items="${teacherList}">
		    				<option value="${c}">${c}</option>
		    			</c:forEach>
		    	</select> 
		    	<div>
		   			<input type="text" id="adminId" name="adminId" class="form-control" placeholder="개설 운영자 이름">
		   		 	<span id="adminIdHelper" class="helper"></span>	
		    	</div>
		    	<div>  	
		    		<input type="text" id="subjectName" name="subjectName" class="form-control" placeholder="강좌 이름">
		    		<span id="subjectNameHelper" class="helper"></span>	
		    	</div>
		    	<div>	
					<input type="number" id="subjectStudentMax" name="subjectStudentMax" class="form-control" placeholder="총원">
					<span id="subjectStudentMaxHelper" class="helper"></span>
		    	</div>
		    	<div>
		    		<textarea id="subjectInfo" name="subjectInfo" class="form-control" placeholder="강좌 설명"></textarea>
		    		<span id="subjectInfoHelper" class="helper"></span>
		    	</div>
		    	<div>
		    		<input type="date" id="subjectStartDate" name="subjectStartDate" class="form-control" placeholder="개강 일">
		    		<span id="subjectStartDateHelper" class="helper"></span>	
		    	</div>
		    	<div> 		
		    		 <input type="date" id="subjectFinishDate" name="subjectFinishDate" class="form-control" placeholder="종강 일">
		    		 <span id="subjectFinishDateHelper" class="helper"></span>	
		    	</div>
		    	<div>
		    		 <input type="time" id="subjectStartTime" name="subjectStartTime" class="form-control" placeholder="강의 시작 시간">
		    		 <span id="subjectStartTimeHelper" class="helper"></span>	
		    	</div>
		    	<div>
		    		 <input type="time" id="subjectEndTime" name="subjectEndTime" class="form-control" placeholder="강의 마감 시간">
		    		 <span id="subjectEndTimeHelper" class="helper"></span>
		    	</div>
		    	<div class="form-group">
					 <button type="button" id="signup">강좌 추가</button>
				</div>
		    </form>
</div>
<!-- 강좌 입력 유효성 검사 -->
	<script type="text/javascript">	
		// 개별 유효성 검사 코드 
		$('#adminId').blur(function() {
			if ($('#adminIdId').val().length == 0) {
				$('#adminIdHelper').text('개설 운영자를 입력하세요');
				$('#adminId').focus();
			} else {
				$('#adminIdHelper').text('');
			}
		});
	
		$('#subjectName').blur(function() {
			if ($('#subjectName').val().length == 0) {
				$('#subjectNameHelper').text('강좌이름을 입력하세요');
				$('#subjectName').focus();
			} else {
				$('#subjectNameHelper').text('');
			}
		});
		
		$('#subjectStudentMax').blur(function() {
			if ($('#subjectStudentMax').val().length == 0) {
				$('#subjectStudentMaxHelper').text('강좌 총원을 입력하세요');
				$('#subjectStudentMax').focus();
			} else {
				$('#subjectStudentMaxHelper').text('');
			}
		});
		
		$('#subjectInfo').blur(function() {
			if ($('#subjectInfo').val().length == 0) {
				$('#subjectInfoHelper').text('강좌 설명을 작성하세요');
				$('#subjectInfo').focus();
			} else {
				$('#subjectInfoHelper').text('');
			}
		});
		
		$('#subjectStartDate').blur(function() {
			if ($('#subjectStartDate').val().length == 0) {
				$('#subjectStartDateHelper').text('강좌 개강일을 입력하세요');
				$('#subjectStartDate').focus();
			} else {
				$('#subjectStartDateHelper').text('');
			}
		});
		
		$('#subjectFinishDate').blur(function() {
			if ($('#subjectFinishDate').val().length == 0) {
				$('#subjectFinishDateHelper').text('강좌 종강일을 입력하세요');
				$('#subjectFinishDate').focus();
			} else {
				$('#subjectFinishDateHelper').text('');
			}
		});
		
		$('#subjectStartTime').blur(function() {
			if ($('#subjectStartTime').val().length == 0) {
				$('#subjectStartTimeHelper').text('강좌 시작 시간을 입력하세요');
				$('#subjectStartTime').focus();
			} else {
				$('#subjectStartTimeHelper').text('');
			}
		});
		
		$('#subjectEndTime').blur(function() {
			if ($('#subjectEndTime').val().length == 0) {
				$('subjectEndTimeHelper').text('강좌 마감 시간을 입력하세요');
				$('#subjectEndTime').focus();
			} else {
				$('#subjectEndTimeHelper').text('');
			}
		});
		
		// 회원 가입 버튼을 눌렀을 시, 진행되는 이벤트 유효성 체크
		$('#signup').click(function() {
			if ($('#adminIdId').val() == '') {
				$('#teacherIdHelper').text('');
				$('#adminIdHelper').text('개설 운영자를 입력하세요');
				$('#adminId').focus();
			} else if ($('#subjectName').val() == '') {
				$('#adminIdHelper').text('');
				$('#subjectNameHelper').text('강좌 이름을 입력하세요');
				$('#subjectName').focus();
			} else if ($('#subjectStudentMax').val() == '') {
				$('#subjectNameHelper').text('');
				$('#subjectStudentMaxHelper').text('강좌 총원을 입력하세요');
				$('#subjectStudentMax').focus();
			} else if ($('#subjectInfo').val() == '') {
				$('#subjectStudentMaxHelper').text('');
				$('#subjectInfoHelper').text('강좌 설명을 작성하세요');
				$('#subjectInfo').focus();
			} else if ($('#subjectStartDate').val() == '') {
				$('#subjectInfoHelper').text('');
				$('#subjectStartDateHelper').text('강좌 개강일을 입력하세요');
				$('#subjectStartDate').focus();
			} else if ($('#subjectFinishDate').val() == '') {
				$('#subjectStartDateHelper').text('');
				$('#subjectFinishDateHelper').text('강좌 종강일을 입력하세요');
				$('#subjectFinishDate').focus();
			} else if ($('#subjectStartTime').val() == '') {
				$('#subjectFinishDateeHelper').text('');
				$('#subjectStartTimeeHelper').text('강좌 시작시간을 입력하세요');
				$('#subjectStartTime').focus();
			} else if ($('#subjectEndTime').val() == '') {
				$('#subjectStartTimeHelper').text('');
				$('#subjectEndTimeHelper').text('강좌 마감 시간을 입력하세요');
				$('#subjectEndTime').focus();
			} else {
				$('#addSubjectForm').submit();
			}
		});
		
		function plus() {
			if (confirm('강좌 개설을 정말 하겠습니까?')) {
				document.getElementById('signup').click();
			}
		}
	</script>
</body>
</html>