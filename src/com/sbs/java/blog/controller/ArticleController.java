package com.sbs.java.blog.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.service.ArticleService;

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
		
				
		return "article/doWrite";
	}

	private String doActionDetail(HttpServletRequest req, HttpServletResponse resp) {
		int id = 0;
		if ( req.getParameter("id") != null ) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		
		Article article = articleService.getForPrintDetailArticle(id);
		req.setAttribute("article", article);
		return "article/detail";
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
		List<Article> articles = articleService.getForPrintListArticles(page, cateItemId);
		req.setAttribute("articles", articles);
		//최신 전체 게시물 리스트
		if ( cateItemId == 0 ) {
			articles = articleService.getForPrintListNewArticles();
			req.setAttribute("articles", articles);
		}
		
		
		
		

		// 게시물별 페이지 수
		int totalPage = articleService.doPaging(cateItemId);
		req.setAttribute("totalPage", totalPage);
		
		return "article/list";
	}
}
