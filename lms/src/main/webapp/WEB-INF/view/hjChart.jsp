
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Charts - Windmill Dashboard</title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="./public/assets/css/tailwind.output.css" />
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="./public/assets/js/init-alpine.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"
	defer></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<main class="h-full pb-16 overflow-y-auto">
		<div class="container px-6 mx-auto grid">
			<h2
				class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
				Charts</h2>
			<!-- Bars chart -->
			<div
				class="min-w-0 p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800">
				<h4 class="mb-4 font-semibold text-gray-800 dark:text-gray-300">
					Bars</h4>
				<canvas id="bars"></canvas>
			</div>
		</div>
	</main>
</body>
<script>
	var arr;
	$.ajax({
		type : 'get',
		url : '/lms/reportCountBySubject',
		success : function(jsonData) {
			let a = [];
			let b = [];
			arr = jsonData;
			console.log(arr);
			for (i = 0; i < jsonData.length; i++) {
				a.push(arr[i].subjectName);
				b.push(arr[i].reportCount);
				console.log(a + 'name');
				console.log(b + 'count');
				new Chart(document.getElementById("bars"),
					{ 
					type : 'bar',
					data : {
							labels : a,
							datasets : [ {
								label : '과제 개수',
								data : b,
								borderColor : "rgba(255, 201, 14, 1)",
								backgroundColor : "rgba(255, 201, 14, 0.5)",
								fill : false,
								} ]
							},
							options : {
								responsive : true,
								title : {
									display : true,
									text : '막대 차트 테스트'
								},
								tooltips : {
									mode : 'index',
									intersect : false,
									callbacks : {
										title : function(tooltipItems,data) {
											return data.labels[tooltipItems[0].datasetIndex];
										}
									}
								},
								hover : {
									mode : 'nearest',
									intersect : true
								},
								scales : {
									xAxes : [ {
										display : true,
										scaleLabel : {
											display : true,
											labelString : '강좌이름'
										},
										ticks : {
											autoSkip : false
										}
									} ],
									yAxes : [ {
										display : true,
										ticks : {
											suggestedMax: 30,
											suggestedMin : 0,
											beginAtZero: true 
										},
										scaleLabel : {
											display : true,
											labelString : '과제개수'
									}
								} ]
							}
						}
					});
				};
			}
		})
</script>
</html>