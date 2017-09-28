<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<h2>전체 회원 목록</h2>
	<h4>회원수 : ${tot }명</h4>
	<table style="width:50%; text-align: center;">
	<c:forEach var="i" items="${list }">
		<tr>
			<td><img id="pre" src="${i.URL eq null ? '/profiles/default.jpg' : i.URL }" alt="기본이미지" style="width: 50; height: 50" /></td>
			<td><b>${i.ID }</b></td>
			<td><b>${i.EMAIL }</b></td>
		</tr>
	</c:forEach>
	</table>
	<div>
		<c:if test="${param.page gt 1 }">
			<a href="/my/list?page=${param.page -1 }" style="text-decoration: none">
				<b>◀</b></a>	
		</c:if>
		<c:forEach var="i" begin="${pb }" end="${pe }" varStatus="vs">
			<c:choose>
				<c:when test="${i eq param.page }">
					<b>${i }</b>
				</c:when>
				<c:otherwise>
					<a href="/my/list?page=${i }" style="text-decoration: none">${i }</a>
				</c:otherwise>
			</c:choose>
			<c:if test="${!vs.last }">|</c:if>
		</c:forEach>
		<c:if test="${param.page lt last }">
			<a href="/my/list?page=${param.page +1 }" style="text-decoration: none">
				<b>▷</b></a>
		</c:if>
	</div>
</div>
