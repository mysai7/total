<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
th {
	border-bottom: 1px solid;
	text-align: left;
}

th, td {
	padding: 10px;
}

input {
	font-family: 맑은 고딕;
	padding: 3px;
}
button {
	padding: 7px;
}
</style>
<div align="center">
	<h2>경매물품들</h2>
	<table style="width: 80%">
		<thead>
			<tr>
				<th style="width: 50%">#상품명</th>
				<th style="width: 20%">#최종입찰가</th>
				<th style="width: 15%">#시작가</th>
				<th style="width: 15%">#즉구가</th>
				<th style="width: 20%">#종료일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="obj" items="${data }">
				<tr>
					<td><a href="/market/view/${obj.NUM }" >${obj.INAME } <small>(<fmt:formatNumber
								value="${obj.GAB }" pattern="#,##0.00" />일 남음)
					</small></a></td>
					<td id="p_${obj.NUM }"><fmt:formatNumber value="${obj.PRICE }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${obj.SMONEY }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${obj.DMONEY }" pattern="#,###" /></td>
					<td><c:if test="${!empty obj.EDATE }">~<fmt:formatDate
								value="${obj.EDATE }" pattern="yyyy.MM.dd" />
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p align="right" style="margin-right: 30px;">
		<a href="/market/add"><button type="button">상품등록</button></a>
	</p>
	<form action="/market/search">
		최고가 <input type="number" name="search" required style="width: 100px;"
			value="${param.search }"> 이하 (<input type="checkbox"
			name="mode" ${param.mode ne null ? 'checked':'' } />즉구가능물품만)
		<button type="submit">검색</button>
	</form>
</div>

