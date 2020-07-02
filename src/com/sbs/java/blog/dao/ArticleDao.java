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

	public List<Article> getForPrintListArticles(int page, int cateItemId, int itemsInAPage) {
		
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

	public Article getForPrintArticle(int id) {
		String sql = "";
		sql += String.format("SELECT *, '김혜련' AS extra__writer "); // 만들어놓은 쿼리에는 작성자명을 담을 수가 없다. 그래서 'extra__' 를 적은 것.
		sql += String.format("FROM article ");				
		sql += String.format("WHERE 1 ");                       // 이유는 id 가 RPIMARY KEY라서 중복되는 값이 없기 때문이다. 그리고 순서를 바꿀 수 있는 이유는 WHERE 1을 걸어놨기 때문에 가능한 것이다.  
		sql += String.format("AND id = %d ", id);   // tip : displayStatus가 이 줄에 와도 상관없지만 검색 속도를 빠르게 하고 싶다면 id를 먼저 써주는 것이 좋다. 
		sql += String.format("AND displayStatus = 1 ");
		
		return new Article(DBUtil.selectRow(dbConn, sql));
	}

	public int doWriteArticle(String title, String body) {
		String sql = "";
		sql += String.format("INSERT INTO article ");
		sql += String.format("SET regDate = NOW() ");
		sql += String.format(", updateDate = NOW() ");
		sql += String.format(", cateItemId = 1 ");
		sql += String.format(", displayStatus = 1 ");
		sql += String.format(", title = '%s' ", title);
		sql += String.format(", body = '%s' ", body);
		
		return DBUtil.insert(dbConn, sql);
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

	public int getForPrintListArticlesCount(int cateItemId) {

		String sql = "";
		
		sql += String.format("SELECT COUNT(*) AS cnt ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE displayStatus = 1 ");
		if ( cateItemId != 0 ) {
			sql += String.format("AND cateItemId = %d ", cateItemId);
		}
		
		
		int count = DBUtil.selectRowIntValue(dbConn, sql);
		
		return count;
		
	}

	public List<Article> getSearchContents(String contents) {
		String sql = "";
		
		sql += String.format("SELECT * ");
		sql += String.format("FROM article ");
		sql += String.format("WHERE 1 ");
		sql += String.format("AND title LIKE '%%%s%%' ", contents);
		sql += String.format("OR `body` LIKE '%%%s%%' ", contents);
		sql += String.format("ORDER BY id DESC ");
		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Article> articles = new ArrayList<>();
		
		for ( Map<String, Object> row : rows ) {
			articles.add(new Article(row));
		}
		
		return articles;
		
	}
}
