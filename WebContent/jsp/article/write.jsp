<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<style>
/* lib   (나중에 다른 곳으로 옮길 예정이라셨음) */
.form1 {
	display: block;
	width: 80%;
	margin-left:10%;
	margin-right:10%;
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
	margin-left:150px;
	margin-right:150px;
	
}

.write-form-box .blank-box {
	position:absolute;
	top:180px;
	right:12%;
	
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
		<div class="write-editor">
			<a href="${pageContext.request.contextPath}/s/article/editor"
				target="_blank"> 🔨 editor 이동 </a>
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
				<textarea name="body" placeholder="내용을 입력해주세요."></textarea>
				<div id="editor1"></div>
			</div>
		</div>
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
function submitWriteForm(form) {

	form.title.value = form.title.value.trim();
	if ( form.title.value.length == 0 ) {
		alert('제목을 입력해주세요.');
		form.title.focus();
		return;
	}
	form.body.value = form.body.value.trim();
	if ( form.body.value.length == 0 ) {
		alert('내용을 입력해주세요.');
		form.body.focus();
		return;
	}
	
	//변수 추가해서 원하는 값을 얻지 못했을 경우, 게시물 생성하지 못하도록하는 방법 찾아보기.
	// form.cateItemId.vaule = form.cateItem.value.trim();
	//if ( form.cateItemId == 0 ) {
		//alert('카테고리를 선택해주세요.');
	//	return;
//	}

	
	
	form.submit();
	
}
</script>

<%@ include file="/jsp/part/foot.jspf"%>