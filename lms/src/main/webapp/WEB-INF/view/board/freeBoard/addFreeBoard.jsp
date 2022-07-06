<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>건의함</title>
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
<script>
$(document).ready(function(){
	$('#adminSideNav').load('${pageContext.request.contextPath}/include/adminSideNav.jsp');
	$('#adminHeaderNav').load('${pageContext.request.contextPath}/include/adminHeaderNav.jsp');
	$('#studentSideNav').load('${pageContext.request.contextPath}/include/studentSideNav.jsp');
	$('#studentHeaderNav').load('${pageContext.request.contextPath}/include/studentHeaderNav.jsp');
	$('#teacherSideNav').load('${pageContext.request.contextPath}/include/teacherSideNav.jsp');
	$('#teacherHeaderNav').load('${pageContext.request.contextPath}/include/teacherHeaderNav.jsp');
	
$('#addFileUpload').click(function(){
	var flag = true;
	
	$('.FreeBoardFileList').each(function(){ // each함수를 이용한 반복
		if($(this).val() == '') {
			flag = false;
		}
	});
	
	if(flag) {
		$('#fileSection').append('<div><input class="FreeBoardFileList" type="file" name="boardfileList"></div>');
	} else {
		alert('파일이 첨부되지 않은 리스트가 존재합니다');
		}
	});

	$('#addFreeBoardBtn').click(function(){
		 if ($('#boardTitle').val() == '') {
					alert('제목을 작성해주세요');
					return;
			} else if ($('#boardContent').val() == '') {
					alert('내용을 입력해주세요');
					return;
			}  else  {
				$('#form').submit();
			}
	});
});
</script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen }">
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
		<!-- Backdrop -->
		<div x-show="isSideMenuOpen"
			x-transition:enter="transition ease-in-out duration-150"
			x-transition:enter-start="opacity-0"
			x-transition:enter-end="opacity-100"
			x-transition:leave="transition ease-in-out duration-150"
			x-transition:leave-start="opacity-100"
			x-transition:leave-end="opacity-0"
			class="fixed inset-0 z-10 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"></div>
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
			<main class="h-full overflow-y-auto">
				<div class="container px-6 mx-auto grid">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						자유게시판 글 작성</h2>
						<div>
			            </div>
					<!-- New Table -->
					<div class="w-full overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
							<form method="post" action="${pageContext.request.contextPath}/addFreeBoard" id="form" enctype="multipart/form-data">
								<table class="w-full whitespace-no-wrap">
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3 text-sm">제목</td>
										<td class="px-4 py-3">
											<input
											class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
											placeholder="제목입력"
											name="boardTitle"
											id="boardTitle"
											/>
											<input type="text" name="memberId" value="${loginUser}" hidden="hidden">
										</td>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3 text-sm">내용</td>
										<td class="px-4 py-3">
											 <textarea
							                  class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-textarea focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
							                  rows="3"
							                  placeholder="내용을 입력해주세요"
							                  name="boardContent"
							                  id="boardContent"
							                ></textarea>
										</td>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3 text-sm">익명설정</td>
										<td class="px-4 py-3">
										 <input
											type="radio"
											class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
											name="privateNo"
											value="1"
											checked="checked"
											/>
											익명X
											<input
											type="radio"
											class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
											name="privateNo"
											value="2"
											/>
											익명O
										</td>
									</tr>
									<tr>
										<td class="px-4 py-3 text-sm">
										 <button
							                  class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
							                  id="addFileUpload"
							                  type="button"
							                >
							                 파일업로드 추가
					                </button>
										</td>
										<td class="px-4 py-3">
											<div  id="fileSection">
											
											</div>
										</td>
									</tr>
								</table>
								</form>
									<br>
								   <div>
					                <button
					                  class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
					                  id="addFreeBoardBtn"
					                  type="button"
					                >
					               	글작성
					                </button>
					              </div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
</html>
