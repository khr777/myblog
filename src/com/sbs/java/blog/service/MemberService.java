package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import com.sbs.java.blog.dao.MemberDao;
import com.sbs.java.blog.dto.Attr;
import com.sbs.java.blog.dto.Member;

public class MemberService extends Service {
	private MailService mailService;
	private MemberDao memberDao;
	private AttrService attrService;

	public MemberService(Connection dbConn, MailService mailService, AttrService attrService) { // 생성자에 꼭 public을 붙여준다.
		this.mailService = mailService;
		memberDao = new MemberDao(dbConn);
		this.attrService = new AttrService(dbConn);
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
	
	
	// 비밀번호만 변경하는 attr, 메서드, sql
	public String genModifyPrivateAuthCode(int actorId) {
		// java random 문자 생성 객체
		String authCode = UUID.randomUUID().toString();
				
		attrService.setValue("member__" + actorId + "__extra__modifyPrivateAuthCode", authCode);
		
		return authCode;
		
	}

	public boolean isValidModifyPrivateAuthCode(int actorId, String authCode) {
		String authCodeOnDB = attrService.getValue("member__" + actorId + "__extra__modifyPrivateAuthCode"); // DB에 있던 데이터를 가져온다는 의미 (변수명)
		return authCodeOnDB.equals(authCode);   // 입력받은 authCode와 db에서 가져온 데이터가 일치한다. true. 맞다.
	}

	public void modify(int actorId, String loginPw) {
		
		memberDao.modify(actorId, loginPw);
		
		
	}
	
	// 비밀번호 말고 나머지 개인정보 변경하는 attr, 메서드, sql
	/*
	 * public String genMemberDataModifyPrivateAuthCode(int actorId) {
	 * 
	 * String authCode = UUID.randomUUID().toString();
	 * 
	 * attrService.setValue("member__" + actorId +
	 * "__extra__modifyDataPrivateAuthCode", authCode);
	 * 
	 * return authCode; }
	 */
}
