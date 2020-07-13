package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;

public class ArticleService extends Service  {
	private ArticleDao articleDao;
	
	public ArticleService(Connection dbConn) {  // 생성자에 꼭 public을 붙여준다.
		//ArticleService에서 특별히 req, resp을 사용할 일은 없지만 긴급한 경우 화면에 출력하기 위함으로 넘겨 놓으면 좋다.
		articleDao = new ArticleDao(dbConn);
	}

	public List<Article> getForPrintListArticles(int page, int cateItemId, int itemsInAPage, String searchKeywordType, String searchKeywordTypeBody,  String searchKeyword) {
		return articleDao.getForPrintListArticles(page, cateItemId, itemsInAPage, searchKeywordType, searchKeywordTypeBody, searchKeyword );
	}

	public Article getForPrintArticle(int id) {
		return articleDao.getForPrintArticle(id);
	}

	/*public void doWriteArticle(String title, String body) {
		articleDao.doWriteArticle(title, body);
	}*/
	
	
	/*  없어도 되는 메서드 같음. 
	public int doPaging(int cateItemId) {
		return articleDao.doPaging(cateItemId);
	}/*

	/*public List<Article> getForPrintListNewArticles() {
		return articleDao.getForPrintListNewArticles();
	}*/

	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeywordTypeBody,  String searchKeyword) {
		return articleDao.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeywordTypeBody, searchKeyword);
	}

	/*public List<Article> getSearchContents(String contents) {
		return articleDao.getSearchContents(contents);
	}*/

	public List<CateItem> getForPrintCateItems() {
		return articleDao.getForPrintCateItems(); // 나중에 복잡해지면 얘도 할 일이 생긴다. 
	}

	public CateItem getCateItem(int cateItemId) {
		return articleDao.getCateItem(cateItemId);
	}

	public int write( int cateItemId, int displayStatus, String title, String body) {
		return articleDao.write( cateItemId, displayStatus,  title,  body);
		
	}


	public void articleModify(int id, int cateItemId, int displayStatus, String title, String body) {
		articleDao.articleModify(id, cateItemId, displayStatus, title, body);
	}

	public int articleDelete(int id) {
		return articleDao.articleDelete(id);
	}
	/*  없어도 되는 메서드 같음.
	public Article articleDetailForModify(int id) {
		return articleDao.articleDetailForModify(id);
	}*/ 
	
	/* 없어도 되는 메서드 같음.
	public Article getBeforIdForDetail(int id) {
		return articleDao.getBeforIdForDetail(id);
	} */

	public int getForPageMoveBeforeArticle(int id, int cateItemId) {
		return articleDao.getForPageMoveBeforeArticle(id, cateItemId);
	}

	public int getForPageMoveAfterArticle(int id, int cateItemId) {
		return articleDao.getForPageMoveAfterArticle(id, cateItemId);
	}

	public void increaseHit(int id) {
		articleDao.increaseHit(id);
		
	}

	public int getArticleReply(String body, int articleId) {
		return articleDao.getArticleReply(body, articleId);
	}

	public List<ArticleReply> getArticleRepliesForDetail(int articleId) {
		return articleDao.getArticleRepliesForDetail(articleId);
	}

	public void articleReplyDelete(int replyId) {
		articleDao.articleReplyDelete(replyId);
	}

	public void articleReplyModify(int replyId, String body) {
		articleDao.articleReplyModify(replyId, body);
	}


}
