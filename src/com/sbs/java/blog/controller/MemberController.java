package com.sbs.java.blog.controller;

import java.security.SecureRandom;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.util.Util;

public class MemberController extends Controller {
	// private String gmailId;
	// private String gmailPw;

	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
		// this.gmailId = gmailId;
		// this.gmailPw = gmailPw;

	}

	public void beforeAction() {
		// [ 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다. ]
		// [ 필요없다면 지워도 된다. ]
		super.beforeAction();
	}

	@Override
	public String doAction() {
		switch (actionMethodName) {
		case "join":
			return actionJoin(); // controller req, resp 나중에 뺀다고 하셨음.
		case "doJoin":
			return actionDoJoin();
		case "login":
			return actionLogin();
		case "doLogin":
			return actionDoLogin();
		case "doLogout": // 작업을 하는 jsp, 페이지가 아닌 잠깐 들렀다 이동하는 곳은 do를 붙인다!
			return actionDoLogout();
		case "lookForLoginId":
			return actionLookForLoginId();
		case "doLookForLoginId":
			return actionDoLookForLoginId();
		case "lookForLoginPw":
			return actionLookForLoginPw();
		case "doLookForLoginPw":
			return actionDoLookForLoginPw();
		case "myPage":
			return actionMyPage();
		case "passwordForPrivate":
			return actionPasswordForPrivate();
		case "memberDataModifyConfirm":
			return actionMemberDataModifyConfirm();
		case "memberDataModify":
			return actionMemberDataModify();
		case "doMemberDataModify":
			return actionDoMemberDataModify();
		case "doAuthEmail":
			return actionDoAuthEmail();
		case "getLoginIdDup":
			return actionGetLoginIdDup();
		case "doPasswordForPrivate":
			return actionDoPasswordForPrivate();
		case "memberDataForPrivate": // myPrivate 메뉴에서 회원정보 변경 페이지로 이동하는 uri (비밀번호 빼고)
			return actionMemberDataForPrivate();
		case "doMemberDataForPrivate":
			return actionDoMemberDataForPrivate();
		case "modifyPrivate":
			return actionModifyPrivate();
		case "doModifyPrivate":
			return actionDoModifyPrivate();
		case "emailAuthed":
			return actionEmailAuthed(); // 이메일 인증코드 재발송
		case "sendEmailAuthedAgain":
			return actionSendEmailAuthedAgain();
		}
		return "";
	}
	private String actionSendEmailAuthedAgain() {
		int id = 0;
		
		if (!Util.empty(req, "id") && Util.isNum(req, "id")) {

			id = Util.getInt(req, "id");
		}
		
		
		String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
		String ENGLISH_UPPER = ENGLISH_LOWER.toUpperCase();
		String NUMBER = "0123456789";

		// 랜덤을 생성할 대상 문자열
		String DATA_FOR_RANDOM_STRING = ENGLISH_LOWER + ENGLISH_UPPER + NUMBER;

		// 랜덤 문자열 길이
		int random_string_length = 10;

		String authCode = generate(DATA_FOR_RANDOM_STRING, random_string_length);
		
		
		memberService.sendEmailAuthedAgain(id, authCode );
		
		
		return String.format("html:<script> alert('이메일 인증 후 로그인해주세요.'); location.replace('../home/main') </script>");
	}

	private String actionEmailAuthed() {
		
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) {

			id = Util.getInt(req, "id");
		}
		
		
		Member member = memberService.getMemberById(id);
		
		req.setAttribute("member", member);

		return "member/emailAuthed.jsp";
	}

	private String actionMemberDataForPrivate() {

		return "member/memberDataForPrivate.jsp";

	}

	// 회원 정보변경(비밀번호 말고) , 필요한 다른 메서드 모두 새로 만들어야 할 듯 ㅠㅠ....
	private String actionDoMemberDataForPrivate() {
		String loginPw = req.getParameter("loginPwReal");

		Member loginedMember = (Member) req.getAttribute("loginedMember");
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (loginedMember.getLoginPw().equals(loginPw)) {
			// 감히 controller가 직접 authCode를 만들 수 없다. 데이터를 구워달라고 service한테 부탁해야 한다.
			String authCode = memberService.genModifyPrivateAuthCode(loginedMemberId);

			return String
					.format("html:<script> location.replace('memberDataModify?authCode=" + authCode + "'); </script>");
		}

		return String.format("html:<script> alert('비밀번호를 다시 입력해주세요.'); history.back(); </script>");
	}

	// 페이지를 보여주는데서만 처리하는게 아니라 정보를 받아서 저장하는 곳에서도 authCode 확인을 해준다.
	// 이렇게 하지 않으면 해커가 페이지 이동 단계를 건너뛰고 해킹할 수도 있다.
	private String actionDoModifyPrivate() {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		String authCode = req.getParameter("authCode");

		if (memberService.isValidModifyPrivateAuthCode(loginedMemberId, authCode) == false) {
			return String.format(
					"html:<script> alert('비밀번호를 다시 체크해주세요.'); location.replace('../member/passwordForPrivate') </script>");
		}

		String loginPw = req.getParameter("loginPwReal");

		memberService.modify(loginedMemberId, loginPw);
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		loginedMember.setLoginPw(loginPw); // 크게 의미는 없지만, 의미론적인 면에서 해야 하는
		// modify를 통해서 변경된 비밀번호를 db 저장은 하지만 현재 로그인 중인 회원의 비밀번호는 변경 전 비밀번호로 로그인 한 것이므로
		// 현재 로그인 중인 회원의 비밀번호를 setLoginPw를 통해서 셋팅해주는 것이다. 로그아웃 후 로그인하면 의미없어지는 것이지만.

		return String.format("html:<script> alert('비밀번호가 수정되었습니다.'); location.replace('../home/main') </script>");
	}

	private String actionModifyPrivate() {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		String authCode = req.getParameter("authCode");
		if (memberService.isValidModifyPrivateAuthCode(loginedMemberId, authCode) == false) {
			return String.format(
					"html:<script> alert('비밀번호를 다시 체크해주세요.'); location.replace('../member/passwordForPrivate') </script>");
		}

		return "member/modifyPrivate.jsp";
	}

	private String actionDoPasswordForPrivate() {
		String loginPw = req.getParameter("loginPwReal");

		Member loginedMember = (Member) req.getAttribute("loginedMember");
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (loginedMember.getLoginPw().equals(loginPw)) {
			// 감히 controller가 직접 authCode를 만들 수 없다. 데이터를 구워달라고 service한테 부탁해야 한다.
			String authCode = memberService.genModifyPrivateAuthCode(loginedMemberId);

			return String
					.format("html:<script> location.replace('modifyPrivate?authCode=" + authCode + "'); </script>");
		}

		return String.format("html:<script> alert('비밀번호를 다시 입력해주세요.'); history.back(); </script>");
	}

	private String actionPasswordForPrivate() {
		return "member/passwordForPrivate.jsp";
	}

	private String actionGetLoginIdDup() { // 이 메서드가 return 하는 값이 바로 아작스에 (data)안으로 들어간다.
		String loginId = req.getParameter("loginId");

		boolean isJoinableLoginId = memberService.isJoinableLoginId(loginId);

		if (isJoinableLoginId) {
			return "json:{\"msg\":\"사용할 수 있는 아이디 입니다.\", \"resultCode\": \"S-1\", \"loginId\":\"" + loginId + "\"}"; // 필히
																														// \"
																														// 을
																														// 붙여주어야
																														// 한다.
																														// ★
		} else {
			return "json:{\"msg\":\"이미 사용중인 아이디 입니다.\", \"resultCode\": \"F-1\", \"loginId\":\"" + loginId + "\"}"; // 필히
																													// \"
																													// 을
																													// 붙여주어야
																													// 한다.
																													// ★
		}
	}

	private String actionDoAuthEmail() {
		String authCode = "";

		if (!Util.empty(req, "authCode")) { // cateItemId가 없지 않고 숫자가 맞으면
			authCode = Util.getString(req, "authCode");
		}

		String email = "";
		if (!Util.empty(req, "email")) { // cateItemId가 없지 않고 숫자가 맞으면
			email = Util.getString(req, "email");
		}

		int memberId = 0;

		if (!Util.empty(req, "memberId") && Util.isNum(req, "memberId")) { // 게시물 번호
			memberId = Util.getInt(req, "memberId");
		}

		attrService.setValue("member__" + memberId + "__extra__emailAuthed", email);

		// memberService.setAuthCodeForJoin(authCode); 이메일 인증코드와 일치하는 발송했던 인증코드를 찾아서 인증
		// 여부를 1:true로 만드는 메서드.

		return "html:<script> alert('이메일 인증이 완료되었습니다. 로그인 후 이용해주세요.'); location.replace('../member/login'); </script>";
	}

	private String actionDoMemberDataModify() {

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		String authCode = req.getParameter("authCode");

		if (memberService.isValidModifyPrivateAuthCode(loginedMemberId, authCode) == false) {
			return String.format(
					"html:<script> alert('비밀번호를 다시 체크해주세요.'); location.replace('../member/memberDataForPrivate') </script>");
		}

		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String loginPw = req.getParameter("loginPwReal");

		memberService.memberDataUpdate(name, nickname, email, loginPw, loginedMemberId);

		return "html:<script> alert('회원정보가 변경되었습니다.'); location.replace('../home/main'); </script>";
	}

	private String actionMemberDataModifyConfirm() {

		return "member/memberDataModifyConfirm.jsp";
	}

	private String actionMemberDataModify() {

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		String authCode = req.getParameter("authCode");
		if (memberService.isValidModifyPrivateAuthCode(loginedMemberId, authCode) == false) {
			return String.format(
					"html:<script> alert('비밀번호를 다시 체크해주세요.'); location.replace('../member/memberDataForPrivate') </script>");
		}

		return "member/memberDataModify.jsp";
	}

	private String actionMyPage() {
		return "member/myPage.jsp";
	}

	// 임시 비밀번호를 받기 위한 SecureRandom 객체를 생성하는 메서드
	public static String generate(String DATA, int length) {
		SecureRandom random = new SecureRandom();

		if (length < 1)
			throw new IllegalArgumentException("length must be a positive number.");
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(DATA.charAt(random.nextInt(DATA.length())));
		}
		return sb.toString();
	}

	private String actionDoLookForLoginPw() {
		String name = req.getParameter("name");
		String loginId = req.getParameter("loginId");
		String email = req.getParameter("email");

		int memberId = memberService.getLookForLoginPw(name, loginId, email);

		if (memberId == -1) {
			return "html:<script> alert('일치하는 회원정보가 존재하지 않습니다.'); history.back(); </script>";
		}

		// 임시 비밀번호를 랜덤 생성하는 코드들
		String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
		String ENGLISH_UPPER = ENGLISH_LOWER.toUpperCase();
		String NUMBER = "0123456789";

		// 랜덤을 생성할 대상 문자열
		String DATA_FOR_RANDOM_STRING = ENGLISH_LOWER + ENGLISH_UPPER + NUMBER;

		// 랜덤 문자열 길이
		int random_string_length = 10;

		String randomPw = generate(DATA_FOR_RANDOM_STRING, random_string_length);

		String emailTitle = "harry's life 임시 비밀번호를 확인바랍니다.";
		String emailBody = "<h3>발송드린 암호는 임시 비밀번호 입니다.</h3><br>";
		emailBody += "<h3>로그인 후 비밀번호 변경하여 사용바랍니다.</h3><br><br>";
		emailBody += "<h1>임시 비밀번호 : " + randomPw + "</h1>";
		emailBody += "<html><body><h4><a href="
				+ "http://localhost:8081/blog/s/member/login>📣로그인 바로 가기 </a></h4></body></html>";
		memberService.updateRandomPw(email, memberId, randomPw, emailTitle, emailBody);
		/*
		 * MailService mailService = new MailService(gmailId, gmailPw, gmailId, "관리자");
		 * boolean sendMailDone = mailService.send(email, "임시 비밀번호를 확인바랍니다.",
		 * "발송드린 암호는 임시 비밀번호 입니다. 로그인 후 변경하여 사용바랍니다. \n" + "임시 비밀번호 : " + randomPw +
		 * "\n로그인 바로 가기 https://harry.my.iu.gy/blog/s/member/login") == 1;
		 * 
		 * resp.getWriter().append(String.format("발송성공 : %b", sendMailDone));
		 */
		return "html:<script> alert('가입하신 이메일로 임시 비밀번호를 발송드렸습니다.'); location.replace('../home/main'); </script>";
	}

	private String actionLookForLoginPw() {
		return "member/lookForLoginPw.jsp";
	}

	private String actionDoLookForLoginId() {

		String name = req.getParameter("name");
		String email = req.getParameter("email");

		String loginId = memberService.getLookForLoginId(name, email);
		if (loginId.length() == 0) {
			return "html:<script> alert('일치하는 회원정보가 존재하지 않습니다.'); history.back(); </script>";
		}
		String emailTitle = "요청하신 harry's life 회원가입 아이디를 발송해드립니다.";
		String emailBody = "";
		emailBody += "<h3>harry's life에 가입하신 로그인 아이디는</h3><br>";
		emailBody += "<h1>" + loginId + "</h1><br>입니다. <br><br>";
		emailBody += "<html><body><h4><a href="
				+ "http://localhost:8081/blog/s/member/login\">📣로그인 바로 가기 </a></h4></body></html>";
		memberService.getLookForLoginId(name, email, emailTitle, emailBody);

		return "html:<script> alert('가입하신 이메일로 로그인 아이디를 발송드렸습니다.'); location.replace('../home/main'); </script>";
	}

	private String actionLookForLoginId() {
		return "member/lookForLoginId.jsp";
	}

	private String actionDoLogout() {

		HttpSession session = req.getSession();
		int loginedMemberId = 0;
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		session.removeAttribute("loginedMemberId");

		String redirectUri = Util.getString(req, "redirectUri", "../home/main");

		return String.format("html:<script> alert('로그아웃 되었습니다.'); location.replace('" + redirectUri + "'); </script>");
	}

	private String actionDoLogin() {

		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");

		int loginedMemberId = memberService.getMemberIdByLoginIdAndLoginPw(loginId, loginPw);

		if (loginedMemberId == -1) {
			return "html:<script> alert('일치하는 정보가 없습니다.'); history.back(); </script>";
		}

		Member member = memberService.getMemberById(loginedMemberId);

		String emailAuthed = attrService.getValue("member__" + loginedMemberId + "__extra__emailAuthed");

		if (loginedMemberId != -1 && emailAuthed.length() == 0) {
			emailAuthed = member.getEmail();
			return "html:<script> alert('이메일 미인증 회원으로 인증 후 이용해주세요.'); location.replace('../member/emailAuthed?id=" + loginedMemberId + "'); </script>";
		}

		String useTempPassword = attrService.getValue("member__" + loginedMemberId + "__extra__useTempPassword");
		if ( useTempPassword.equals("1")) {
			session.setAttribute("loginedMemberId", loginedMemberId); // 최초 키값을 설정하는 코드(개별 저장소 생성)
			return "html:<script> alert('현재 임시패스워드를 사용중 입니다.'); location.replace('../home/main'); </script>";
		}
		
		
		
		
		session.setAttribute("loginedMemberId", loginedMemberId); // 최초 키값을 설정하는 코드(개별 저장소 생성)
		String redirectUri = Util.getString(req, "redirectUri", "../home/main");

		return String.format("html:<script> alert('로그인 되었습니다.'); location.replace('" + redirectUri + "'); </script>");
	}

	private String actionDoJoin() {
		String loginId = req.getParameter("loginId");
		String name = req.getParameter("name");
		String nickName = req.getParameter("nickname");
		String loginPw = req.getParameter("loginPwReal");
		String email = req.getParameter("email");

		boolean isJoinableLoginId = memberService.isJoinableLoginId(loginId);

		if (isJoinableLoginId == false) {
			return String.format("html:<script> alert('%s은(는) 이미 사용중인 아이디 입니다.'); history.back(); </script>", loginId);
		}

		boolean isJoinableNickName = memberService.isJoinableNickName(nickName);

		if (isJoinableNickName == false) {
			return String.format("html:<script> alert('%s은(는) 이미 사용중인 닉네임 입니다.'); history.back(); </script>", nickName);
		}

		boolean isJoinableEmail = memberService.isJoinableEmail(email);

		if (isJoinableEmail == false) {
			return String.format("html:<script> alert('%s은(는) 이미 사용중인 이메일 입니다.'); history.back(); </script>", email);
		}

		String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
		String ENGLISH_UPPER = ENGLISH_LOWER.toUpperCase();
		String NUMBER = "0123456789";

		// 랜덤을 생성할 대상 문자열
		String DATA_FOR_RANDOM_STRING = ENGLISH_LOWER + ENGLISH_UPPER + NUMBER;

		// 랜덤 문자열 길이
		int random_string_length = 10;

		String authCode = generate(DATA_FOR_RANDOM_STRING, random_string_length);

		int id = memberService.join(loginId, name, nickName, loginPw, email, authCode);

		return String.format("html:<script> alert('%s님, 환영합니다.'); location.replace('../home/main'); </script>", name);
	}

	private String actionLogin() {

		return "member/login.jsp";
	}

	private String actionJoin() {
		return "member/join.jsp";
	}

	@Override
	public String getControllerName() {
		return "member";
	}
}
