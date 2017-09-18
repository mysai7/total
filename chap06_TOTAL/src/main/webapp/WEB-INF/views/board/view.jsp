<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div align="center" style="line-height: 35px">
	<h2>게시판</h2>
	<hr />
	<c:choose>
		<c:when test="${empty map }">
			이미 삭제된 글입니다.
		</c:when>
		<c:otherwise>
			<div style="width: 80%" align="left">
				<input type="hidden" id="num" value="${map.NUM }"/>
				<h3>${map.TITLE } </h3>
				<p>
					<small>작성자 : ${map.WRITER } | 작성일 : <fmt:formatDate
							pattern="MM.dd.yyyy HH:mm:ss" value="${map.WRITEDATE }" /> | 조회수
						: <fmt:formatNumber value="${map.COUNT }" pattern="#,###" />
						| <button id="add">&#9733;</button>
					</small>
				</p>
				<pre style="font-family: 맑은 고딕; font-size: 10pt; min-height: 250px;">${map.CONTENT }</pre>
			</div>
		</c:otherwise>
	</c:choose>
	<hr />
	<a href="/board/list"><button>목록으로</button></a>
	<a href="/delete.do?num=${map.NUM }"><button>삭제</button></a>
	<hr/>
	<div align="center" style="width: 100%; line-height: 30px;">
		<p>
			<input id="writer" type="text" placeholder="작성자" style="width: 40%;" />
		</p>
		<p>
			<textarea id="content" rows="5" placeholder="댓글내용" style="width: 40%;" required></textarea>
		</p>
		<p>
			<input id="pass" type="password" placeholder="비밀번호" style="width: 40%;" />
		</p>
		<p>
			<button id="sbt" type="submit">댓글등록</button>
		</p>
	</div>
	<hr/>
	<div id="view" style="width:40%; padding: 5px; margin: 10px;">
	</div>
</div>
<script>
	document.getElementById("sbt").onclick = function() {
		var parent = ${map.NUM};
		var writer = document.getElementById("writer").value;
		var content = document.getElementById("content").value;
		var pass = document.getElementById("pass").value;
		if(content.length == 0){
			return;
		}			
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange= function(){
			if(this.readyState==4) {
				var obj = JSON.parse(this.responseText);
				if (obj.result) {
					window.alert("등록되었습니다.");
					document.getElementById("writer").value="";
					document.getElementById("content").value="";
					document.getElementById("pass").value="";
					list();
				}else{
					window.alert("등록 실패.");
				}
			}
		}
		var data = {
			"parent" : parent,
			"writer" : writer,
			"content" : content,
			"pass" : pass
		};
		xhr.open("post","/reply/add", true);
		xhr.send(JSON.stringify(data));
	};
	var list = function() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange= function(){
			if(this.readyState==4) {
				var obj = JSON.parse(this.responseText);
				var rst ="";
				for(i in obj){
					rst += "<span>"+obj[i].WRITER+"</span> <span>"+obj[i].CONTENT+"</span> -<small>"+obj[i].NUM+"</small><br/>";
				}
				document.getElementById("view").innerHTML = rst;
			}
		}
		xhr.open("post","/reply/list/${map.NUM}", true);
		xhr.send();
	};
	list();	
</script>