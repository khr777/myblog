package com.sbs.java.blog.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.java.blog.dao.ArticleDao;
import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.Util;

public class ArticleService extends Service  {
	private ArticleDao articleDao;
	
	public ArticleService(Connection dbConn) {  // 생성자에 꼭 public을 붙여준다.
		//ArticleService에서 특별히 req, resp을 사용할 일은 없지만 긴급한 경우 화면에 출력하기 위함으로 넘겨 놓으면 좋다.
		articleDao = new ArticleDao(dbConn);
	}

	public List<Article> getForPrintListArticles(int actorId, int page, int cateItemId, int itemsInAPage,  String searchKeywordType, String searchKeywordTypeBody,  String searchKeyword) {
		List<Article> articles = articleDao.getForPrintListArticles(page, cateItemId, itemsInAPage, searchKeywordType, searchKeywordTypeBody, searchKeyword );
		
		for ( Article article : articles ) { // 꼬리표를 달아준다. 이 게시물을 이 활동자가 삭제할 수 있는지에 대한.
			updateArticleExtraDataForPrint(article, actorId);
		}
		
		return articles;
	}

	private void updateArticleExtraDataForPrint(Article article, int actorId) {
		boolean deleteAvailable = Util.isSuccess(getCheckRsDeleteAvailable(article, actorId));
		article.getExtra().put("deleteAvailable", deleteAvailable);
		
		
		boolean modifyAvailable = Util.isSuccess(getCheckRsModifyAvailable(article, actorId));
		article.getExtra().put("modifyAvailable", modifyAvailable);
		
		
	}
	
	

	

	public Article getForPrintArticle(int id, int actorId) {
		Article article = articleDao.getForPrintArticle(id); 
		updateArticleExtraDataForPrint(article, actorId);
		
		return article;
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


	public void modifyArticle(int id, int cateItemId, int displayStatus, String title, String body) {
		articleDao.modifyArticle(id, cateItemId, displayStatus, title, body);
	}

	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
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

	
	private Map<String, Object> getCheckRsModifyAvailable(Article article, int actorId) {
		return getCheckRsDeleteAvailable(article, actorId);
	}
	
	
	private Map<String, Object> getCheckRsDeleteAvailable(Article article, int actorId) {
		Map<String, Object> rs = new HashMap<>();
		
		
		if ( article == null ) {
			rs.put("resultCode", "F-1");
			rs.put("msg", "존재하지 않는 게시물 입니다.");
			
			return rs;
		}
		
		if ( article.getMemberId() != actorId ) {
			rs.put("resultCode", "F-2");
			rs.put("msg", "게시물 삭제 권한이 없습니다.");
			
			return rs;
		}
		
		rs.put("resultCode", "S-1");
		rs.put("msg", "작업이 가능합니다.");
		
		return rs;
	}
	
	public Map<String, Object> getCheckRsDeleteAvailable(int id, int actorId) {
		Article article = articleDao.getForPrintArticle(id);
		
		return getCheckRsDeleteAvailable(article, actorId);
	}
	
	public Map<String, Object> getCheckRsModifyAvailable(int id, int actorId) {
		
		return getCheckRsDeleteAvailable(id, actorId);
	}
	
	
}
