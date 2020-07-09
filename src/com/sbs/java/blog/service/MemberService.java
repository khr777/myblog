package com.sbs.java.blog.service;

import java.sql.Connection;


import com.sbs.java.blog.dao.MemberDao;

public class MemberService extends Service {
	private MemberDao memberDao;
	
	public MemberService(Connection dbConn) {  // 생성자에 꼭 public을 붙여준다.
		//ArticleService에서 특별히 req, resp을 사용할 일은 없지만 긴급한 경우 화면에 출력하기 위함으로 넘겨 놓으면 좋다.
		memberDao = new MemberDao(dbConn);
	}


	public int getMemberJoin(String loginId, String name, String nickName, String loginPw) {
		System.out.println("memberService");
		System.out.println(loginId);
		System.out.println(name);
		System.out.println(nickName);
		System.out.println(loginPw);
		
		return memberDao.getMemberJoin(loginId, name, nickName, loginPw);
	}
}
