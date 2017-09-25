<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3 id="q">JQuery</h3>
each() : 각 엘리멘트마다, 특정수행 작업이 필요할때,
<hr/>
<c:forTokens var="t" items="봄,여름,가을,겨울" delims=",">
	<button class="bt">${t }</button>
</c:forTokens>
<hr/>
<c:forEach begin="1" end="4">
	→ <input type="text"/><br/>
</c:forEach>
<hr/>
<button id="sbt">EACH TEST</button>
<script>
	$("#sbt").click(function(){
		$("input").each(function(){
			if($(this).val().length>=3) {
				$(this).css("background", "#FAED7D");
			}
		});
	});
	
	$("#q").click(function(){
		$(".bt").each(function(){
			//this.innerHTML += this.innerHTML;
			//this.html("zzzz");
			$(this).prop("disabled", true);
			console.log($(this).html()+" / "+typeof this.innerHTML+" / "+$(this).html().length);
			
			if($(this).html().length == 2){
				window.alert($(this).html());
			}
		});
		// 이 작업은 each를 안써도 되는 상황, $(".bt").prop("disabled", true);
	});
</script>