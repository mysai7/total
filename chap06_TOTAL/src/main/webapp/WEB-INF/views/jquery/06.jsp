<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 id="t">JQuery Animation effect</h3>
<div>
	<button id="bt1">HIDE/SHOW</button>
	<button id="bt2">FADE IN/OUT</button>
	<button id="bt3">SLIDE UP/DOWN</button>
	<button id="bt4">ANIMATE</button>
</div>
<div style="height: 150px; background: silver;" id="tg">
	<span>아무거나,,</span>
</div>
<div style="height: 150px; background: fuchsia; display: none;">
	<span>아무거나,,</span>
</div>
<script>
	$("#bt4").click(function(){
		$("#tg").animate({
			"height":300
		}).animate({
			"font-size":"30pt"
		}).slideUp();
	});

	$("#bt3").click(function(){
		//$("#tg").slideUp();
		//$("#tg").next().slideDown();
		//$("#tg").next().fadeIn();
		//$("#t").fadeToggle();
	});

	$("#bt2").click(function(){
		$("#tg").fadeOut();
		$("#tg").next().fadeIn();
		$("#t").fadeToggle();
	});

	$("#bt1").click(function(){
		// hide, show, toggle
		$("#tg").hide(1000);
		$("#tg").next().show(1000);
		$("#t").toggle();
	});
</script>