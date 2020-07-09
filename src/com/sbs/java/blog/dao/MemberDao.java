package com.sbs.java.blog.dao;

import java.sql.Connection;

import com.sbs.java.blog.util.DBUtil;

public class MemberDao {
	private Connection dbConn;

	public MemberDao(Connection dbConn) {// 생성자에서 입력받고, super로 넘긴다.구조 개선으로 super 제거
		this.dbConn = dbConn;
	}

	public int getMemberJoin(String loginId, String name, String nickName, String loginPw) {
		System.out.println("memberDao");
		System.out.println(loginId);
		System.out.println(name);
		System.out.println(nickName);
		System.out.println(loginPw);
		

		String sql = "";

		sql += String.format("INSERT INTO `member` ");
		sql += String.format("SET regDate = NOW() ");
		sql += String.format(", loginId = '%s' ", loginId);
		sql += String.format(", name = '%s' ", name);
		sql += String.format(", nickname = '%s' ", nickName);
		sql += String.format(", loginPw = '%s' ", loginPw);

		System.out.println(sql);
		return DBUtil.insert(dbConn, sql);

	}
	
}
