<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><t:getAsString name="title"/></title>
<!-- JQUERY -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- BootStrap -->
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<header>
		<t:insertAttribute name="header"></t:insertAttribute>
	</header>
	<nav  class="navbar navbar-inverse">
		<t:insertAttribute name="nav"></t:insertAttribute>
	</nav>
	<section style="min-height: 70%;">
		<t:insertAttribute name="section"/>
	</section>
	<footer>
		<t:insertAttribute name="footer"></t:insertAttribute>
	</footer>
</div>
</body>
</html>