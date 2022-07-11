<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
 <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Modify Subject Notice Page</title>
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
     <!-- summernote --> 
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
 	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
  	<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>	
    <script>
    // 유효성 검사
		$(document).ready(function(){
			let flag = true;
			$('#addFileupload').click(function(){
				$('.subjectNoticeFileList').each(function(){
					if($(this).val() == '') {
						flag = false;
					}
				});
				
				if(flag) {
					$('#fileSection').append("<div><input type='file' class='subjectNoticeFileList' onchange='checkFile(this)' id='subjectNoticeFileList' name='subjectNoticeFileList' accept='image/*, .xls, .xlsx, pdf, hwp, docx, ppt, txt'></div>");
				} else {
					Swal.fire('파일 첨부를 다시 확인하십시오');
				}
			});
			
			$('#modifySubjectNotice').click(function(){
				if($('#subjectNoticeTitle').val() == '') {
					Sweet.alert('제목을 입력하세요!');
				} else if($('#subjectNoticeContent').val() == '') {
					Swal.fire('내용을 입력하세요!');
				} else {
					$('.subjectNoticeFileList').each(function(){
						if($(this).val() == '') {
							flag = false;
							return;
						}
					});
					if(flag) {
						$('#modifySubjectNoticeForm').submit();
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
			if(!/\.(pdf|hwp|docx|ppt|txt|xls|xlsx|png|jpeg)$/i.test(file[0].name)) Swal.fire('pdf, hwp, docx, ppt, txt 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);
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
            <c:forEach var="n" items="${subjectNotice}">
            <h2 class="my-6 text-2xl1 font-semibold text-gray-700 dark:text-gray-200">${n.subjectNoticeTitle}님의 Subject Notice Update</h2>
            <!-- CTA -->
            <a class="flex items-center justify-between p-41 mb-8 text-sm1 font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
              href="${pageContext.request.contextPath}/subjectNoticeList?subjectNo=${n.subjectNo}">
              <div class="flex items-center">
                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                </svg>
                <span>강좌 공지사항 게시판</span>
              </div>
              <span>수정 취소 &RightArrow;</span>
            </a>
            
            
       
            <!-- With avatar -->
            <h4
              class="mb-4 text-lg1 font-semibold text-gray-600 dark:text-gray-300"
            >${n.subjectNoticeTitle} 수정 페이지
            </h4>
             </c:forEach>
            <div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto">
             	<form id="modifySubjectNoticeForm" method="post" name="modifySubjectNoticeForm" action="${pageContext.request.contextPath}/modifySubjectNotice" enctype="multipart/form-data">
            	 	<c:forEach var="sn" items="${subjectNotice}">
	                	<table class="w-full whitespace-no-wrap">
	                		<input type="hidden" id="subjectBoardNo" name="subjectBoardNo" class="form-control" value="${sn.subjectBoardNo}">
	                		<input type="hidden" id="subjectNo" name="subjectNo" class="form-control" value="${sn.subjectNo}">
		                	<tr class="text-gray-700 dark:text-gray-400">
		                      <td class="px-4 py-3">
		                        <div class="flex items-center text-sm1">
		                          <!-- Avatar with inset shadow -->
		                          <div>
		                            <p class="font-semibold">작성자 ID</p>
		                          </div>
		                        </div>
		                      </td>
		                       <td class="px-4 py-3">
		                        <div class="flex items-center text-sm1">
		                          <!-- Avatar with inset shadow -->
		                          <div>
				    		 	  	<p class="block w-full mt-1 text-sm1 dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
			                            	<input type="text" id="memberId" name="memberId" value="${sn.memberId}" readonly>
			                        </p>
		                          </div>
		                        </div>
		                      </td>
		                     </tr>
			                 <tr class="text-gray-700 dark:text-gray-400">
			                      <td class="px-4 py-3">
			                        <div class="flex items-center text-sm1">
			                          <!-- Avatar with inset shadow -->
			                          <div>
			                            <p class="font-semibold">강좌공지사항 제목</p>
			                          </div>
			                        </div>
			                      </td>
			                      <td class="px-4 py-3">
			                        <div class="flex items-center text-sm1">
			                          <!-- Avatar with inset shadow -->
			                          <div>
			                            <p class="block w-full mt-1 text-sm1 dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
			                            	<input type="text" id="subjectNoticeTitle" name="subjectNoticeTitle" value="${sn.subjectNoticeTitle}">
			                            </p>
			                          </div>
			                        </div>
			                      </td>
			                 </tr>
	                     <tr class="text-gray-700 dark:text-gray-400">
	                      <td class="px-4 py-3">
	                        <div class="flex items-center text-sm1">
	                          <!-- Avatar with inset shadow -->
	                          <div>
	                            <p class="font-semibold">강좌 공지사항 내용</p>
	                          </div>
	                        </div>
	                      </td>
	                       <td class="px-4 py-3">
	                        <div class="flex items-center text-sm1">
	                          <!-- Avatar with inset shadow -->
	                          <div>
	                            <p class="font-semibold">
	                             	<textarea id="subjectNoticeContent" name="subjectNoticeContent" class="form-control">${sn.subjectNoticeContent}</textarea>
	                            	<script type="text/javascript">
											$(document).ready(function(){
												$('#subjectNoticeContent').summernote({
													placeholder : '내용을 작성하세요',
													height : 400,
													maxHeight : 400
												});
											});	
										</script>
	                            </p>
	                          </div>
	                        </div>
	                      </td>
	                     </tr>
                     </c:forEach>
                    <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm1">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">첨부 파일 추가</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm1">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <button 
                            	class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" 
                            	type="button" id="addFileupload">파일 추가</button>
					    		<div id="fileSection">
					    			<!-- subject file input 태그가 추가댐 -->
					    		</div>	
                          </div>
                        </div>
                      </td>
                     </tr>
                     
                     <tr class="text-gray-700 dark:text-gray-400">
                      <td class="px-4 py-3">
                        <div class="flex items-center text-sm1">
                          <!-- Avatar with inset shadow -->
                          <div>
                            <p class="font-semibold">첨부 파일</p>
                          </div>
                        </div>
                      </td>
                       <td class="px-4 py-3">
                        <div class="flex items-center text-sm1">
                          <!-- Avatar with inset shadow -->
                          <div>
					    		<c:forEach var="snf" items="${subjectNoticeFile}">
									<div>
										${snf.subjectFileOriginalName}
										<a class="px-1 py-0.8 text-xs font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
										href="${pageContext.request.contextPath}/removeSubjectNoticeFile?subjectFileNo=${snf.subjectFileNo}&subjectBoardNo=${snf.boardNo}&subjectNo=${snf.subjectNo}">삭제</a>
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
			                  type="button" id="modifySubjectNotice">강좌 공지사항 수정
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
  <!-- 인클루드 -->
   	<script>
 		$('#adminSideNav').load('${pageContext.request.contextPath}/include/adminSideNav.jsp');
		$('#adminHeaderNav').load('${pageContext.request.contextPath}/include/adminHeaderNav.jsp');
		$('#teacherSideNav').load('${pageContext.request.contextPath}/include/teacherSideNav.jsp');
		$('#teacherHeaderNav').load('${pageContext.request.contextPath}/include/teacherHeaderNav.jsp');
	</script>
</html>
