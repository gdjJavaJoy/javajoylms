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
	<script src="./asset/reportCountBySubject.js" defer></script> <!-- reportCountBySubject 차트 불러오기 -->
	<script src="./asset/studentEmployedRate.js" defer></script> <!-- studentEmployedRate 차트 불러오기 -->
</head>
<body>
	<main class="h-full pb-16 overflow-y-auto">
		<div class="container px-6 mx-auto grid">
			<h2
				class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">
				Charts</h2>
			<!-- CTA -->
			<a
				class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
				href="https://github.com/estevanmaito/windmill-dashboard">
				<div class="flex items-center">
					<svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20">
                  <path
							d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                </svg>
					<span>Star this project on GitHub</span>
				</div> <span>View more &RightArrow;</span>
			</a>

			<p class="mb-8 text-gray-600 dark:text-gray-400">
				Charts are provided by <a
					class="text-purple-600 dark:text-purple-400 hover:underline"
					href="https://www.chartjs.org/"> Chart.js </a> .
				Note that the default legends are disabled and you should provide a
				description for your charts in HTML. See source code for examples.
			</p>

			<div class="grid gap-6 mb-8 md:grid-cols-2">
				<!-- Doughnut/Pie chart -->
				<div
					class="min-w-0 p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800">
					<h4 class="mb-4 font-semibold text-gray-800 dark:text-gray-300">
						취업률</h4>
					<canvas id="studentEmployedRate"></canvas>
				</div>
				<!-- Lines chart -->
				<div
					class="min-w-0 p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800">
					<h4 class="mb-4 font-semibold text-gray-800 dark:text-gray-300">
						Lines</h4>
					<canvas id="line"></canvas>
					<div
						class="flex justify-center mt-4 space-x-3 text-sm text-gray-600 dark:text-gray-400">
						<!-- Chart legend -->
						<div class="flex items-center">
							<span class="inline-block w-3 h-3 mr-1 bg-teal-500 rounded-full"></span>
							<span>Organic</span>
						</div>
						<div class="flex items-center">
							<span
								class="inline-block w-3 h-3 mr-1 bg-purple-600 rounded-full"></span>
							<span>Paid</span>
						</div>
					</div>
				</div>
				<!-- Bars chart -->
				<div
					class="min-w-0 p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800">
					<h4 class="mb-4 font-semibold text-gray-800 dark:text-gray-300">
						강좌별 과제 개수</h4>
					<canvas id="bars"></canvas>
				</div>
				
			</div>
		</div>
	</main>
</body>
</html>