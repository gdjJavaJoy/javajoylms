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
				if($('subjectNo').val() == '') {
					Swal.fire('test');
			    } else if($('#curriculumNo').val() == '') {
			    	Swal.fire('test');
			    } else if($('#memberId').val() == '') {
			    	Swal.fire('담당 강사를 입력하세요');
			    } else if($('#languageNo').val() == '') {
			    	Swal.fire('프로그래밍 언어 선택!');
				} else if($('#curriculumTitle').val() == '') {
					Swal.fire('커리큘럼 제목을 입력!');
				} else if($('#startDay').val() == '') {
					Swal.fire('커리큘럼 시작 날짜를 입력하세요!');
				} else if($('#endDay').val() == '') {
					Swal.fire('커리큘럼 마감 날짜를 입력하세요!');
				} else if($('#curriculumContent').val() == '') {
					Swal.fire('커리큘럼 내용을 입력하세요!');
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
            <div class="container grid px-6 mx-auto">
            <h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">${loginUser}님의 커리큘럼 페이지</h2>
            <!-- CTA -->
            <c:forEach var="curriculum" items="${curriculum}">
	            <a class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
	              href="${pageContext.request.contextPath}/getCurriculumOne?curriculumNo=${curriculum.curriculumNo}&subjectName=${curriculum.subjectName}">
	              <div class="flex items-center">
	                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
	                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
	                </svg>
	                <span>커리큘럼 관리 페이지</span>
	              </div>
	              <span>${curriculum.curriculumTitle} &RightArrow;</span>
	            </a>
	       
	            <!-- With avatar -->
	            <h4
	              class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
	            >
	              ${curriculum.curriculumTitle} 수정
	            </h4>
            </c:forEach>
            
            <div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto">
              <c:forEach var="curriculum" items="${curriculum}">
             	<form id="addSubjectForm" method="post" name="addSujectForm" action="${pageContext.request.contextPath}/modifyCurriculum">
                	<input type="hidden" id="subjectNo" name="subjectNo" class="form-control" value="${curriculum.subjectNo}">
                	<input type="hidden" id="curriculumNo" name="curriculumNo" class="form-control" value="${curriculum.curriculumNo}">
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
			    				<select id="memberId" name="memberId" 
			    						class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-select" >
			    					<c:forEach var="c" items="${teacherList}">
			    						<option value="${c.memberId}">${c.teacherName}</option>
				    			    </c:forEach>
			    	 			</select>
	                          </div>
	                        </div>
	                      </td>
	                     </tr>
	                     <tr class="text-gray-700 dark:text-gray-400">
	                      <td class="px-4 py-3">
	                        <div class="flex items-center text-sm">
	                          <!-- Avatar with inset shadow -->
	                          <div>
	                            <p class="font-semibold">교육 프로그래밍 언어</p>
	                          </div>
	                        </div>
	                      </td>
	                       <td class="px-4 py-3">
	                        <div class="flex items-center text-sm">
	                          <!-- Avatar with inset shadow -->
	                          <div>
			    				<select id="languageNo" name="languageNo" 
			    						class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-select" >
			    					<c:forEach var="s" items="${languageList}">
			    						<option value="${s.languageNo}">${s.languageName}</option>
				    			    </c:forEach>
			    	 			</select>
	                          </div>
	                        </div>
	                      </td>
	                     </tr>
		                 <tr class="text-gray-700 dark:text-gray-400">
		                      <td class="px-4 py-3">
		                        <div class="flex items-center text-sm">
		                          <!-- Avatar with inset shadow -->
		                          <div>
		                            <p class="font-semibold">커리큘럼 제목</p>
		                          </div>
		                        </div>
		                      </td>
		                      <td class="px-4 py-3">
		                        <div class="flex items-center text-sm">
		                          <!-- Avatar with inset shadow -->
		                          <div>
		                            <p class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
		                            	<input type="text" id="curriculumTitle" name="curriculumTitle" value="${curriculum.curriculumTitle}">
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
                            <p class="font-semibold">수업 시작 날짜</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">
                             	<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                             	type="date" id="startDay" name="startDay" value="${curriculum.startDay}">
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
                            <p class="font-semibold">수업 마감 날짜</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">
                             	<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                             	type="date" id="endDay" name="endDay" value="${curriculum.endDay}">
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
		                            <p class="font-semibold">커리큘럼 내용</p>
		                          </div>
		                        </div>
		                      </td>
		                      <td class="px-4 py-3">
		                        <div class="flex items-center text-sm">
		                          <!-- Avatar with inset shadow -->
		                          <div>
		                            <p class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
		                            	<textarea id="curriculumContent" name="curriculumContent" class="form-control">${curriculum.curriculumContent}</textarea>
		                            </p>
		                          </div>
		                        </div>
		                      </td>
		                 </tr>
                     </c:forEach>
                </table>
                 <br>
                 	<div style="display:inline;">
                 	<div style="float:inherit;" display:inline-block;"></div>
	                  <button class="px-10 py-4 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent 
	                  rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" style="margin: auto; display: block;" 
	                  type="button" id="signup">커리큘럼 수정
	                 </button>
	                </div>
	                </form>
	               </div>
                 <br>
                 
               </div>
               <!-- 도서 리스트 -->
				<div class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
	           		참고 도서 목록
	           	</div>
					<table class="w-full whitespace-no-wrap">
		            	<thead>
		            		<tr class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
		            			<th class="px-4 py-3">도서 제목</th>
					            <th class="px-4 py-3">저자</th>			             
					            <th class="px-4 py-3">출판사</th>
		            		</tr>
		            	</thead>
		            	<tbody class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
		            		<c:forEach var="modifyBookList" items="${modifyBookList}">
		            			<tr class="text-gray-700 dark:text-gray-400">
		            				<td class="px-4 py-3 text-sm">${modifyBookList.bookTitle}</td>
		            				<td class="px-4 py-3 text-sm">${modifyBookList.bookWriter}</td>
		            				<td class="px-4 py-3 text-sm">${modifyBookList.bookCompany}</td>	
		            			</tr>
		            		</c:forEach>
		            	</tbody>
		            </table>
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
