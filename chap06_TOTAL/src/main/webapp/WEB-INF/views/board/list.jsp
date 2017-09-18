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
	<h2>게시판</h2>
	<p align="right" style="margin-right: 30px;">
		총 <b>${all.size() }</b> 개의 글이 등록되어있습니다.
	</p>
	<table style="width: 95%">
		<thead>
			<tr>
				<th style="width: 10%">글번호</th>
				<th style="width: 60%">글제목</th>
				<th style="width: 20%">작성자</th>
				<th style="width: 10%">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="obj" items="${all }">
				<tr>
					<td>${obj.NUM }</td>
					<td>
						<a href="/board/view/${obj.NUM}">${fn:substring(obj.TITLE, 0, 12) } <c:if test="${obj.C ne null }">[${obj.C }]</c:if></a>
					</td>
					<td>${obj.WRITER }</td>
					<td><fmt:formatNumber value="${obj.COUNT }" pattern="#,###"/>  </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p align="right" style="margin-right: 30px;">
		<a href="/board/add"><button type="button">글작성</button></a>
	</p>
</div>