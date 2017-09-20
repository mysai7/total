<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center" style="line-height: 35px">
	<h2>쪽지보내기</h2>
	<form action="/memo/send" method="post">
		<input type="hidden" name="sender" value="${auth }" />
		<p>
			<b>받을사람</b><br/>
			<input type="text" name="receiver" placeholder="받을사람.." required autocomplete="off"/>
		</p>
		<p>
			<b>보낼내용</b><br /> 
			<textarea rows="6" cols="40" name="content" placeholder="보낼내용.." required></textarea>
		</p>
		<p>
			<button type="submit">쪽지발송</button>
		</p>
	</form>
</div>