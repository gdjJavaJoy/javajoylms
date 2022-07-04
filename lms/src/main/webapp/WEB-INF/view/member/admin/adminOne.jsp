<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>adminOne</title>
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
			<div id="adminSideNav"></div>
		</aside>
		
		<div class="flex flex-col flex-1 w-full">
			<!-- header -->
			<div id="adminHeaderNav"></div>
			
			<main class="h-full pb-16 overflow-y-auto">
				<div class="container grid px-6 mx-auto">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						내 정보보기</h2>
					<!-- CTA -->
					<a
						class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
						href="${pageContext.request.contextPath}/modifyAdminOne?memberId=${admin.memberId}"> <!-- 회원정보 수정창으로 -->
						<div class="flex items-center">
							<span></span>
						</div> <span>회원정보 수정 &RightArrow;</span>
					</a>
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
												<p class="font-semibold">${admin.memberId}</p>
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
												<p class="font-semibold">${admin.adminName}</p>
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
												<p class="font-semibold">${admin.adminPhone}</p>
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
												<p class="font-semibold">${admin.adminEmail}</p>
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
												<p class="font-semibold">${admin.adminAddress}</p>
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
													${admin.adminDetailAddress}</p>
											</div>
										</div>
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
<script>
	$('#adminSideNav').load('${pageContext.request.contextPath}/include/adminSideNav.jsp');
	$('#admintHeaderNav').load('${pageContext.request.contextPath}/include/adminHeaderNav.jsp');
</script>
</html>
