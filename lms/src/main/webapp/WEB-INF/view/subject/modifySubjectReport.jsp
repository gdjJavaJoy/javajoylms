@@ -0,0 +1,69 @@
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Modify Subject(운영자/강사)</title>
	<!-- bootstrap을 사용하기 위한 CDN주소 -->
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h1>과제 게시판 수정</h1>
		    <form id="modifySubjectReportForm" method="post" name="modifySubjectReportForm" action="${pageContext.request.contextPath}/modifySubjectReport">
		    	<c:forEach var="subjectReport" items="${subjectReport}">
		    		<input type="hidden" id="subjectBoardNo" name="subjectBoardNo" class="form-control" value="${subjectReport.subjectBoardNo}">
			    	<input type="hidden" id="subjectNo" name="subjectNo" class="form-control" value="${subjectReport.subjectNo}">
			    	<input type="hidden" id="subjectName" name="subjectName" class="form-control" value="${subjectReport.subjectName}">
			    	<div>
			   			memberId<input type="text" id="memberId" name="memberId" class="form-control" value="${subjectReport.memberId}">
			   		 	<span id="memberIdHelper" class="helper"></span>	
			    	</div>
			    	<div>  	
			    		subjectReportTitle<input type="text" id="subjectReportTitle" name="subjectReportTitle" class="form-control" value="${subjectReport.subjectReportTitle}">
			    		<span id="subjectReportTitleNameHelper" class="helper"></span>	
			    	</div>
			    	<div>
			    		subjectReportPeriod<input type="date" id="subjectReportPeriod" name="subjectReportPeriod" class="form-control" value="${subjectReport.subjectReportPeriod}">
						<span id="subjectReportPeriod" class="helper"></span>
			    	</div>
			    	<div>	
						subjectReportContent<input type="text" id="subjectReportContent" name="subjectReportContent" class="form-control" value="${subjectReport.subjectReportContent}">
						<span id="subjectReportContent" class="helper"></span>
			    	</div>
		    	</c:forEach>
		    			첨부파일
		    			<c:forEach var="subjectFile" items="${subjectFile}">
							<div>
								${subjectFile.subjectFileOriginalName}<a href="${pageContext.request.contextPath}/removeSubjectFile?subjectFileNo=${subjectFile.subjectFileNo}&subjectBoardNo=${subjectFile.subjectBoardNo}">삭제</a>
							</div>
						</c:forEach>
		    	<div class="form-group">
					 <button type="submit">과제 수정</button>
				</div>
		    </form>
		</div>
</body>
</html> 