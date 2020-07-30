package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.SecSql;

public class MemberDao {
	private Connection dbConn;

	public MemberDao(Connection dbConn) {// 생성자에서 입력받고, super로 넘긴다.구조 개선으로 super 제거
		this.dbConn = dbConn;
	}

	public int join(String loginId, String name, String nickName, String loginPw, String email, String authCode) {
		SecSql secSql = new SecSql();
		
		secSql.append("INSERT INTO `member` ");
		secSql.append("SET regDate = NOW() ");
		secSql.append(", updateDate = NOW() ");
		secSql.append(", loginId = ? ", loginId);
		secSql.append(", name = ? ", name);
		secSql.append(", nickname = ?", nickName);
		secSql.append(", loginPw = ?", loginPw);
		secSql.append(", email = ?", email);
		secSql.append(", mailAuthCode =?", authCode);
		secSql.append(", mailAuthStatus = 0");
		return DBUtil.insert(dbConn, secSql);

	}

	public List<Member> getForJoinMembers() {
		SecSql sql = new SecSql();
		//String sql = "";
		
		sql.append("SELECT * ");
		sql.append("FROM member ");
		
		
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Member> members = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			members.add(new Member(row));
		}
		return members;
	}

	public boolean isJoinableLoginId(String loginId) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");  // 여기서부터 시작하겠다는 의미.
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		
		
		return DBUtil.selectRowIntValue(dbConn, sql) == 0 ;
	}

	public boolean isJoinableNickName(String nickName) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");  // 여기서부터 시작하겠다는 의미.
		sql.append("FROM `member`");
		sql.append("WHERE nickname = ?", nickName);
		
		
		return DBUtil.selectRowIntValue(dbConn, sql) == 0 ;
	}
	
	public boolean isJoinableEmail(String email) {
		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");  // 여기서부터 시작하겠다는 의미.
		sql.append("FROM `member`");
		sql.append("WHERE email = ?", email);
		
		
		return DBUtil.selectRowIntValue(dbConn, sql) == 0 ;
	}

	public boolean loginIdAndPwValid(String loginId, String loginPw) {
		
		return false;
	}

	public Member getForLoginMember(String loginId) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		 return new Member(DBUtil.selectRow(dbConn, sql));
	}

	public Member getMemberFromMemberId(int loginedMemberId) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE id = ?", loginedMemberId);
		 return new Member(DBUtil.selectRow(dbConn, sql));
	}

	public int getMemberIdByLoginIdAndLoginPw(String loginId, String loginPw) {
		SecSql sql = SecSql.from("SELECT id");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		sql.append("AND loginPw = ?", loginPw);
		
		return DBUtil.selectRowIntValue(dbConn, sql);
	}

	public Member getMemberById(int id) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE id = ?", id);
		
		return new Member(DBUtil.selectRow(dbConn, sql));
	}

	public String getLookForLoginId(String name, String email) {
		SecSql sql = SecSql.from("SELECT loginId");
		sql.append("FROM `member`");
		sql.append("WHERE name = ?", name);
		sql.append("AND email = ?", email);
		
		return DBUtil.selectRowStringValue(dbConn, sql);
		//return new Member(DBUtil.selectRow(dbConn, sql));
	}

	public int getLookForLoginPw(String name, String loginId, String email) {
		
		SecSql sql = SecSql.from("SELECT id");
		sql.append("FROM `member`");
		sql.append("WHERE name = ?", name);
		sql.append("AND loginId = ?", loginId);
		sql.append("AND email = ?", email);
		
		return DBUtil.selectRowIntValue(dbConn, sql);
	}

	public int updateRandomPw(int memberId, String randomPw) {
		SecSql sql = SecSql.from("UPDATE `member`");

		sql.append("SET updateDate = NOW()");
		sql.append(", loginPw = SHA2(? , 256)", randomPw);
		sql.append(" WHERE id = ?", memberId);
		
		
		return DBUtil.update(dbConn, sql);
	}

	public int memberDataUpdate(String name, String nickname, String email, String loginPw, int id) {
		SecSql sql = SecSql.from("UPDATE `member`");

		sql.append("SET updateDate = NOW()");
		sql.append(", name = ?", name);
		sql.append(", nickname = ?", nickname);
		sql.append(", email = ?", email);
		sql.append(", loginPw = ?", loginPw);
		sql.append("WHERE id = ?", id);
		
		
		return DBUtil.update(dbConn, sql);
	}

	public int setAuthCodeForJoin(String authCode) {
		SecSql sql = SecSql.from("UPDATE `member`");
		sql.append("SET mailAuthStatus = 1");
		sql.append("WHERE mailAuthCode = ?", authCode);
		
		
		
		return DBUtil.update(dbConn, sql);
	}

	public void modify(int actorId, String loginPw) {
		
		SecSql sql = SecSql.from("UPDATE `member`");
		sql.append("SET updateDate = NOW()");
		sql.append(", loginPw = ?", loginPw);
		sql.append("WHERE id = ? ", actorId);
		
		DBUtil.update(dbConn, sql);
	}

	
	
}


