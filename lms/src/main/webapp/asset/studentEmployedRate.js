var arr;
$.ajax({
	type: 'get',
	url: '/lms/studentEmployedRate',
	success: function(jsonData) {
		var totalStudentCount = jsonData.totalStudentCount;
		var totalEmployedStudentCount = jsonData.totalEmployedStudentCount;
		var employedAvg = jsonData.employedAvg;

		console.log(totalStudentCount + 'totalStudentCount');
		console.log(totalEmployedStudentCount + 'totalEmployedStudentCount');
		console.log(employedAvg + 'employedAvg');
		
		const ctx = document.getElementById('studentEmployedRate').getContext('2d');
		const myChart = new Chart(ctx, {
		    type: 'doughnut',
		    data: {
		        labels: ['취업학생', '미취업학생'],
		        datasets: [{
		            label: '테스트',
		            data: [totalEmployedStudentCount, totalStudentCount - totalEmployedStudentCount],
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
		Chart.pluginService.register({
			beforeDraw: function(chart) {
		    var width = chart.chart.width,
		        height = chart.chart.height,
		        ctx = chart.chart.ctx;
		    ctx.restore();
		    var fontSize = (height / 120).toFixed(2);
		    ctx.font = fontSize + "em sans-serif";
		    ctx.textBaseline = "middle";
		    var text = employedAvg + "%",
		        textX = Math.round((width - ctx.measureText(text).width) / 2),
		        textY = height / 2;
		    ctx.fillText(text, textX, textY);
		    ctx.save();
		  }
		});
	}
});