<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>modifySubjectData</title>
<style>
textarea {
	width: 500px;
	height: 100px;
	resize: none;
	/* 크기고정 */
	/*	resize: horizontal; // 가로크기만 조절가능 
			resize: vertical;  세로크기만 조절가능  */
}
</style>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="./public/assets/css/tailwind.output.css" />
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="./public/assets/js/init-alpine.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
		$(document).ready(function(){
			let flag = true;
			$('#addFileupload').click(function(){
				$('.subjectBoardFileList').each(function(){
					if($(this).val() == '') {
						flag = false;
					}
				});
				
				if(flag) {
					$('#fileSection').append("<div><input type='file' class='subjectBoardFileList' onchange='checkFile(this)' id='subjectBoardFileList' name='subjectBoardFileList'></div>");
				} else {
					Swal.fire('파일 첨부를 다시 확인하십시오');
				}
			});
			
			$('#modifySubjectDataBtn').click(function(){
				if($('#memberId').val() == '') {
			    	Swal.fire('test');
			    	return;
				} else if($('#subjectDataTitle').val() == '') {
					Swal.fire('제목을 입력하세요');
					return;
				} else if($('#subjectDataContent').val() == '') {
					Swal.fire('내용을 입력하세요');
					return;
				} else {
					$('.subjectBoardFileList').each(function(){
						if($(this).val() == '') {
							flag = false;
							return;
						}
					});
					if(flag) {
						$('#modifySubjectDataForm').submit();
					} else {
						Swal.fire('파일 첨부를 다시 확인하십시오');
					}
				}
			});
		});
		// 강좌 - 과제 게시판은 pdf, hwp, doxc, ppt, txt만 가능
		function checkFile(f){
			// files 로 해당 파일 정보 얻기.
			var file = f.files;
			// file[0].name 은 파일명 입니다.
			// 정규식으로 확장자 체크
			if(!/\.(pdf|hwp|docx|ppt|txt)$/i.test(file[0].name)) Swal.fire('pdf, hwp, docx, ppt, txt 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);
			// 체크를 통과했다면 종료.
			else return;
			// 체크에 걸리면 선택된  내용 취소 처리를 해야함.
			// 파일선택 폼의 내용은 스크립트로 컨트롤 할 수 없습니다.
			// 그래서 그냥 새로 폼을 새로 써주는 방식으로 초기화 합니다.
			f.outerHTML = f.outerHTML;
		}
	</script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen}">
		<!-- Desktop sidebar -->
		<aside
			class="z-20 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block flex-shrink-0">
			<c:if test="${level eq 1}">
				<div id="adminSideNav"></div>
			</c:if>
			<c:if test="${level eq 2}">
				<div id="teacherSideNav"></div>
			</c:if>
			<c:if test="${level eq 3}">
				<div id="studentSideNav"></div>
			</c:if>
		</aside>
		<!-- Desktop HeaderNav -->
		<div class="flex flex-col flex-1 w-full">
			<c:if test="${level eq 1}">
				<div id="adminHeaderNav"></div>
			</c:if>
			<c:if test="${level eq 2}">
				<div id="teacherHeaderNav"></div>
			</c:if>
			<c:if test="${level eq 3}">
				<div id="studentHeaderNav"></div>
			</c:if>
			<main class="h-full pb-16 overflow-y-auto">
				<div class="container grid px-6 mx-auto">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">${loginUser}님의
						modifySubjectData</h2>
					<!-- CTA -->
					<a
						class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
						href="${pageContext.request.contextPath}/getSubjectDataListByPage?subjectNo=${subjectNo}">
						<div class="flex items-center">
							<svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
	                  <path
									d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
	                </svg>
							<span>Subject Data Management Page</span>
						</div> <span>강좌 자료 게시판 &RightArrow;</span>
					</a>
					<div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
							<form action="${pageContext.request.contextPath}/modifySubjectData"
								enctype="multipart/form-data" method="post"
								id="modifySubjectDataForm">
								<table class="w-full whitespace-no-wrap">
									<tr
										class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div>
													<p class="font-semibold">강좌 자료 제목</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div>
													<p
														class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
														<input type="text" id="subjectDataTitle"
															name="subjectBoardTitle"
															value="${dataMap.subjectDataTitle}"> 
														<input type="number" value="${subjectNo}" name="subjectNo" hidden="hidden">
														<input type="number" value="${dataMap.subjectDataNo}" name="subjectBoardNo" hidden="hidden">
													</p>
												</div>
											</div>
										</td>
									</tr>
									<tr
										class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div>
													<p class="font-semibold">작성자</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div>
													<p
														class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
														<input type="text" id="teacherName"
															value="${dataMap.teacherName}" readonly="readonly">
													</p>
												</div>
											</div>
										</td>
									</tr>
									<tr
										class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div>
													<p class="font-semibold">내용</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div>
													<p
														class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
														<textarea id="subjectDataContent"
															name="subjectBoardContent" class="form-control">${dataMap.subjectDataContent}</textarea>
													</p>
												</div>
											</div>
										</td>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">첨부 파일 추가</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div>
													<button
														class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
														type="button" id="addFileupload">파일 추가</button>
													<div id="fileSection"></div>
												</div>
											</div>
										</td>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div>
													<p class="font-semibold">첨부 파일 삭제</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div
													style="display: inline-block; margin: 0 5px; float: right;">
													<div class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">첨부파일</div>
													<c:if test="${fileCount > 0}">
														<c:forEach var="s" items="${subjectDataFile}">
															<div>
																<a class="px-1 py-0.8 text-xs font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
																	href="${pageContext.request.contextPath}/removeSubjectDataFileOne?subjectFileNo=${s.subjectFileNo}&subjectBoardNo=${s.subjectFileBoardNo}&subjectNo=${subjectNo}">삭제</a>${s.subjectFileOriginalName}
															</div>
														</c:forEach>
													</c:if>
												</div>
											</div>
										</td>
									</tr>
								</table>
								<br>
								<div style="display: inline;">
									<div style="float: inherit;"display:inline-block;"></div>
									<button class="px-10 py-4 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
										style="margin: auto; display: block;" type="button"
										id="modifySubjectDataBtn">과제 수정</button>
								</div>
							</form>
						</div>
						<br>
					</div>
				</div>
		</div>
		</main>
	</div>
	</div>
</body>
<script>
	$('#adminSideNav').load(
			'${pageContext.request.contextPath}/include/adminSideNav.jsp');
	$('#adminHeaderNav').load(
			'${pageContext.request.contextPath}/include/adminHeaderNav.jsp');
	$('#studentSideNav').load(
			'${pageContext.request.contextPath}/include/studentSideNav.jsp');
	$('#studentHeaderNav').load(
			'${pageContext.request.contextPath}/include/studentHeaderNav.jsp');
	$('#teacherSideNav').load(
			'${pageContext.request.contextPath}/include/teacherSideNav.jsp');
	$('#teacherHeaderNav').load(
			'${pageContext.request.contextPath}/include/teacherHeaderNav.jsp');
</script>
</html>
