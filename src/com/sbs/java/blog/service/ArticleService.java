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

	public int getForPrintListArticlesCount(int cateItemId, String searchKeywordType, String searchKeywordTypeBody,  String searchKeyword) {
		return articleDao.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeywordTypeBody, searchKeyword);
	}


	public List<CateItem> getForPrintCateItems() {
		return articleDao.getForPrintCateItems(); // 나중에 복잡해지면 얘도 할 일이 생긴다. 
	}

	public CateItem getCateItem(int cateItemId) {
		return articleDao.getCateItem(cateItemId);
	}
	
	//articleService에서는 controller가 넘겨주는 값이 어떤 값인지(예시:loginedMemberId) 알지 못한다. dto에 선언된 변수명으로 통일해서 넘기는게 좋다!
	public int write( int cateItemId, int displayStatus, String title, String body, int memberId) {
		return articleDao.write( cateItemId, displayStatus,  title,  body, memberId);
		
	}


	public void articleModify(int id, int cateItemId, int displayStatus, String title, String body) {
		articleDao.articleModify(id, cateItemId, displayStatus, title, body);
	}

	public int articleDelete(int id) {
		return articleDao.articleDelete(id);
	}
	
	public int getForPageMoveBeforeArticle(int id, int cateItemId) {
		return articleDao.getForPageMoveBeforeArticle(id, cateItemId);
	}

	public int getForPageMoveAfterArticle(int id, int cateItemId) {
		return articleDao.getForPageMoveAfterArticle(id, cateItemId);
	}

	public void increaseHit(int id) {
		articleDao.increaseHit(id);
		
	}

	public int getArticleReply(String body, int articleId, int memberId) {
		return articleDao.getArticleReply(body, articleId, memberId);
	}

	public List<ArticleReply> getArticleRepliesForDetail(int articleId) {
		return articleDao.getArticleRepliesForDetail(articleId);
	}

	public void articleReplyDelete(int id) {
		articleDao.articleReplyDelete(id);
	}

	public void articleReplyModify(int id, String body) { 
		articleDao.articleReplyModify(id, body);
	}

	public ArticleReply getArticleReplyForModify(int id, int memberId) {
		return articleDao.getArticleReplyForModify(id, memberId);
	}

	


}
