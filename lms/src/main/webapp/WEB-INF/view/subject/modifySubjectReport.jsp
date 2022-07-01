<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="./public/assets/css/tailwind.output.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script> 
    <script src="./public/assets/js/init-alpine.js"></script>
    
	<script>
		$(document).ready(function(){
			let flag = true;
			$('#addFileupload').click(function(){
				$('.subjectReportFileList').each(function(){
					if($(this).val() == '') {
						flag = false;
					}
				});
				
				if(flag) {
					$('#fileSection').append("<div><input type='file' class='subjectReportFileList' onchange='checkFile(this)' id='subjectReportFileList' name='subjectReportFileList'></div>");
				} else {
					Swal.fire('파일 첨부를 다시 확인하십시오');
				}
			});
			
			$('#modifySubjectReport').click(function(){
				if($('#subjectBoardNo').val() == '') {
					Swal.fire('test');
			    } else if($('#subjectNo').val() == '') {
			    	Swal.fire('test');
			    } else if($('#memberId').val() == '') {
			    	Swal.fire('test');
				} else if($('#subjectReportTitle').val() == '') {
					Swal.fire('과제 게시판 제목을 설정하세오!');
				} else if($('#subjectReportPeriod').val() == '') {
					Swal.fire('과제 기한을 설정하세요!');
				} else if($('#subjectReportContent').val() == '') {
					Swal.fire('과제 게시판 내용을 입력하세요!');
				} else {
					$('.subjectReportFileList').each(function(){
						if($(this).val() == '') {
							flag = false;
							return;
						}
					});
					if(flag) {
						$('#modifySubjectReportForm').submit();
					} else {
						Swal.fire('파일 첨부를 다시 확인하십시오');
					}
				}
			});
		});
		
		// 파일 확장자 체크 
		// 강좌 - 과제 게시판은 pdf, hwp, doxc, ppt, txt만 가능
		function checkFile(f){
			// files 로 해당 파일 정보 얻기.
			var file = f.files;
			// file[0].name 은 파일명 입니다.
			// 정규식으로 확장자 체크
			if(!/\.(pdf|hwp|docx|ppt|txt)$/i.test(file[0].name)) Swal.fire('pdf, hwp, docx, ppt, txt 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);
			// 체크를 통과했다면 종료.
			else return;
			// 체크에 걸리면 선택된  내용 취소 처리를 해야함.
			// 파일선택 폼의 내용은 스크립트로 컨트롤 할 수 없습니다.
			// 그래서 그냥 새로 폼을 새로 써주는 방식으로 초기화 합니다.
			// 이렇게 하면 간단 !?
			f.outerHTML = f.outerHTML;
		}
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
			
        <main class="h-full pb-16 overflow-y-auto">
            <div class="container grid px-6 mx-auto">
            <h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">${loginUser}님의 Subject Report Update</h2>
            <!-- CTA -->
            <c:forEach var="subjectReport" items="${subjectReport}">
            <a class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
              href="${pageContext.request.contextPath}/getSubjectReportListByPage?subjectNo=${subjectReport.subjectNo}">
              <div class="flex items-center">
                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                </svg>
                <span>Subject Management Page</span>
              </div>
              <span>과제 게시판 &RightArrow;</span>
            </a>
            </c:forEach>
       
            <!-- With avatar -->
            <h4
              class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300"
            >
              과제 수정
            </h4>
            <div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto">
             	<form id="modifySubjectReportForm" method="post" name="modifySubjectReportForm" action="${pageContext.request.contextPath}/modifySubjectReport" enctype="multipart/form-data">
             	<c:forEach var="subjectReport" items="${subjectReport}">
	                	<table class="w-full whitespace-no-wrap">
	                		<input type="hidden" id="subjectBoardNo" name="subjectBoardNo" class="form-control" value="${subjectReport.subjectBoardNo}">
	               			<span id="subjectBoardNoHelper" class="helper"></span>
	                		<input type="hidden" id="subjectNo" name="subjectNo" class="form-control" value="${subjectReport.subjectNo}">
	                		<span id="subjectNoHelper" class="helper"></span>	              
		                	<tr class="text-gray-700 dark:text-gray-400">
		                      <td class="px-4 py-3">
		                        <div class="flex items-center text-sm">
		                          <!-- Avatar with inset shadow -->
		                          <div>
		                            <p class="font-semibold">작성자 ID</p>
		                          </div>
		                        </div>
		                      </td>
		                       <td class="px-4 py-3">
		                        <div class="flex items-center text-sm">
		                          <!-- Avatar with inset shadow -->
		                          <div>
				    		 	  	<p class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
			                            	<input type="text" id="memberId" name="memberId" value="${subjectReport.memberId}" readonly>
			                            	<span id="memberIdHelper" class="helper"></span>	
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
			                            <p class="font-semibold">과제 제목</p>
			                          </div>
			                        </div>
			                      </td>
			                      <td class="px-4 py-3">
			                        <div class="flex items-center text-sm">
			                          <!-- Avatar with inset shadow -->
			                          <div>
			                            <p class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
			                            	<input type="text" id="subjectReportTitle" name="subjectReportTitle" value="${subjectReport.subjectReportTitle}">
			                            	<span id="subjectReportTitleHelper" class="helper"></span>	
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
		                            	<p class="font-semibold">과제 기한</p>
		                             </div>
	                       		 </div>
	                      	</td>
	                     	<td class="px-4 py-3">
	                        <div class="flex items-center text-sm">
	                       	   <!-- Avatar with inset shadow -->
	                           <div>
		                            <p class="font-semibold">
		                             	<input class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
		                             	type="datetime-local" id="subjectReportPeriod" name="subjectReportPeriod" value="${subjectReport.subjectReportPeriod}">
				    					<span id="subjectReportPeriodHelper" class="helper"></span>	
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
	                            <p class="font-semibold">과제 내용</p>
	                          </div>
	                        </div>
	                      </td>
	                       <td class="px-4 py-3">
	                        <div class="flex items-center text-sm">
	                          <!-- Avatar with inset shadow -->
	                          <div>
	                            <p class="font-semibold">
	                             	<textarea id="subjectReportContent" name="subjectReportContent" class="form-control">${subjectReport.subjectReportContent}</textarea>
			    					<span id="subjectReportContentHelper" class="helper"></span>
	                            </p>
	                          </div>
	                        </div>
	                      </td>
	                     </tr>
                     </c:forEach>
                     
                    <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">첨부 파일 추가</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <button 
                            	class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" 
                            	id="addFileupload">파일 추가</button>
					    		<div id="fileSection">
					    			<!-- subject file input 태그가 추가댐 -->
					    		</div>	
                          </div>
                        </div>
                      </td>
                     </tr>
                     
                     <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">첨부 파일 삭제</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm">
                          <!-- Avatar with inset shadow -->
                          <div>
					    		<c:forEach var="subjectFile" items="${subjectFile}">
									<div>
										<a class="px-1 py-0.8 text-xs font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
										href="${pageContext.request.contextPath}/removeSubjectFile?subjectFileNo=${subjectFile.subjectFileNo}&subjectBoardNo=${subjectFile.subjectBoardNo}">삭제</a>${subjectFile.subjectFileOriginalName}
									</div>
								</c:forEach>
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
			                  type="button" id="modifySubjectReport">과제 수정
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
