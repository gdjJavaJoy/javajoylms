<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>자유게시판</title>
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
		<div x-show="isSideMenuOpen"
			x-transition:enter="transition ease-in-out duration-150"
			x-transition:enter-start="opacity-0"
			x-transition:enter-end="opacity-100"
			x-transition:leave="transition ease-in-out duration-150"
			x-transition:leave-start="opacity-100"
			x-transition:leave-end="opacity-0"
			class="fixed inset-0 z-10 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"></div>
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
			<main class="h-full overflow-y-auto">
				<div class="container px-6 mx-auto grid">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						자유게시판</h2>
					<!-- New Table -->
					<div class="w-full overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
								<table class="w-full whitespace-no-wrap">
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3 text-sm">작성자ID</td>
										<c:choose>
											<c:when test="${board.privateNo eq 1 || level eq 1 || board.memberId eq loginUser}">
											<td class="px-4 py-3">
											${board.memberId}
											</td>
											</c:when>
											<c:otherwise>
											<td class="px-4 py-3">
											익명
											</td>
											</c:otherwise>
										</c:choose>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3 text-sm">작성자</td>
										<td class="px-4 py-3">
										<c:choose>
											<c:when test="${board.privateNo eq 1 || level eq 1 || board.memberId eq loginUser}">
												<c:if test="${board.adminName != null}">
													${board.adminName}
												</c:if>				
												<c:if test="${board.teacherName != null}">
													${board.teacherName}
												</c:if>
												<c:if test="${board.studentName != null}">
													${board.studentName}
												</c:if>			
											</c:when>
											<c:otherwise>
												익명
											</c:otherwise>
										</c:choose>
										</td>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3 text-sm">제목</td>
										<td class="px-4 py-3">
										${board.boardTitle}
										</td>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3 text-sm">작성일</td>
										<td class="px-4 py-3">
										${board.createDate}
										</td>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3 text-sm">내용</td>
										<td class="px-4 py-3">
											<textarea class="text-gray-700 dark:text-gray-400" readonly>${board.boardContent}</textarea>
										<c:if test="${fileCount > 0}">
											<c:forEach var="img" items="${boardFile}">
												<!-- 첨부파일 안에 이미지파일이 하나라도 있으면 실행 -->
												<c:if test="${img.boardFileType eq 'image/jpeg' || img.boardFileType eq 'image/jpg' || img.boardFileType eq 'image/png'}"> 
												<img src="${pageContext.request.contextPath}/file/freeBoardFile/${img.boardFileName}">
												</c:if>
											</c:forEach>
										</c:if>
										</td>
									</tr>
								</table>
								<div>
								<!-- 수정삭제 버튼  -->
									<div class="flex items-center space-x-4 text-sm">
						<c:if test="${loginUser eq board.memberId}"> <!-- sessionID 와 board에 저장되어있는 Id가 같을 때 나타냄 -->
                           <a href="${pageContext.request.contextPath}/modifyFreeBoard?boardNo=${board.boardNo}">수정</a>
                         
                          </c:if>
                          <c:if test="${loginUser eq  board.memberId || level eq 1}">
                           <a href="${pageContext.request.contextPath}/removeFreeBoard?boardNo=${board.boardNo}">삭제</a>
                          </c:if>
						</div>
						  <c:if test="${fileCount > 0}"> <!-- boardNo 에 저장되어있는 파일 이한개라도 있으면 실행  -->
								<div style="display: inline-block; margin: 0 5px;  float: right;">
	              					<div class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">첨부 파일</div>
									<div>
										<c:forEach var="bf" items="${boardFile}">
										<div>
											<a href="${pageContext.request.contextPath}/file/freeBoardFile/${bf.boardFileName}" download="${bf.boardFileOriginalName}">${bf.boardFileOriginalName}</a>
										</div>
										</c:forEach>
									</div>
								</div>
								</c:if>
							<br>
						<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						답변</h2>
						<form id="addCommentForm" method="post" action="${pageContext.request.contextPath}/addFreeBoardComment">
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
					                            	${loginUser}
					                            <input type="text" name="memberId" value="${loginUser}" hidden="hidden">
					                            <input type="number" name="boardNo" value="${board.boardNo}" hidden="hidden">
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
											    <textarea id="commentContent" name="boardCommentContent" class="form-control" placeholder="댓글을 입력해주세요"></textarea>
												<span id="subjectReportCommentContentHelper" class="helper"></span>
										    </p>
								        </div>
						            </div>
				                    </td>
			                    </tr>
			                    <tr class="text-gray-700 dark:text-gray-400">
				                    <td class="px-4 py-3">
					                    <div class="flex items-center text-sm">
							            	<div>
								        		<p class="font-semibold">익명체크</p>
								        	</div>
								        </div>
								    </td>
								    <td class="px-4 py-3">
								   <input
											type="radio"
											class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
											name="commentAnony"
											value="1"
											checked
											/>
											익명X
											<input
											type="radio"
											class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
											name="commentAnony"
											value="2"
											/>
											익명O
				                    </td>
			                    </tr>
							</table>
							<br>
			                 	<div style="display:inline;">
				                 	<div style="float:inherit;" display:inline-block;"></div>
						                  <button class="px-5 py-2 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent 
						                  rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple" ;" 
						                  type="button" id="addFreeBoardComment">댓글 입력
						                 </button>
					            </div>
						</form>
						<br>
						<div class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
	           			전체댓글  [${fn:length(boardComment)}]
	           		</div>
	           		<table class="w-full whitespace-no-wrap">
		            		<thead>
		            			<tr class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
		            				<th class="px-4 py-3">작성자ID</th>
		            				<th class="px-4 py-3">작성자이름</th>
					                <th class="px-4 py-3">내용</th>			             
					                <th class="px-4 py-3">작성일</th>
					                <c:if test="${level eq 1 || loginUser eq ic.memberId}">
					                <th class="px-4 py-3">삭제</th>
					                </c:if>
		            			</tr>
		            		</thead>
		            		<tbody class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
		            			<c:forEach var="ic" items="${boardComment}">
		            				<tr class="text-gray-700 dark:text-gray-400">
		            				<c:choose>
		            					<c:when test="${ic.anony eq 1 || level eq 1}">
		            					<td class="px-4 py-3 text-sm">${ic.memberId}</td>
		            					</c:when>
		            					<c:otherwise>
		            					<td class="px-4 py-3 text-sm">익명</td>
		            					</c:otherwise>
		            				</c:choose>
		            					<td class="px-4 py-3 text-sm">
		            					<c:choose>
		            					<c:when test="${ic.anony eq 1 || level eq 1}">
		            						<c:if test="${ic.adminName != null}">
													${ic.adminName}
												</c:if>				
												<c:if test="${ic.teacherName != null}">
													${ic.teacherName}
												</c:if>
												<c:if test="${ic.studentName != null}">
													${ic.studentName}
												</c:if>			
		            					</c:when>
			            				<c:otherwise>
			            					익명		
			            				</c:otherwise>
			            					</c:choose>
		            					</td>
		            					<td class="px-4 py-3 text-sm">${ic.boardCommentContent}</td>
		            					<td class="px-4 py-3 text-sm">${ic.createDate}</td>
		            					<c:if test="${level eq 1 || loginUser eq ic.memberId}">
		            					<td class="px-4 py-3 text-sm"><a href="${pageContext.request.contextPath}/removeFreeBoardComment?boardCommentNo=${ic.boardCommentNo}&boardNo=${board.boardNo}">삭제</a></td>
		            					</c:if>	
		            				</tr>
		            			</c:forEach>
		            		</tbody>
		            	</table>
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

	$('#addFreeBoardComment').click(function(){
		if($('#commentContent').val() == '') {
			alert('댓글을 입력해주세요');
			return;
		} else {
			$('#addCommentForm').submit();
		}
	});
</script>
</html>
