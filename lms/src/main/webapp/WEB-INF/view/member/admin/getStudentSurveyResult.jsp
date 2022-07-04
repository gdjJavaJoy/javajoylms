<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>getStudentSurveyResult</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="./public/assets/js/init-alpine.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
						설문조사 결과</h2>
					<h4
						class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
						설문조사 결과</h4>
					<div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
							<input type="hidden" class="font-semibold" value="${subjectNo}"
								name="subjectNo">
							<table class="w-full whitespace-no-wrap">
								<thead>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">번호</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">질문</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">매우 좋음</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">좋음</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">보통</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">안좋음</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">매우 안좋음</p>
												</div>
											</div>
										</td>
									</tr>
								</thead>
								<c:forEach var="s" items="${surveyList}" varStatus="cnt">
									<tbody>
										<tr class="text-gray-700 dark:text-gray-400">
											<td class="px-4 py-3">
												<div class="flex items-center text-sm">
													<!-- Avatar with inset shadow -->
													<div>
														<input type="hidden" readonly="readonly"
															class="font-semibold">${cnt.index+1}
													</div>
												</div>
											</td>
											<td class="px-4 py-3">
												<div class="flex items-center text-sm">
													<!-- Avatar with inset shadow -->
													<div>
														<p class="font-semibold">${s.surveyQuestion}</p>
													</div>
												</div>
											</td>
											<td class="px-4 py-3">
												<div class="flex items-center text-sm">
													<!-- Avatar with inset shadow -->
													<div>
														<p class="font-semibold">
															<label
																class="inline-flex items-center text-gray-600 dark:text-gray-400">
																<input type="radio"
																class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
																onclick="return(false);"
																<c:if test="${s.result == '1'}">checked</c:if> />
															</label>
														</p>
													</div>
												</div>
											</td>
											<td class="px-4 py-3">
												<div class="flex items-center text-sm">
													<!-- Avatar with inset shadow -->
													<div>
														<p class="font-semibold">
															<label
																class="inline-flex items-center text-gray-600 dark:text-gray-400">
																<input type="radio"
																class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
																onclick="return(false);"
																<c:if test="${s.result == '2'}">checked</c:if> />
															</label>
														</p>
													</div>
												</div>
											</td>
											<td class="px-4 py-3">
												<div class="flex items-center text-sm">
													<!-- Avatar with inset shadow -->
													<div>
														<p class="font-semibold">
															<label
																class="inline-flex items-center text-gray-600 dark:text-gray-400">
																<input type="radio"
																class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
																onclick="return(false);"
																<c:if test="${s.result == '3'}">checked</c:if> />
															</label>
														</p>
													</div>
												</div>
											</td>
											<td class="px-4 py-3">
												<div class="flex items-center text-sm">
													<!-- Avatar with inset shadow -->
													<div>
														<p class="font-semibold">
															<label
																class="inline-flex items-center text-gray-600 dark:text-gray-400">
																<input type="radio"
																class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
																onclick="return(false);"
																<c:if test="${s.result == '4'}">checked</c:if> />
															</label>
														</p>
													</div>
												</div>
											</td>
											<td class="px-4 py-3">
												<div class="flex items-center text-sm">
													<!-- Avatar with inset shadow -->
													<div>
														<p class="font-semibold">
															<label
																class="inline-flex items-center text-gray-600 dark:text-gray-400">
																<input type="radio"
																class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
																onclick="return(false);"
																<c:if test="${s.result == '5'}">checked</c:if> />
															</label>
														</p>
													</div>
												</div>
											</td>
										</tr>
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
	$('#adminSideNav').load('${pageContext.request.contextPath}/include/adminSideNav.jsp');
	$('#adminHeaderNav').load('${pageContext.request.contextPath}/include/adminHeaderNav.jsp');
</script>
</html>
