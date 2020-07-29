package com.sbs.java.blog.dao;

import java.sql.Connection;

import com.sbs.java.blog.dto.Attr;
import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.SecSql;

public class AttrDao extends Dao {
	private Connection dbConn;

	public AttrDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	
	// ON DUPLICATE 중복될 때,  중복되는 키 
	public int setValue(String relTypeCode, int relId,String typeCode,String type2Code, String value) {
		SecSql sql = SecSql.from("INSERT INTO attr (regDate, updateDate, `relTypeCode`, `relId`, `typeCode`,  `type2Code`, `value`)");

		sql.append("VALUES (NOW(), NOW(), ? , ?, ?, ?, ? )", relTypeCode, relId, typeCode, type2Code, value);
		sql.append("ON DUPLICATE KEY UPDATE"); // UNIQUE를 걸어뒀기 때문에 중복될 수 없다. 중복된다면 update라도 해라. 무엇을? value를 
		sql.append("updateDate = NOW()");
		sql.append(", `value` = ?", value);

		return DBUtil.update(dbConn, sql);
	}
	
	//MySQL에 index를 걸어뒀다면 쿼리문 순서를 필히 index 지정 순서와 일치시켜야 한다. 그러지 않으면 걸어놓은 index가 적용되지 않는다.
	public String getValue(String relTypeCode, int relId,String typeCode,String type2Code) {
		SecSql sql = SecSql.from("SELECT `value` ");

		sql.append("FROM attr");
		sql.append("WHERE 1");
		sql.append("AND `relTypeCode` = ?", relTypeCode);
		sql.append("AND `relId` = ?", relId);
		sql.append("AND `typeCode` = ?", typeCode);
		sql.append("AND `type2Code` = ?", type2Code);

		return DBUtil.selectRowStringValue(dbConn, sql);
	}
	
	
	public Attr get(String relTypeCode, int relId,String typeCode,String type2Code) {
		SecSql sql = SecSql.from("SELECT *");

		sql.append("FROM attr");
		sql.append("WHERE 1");
		sql.append("AND `relTypeCode` = ?", relTypeCode);
		sql.append("AND `relId` = ?", relId);
		sql.append("AND `typeCode` = ?", typeCode);
		sql.append("AND `type2Code` = ?", type2Code);
		
		return new Attr(DBUtil.selectRow(dbConn, sql));
	}
	
	public int remove(String relTypeCode, int relId,String typeCode,String type2Code) {
		SecSql sql = SecSql.from("DELETE FROM attr");
		
		sql.append("WHERE 1");
		sql.append("AND `relTypeCode` = ?", relTypeCode);
		sql.append("AND `relId` = ?", relId);
		sql.append("AND `typeCode` = ?", typeCode);
		sql.append("AND `type2Code` = ?", type2Code);

		return DBUtil.delete(dbConn, sql);
	}
	
}
