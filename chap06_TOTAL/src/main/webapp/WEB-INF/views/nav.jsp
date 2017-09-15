<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="right" style="background: silver; padding: 10px;">
<c:choose>
	<c:when test="${auth eq null }">
		<div>
			<a href="/member/join">JOIN</a> |
			<a href="/log/login">LOGIN</a>			
		</div>
	</c:when>
	<c:otherwise>
		<div>
			<b>${auth }</b>
			<a href="/my/info">INFO</a> |
			<a href="/my/profile">PROFILE</a> |
			<a href="/log/logout">LOG OUT</a>
		</div>
	</c:otherwise>
</c:choose>
</div>