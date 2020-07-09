<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
/* lib   (나중에 다른 곳으로 옮길 예정이라셨음) */
.form1 {
	position: absolute;
	display: block;
	width: 550px;
	top: 30%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
}

.form1 .form-row {
	align-items: center;
	display: flex;
	text-align: center;
	margin-top: 5px;
	
	
}


.form1 .form-row>.label {
	width: 150px;
}

.form1 .form-row>.input {
	flex-grow: 1;
	
	
}


.form1 .form-row:nth-child(6) .input:nth-child(1) {
	width: 80px;
	flex-grow: 0;
	margin-left: 0;
}

.form1 .form-row:nth-child(6) .input:nth-child(2) {
	width: 290px;
	flex-grow: 0;
	margin-left: auto;
	margin-right: 0;
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
	height: 600px;
}

@media ( max-width :799px) {
	.form1 .form-row {
		display: block;
	}
}

/* cus */
.write-form-box {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
	height: 500px;
	width: 30%;
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
	.write-form-box .blank-box {
		top: 240px;
		right: -8%;
	}
}
</style>

<div class="write-form-box">
	<form name="form" action="doJoin" method="POST"	class="write-form form1" onsubmit="submitJoinForm(this); return false">
		<div class="form-row">
			<div class="label">로그인 아이디</div>
			<div class="input">
				<input name="loginId" type="text" placeholder="ID를 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">이름</div>
			<div class="input">
				<input name="name" type="text" placeholder="이름을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">닉네임</div>
			<div class="input">
				<input name="nickname" type="text" placeholder="닉네임을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">로그인 비밀번호</div>
			<div class="input">
				<input name="loginPw" type="password" placeholder="로그인 비밀번호를 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">로그인 비밀번호 확인</div>
			<div class="input">
				<input name="loginPw" type="password" placeholder="로그인 비밀번호 확인을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="button" value="취소" onclick="history.back();" />
			</div>
			<div class="input">
				<input type="submit" value="회원가입" />
				<!-- <a href="#" onclick="history.back();">취소</a>   샘 코드 -->
				<!-- 				<input type="button" value="취소" onclick="history.back();"/> -->

			</div>
		</div>
	</form>
</div>
<script>
function submitJoinForm(form) {
	form.loginId.value = form.loginId.value.trim();
	if ( form.loginId.value.length == 0 ) {
		alert('아이디를 입력해주세요.');
		form.loginId.focus();	
	}

	form.loginPw.value = form.loginPw.
}


</script>


<%@ include file="/jsp/part/foot.jspf"%>