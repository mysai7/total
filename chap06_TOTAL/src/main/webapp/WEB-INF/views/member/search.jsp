<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div align="center">
	<h3>친구 찾기</h3>
	<small>찾고깊은 친구의 ID를 검색해보세요.</small>
	<p>
		<input type="text" id="srch"/>
	</p>
	<div id="rst">
	</div>
</div>
<script>
	// keypress, keydown, keyup
	$("#srch").keyup(function(){
		if($("#srch").val().length == 0){
			return;
		}
		$.ajax({
			"url" : "/member/rst",
			"data" : {
				"search" : $("#srch").val()
			}
		}).done(function(r){
			//$("#rst").append(r);
			
			for(var i=0; i<r.length; i++){
				var h = "<b>"+r[i].ID+"</b>("+r[i].EMAIL+")<br/>";
				$("#rst").append(h);
			}
			
		});
	});
</script>