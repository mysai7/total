<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
th {
	border-bottom: 1px solid;
	text-align: left;
}
th, td {
	padding: 10px;
}
</style>
<div align="center" style="line-height: 35px">
	<h2>주차장 정보</h2>
	<p align="right" style="margin-right: 30px;">
		총 <b>${obj.GetParkInfo.list_total_count }</b> 개의 정보가있습니다.
	</p>
	<table style="width: 80%">
		<thead>
			<tr>
				<th>주차장명</th>
				<th>주소</th>
				<th>전화번호</th>
				<th>기본 주차 요금(원)</th>
				<th>기본 주차 시간(분 단위)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="data" items="${obj.GetParkInfo.row }">
				<tr>
					<td>${data.PARKING_NAME }</td>
					<td>${data.ADDR }</td>
					<td>${data.TEL }</td>
					<td><fmt:formatNumber value="${data.RATES }" pattern="#,###"/></td>
					<td><fmt:formatNumber value="${data.TIME_RATE }" pattern="#,###"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p align="right" style="margin-right: 30px;">
		<a href="/board/add"><button type="button"></button></a>
	</p>
</div>