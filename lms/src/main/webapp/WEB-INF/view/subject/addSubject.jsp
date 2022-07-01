<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Insert Subject Page</title>
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
<script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>

<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
<link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="./public/assets/css/tailwind.output.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"  defer></script>
    <script src="./public/assets/js/init-alpine.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script> 
    <!-- 강좌 입력 유효성 검사 -->
    <script>
		$(document).ready(function(){
			let flag = true;
			$('#signup').click(function(){
				if($('#teacherId').val() == '') {
					Swal.fire('담임 강사를 입력하세요');
			    } else if($('#adminId').val() == '') {
			    	Swal.fire('개설 운영자를 입력하세요');
			    } else if($('#subjectName').val() == '') {
			    	Swal.fire('강좌 이름을 입력하세요');
				} else if($('#subjectStudentMax').val() == '') {
					Swal.fire('강좌 총원을 입력하세요!');
				} else if($('#subjectStartDate').val() == '') {
					Swal.fire('강좌 개강일을 입력하세요!');
				} else if($('#subjectFinishDate').val() == '') {
					Swal.fire('강좌 종강일을 입력하세요!');
				} else if($('#subjectStartTime').val() == '') {
					Swal.fire('강좌 시작 시간을 입력하세요!');
				} else if($('#subjectEndTime').val() == '') {
					Swal.fire('강좌 마감 시간을 입력하세요!');
				} else if($('#subjectInfo').val() == '') {
					Swal.fire('강좌 설명을 작성하세요!');
				} else {
					$('#addSubjectForm').submit();
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
            <h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">${loginUser}님의 Subject Insert</h2>
            <!-- CTA -->
            <a class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
              href="${pageContext.request.contextPath}/getSubjectByPage">
              <div class="flex items-center">
                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                </svg>
                <span>Subject Management Page</span>
              </div>
              <span>강좌 목록 &RightArrow;</span>
            </a>
       
            <!-- With avatar -->
            <h4
              class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
            >
              운영자 강좌 추가
            </h4>
            <div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto">
             	<form id="addSubjectForm" method="post" name="addSujectForm" action="${pageContext.request.contextPath}/addSubject">
                	<table class="w-full whitespace-no-wrap">
	                	<tr class="text-gray-700 dark:text-gray-400">
	                      <td class="px-4 py-3">
	                        <div class="flex items-center text-sm">
	                          <!-- Avatar with inset shadow -->
	                          <div>
	                            <p class="font-semibold">담임 강사</p>
	                          </div>
	                        </div>
	                      </td>
	                       <td class="px-4 py-3">
	                        <div class="flex items-center text-sm">
	                          <!-- Avatar with inset shadow -->
	                          <div>
			    				<select id="teacherId" name="teacherId" 
			    						class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-select" >
			    					<option value="">강사 선택</option>
				    			     	<c:forEach var="c" items="${teacherList}">
				    				  		<option value="${c}">${c}</option>
				    			        </c:forEach>
			    	 			</select>
			    	 			<span id="teacherIdHelper" class="helper"></span>		
	                          </div>
	                        </div>
	                      </td>
	                     </tr>
		                 <tr class="text-gray-700 dark:text-gray-400">
		                      <td class="px-4 py-3">
		                        <div class="flex items-center text-sm">
		                          <!-- Avatar with inset shadow -->
		                          <div>
		                            <p class="font-semibold">개설 운영자 ID</p>
		                          </div>
		                        </div>
		                      </td>
		                      <td class="px-4 py-3">
		                        <div class="flex items-center text-sm">
		                          <!-- Avatar with inset shadow -->
		                          <div>
		                            <p class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
		                            	<input type="text" id="adminId" name="adminId" value="${loginUser}" readonly>
		                            	<span id="adminIdHelper" class="helper"></span>	
		                            </p>
		                          </div>
		                        </div>
		                      </td>
		                 </tr>
                      
                    
                     
                     <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">강좌 이름</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">
                             	<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                             	type="text" id="subjectName" name="subjectName" placeholder="강좌 이름을 입력하세요">
		    					<span id="subjectNameHelper" class="helper"></span>	
                            </p>
                          </div>
                        </div>
                      </td>
                     </tr>
                     
                     <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">강좌 총원</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">
                             	<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                             	type="number" id="subjectStudentMax" name="subjectStudentMax" placeholder="강좌 총원을 입력하세요">
		    					<span id="subjectStudentMaxHelper" class="helper"></span>	
                            </p>
                          </div>
                        </div>
                      </td>
                     </tr>
                     
                     <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">개강 일</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">
                             	<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                             	type="date" id="subjectStartDate" name="subjectStartDate" placeholder="개강 일을 입력하세요">
		    					<span id="subjectStartDateHelper" class="helper"></span>	
                            </p>
                          </div>
                        </div>
                      </td>
                     </tr>
                     
                     <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">종강 일</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">
                             	<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                             	type="date" id="subjectFinishDate" name="subjectFinishDate" placeholder="종강 일을 입력하세요">
		    					<span id="subjectFinishDateHelper" class="helper"></span>	
                            </p>
                          </div>
                        </div>
                      </td>
                     </tr>
                     
                     <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">수업 시작 시간</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">
                             	<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                             	type="time" id="subjectStartTime" name="subjectStartTime" placeholder="수업 시작 시간을 입력하세요">
		    					<span id="subjectStartTimeHelper" class="helper"></span>	
                            </p>
                          </div>
                        </div>
                      </td>
                     </tr>
                     
                     <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">수업 마감 시간</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">
                             	<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                             	type="time" id="subjectEndTime" name="subjectEndTime" placeholder="수업 마감 시간을 입력하세요">
		    					<span id="subjectEndTimeHelper" class="helper"></span>	
                            </p>
                          </div>
                        </div>
                      </td>
                     </tr>
                     
                      
                     <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">강좌 설명</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">
                             	<textarea id="subjectInfo" name="subjectInfo" class="form-control" placeholder="강좌 설명을 입력하세요"></textarea>
		    					<span id="subjectInfoHelper" class="helper"></span>	
                            </p>
                          </div>
                        </div>
                      </td>
                     </tr>
                </table>
                 <br>
                 	<div style="display:inline;">
                 	<div style="float:inherit;" display:inline-block;"></div>
	                  <button class="px-10 py-4 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent 
	                  rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" style="margin: auto; display: block;" 
	                  type="button" id="signup">강좌 추가
	                 </button>
	                </div>
	                </form>
	               </div>
                 <br>
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
