<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 로그인 관련 입력 폼만 구축 -->
<div align="center">
	<h2>로그인</h2>
	<form action="/log/session" method="post">
		<P>
			<b>ID</b><br /><input type="text" name="id" required /><br /> 
		</P>
		<p>
			<b>PASS</b><br /> <input type="password" name="pass" required /><br />
		</p>
		<p>
			<input type="checkbox" name="keep" value="keep" id="ch" onchange="javascript:check()"/>로그인 유지
		</p>
		<p>
			<button type="submit" style="width: 169px;">LOGIN</button>
		</p>
	</form>
</div>
<script>
	function check(){
		if(document.getElementById("ch").checked){
			if(window.confirm("개인정보 보호를 위해 개인PC에서만 사용해 주세요\n계속 하시겠습니까?")) {
				document.getElementById("ch").checked = true;
			}else{
				document.getElementById("ch").checked = false;
			}
		}
	}
</script>
