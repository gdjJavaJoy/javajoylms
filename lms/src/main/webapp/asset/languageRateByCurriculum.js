var arr;
$.ajax({
	type: 'get',
	url: '/lms/languageRateByCurriculum',
	success: function(jsonData) {
		let languageName = [];
		let count = [];
		arr = jsonData;
		console.log(arr);
		
		for (i = 0; i < jsonData.length; i++) {
			languageName.push(arr[i].languageName);
			count.push(arr[i].count);
			
			console.log(languageName + 'languageName');
			console.log(count + 'count');

			const ctx = document.getElementById('languageRateByCurriculum').getContext('2d');
			const myChart = new Chart(ctx, {
				type: 'pie',
				data: {
					labels: languageName,
					datasets: [{
						data: count,
						datalables: {
							color: 'black',
							font: { size: 24 }
						},
						backgroundColor: [
							'rgba(255, 99, 132, 0.2)'
							, 'rgba(54, 162, 235, 0.2)'
							, 'rgba(20, 162, 225, 0.2)'
							, 'rgba(10, 162, 135, 0.2)'
							, 'rgba(28, 162, 285, 0.2)'
							, 'rgba(9, 162, 205, 0.2)'
						],
						borderColor: [
							'rgba(255, 99, 132, 1)'
							, 'rgba(54, 162, 235, 1)'
							, 'rgba(10, 162, 135, 0.2)'
							, 'rgba(28, 162, 285, 0.2)'
							, 'rgba(9, 162, 205, 0.2)'],
						borderWidth: 1
					}]
				},
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				},
			});
		};
	}
});