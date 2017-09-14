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
		</p>
		<button id="bt" type="submit" style="width: 120px;" >가입</button>
	</form>
</div>
<script>
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
		}else{
			document.getElementById("irst").innerHTML = "";
		}
		xhr.open("post", "/member/signup_check/id/"+id, true);
		xhr.send();
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
		}else{
			document.getElementById("erst").innerHTML = "";
		}
		xhr.open("post", "/member/signup_check/email/"+email, true);
		xhr.send();
	}
</script>
