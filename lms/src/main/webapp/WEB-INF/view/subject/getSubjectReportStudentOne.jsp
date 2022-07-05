<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Subject Report One</title>
    <style>
			img { display: block; margin: 0px auto; }
			textarea{
				width:500px; 
				height:100px; 
			    resize:none;
			    /* 크기고정 */ 
				/*   resize: horizontal; // 가로크기만 조절가능 
				resize: vertical;  세로크기만 조절가능  */
   			}
		</style>
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
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script> 
 	<script>
		$(document).ready(function(){
			let flag = true;
			$('#addSubjectReportComment').click(function(){
				if($('#subjectBoardNo').val() == '') {
					Swal.fire('test');
			    } else if($('#memberId').val() == '') {
			    	Swal.fire('test');
				} else if($('#subjectReportCommentContent').val() == '') {
					Swal.fire('댓글 내용을 입력해주세요');
				} else {
					if(flag) {
						$('#addSubjectReportCommentForm').submit();
						Swal.fire('댓글 작성 성공!');
					}
				}
				
			});
		});
	</script>
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
          	
            <h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">${loginUser}님의 Subject Report One</h2>
            <!-- CTA -->
            <c:forEach var="subjectReportStudent" items="${subjectReportStudent}">
	            <a class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
	              href="${pageContext.request.contextPath}/getSubjectReportStudentListByPage?subjectReportNo=${subjectReportStudent.subjectReportNo}">
	              <div class="flex items-center">
	                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
	                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
	                </svg>
	                <span>Subject Report Management Page</span>
	              </div>
	              <span>${subjectReportStudent.subjectReportNo}번 과제 제출 리스트 &RightArrow;</span>
	            </a>
            
            <!-- With avatar -->
           		<h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">${subjectReportStudent.memberId}의 과제 상세보기
           		</h4> 
           	</c:forEach>
            <!-- 과제 상세보기 테이블 -->
            <div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto">
              	 <c:forEach var="subjectReportStudent" items="${subjectReportStudent}">
               		<table class="w-full whitespace-no-wrap">
               			 <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">학생 과제 번호</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReportStudent.subjectReportStudentNo}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 번호</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReportStudent.subjectReportNo}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">작성자 ID</th>
							<td class="px-4 py-3 text-sm">${subjectReportStudent.memberId}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 제목</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReportStudent.subjectReportStudentTitle}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 내용</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReportStudent.subjectReportStudentContent}</td>
	                    </tr>
	                     <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 점수</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReportStudent.score}</td>
	                    </tr>
	                     <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">status</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReportStudent.status}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 생성 날짜</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReportStudent.createDate}</td>
	                    </tr>
              		</table> 
	              	<div class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-9 dark:text-gray-400 dark:bg-gray-800">
		            <span class="flex items-center col-span-3">
		             	
		            </span>
		            <span class="col-span-2"></span>
		            	<!-- Pagination -->
	                <span class="flex col-span-4 mt-2 sm:mt-auto sm:justify-end">
	                <c:if test="${level eq 1 || level eq 2}">
	              		<a href="${pageContext.request.contextPath}/modifySubjectReportStudent?subjectReportStudentNo=${subjectReportStudent.subjectReportStudentNo}">과제 채점</a>
	              		<span>&nbsp | &nbsp</span>
	                    <a href="${pageContext.request.contextPath}/removeSubjectReport?subjectReportStudentNo=${subjectReportStudent.subjectReportStudentNo}">과제 삭제</a> 
	                </c:if>
	                </span>
              	  </div>
              	 </c:forEach>
              </div>
            </div>
            
            <div>
             <!-- 첨부 파일 -->
             <div style="display: inline-block; margin: 0 5px;  float: right;">
              	<div class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">첨부 파일</div>
	              	<c:choose>
		              	<c:when test="${studentFileList.size() < 0}">
					    	<div>파일없음</div>
					    </c:when>
					    <c:when test="${studentFileList.size() >= 0}">
					    	<c:forEach var="studentFileList" items="${studentFileList}">
					    		<div>
					    			<a href="${pageContext.request.contextPath}/file/studentFile/${studentFileList.studentFileName}" download="${studentFileList.studentFileOriginalName}">${studentFileList.studentFileOriginalName}</a>
					    		</div>
					    	</c:forEach>
					    </c:when>
				    </c:choose>
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
		$('#studentSideNav').load('${pageContext.request.contextPath}/include/studentSideNav.jsp');
		$('#studentHeaderNav').load('${pageContext.request.contextPath}/include/studentHeaderNav.jsp');
		$('#teacherSideNav').load('${pageContext.request.contextPath}/include/teacherSideNav.jsp');
		$('#teacherHeaderNav').load('${pageContext.request.contextPath}/include/teacherHeaderNav.jsp');
	</script>
</html>
            	