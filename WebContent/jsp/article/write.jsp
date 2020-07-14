<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/css.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/javascript.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/java.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/xml.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php-template.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/sql.min.js"></script>

	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />

	<script
		src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

	<script
		src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

	<link rel="stylesheet"
		href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
	
	
<style>
/* lib   (나중에 다른 곳으로 옮길 예정이라셨음) */
.form1 {
	display: block;
	width: 95%;
	margin-left:2%;
	margin-right:2%;
}

.form1 .form-row {
	align-items: center;
	display:flex;
	
}

.form1 .form-row:not(:first-child) {
	margin-top: 10px;
}

.form1 .form-row>.label {
	width:100px;
	
}

.form1 .form-row>.input {
	flex-grow:1;
}




.form1 .form-row:nth-child(5) .input  a {
	display:block;
	padding:20px;
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
	margin-top: 170px;
	border:1px solid black;
	padding-top:20px;
	margin-left:100px;
	margin-right:100px;
	
}

.write-form-box .blank-box {
	position:absolute;
	top:200px;
	right:5%;
	
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
		border:1px solid black;
		padding-top:20px;
		margin-left:50px;
		margin-right:50px;
	
	}

	.write-form-box .blank-box {
	top:240px;
	right:2%;
	
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

	<form name="form" action="doWrite" method="POST" class="write-form form1" onsubmit="submitWriteForm(this); return false;">
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
					<%
						for (CateItem cateItem : cateItems) {
					%>
					<option value="<%=cateItem.getId()%>"><%=cateItem.getName()%></option>
					<%
						}
					%>
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
<!-- 				<textarea name="body" placeholder="내용을 입력해주세요."></textarea> -->
				<div id="editor1"></div>
			</div>
		</div>
		<input type="hidden" name="memberId" value="<%=session.getAttribute("loginedMemberId")%>"/>
		<div class="form-row">
			<div class="label"></div>
			<div class="input">
				<input type="submit" value="전송" />
				<a href="list">취소</a>
				<!-- 				<input type="button" value="취소" onclick="history.back();"/> -->

			</div>
		</div>
	</form>
</div>
<script>


var editor1 = new toastui.Editor({
el: document.querySelector("#editor1"),
height: "600px",
initialEditType: "markdown",
previewStyle: "vertical",
initialValue: "# 내용을 입력해주세요.",
plugins: [toastui.Editor.plugin.codeSyntaxHighlight, youtubePlugin, replPlugin, codepenPlugin]
});

var writeFormSubmitted = false;
function submitWriteForm(form) {
	if ( writeFormSubmitted ) {
		alert('처리중입니다.');
		return;
	}
	form.title.value = form.title.value.trim();
	if ( form.title.value.length == 0 ) {
		alert('제목을 입력해주세요.');
		form.title.focus();
		return;
	}
	var source = editor1.getMarkdown().trim();
	if ( source.length == 0 ) {
		alert('내용을 입력해주세요.');
		editor1.focus();
		return;
	}
	form.body.value = source;
	form.submit();
	writeFormSubmitted = true;
	
}


</script>

<%@ include file="/jsp/part/foot.jspf"%>