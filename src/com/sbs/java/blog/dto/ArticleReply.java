package com.sbs.java.blog.dto;

import java.util.Map;

import lombok.Data;


@Data
public class ArticleReply extends Dto {
	private int articleId;
	private String body;
	private int memberId;
	private int displayStatus;
	
	public ArticleReply(Map<String, Object> row) {
		super(row);
		this.articleId = (int)row.get("articleId");
		this.body = (String)row.get("body");
		this.memberId = (int)row.get("memberId");
		this.displayStatus = (int)row.get("displayStatus");
		
	}
	

	public String getBodyForXTemplate() {
		return body.replaceAll("(?i)script", "<!--REPLACE:script-->").trim();
	}
	
}
