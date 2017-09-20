<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
input {
	font-family: 맑은 고딕;
	font-size: 10pt;
	padding: 3px;
	width: 250px;
}
</style>
<div align="center">
	<div style="width: 60%; line-height: 30px;">
		<h2>경매물품 상세보기</h2>
		<hr />
		<form action="/market/auction" method="post">
			<p>
				물품명 : ${data.INAME }<small> (<fmt:formatNumber value="${data.GAB }" pattern="#,##0.00" />일 남음)</small>
			</p>
			<p>
				시작가 : <fmt:formatNumber value="${data.SMONEY }" pattern="#,###" />
			</p>
			<p>
				즉구가 : <fmt:formatNumber value="${data.DMONEY }" pattern="#,###" />
			</p>
			<p>
				마감일 : ${data.EDATE }
			</p>
			<c:if test="${!empty data.Price or !empty auth}">
				<p>
					최종입찰가 : <fmt:formatNumber value="${data.PRICE }" pattern="#,###" />
				</p>
			</c:if>
			<hr />
			<c:if test="${auth ne null }">
				<input type="hidden" name="parent" value="${data.NUM }">
				<input type="hidden" name="id" value="${auth }">
				<span id="rst"></span>
				<p>
					입찰가 : <input id="p" type="number" name="price" /> <button id="bt" type="button" >입찰하기</button>
				</p>
			</c:if>
			<button type="button" onclick="javascript:location.href='/market/all'">전체 목록</button>
		</form>
	</div>
</div>
<script>
	document.getElementById("p").onchange = function(){
		var price = document.getElementById("p").value;
		var tot = ${data.PRICE};
		if( price < tot ){
			document.getElementById("rst").innerHTML = "<b style=\"color: red;\">최종입찰가 보다 적은 금액은 입찰이 불가능 합니다.</b>";
			location.reload();
		}else{
			document.getElementById("bt").type = submit;
		}
	}
</script>


