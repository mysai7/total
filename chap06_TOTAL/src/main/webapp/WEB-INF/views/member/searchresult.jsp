<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul style="list-style: none">
<c:forEach var="t" items="${data }">
	<li><b>${t.ID }</b> (${t.EMAIL })</li>
</c:forEach>
</ul>
<script>
	$("b").click(function(){
		window.alert("?");
	});
</script>