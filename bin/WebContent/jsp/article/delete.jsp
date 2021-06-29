<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>delete</title>
<!--  하는 일 없는... 없어도 될 것 같은 ....  -->

<div class="deleteOk">게시물 삭제 성공!</div>
<button type="button" onclick="location.href='list'" class="delete-button" >뒤로가기</button>


<style>
.deleteOk {
	font-size:4rem;
	font-weight:bold;
	position:absolute;
	top:40%;
	left:50%;
	transform:translateX(-50%) translateY(-40%);
	color:orange;
}
.delete-button {
	position:absolute;
	top:50%;
	left:50%;
	transform:translateX(-50%) translateY(-40%);
}
</style>

<%@ include file="/jsp/part/foot.jspf"%>