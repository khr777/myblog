package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Member;
	
	public class MemberController extends Controller {

		
		
	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}
	
	public void beforeAction() {
		// [ 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다. ]
		// [ 필요없다면 지워도 된다. ]
		super.beforeAction();
	}
	
	
	@Override
	public String doAction() {
		switch ( actionMethodName ) {
		case "join":
			return doActionJoin(req, resp);
		case "doJoin":
			return doActionDoJoin(req, resp);
		case "login":
			return doActionLogin(req, resp);
		case "idCheck":
			return doActionIdCheck(req, resp);
		}
		return "";
	}

	private String doActionIdCheck(HttpServletRequest req, HttpServletResponse resp) {
		
		return null; 
	}

	private String doActionDoJoin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String name = req.getParameter("name");
		String nickName = req.getParameter("nickname");
		String loginPw = req.getParameter("loginPwReal");
		
		List<Member> members = memberService.getForJoinMembers();
		for ( Member member : members ) {
			if ( member.getLoginId().equals(loginId)) {
				return "html:<script> alert('" + loginId + "는(은) 중복되는 아이디로 사용할 수 없습니다.'); history.back(); </script>";
			}
			if ( member.getNickname().equals(nickName)) {
				return "html:<script> alert('" + nickName + "는(은) 중복되는 닉네임으로 사용할 수 없습니다.'); history.back(); </script>";
			}
		}
		
		int id = memberService.getMemberJoin(loginId, name, nickName, loginPw);
		
		
		
		return "html:<script> alert('" + name + "님 회원가입을 축하드립니다.'); location.replace('../home/main'); </script>";
	}

	private String doActionLogin(HttpServletRequest req, HttpServletResponse resp) {

		return "member/login.jsp";
	}

	private String doActionJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "member/join.jsp";
	}
}
