<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>addMember</title>
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
  </head>
  <body>
    <div class="flex items-center min-h-screen p-6 bg-gray-50 dark:bg-gray-900">
      <div
        class="flex-1 h-full max-w-4xl mx-auto overflow-hidden bg-white rounded-lg shadow-xl dark:bg-gray-800"
      >
        <div class="flex flex-col overflow-y-auto md:flex-row">
          <div class="h-32 md:h-auto md:w-1/2">
            <img
              aria-hidden="true"
              class="object-cover w-full h-full dark:hidden"
              src="./public/assets/img/create-account-office.jpeg"
              alt="Office"
            />
            <img
              aria-hidden="true"
              class="hidden object-cover w-full h-full dark:block"
              src="./public/assets/img/create-account-office-dark.jpeg"
              alt="Office"
            />
          </div>
          <div class="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
            <div class="w-full">
              <h1
                class="mb-4 text-xl font-semibold text-gray-700 dark:text-gray-200"
              >
                Create account
              </h1>
               <form method="post" action="${pageContext.request.contextPath}/addMember">
              <label class="block mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
                  회원구분
                </span>
                <select
                  class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
               	  name="level"
               	  id="level"
                >
                  <option value="1">관리자</option>
                  <option value="2">강사</option>
                  <option value="3">학생</option>
                </select>
              </label>
              <label class="block text-sm">
                <span class="text-gray-700 dark:text-gray-400">아이디</span>
                <input
                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  placeholder="ID 입력"
                  id="idck"
                />
                <br>
                 <button
                  class="px-3 py-1 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-md active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                  type="button"
                  id="btn"
                >
                 ID중복검사
                </button>
              </label>
              <label class="block mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
                ID 
                </span>
                <input
                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  placeholder="ID"
                  type="text"
                  readonly="readonly"
                  name="memberId"
                  id="id"
                />
              </label>
              <label class="block mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
            	PASSWORD
            </span>
            <input
              class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
              placeholder="password"
              type="password"
              name="memberPw"
              id="pw"
            />
          </label>
			    <label class="block mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
                이름 
                </span>
                <input
                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  placeholder="이름 입력"
                  type="text"
                  name="memberName"
                  id="memberName"
                />
              </label>
               <label class="block mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
                전화번호 
                </span>
                <input
                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  placeholder="-을제외하고 입력해주세요"
                  type="number"
                  id="memberPhone"
                  name="memberPhone"
                />
              </label>
              <label class="block mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
                e-mail 
                </span>
                <input
                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  placeholder="email을 입력해주세요"
                  type="text"
                  id="memberEmail"
                  name="memberEmail"
                />
              </label>
              <div id="insertForm">
              </div>
              <label class="block mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
                주소입력
                </span>
                <input
                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  placeholder="주소 입력"
                  type="text"
                  name="keyword"
                  id="keyword"
                />
              </label>
              <br>
               <button
                  class="px-3 py-1 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-md active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
                  type="button"
                  id="addrBtn"
                >
                 주소 검색
                </button>
                <div id="list">
                </div>
                <br>
                <select
                  class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
               	  name="memberAddress"
               	  id="memberAddr"
                ></select>
              <!-- You should use a button here, as the anchor is only used for the example  -->
              <label class="block mt-4 text-sm">
                <span class="text-gray-700 dark:text-gray-400">
                상세주소
                </span>
                <input
                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
                  placeholder="상세주소"
                  type="text"
                  name="memberDetailAddress"
                  id="memberDetailAddr"
                />
              </label>
              <button 
              	type="submit"
                class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple"
              >
                Create account
              </button>
			 </form>
              <hr class="my-8" />
              <p class="mt-4">
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$('#level').change(function(){
	if($(this).val() =="1") {
		$('#insertForm').empty();
		$('#insertForm').append('<input type="text" name="active" hidden="hidden" value="1"');
	}	
	else if($(this).val() == "2"){
			  $('#insertForm').empty();
			  $('#insertForm').append('<div class="mt-4 text-sm">\
		                <span class="text-gray-700 dark:text-gray-400">\
		                  성별\
		                <span>\
		                <div class="mt-2">\
		                  <label\
		                    class="inline-flex items-center text-gray-600 dark:text-gray-400"\
		                  >\
		                    <input\
		                      type="radio"\
		                      class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"\
		                      name="gender"\
		                      value="남"\
		                    />\
		                    <span class="ml-2">남</span>\
		                  </label>\
		                  <label\
		                    class="inline-flex items-center ml-6 text-gray-600 dark:text-gray-400"\
		                  >\
		                    <input\
		                      type="radio"\
		                      class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"\
		                      name="gender"\
		                      value="여"\
		                    />\
		                    <span class="ml-2">여</span>\
		                  </label>\
		                </div>\
		                <label class="block mt-4 text-sm">\
		                <span class="text-gray-700 dark:text-gray-400">\
		                입사일 선택\
		                </span>\
		                <input\
		                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"\
		                  type="date"\
		                  name="memberJoin"\
		                />\
		              </label>\
		              </div>\
		              <input type="text" name="active" hidden="hidden" value="4">'
		              );
		  } else if ($(this).val() == "3") {
			  $('#insertForm').empty();
			  $('#insertForm').append('<div class="mt-4 text-sm">\
		                <span class="text-gray-700 dark:text-gray-400">\
	                  성별\
	                <span>\
	                <div class="mt-2">\
	                  <label\
	                    class="inline-flex items-center text-gray-600 dark:text-gray-400"\
	                  >\
	                    <input\
	                      type="radio"\
	                      class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"\
	                      name="gender"\
	                      value="남"\
	                    />\
	                    <span class="ml-2">남</span>\
	                  </label>\
	                  <label\
	                    class="inline-flex items-center ml-6 text-gray-600 dark:text-gray-400"\
	                  >\
	                    <input\
	                      type="radio"\
	                      class="text-purple-600 form-radio focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"\
	                      name="gender"\
	                      value="여"\
	                    />\
	                    <span class="ml-2">여</span>\
	                  </label>\
	                  <label class="block mt-4 text-sm">\
	                  <span class="text-gray-700 dark:text-gray-400">\
	                   최종학력\
	                  </span>\
	                  <select\
	                    class="block w-full mt-1 text-sm dark:text-gray-300 dark:border-gray-600 dark:bg-gray-700 form-select focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"\
	                 	  name="education"\
	                  >\
	                    <option value="고졸">고졸</option>\
	                    <option value="초대졸">초대졸</option>\
	                    <option value="대졸">대졸</option>\
	                  </select>\
	                </label>\
	                <label class="block mt-4 text-sm">\
	                <span class="text-gray-700 dark:text-gray-400">\
	                학원 등록일\
	                </span>\
	                <input\
	                  class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"\
	                  type="date"\
	                  name="memberJoin"\
	                />\
	              </label>\
	              </div>\
	              <input type="text" name="active" hidden="hidden" value="4">'
				 );
		  }
	});
	$('#btn').click(function(){
	if($('#idck').val().length > 3) {
		$.ajax({
			type:'post'
			,url:'/lms/idCheck'
			,data:{id:$('#idck').val()}
			,success:function(ck){
				console.log('ck:',ck);
				if(ck=='false') {
					alert('이미 사용중인 아이디 입니다');
				} else {
					$('#id').val(ck);
				}
			}
		});
	} else {
		alert('id는 4자 이상입력해주세요');
	}
	
});
	$('#addrBtn').click(function(){
		$.ajax({
			type:'get'
			,url:'/lms/getAddr'
			,data:{keyword:$('#keyword').val()}
			,success:function(a){
				console.log(a);
				console.log(typeof(a));
				var a2 = JSON.parse(a);
				console.log(typeof(a2));
				console.log(a2);
				
				let arr = a2.results.juso; // 주소배열
				console.log(arr);
				for (let i=0; i<arr.length; i++) {
					var addr = (arr[i].jibunAddr).replace(/\s/gi,"");
					var obj = $("<option value="+addr+">"+arr[i].jibunAddr+'('+arr[i].zipNo+")</option>");
					$('#memberAddr').append(obj);
				};
				
			}
		});
	});
</script>
</html> 
