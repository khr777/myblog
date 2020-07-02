package com.sbs.java.blog.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.service.ArticleService;
import com.sbs.java.blog.util.Util;

public class ArticleController extends Controller {
	private ArticleService articleService;  //처음에 깡통이 들어있으니까 생성자를 통해서 객체 생성해준다.
	public ArticleController(Connection dbConn) {
		articleService = new ArticleService(dbConn);
	}

	//상속받은 아이는 부모의 추상메서드를 무조건(싫어도) 상속받아서 구현해야 한다. 
	public String doAction(String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		switch ( actionMethodName ) {
		case "list":
			return doActionList(req, resp);
		case "detail":
			return doActionDetail(req, resp);
		case "doWrite":
			return doActionDoWrite(req, resp);
		case "aboutMe":
			return doActionAboutMe(req, resp);
		}
		
		return "";
	}

	private String doActionAboutMe(HttpServletRequest req, HttpServletResponse resp) {
		// 경로 패스하는거 밖에 하는게 없었음.
		return "article/aboutMe";
	}

	private String doActionDoWrite(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		
		articleService.doWriteArticle(title, body);
		

		req.setAttribute("title", title);
		req.setAttribute("body", body);
		
				
		return "article/doWrite.jsp";
	}

	private String doActionDetail(HttpServletRequest req, HttpServletResponse resp) {
		if ( Util.empty(req, "id")) {
			return "plain:id를 입력해주세요.";
		}
		if ( Util.isNum(req, "id") == false) {
			return "plain:id를 정수로 입력해주세요.";
		}
		int id = Util.getInt(req, "id");
		
		// 이 article은 그냥(평범한) article이 아니다. 
		Article article = articleService.getForPrintArticle(id); // sql 쿼리에 작성해놓은 정보들만이 아닌 부가적으로 추가한 자잘한 (항목 추가한)작성자 등 항목 모두 불러오는 메서드 네임
		req.setAttribute("article", article);
		//return "article/detail.jsp";
		
		//작성자명이 게시물 상세보기에서 잠깐 필요한데 필드에까지 추가할 필요가 없다. 그래서 extra__를 이용해서 사용한다. sql에는 영향을 주지 않는 듯. 확인해보기.
		return "article/detail.jsp";
		// 다시 한번 설명!   자질구래한 것들을 모아놓는 것이 dto의 extra 변수이다. 
	}

	private String doActionList(HttpServletRequest req, HttpServletResponse resp) {
	
		
		
		
		
		//카테고리별 게시물 리스트
		int cateItemId = 0;
		int page = 1;
		if ( req.getParameter("cateItemId") != null ) {
			cateItemId = Integer.parseInt(req.getParameter("cateItemId"));
			
		}
		if ( req.getParameter("page") != null ) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		
		int itemsInAPage = 10; // 게시물 리스트에 보여줄 게시물 개수
		int totalCount = articleService.getForPrintListArticlesCount(cateItemId);
		int totalPage = (int)Math.ceil(totalCount / (double)itemsInAPage);
		
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);
		
		
		List<Article> articles = articleService.getForPrintListArticles(page, cateItemId, itemsInAPage);
		req.setAttribute("articles", articles);
		
		//검색 기능
		
		String search = req.getParameter("search");
		if ( search != null ) {
			articles = articleService.getSearchContents(search);
			req.setAttribute("articles", articles);
		}
		
		
		
		//최신 10개 게시물 리스트
		if ( search == null ) {
			if ( cateItemId == 0 ) {
				articles = articleService.getForPrintListNewArticles();
				req.setAttribute("articles", articles);
				
			}
		}
		
		req.setAttribute("cateItemId", cateItemId);
		
		
	
		
		
		
		
		
		
		
		

		return "article/list.jsp";
	}
}
