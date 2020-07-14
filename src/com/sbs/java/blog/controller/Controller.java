package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.service.ArticleService;
import com.sbs.java.blog.service.MemberService;

public abstract class Controller {
	//private으로 하고 getters, setters 를 만들어서 사용해왔지만 코드가 길어지면서 일손을 줄이기 위함으로 사용.
	//protected : 외부에 오픈은 되지 않지만 자식에게 상속은 가능한.  자식이 자유롭게 변수를 사용할 수 있다.
	protected Connection dbConn;
	protected String actionMethodName;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	protected ArticleService articleService;
	protected MemberService memberService;
	
	public Controller(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		this.dbConn = dbConn;
		this.actionMethodName = actionMethodName;
		this.req = req;
		this.resp = resp;
		articleService = new ArticleService(dbConn);
		memberService = new MemberService(dbConn);
		
	}
	
	//beforeAction, afterAction 2개는 추상이 아닌 구상으로 빼놓으셨음. 그리고 { } 이렇게 하시는 것이 "객체지향 패턴"이라고 하셨음.
	public void beforeAction() {
		// [ 액션 전 실행 ]
		// [ 이 메서드는 모든 컨트롤러의 모든 액션이 실행되기 전에 실행된다. ] 굉장히 유연해진다.
		List<CateItem> cateItems = articleService.getForPrintCateItems();
		// beforeAction()  메서드에 만들어놓고 executeAction() 메서드에서 호출했기 때문에 모든 
		// jsp에서 cateItems 호출이 가능한 것이다.
		
		// article/list, detail 을 들어가도 home/main을 들어가도 어느 곳에 있던 게시물 카테고리에 연결이 가능해야 하므로. 
		// 모든 곳에서 사용해야 할 때 beforeAction()을 이용하면 된다. 
		req.setAttribute("cateItems", cateItems);
		
		List<Member> members = memberService.getForJoinMembers();
		req.setAttribute("members", members);
		
		/*    안되넹... 
		 * HttpSession session = req.getSession(); if ( session != null ) { int
		 * loginedMemberId = (int)session.getAttribute("loginedMemberId"); Member member
		 * = memberService.getForLogoutMember(loginedMemberId);
		 * req.setAttribute("loginMember", member); }
		 */
		
	}
	
	public void afterAction() {
		// [ 액션 후 실행 ]
	}
	
	
	public abstract String doAction();
	
	// 템플릿 메소드 (객체 지향 패턴으로써 중요하다, 나중에 차장보기 )
	public String executeAction() {
		beforeAction();
		String rs = doAction();
		afterAction();
		
		return rs;
	}
	
}
