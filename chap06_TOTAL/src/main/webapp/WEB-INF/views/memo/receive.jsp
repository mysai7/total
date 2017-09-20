<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<h2>받은 쪽지</h2>
	<form action="/memo/delete" method="post">
		<p>
			<b>${sessionScope.auth }</b>님 총 <b>${list.size() }</b> 개의 쪽지가 있습니다.
		</p>
		<table style="width:80%">
			<thead>
				<tr>
					<th style="width: 5%"><input type="checkbox" id="all" onchange="javascript:allCheck()"/></th>
					<th style="width: 20%">보낸사람</th>
					<th style="width: 75%">메모내용</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="m" items="${list }">
				<tr>
					<td><input type="checkbox" name="no" value="${m.NO }" class="check" onchange="javascript:check()"/></td>
					<td>
						<c:choose>
							<c:when test="${m.SENDER ne sessionScope.auth }">
								<a href="/member/find.jsp?target=${m.sender }">${m.SENDER }</a>
							</c:when>
							<c:otherwise>
								${m.SENDER }
							</c:otherwise>
						</c:choose>
					</td>
					<td>${m.CONTENT }</td>
				</tr>	
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">
						<button type="submit">삭제</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>
<script>
	function allCheck(){
		var c = document.getElementsByClassName("check");
		for(var i=0; i<c.length; i++){
			c[i].checked = document.getElementById("all").checked;
		}
	}
	function check(){
		var all = document.getElementById("all");
		var c = document.getElementsByClassName("check");
		var cnt = 0;
		for(var i=0; i<c.length; i++){
			if(!c[i].checked){
				all.checked = false;
			}else{
				cnt++;
			}
			if(c.length == cnt){
				all.checked = true;
			}
		}
	}
</script>