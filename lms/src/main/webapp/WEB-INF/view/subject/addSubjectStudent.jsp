<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title></title>
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
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
$(document).ready(function(){
	$('#adminSideNav').load('${pageContext.request.contextPath}/include/adminSideNav.jsp');
	$('#adminHeaderNav').load('${pageContext.request.contextPath}/include/adminHeaderNav.jsp');
	$('#studentSideNav').load('${pageContext.request.contextPath}/include/studentSideNav.jsp');
	$('#studentHeaderNav').load('${pageContext.request.contextPath}/include/studentHeaderNav.jsp');
	
$('#subjectNo').change(function(){
	$.ajax({
		url:'/lms/getStudentList'
		,type:'GET'
		,data:{subjectNo:$('#subjectNo').val()}
		,success:function(data){
			$('#list').empty();
			$(data).each(function(index,item){
				$('#list').append('<td class="px-4 py-3">\
						<input\
		                type="checkbox"\
		                class="text-purple-600 form-checkbox focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"\
		                name="studentId" value="'+item.memberId+'"/>\
				</td>');
				$('#list').append('</tr>');
			});
		}
	});
});

	$('#addBtn').click(function(){
		if($("#subjectNo").val() == '0'){
			Swal.fire('강좌를 선택해주세요');
			return;
		} else if ($("input:checked[Name='studentId']").is(":checked")<1) {
			Swal.fire('학생을 선택해주세요');
			return;
		} else  {
			$('#form').submit();
		}
	});
});



</script>
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
			<main class="h-full overflow-y-auto">
				<div class="container px-6 mx-auto grid">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
					학생 추가</h2>
						<div>
			            </div>
					<!-- New Table -->
					<div class="w-full overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
							<form method="post" action="${pageContext.request.contextPath}/addSubjectStudent" id="form">
								<table class="w-full whitespace-no-wrap">
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3 text-sm">강의 선택</td>
										<td class="px-4 py-3 text-sm">
											<select
											class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
											name="subjectNo"
											id="subjectNo">
											<option value="0">::강의선택::</option>
											<c:forEach var ="s" items="${subjectList}">
											<option value="${s.subjectNo}">${s.subjectName}</option>
											</c:forEach>
											</select>
										</td>
									</tr>
									</table>
									<table class="w-full whitespace-no-wrap">
										<thead>
										<tr
											class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800">
											<th class="px-4 py-3">학생ID</th>
											<th class="px-4 py-3">이름</th>
											<th class="px-4 py-3">성별</th>
											<th class="px-4 py-3">전화번호</th>
											<th class="px-4 py-3">이메일</th>
											<th class="px-4 py-3">최종학력</th>
											<th class="px-4 py-3">check</th>
										</tr>
									<tbody
										class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800">
										<c:forEach var="l" items="${studentList}">
										<tr class="text-gray-700 dark:text-gray-400">
											<td class="px-4 py-3">${l.memberId}</td>
											<td class="px-4 py-3">${l.studentName}</td>
											<td class="px-4 py-3">${l.studentGender}</td>
											<td class="px-4 py-3">${l.studentPhone}</td>
											<td class="px-4 py-3">${l.studentEmail}</td>
											<td class="px-4 py-3">${l.studentEducation}</td>
											<td class="px-4 py-3">
											<input
		               						 type="checkbox"
		                					 class="text-purple-600 form-checkbox focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
		                					 name="studentId" value="${l.memberId}"/>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								</form>
									<br>
								   <div>
					                <button
					                  class="px-4 py-2 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
					                  id="addBtn"
					                  type="button"
					                >
					               	학생 추가
					                </button>
					              </div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
</html>
