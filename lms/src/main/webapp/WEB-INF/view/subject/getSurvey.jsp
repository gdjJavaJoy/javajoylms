<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>getSurvey</title>
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
		<!-- Desktop sidebar -->
		<aside class="z-20 flex-shrink-0 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block">
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
						설문조사</h2>
					<img class="object-cover w-350 h-350 rounded-full"
						src="https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjE3Nzg0fQ"
						alt="" loading="lazy" />
					<!-- With avatar -->
					<h4
						class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
						${loginUser}님의 설문조사</h4>
					<div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
							<form action="${pageContext.request.contextPath}/getSurveyResult" method="post" id="surveyForm">
									<input type="hidden" class="font-semibold" value="${subjectNo}" name="subjectNo">
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
															<input type="hidden" readonly="readonly" class="font-semibold">${cnt.index+1}
															<input type="hidden" class="font-semibold" value="${s.surveyNo}" name="surveyResultList[${cnt.index}].surveyNo">
															<input type="hidden" class="font-semibold" value="${loginUser}" name="surveyResultList[${cnt.index}].memberId">
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
														 <input
								                            type="radio"
								                            class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
								                            name="surveyResultList[${cnt.index}].result"
								                            value="1">
													</div>
												</td>
												<td class="px-4 py-3">
													<div class="flex items-center text-sm">
														<!-- Avatar with inset shadow -->
														 <input
								                            type="radio"
								                            class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
								                            name="surveyResultList[${cnt.index}].result"
								                            value="2">
													</div>
												</td>
												<td class="px-4 py-3">
													<div class="flex items-center text-sm">
														<!-- Avatar with inset shadow -->
														 <input
								                            type="radio"
								                            class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
								                            name="surveyResultList[${cnt.index}].result"
								                            value="3">
													</div>
												</td>
												<td class="px-4 py-3">
													<div class="flex items-center text-sm">
														<!-- Avatar with inset shadow -->
														 <input
								                            type="radio"
								                            class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
								                            name="surveyResultList[${cnt.index}].result"
								                            value="4">
													</div>
												</td>
												<td class="px-4 py-3">
													<div class="flex items-center text-sm">
														<!-- Avatar with inset shadow -->
														 <input
								                            type="radio"
								                            class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
								                            name="surveyResultList[${cnt.index}].result"
								                            value="5">
													</div>
												</td>
											</tr>
											</tbody>
									</c:forEach>
									</table>
								<button type="submit"
									class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
									id="submitBtn">설문조사 제출</button>
							</form>	
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
<script>
	<!-- val()를 못 받아옴 
	$('#submitBtn').click(function() {
		<c:forEach var="s" items="${surveyList}" varStatus="cnt">
			console.log( $('input[name=surveyResultList[${cnt.index}].result]:radio:checked').val() );
			if ($('input[ name=surveyResultList[${cnt.index}].result ]:radio:checked').val() == null) {
				Swal.fire("만족도 검사를 선택하세요");
				return;
			}
		</c:forEach>
		else {
			$('#surveyForm').submit();
		}
	});
	-->
</script>
</html>
