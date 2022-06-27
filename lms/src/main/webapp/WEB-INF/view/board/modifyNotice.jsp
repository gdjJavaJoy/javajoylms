<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>modifyNotice</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
			let flag = true;
		$('#addFileupload').click(function(){
			$('.boardfileList').each(function(){
				if($(this).val() == '' ) {
					flag = false;
				}
		});
		if(flag) {
			$('#fileSection').append("<div><input class='boardfileList' type='file' name='boardfileList'></div>");
		} else {
			alert('파일 첨부되지 않은 boardfileList가 존재합니다');
		}
	});
	
	$('#modifyNotice').click(function(){
		if($('#boardTitle').val() == ''){
			alert('noticeTitle 입력하세요');
		} else if($('#boardContent').val() == '') {
			alert('noticeContent 입력하세요');
		} else {
			$('.boardfileList').each(function(){
				if($(this).val() == '') {
					flag = false;
				}
			});
			if(flag) {
				$('#addForm').submit();
			} else {
				alert('파일이 첨부되지않은 boardfileList가 존재합니다');
			}
		}
		
	});
});
</script>
</head>
<body>
<h1>modifyNotice</h1>
<a href="${pageContext.request.contextPath}/getNoticeByPage">목록</a>
	<form method="post" action="${pageContext.request.contextPath}/modifyNotice" id="addForm">
		<C:forEach var="n" items="${board}">
			<table class="table table=striped">
				<tr>
					<td>번호</td>
					<td>
						<input type="number" value="${n.boardNo}" name="boardNo" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<input value="${loginUser}" id="memberId" name="memberId" readonly="readonly" >
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" value="${n.boardTitle}" name="boardTitle" id="boardTitle">
					</td>
				</tr>
				<tr>
					<td>파일 업로드</td>
					<td>
					<!-- 파일 : <input type="file" name="boardfileList" multiple="multiple" id="addfFileupload">-->
						<button type="button" id="addFileupload">파일 업로드 추가</button>
						<div id="fileSection"> 
						</div>
					</td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td>
						<C:forEach var="boardfile" items="${boardfile}">
									<div>
										${boardfile.boardFileName}${boardfile.boardFileType}<a href="${pageContext.request.contextPath}/removeNoticefile?boardFileNo=${boardfile.boardFileNo}&boardNo=${boardfile.boardNo}">삭제</a>
									</div>
						</C:forEach>
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="boardContent" id="boardContent">${n.boardContent}</textarea>
					</td>
				</tr>
			</table>
			<button type="button" id="modifyNotice">게시글 수정</button>
		</C:forEach>
	</form>
				
</body>
</html>