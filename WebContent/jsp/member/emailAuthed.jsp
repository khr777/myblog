<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="이메일 인증 재발송"></c:set>
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
	top: 40%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
}

.form1 .form-row {
	padding: 5px;
	align-items: center;
	display: flex;
	text-align: center;
}

.form1 .title {
	font-weight:bold;
	color:DeepSkyBlue;
	font-size:1.6rem;
	opacity: 0.8;
}

.form1 .body .이 strong {
	font-size:2rem;
	color:gold;
	opacity : 0.8;
}




.form1 .form-row>.label {
	width: 30%;
}

.form1 .form-row .input  {
	flex-grow: 1;
}

.form1 .form-row .input > input{
	font-size:1.3rem;
	font-weight:bold;	
}




.form1 .form-row .input>input {
	/* 맨 위에 hidden으로 input을 1개 넣어서 자식 순서가 1씩 밀려버림... */
	border-radius: 5px;
}


.form1 .form-row>.input>input, .form1 .form-row>.input>textarea {
	display: block;
	width: 100%;
	box-sizing: border-box;
	padding: 10px;
}

.form1 .form-row>.input>textarea {
	height: 500px;
}

/* cus */
.emailAuth-form-box {
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
	top: 80%;
	left: 50%;
	transform: translateX(-50%);
	letter-spacing: 10px;
}

.emailAuth-form-box .blank-box {
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
	.emailAuth-form-box {
		border: none;
		width: 20%;
		top: 250px;
		left: 42%;
	}
	.emailAuth-form-box .blank-box {
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

<div class="emailAuth-form-box">
	<form name="form" action="sendEmailAuthedAgain" method="POST"
		class="emailAuth-form form1"
		onsubmit="submitLoginIdForm(this); return false;">
		<input type="hidden" value="${member.id}" name="id"/>
		<div class="title">이메일 주소 인증하기</div>
		<div class="body">
			<p class="일">안녕하세요.</p>
			<p class="이"><strong>Harry's life</strong> 회원가입을 감사드립니다.</p>
			<p class="삼"><strong>${member.email}</strong>고객님.</p>
			<p class="사">현재 <strong>이메일 미인증</strong> 회원님으로</p>
			<p class="오">아래 버튼을 클릭하여 이메일 인증을 완료해주세요.</p>
		 	<p class="육">감사합니다.</p>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="submit" value="이메일 인증하기" />
			</div>
		</div>
		<p><strong>이메일을 확인해주세요.</strong></p>
	</form>
	<div class="blog-name">harry.my.iu.gy</div>
</div>

<script>
	function submitLoginIdForm(form) {
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('가입하신 성명을 입력해주세요.');
			form.name.focus();
			return;
		}
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('가입하신 아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('가입하신 이메일을 입력해주세요.');
			form.email.focus();
			return;
		}

		form.submit();
	}
</script>









<%@ include file="/jsp/part/foot.jspf"%>
