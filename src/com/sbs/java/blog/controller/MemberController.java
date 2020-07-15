package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.sbs.java.blog.dto.Member;

public class MemberController extends Controller {

	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
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
			return doActionJoin(); // controller req, resp 나중에 뺀다고 하셨음.
		case "doJoin":
			return doActionDoJoin();
		case "login":
			return doActionLogin();
		case "doLogin":
			return doActionDoLogin();
		case "doLogout":   // 작업을 하는 jsp, 페이지가 아닌 잠깐 들렀다 이동하는 곳은 do를 붙인다! 
			return doActionDoLogout(); 
		}
		return "";
	}

	private String doActionDoLogout() {

		HttpSession session = req.getSession();
		int loginedMemberId = 0;
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		System.out.println("logout_id : " + loginedMemberId);

		session.removeAttribute("loginedMemberId");

		return "html:<script> alert('로그아웃 되었습니다.'); location.replace('../home/main'); </script>";
	}

	private String doActionDoLogin() {

		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");

		boolean isJoinableLoginId = memberService.isJoinableLoginId(loginId);

		if (isJoinableLoginId) {
			return String.format("html:<script> alert('%s은(는) 존재하지 않는 아이디 입니다.'); history.back(); </script>", loginId);
		}

		int loginedMemberId = memberService.getMemberIdByLoginIdAndLoginPw(loginId, loginPw);

		if (loginedMemberId == -1) {
			return "html:<script> alert('일치하는 정보가 없습니다.'); history.back(); </script>";
		}

		session.setAttribute("loginedMemberId", loginedMemberId); // 최초 키값을 설정하는 코드(개별 저장소 생성)

		return String.format("html:<script> alert('로그인 되었습니다.'); location.replace('../home/main'); </script>");
	}

	private String doActionDoJoin() {
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

		int id = memberService.join(loginId, name, nickName, loginPw, email);

		return String.format("html:<script> alert('%s님, 환영합니다.'); location.replace('../home/main'); </script>", name);
	}

	private String doActionLogin() {

		return "member/login.jsp";
	}

	private String doActionJoin() {
		return "member/join.jsp";
	}

	@Override
	public String getControllerName() {
		return "member";
	}
}
