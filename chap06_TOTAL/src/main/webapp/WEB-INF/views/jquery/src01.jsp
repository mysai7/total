<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>JQuery Simple AJAX</h3>
<div>
	<button id="a">AJAX</button>
	<button id="b">AJAX</button>
</div>
<script>
	$("#a").click(function(){
		$.ajax({
			"type" : "post",	// default : "get"
			"async" : false,	// default : true
			"url" : "/jquery/dst02",
			"data" : {
				"id" : "saan",
				"pw" : "1q2w3e"
			}
		}).done(function(r){
			console.log(r+" / "+typeof r);
			var obj = JSON.parse(r);
			window.alert(obj.name+" / "+obj.gender);
			//window.alert(r.name+" / "+r.gender);
		});
	});
	
	$("#b").click(function(){
		$.ajax({
			"url" : "/jquery/dst03"
		}).done(function(r){
			console.log(r+" / "+typeof r);
			window.alert(r.name+" / "+r.gender);
		});
	});
</script>