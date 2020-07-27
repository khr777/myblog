package com.sbs.java.blog.dto;

import java.util.Map;

public class ArticleReply extends Dto {
	private int articleId;
	private String body;
	private int memberId;
	private int displayStatus;
	
	public ArticleReply() {

	}
	
	public ArticleReply(Map<String, Object> row) {
		super(row);
		this.articleId = (int)row.get("articleId");
		this.body = (String)row.get("body");
		this.memberId = (int)row.get("memberId");
		this.displayStatus = (int)row.get("displayStatus");
		
	}
	
	
	@Override
	public String toString() {
		return "ArticleReply [articleId=" + articleId + ", body=" + body + ", memberId=" + memberId + ", displayStatus="
				+ displayStatus + ", getId()=" + getId() + ", getRegDate()=" + getRegDate() + ", getExtra()="
				+ getExtra() + ", getUpdateDate()=" + getUpdateDate() + "]";
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(int displayStatus) {
		this.displayStatus = displayStatus;
	}

	public String getBodyForXTemplate() {
		return body.replaceAll("(?i)script", "<!--REPLACE:script-->").trim();
	}
	
}
