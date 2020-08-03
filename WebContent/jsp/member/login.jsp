<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="로그인"></c:set>
<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<style>
/* lib   (나중에 다른 곳으로 옮길 예정이라셨음) */
.form1 {
	position: absolute;
	display: block;
	width: 400px;
	top: 30%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
}

.form1 .form-row {
	padding: 5px;
	align-items: center;
	display: flex;
	text-align: center;
}

.form1 .form-row>.label {
	width: 30%;
}

.form1 .form-row>.input {
	flex-grow: 1;
}

.form1 .form-row .input>input {
	/* 맨 위에 hidden으로 input을 1개 넣어서 자식 순서가 1씩 밀려버림... */
	border-radius: 5px;
}

.form1 .form-row:nth-child(5) {
	display: flex;
	justify-content: space-between;
}

.form1 .form-row:nth-child(5) .input {
	width: 90px;
	flex-grow: 0;
}

.form1 .form-row:nth-child(5) .input:nth-child(2) {
	width: 60%;
	flex-grow: 0;
	margin-left: 0;
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

/* cus */
.write-form-box {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
	height: 600px;
	width: 500px;
	border: 2px solid gold;
}

.blog-name {
	position: absolute;
	font-size: 2.4rem;
	top: 70%;
	left: 50%;
	transform: translateX(-50%);
	letter-spacing: 10px;
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
	.form1 {
		margin-top: 200px;
	}
	.blog-name {
		font-size: 1.7rem;
		top: 100%;
		left: 96%;
		transform: translateX(-50%);
		letter-spacing: 10px;
	}
	.write-form-box {
		border: none;
		width: 20%;
		top: 250px;
		left: 42%;
	}
	.write-form-box .blank-box {
		top: 240px;
		right: -8%;
	}
	.form1 .form-row {
		display: block;
		width: 80%;
		margin-left: auto;
		margin-top: auto;
		text-align: left;
	}
	.form1 .form-row>.input {
		flex-grow: 1;
	}
	.form1 .form-row .label {
		width: 45%;
	}
	.form1 .form-row:nth-child(4) {
		display: flex;
	}
}
</style>

<div class="write-form-box">
	<form name="form" action="doLogin" method="POST"
		class="write-form form1"
		onsubmit="submitLoginForm(this); return false;">
		<input type="hidden" name="loginPwReal" />
			<div class="form-row">
				<div class="label">로그인 아이디</div>
				<div class="input">
					<input name="loginId" autofocus type="text" placeholder="로그인 아이디를 입력해주세요." />
				</div>
			</div>
			<div class="form-row">
				<div class="label">로그인 비밀번호</div>
				<div class="input">
					<input name="loginPw" type="password"
						placeholder="로그인 비밀번호를 입력해주세요." />
				</div>
			</div>
			<div class="form-row">
				<div class="input">
					<input type="submit" value="로그인" />
				</div>
			</div>
			<div class="form-row">
				<div class="input">
					<input type="button" value="취소" onclick="history.back();" />
				</div>
				<div class="input">
					<input type="button" value="회원가입" onclick="location.href='join'" />
				</div>
			</div>
			<input type="hidden" name="redirectUri"
				value="${param.afterLoginRedirectUri}" />
			<input type="button" value="아이디 찾기"
				onclick="location.href='findAccount'">
			<input type="button" value="비밀번호 찾기"
				onclick="location.href='lookForLoginPw'">
	</form>
	<div class="blog-name">harry.my.iu.gy</div>
</div>

<script>
	function submitLoginForm(form) {
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}

		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.loginPw.focus();
			return;
		}

		form.loginPwReal.value = sha256(form.loginPw.value); /* 암호화된 텍스트를 넘겨준다.*/
		form.loginPw.value = "";

		form.submit();
	}
</script>




<%@ include file="/jsp/part/foot.jspf"%>