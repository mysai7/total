<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div align="center">
	<h2>회원가입</h2>
	<form action="/member/join" method="post">
		<p>
			<b>ID</b> <input id="id" type="text" name="id" required autocomplete="off"/><br/>
			<span id="irst"></span>
		</p>
		<p>
			<b>PASS</b> <input type="password" name="pass" required/><br/>
		</p>
		<p>
			<b>EMAIL</b> <input id="email" type="email" name="email" required autocomplete="off"/><br/>
			<span id="erst"></span>
		<p>
			<button id="auth" type="button" style="width: 120px;">이메일 인증</button>
		</p>
		<p id="auth_view" style="display: none;">
			<b>AUTHORIZED KEY</b> <small id="left" style="color: red; font-weight: bold"></small><br />
			<input	id="authkey" type="text" name="email" required />
		</p>
		<button id="bt" type="submit" style="width: 120px;" disabled >가입</button>
	</form>
</div>
<script>
	var tot;
	var time;
	document.getElementById("auth").onclick = function() {
		if (document.getElementById("email").value.length != 0) {
			var email = document.getElementById("email").value;
			var xhr = new XMLHttpRequest();
			xhr.open("post", "/member/email_check/", true);
			xhr.send(email);
			xhr.onreadystatechange = function() {
				if (this.readyState == 4) {
					var obj = this.responseText;
					if(obj == "true"){
						window.alert(email + "\n의 메일 주소로 인증키가 발송되었습니다.");
						document.getElementById("auth").style.display = "none";
						document.getElementById("auth_view").style.display = "";
						tot = 180;
						time = setInterval(limit, 1000);
					}
				}
			}
		}
	}
	
	document.getElementById("authkey").onchange = function(e) {
		var key = document.getElementById("authkey").value;
		var param = "key=" + key;
		var xhr = new XMLHttpRequest();
		xhr.open("post", "/member/join/authCheck?" + param, true);
		// 파라미터 방식이 아닌 post requestBody로 처리하는게 가능하기에
		xhr.send(); // 이메일만 넘겨보는 방식
		xhr.onreadystatechange = function() {
			if (this.readyState == 4) {
				if (this.responseText == "YYYYY") {
					window.alert("인증에 성공하였습니다.");
					clearInterval(time);
					document.getElementById("auth_view").style.display = "none";
					document.getElementById("email").readOnly = true;
					document.getElementById("bt").disabled = false;
				} else {
					window.alert("잘못된 인증키를 입력하셨습니다.");
				}
			}
		}
	}
	
	//document.getElementById("authkey").onkeyup = function(){
	//	document.getElementById("bt").disabled = false;
//	}
	
	var limit = function() {
		var m = Math.floor(tot / 60);
		var s = tot % 60;
		console.log(m + "/" + s);
		document.getElementById("left").innerHTML = m + ":"
				+ (s < 10 ? "0" + s : s);
		tot--;
		console.log(tot);
		if(tot<0) {
			window.alert("인증시간이 초과되었습니다.");
			clearInterval(time);
			document.getElementById("auth").style.display = "";
			document.getElementById("auth_view").style.display = "none";
		}
	}
//========================================================================================
	document.getElementById("id").onkeyup = function(){
		var id = document.getElementById("id").value;
		if(id.length != 0){
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(this.readyState == 4){
					var obj = this.responseText;
					document.getElementById("irst").innerHTML = obj;						
				}
			}
		xhr.open("post", "/member/signup_check/id", true);
		xhr.send(id);
		}else{
			document.getElementById("irst").innerHTML = "";
		}
	}
	
	document.getElementById("email").onkeyup = function(){
		var email = document.getElementById("email").value;
		if(email.length != 0){
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if(this.readyState == 4){
					var obj = this.responseText;
					document.getElementById("erst").innerHTML = obj;						
				}
			}
		xhr.open("post", "/member/signup_check/email", true);
		xhr.send(email);
		}else{
			document.getElementById("erst").innerHTML = "";
		}
	}
</script>
