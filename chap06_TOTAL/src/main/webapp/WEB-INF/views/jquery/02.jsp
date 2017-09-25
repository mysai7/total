<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
input, button{
	height: 25px;
}
</style>
<h3 id="t">JQuery</h3>
JQuery로 element가 가지고 있는 정보를 조금 쉽게 얻거나 설정하는 메서드를 제공함.
<ul>
	<li>innerHTML --> html()</li>
	<li>value --> val()</li>
	<li>그외 attribute들 --> attr()</li>
	<li>특수 attribute들 --> prop() /
		(true,false설정하는 attr들 : disabled, checked, readonly)</li>
	<li>style --> css()</li>
</ul>
<input type="text" id="txt" class="i" maxlength="8" readonly/>
<input type="text" class="i"/>
<hr/>
<input type="email" id="e" class="i" size="40"/>
<input type="checkbox" id="cb" checked/>체크박스
<hr/>
<button id="bt1" style="border-radius: 5px;">테스트1</button>
<button id="bt2" disabled>테스트2</button>
<button id="bt3">테스트3</button>
<button id="bt4">테스트4</button>
<button id="bt5">테스트5</button>
<script>
	$("#bt5").click(function(){
		window.alert($("#bt1").css("border-radius"));
		$("button").css("color", "white");
		$("button").css("background", "black");
	});

	$("#bt4").click(function(){
		var pc = $("#cb").prop("checked");
		window.alert(pc+" / "+typeof pc);
		$("#cb").prop("checked", false);	
		$("#bt1").prop("disabled", true);
		window.alert($("#bt2").prop("disabled"));
		$("#bt2").prop("disabled", !$("#bt2").prop("disabled"));
		$("#txt").prop("readonly", false);
	});
	
	$("#bt3").click(function(){
		var t = $("#txt").attr("maxlength");
		window.alert(t+" / "+typeof t);
		window.alert($("#e").attr("class"));
		//=====================================
		$(".i").attr("type", "checkbox");
	});
	
	$("#bt2").click(function(){
		window.alert($(".i").val());	// 얻어내는건, 그룹이라면 0번째가 사용됨.()
		$(".i").val("");	// 설정하는건 그룹으로 처리가 됨
	});
	
	$("#bt1").click(function(){
		var val = $("#txt").val();
		$("#t").html(val);
		$("#txt").val("");
	});
/*
	$("#bt1").click(function(){
		window.alert($("#t").html());
		$("#t").html("제이쿼리");
	});
*/
</script>