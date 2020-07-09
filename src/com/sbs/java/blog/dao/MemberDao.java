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

	public int getMemberJoin(String loginId, String name, String nickName, String loginPw) {
		SecSql secSql = new SecSql();
		
		secSql.append("INSERT INTO `member` ");
		secSql.append("SET regDate = NOW() ");
		secSql.append(", loginId = ? ", loginId);
		secSql.append(", name = ? ", name);
		secSql.append(", nickname = ?", nickName);
		secSql.append(", loginPwReal = ?", loginPw);
		
		/*
		String sql = "";

		sql += String.format("INSERT INTO `member` ");
		sql += String.format("SET regDate = NOW() ");
		sql += String.format(", loginId = ? ", loginId);
		sql += String.format(", name = ? ", name);
		sql += String.format(", nickname = ?", nickName);
		sql += String.format(", loginPwReal = ?", loginPw);
		*/
		return DBUtil.insert(dbConn, secSql);

	}

	public List<Member> getForJoinMembers() {
		String sql = "";
		
		sql += String.format("SELECT * ");
		sql += String.format("FROM member ");
		
		
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Member> members = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			members.add(new Member(row));
		}
		return members;
	}
	
}
