<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>memberIndex</title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="./public/assets/css/tailwind.output.css" />
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="./public/assets/js/init-alpine.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"
	defer></script>
<script src="./public/assets/js/charts-lines.js" defer></script>
<script src="./public/assets/js/charts-pie.js" defer></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen }">
		<!-- Desktop sidebar -->
		<aside
		class="z-20 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block flex-shrink-0">
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
						공지사항</h2>
					<!-- New Table -->
					<div class="w-full overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
								<table class="w-full whitespace-no-wrap">
									<thead>
									<c:if test="${level eq 2}">
										<tr
											class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
											<th class="px-4 py-3">subjectName</th>
											<th class="px-4 py-3">teacherId</th>
											<th class="px-4 py-3">curriculumTitle</th>
											<th class="px-4 py-3">startDay</th>
											<th class="px-4 py-3">EndDay</th>
										</tr>
									</c:if>
									<c:if test="${level eq 3}">
										<tr
											class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
											<th class="px-4 py-3">subjectName</th>
											<th class="px-4 py-3">teacherId(담임강사)</th>
											<th class="px-4 py-3">subjectStartDate</th>
											<th class="px-4 py-3">subjectFinishDate</th>
										</tr>
									</c:if>
									</thead>
									<c:forEach var="m" items="${memberList}">
										<tbody
											class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
											<c:if test="${level eq 2}">
											<tr onClick="location.href='${pageContext.request.contextPath}/getSubjectOne?subjectNo=${m.subjectNo}'" style="cursor:pointer;" class="text-gray-700 dark:text-gray-400">
												<td class="px-4 py-3 text-sm">${m.subjectName}</td>
												<td class="px-4 py-3">
													<div class="flex items-center text-sm">
														<!-- Avatar with inset shadow -->
														<p class="font-semibold">${m.teacherId}</p>
													</div>
												</td>
												<td class="px-4 py-3 text-sm">${m.curriculumTitle}</td>
												<td class="px-4 py-3 text-sm">${m.startDay}</td>
												<td class="px-4 py-3 text-sm">${m.endDay}</td>
											</tr>
											</c:if>
											<c:if test="${level eq 3}">
												<tr onClick="location.href='${pageContext.request.contextPath}/getSubjectOne?subjectNo=${m.subjectNo}'" style="cursor:pointer;" class="text-gray-700 dark:text-gray-400">
													<td class="px-4 py-3 text-sm">${m.subjectName}</td>
													<td class="px-4 py-3">
														<div class="flex items-center text-sm">
															<!-- Avatar with inset shadow -->
															<p class="font-semibold">${m.teacherId}</p>
														</div>
													</td>
													<td class="px-4 py-3 text-sm">${m.subjectStartDate}</td>
													<td class="px-4 py-3 text-sm">${m.subjectFinishDate}</td>
												</tr>
											</c:if>
										</tbody>
									</c:forEach>
								</table>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
<script>
	$('#studentSideNav').load('${pageContext.request.contextPath}/include/studentSideNav.jsp');
	$('#studentHeaderNav').load('${pageContext.request.contextPath}/include/studentHeaderNav.jsp');
	$('#teacherSideNav').load('${pageContext.request.contextPath}/include/teacherSideNav.jsp');
	$('#teacherHeaderNav').load('${pageContext.request.contextPath}/include/teacherHeaderNav.jsp');
</script>
</html>
