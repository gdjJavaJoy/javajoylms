<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>studentOne</title>
<style>
img {
	display: block;
	margin: 0px auto;
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen}">
		<!-- sidebar -->
		<aside class="z-20 flex-shrink-0 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block">
			<div id="studentSideNav"></div>
		</aside>
		
		<div class="flex flex-col flex-1 w-full">
			<!-- header -->
			<div id="studentHeaderNav"></div>
			
			<main class="h-full pb-16 overflow-y-auto">
				<div class="container grid px-6 mx-auto">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						내 정보보기</h2>
					<!-- CTA -->
				<c:choose>
		            <c:when test="${student.memberPhotoName != null}">
		              <img
		                 class="object-cover w-350 h-350 rounded-full"
		                 src="${pageContext.request.contextPath}/file/memberPhoto/${student.memberPhotoName}"
		                 style="width:350px; height:350px;"
		                 alt=""
		                 loading="lazy"
		            />
		            </c:when>
		            <c:otherwise>
		             <img
		              	class="object-cover w-350 h-350 rounded-full"
		                src="https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjE3Nzg0fQ"
		                alt=""
		                loading="lazy"
		            />
		            </c:otherwise>
            	</c:choose>
					<!-- With avatar -->
					<h4
						class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
						${loginUser}님의 정보</h4>
					<div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
							<table class="w-full whitespace-no-wrap">
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">ID</p>
											</div>
										</div>
									</td>
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">${student.memberId}</p>
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">이름</p>
											</div>
										</div>
									</td>
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">${student.studentName}</p>
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">성별</p>
											</div>
										</div>
									</td>
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">${student.studentGender}</p>
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">연락처</p>
											</div>
										</div>
									</td>
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">${student.studentPhone}</p>
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">이메일</p>
											</div>
										</div>
									</td>
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">${student.studentEmail}</p>
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">최종학력</p>
											</div>
										</div>
									</td>
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">${student.studentEducation}</p>
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">주소</p>
											</div>
										</div>
									</td>
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">${student.studentAddress}</p>
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">상세주소</p>
											</div>
										</div>
									</td>
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">
													${student.studentDetailAddress}</p>
											</div>
										</div>
									</td>
								</tr>
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">등록일</p>
											</div>
										</div>
									</td>
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<!-- Avatar with inset shadow -->
											<div>
												<p class="font-semibold">${student.studentRegisterDate}</p>
											</div>
										</div>
									</td>
									<td>
										<a class= "px-3 py-1 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-md active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" href="${pageContext.request.contextPath}/modifyStudentOne?memberId=${student.memberId}">회원 수정</a> 
									</td>
									<td>
										<a class= "deletebtn px-3 py-1 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-red-600 border border-transparent rounded-md active:bg-red-600 hover:bg-red-700 focus:outline-none focus:shadow-outline-red">회원 탈퇴</a>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
$(document).ready(function(){
	// 삭제버튼 클릭시 발생하는 이벤트
	$(".deletebtn").on('click',function(){
		var memberId = $(this).data('value')
		Swal.fire({
		    title: '정말 삭제 하시겠습니까?'
		   ,text: "삭제후 다시 되돌릴 수 없습니다."
		   ,icon: 'warning'
		   ,showCancelButton: true
		   ,confirmButtonColor: '#3085d6'
		   ,cancelButtonColor: '#d33'
		   ,confirmButtonText: '삭제'
		   ,cancelButtonText: '취소'
		}).then((result) => {
			if (result.isConfirmed) {
				Swal.fire({
					 text: '완료 버튼을 누를시 로그인 페이지로 이동합니다.'
					,buttons: ['완료']
				})
			window.location.href = '${pageContext.request.contextPath}/deleteStudentOne?memberId=${student.memberId}';
				}
			});
		});
init();
function init(){
	$('#studentSideNav').load('${pageContext.request.contextPath}/include/studentSideNav.jsp');
	$('#studentHeaderNav').load('${pageContext.request.contextPath}/include/studentHeaderNav.jsp');
	}
});
</script>
</html>
