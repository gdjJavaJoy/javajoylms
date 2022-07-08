var arr;
$.ajax({
	type: 'get',
	url: '/lms/studentEducationRate',
	success: function(jsonData) {
		let a = [];
		let b = [];
		arr = jsonData;
		console.log(arr);
		for (i = 0; i < jsonData.length; i++) {
			a.push(arr[i].studentEducation);
			b.push(arr[i].educationCount);
			console.log(a + 'studentEducation');
			console.log(b + 'educationCount');
			new Chart(document.getElementById("studentEducationRate"),
				{
					type: 'bar',
					data: {
						labels: a,
						datasets: [{
							label: '학생 수',
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
									labelString: '학력'
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
									labelString: '학력'
								}
							}]
						}
					}
				});
		};
	}
})