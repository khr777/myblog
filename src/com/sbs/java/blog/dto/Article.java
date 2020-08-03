package com.sbs.java.blog.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Article extends Dto {
	private int cateItemId;
	private String title;
	private String body;
	private int hit;
	private int memberId;

	public Article() {

	}

	// row들의 값들을 형변환 해주는 이유는 Map은 Object 타입을 리턴하기 때문!!
	// 필요한, 맞는 타입으로 꼭 형변환을 해주어야 한다!
	public Article(Map<String, Object> row) {
		super(row);
		this.cateItemId = (int) row.get("cateItemId");
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.hit = (int)row.get("hit");
		this.memberId = (int)row.get("memberId");

	}
	
	
	
	@Override
	public String toString() {
		return "Article [cateItemId=" + cateItemId + ", title=" + title + ", body=" + body + ", hit=" + hit
				+ ", memberId=" + memberId + ", getId()=" + getId() + ", getRegDate()=" + getRegDate() + ", getExtra()="
				+ getExtra() + ", getUpdateDate()=" + getUpdateDate() + "]";
	}

	
	public String getBodyForXTemplate() {
		return body.replaceAll("(?i)script", "<!--REPLACE:script-->").trim();
	}

	public String getSummary() {
		String summary = this.getBody();
		
		summary = summary.replace("-", ""); // - 없애기
		summary = summary.replace("!", ""); // ! 없애기
		summary = summary.replace("#", ""); // # 없애기
		summary = summary.replace("*", ""); // * 없애기
		summary = summary.replaceAll("`", ""); // * 없애기
		summary = summary.replaceAll("<([^>]+)>", "");   //html 태그 제거 정규식.

		summary = summary.replaceAll("\\(.*?\\)", ""); // (~) 없애기
		summary = summary.replaceAll("\\[.*?\\]", ""); // [~] 없애기
		
		

		return summary;
	}
}
