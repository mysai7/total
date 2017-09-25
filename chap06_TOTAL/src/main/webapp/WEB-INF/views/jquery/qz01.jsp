<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>JQuery 연습#1</h3>
<hr/>
<input type="checkbox" id="all"/>
<hr/>
<c:forEach var="i" begin="10" end="19">
	<input type="checkbox" class="item" value="${i }"/>${i }........<br/>
</c:forEach>
<hr/>
<button id="move">휴지통으로</button>
<script>
	$("#move").click(function(){
		// length property 를 이용하는 방식.
		// JQuery로 객체(들) 선택시 length라는 변수를 갖게 됨.
		for(var i=0; i<$(".item").length; i++){
			// $(".item")[i] X
			// get 이나, eq (★) 를 사용하면 됨.
			console.log($(".item").eq(0));	// JQuery 객체
			console.log($(".item").get(0));	// HTML DOM
			console.log("======================================");
			if($(".item").eq(i).prop("checked")){
				window.alert($(".item").eq(i).val());
			}
			/*
			if($(".item").get(i).checked){
				window.alert($(".item").get(i).value);
			}
			*/
		}
	});

	$("#all").change(function(){
		$(".item").prop("checked", $(this).prop("checked"));
	});
	
	/*
	$("#move").click(function(){
		var ar = [];
		$(".item").each(function(){
			if($(this).prop("checked")){
				ar.push($(this).val());
			}
		});
		window.alert(JSON.stringify(ar));
	});
	*/
</script>