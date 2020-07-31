package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Attr;

public class TestController extends Controller {
// 테스트 기능을 위한 클래스 
	public TestController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}

	@Override // 는 붙여주는게 좋다.
	public String doAction() {
		switch (actionMethodName) {
		case "dbInsert":
			return actionDbInsert();
		case "dbSelect":
			return actionDbSelect();
		case "sendMail":
			return actionSendMail();
		case "attr":
			return actionAttr();
		case "attr2":
			return actionAttr2();
		case "attr91":
			return actionAttr11();
		case "attr1234":
			return actionAttr12();
		case "date":
			return actionDate();
		}

		return "";
	}

	// 연도 비교 구상 코드
	private String actionDate() {

		String date19 = "2020-07-31";
		String date20 = "2021-07-31";

		int date19a = Integer.parseInt(date19.substring(0, 4));
		int date20a = Integer.parseInt(date20.substring(0, 4));

		System.out.println("date19a : " + date19a);
		System.out.println("date20a : " + date20a);

		if (date19a + 1 == date20a) {
			System.out.println("연도 비교 성공");
		}

		return "html:오늘날짜 :  ";
	}

	// 혜련 테스트 중...
	private String actionAttr11() {

		// attr 변수 만들 데이터 합쳐서 DB 저장하기 <template 여기서부터 시작>

		int memberId = 2;

		String relTypeCode = "member";
		int relId = memberId;
		String typeCode = "extra";
		String type2Code = "tempPasswordExpireDate";
		String value = "2020-07-01 12:12:12";

		String name = relTypeCode + "__" + relId + "__" + typeCode + "__" + type2Code;

		attrService.setValue(name, value);

		// attr 변수 만들 데이터 합쳐서 DB 저장하기 <template 여기서부터 끝>

		// attr 변수명으로 DB 데이터에서 attr 불러오기 ( type2Code 명 )
		// 불러온 attr에 저장된 정보들을 get으로 받아서 활용할 수 있음
		// 사용할 수 있는 것들 (member/article 관련_id_typeCode_활용 용도 그리고 regDate, updateDate)
		Attr tempPasswordExpireDate = attrService.get(name);

		return "html:" + tempPasswordExpireDate.getRegDate() + tempPasswordExpireDate.getId();
	}

	private String actionAttr12() {

		// 샘 코드
		attrService.setValue("member__1__extra__tempPasswordExpireDate", "2020-07-02 12:12:12");
		Attr tempPasswordExpireDateAttr = attrService.get("member__1__extra__tempPasswordExpireDate");
		// attrService.remove("member__1__extra__tempPasswordExpireDate");
		return "html:" + tempPasswordExpireDateAttr.getRegDate() + tempPasswordExpireDateAttr.getId();
	}

	private String actionAttr() {
		attrService.setValue("member__1__common__tempPasswordExpireDate", "2020-07-02 12:12:12");
		String tempPasswordExpireDate = attrService.getValue("member__1__common__tempPasswordExpireDate");
		// attrService.remove("member__1__common__tempPasswordExpireDate");
		return "html:" + tempPasswordExpireDate;
	}

	// extra(member의 부가적인 데이터_회원한테 임시비밀번호를 발송한 날짜를 기억해야 한다_회원이 100년 후에 이 메일을 봤을 때
	// 안된다고 하기 위해)
	private String actionAttr2() {
		attrService.setValue("member__1__extra__tempPasswordExpireDate", "2020-07-02 12:12:12");
		Attr tempPasswordExpireDateAttr = attrService.get("member__1__extra__tempPasswordExpireDate");
		attrService.remove("member__1__extra__tempPasswordExpireDate");
		return "html:" + tempPasswordExpireDateAttr.getId();
	}

	private String actionSendMail() {
		mailService.send("kim5638yw@gmail.com", "안녕하세요.", "반가워요^^");
		return "html:성공";
	}

	private String actionDbInsert() {
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

	// insert와 select 똑같지만 차이점은 insert는 RETURN_GENERATED_KEYS); 들어가지만 select는 들어가지
	// 않는다.
	private String actionDbSelect() {
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
