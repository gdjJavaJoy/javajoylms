<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>자유게시판</title>
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
</head>
<body>
<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen }">
		<!-- Desktop sidebar -->
		<aside
		class="z-20 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block flex-shrink-0">
				<div id="adminSideNav"></div>
		</aside>
		<!-- Backdrop -->
		<div class="flex flex-col flex-1 w-full">
				<div id="adminHeaderNav"></div>
			<main class="h-full overflow-y-auto">
				<div class="container px-6 mx-auto grid">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						교부재</h2>
						<div>
						<button
			                  class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
			               	   onclick="location.href='${pageContext.request.contextPath}/addBook'"
			               		 >
			                  책 추가 
			                </button>
			                <br>
			            </div>
					<!-- New Table -->
					<div class="w-full overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
								<table class="w-full whitespace-no-wrap">
									<thead>
										<tr
											class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
											<th class="px-4 py-3">번호</th>
											<th class="px-4 py-3">책 제목</th>
											<th class="px-4 py-3">글쓴이</th>
											<th class="px-4 py-3">출판사</th>
											<th class="px-4 py-3">수정</th>
										</tr>
									</thead>
									<!-- 글번호 1부터 매기는 부분  --> 
									<c:set var="num" value="${totalCount-((currentPage-1)*rowPerPage)}"></c:set>
										<tbody
											class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
										<c:forEach var="l" items="${list}" >
											<tr class="text-gray-700 dark:text-gray-400">
													<td class="px-4 py-3 text-sm">${num}</td>
													<td class="px-4 py-3">
														<div class="flex items-center text-sm">
																<p class="font-semibold">${l.bookTitle}</p>
														</div>
													</td>
													<td class="px-4 py-3">
													${l.bookWriter}
													</td>
													<td class="px-4 py-3 text-sm">${l.bookCompany}</td>
													<td class="px-4 py-3 text-sm">
														<a href="${pageContext.request.contextPath}/modifyBook?bookNo=${l.bookNo}">수정</a>
													</td>
												</tr>
												<c:set var="num" value="${num-1}"></c:set>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
					     <div
                class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-12 dark:text-gray-400 dark:bg-gray-800">
              <form method="get" action="${pageContext.request.contextPath}/getBookListByPage">
                <span class="flex items-center col-span-3">
                  <input name="searchBookTitle" class="form-control" type="text"  placeholder="제목 검색">
                	<button type="submit" class="grid px-4 py-3 text-sm">검색</button>
                	<a href="${pageContext.request.contextPath}/getBookListByPage">초기화</a>
                </span>
                </form>
                <span class="col-span-2"></span>
                <!-- Pagination -->
                <span class="flex col-span-4 mt-2 sm:mt-auto sm:justify-end">
               	<c:if test="${currentPage > 1}">
               	<c:choose>
               	<c:when test="${searchBookTitle eq null}">
               <a href="${pageContext.request.contextPath}/getBookListByPage?currentPage=${currentPage-1}">이전</a>
               </c:when>
               <c:otherwise>
               <a href="${pageContext.request.contextPath}/getBookListByPage?currentPage=${currentPage-1}&searchBookTitle=${searchBookTitle}">이전</a>
               </c:otherwise>
               </c:choose>
       			</c:if>
                <span>&nbsp  &nbsp</span>
                <c:if test="${currentPage  < lastPage}">
                <c:choose>
                <c:when test="${searchInquiryTitle eq null}">
               <a  href="${pageContext.request.contextPath}/getBookListByPage?currentPage=${currentPage+1}">다음</a>
               </c:when>
               <c:otherwise>
                  <a href="${pageContext.request.contextPath}/getBookListByPage?currentPage=${currentPage+1}&searchBookTitle=${searchBookTitle}">다음</a>
               </c:otherwise>
               </c:choose>
      		  </c:if>
                </span>
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
