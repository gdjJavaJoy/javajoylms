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
            <c:forEach var="subjectReport" items="${subjectReport}">
	            <a class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
	              href="${pageContext.request.contextPath}/getSubjectReportListByPage?subjectNo=${subjectReport.subjectNo}">
	              <div class="flex items-center">
	                <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
	                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
	                </svg>
	                <span>Subject Report Management Page</span>
	              </div>
	              <span>${subjectReport.subjectName} 과제 게시판 &RightArrow;</span>
	            </a>
            </c:forEach>
            <!-- With avatar -->
            <c:forEach var="subjectReport" items="${subjectReport}">
           		<h4 class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">${subjectReport.subjectName} 과제 상세보기<a href="${pageContext.request.contextPath}/getSubjectReportStudentListByPage?subjectReportNo=${subjectReportNo}" class="text-sm" style="float: right;">학생 과제 제출 게시판(미구현) -></a></h4> 
            </c:forEach>
            <!-- 과제 상세보기 테이블 -->
            <div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
              <div class="w-full overflow-x-auto">
              	 <c:forEach var="subjectReport" items="${subjectReport}">
               		<table class="w-full whitespace-no-wrap">
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 번호</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReport.subjectReportNo}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">강좌 이름</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReport.subjectName}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">작성자</th>
	                    	<c:choose>
	                    		<c:when test="${subjectReport.teacherName != null}">
	                    			<td class="px-4 py-3 text-sm">${subjectReport.teacherName}</td>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<td class="px-4 py-3 text-sm">운영자</td>
	                    		</c:otherwise>
	                    	</c:choose>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 제목</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReport.subjectReportTitle}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 기한</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReport.subjectReportPeriod}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 생성 날짜</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReport.createDate}</td>
	                    </tr>
	                    <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
	                    	<th class="px-4 py-3">과제 내용</th>
	                    	<td class="px-4 py-3 text-sm">${subjectReport.subjectReportContent}</td>
	                    </tr>
              		</table> 
	              	<div class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-9 dark:text-gray-400 dark:bg-gray-800">
		            <span class="flex items-center col-span-3">
		             	
		            </span>
		            <span class="col-span-2"></span>
		            	<!-- Pagination -->
	                <span class="flex col-span-4 mt-2 sm:mt-auto sm:justify-end">
	                <c:if test="${level eq 1 || level eq 2}">
	              		<a href="${pageContext.request.contextPath}/modifySubjectReport?subjectBoardNo=${subjectReport.subjectBoardNo}">과제 수정</a>
	              		<span>&nbsp | &nbsp</span>
	                    <a href="${pageContext.request.contextPath}/deleteSubjectReport?subjectBoardNo=${subjectReport.subjectBoardNo}">과제 삭제(미구현)</a> 
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
		              	<c:when test="${subjectFileList.size() < 0}">
					    	<div>파일없음</div>
					    </c:when>
					    <c:when test="${subjectFileList.size() >= 0}">
					    	<c:forEach var="subjectFileList" items="${subjectFileList}">
					    		<div>
					    			<a href="${pageContext.request.contextPath}/file/subjectFile/${subjectFileList.subjectFileName}" download="${subjectFileList.subjectFileOriginalName}">${subjectFileList.subjectFileOriginalName}</a>
					    		</div>
					    	</c:forEach>
					    </c:when>
				    </c:choose>
   	  		  	</div>
   	  		  </div>
   	  		  <!-- 댓글 입력 -->
   	  		  <div>
	            <div class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
	           			댓글 쓰기 [${commentTotalCount}]
	           	</div>
	           	 <c:forEach var="subjectReport" items="${subjectReport}">
						<form id="addSubjectReportCommentForm" name="addSubjectReportCommentForm" method="post" action="${pageContext.request.contextPath}/addSubjectReportComment?subjectReportNo=${subjectReport.subjectReportNo}&subjectBoardNo=${subjectReport.subjectBoardNo}">
							<input type="hidden" id="subjectReportNo" name="subjectReportNo" class="form-control" value="${subjectReport.subjectReportNo}">
							<span id="subjectReportNoHelper" class="helper"></span>	
							<table class="w-full whitespace-no-wrap">
								<tr class="text-gray-700 dark:text-gray-400">
									<td class="px-4 py-3">
										<div class="flex items-center text-sm">
											<div>
			                            		<p class="font-semibold">작성자</p>
			                          		</div>
		                           		</div>
									</td>
									<td class="px-4 py-3">
		                        		<div class="flex items-center text-sm">
		                         			<div>
					    		 	  			<p class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input">
					                            	<input type="text" id="memberId" name="memberId" value="${loginUser}" readonly>
					                            	<span id="memberIdHelper" class="helper"></span>	
				                       			</p>
		                          			</div>
		                        		</div>
		                      		</td>
								</tr>
								<tr class="text-gray-700 dark:text-gray-400">
				                    <td class="px-4 py-3">
					                    <div class="flex items-center text-sm">
							            	<div>
								        		<p class="font-semibold">댓글 내용</p>
								        	</div>
								        </div>
								    </td>
								    <td class="px-4 py-3">
								    <div class="flex items-center text-sm">
										<div>
										    <p class="font-semibold">
											    <textarea id="subjectReportCommentContent" name="subjectReportCommentContent" class="form-control" placeholder="댓글을 입력하세요"></textarea>
												<span id="subjectReportCommentContentHelper" class="helper"></span>
										    </p>
								        </div>
						            </div>
				                    </td>
			                    </tr>
							</table>
							<br>
			                 	<div style="display:inline;">
				                 	<div style="float:inherit;" display:inline-block;"></div>
						                  <button class="px-5 py-2 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent 
						                  rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" ;" 
						                  type="button" id="addSubjectReportComment">댓글 입력
						                 </button>
					            </div>
						</form>
					</div>
	                 		<br>
					</c:forEach>
				
				<div>
					<!-- 댓글 리스트 -->
					<div class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
	           			전체 댓글 [${commentTotalCount}]
	           		</div>
						<table class="w-full whitespace-no-wrap">
		            		<thead>
		            			<tr class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
		            				<th class="px-4 py-3">작성자</th>
					                <th class="px-4 py-3">내용</th>			             
					                <th class="px-4 py-3">작성일</th>
					                <th class="px-4 py-3"></th>
		            			</tr>
		            		</thead>
		            		<tbody class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
		            			<c:forEach var="commentList" items="${commentList}">
		            				<tr class="text-gray-700 dark:text-gray-400">
		            					<td class="px-4 py-3 text-sm">${commentList.memberId}</td>
		            					<td class="px-4 py-3 text-sm">${commentList.subjectReportCommentContent}</td>
		            					<td class="px-4 py-3 text-sm">${commentList.updateDate}</td>	
		            					<td class="px-4 py-3 text-sm"><a href="">삭제(미구현)</a></td>		
		            				</tr>
		            			</c:forEach>
		            		</tbody>
		            	</table>
		            		<div class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-9 dark:text-gray-400 dark:bg-gray-800">
			            		<span class="flex items-center col-span-3">
					            	Search : <input class="form-control" type="text"  placeholder=" 댓글 검색(미구현)">
					        	</span>
								<span class="col-span-2"></span>
								<span class="flex col-span-4 mt-2 sm:mt-auto sm:justify-end">
									<c:forEach var="subjectReport" items="${subjectReport}">
										<c:if test="${commentCurrentPage > 1}">
													<a href="${pageContext.request.contextPath}/getSubjectReportOne?commentCurrentPage=${commentCurrentPage-1}&subjectBoardNo=${subjectReport.subjectBoardNo}">이전</a>
										</c:if>
											<span>&nbsp &nbsp</span>
										<c:if test="${commentCurrentPage < commentLastPage}">
													<a href="${pageContext.request.contextPath}/getSubjectReportOne?commentCurrentPage=${commentCurrentPage+1}&subjectBoardNo=${subjectReport.subjectBoardNo}">다음</a>
										</c:if>
									</c:forEach>
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
		$('#studentSideNav').load('${pageContext.request.contextPath}/include/studentSideNav.jsp');
		$('#studentHeaderNav').load('${pageContext.request.contextPath}/include/studentHeaderNav.jsp');
		$('#teacherSideNav').load('${pageContext.request.contextPath}/include/teacherSideNav.jsp');
		$('#teacherHeaderNav').load('${pageContext.request.contextPath}/include/teacherHeaderNav.jsp');
	</script>
</html>
            	