<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="right" style="background: silver; padding: 10px;">
<c:choose>
	<c:when test="${auth eq null }">
		<div>
			<a href="/my/list?page=1">MEMBER LIST</a> |
			<a href="/member/join">JOIN</a> |
			<a href="/log/login">LOGIN</a><br/>
			<a href="/board/list">BOARD</a> |
			<a href="/market/all">MARKET</a> |
			<a href="/chat">CHAT</a>
		</div>
	</c:when>
	<c:otherwise>
		<div>
			<b>${auth }</b>
			<a href="/my/info">INFO</a> |
			<a href="/my/profile">PROFILE</a> |
			<a href="/my/list?page=1">MEMBER LIST</a> |
			<a href="/log/logout">LOG OUT</a><br/>
			<a href="/board/list">BOARD</a> |
			<a href="/market/all">MARKET</a> |
			<a href="/chat">CHAT</a> |
			<a href="/memo/send">SEND MEMO</a> |
			<a href="/memo/receive">RECEIVE</a>
		</div>
	</c:otherwise>
</c:choose>
</div>
<c:if test="${!empty auth }">
	<script>
		var lws = new WebSocket("ws://192.168.10.66/ws/login");
		
		lws.onmessage = function(e){
			console.log(e.data);
			if(window.confirm(e.data)){
				location.href="/memo/receive";	
			}
		}
	</script>
</c:if>