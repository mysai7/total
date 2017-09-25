<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
</style>
<h3>JQuery 연습#2</h3>
아래 선택된 항목에 따라 총 상품금액을 계산해서 출력하는 script 설정
<hr />
<div>
	<h3>삼성노트북 코어 i5</h3>
	판매가 : <span id="price">656,000</span> <hr/>
	옵션 :
	<ul style="list-style: none;">
		<li><input class="opt" data="24000" type="checkbox" />RAM 추가 (+24,000)</li>
		<li><input class="opt" data="34000" type="checkbox" />HDD추가 (+34,000)</li>
		<li><input class="opt" data="22000" type="checkbox" />외장그래픽카드추가 (+22,000)</li>
		<li><input class="opt" data="42000" type="checkbox" />OS포함 (+42,000)</li>
	</ul>
	<hr />
	수량 :
	<button id="m">-</button>
	<input id="c" type="number" style="width: 50px;" value="1" min="1" />
	<button id="p">+</button>
	<hr />
</div>
<div>전체 상품 총액 : <span id="t">656,000</span></div>
<script>
	var osum = 0;
	$(".opt").change(function(){
	//	console.log(typeof $(this).attr("data"));
	//	console.log($(this).attr("data") + 100);
	//	console.log(typeof  parseInt($(this).attr("data"))  );
	//	console.log(parseInt($(this).attr("data")) + 100 );
		if($(this).prop("checked")){
			osum += parseInt($(this).attr("data"));
			console.log(osum);
		}else{
			osum -= parseInt($(this).attr("data"));
			console.log(osum);
		}
	});
</script>