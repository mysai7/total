<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="jumbotron" style="margin-bottom: 0px">
	<h1>
		<a href="/">SPRING WEB MVC</a>
	</h1>
	<p id="alt">
	
	</p>
</div>
<script>
	//var audio = new Audio("/alert.mp3");
	//audio.play();
	var aws = new WebSocket("ws://192.168.10.66/ws/alert");
	aws.onmessage = function(e){
		//window.alert(e.data+" / "+typeof e.data);
		document.getElementById("alt").innerHTML = e.data;
	//	var audio = new Audio("/join.wav");
	//	audio.play();
	}
	document.getElementById("alt").onclick = function() {
		this.innerHTML="";
	}
</script>