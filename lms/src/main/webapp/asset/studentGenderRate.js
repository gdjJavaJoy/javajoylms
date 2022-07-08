$.ajax({
	type: 'get',
	url: '/lms/studentGenderRate',
	success: function(jsonData) {
		var totalStudentCount = jsonData.totalStudentCount;
		var totalStudentOfMan = jsonData.totalStudentOfMan;
		var totalStudentOfWoman = jsonData.totalStudentOfWoman;
		var studentGenderRate = jsonData.studentGenderRate;
		document.getElementById('studentGenderAvg').innerHTML = studentGenderRate + '%';

		console.log(totalStudentCount + 'totalStudentCount');
		console.log(totalStudentOfMan + 'totalStudentOfMan');
		console.log(totalStudentOfWoman + 'totalStudentOfWoman');
		console.log(studentGenderRate + 'studentGenderRate');
		
		const ctx = document.getElementById('studentGenderRate').getContext('2d');
		const myChart = new Chart(ctx, {
		    type: 'pie',
		    data: {
		        labels: ['남자', '여자'],
		        datasets: [{
		            data: [totalStudentOfMan, totalStudentOfWoman],
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