var arr;
$.ajax({
	type: 'get',
	url: '/lms/reportCountBySubject',
	success: function(jsonData) {
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
					type: 'bar',
					data: {
						labels: a,
						datasets: [{
							label: '과제 개수',
							data: b,
							borderColor: "rgba(255, 201, 14, 1)",
							backgroundColor: "rgba(255, 201, 14, 0.5)",
							fill: false,
						}]
					},
					options: {
						responsive: true,
						tooltips: {
							mode: 'index',
							intersect: false,
							callbacks: {
								title: function(tooltipItems, data) {
									return data.labels[tooltipItems[0].datasetIndex];
								}
							}
						},
						hover: {
							mode: 'nearest',
							intersect: true
						},
						scales: {
							xAxes: [{
								display: true,
								scaleLabel: {
									display: true,
									labelString: '강좌이름'
								},
								ticks: {
									autoSkip: false
								}
							}],
							yAxes: [{
								display: true,
								ticks: {
									suggestedMax: 30,
									suggestedMin: 0,
									beginAtZero: true
								},
								scaleLabel: {
									display: true,
									labelString: '과제개수'
								}
							}]
						}
					}
				});
		};
	}
})