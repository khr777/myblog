<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<form action="doReplyModify"  name="replyModifyForm" method="POST" class="modifyForm" onsubmit="submitReplyModify(this); return false;">
	<div class="replyModify-box">
		<h2 class="heading">${articleReply.articleId}번 게시물의 ${articleReply.id}번 댓글 수정 </h2>
		<div class="writer-data">
			<div class="writer1">작성자 :${articleReply.extra.writer}</div>
			<div class="regDate1">작성일 :${articleReply.regDate}</div>
			<input type="hidden" value="${articleReply.id}" name="id"/>
			<input type="hidden" value="${articleReply.articleId}" name="articleId"/>
		</div>
		<textarea class="body" name="body" >${articleReply.body}</textarea>
		<div class="button">	
			<button type="button" onclick=" history.back(); ">취소</button>
			<input type="submit"  value="등록" /> 
		</div>
	</div>
</form>




<style>

.modifyForm {
	width:60%;
	height:40%;
	position:absolute;
	top:40%;
	left:50%;
	transform:translateX(-50%) translateY(-50%);
	display:flex;
	justify-content:space-between;
}

.modifyForm .replyModify-box {
	width:100%;
	
}

.modifyForm .replyModify-box .writer-data {
}

.modifyForm .replyModify-box .button {
	width:150px;
	display:flex;
	justify-content:center;
	margin-right:0;
	margin-left:auto;
}

.modifyForm .replyModify-box .button input, button {
	margin:0 10px;
}






.modifyForm .replyModify-box .body {
	width:100%;
	height:20%;
	
}
/* 모바일 모드  */
@media ( max-width :799px) {
	.heading {
		font-size:1.2rem;
	}
}


</style>


<script>
var replyModifySubmitted = false;
function submitReplyModify(replyModifyForm) {
	if ( replyModifySubmitted ) {
		alert('처리중입니다.');
		return;
	}	
	replyModifyForm.body.value = replyModifyForm.body.value.trim();
	if ( replyModifyForm.body.value.length == 0 ) {
		alert('수정 댓글을 입력해주세요.');
		replyModifyForm.body.focus();
		return;
	}
	
	replyModifyForm.submit();
	replyModifySubmitted = true;
}

</script>




<%@ include file="/jsp/part/foot.jspf"%>