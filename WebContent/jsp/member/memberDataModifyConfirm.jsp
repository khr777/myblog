<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="개인정보 유출 방지"></c:set>
<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>	

<div class="loginPwConfirm-box">
	<div>현재 비밀번호 입력</div>
	<input type="password" id="loginPw" />
	<input id="originLoginPw" type="hidden" value="${loginedMember.loginPw}"/>
	<input type="button" value="확인" onclick="checkBox();"/>	
</div>

<style>
.page-title {
	top:0;	
}

.loginPwConfirm-box {
	margin-top:130px;
	margin-left:300px;
	font-size:1.5rem;
}

.loginPwConfirm-box > input {
	margin-top:30px;
	height:30px;
	width:80%;
}


</style>

<script>
function checkBox() {
	var loginPw = sha256(document.getElementById("loginPw").value);
	var originLoginPw = document.getElementById("originLoginPw").value;
	
	if ( loginPw != originLoginPw ) {
		alert('비밀번호가 일치하지 않습니다.');
		loginPw.focus();
		return;
	}
	loginPw = "";
	originLoginPw = "";

	
	location.href="memberDataModify";			
}
</script>


<%@ include file="/jsp/part/foot.jspf"%>