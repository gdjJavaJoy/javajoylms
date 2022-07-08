$.ajax({
	type: 'get',
	url: '/lms/employedStudentFirstSalaryRate',
	success: function(jsonData) {
		var salarayOne = jsonData.salarayOne;
		var salarayTwo = jsonData.salarayTwo;
		var salarayThree = jsonData.salarayThree;
		var salarayFour = jsonData.salarayFour;
		var salarayFive = jsonData.salarayFive;

		console.log(salarayOne + 'salarayOne');
		console.log(salarayTwo + 'salarayTwo');
		console.log(salarayThree + 'salarayThree');
		console.log(salarayFour + 'salarayFour');
		console.log(salarayFive + 'salarayFive');
		
		const ctx = document.getElementById('employedStudentFirstSalaryRate').getContext('2d');
		const myChart = new Chart(ctx, {
		    type: 'pie',
		    data: {
		        labels: ['2000미만', '2000~3000','3000~3500','3500~4000','4000이상'],
		        datasets: [{
		            data: [salarayOne, salarayTwo,salarayThree,salarayFour,salarayFive],
		            datalables:{
						color:'black',
						font:{size:24}
					},
		            backgroundColor: [
		                'rgba(255, 99, 132, 0.2)'
		                ,'rgba(54, 162, 235, 0.2)'
		                ,'rgba(20, 162, 225, 0.2)'
		                ,'rgba(10, 162, 135, 0.2)'
		                ,'rgba(28, 162, 285, 0.2)'
		                ,'rgba(9, 162, 205, 0.2)'
		            ],
		            borderColor: [
		                'rgba(255, 99, 132, 1)'
		                ,'rgba(54, 162, 235, 1)'
		                ,'rgba(10, 162, 135, 0.2)'
		                ,'rgba(28, 162, 285, 0.2)'
		                ,'rgba(9, 162, 205, 0.2)'],
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