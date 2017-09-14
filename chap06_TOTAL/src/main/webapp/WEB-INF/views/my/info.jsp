<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center" style="line-height: 30px;">
	<h2>${auth }'s INFO</h2>
	<div style="float:left; margin-left: 100px;">
		<h3>프로필사진</h3>
		<form action="/my/pic_update.jsp" method="post" enctype="multipart/form-data">
		<p>	
			<img src="/images/" style="border-radius: 100%; width: 150px; height: 150px;"/>
		</p>
		<p>
			<input type="file" name="pic"/><button type="submit">변경</button>
		</p>
		</form>
	</div>
	<form action="/my/info_rst.jsp" method="post">
		<p>
			<b>NAME</b><br/>
			<input type="text" value="${map.name ne null ? map.name : '' }" name="name" required/>
		</p>
		<p>
			<b>GENDER</b><br/>
			<c:choose>
				<c:when test="${map.gender eq null or map.gender eq '남' }">
					<input type="radio" name="gender" value="남" checked required />남
					<input type="radio" name="gender" value="여" required />여
				</c:when>
				<c:otherwise>
					<input type="radio" name="gender" value="남" required />남
					<input type="radio" name="gender" value="여" checked required />여
				</c:otherwise>
			</c:choose>
		</p>
		<p>
			<b>BIRTH</b><br/>
			<select name="birth">
			<c:forEach var="i" begin="2000" end="1900" >
				<option value="${i }" ${i eq map.birth ? 'selected' : '' } >${i }년</option>
			</c:forEach>
			</select>
		</p>
		<p>
			<b>ADDRESS</b><br/> 
			<input type="text" value="${map.address ne null ? map.address : '' }" name="address" size="50" required/>
		</p>
		<button type="submit" style="width: 120px;">정보변경</button>
		<a href="/my/drop.jsp"><button type="button">회원탈퇴</button></a>
	</form>
</div>