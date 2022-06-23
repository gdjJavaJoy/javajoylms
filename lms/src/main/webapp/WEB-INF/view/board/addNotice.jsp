<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>addNotice</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- <script>
	$(document).ready(function() {
		$('#addFileupload').click
			let flag = true;
		$('.noticefileList').each(function(){
			if($(this).val() == '' ) {
				flag = false;
			}
		});
		if(flag) {
			$('#fileSection').append("<div><input class='noticefileList' type='file' name='noticefileList'></div>")
		} else {
			alert('파일 첨부되지 않은 noticefileList가 존재합니다');
		}
	});
	
	$('#addNotice').click(function(){
		let flag = true;
		if($('#boardTitle').val() == ''){
			alert('noticeTitle 입력하세요');
		} else if($('#boardContent').val() == '') {
			alert('noticeContent 입력하세요');
		} else {
			$('.noticefileList').each(function(){
				if($(this).val() == '') {
					flag = false;
				}
			});
			if(flag) {
				$('#addForm').submit();
			} else {
				alert('파일이 첨부되지않은 noticefileList가 존재합니다');
			}
		}
	});
</script>
 -->
</head>
<body>
<div>
	<form method="post" action="${pageContext.request.contextPath}/addNotice" enctype="multipart/form-data" id="addForm">
		<h1>addNotice</h1>
		<table class="table table-striped">
			<tr>
				<td>ID</td>
				<td>
					<!-- 임시 값 대입 추후 세션 추가 예정 -->
					<input name="memberId" value="admin">
				</td>
			</tr>
			<tr>
				<td>카테고리</td>
				<td>
					<select name="boardCategory">
						<option value="1">전체공지사항</option>
						<option value="2">자유게시판</option>
						<option value="3">전체공지</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="boardTitle" id="boardTitle">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<input type="text" name="boardContent" id="boardContent">
				</td>
			</tr>
			<tr>
				<td>익명여부</td>
				<td>
					<input type="radio" name="privateNo" value="1">없음<br>
					<input type="radio" name="privateNo" value="2">익명<br>
					<input type="radio" name="privateNo" value="3">비밀
				</td>
			</tr>
			<tr>
				<td>파일 업로드</td>
				<td>
				파일 : <input type="file" name="boardfileList" multiple="multiple" >
					<!-- <button type="button" id="addFileupload">파일 업로드 추가</button>
					<div id="fileSection"> -->
					
					</div>
				</td>
			</tr>
		</table>
		<button type="submit" id="addNotice">작성</button>
	</form>
</div>
</body>
</html>