<%@ include file="/jsp/part/head.jspf"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.java.blog.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%List<Member> members = (List<Member>)request.getAttribute("members"); %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<style>
/* lib   (나중에 다른 곳으로 옮길 예정이라셨음) */
.form1 {
	position: absolute;
	display: block;
	width: 550px;
	top: 40%;
	left: 50%;
	transform: translateX(-50%) translateY(-50%);
}

.form1 .form-row {
	padding:5px;
	align-items: center;
	display:flex;
	text-align:center;
	
}


.form1 .form-row >.label {
	width: 150px;
}

.form1 .form-row>.input {
	flex-grow: 1;
	
	
}



.form1 .form-row>.input>input, .form1 .form-row>.input>textarea {
	display: block;
	width: 100%;
	box-sizing: border-box;
	padding: 10px;
}

.form1 .form-row>.input>select {
	padding: 12px;
}


.form1 .form-row:last-child .input:first-child input {
	width:80px;
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
	width: 40%;
	border:4px solid gold;
}
.blog-name {
	position:absolute;
	font-size:2rem;
	top:85%;
	left:50%;
	transform:translateX(-50%);
	letter-spacing:10px;
	width:100%;
	text-align:center;
	
}

</style>

<div class="write-form-box">
	<form name="form" action="doJoin" method="POST"	class="write-form form1" onsubmit="submitJoinForm(this); return false">
	<input type="hidden" name="loginPwReal"/>
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
				<input name="loginPwConfirm" type="password" placeholder="로그인 비밀번호 확인을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="button" value="취소" onclick="location.href='../home/main'" />
			</div>
			<div class="input">
				<input type="submit" value="회원가입" />
				<!-- <a href="#" onclick="history.back();">취소</a>   샘 코드 -->
				<!-- 				<input type="button" value="취소" onclick="history.back();"/> -->

			</div>
		</div>
	</form>
	<div class="blog-name">Welcome to my blog!</div>
</div>
<script>
var joinFormSubmitted = false;

function submitJoinForm(form) {
	if ( joinFormSubmitted ) {
		alert('가입 처리중입니다.');
		return;
	}
	form.loginId.value = form.loginId.value.trim();
	if ( form.loginId.value.length == 0 ) {
		alert('아이디를 입력해주세요.');
		form.loginId.focus();	
		return;
	}
	if ( form.loginId.value.length < 4 || form.loginId.value.length > 12 ) {
		alert('아이디를 4~12자까지 입력해주세요.');
		form.loginId.focus();
		return;
	}

	if ( form.loginId.value.indexOf(' ') != -1 ) {
		alert('아이디를 영문소문자와 숫자의 조합으로 입력해주세요.');
		form.loginId.focus();
		return;
	}
	

	form.name.value = form.name.value.trim();
	if ( form.name.value.length == 0 ) {
		alert('이름을 입력해주세요.');
		form.name.focus();
		return;
	}
	if ( form.name.value.length < 2 ) {
		alert('이름을 2자 이상 입력해주세요.');
		form.name.focus();
		return;
	}

	form.nickname.value = form.nickname.value.trim();
	if ( form.nickname.value.length == 0 ) {
		alert('닉네임을 입력해주세요.');
		form.nickname.focus();
		return;
	}
	if ( form.nickname.value.length < 2 ) {
		alert('닉네임을 2자 이상 입력해주세요.');
		form.nickname.focus();
		return;
	}

	form.loginPw.value = form.loginPw.value.trim();
	if ( form.loginPw.value.length == 0 ) {
		alert('비밀번호를 입력해주세요.');
		form.loginPw.focus();
		return;
	}
	if ( form.loginPw.value.length < 4 || form.loginPw.value.length > 12 ) {
		alert('로그인 비밀번호를 4~12자까지 입력해주세요.');
		form.loginPw.value = "";
		form.loginPwConfirm.value = "";
		form.loginPw.focus();
		return;
	}

	form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
	if ( form.loginPwConfirm.value.length == 0 ) {
		alert('비밀번호 확인을 입력해주세요.');
		form.loginPwConfirm.focus();
		return;
	}
	if ( form.loginPw.value != form.loginPwConfirm.value ) {
		alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요.');
		form.loginPw.value = "";
		form.loginPwConfirm.value = "";
		form.loginPw.focus();
		return;
	}
	form.loginPwReal.value = sha256(form.loginPw.value);  /* 암호화된 텍스트를 넘겨준다.*/
	form.loginPw.value = "";  /* 이 값은 비워준다. */
	
	form.submit();
	joinFormSubmitted = true;
}


</script>


<%@ include file="/jsp/part/foot.jspf"%>