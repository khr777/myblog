package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.controller.ArticleController;
import com.sbs.java.blog.controller.Controller;
import com.sbs.java.blog.controller.HomeController;
import com.sbs.java.blog.controller.MemberController;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.Util;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		
		// DB 커넥터 로딩 시작
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.err.printf("[ClassNotFoundException 예외, %s]\n", e.getMessage());
			resp.getWriter().append("DB 드라이버 클래스 로딩 실패");
			return;
		}
		// DB 커넥터 로딩 성공

		// DB 접속 시작                             //&zeroDateTimeBehavior=convertToNull (String url 에 추가해준 이 코드가 입력되지 않은 쿼리 값을 오류 발생시키지 않고 null 값으로 처리해준다.
		String url = "jdbc:mysql://site24.iu.gy:3306/site24?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";
		String user = "site24";
		String password = "sbs123414";
		Connection dbConn = null;
		try  {
			// DB 접속 성공
			dbConn = DriverManager.getConnection(url, user, password);
			String contextPath = req.getContextPath();
			String requestURI = req.getRequestURI();
			String actionStr = requestURI.replace(contextPath + "/s/", "");
			String[] actionStrBits = actionStr.split("/");
			String controllerName = actionStrBits[0];
			String actionMethodName = actionStrBits[1];
			Controller controller = null; // 변수를 	그냥 만들지 말고 무조건 null 을 만들자!

			switch (controllerName) {
			case "article":
				controller = new ArticleController(dbConn);
				break;
			case "member":
				controller = new MemberController();
				break;
			case "home":
				controller = new HomeController(dbConn);
				break;
				
			}
			if (controller != null) {
				String actionResult = controller.doAction(actionMethodName, req, resp);
				if ( actionResult.equals("")) {
					resp.getWriter().append("ERROR, CODE 1");
				}
				else if ( actionResult.endsWith(".jsp")) {
					String	viewPath = "/jsp/" + actionResult;
					req.getRequestDispatcher(viewPath).forward(req, resp);
				}
				else if ( actionResult.startsWith("plain:")) {
					resp.getWriter().append(actionResult.substring(6));
				}
				else {
					resp.getWriter().append(actionResult);
				}
				
			} 
			else {
				resp.getWriter().append("존재하지 않는 페이지 입니다.");
			}
			
		} catch (SQLException e) {
			Util.printEx("SQL 예외(커넥션 열기)", resp, e);
			
			//return;      catch 문에도 해당 메서드 마지막에 위치하고 있어서 더 실행될 것이 없으므로 쓰지 않아도 된다. 
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


	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
