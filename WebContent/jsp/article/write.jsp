<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="게시물 작성"></c:set>
<%@ include file="/jsp/part/head.jspf"%>
<%@ include file="/jsp/part/toastUIEditor.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<style>
.page-title {
	top: 0;
}

/* lib   (나중에 다른 곳으로 옮길 예정이라셨음) */
.form1 {
	display: block;
	width: 95%;
	margin-left: 2%;
	margin-right: 2%;
}

.form1 .form-row {
	align-items: center;
	display: flex;
}

.form1 .form-row:not (:first-child ) {
	margin-top: 10px;
}

.form1 .form-row>.label {
	width: 100px;
}

.form1 .form-row>.input {
	flex-grow: 1;
}

.form1 .form-row:nth-child(5) .input  a {
	display: block;
	padding: 20px;
}

.form1 .form-row>.input>input, .form1 .form-row>.input>textarea {
	display: block;
	width: 100%;
	box-sizing: border-box;
	padding: 10px;
}

.form1 .form-row>.input>select {
	padding: 10px;
}

.form1 .form-row>.input>textarea {
	height: 500px;
}

@media ( max-width :799px) {
	.form1 .form-row {
		display: block;
	}
}

/* cus */
.write-form-box {
	margin-top: 180px;
	border: 1px solid black;
	padding-top: 20px;
	margin-left: 100px;
	margin-right: 100px;
}

.write-form-box .blank-box {
	position: absolute;
	top: 200px;
	right: 5%;
}

.emoji, .pixabay, .github, .write-editor {
	width: 200px;
}

.emoji, .pixabay, .github, .write-editor a {
	display: block;
}

@media ( max-width :799px) {
	.write-form-box {
		margin-top: 170px;
		border: 1px solid black;
		padding-top: 20px;
		margin-left: 50px;
		margin-right: 50px;
	}
	.write-form-box .blank-box {
		top: 240px;
		right: 2%;
	}
}
</style>

<div class="write-form-box">
	<div class="blank-box">
		<div class="emoji">
			<a href="https://www.emojiengine.com/ko/" target="_blank">
				😵emoji 이동 </a>
		</div>
		<div class="pixabay">
			<a href="https://pixabay.com/ko/" target="_blank"> 📸 pixabay 이동
			</a>
		</div>
		<div class="github">
			<a href="https://github.com/hyeryeonkim" target="_blank"> 🚀
				github 이동 </a>
		</div>

	</div>

	<form name="form" action="doWrite" method="POST"
		class="write-form form1"
		onsubmit="submitWriteForm(this); return false;">
		<div class="form-row">
			<div class="label">공개여부</div>
			<div class="input">
				<select name="displayStatus">
					<option value="1">공개</option>
					<option value="0">비공개</option>
				</select>
			</div>
		</div>
		<div class="form-row">
			<div class="label">카테고리 선택</div>
			<div class="input">
				<select name="cateItemId">
					<c:forEach items="${cateItems}" var="cateItem">
						<option value="${cateItem.id}">${cateItem.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-row">
			<div class="label">제목</div>
			<div class="input">
				<input name="title" type="text" placeholder="제목을 입력해주세요." />

			</div>
		</div>
		<div class="form-row">
			<div class="label">내용</div>
			<div class="input">
				<input type="hidden" name="body" />
				<script type="text/x-template"></script>
				<div class="toast-editor"></div>
			</div>
		</div>
		<div class="form-row">
			<div class="label"></div>
			<div class="input">
				<input type="submit" value="전송" /> <a href="list">취소</a>
				<!-- 				<input type="button" value="취소" onclick="history.back();"/> -->

			</div>
		</div>
	</form>
</div>
<script>


	var submitWriteFormDone = false;
	function submitWriteForm(form) {
		if (submitWriteFormDone) {
			alert('처리중입니다.');
			return;
		}
		form.title.value = form.title.value.trim();
		if (form.title.value.length == 0) {
			alert('제목을 입력해주세요.');
			form.title.focus();
			return;
		}

		var editor = $(form).find('.toast-editor').data('data-toast-editor');
		var body = editor.getMarkdown();
		body = body.trim();

		//var source = editor1.getMarkdown().trim();
		if (body.length == 0) {
			alert('내용을 입력해주세요.');
			editor.focus();
			return;
		}

		removeOnBeforeUnload();
		form.body.value = body;
		form.submit();
		submitWriteFormDone = true;

	}

	function WriteForm__init() {
		  // 폼의 특정 요소를 건드리(?)면, 그 이후 부터 외부로 이동하는 것에 참견하는 녀석을 작동시킨다.
		  $('.toast-editor').keyup(function() {
		    applyOnBeforeUnload();
		  });
		}

		WriteForm__init();
</script>

<%@ include file="/jsp/part/foot.jspf"%>