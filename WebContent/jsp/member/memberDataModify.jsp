<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>	

<h1>회원정보 수정페이지 이동 성공!</h1>

<div class="write-form-box">
	<form name="form" action="doMemberDataModify" method="POST" class="write-form form1" onsubmit="submitMemberModifyForm(this); return false;">
		<input type="hidden" name="loginPwReal"/>	
		<div class="form-row">
			<div class="label">로그인 아이디</div>
			<div class="input">
				<%=loginedMember.getLoginId()%>
			</div>
		</div>
		<div class="form-row">
			<div class="label">이름</div>
			<div class="input">
				<input name="name" type="text"  value="<%=loginedMember.getName()%>"/>
			</div>
		</div>
		<div class="form-row">
			<div class="label">닉네임</div>
			<div class="input">
				<input name="nickname" type="text"  value="<%=loginedMember.getNickname()%>"/>
			</div>
		</div>
		<div class="form-row">
			<div class="label">이메일</div>
			<div class="input">
				<input name="email" type="text"  value="<%=loginedMember.getEmail()%>"/>
			</div>
		</div>
		<div class="form-row">
			<div class="label">로그인 비밀번호</div>
			<div class="input">
				<input name="loginPw" type="password" placeholder="로그인 비밀번호를 입력해주세요." />				
			</div>
		</div>
		<div class="form-row">
			<div class="label">비밀번호 확인</div>
			<div class="input">
				<input name="loginPwConfirm" type="password" placeholder="로그인 비밀번호 확인을 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="submit" value="변경사항 저장" />
			</div>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="button" value="취소" onclick="location.href='myPage'" />
			</div>
		</div>
	</form>
	<div class="blog-name">harry.my.iu.gy</div>
</div>

<style>
/* lib   (나중에 다른 곳으로 옮길 예정이라셨음) */
.form1 {
	position:absolute;
	display: block;
	width: 400px;
	top:40%;
	left:50%;
	transform:translateX(-50%) translateY(-50%);
	
}

.form1 .form-row {
	padding:2px;
	align-items: center;
	display:flex;
	text-align:left;
	
	
	
}

.form1 .form-row>.label {
	width:60%;
	
	
}

.form1 .form-row>.input {
	flex-grow:1;
	width:110%;
}

.form1 .form-row .input > input {   /* 맨 위에 hidden으로 input을 1개 넣어서 자식 순서가 1씩 밀려버림... */
	border-radius:5px;
	
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
	top:80%;
	left:50%;
	transform:translateX(-50%);
	letter-spacing:10px;
	
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
	.write-form-box {
		border:none;
		width:20%;
		top:250px;
		left:42%;
		
		
	}
	.write-form-box .blank-box {
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

<script>
var modifyFormSubmitted = false;

function submitMemberModifyForm(form) {
	if ( modifyFormSubmitted ) {
		alert('변경사항 처리중입니다.');
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
	form.email.value = form.email.value.trim();
	if ( form.email.value.length == 0 ) {
		alert('이메일을 입력해주세요.');
		form.email.focus();
		return;
	}
	
	form.loginPwReal.value = sha256(form.loginPw.value);  /* 암호화된 텍스트를 넘겨준다.*/
	form.loginPw.value = "";  /* 이 값은 비워준다. */
	form.loginPwConfirm.value =""; // 로그인 비밀번호 확인도 함께 날려준다.  
	
	form.submit();
	modifyFormSubmitted = true;
}


</script>

<%@ include file="/jsp/part/foot.jspf"%>