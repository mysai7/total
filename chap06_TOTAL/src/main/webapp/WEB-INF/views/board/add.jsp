<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
	input, textarea, button {
		padding:5px;
		font-family: 맑은고딕;
	}
</style>
<div align="center">
<h2>게시판</h2>
<div align="left" style="width: 80%; line-height: 30px;">
	<form action="/board/add" method="post" autocomplete="off">
		<p>
			<b>작성자</b><br /><input type="text" name="writer" placeholder="작성자"
				 style="width: 100%;" />
		</p>
		<p>
			<b>글제목</b><br /> <input type="text" name="title" placeholder="글제목"
				 style="width: 100%;" />
		</p>
		<p>
			<b>글내용</b><br />
			<textarea rows="10" name="content" placeholder="글내용"
				style="width: 100%;"></textarea>
		</p>
		<p>
			<button type="submit">글등록</button>
			<button type="reset">재작성</button>
		</p>
	</form>
</div>
</div>