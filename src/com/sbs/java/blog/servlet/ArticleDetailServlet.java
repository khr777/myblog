package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String url = "jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "root";
		String password = "";
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		String sql = "";
			
		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE id = %d ", id);
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		Article article = null;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			
			Map<String, Object> row = DBUtil.selectRow(connection, sql);
			article = new Article(row);
		} catch (ClassNotFoundException e) {
			System.err.println("[ClassNotFoundException 예외]");
			System.err.println("msg : " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("[SQLException 예외]");
			System.err.println("msg : " + e.getMessage());
		}
		finally {
			if ( connection != null ) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.println("[SQLException 예외]");
					System.err.println("mag : " + e.getMessage());
				}
			}
		}
		
		request.setAttribute("article", article);
		request.getRequestDispatcher("/jsp/home/detail.jsp").forward(request, response);
		
		
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
