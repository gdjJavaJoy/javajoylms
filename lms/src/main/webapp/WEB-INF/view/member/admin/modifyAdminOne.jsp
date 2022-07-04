<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>modifyAdminOne</title>
<style>
img {
	display: block;
	margin: 0px auto;
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
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen}">
		<!-- sidebar -->
		<aside
			class="z-20 flex-shrink-0 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block">
			<div id="adminSideNav"></div>
		</aside>
		<div class="flex flex-col flex-1 w-full">
			<!-- header -->
			<div id="adminHeaderNav"></div>
			<main class="h-full pb-16 overflow-y-auto">
				<div class="container grid px-6 mx-auto">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						Tables</h2>
					<h4
						class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
						${admin.adminName}님의 정보</h4>
					<div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
							<form method="post"
								action="${pageContext.request.contextPath}/modifyAdminOne" id="updateForm">
								<table class="w-full whitespace-no-wrap">
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">ID</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">
														${admin.memberId} <input type="text"
															value="${admin.memberId}" name="memberId" hidden="hidden">
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
													<p class="font-semibold">이름</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">
														<input
															class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
															id="memberName" type="text" name="memberName"
															value="${admin.adminName}" />
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
													<p class="font-semibold">연락처</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">
														<input
															class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
															id="memberPhone" type="text" name="memberPhone"
															value="${admin.adminPhone}" />
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
													<p class="font-semibold">주소</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<input
													class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
													type="text" value="${admin.adminAddress}"
													name="currentMemberAddress" id="keyword">
												<button
													class="px-3 py-1 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-md active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
													type="button" id="updateAddrBtn">변경할 주소 검색</button>
											</div>
										</td>
									</tr>
									<tr id="insertForm" class="text-gray-700 dark:text-gray-400">
										<!-- 추가될 폼 -->
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">상세주소</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">
														<input
															class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
															id="memberDetailAddress" name="memberDetailAddress"
															type="text" value="${admin.adminDetailAddress}" />
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
													<p class="font-semibold">이메일</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">
														<input
															class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
															id="memberEmail" name="memberEmail" type="text"
															value="${admin.adminEmail}" />
													</p>
												</div>
											</div>
										</td>
									</tr>
								</table>
								<br>
								<button
									class="px-10 py-4 font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
									type="button"
									id = "submitBtn">
									정보수정</button>
							</form>
							<br>
						</div>
			</main>
		</div>
	</div>
</body>
<script>
	$('#adminSideNav').load('${pageContext.request.contextPath}/include/adminSideNav.jsp');
	$('#adminHeaderNav').load('${pageContext.request.contextPath}/include/adminHeaderNav.jsp');
	
	$('#updateAddrBtn').click(function(){
		$('#insertForm').empty();
		$('#insertForm').append('<td class="px-4 py-3">\
	    <div class="flex items-center text-sm">\
	    <div><p class="font-semibold">변경할 주소</p></div></div></td>\
	    <td class="px-4 py-3">\
	    <div class="flex items-center text-sm">\
	    <select class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"\
	    name="changeMemberAddress" id="changeMemberAddress"></select></div></td>');
		$.ajax({
			type:'get'
			,url:'/lms/getAddr'
			,data:{keyword : $('#keyword').val()}
			,success:function(a) {
				console.log(a);
				console.log(typeof (a));
				var a2 = JSON.parse(a);
				console.log(typeof (a2));
				console.log(a2);
	
				let arr = a2.results.juso; // 주소배열
				console.log(arr);
				for (let i = 0; i < arr.length; i++) {
					var addr = (arr[i].jibunAddr).replace(/\s/gi, "");
					var obj = $("<option value="+addr+">"+ arr[i].jibunAddr + '('+ arr[i].zipNo + ")</option>");
					$('#changeMemberAddress').append(obj);
				};
			}
		});
	});
		
	$('#submitBtn').click(function() {
		if($('#memberName').val() == '') {
			Swal.fire('이름을 입력해주세요');
			alert('이름을 입력해주세요');
			return;
		} else if (!/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/.test($('#memberPhone').val())) { //핸드폰 1/2번째자리는 01로 시작함/ 번호 사이사이 대쉬는 무시
			Swal.fire('연락처를 입력해주세요');
			return;
		} else if (!/^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/.test($('#memberEmail').val())) {
			// 이메일의 경우 첫글자 _가 허용되므로 첫번째 글자 검사식을 따로 두었다.
			// 영어 대/소문자 구분
			// @ 반드시 하나만 입력, . 반드시 하나만 입력
			// .뒤에 최소 한글자에서 최대 3글자까지
			Swal.fire('이메일을 입력하세요');
			return;
		} else if ($('#keyword').val() == '') {
			Swal.fire('주소를 입력해주세요');
			return;
		}  else if ($('#changeMemberAddress').val() == '') {
			Swal.fire('변경할 주소를 입력해주세요');
			return;
		} else if ($('#memberDetailAddress').val() == '') {
			Swal.fire('상세주소를 입력해주세요');
			return;
		} else {
			$('#updateForm').submit();
		}
	});
</script>
</html>
