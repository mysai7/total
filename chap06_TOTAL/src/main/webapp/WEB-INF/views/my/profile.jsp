<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.bt{
	padding: 3pt;
	width: 50px;
}
</style>
<div align="center">
	<h3>프로필 관리</h3>
	<div>
		<p>
			<img id="pre" src="${map.URL eq null ? '/profiles/default.jpg' : map.URL }" alt="기본이미지" style="width: 200; height: 200"/>
		</p>
		<form action="/my/profile" method="post" enctype="multipart/form-data">
			<input id="profile" type="file" name="profile" style="display: none;"/>
			<input type="text" name="nick">
			<button type="submit" class="bt">적용</button>
			<button type="button" class="bt" onclick="javascript:location.reload()">취소</button>
		</form>
	</div>
</div>
<script>
	document.getElementById("pre").onclick = function(){
		document.getElementById("profile").click();
	}
	document.getElementById("profile").onchange = function(){
		//console.log(this.files[0]);
		//console.log("변경발생");
		var reader = new FileReader();
		reader.onload = function(e){
			//console.log(e.target.result);
			document.getElementById("pre").src = e.target.result;
		}
		reader.readAsDataURL(this.files[0]);
	}
</script>