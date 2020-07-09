package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.sbs.java.blog.dao.MemberDao;
import com.sbs.java.blog.dto.Member;

public class MemberService extends Service {
	private MemberDao memberDao;
	
	public MemberService(Connection dbConn) {  // 생성자에 꼭 public을 붙여준다.
		memberDao = new MemberDao(dbConn);
	}


	public int getMemberJoin(String loginId, String name, String nickName, String loginPw) {
		
		return memberDao.getMemberJoin(loginId, name, nickName, loginPw);
	}


	public List<Member> getForJoinMembers() {
		
		return memberDao.getForJoinMembers();
	}
}
