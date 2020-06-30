package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;
@WebServlet("/s/article/detail")
public class ArticleDetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		// DB 커넥터 로딩 시작
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.err.println("[ClassNotFoundException 예외]");
			System.err.println("msg : " + e.getMessage());
			response.getWriter().append("DB 커넥터 로딩 실패");
			return;   // ClassNotFoundException 오류 발생하면 여기서 끝내겠다.
		}
		// DB 커넥터 로딩 성공
		
		//DB 접속 시작 
		String url = "jdbc:mysql://site24.iu.gy:3306/site24?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site24";
		String password = "sbs123414";
		
		// 자바문법 7부터 생긴 것으로 괄호안에 써주면 따로 닫기를 안해줘도 된다.
		//자원이 사라진다고 객체가 사라지지 않아 프로그램 실행할수록 자원 소모되면서 느려졌던 것.
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			// DB 접속 성공
			int id = Integer.parseInt(request.getParameter("id"));
			Article article = getArticle(connection, id); 
			request.setAttribute("article", article);
			request.getRequestDispatcher("/jsp/home/detail.jsp").forward(request, response);
			
		} catch (SQLException e) {
			System.err.println("[SQLException 예외]");
			System.err.println("msg : " + e.getMessage());
			response.getWriter().append("DB 접속 실패");
			
			return;
		} /*finally {  // finally 는 try 성공, catch 실패 두 경우에 다 작동된다!
			
		}*/  //finally는 이제 없어도 된다. 
		
	}
	private Article getArticle(Connection connection, int id) {
		String sql = "";
		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE id = %d ", id);
		
		Map<String, Object> row = DBUtil.selectRow(connection, sql); 
		return new Article(row);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
