package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.SecSql;

public class MemberDao {
	private Connection dbConn;

	public MemberDao(Connection dbConn) {// 생성자에서 입력받고, super로 넘긴다.구조 개선으로 super 제거
		this.dbConn = dbConn;
	}

	public int join(String loginId, String name, String nickName, String loginPw, String email) {
		SecSql secSql = new SecSql();
		
		secSql.append("INSERT INTO `member` ");
		secSql.append("SET regDate = NOW() ");
		secSql.append(", updateDate = NOW() ");
		secSql.append(", loginId = ? ", loginId);
		secSql.append(", name = ? ", name);
		secSql.append(", nickname = ?", nickName);
		secSql.append(", loginPw = ?", loginPw);
		secSql.append(", email = ?", email);
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

	
	
}

