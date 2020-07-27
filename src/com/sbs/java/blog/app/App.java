package com.sbs.java.blog.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.config.Config;
import com.sbs.java.blog.controller.ArticleController;
import com.sbs.java.blog.controller.Controller;
import com.sbs.java.blog.controller.HomeController;
import com.sbs.java.blog.controller.MemberController;
import com.sbs.java.blog.controller.TestController;
import com.sbs.java.blog.exception.SQLErrorException;
import com.sbs.java.blog.util.Util;

public class App { //loadDriver()를 접고 다음 메서드를 보면 편하다.
	private HttpServletRequest req; 
	private HttpServletResponse resp;
	
	public App(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}
	private void loadDriver(HttpServletRequest req, HttpServletResponse resp) throws IOException  {


		// DB 커넥터 로딩 시작
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.err.printf("[ClassNotFoundException 예외, %s]\n", e.getMessage());
			resp.getWriter().append("DB 드라이버 클래스 로딩 실패");
			//try/catch를 해줘야 하는데 하지 않고 메서드에 'throws IOExeption'을 해주므로써 후임이 일을 제대로 못하면 선임이 깨지게 된다(App().start();가 선임)
			return;
		}
		// DB 커넥터 로딩 성공
	}
	private String getDbUri() {  //이렇게 하는 이유 : 나중에 개발, 운영하는 sql정보를 달리해야 한다. 개발할 때는 다른 접속 정보로 로그인해서 테스트하면 된다. 데이터를 지우고 삭제하고 등등을 반복하기 때문인 듯
		return "jdbc:mysql://site24.iu.gy:3306/site24?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
		//&zeroDateTimeBehavior=convertToNull (String Uri 에 추가해준 이 코드가 입력되지 않은 쿼리 값을 오류 발생시키지 않고 null 값으로 처리해준다.
	}
	
	private String getId() {
		return "site24";
	}
	
	private String getDbPassword() {
		return "sbs123414";
	}
	
	public void start() throws ServletException, IOException  {
		
		//Config 구성
		if (req.getServletContext().getInitParameter("gmailId") != null ) {
			Config.gmailId = (String)req.getServletContext().getInitParameter("gmailId");
		}
		
		
		if ( req.getServletContext().getInitParameter("gmailPw") != null ) {
			Config.gmailPw = (String)req.getServletContext().getInitParameter("gmailPw");
		}
		
		
		
		
		
		// [ DB드라이버 로딩 ]
		loadDriver(req, resp);
		
		
		// [ DB 접속정보 세팅 ]   ★ 거대한 프로그램 하나가 있다?? 그것은 잘못된 것이다. (테스트, 개발용을 구분해서 프로그램 관리를 해야 한다!)
		// 그래서 필요한 프로그램에 대한 정보들을 아래 Uri, user, password에 딸깍 끼워서 편리하게 구분해서 활용할 수 있다.
		String Uri = getDbUri(); 
		String user = getId();
		String password = getDbPassword();
		
		
		Connection dbConn = null;
		
		// try/catch 문에서 올바른 접속을 하고 route 메서드를 통해서 일을 한다. route 안에 원래 일을 노나줌. 보기 쉽다.
		try  {
			// = DB 접속 성공 =
			dbConn = DriverManager.getConnection(Uri, user, password);
			
			// [ 올바른 컨트롤러로 라우팅 ] : 올바른 길로 인도한다
			route(dbConn, req, resp); //1. (한 곳에서 다른 곳으로 가기 위해 따라가는) 길, 노선 
			
		} catch (SQLException e) {
			Util.printEx("SQL 예외(커넥션 열기)", resp, e);
			//return;      catch 문에도 해당 메서드 마지막에 위치하고 있어서 더 실행될 것이 없으므로 쓰지 않아도 된다. 
		} catch (SQLErrorException e) {   // ← 이 catch를 추가해줌 (200707 15:32)  
			Util.printEx(e.getMessage(), resp, e.getOrigin());
		} catch (Exception e) {
			Util.printEx("기타 예외", resp, e);
			//return;
		}
		finally { // try/DB 접속 성공한 후에 finally에서 DB접속 연결이 끊어지니까 그 전에, 위에 할 일을 해야 한다.
			if ( dbConn != null ) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					Util.printEx("SQL 예외(커넥션 닫기)", resp, e);
					//finally 마지막에는 실패해도 return 할 필요 없다.
				}
			}
		}

	}																			// 선임 믿고 가는 것. 처리해달라고 넘기는 것.
	private void route(Connection dbConn, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8"); // 클라이언트 요청 받을 때에는 이 코드를 꼭 써줘야행 흐어ㅠㅠㅠㅠㅠ
		
		
		String contextPath = req.getContextPath();
		String requestURI = req.getRequestURI();
		String actionStr = requestURI.replace(contextPath + "/s/", "");
		String[] actionStrBits = actionStr.split("/");
		String controllerName = actionStrBits[0];
		String actionMethodName = actionStrBits[1];
		Controller controller = null; // 변수를 	그냥 만들지 말고 무조건 null 을 만들자!

		switch (controllerName) {
		case "article":
			controller = new ArticleController(dbConn, actionMethodName, req, resp);
			break;
		case "member":
			controller = new MemberController(dbConn, actionMethodName, req, resp);
			break;
		case "home":
			controller = new HomeController(dbConn, actionMethodName, req, resp);
			break;
		case "test":
			controller = new TestController(dbConn, actionMethodName, req, resp);
			
		}
		if (controller != null) {
			String actionResult = controller.executeAction();
			
			if ( actionResult.equals("")) {
				resp.getWriter().append("액션의 결과가 없습니다.");
			}
			else if ( actionResult.endsWith(".jsp")) { // jsp 길로 인도 
				String	viewPath = "/jsp/" + actionResult;
				req.getRequestDispatcher(viewPath).forward(req, resp);
			}
			else if ( actionResult.startsWith("html:")) { // html에서 지정한 방법?으로 입력하지 않으면 문제 해결을 위해 화면에 출력하기 위한 용도
				resp.getWriter().append(actionResult.substring(5));
			}
			else {
				resp.getWriter().append("처리할 수 없는 액션 결과입니다.");
			}
		} 
		else {
			resp.getWriter().append("존재하지 않는 페이지 입니다.");
		}
		
	}

}
