<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div align="center">
	<h3>RESEARCH CHART</h3>
	<div id = "columnchart_values"style = "width : 900px; height : 300px;">
		
	</div>
</div>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'score');
		data.addColumn('number', 'count');
		
		$.ajax({
	    	  "url" : "/chart/researchAjax",
	    	  "async" : false
	      }).done(function(obj){
	    	  	console.log(obj);
				data.addRows(obj);
	      });
		
		//data.addRows([ [ 1, 4 ], [ 2, 10 ], [ 3, 5 ], [ 4, 20 ] ,[5, 0] ]);
		var options = {
			"title" : "점수별 인원수",
		};
		// ColumnChart , BarChart, PieChart
		var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
		chart.draw(data, options);
	}
</script>