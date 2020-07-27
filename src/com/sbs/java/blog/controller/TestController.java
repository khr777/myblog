package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Attr;

public class TestController extends Controller {
// 테스트 기능을 위한 클래스 
	public TestController(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}
	@Override //는 붙여주는게 좋다. 
	public String doAction() {
		switch ( actionMethodName ) {
		case "dbInsert":
			return doActionDbInsert();
		case "dbSelect":
			return doActionDbSelect();
		case "sendMail":
			return doActionSendMail();
		case "attr":
			return doActionAttr();
		case "attr2":
			return doActionAttr2();
		}

		return "";
	}

	private String doActionAttr() {
		attrService.setValue("member__1__tempPasswordExpireDate", "2020-07-02 12:12:12");
		String tempPasswordExpireDate = attrService.getValue("member__1__tempPasswordExpireDate");
		attrService.remove("member__1__tempPasswordExpireDate");
		return "html:" + tempPasswordExpireDate;
	}

	private String doActionAttr2() {
		attrService.setValue("member__1__tempPasswordExpireDate", "2020-07-02 12:12:12");
		Attr tempPasswordExpireDateAttr = attrService.get("member__1__tempPasswordExpireDate");
		attrService.remove("member__1__tempPasswordExpireDate");
		return "html:" + tempPasswordExpireDateAttr.getId();
	}

	private String doActionSendMail() {
		mailService.send("kim5638yw@gmail.com", "안녕하세요.", "반가워요^^");
		return "html:성공";
	}
	private String doActionDbInsert() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int id = -1;
		try {
			stmt = dbConn.prepareStatement(
					"INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = ?, body = ?, displayStatus = ?, cateItemId = ?",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "제목");
			stmt.setString(2, "내용");
			stmt.setInt(3, 1);
			stmt.setInt(4, 1);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return "html:" + id;
	}
	//insert와 select 똑같지만 차이점은 insert는 RETURN_GENERATED_KEYS); 들어가지만 select는 들어가지 않는다.
	private String doActionDbSelect() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String title = null;
		try {
			stmt = dbConn.prepareStatement(
					"SELECT title FROM article WHERE title LIKE CONCAT('%', ?, '%') ORDER BY id DESC LIMIT 1");
			stmt.setString(1, "제목");
			rs = stmt.executeQuery();
			if (rs.next()) {
				title = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return "html:" + title;
	}
	@Override
	public String getControllerName() {
		return "test";
	}


	
}
