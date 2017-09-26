<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div align="center">
	<h3>친구 찾기</h3>
	<small>찾고깊은 친구의 ID를 검색해보세요.</small>
	<p>
		<input type="text" id="srch"/>
	</p>
	<p id="rst">
	</p>
</div>
<script>
	// keypress, keydown, keyup
	$("#srch").keyup(function(){
		var val = $(this).val();
		console.log(val);
		$.ajax({
			"url" : "/member/rst",
			"data" : {
				"search" : val 
			}
		}).done(function(r){
			for(var i=0; i<r.length; i++){
				console.log(r[i].ID);
			}
		});
	});
</script>