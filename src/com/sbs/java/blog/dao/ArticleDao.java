package com.sbs.java.blog.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.DBUtil;
import com.sbs.java.blog.util.SecSql;

public class ArticleDao extends Dao {
	private Connection dbConn;
	private int itemInAPage;

	public ArticleDao(Connection dbConn) {// 생성자에서 입력받고, super로 넘긴다.구조 개선으로 super 제거
		this.dbConn = dbConn;
	}

	public List<Article> getForPrintListArticles(int page, int cateItemId, int itemsInAPage, String searchKeywordType,
			String searchKeywordTypeBody, String searchKeyword) {

		int itemInAPage = 10; // 한 페이지에 담을 게시물 개수. //샘 구조랑 조금 다른듯.. 내 구조에서는 이 3가지 코드 지우면 안됌.
		this.itemInAPage = itemInAPage;
		int limitFrom = (page - 1) * itemInAPage;
		SecSql sql = SecSql.from("SELECT A.*, M.nickname AS extra__writer");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.displayStatus = 1");
		if (cateItemId != 0) {
			sql.append("AND cateItemId = ?", cateItemId);
		}
		if (searchKeywordType.equals("title") && searchKeywordTypeBody.equals("body") && searchKeyword.length() > 0) {
			sql.append("AND title LIKE CONCAT('%', ? , '%')", searchKeyword);
			sql.append("OR body LIKE CONCAT('%', ? , '%')", searchKeyword);
		}
		sql.append("ORDER BY A.id DESC");
		sql.append("LIMIT ?, ? ", limitFrom, itemInAPage);

		// 이 쿼리를 위의 문장으로 바꿨음.sql += String.format("LIMIT 0, 5 "); //최신 5개 가져와라. (1, 5):
		// 최신꺼 1개 건너띄고 5개 가져와라.

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			articles.add(new Article(row));
		}

		return articles;
	}

	/*
	 * public Article getForPrintArticle(int id) { SecSql sql = new SecSql();
	 * sql.append("SELECT *, '김혜련' AS extra__writer "); // 만들어놓은 쿼리에는 작성자명을 담을 수가
	 * 없다. 그래서 'extra__' 를 적은 것. sql.append("FROM article ");
	 * sql.append("WHERE 1 "); // 이유는 id 가 RPIMARY KEY라서 중복되는 값이 없기 때문이다. 그리고 순서를 바꿀
	 * 수 있는 이유는 WHERE 1을 걸어놨기 // 때문에 가능한 것이다. sql.append("AND id = ? ", id); // tip
	 * : displayStatus가 이 줄에 와도 상관없지만 검색 속도를 빠르게 하고 싶다면 id를 먼저 써주는 것이 // 좋다.
	 * sql.append("AND displayStatus = 1 ");
	 * 
	 * return new Article(DBUtil.selectRow(dbConn, sql)); }
	 */
	public Article getForPrintArticle(int id) {
		SecSql sql = SecSql.from("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN member AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.displayStatus = 1");
		sql.append("AND A.id = ?", id);
		
		
		return new Article(DBUtil.selectRow(dbConn, sql));
		
	}

	
	
	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeywordTypeBody,
			String searchKeyword) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) AS cnt ");
		sql.append("FROM article ");
		sql.append("WHERE displayStatus = 1 ");
		if (cateItemId != 0) {
			sql.append("AND cateItemId = ? ", cateItemId);
		}

		if (searchKeywordType.equals("title") && searchKeywordTypeBody.equals("body") && searchKeyword.length() > 0) {
			sql.append("AND title LIKE CONCAT('%', ?, '%')", searchKeyword);
			sql.append("OR body LIKE CONCAT('%', ?, '%')", searchKeyword);
		}

		int count = DBUtil.selectRowIntValue(dbConn, sql);

		return count;

	}

	public List<CateItem> getForPrintCateItems() {
		SecSql sql = new SecSql();

		sql.append("SELECT * ");
		sql.append("FROM cateItem ");
		sql.append("WHERE 1 ");
		sql.append("ORDER BY id ASC "); // ASC : 만든 순서로(생성한 순서로)

		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<CateItem> cateItems = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			cateItems.add(new CateItem(row));
		}

		return cateItems;
	}

	public CateItem getCateItem(int cateItemId) {
		SecSql sql = new SecSql();

		sql.append("SELECT * ");
		sql.append("FROM cateItem ");
		sql.append("WHERE 1 ");
		sql.append("AND id = ? ", cateItemId);

		return new CateItem(DBUtil.selectRow(dbConn, sql));
	}

	public int write(int cateItemId, int displayStatus, String title, String body, int memberId) {
		SecSql secSql = new SecSql();

		secSql.append("INSERT INTO article");
		secSql.append("SET regDate = NOW()");
		secSql.append(", updateDate = NOW()");
		secSql.append(", title = ? ", title);
		secSql.append(", body = ? ", body);
		secSql.append(", displayStatus = ? ", displayStatus);
		secSql.append(", cateItemId = ?", cateItemId);
		secSql.append(", memberId = ?", memberId);

		return DBUtil.insert(dbConn, secSql);
	}

	public void modifyArticle(int id, int cateItemId, int displayStatus, String title, String body) {

		SecSql sql = SecSql.from("UPDATE article");

		sql.append("SET updateDate = NOW()");
		sql.append(", cateItemId = ?", cateItemId);
		sql.append(", title = ?", title);
		sql.append(", body = ? ", body);
		sql.append(", displayStatus = ?", displayStatus);
		sql.append(" WHERE id = ?", id);

		DBUtil.update(dbConn, sql);

	}

	public int deleteArticle(int id) {
		SecSql sql = SecSql.from("DELETE FROM article");

		sql.append("WHERE id = ? ", id);

		return DBUtil.delete(dbConn, sql);
	}

	public int getForPageMoveBeforeArticle(int id, int cateItemId) {
		SecSql sql = new SecSql();

		sql.append("SELECT id ");
		sql.append("FROM article ");
		sql.append("WHERE id < ? ", id);
		sql.append("AND displayStatus = 1 ");
		if (cateItemId != 0) {
			sql.append("AND cateItemId = ? ", cateItemId);
		}
		sql.append("ORDER BY id DESC ");
		sql.append("LIMIT 1");

		int articleId = DBUtil.selectRowIntValue(dbConn, sql);

		return articleId;
	}

	public int getForPageMoveAfterArticle(int id, int cateItemId) {
		SecSql sql = new SecSql();
		sql.append("SELECT id");
		sql.append("FROM article");
		sql.append("WHERE id > ?", id);
		sql.append("AND displayStatus = 1");
		if (cateItemId != 0) {
			sql.append("AND cateItemId = ?", cateItemId);
		}

		sql.append("ORDER BY id DESC ");
		sql.append("LIMIT 1");

		int articleId = DBUtil.selectRowIntValue(dbConn, sql);

		return articleId;
	}

	public int increaseHit(int id) {
		SecSql sql = SecSql.from("UPDATE article");
		sql.append("SET hit = hit + 1");
		sql.append("WHERE id = ?", id);

		return DBUtil.update(dbConn, sql);
	}

	public int getArticleReply(String body, int articleId, int memberId) {
		SecSql sql = SecSql.from("INSERT INTO articleReply");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", articleId = ?", articleId);
		sql.append(", `body` = ?", body);
		sql.append(", memberId = ?", memberId);

		return DBUtil.insert(dbConn, sql);
	}

	public List<ArticleReply> getArticleRepliesForDetail(int articleId) {
		
		
		SecSql sql = SecSql.from("SELECT A.*, M.nickname AS extra__writer");
		sql.append("FROM articleReply AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE articleId = ?", articleId);
		sql.append("ORDER BY A.id DESC");


		List<Map<String, Object>> rows = DBUtil.selectRows(dbConn, sql);
		List<ArticleReply> articleReplies = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			articleReplies.add(new ArticleReply(row));
		}
		return articleReplies;
	}

	
	public int articleReplyDelete(int id) {
		SecSql secSql = new SecSql();

		secSql.append("DELETE FROM articleReply ");
		secSql.append("WHERE id = ? ", id);

		return DBUtil.update(dbConn, secSql);
	}

	public void articleReplyModify(int id, String body) {
		SecSql sql = SecSql.from("UPDATE articleReply ");
		sql.append("SET updateDate = NOW()");
		sql.append(", body = ? ", body);
		sql.append(" WHERE id = ?", id);

		DBUtil.update(dbConn, sql);

	}
	
	
	/*
	 * public ArticleReply getArticleReplyForModify(int id, int memberId) { SecSql
	 * sql = SecSql.from("SELECT *"); sql.append("FROM articleReply");
	 * sql.append("WHERE id = ?", id);
	 * 
	 * return new ArticleReply(DBUtil.selectRow(dbConn, sql)); }
	 */
	
	

	public ArticleReply getArticleReplyForModify(int id, int memberId) {
		
		SecSql sql = SecSql.from("SELECT A.*");
		sql.append(", M.nickname AS extra__writer");
		sql.append("FROM articleReply AS A");
		sql.append("INNER JOIN member AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.id = ?", id);
		
		return new ArticleReply(DBUtil.selectRow(dbConn, sql));
	}

}
