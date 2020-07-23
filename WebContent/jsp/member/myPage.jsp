<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>	

<h1>마이페이지로 이동 성공!</h1>

<div class="memberData-box">
	<div class="memberData joinData ">회원정보</div>
		<table>
			<colgroup>
				<col width="180">
				<col width="230">
			</colgroup>
			<tbody>
				<tr>
					<th>기본정보</th>
					<th>가입정보</th>
				</tr>
				<tr>
					<th>이름</th>
					<td><%=loginedMember.getName()%></td>
				</tr>
				<tr>
					<th>가입일</th>
					<td><%=loginedMember.getRegDate()%></td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><%=loginedMember.getLoginId()%></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><%=loginedMember.getNickname()%></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><%=loginedMember.getEmail()%></td>
				</tr>
				<input type="button" onclick="location.href='memberDataModifyConfirm'" value="회원정보 변경"  />
			</tbody>
		</table>
		
</div>

<style>
h1 {
	margin-top:130px;
	margin-left:100px;
}
.memberData-box {
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%, -50%);
}

.memberData-box .joinData {
	font-size:1.5rem;
	font-weight:bold;
	margin-bottom:20px;
}

.memberData-box table  {
	padding:30px 30px 200px 30px;
	border:1px solid gold;
	
}


.memberData-box table td {
	text-align:center;
}

.memberData-box th, td {
	border:0.6px solid black;
	opacity: 0.5;
	height:40px;
}

.memberData-box input {
	position:absolute;
	bottom:20%;
	left:10%;
	right:10%;
	width:80%;
	height:10%;
	font-size:1.2rem;
	font-weight:bold;
}

</style>


<%@ include file="/jsp/part/foot.jspf"%>