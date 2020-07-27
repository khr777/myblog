package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.sbs.java.blog.dao.MemberDao;
import com.sbs.java.blog.dto.Member;

public class MemberService extends Service {
	private MailService mailService;
	private MemberDao memberDao;

	public MemberService(Connection dbConn, MailService mailService) { // 생성자에 꼭 public을 붙여준다.
		this.mailService = mailService;
		memberDao = new MemberDao(dbConn);
	}

	public int join(String loginId, String name, String nickName, String loginPw, String email, String authCode,
			String emailTitle, String emailBody) {
		int id = memberDao.join(loginId, name, nickName, loginPw, email, authCode);

		mailService.send(email, emailTitle, emailBody);

		return id;
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
	
	// 로그인 아이디 찾아오는 메서드
	public String getLookForLoginId(String name, String email) {

		return memberDao.getLookForLoginId(name, email);
	}
	
	// 로그인 아이디 찾기로 찾아온 아이디를 메일로 보내는 메서드.
	public void getLookForLoginId(String name, String email, String emailTitle, String emailBody) {

		mailService.send(email, emailTitle, emailBody);

	}

	public int getLookForLoginPw(String name, String loginId, String email) {
		return memberDao.getLookForLoginPw(name, loginId, email);
	}

	public void updateRandomPw(String email, int memberId, String randomPw, String emailTitle, String emailBody) {

		mailService.send(email, emailTitle, emailBody);

		memberDao.updateRandomPw(memberId, randomPw);
	}

	public void memberDataUpdate(String name, String nickname, String email, String loginPw, int id) {
		memberDao.memberDataUpdate(name, nickname, email, loginPw, id);
	}

	public int setAuthCodeForJoin(String authCode) {
		return memberDao.setAuthCodeForJoin(authCode);
	}
}
