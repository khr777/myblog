<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="아이디/비밀번호 찾기"></c:set>
<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>	
<style>
/* lib   (나중에 다른 곳으로 옮길 예정이라셨음) */
.find-loginId-box .form1 {
	position:absolute;
	display: block;
	width: 400px;
	top:30%;
	left:50%;
	transform:translateX(-50%) translateY(-50%);
	
}

find-loginId-box .form1 .form-row {
	padding:5px;
	align-items: center;
	display:flex;
	text-align:center;
	
	
	
}


.find-loginPw-box .form1 {
	position:absolute;
	display: block;
	width: 400px;
	top:70%;
	left:50%;
	transform:translateX(-50%) translateY(-50%);
	
}



.form1 .form-row>.label {
	width:30%;
	
	
}

.form1 .form-row>.input {
	flex-grow:1;
	
}

.form1 .form-row .input > input {   /* 맨 위에 hidden으로 input을 1개 넣어서 자식 순서가 1씩 밀려버림... */
	border-radius:5px;
	
}

.form1 .form-row:nth-child(5) {
	display:flex;
	justify-content:space-between;
}

.form1 .form-row:nth-child(5) .input {
	width:90px;
	flex-grow:0;
	
	
}



.form1 .form-row:nth-child(5) .input:nth-child(2) {
	width:60%;
	flex-grow:0;
	margin-left:0;
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
.find-loginId-box {
	position:absolute;
	top:50%;
	left:50%;
	transform:translateX(-50%) translateY(-50%);
	height:600px;
	width:500px;
	border:2px solid gold;
}

.blog-name {
	position:absolute;
	font-size:2.4rem;
	top:90%;
	left:50%;
	transform:translateX(-50%);
	letter-spacing:10px;
	
}


.find-loginId-box .blank-box {
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
	.form1 {
		margin-top:200px;
		
	}
	.blog-name {
		font-size:1.7rem;
		top:100%;
		left:96%;
		transform:translateX(-50%);
		letter-spacing:10px;
	}
	.find-loginId-box {
		border:none;
		width:20%;
		top:250px;
		left:42%;
		
		
	}
	.find-loginId-box .blank-box {
	top:240px;
	right:-8%;
	
	
	}
	.form1 .form-row {
		display: block;
		width:80%;
		margin-left:auto;
		margin-top:auto;
		text-align:left;
		
	}
	.form1 .form-row>.input {
		flex-grow:1;
	}
	.form1 .form-row .label {
		width:45%;
	}
	.form1 .form-row:nth-child(4) { 
		display:flex;
		
	}
	
	
}
</style>

<div class="find-loginId-box">
	<form name="form" action="doFindLoginId" method="POST" class="find-loginId form1" onsubmit="findLoginIdForm(this); return false;">	
		<input type="hidden" name="loginPwReal" />
		
		<div class="form-row">
			<div class="label">가입 성명</div>
			<div class="input">
				<input name="name" autofocus type="text" placeholder="가입 성명을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">가입 이메일</div>
			<div class="input">
				<input name="email" type="email" placeholder="가입 이메일을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="submit" value="로그인 아이디 찾기" />
			</div>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="button" value="취소" onclick="history.back();" />
			</div>
		</div>
	</form>
</div>
<div class="find-loginPw-box">
	<form name="form" action="doFindLoginPw" method="POST" class="find-loginPw form1" onsubmit="findLoginPwForm(this); return false;">	
		<div class="form-row">
			<div class="label">가입 성명</div>
			<div class="input">
				<input name="name" type="text" placeholder="가입하신 성명을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">가입 아이디</div>
			<div class="input">
				<input name="loginId" type="text" placeholder="가입하신 아이디를 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">가입 이메일</div>
			<div class="input">
				<input name="email" type="email" placeholder="가입하신 이메일을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="submit" value="로그인 비밀번호 찾기" />
			</div>
		</div>
	</form>
	<div class="blog-name">harry.my.iu.gy</div>
</div>




<script>
var FindLoginIdForm__submitDone = false;
function findLoginIdForm(form) {
	if ( FindLoginIdForm__submitDone ) {
		alert('처리중 입니다.');
		return;
	}
	form.name.value = form.name.value.trim();
	if ( form.name.value.length == 0 ) {
		alert('가입 성명을 입력해주세요.');
		form.name.focus();
		return;
	}

	form.email.value = form.email.value.trim();
	if ( form.email.value.length == 0 ) {
		alert('가입하신 이메일을 입력해주세요.');
		form.email.focus();
		return;
	}
	
	
	form.submit();
	FindLoginIdForm__submitDone = true;
}
</script>



<script>
var FindLoginPwForm__submitDone = false;
function submitLoginIdForm(form) {
	if ( FindLoginPwForm__submitDone ) {
		alert('처리중 입니다.');
		return;
	} 
	
	form.name.value = form.name.value.trim();
	if ( form.name.value.length == 0 ) {
		alert('가입하신 성명을 입력해주세요.');
		form.name.focus();
		return;
	}
	form.loginId.value = form.loginId.value.trim();
	if ( form.loginId.value.length == 0 ) {
		alert('가입하신 아이디를 입력해주세요.');
		form.loginId.focus();
		return;
	}
	form.email.value = form.email.value.trim();
	if ( form.email.value.length == 0 ) {
		alert('가입하신 이메일을 입력해주세요.');
		form.email.focus();
		return;
	}
	
	
	form.submit();
	FindLoginPwForm__submitDone = true;
}



<%@ include file="/jsp/part/foot.jspf"%>