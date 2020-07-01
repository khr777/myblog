package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;
@WebServlet("/s/article/list")
public class ArticleListServlet extends HttpServlet {
	
	
	//초반에 카테고리 없이 모든 게시물을 불러오기위한 메서드
	private List<Article> getArticles() {
		String url = "jdbc:mysql://site24.iu.gy:3306/site24?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site24";
		String password = "sbs123414";
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		String sql = "";
		
		List<Article> articles = new ArrayList<>();
		
		
		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("ORDER BY id DESC ");
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			
			List<Map<String, Object>> rows = DBUtil.selectRows(connection, sql);
			
			for (Map<String, Object> row : rows ) {
				articles.add(new Article(row));
			}
			
			
			
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
		
		return articles;
		
	}
	private List<Article> getCateItemArticles(int cateItemId, int page) {
	
		String sql = "";
		if ( page == 1 ) {   // a< href="/blog/s/~~~~~~cateItem=1, ~~~~~  
			sql += String.format("SELECT * ");
			sql += String.format("FROM article ");
			sql += String.format("WHERE displayStatus = 1 ");
			sql += String.format("AND cateItemid = %d ", cateItemId);
			sql += String.format("ORDER BY id DESC LIMIT 0, 5");  // 처음 5개 
		}                      // 카테고리 호버/클릭했을 때 이동하는 페이지의 카테고리 번호를 받아서 활용하면 할 수 있지 않을까? 
		if ( page == 2 ) {
			sql += String.format("SELECT * ");
			sql += String.format("FROM article ");
			sql += String.format("WHERE displayStatus = 1 ");
			sql += String.format("AND cateItemid = 2 ");  //  
			sql += String.format("ORDER BY id DESC LIMIT 5, 5");  //최근 5개 
		}
		
		//getArticles(); 메서드 복사 시작 
		String url = "jdbc:mysql://site24.iu.gy:3306/site24?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site24";
		String password = "sbs123414";
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		
		
		List<Article> articles = new ArrayList<>();
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			
			List<Map<String, Object>> rows = DBUtil.selectRows(connection, sql);
			
			for (Map<String, Object> row : rows ) {
				articles.add(new Article(row));
			}
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
		//  getArticles(); 메서드 복사 끝 
		return articles;
	}
	private int getAateItemIdNumByTotalArticles(int cateItemId) {
		String url = "jdbc:mysql://site24.iu.gy:3306/site24?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site24";
		String password = "sbs123414";
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		sql += String.format("SELECT COUNT(*) ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE cateItemid = %d ", cateItemId);
		try {
			//count를 못받고 있음 ㅠㅠ.... 
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int total = metaData.getColumnCount();
			System.out.println(total);
			
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
		return 0;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		// 아래 3줄은 기존에 사용한 코드로 다시 살려서 활용하기 //
		/*List<Article> articles = getArticles();
		
		request.setAttribute("articles", articles);
		request.getRequestDispatcher("/jsp/home/articles.jsp").forward(request, response);*/
		

		//여기서부터 추가함
		int cateItemId= Integer.parseInt(request.getParameter("cateItemId"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		//해당 카테고리 전체 게시물 개수 받아오는 메서드/int cateItemId값으로 이 카테고리 번호의 전체 게시물 개수를 쿼리문으로 받아오기 위함.
		int cateItemIdNumByTotalArticles = getAateItemIdNumByTotalArticles(cateItemId); 
		
		
		
		
		//카테고리별 게시물 5개 또는 임의 지정 개수만큼 게시물을 불러오는 리모콘
		List<Article> cateItemArticles = getCateItemArticles(cateItemId, page);
		request.setAttribute("cateItemArticles", cateItemArticles);
		
		
		request.getRequestDispatcher("/jsp/home/articles.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
