package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.List;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	public ArticleService(Connection dbConn) {  // 생성자에 꼭 public을 붙여준다. 
		articleDao = new ArticleDao(dbConn);
	}

	public List<Article> getForPrintListArticles(int page, int cateItemId, int itemsInAPage) {
		return articleDao.getForPrintListArticles(page, cateItemId, itemsInAPage);
	}

	public Article getForPrintArticle(int id) {
		return articleDao.getForPrintArticle(id);
	}

	public void doWriteArticle(String title, String body) {
		articleDao.doWriteArticle(title, body);
	}

	public int doPaging(int cateItemId) {
		return articleDao.doPaging(cateItemId);
	}

	public List<Article> getForPrintListNewArticles() {
		return articleDao.getForPrintListNewArticles();
	}

	public int getForPrintListArticlesCount(int cateItemId) {
		return articleDao.getForPrintListArticlesCount(cateItemId);
	}

	public List<Article> getSearchContents(String contents) {
		return articleDao.getSearchContents(contents);
	}

}
