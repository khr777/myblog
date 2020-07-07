<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<title>listWrite-ok</title>

<div class="WriteOk">게시물 작성 성공!</div>
<button type="button" onclick="location.href='list?cateItemId=${param.cateItemId}&page=${param.page}'" class="write-button" >뒤로가기</button>

<style>
.WriteOk {
	font-size:4rem;
	font-weight:bold;
	position:absolute;
	top:40%;
	left:50%;
	transform:translateX(-50%) translateY(-40%);
	color:orange;
}	
.write-button {
	position:absolute;
	top:50%;
	left:50%;
	transform:translateX(-50%) translateY(-40%);
}

@media (max-width:799px) {
	.WriteOk {
		font-size:1.5rem;
	}
	
}



</style>



<%@ include file="/jsp/part/foot.jspf"%>