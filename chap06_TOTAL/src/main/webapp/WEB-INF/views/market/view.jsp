<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<form action="/market/add" method="post">
			<p>
				물품명 : ${data.INAME }
			</p>
			<p>
				시작가 : ${data.SMONEY }
			</p>
			<p>
				즉구가 : ${data.DMONEY }
			</p>
			<p>
				마감일 : ${data.EDATE }
			</p>
			<hr />
			<c:if test="${auth ne null }">
				<p>
					입찰가 : <input type="number" name="smoney" /> <button type="submit">입찰하기</button>
				</p>
			</c:if>
			<button type="button" onclick="javascript:location.href='/market/all'">전체 목록</button>
		</form>
	</div>
</div>



