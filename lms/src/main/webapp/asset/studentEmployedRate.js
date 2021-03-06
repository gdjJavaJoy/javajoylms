$.ajax({
	type: 'get',
	url: '/lms/studentEmployedRate',
	success: function(jsonData) {
		var totalStudentCount = jsonData.totalStudentCount;
		var totalEmployedStudentCount = jsonData.totalEmployedStudentCount;
		var employedAvg = jsonData.employedAvg;
		document.getElementById('employedAvg').innerHTML = employedAvg + '%';

		console.log(totalStudentCount + 'totalStudentCount');
		console.log(totalEmployedStudentCount + 'totalEmployedStudentCount');
		console.log(employedAvg + 'employedAvg');
		
		const ctx = document.getElementById('studentEmployedRate').getContext('2d');
		const myChart = new Chart(ctx, {
		    type: 'pie',
		    data: {
		        labels: ['취업학생', '미취업학생'],
		        datasets: [{
		            data: [totalEmployedStudentCount, totalStudentCount - totalEmployedStudentCount],
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