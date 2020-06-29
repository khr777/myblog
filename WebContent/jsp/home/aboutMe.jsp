<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>

/* aboutMe 시작 */
.profile-box-1 {
	position: absolute;
	
}

.profile-img-box {
	width: 240px;
	height: 240px;
	border-radius: 30%;
	overflow: hidden;
	text-align: center;
	padding-bottom: 10px;
}

.profile {
	width: 80%;
	height: 100%;
	object-fit: cover;
}

.profile-box-1>.profile-name {
	font-size: 2rem;
	text-align: center;
	font-weight: bold;
	color: black;
}

.profile-text {
	padding-left: 80px;
	padding-top: 30px;
	padding-right: 60px;
	font-weight: bold;
}

.profile-text>p {
	white-space: nowrap;
}
.football {
	font-size:1.6rem;
}
@media ( max-width:799px) {
	.profile-box-1 {
		 flex-flow:column wrap;
	}
	.profile-img-box {
		margin-left:23%;
	}
}


</style>


<div class="con ">
	<div class="profile-box-1 flex flex-jc-c absolute-center absolute-middle ">
		<div class="profile-name">
			<div class="profile-img-box ">
				<img class="profile" src="../../resource/img/profile.jpg" alt="">
			</div>
			예비 아기개발자
		</div>
		<div class="profile-text">
			<p class="name">이름 : 김혜련</p>
			<p class="age">나이 : 30세(만 29세)</p>
			<p class="hobby">취미 : 영화, 독서, 축구<span class="football">⚽</span></p>
			<p class="special-ability">특기 : 세상 열정</p>
			<p class="hope">장래희망 : 웹, 앱 개발자</p>
			<p class="dream">꿈 : 한적한 시골, 조용한 곳에서 웹개발하기</p>
		</div>
	</div>

</div>
<%@ include file="/jsp/part/foot.jspf"%>