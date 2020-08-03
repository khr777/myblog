package com.sbs.java.blog.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.java.blog.config.Config;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.service.ArticleService;
import com.sbs.java.blog.service.AttrService;
import com.sbs.java.blog.service.MailService;
import com.sbs.java.blog.service.MemberService;
import com.sbs.java.blog.util.Util;

public abstract class Controller {
	// private으로 하고 getters, setters 를 만들어서 사용해왔지만 코드가 길어지면서 일손을 줄이기 위함으로 사용.
	// protected : 외부에 오픈은 되지 않지만 자식에게 상속은 가능한. 자식이 자유롭게 변수를 사용할 수 있다.
	protected Connection dbConn;
	protected String actionMethodName;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected ArticleService articleService;
	protected MemberService memberService;
	protected HttpSession session;	
	protected MailService mailService;
	protected AttrService attrService;
	
	
	public Controller(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		this.dbConn = dbConn;
		this.actionMethodName = actionMethodName;
		this.req = req;
		this.session = req.getSession();
		this.resp = resp;
		articleService = new ArticleService(dbConn);
		mailService = new MailService(Config.gmailId, Config.gmailPw, Config.mailFrom, Config.mailFromName);
		attrService = new AttrService(dbConn);
		memberService = new MemberService(dbConn, mailService, attrService);
			

	}
	// abstract 추상이니까 자식들은 구현할 수 밖에 없다. 
	public abstract String getControllerName();
	
	
	
	// ★ 여러곳에서 쓸 수 있는 기능을 "GNB"라고 표현한다.
	// beforeAction, afterAction 2개는 추상이 아닌 구상으로 빼놓으셨음. 그리고 { } 이렇게 하시는 것이 "객체지향
	// 패턴"이라고 하셨음.
	public void beforeAction() {
		// [ 액션 전 실행 ]
		// [ 이 메서드는 모든 컨트롤러의 모든 액션이 실행되기 전에 실행된다. ] 굉장히 유연해진다.
		List<CateItem> cateItems = articleService.getForPrintCateItems();
		// beforeAction() 메서드에 만들어놓고 executeAction() 메서드에서 호출했기 때문에 모든
		// jsp에서 cateItems 호출이 가능한 것이다.

		// article/list, detail 을 들어가도 home/main을 들어가도 어느 곳에 있던 게시물 카테고리에 연결이 가능해야 하므로.
		// 모든 곳에서 사용해야 할 때 beforeAction()을 이용하면 된다.
		req.setAttribute("cateItems", cateItems);

		List<Member> members = memberService.getForJoinMembers();
		req.setAttribute("members", members);

		// [ 사용자 관련 정보를 리퀘스트 객체에 정리해서 넣기. 굽기 ]
		/*
		 * 만인의 정보를 모든 액션을 실행하기 전인 beforAction에서 관련 정보를 굽는다. 구워서 request에 담는다. request는
		 * 모든 jsp에서 사용이 가능하다. (예시: request.getAttribute...~)
		 */
		int loginedMemberId = -1;
		boolean isLogined = false;
		Member loginedMember = null;
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			isLogined = true;
			loginedMember = memberService.getMemberByIdForSession(loginedMemberId); // 그냥 member가 아닌 session에 저장하는 용도.현재 로그인한 회원(메서드?가 살짝 달라야 한다._코드가)
		}
		req.setAttribute("loginedMember", loginedMember);
		req.setAttribute("isLogined", isLogined);
		req.setAttribute("loginedMemberId", loginedMemberId);
		
		
		
		// [현재 Uri]
		
		String currentUri = req.getRequestURI();
		
		if ( req.getQueryString() != null ) {
			currentUri += "?" +  req.getQueryString();
		}
		
		String encodedCurrentUri = Util.getUriEncoded(currentUri);
		
		
		
		// 현재 접속된 페이지와 관련된 유용한 정보 담기
		req.setAttribute("currentUri", currentUri);
		req.setAttribute("encodedCurrentUri", encodedCurrentUri);
		req.setAttribute("encodedAfterLoginRedirectUri", encodedCurrentUri);
		req.setAttribute("noBaseCurrentUri", req.getRequestURI().replace(req.getContextPath(), ""));
		
		// [ 로그인 페이지에서 로그인 페이지로 이동하는 버튼을 또 누른 경우
		// 기존 afterLoginRedirectUri 정보를 유지시키기 위한 로직 ]
		// 아래 로직이 의미하는 것 : login page에서 다시 login page로 돌아가는 것을 방지! 그리고 Uri이 길어지는 것을 방지! 
		if ( currentUri.contains("/s/member/login")) {
			String encodedOldAfterLoginRedirectUri = Util.getString(req, "afterLoginRedirectUri", "");
			encodedOldAfterLoginRedirectUri = Util.getUriEncoded(encodedOldAfterLoginRedirectUri);
			req.setAttribute("encodedAfterLoginRedirectUri", encodedOldAfterLoginRedirectUri);
		}
		
		//로그아웃 후 돌아가야 하는 곳, 기본적으로 현재 Uri
		req.setAttribute("encodedAfterLogoutRedirectUri", encodedCurrentUri);
		
		

	}

	public void afterAction() {
		// [ 액션 후 실행 ]
	}

	public abstract String doAction() throws IOException;

	// 템플릿 메소드 (객체 지향 패턴으로써 중요하다, 나중에 차장보기 )
	public String executeAction() throws IOException {
		beforeAction();
		
		// 입구 컷 작업
		String doGuardRs = doGuard(); // doGuardRs가 특별한 일이 없으면 통과, 통과해야 한다. null 이 통과라는 의미.

		if (doGuardRs != null) {
			return doGuardRs;
		}

		String rs = doAction();
		afterAction();

		return rs;
	}

	private String doGuard() {

		// 로그인 상태인지 확인
		boolean isLogined = (boolean)req.getAttribute("isLogined");
		
		// [ 로그인에 관련된 가드 시작 ]
		// 로그인이 필요한지 확인
		boolean needToLogin = false;

		String controllerName = getControllerName();
		
		// 임의로 정한 controllerName의 actionMethodName이 부합하다면 needToLogin은 true가 되고, 
		// 로그인 상태도 아니라면 로그인을 요청하는 코드이다. login, join을 함부로 추가하게 되면 
		// login, join을 하려고 접속했는데 로그인을 요청받는 불상사가 생길 수 있다. 
		switch(controllerName) {
		case "member":
			switch(actionMethodName) {
			case "doLogout":
				needToLogin = true;
				break;
			}
			break;
		case "article":
			switch(actionMethodName) {
			case "write":
			case "doWrite":
			case "modify":
			case "doModify":
			case "doDelete":
			case "doWriteReply":
			case "replyModify":
			case "doReplyModify":
			case "doReplyDelete":
				needToLogin = true;
				break;
			}
			break;
		}
		
		String encodedAfterLoginRedirectUri = (String)req.getAttribute("encodedAfterLoginRedirectUri");
		
		
		
		// 로그인이 필요한 상태이고 로그인한 상태가 아니라면?
		if ( needToLogin && isLogined == false ) { 
			return "html:<script>alert('로그인 후 이용해주세요.'); location.href = '../member/login?afterLoginRedirectUri=" + encodedAfterLoginRedirectUri + "'; </script>"; 
		}
		// [ 로그인에 관련된 가드 끝 ]
		
		
		
		//----------------------------------------------------------------------------------------------
		
		// [ 로그아웃에 관련된 가드 시작 ]
		// 로그아웃이 되어야 하는 상황
		boolean needToLogout = false;

		
		 
		switch(controllerName) {
		case "member":
			switch(actionMethodName) {
			case "login":
			case "join":
			case "doFindLoginId":
			case "doFindLoginPw":
			case "findAccount":
				needToLogout = true;
				break;
			}
			break;
		}
		
		
		// 로그아웃이 필요한 상태이고 로그인한 상태라면?
		if ( needToLogout && isLogined ) { 
			return "html:<script>alert('로그아웃 후 이용해주세요.'); history.back(); </script>"; 
		}
		// [ 로그아웃에 관련된 가드 끝 ]
		
		
		
		
		 
		return null;  // null 이라면 현재 "로그인 상태" 가 맞다. 
	}

}
