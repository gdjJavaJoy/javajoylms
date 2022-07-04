<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>modifyStudentOne</title>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="./public/assets/js/init-alpine.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
	<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
		:class="{ 'overflow-hidden': isSideMenuOpen}">
		<!-- sidebar -->
		<aside class="z-20 flex-shrink-0 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block">
			<div id="studentSideNav"></div>
		</aside>
		<div class="flex flex-col flex-1 w-full">
			<div id="studentHeaderNav"></div>
			<main class="h-full pb-16 overflow-y-auto">
				<div class="container grid px-6 mx-auto">
					<h2
						class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
						회원정보 수정</h2>
					<c:choose>
	           	 <c:when test="${student.memberPhotoName != null}">
	            <img
	                 class="object-cover w-350 h-350 rounded-full"
	                 src="${pageContext.request.contextPath}/file/memberPhoto/${student.memberPhotoName}"
	                 style="width:350px; height:350px;"
	                 alt=""
	                 loading="lazy"
	            />
	            </c:when>
	            <c:otherwise>
	             <img
	                 class="object-cover w-350 h-350 rounded-full"
	                 src="https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjE3Nzg0fQ"
	                 alt=""
	                 loading="lazy"
	                 />
	            </c:otherwise>
            </c:choose>
             <form method="post" action="${pageContext.request.contextPath}/addMemberPhoto" enctype="multipart/form-data">
            <input type="file" name="memberPhotoList" id="memberPhotoList">
            <input type="text" hidden="hidden" name="memberId" id="memberId" value="${student.memberId}"> 
             <button
                  class="px-3 py-1 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-md active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
              	  id="updatePhotoBtn"
              	  type="submit"
                >
                  사진수정
                </button>
                </form>
					<!-- With avatar -->
					<h4
						class="mb-4 text-lg font-semibold text-gray-600 dark:text-gray-300">
						${loginUser}님의 정보</h4>
					<div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
						<div class="w-full overflow-x-auto">
							<form action="${pageContext.request.contextPath}/modifyStudentOne" method="post" id="updateForm">
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
													<input type="hidden" name="memberId" value="${student.memberId}">
													<p class="font-semibold">${student.memberId}</p>
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
												<input
													class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
													type="text" value="${student.studentName}"
													name="memberName" id="memberName">
											</div>
										</td>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">성별</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<div>
						                            <p class="font-semibold"> 
						                              <label
						                          class="inline-flex items-center text-gray-600 dark:text-gray-400"
						                        >
						                          <input
						                            type="radio"
						                            class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
						                            name="memberGender"
						                            value="남"
						                            <c:if test="${student.studentGender == '남'}">checked</c:if>
						                          />
						                          <span class="ml-2">남</span>
						                        </label>
						                        <label
						                          class="inline-flex items-center ml-6 text-gray-600 dark:text-gray-400"
						                        >
						                          <input
						                            type="radio"
						                            class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
						                            name="memberGender"
						                            value="여"
						                            <c:if test="${student.studentGender == '여'}">checked</c:if>
						                          />
						                          <span class="ml-2">여</span>
						                        </label>
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
												<input
													class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
													type="text" value="${student.studentPhone}"
													name="memberPhone" id ="memberPhone">
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
												<input
													class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
													type="text" value="${student.studentEmail}"
													name="memberEmail" id="memberEmail">
											</div>
										</td>
									</tr>
									<tr class="text-gray-700 dark:text-gray-400">
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<div>
													<p class="font-semibold">최종학력</p>
												</div>
											</div>
										</td>
										<td class="px-4 py-3">
											<div class="flex items-center text-sm">
												<!-- Avatar with inset shadow -->
												<select
													class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
													name="memberEducation" id="memberEducation">
													<option value="${student.studentEducation}">현재 저장된
														학력 : ${student.studentEducation}</option>
													<option value="고졸">고졸</option>
													<option value="초대졸">초대졸</option>
													<option value="대졸">대졸</option>
												</select>
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
												<!-- Avatar with inset shadow -->
												<input
													class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
													type="text" value="${student.studentAddress}"
													name="currentMemberAddress" id="keyword">
												<button
													class="px-3 py-1 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-md active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
													type="button" id="updateAddr">주소 변경</button>
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
												<input
													class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
													type="text" value="${student.studentDetailAddress}"
													name="memberDetailAddress" id="memberDetailAddress">
											</div>
										</td>
									</tr>
								</table>
								<button type="button"
									class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
									id="submitBtn">회원정보 수정</button>
							</form>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
<script>
	$('#studentSideNav').load('${pageContext.request.contextPath}/include/studentSideNav.jsp');
	$('#studentHeaderNav').load('${pageContext.request.contextPath}/include/studentHeaderNav.jsp');

	$('#updateAddr').click(function(){
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
		console.log($('#memberName').val())
		if($('#memberName').val() == '') {
			Swal.fire('이름을 입력해주세요');
			return;
		} else if ($('#memberGender').val() == '') {
			Swal.fire('성별을 선택해주세요');
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
		} else if ($('#memberEducation').val() == '') {
			Swal.fire('학력을 선택해주세요');
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
