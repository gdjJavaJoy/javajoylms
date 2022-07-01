<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Subject One</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="./public/assets/css/tailwind.output.css" />
    
    <script
      src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
      defer
    ></script>
    <script src="./public/assets/js/init-alpine.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  </head>
  <body>
    <div
      class="flex h-screen bg-gray-50 dark:bg-gray-900"
      :class="{ 'overflow-hidden': isSideMenuOpen}"
    >
	<!-- Desktop sidebar -->
	<aside class="z-20 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block flex-shrink-0">
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
            <h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">${loginUser}님의 Subject One</h2>
		     <!-- CTA -->
	             <a class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple" href="${pageContext.request.contextPath}/getSubjectByPage">
	              <div class="flex items-center">
	                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
	                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
	                </svg>
	                <span>Subject Management Page ${surveyChk}</span>
	              </div>
	              <span>강좌 목록 &RightArrow;</span>
	            </a>
           	<!-- 카테고리 -->
           	<nav class="navbar navbar-default navbar-static-top">
           	  <div class="container">
					<ol class="breadcrumb" style="float:rigth;">
						<li>
							<a style="float:right;" href="${pageContext.request.contextPath}/subjectDataList?subjectNo=${subject.subjectNo}">&nbsp&nbsp강좌 자료(미구현)&nbsp&nbsp</a>
						</li>
						<li >
							<a style="float:right;" href="${pageContext.request.contextPath}/subjectNoticeList?subjectNo=${subject.subjectNo}">&nbsp&nbsp강좌 공지사항(미구현)&nbsp&nbsp</a>
						</li>
						<li>
							<a style="float:right;" href="${pageContext.request.contextPath}/curriculumList?subjectNo=${subject.subjectNo}">&nbsp&nbsp커리큘럼(미구현)&nbsp&nbsp</a>
						</li>
						<c:if test="${surveyChk < 1}"> <!--  설문조사를 하지 않은사람만 보이게 -->
							<li>
								<a style="float:right;" href="${pageContext.request.contextPath}/getSurvey?subjectNo=${subject.subjectNo}">&nbsp&nbsp설문 조사&nbsp&nbsp</a>
							</li>
						</c:if>
						<li>
							<a style="float:right;" href="${pageContext.request.contextPath}/getSubjectVideo?subjectNo=${subject.subjectNo}">&nbsp&nbsp강좌 영상&nbsp&nbsp</a>
						</li>
						<li>
							<a style="float:right;" href="${pageContext.request.contextPath}/getSubjectReportListByPage?subjectNo=${subject.subjectNo}&subjectName=${subject.subjectName}">&nbsp&nbsp과제 게시판&nbsp&nbsp</a>
						</li>
						<!-- 운영자 + 강사 전용 페이지 -->
						<c:if test="${level eq 1 || level eq 2}">
							<li>
								<a style="float:right;" href="${pageContext.request.contextPath}/getSubjectStudentList?subjectNo=${subject.subjectNo}">&nbsp&nbsp수강 학생&nbsp&nbsp</a>
							</li>
						</c:if>
					</ol>
				</div>
			</nav>
            <!-- With avatar -->
            <h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">${subject.subjectName} 강좌 상세보기</h4>
            <div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto">
                <table class="w-full whitespace-no-wrap">
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">강좌 번호</th>
                    	<td class="px-4 py-3 text-sm">${subject.subjectNo}</td>
                    </tr>
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">담당 강사 ID</th>
                    	<td class="px-4 py-3 text-sm">${subject.teacherId}</td>
                    </tr>
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">담당 강사 이름</th>
                    	<td class="px-4 py-3 text-sm">${subject.teacherName}</td>
                    </tr>
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">개설 운영자</th>
                    	<td class="px-4 py-3 text-sm">${subject.adminId}</td>
                    </tr>
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">강좌 이름</th>
                    	<td class="px-4 py-3 text-sm">${subject.subjectName}</td>
                    </tr>
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">총원</th>
                    	<td class="px-4 py-3 text-sm">${subject.subjectStudentMax}</td>
                    </tr>
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">강좌 시작시간</th>
                    	<td class="px-4 py-3 text-sm">${subject.subjectStartTime}</td>
                    </tr>
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">강좌 마감시간</th>
                    	<td class="px-4 py-3 text-sm">${subject.subjectEndTime}</td>
                    </tr>
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">강좌 시작일</th>
                    	<td class="px-4 py-3 text-sm">${subject.subjectStartDate}</td>
                    </tr>
                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">강좌 수료일</th>
                    	<td class="px-4 py-3 text-sm">${subject.subjectFinishDate}</td>
                    </tr>
                     <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
                    	<th class="px-4 py-3">강좌 설명</th>
                    	<td class="px-4 py-3 text-sm">${subject.subjectInfo}</td>
                    </tr>
                 </table>  
              </div>
              <div
                class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-9 dark:text-gray-400 dark:bg-gray-800"
              >
                <span class="flex items-center col-span-3">
                  <!-- 공백 -->
                </span>
                <span class="col-span-2"></span>
                <!-- Pagination -->
                <span class="flex col-span-4 mt-2 sm:mt-auto sm:justify-end">
                <!-- 운영자만 삭제, 수정 가능 -->
                <c:if test="${level eq 1}">
              		<a href="${pageContext.request.contextPath}/modifySubject?subjectNo=${subject.subjectNo}">강좌 수정</a>
              			<span>&nbsp | &nbsp</span>
                    <a href="${pageContext.request.contextPath}/deleteSubject?subjectNo=${subject.subjectNo}">강좌 삭제(미구현)</a> 
                </c:if>
                </span>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
    <script>
		$('#adminSideNav').load('${pageContext.request.contextPath}/include/adminSideNav.jsp');
		$('#adminHeaderNav').load('${pageContext.request.contextPath}/include/adminHeaderNav.jsp');
		$('#studentSideNav').load('${pageContext.request.contextPath}/include/studentSideNav.jsp');
		$('#studentHeaderNav').load('${pageContext.request.contextPath}/include/studentHeaderNav.jsp');
		$('#teacherSideNav').load('${pageContext.request.contextPath}/include/teacherSideNav.jsp');
		$('#teacherHeaderNav').load('${pageContext.request.contextPath}/include/teacherHeaderNav.jsp');
		</script>
  </body>
</html>
