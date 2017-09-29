<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/">Home</a></li>
        <c:choose>
			<c:when test="${auth eq null }">
		        <li><a href="/my/list?page=1">MEMBER LIST</a></li>
		        <li><a href="/parkinfo">PARKINFO</a></li>
		        <li><a href="/board/list">BOARD</a></li>
		        <li><a href="/market/all">MARKET</a></li>
		        <li><a href="/chat">CHAT</a></li>
		        <li><a href="/my/profile">PROFILE</a></li>
		    </c:when>
			<c:otherwise>
				<li><a href="/board/list">BOARD</a></li>
				<li><a href="/market/all">MARKET</a></li>
				<li><a href="/chat">CHAT</a></li>
				<li>
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle navbar-btn" type="button" data-toggle="dropdown">INFO
						<span class="caret"></span></button>
						<ul class="dropdown-menu">
							<li><a href="/my/info">INFO</a></li>
							<li><a href="/my/profile">PROFILE</a></li>
							<li><a href="/my/list?page=1">MEMBER LIST</a></li>
						</ul>
					</div>
				</li>
				<li>
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle navbar-btn" type="button" data-toggle="dropdown">MEMO
						<span class="caret"></span></button>
						<ul class="dropdown-menu">
							<li><a href="/memo/send">SEND MEMO</a></li>
							<li><a href="/memo/receive">RECEIVE</a></li>
						</ul>
					</div>
				</li>
			</c:otherwise>
		</c:choose>     
      	</ul>
      	<ul class="nav navbar-nav navbar-right">
		<c:choose>
			<c:when test="${auth eq null }">
		        <li><a href="/member/join"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
		        <li><a href="/log/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/log/logout"><span class="glyphicon glyphicon-log-out"></span> LOG OUT</a></li>
			</c:otherwise>
		</c:choose>     
      </ul>
    </div>
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
		
		var auctionws = new WebSocket("ws://192.168.10.66/ws/auction");
		
		auctionws.onmessage = function(e){
			console.log(e.data);
			var obj = JSON.parse(e.data);
			document.getElementById("p_"+obj.parent).innerHTML = obj.price;
		}
	</script>
</c:if>