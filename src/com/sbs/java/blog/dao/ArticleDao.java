package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.util.DBUtil;

public class ArticleDao {
	private Connection dbConn;
	private int itemInAPage;
	public ArticleDao(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public List<Article> getForPrintListArticles(int page, int cateItemId) {
		
		String sql = "";
		
		int itemInAPage = 10;   // 한 페이지에 담을 게시물 개수.
		this.itemInAPage = itemInAPage;
		int limitFrom = ( page - 1 ) * itemInAPage; 
		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE displayStatus = 1 ");
		if ( cateItemId != 0 ) {
			sql += String.format("AND cateItemId = %d ", cateItemId);
		}
		sql += String.format("ORDER BY id DESC ");
		sql += String.format("LIMIT %d, %d ", limitFrom, itemInAPage);
		// 이 쿼리를 위의 문장으로 바꿨음.sql += String.format("LIMIT 0, 5 "); //최신 5개 가져와라. (1, 5): 최신꺼 1개 건너띄고 5개 가져와라.
		
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Article> articles = new ArrayList<>();
		
		for ( Map<String, Object> row : rows ) {
			articles.add(new Article(row));
		}
		
		return articles;
	}

	public Article getForPrintDetailArticle(int id) {
		String sql = "";
		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE id = %d ", id);
		
		Map<String, Object> row = DBUtil.selectRow(dbConn, sql);
		
		return new Article(row);
	}

	public void doWriteArticle(String title, String body) {
		String sql = "";
		sql += String.format("INSERT INTO article ");
		sql += String.format("SET regDate = NOW() ");
		sql += String.format(", updateDate = NOW() ");
		sql += String.format(", title = '%s' ", title);
		sql += String.format(", body = '%s' ", body);
		
		DBUtil.insert(dbConn, sql);
	}

	public int doPaging(int cateItemId) {
	Map<String, Object> row = new HashMap<>();
		
		String sql = "";
		sql += String.format("SELECT COUNT(*) ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE cateItemid = %d ", cateItemId);
		
		row = DBUtil.selectRow(dbConn, sql);
		int totalCount = (int)row.get("COUNT(*)");
		int totalPage = (int)Math.ceil((double)totalCount / itemInAPage );
		System.out.println(totalPage);
		
		return totalPage;
	}

	public List<Article> getForPrintListNewArticles() {

		String sql = "";
		
		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("ORDER BY id DESC ");
		sql += String.format("LIMIT 10");
		
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Article> articles = new ArrayList<>();
		
		for ( Map<String, Object> row : rows ) {
			articles.add(new Article(row));
		}
		
		return articles;
	}
}
