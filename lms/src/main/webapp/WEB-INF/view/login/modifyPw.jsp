<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ChangePassword</title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="./public/assets/css/tailwind.output.css" />
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="./public/assets/js/init-alpine.js"></script>
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
						src="./public/assets/img/create-account-office.jpeg" alt="Office" />
					<img aria-hidden="true"
						class="hidden object-cover w-full h-full dark:block"
						src="./public/assets/img/create-account-office-dark.jpeg"
						alt="Office" />
				</div>
				<div class="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
					<div class="w-full">
						<h1
							class="mb-4 text-xl font-semibold text-gray-700 dark:text-gray-200">
							${loginUser}님의 비밀번호 변경</h1>
						<form method="post"
							action="${pageContext.request.contextPath}/modifyPw" id="pwForm">
							<input type="text" name="memberId" id="memberId"
								value="${loginUser}" hidden="hidden"> <label
								class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400">변경할 비밀번호 입력</span> <input
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="변경할 비밀번호 입력" type="password" name="password"
								id="password" />
							</label> <br>
							<button
								class="px-3 py-1 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-md active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
								type="button" id="pwck">비밀번호 사용이력 확인
							</button>
							<label class="block mt-4 text-sm"> <span
								class="text-gray-700 dark:text-gray-400"> 비밀번호 확인 </span> <input
								class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
								placeholder="비밀번호 확인" type="password" id="confirmPw" />
							</label>
							<!-- You should use a button here, as the anchor is only used for the example  -->
							<button
								class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
								id="btn">비밀번호 변경</button>
						</form>
						<c:if test="${active != 4}">
							<a
								class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
								href="${pageContext.request.contextPath}/memberIndex">
								다음에 변경하기 </a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$('#pwck').click(function(){
		$.ajax({
			type:'get'
		   ,url:'/lms/pwCheck'
		   ,data:{memberId:$('#memberId').val()
			   	 ,password:$('#password').val()}
		   ,success:function(ck){
			   console.log('ck:',ck);
			   if(ck=='false') {
				   Swal.fire('이미 사용한 비밀번호 입니다');
				   $('#password').val('');
				   return false;
			   } else if(!/^[a-zA-z0-9]{8,16}$/.test($('#password').val())) {
					Swal.fire("영문, 숫자로 8자리 이상 입력하세요.");
					$('#password').focus();
					return false;
				} else {
				  Swal.fire('사용가능한 비밀번호 입니다');
			   }
		   }
		});
	});
	$('#confirmPw').blur(function(){
		if($('#password').val() != ''){
			if($('#password').val() != $('#confirmPw').val()) {
				Swal.fire("비밀번호가 일치하지 않습니다.");
				$('#password').focus();
				return false;
			}
		}
	});
	$('#btn').click(function(){
		if($('#password').val()== '') {
			Swal.fire("비밀번호를 입력해주세요");
			$('#password').focus();
			return false;
		} else if ($('#password').val() != $('#confirmPw').val()){
			Swal.fire("비밀번호가 일치하지않습니다");
			return false;
		} else {
			$('#pwForm').submit();
		}
	});
</script>
</html>
