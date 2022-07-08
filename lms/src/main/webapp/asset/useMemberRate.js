$.ajax({
	type: 'get',
	url: '/lms/useMemberRate',
	success: function(jsonData) {
		var active = jsonData.active;
		var nonActive = jsonData.nonActive;

		console.log(active + 'active');
		console.log(nonActive + 'nonActive');
		
		const ctx = document.getElementById('useMemberRate').getContext('2d');
		const myChart = new Chart(ctx, {
		    type: 'pie',
		    data: {
		        labels: ['활성화', '비활성화'],
		        datasets: [{
		            data: [active, nonActive],
		            datalables:{
						color:'black',
						font:{size:24}
					},
		            backgroundColor: [
		                'rgba(255, 99, 132, 0.2)',
		                'rgba(54, 162, 235, 0.2)'
		            ],
		            borderColor: [
		                'rgba(255, 99, 132, 1)',
		                'rgba(54, 162, 235, 1)'],
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
	}
});