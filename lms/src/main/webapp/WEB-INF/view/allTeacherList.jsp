<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Teacher List Page</title>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />
	<link rel="stylesheet" href="./public/assets/css/tailwind.output.css" />
	<script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
	<script src="./public/assets/js/init-alpine.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900" :class="{ 'overflow-hidden': isSideMenuOpen}">
<!-- Desktop sidebar -->
	<aside class="z-20 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block flex-shrink-0">
			<div id="adminSideNav"></div>
	</aside>
	      <!-- Backdrop -->
      <div
        x-show="isSideMenuOpen"
        x-transition:enter="transition ease-in-out duration-150"
        x-transition:enter-start="opacity-0"
        x-transition:enter-end="opacity-100"
        x-transition:leave="transition ease-in-out duration-150"
        x-transition:leave-start="opacity-100"
        x-transition:leave-end="opacity-0"
        class="fixed inset-0 z-10 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center">
      </div>
	<!-- Desktop HeaderNav -->
      <div class="flex flex-col flex-1 w-full">
			<div id="adminHeaderNav"></div>
        
        <main class="h-full pb-16 overflow-y-auto">
          <div class="container grid px-8 mx-auto">
            <h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">Teacher List</h2>
            <!-- CTA -->
            <a class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
              href="${pageContext.request.contextPath}/addMember">
              <div class="flex items-center">
                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                </svg>
                <span>Teacher Management Page</span>
              </div>
              <span>회원 등록 &RightArrow;</span>
            </a>

            <!-- With avatar -->
            <h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">모든 강사 목록</h4>
            <div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto">
                <table class="w-full whitespace-no-wrap">
                  <thead>
                    <tr class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                      <th class="px-4 py-3">이름</th>
                  	  <th class="px-4 py-3">전화번호</th>
                      <th class="px-4 py-3">성별</th>
                      <th class="px-4 py-3">주소</th>
                      <th class="px-4 py-3">상세주소</th>
                      <th class="px-4 py-3">관리자 이메일</th>
                      <th class="px-4 py-3">입사일</th>
                    </tr>
                  </thead>
                  <tbody class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
					<c:forEach var="s" items="${list}">
						<tr class="text-gray-700 dark:text-gray-400" align="center">										
							<td class="px-4 py-3 text-sm">${s.teacherName}</td>
							<td class="px-4 py-3 text-sm">${s.teacherPhone}</td>
							<td class="px-4 py-3 text-sm">${s.teacherGender}</td>
							<td class="px-4 py-3 text-sm">${s.teacherAddress}</td>
							<td class="px-4 py-3 text-sm">${s.teacherDetailAddress}</td>
							<td class="px-4 py-3 text-sm">${s.teacherEmail}</td>
							<td class="px-4 py-3 text-sm">${s.teacherJoin}</td>
							<td><form method="get" name="form">
								<input type="hidden" value="${s.memberId}" name="memberId">
								<input type="submit" value="수정" onclick="javascript: form.action='${pageContext.request.contextPath}/modifyTeacherOne'"/>
								<input type="submit" value="삭제" onclick="javascript: form.action='${pageContext.request.contextPath}/deleteTeacher'"/>
								</form>
							</td>
						</tr>
					</c:forEach>
                  </tbody>
                </table>
              </div>
              <div class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-12 dark:text-gray-400 dark:bg-gray-800">
                <form method="get" action="${pageContext.request.contextPath}/allTeacherList" name="search">
	                <span class="flex items-center col-span-3">
	                  Search Teacher : 
	                  <input name="SearchTeacherName" class="form-control" type="text"  placeholder=" 강사 이름 검색">
	                  <button type="submit" class="grid px-4 py-3 text-sm">검색</button>
	                </span>
                </form>
                <span class="col-span-2"></span>
                <!-- Pagination -->
                <span class="flex col-span-4 mt-2 sm:mt-auto sm:justify-end">
                	<c:if test="${currentPage > 1}">
                   		<a class="btn btn-primary" href="${pageContext.request.contextPath}/allTeachertList?currentPage=${currentPage-1}">이전</a>
                   	</c:if>
                   		<span>&nbsp;  &nbsp;</span>
                   	<c:if test="${currentPage < lastPage}">	
                  		 <a class="btn btn-primary" href="${pageContext.request.contextPath}/allTeacherList?currentPage=${currentPage+1}">다음</a> 
                  	</c:if>	 
                </span>
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
