<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>findMemberId</title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="./public/assets/css/tailwind.output.css" />
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="./public/assets/js/init-alpine.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
	<div
		class="flex items-center min-h-screen p-6 bg-gray-50 dark:bg-gray-900">
		<div
			class="flex-1 h-full max-w-4xl mx-auto overflow-hidden bg-white rounded-lg shadow-xl dark:bg-gray-800">
			<div class="flex flex-col overflow-y-auto md:flex-row">
				<div class="h-32 md:h-auto md:w-1/2">
					<img aria-hidden="true"
						class="object-cover w-full h-full dark:hidden"
						src="./public/assets/img/login-office.jpeg" alt="Office" /> <img
						aria-hidden="true"
						class="hidden object-cover w-full h-full dark:block"
						src="./public/assets/img/login-office-dark.jpeg" alt="Office" />
				</div>
				<div class="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
					<div class="w-full">
						<h1
							class="mb-4 text-xl font-semibold text-gray-700 dark:text-gray-200">
							아이디 찾기</h1>
						<form action="${pageContext.request.contextPath}/findMemberId"
							method="post" id="findMemberIdForm">
							<label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400"> 회원구분 </span> <select
								class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
								name="memberLevel" id="memberLevel">
									<option value="2">강사</option>
									<option value="3">학생</option>
							</select>
							</label> <label class="block text-sm"> <span
								class="text-gray-700 dark:text-gray-400">이름</span> <input
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="이름" type="text" name="memberName" id="memberName" />
							</label> <label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">전화번호</span> <input
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="-없이 입력하세요" type="text" name="memberPhone" id="memberPhone" />
							</label>
							<button type="button" id="findIdBtn"
								class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">
								아이디 찾기</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$('#findIdBtn').click(function() {
		if ($('#memberLevel').val() == '') {
			Swal.fire('빈칸이 있습니다.');
			return;
		}else if ($('#memberName').val() == '') {
			Swal.fire('빈칸이 있습니다.');
			return;
		} else if($('#memberPhone').val() == ''){
			Swal.fire('빈칸이 있습니다.');
			return;
		}
		$('#findMemberIdForm').submit();
		
	});
</script>
</html>
