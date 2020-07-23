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


	public int join(String loginId, String name, String nickName, String loginPw, String email) {
		
		return memberDao.join(loginId, name, nickName, loginPw, email);
	}


	public List<Member> getForJoinMembers() {
		
		return memberDao.getForJoinMembers();
	}


	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}


	public boolean isJoinableNickName(String nickName) {
		return memberDao.isJoinableNickName(nickName);
	}


	public boolean loginIdAndPwValid(String loginId, String loginPw) {
		return memberDao.loginIdAndPwValid(loginId, loginPw);
	}


	public Member getForLoginMember(String loginId) {
		return memberDao.getForLoginMember(loginId);
	}


	public Member getMemberFromMemberId(int loginedMemberId) {
		return memberDao.getMemberFromMemberId(loginedMemberId);
	}


	public boolean isJoinableEmail(String email) {
		return memberDao.isJoinableEmail(email);
	}


	public int getMemberIdByLoginIdAndLoginPw(String loginId, String loginPw) {
		return memberDao.getMemberIdByLoginIdAndLoginPw(loginId, loginPw);
	}


	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}


	public String getLookForLoginId(String name, String email) {
		return memberDao.getLookForLoginId(name, email);
	}


	public int getLookForLoginPw(String name, String loginId, String email) {
		return memberDao.getLookForLoginPw(name, loginId, email);
	}


	public void updateRandomPw(int memberId, String randomPw) {
		memberDao.updateRandomPw(memberId, randomPw);
	}


	public void memberDataUpdate(String name, String nickname, String email, String loginPw, int id) {
		memberDao.memberDataUpdate(name, nickname, email, loginPw, id);
	}
}
