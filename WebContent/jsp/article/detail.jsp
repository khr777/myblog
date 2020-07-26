<%@ page import="com.sbs.java.blog.util.Util"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="게시물 상세페이지"></c:set>
<%@ include file="/jsp/part/head.jspf"%>
<%@ include file="/jsp/part/toastUIEditor.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="con">
	<div class="detail-box-1 ">
		<div class="detail-title">${article.title}</div>
		<br>
		<div class="data-box-1 ">
			<div class="writeData">
				<div class="id ">번호 : ${article.id}</div>
				<div class="date">등록일 : ${article.regDate}</div>
				<div class="updateDate visible-on-md-up">수정일 :
					${article.updateDate}</div>
				<div class="hit">조회수 : ${article.hit}</div>
				<div class="writer">작성자 : ${article.extra.writer}</div>
				<div class="cateItemName">카테고리 : ${cateItem.name}</div>
			</div>
			<!-- 카테고리 게시물 접속했을 때, 목록 클릭하면 최신 게시물을 불러왔음. 카테고리 게시물로 이동하게 수정한 코드 -->
			<a href="list?cateItemId=${param.cateItemId}&page=${param.page}"
				class="back-icon list-icon"><i class="fas fa-arrow-left">목록
			</i></a>
			<c:if test="${article.extra.modifyAvailable}">
				<a
					href="${pageContext.request.contextPath}/s/article/modify?id=${article.id}"
					class="back-icon  modify-icon"><i class="fas fa-edit">수정 </i></a>
			</c:if>


		</div>
		<div class="editor-box">
			<script type="text/x-template">${article.getBodyForXTemplate()}</script>
			<div class="toast-editor toast-editor-viewer"></div>
			<br />
			<div class="move-button">
				<c:if test="${beforeId > 0}">
					<input class="before" value="이전글" type="button"
						onclick="location.href='detail?id=${beforeId}&cateItemId=${cateItemId}'">
				</c:if>
				<c:if test="${afterId != -1 }">
					<input class="after" value="다음글" type="button"
						onclick="location.href='detail?id=${afterId}&cateItemId=${cateItemId}'">
				</c:if>

			</div>
			<div class="delete-button">
				<c:if test="${article.extra.deleteAvailable}">
					<button type="submit"
						onclick="if (confirm('삭제하시겠습니까?') == false ) return false; location.href='doDelete?id=${param.id}'"
						style="position: absolute; left: 55%; top: 30%;">게시물 삭제</button>
				</c:if>

			</div>
		</div>
		<br />
		<div class="writeReply-text">댓글 입력</div>
		<c:if test="${isLogined == false }">
			<div class="writeReply-text-login">
<%-- 				 <c:Uri value="/s/member/login" var="loginUri"> --%>
<%-- 					<c:param name="afterLoginRedirectUri" --%>
<%-- 						value="${currentUri}&jsAction=WriteReplyForm__focus" /> --%>
<%-- 				</c:Uri> --%>
				<c:set var="loginUri" value="../member/login?afterLoginRedirectUri=${Util.getNewUriAndEncoded(currentUri, 'jsAction', 'WriteReplyForm__focus')}"/>
				<a href="${loginUri}" class="login-link">로그인 </a> 후 이용해주세요.
			</div>
		</c:if>
		<c:if test="${isLogined}">
			<form name="replyForm" action="doWriteReply" method="POST"
				class="write-reply-form form2"
				onsubmit="writeReplyForm__submit(this); return false;">
				<div class="replyWrite-box ">
					<div class="write">
						<div class="label">
							<input type="hidden" name="articleId" value="${param.id}" />
							<!-- 							parameter가 없고 ?까지만 있는 Uri / Uri은 parameter까지 포함하고 있다. -->
							<c:set var="redirectUri"
								value="${Util.getNewUriRemoved(currentUri, 'lastWorkArticleReplyId')}" />
							<c:set var="redirectUri"
								value="${Util.getNewUri(redirectUri, 'jsAction', 'WriteReplyList__showDetail')}" />
							<script type="text/x-template"></script>
							<div data-toast-editor-height="300" class="toast-editor"></div>
						</div>
					</div>
					<div class="submit flex">
						<input type="submit" value="작성" /> <input class="cancel"
							type="button" value="취소" />
					</div>
				</div>
				<input type="hidden" name="body" /> <input type="hidden"
					name="redirectUri" value="${redirectUri}" />
			</form>
		</c:if>
		<div class="border">
			<c:forEach items="${articleReplies}" var="articleReply">
				<div class="replyList" data-id="${articleReply.id}">
					<div class="reply-contents">
						<div class="writer-data">
							<div class="writer1">작성자 : ${articleReply.extra.writer}</div>
							<div class="regDate1">작성일 : ${articleReply.regDate}</div>
							<input type="hidden" value="${param.id}" />
						</div>
						<script type="text/x-template">${articleReply.getBodyForXTemplate()}</script>
						<div class="toast-editor toast-editor-viewer"></div>
					</div>
					<div class="button">
						<c:if test="${articleReply.extra.modifyAvailable}">
							<c:set var="afterDeleteReplyRedirectUri"
								value="${Util.getNewUriRemoved(currentUri, 'lastWorkArticleReplyId')}" />
							<c:set var="afterDeleteReplyRedirectUri"
								value="${Util.getNewUriAndEncoded(afterDeleteReplyRedirectUri, 'jsAction', 'WriteReplyList__showTop')}" />

							<c:set var="afterModifyReplyRedirectUri"
								value="${Util.getNewUriRemoved(currentUri, 'lastWorkArticleReplyId')}" />
							<c:set var="afterModifyReplyRedirectUri"
								value="${Util.getNewUriAndEncoded(afterModifyReplyRedirectUri, 'jsAction', 'WriteReplyList__showDetail')}" />


							<input type="button"
								onclick="location.href='modifyReply?id=${articleReply.id}&redirectUri=${afterModifyReplyRedirectUri}'"
								name="body" value="수정" />
						</c:if>
						<c:if test="${articleReply.extra.deleteAvailable}">
							<button type="submit"
								onclick=" if ( confirm('삭제하시겠습니까?') == false ) return false; location.href='doDeleteReply?replyId=${articleReply.id}&redirectUri=${afterDeleteReplyRedirectUri}'">삭제</button>
						</c:if>
					</div>
				</div>
				<div class="border"></div>
			</c:forEach>
		</div>
	</div>
</div>




<!-- 댓글 리스트 자바스크립트 ( 맨 아래 다른 스크립트 있음)-->
<script>
	//원래는 $('article-replies-list-box').offset().top;
	function WriteReplyList__showTop() {
		var top = $('.cancel').offset().top;
		$(window).scrollTop(top);
	}

	function WriteReplyList__showDetail() {
		WriteReplyList__showTop();

		var $tr = $('.border .replyList[data-id="'
				+ param.lastWorkArticleReplyId + '"]');
		$tr.addClass('high');
		setTimeout(function() {
			$tr.removeClass('high');
		}, 1000);
	}
</script>


<style>
.editor-box {
	
}

.move-button {
	position: absolute;
	buttom: 0;
}

.delete-button {
	position: absolute;
	buttom: 0;
	right: 4%;
	width: 100px;
}

.toast-youtube-embed {
	background-color: red;
}

.toast-youtube-embed {
	position: relative;
}

.ratio-16-9::after {
	content: "";
	display: block;
	padding-top: 56.25%;
}

.ratio-1-1::after {
	content: "";
	display: block;
	padding-top: 100%;
}

.abs-full {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

/* 댓글 등록 시작 */
.form2 {
	margin-bottom: 30px;
	padding: 30px 10px;
}

.form2 .replyWrite-box {
	align-items: center;
}

.form2 .replyWrite-box .write {
	width: 100%;
}

.writeReply-text {
	font-size: 1.4rem;
	color: orange;
}

.writeReply-text-login {
	padding: 10px 0 40px 0;
}

.writeReply-text-login .login-link {
	color: red;
	font-weight: bold;
	opacity: 0.6;
}

.form2 .replyWrite-box .submit {
	margin-right: 25px;
	width: 100%;
}

.form2 .replyWrite-box .submit>input {
	width: 100%;
	padding: 10px 0;
}

.form2 .replyWrite-box .write .label>input {
	margin-left: 20px;
	height: 20px;
	width: 90%;
}

/* 댓글 등록 끝 */

/* 댓글 리스팅 시작 */
.replyList {
	margin-top: 10px;
	display: flex;
	justify-content: space-between;
}

.border {
	border: 1px solid #dfdfdf;
	border-top: 0;
	border-left: 0;
	border-right: 0;
	margin-top: 10px;
	margin-bottom: 10px;
}

.replyList .reply-contents {
	width: 87%;
}

.replyList .reply-contents .writer-data .writer1, .regDate1 {
	font-size: 0.7rem;
}

.replyList .reply-contents  .body {
	
}

.replyList .button {
	display: flex;
	flex-direction: column;
	margin-right: 23px;
}

.border .replyList.high {
	background-color: #dfdfdf;
}

.border .replyList {
	transition: background-color 1s;
}
</style>




</style>

<c:if test="${isLogined}">
	<script>
		var writeReplyForm__submitDone = false;

		function WriteReplyForm__focus() {
			var editor = $('.write-reply-form .toast-editor').data(
					'data-toast-editor');
			editor.focus();
		}

		function writeReplyForm__submit(replyForm) {

			if (writeReplyForm__submitDone) {
				alert('처리중입니다.');
				return;
			}

			var editor = $(replyForm).find('.toast-editor').data(
					'data-toast-editor');
			var body = editor.getMarkdown();
			body = body.trim();

			if (body.length == 0) {
				alert('댓글을 입력해주세요.');
				editor.focus();
				return;
			}
			replyForm.body.value = body;
			replyForm.submit();
			writeReplyForm__submitDone = true;
		}

		function writeReplyForm__init() { //init : 초반에 정리할 때 사용하는. 가독성을 높이는
			$('.write-reply-form .cancel').click(
					function() {
						var editor = $('.write-reply-form .toast-editor').data(
								'data-toast-editor');
						editor.setMarkdown(''); // click하면 editor를 공백으로 만들겠다.
					});
		}

		$(function() {
			writeReplyForm__init();
		});
	</script>
</c:if>
<%@ include file="/jsp/part/foot.jspf"%>