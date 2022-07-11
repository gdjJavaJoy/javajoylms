<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>getSubjectVideo</title>
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
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						영상 자료</h2>
					<c:if test="${level eq 1}">
						<a
							href="${pageContext.request.contextPath}/addSubjectVideo?subjectNo=${subjectNo}">등록 </a>
					</c:if>
					<a
						class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
						href="${pageContext.request.contextPath}/getSubjectOne?subjectNo=${subjectNo}">
						<div class="flex items-center">
							<span></span>
						</div> <span>강좌 &RightArrow;</span>
					</a>

					<c:forEach var="v" items="${subjectVideoList}" varStatus="cnt">
						<div class="w-full overflow-x-auto">
							<h4
								class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
								${v.subjectVideoTitle}</h4>
							<iframe width="600" height="400" src="${v.subjectVideoUrl}"
								allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
								allowfullscreen> </iframe>
							<div>${v.subjectVideoContent}</div>
							<c:if test="${level eq 1}">
								<a
									href="${pageContext.request.contextPath}/modifySubjectVideo?subjectVideoNo=${v.subjectVideoNo}">수정</a>
								<a
									href="${pageContext.request.contextPath}/removeSubjectVideo?subjectVideoNo=${v.subjectVideoNo}&subjectNo=${subjectVideoList[0].subjectNo}">삭제</a>
							</c:if>
						</div>
						<br>
						<br>
						<br>
					</c:forEach>
				</div>
			</main>
		</div>
	</div>
</body>
<script>
	$('#adminSideNav').load('${pageContext.request.contextPath}/include/adminSideNav.jsp');
	$('#adminHeaderNav').load('${pageContext.request.contextPath}/include/adminHeaderNav.jsp');
	$('#studentSideNav').load('${pageContext.request.contextPath}/include/studentSideNav.jsp');
	$('#studentHeaderNav').load('${pageContext.request.contextPath}/include/studentHeaderNav.jsp');
	$('#teacherSideNav').load('${pageContext.request.contextPath}/include/teacherSideNav.jsp');
	$('#teacherHeaderNav').load('${pageContext.request.contextPath}/include/teacherHeaderNav.jsp');
</script>
</html>
